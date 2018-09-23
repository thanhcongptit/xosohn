/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.soicaupro.thongkebacnho.CommonUtil;

import inet.bean.LotteryCompany;
import inet.bean.LotteryResult;
import inet.bean.News;
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
public class ThongKe00_99Controller extends BaseController {

	private List<News> listLotteryNews = null;
	private int maxCount = 0; 
	
	private static String sDDMMYYYY = null;
	public ThongKe00_99Controller() {
	}

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
		
		int numberAward = 19;
		
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
			numberAward = 27;
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
			url_ca = "/thong-ke-00-99.html";
		} else {
			url_ca = "/thong-ke-00-99-"+lotteryCompany.getCompanyLink().toLowerCase()+"-"+lotteryCompany.getCodeLowerCase()+".html";
		}
		mod.addObject("url_ca", Constants.URL_CAN + url_ca);
		
		categoryNews = lotteryCompany.getCompanyLink().replaceAll("-", " ").toUpperCase();
		
		List<LotteryResult> lotteryResults = lotteryResultDAO.findResultByCode(codeXS.toUpperCase(), biendo, new BigDecimal("1000000"));
		String values[] = caculate(lotteryResults);
		
		
		listLotteryNews = lotteryRequest.parserLotteryNewsProvince(Contant.SITE_ID, "1", Contant.MAX_NEWS, categoryNews);
		mod.addObject("listLotteryNews", listLotteryNews);
		//lo gan
		mod.addObject("maxCount", maxCount);
		mod.addObject("code", code.toUpperCase());
		mod.addObject("numberAward", numberAward*biendo);
		mod.addObject("biendo", biendo);
		mod.addObject("lotos", values);
		mod.addObject("categoryNews", categoryNews);
		mod.addObject("lotteryCompany", lotteryCompany);
		mod.addObject("company", lotteryCompany);
		mod.addObject("activeMenu",7);

		mod.setViewName("/thongke_00_99");

		return mod;
	}

	private String[] caculate(List<LotteryResult> lotteryResults) {
		String values[] = new String[100];
		
		
		for(int i=0;i<100;i++) {
			
			String loto = ""+ i;
			if(i < 10) {
				loto = "0" + loto;
			}
			int count = 0;
			
			for(LotteryResult result: lotteryResults) {
				
				String special = result.getSpecial();
				count = count + getNumberInAward(loto, special);
				
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
			
			if(count > maxCount) {
				maxCount = count;
			}
			
			values[i] = ""+count;
		}
		
		return values;
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
