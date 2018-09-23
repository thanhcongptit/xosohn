/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.soicaupro.thongkebacnho.CommonUtil;

import inet.bean.LotteryCompany;
import inet.bean.LotteryResult;
import inet.bean.News;
import inet.bean.TanSuatXuatHien;
import inet.constant.Constants;
import inet.model.LotteryCompanyDAO;
import inet.model.LotteryResultDAO;
import inet.request.LotteryRequest;
import inet.util.Contant;
import inet.util.RequestUtil;

/**
 *
 * @author hanhlm
 */
public class ThongKeTanSuatController extends BaseController {

	private List<News> listLotteryNews = null;
	
	private static String sDDMMYYYY = null;

	@Override
	protected void loadBase() {
		super.loadBase(); // To change body of generated methods, choose Tools | Templates.

		if (sDDMMYYYY == null || !ddmmyyyy.equals(sDDMMYYYY)) {
			sDDMMYYYY = ddmmyyyy;
		}
	}


	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mod = super.handleRequestInternal(request, response);
		LotteryRequest lotteryRequest = new LotteryRequest();
		String code = RequestUtil.getString(request, "code", "");
		boolean isURLDefault = false;
		
		String url_ca = "";
		if(code.isEmpty()) {
			isURLDefault = true;
			code = "xsmb";
		}
		
		String codes[] = code.split("-");
		code = codes[codes.length-1];
		
		int biendo = RequestUtil.getInt(request, "biendo", 30);
		
		if(biendo > 365) {
			biendo = 365;
		} else if(biendo< 5) {
			biendo =5;
		}
		
		LotteryCompanyDAO lotteryCompanyDAO = new LotteryCompanyDAO();
		LotteryCompany lotteryCompany = lotteryCompanyDAO.findCompanyCode(code);
		LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
		
		List<LotteryCompany> lotteryCompanies = lotteryCompanyDAO.findLotteryCompany("MT");
		List<LotteryCompany> lotteryCompanies1 = lotteryCompanyDAO.findLotteryCompany("MN");
		lotteryCompanies.addAll(lotteryCompanies1);
		mod.addObject("lotteryCompanies", lotteryCompanies);
		
		code = code.toUpperCase().trim();
		String codeXS = code;
		String categoryNews = "";
		
		if(code.equalsIgnoreCase("xsmb")) {
			categoryNews = "MIEN BAC";
			codeXS = "XSTD";
			lotteryCompany = new LotteryCompany();
			lotteryCompany.setCode("XSMB");
			lotteryCompany.setCodeLowerCase("xsmb");
			lotteryCompany.setCompany("MIỀN BẮC");
			lotteryCompany.setCompanyLink("MIEN-BAC");
			lotteryCompany.setRegion("MB");
			
		} 
		
		if(isURLDefault) {
			url_ca = "/thong-ke-tan-suat-lo-to.html";
		} else {
			url_ca = "/thong-ke-tan-suat-lo-to-"+lotteryCompany.getCompanyLink().toLowerCase()+"-"+lotteryCompany.getCodeLowerCase()+".html";
		}
		mod.addObject("url_ca", Constants.URL_CAN + url_ca);
		
		categoryNews = lotteryCompany.getCompanyLink().replaceAll("-", " ").toUpperCase();
		
		List<LotteryResult> lotteryResults = lotteryResultDAO.findResultByCode(codeXS.toUpperCase(), biendo, new BigDecimal("1000000"));
		
		List<TanSuatXuatHien> dacBietTanSuat = new ArrayList<TanSuatXuatHien>();
		List<TanSuatXuatHien> lotoTanSuat = new ArrayList<TanSuatXuatHien>();
		
		caculateTanSuat(lotteryResults, dacBietTanSuat, lotoTanSuat);
		
		String dbChuaVe = getChuaVe(dacBietTanSuat);
		String lotoChuaVe = getChuaVe(lotoTanSuat);
				
				
		Collections.sort(dacBietTanSuat);
		Collections.sort(lotoTanSuat);
		
		List<TanSuatXuatHien> dbNhieuNhat = dacBietTanSuat.subList(0, 10);
		List<TanSuatXuatHien> lotoNhieuNhat = lotoTanSuat.subList(0, 10);
		
		List<TanSuatXuatHien> dbItNhat = new ArrayList<>();
		getDbItNhat(dbItNhat, dacBietTanSuat);
		
		List<TanSuatXuatHien> lotoItNhat = new ArrayList<>();
		getDbItNhat(lotoItNhat, lotoTanSuat);
		
		listLotteryNews = lotteryRequest.parserLotteryNewsProvince(Contant.SITE_ID, "1", Contant.MAX_NEWS, categoryNews);
		mod.addObject("listLotteryNews", listLotteryNews);
		//lo gan
		mod.addObject("code", code.toUpperCase());
		mod.addObject("dbChuaVe", dbChuaVe);
		mod.addObject("lotoChuaVe", lotoChuaVe);
		mod.addObject("dbNhieuNhat", dbNhieuNhat);
		mod.addObject("lotoNhieuNhat", lotoNhieuNhat);
		mod.addObject("dbItNhat", dbItNhat);
		mod.addObject("lotoItNhat", lotoItNhat);
		mod.addObject("biendo", biendo);
		
		mod.addObject("categoryNews", categoryNews);
		mod.addObject("lotteryCompany", lotteryCompany);
		mod.addObject("company", lotteryCompany);
		mod.addObject("activeMenu", 7);
		
		mod.setViewName("/thongke_tan_suat");

		return mod;
	}

	private String getChuaVe(List<TanSuatXuatHien> tanSuat) {
		StringBuilder sb = new StringBuilder("");
		
		for(TanSuatXuatHien tanSuatXuatHien : tanSuat) {
			if(tanSuatXuatHien.getCount() == 0) {
				sb.append(tanSuatXuatHien.getLoto()).append(", ");
			}
		}
		
		String rs = sb.toString();
		if(rs.length() > 0) {
			rs = rs.substring(0, rs.length()-2);
		}
		
		return rs;
	}


	//lay ra 10 phan tu nhieu nhat
	private void getDbItNhat(List<TanSuatXuatHien> dbNhieuNhat, List<TanSuatXuatHien> dacBietTanSuat) {
		for(int i=dacBietTanSuat.size() -1; i>=0 ;i--) {
			
			TanSuatXuatHien tanxuat = dacBietTanSuat.get(i);
			if(tanxuat.getCount() > 0) {
				dbNhieuNhat.add(tanxuat);
				
				if(dbNhieuNhat.size() == 10) {
					return;
				}
			}
		}
	}


	private void caculateTanSuat(List<LotteryResult> lotteryResults, List<TanSuatXuatHien> dacBietTanSuat, List<TanSuatXuatHien> lotoTanSuat) {
		
		for(int i=0;i<100;i++) {
			int count = 0;
			int countDB = 0;
			
			String loto = ""+ i;
			if(i < 10) {
				loto = "0" + loto;
			}
			
			for(LotteryResult result: lotteryResults) {
				
				String special = result.getSpecial();
				count = count + getNumberInAward(loto, special);
				countDB = countDB + getNumberInAward(loto, special);
				
				String first = result.getFirst();
				count = count + getNumberInAward(loto, first);
				
				String second = result.getSecond();
				count = count + getNumberInAward(loto, second);
				
				String third = result.getThird();
				count = count + getNumberInAward(loto, third);
				
				String fourth = result.getFourth();
				count = count + getNumberInAward(loto, fourth);
				
				String fifth = result.getFifth();
				count = count + getNumberInAward(loto, fifth);
				
				String sixth = result.getSixth();
				count = count + getNumberInAward(loto, sixth);
				
				String seventh = result.getSeventh();
				count = count + getNumberInAward(loto, seventh);
				
				String eight = result.getEighth();
				count = count + getNumberInAward(loto, eight);
				
			}
			
			TanSuatXuatHien dacbiet = new TanSuatXuatHien(loto,countDB);
			dacBietTanSuat.add(dacbiet);
			
			TanSuatXuatHien lotoBean = new TanSuatXuatHien(loto, count);
			lotoTanSuat.add(lotoBean);
			
		}
		
	}
	
	private int getNumberInAward(String loto, String award) {
		int count = 0;
		
		if(!CommonUtil.isEmptyString(award)) {
			String awards[] = award.split("-");
			
			for(String temp: awards) {
				if(temp.endsWith(loto)) {
					++count;
				}
			}
		}
		return count;
	}

}
