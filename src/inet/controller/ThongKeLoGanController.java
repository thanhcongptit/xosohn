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
import com.thongke.ThongKeLoto;

import inet.bean.Loto;
import inet.bean.Lottery;
import inet.bean.LotteryCompany;
import inet.bean.LotteryResult;
import inet.bean.News;
import inet.bean.SoicauBean;
import inet.constant.Constants;
import inet.model.LotteryCompanyDAO;
import inet.model.LotteryResultDAO;
import inet.request.LotteryRequest;
import inet.util.Contant;
import inet.util.DateProc;
import inet.util.LotoUtils;
import inet.util.RequestUtil;
import inet.util.StringPro;
import inet.util.Today;
import service.SoicauService;

/**
 *
 * @author hanhlm
 */
public class ThongKeLoGanController extends BaseController {

	private List<News> listLotteryNews = null;

	private static String sDDMMYYYY = null;
	public ThongKeLoGanController() {
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
		String linkXS = "";
		
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
			linkXS = "xsmb";
		}  else {
			//linkXS = "xs" + lotteryCompany.getRegion().toLowerCase();
			linkXS = lotteryCompany.getCodeLowerCase();
		}
		
		if(isURLDefault) {
			url_ca = "/thong-ke-lo-gan.html";
		} else {
			url_ca = "/thong-ke-lo-gan-"+lotteryCompany.getCompanyLink().toLowerCase()+"-"+lotteryCompany.getCodeLowerCase()+".html";
		}
		mod.addObject("url_ca", Constants.URL_CAN + url_ca);
		
		categoryNews = lotteryCompany.getCompanyLink().replaceAll("-", " ").toUpperCase();
		
		List<LotteryResult> lotteryResults = lotteryResultDAO.findResultByCode(codeXS.toUpperCase(), 1, new BigDecimal("1000000"));
		String denngay = lotteryResults.get(0).getOpenDate();
		BigDecimal id = lotteryResults.get(0).getId();
		List<LotteryResult> lotteryResults2 = lotteryResultDAO.findResultByCode(codeXS.toUpperCase(), biendo, id);
		String tungay = lotteryResults2.get(lotteryResults2.size()-1).getOpenDate();
		// ---------------------------it ve, hay
		// ve-------------------------------------------
		tungay = DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(tungay));
		denngay = DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(denngay));
		
		String danlotoTK = LotoUtils.getAllLoto(",");
		ThongKeLoto thongKeLoto = new ThongKeLoto();
		List<Loto> listLoto = thongKeLoto.findLoto(danlotoTK, codeXS.toUpperCase(), tungay, denngay);

		// Nhieu
		List<Loto> max30LotoList = new ArrayList<>();
		// It
		List<Loto> min30LotoList = new ArrayList<>();
		if (listLoto != null && listLoto.size() > 0) {
			Loto[] arrayMaxLoto = listLoto.toArray(new Loto[listLoto.size()]);
			arrayMaxLoto = bubbleSort(arrayMaxLoto, listLoto.size(), false);
			for (int i = 0; i < 10; i++) {
				max30LotoList.add(arrayMaxLoto[i]);
			}

			Loto[] arrayMinLoto = listLoto.toArray(new Loto[listLoto.size()]);
			arrayMinLoto = bubbleSort(arrayMinLoto, listLoto.size(), true);
			for (int i = 0; i < 10; i++) {
				min30LotoList.add(arrayMinLoto[i]);
			}
		}

		Collections.sort(listLoto);
		listLoto = listLoto.subList(0, 10);
		
		listLotteryNews = lotteryRequest.parserLotteryNewsProvince(Contant.SITE_ID, "1", Contant.MAX_NEWS, categoryNews);
		mod.addObject("listLotteryNews", listLotteryNews);
		//lo gan
		mod.addObject("listLoto", listLoto);
		mod.addObject("code", code.toUpperCase());
		mod.addObject("biendo", biendo);
		mod.addObject("max30LotoList", max30LotoList);
		mod.addObject("min30LotoList", min30LotoList);
		mod.addObject("categoryNews", categoryNews);
		mod.addObject("lotteryCompany", lotteryCompany);
		mod.addObject("company", lotteryCompany);
		mod.addObject("activeMenu", 7);
		
		//soicau
		Today today = new Today();
		String ngay = "";
		int type = RequestUtil.getInt(request, "type", 1);
		
		if (today.getHour() < 18 || (today.getHour() == 18 && today.getMinute() <= 44)) {
			ngay = ddmmyyyy;
			
		} else if (today.getHour() > 18 || (today.getHour() == 18 && today.getMinute() > 44)) {
			ngay = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), 1);
		}
		
		int exactlimit = 0;
		int nhay = 1;
		int db = 0;
		int lon = 1;
		int limit = 0;
		int ca_cap = 0;
		
		if(type == 2) {
			lon = 0;
		} else if(type == 3) {
			nhay = 2;
		} else if(type == 4) {
			ca_cap = 1;
			nhay = 2;
		}
		
		SoicauBean soicauBean = null;
		List<Lottery> lotteries = null;
		List<List<String>> listLotterysDuoiMB = new ArrayList<>();
		List<List<String>> listLotterysDauMB = new ArrayList<>();
		
		if(code.equalsIgnoreCase("xsmb")) {
			SoicauService soicauService = new SoicauService();
			String key = ngay + ";" + exactlimit + ";" + nhay + ";" + lon + ";" + db + ";" +ca_cap;
			soicauBean = CommonUtil.getSoicauValueByKey(key);
			if (soicauBean == null) {
				soicauBean = soicauService.getSoiCauLoto(exactlimit, limit, ngay, nhay, lon, db, ca_cap);
				CommonUtil.putSoicauValue(key, soicauBean);
			}
			
			List<LotteryResult> lotteryResults1 = new LotteryResultDAO().findResultByCode("XSTD", soicauBean.getTongsoNgay(), new BigDecimal("1000000"));
			
			lotteries = lotteryRequest.parserLotteryResult("XSTD", 
					lotteryResults1.get(lotteryResults1.size()-1).getOpenDate(), ngay);
			
			for(Lottery lottery : lotteries) {
				List<String> listLotteryDuoiMB = StringPro.filterDauOrDuoi(lottery, true);
				listLotterysDuoiMB.add(listLotteryDuoiMB);
				
				List<String> listLotteryDauMB = StringPro.filterDauOrDuoi(lottery, false);
				listLotterysDauMB.add(listLotteryDauMB);
				
			}
		} else {
			exactlimit = 0;
			nhay = 1;
			db = 0;
			lon = 1;
			limit = 0;
			ca_cap = 0;
			
			SoicauService soicauService = new SoicauService();
			List<LotteryResult> lotteryResults1 = lotteryResultDAO.findResultByCode(code, 2, new BigDecimal("1000000"));
			String ngay1 = lotteryResults1.get(0).getOpenDate();
			
			String key = code.toUpperCase() + ";" + ngay1 + ";" + exactlimit + ";" + nhay + ";" + lon + ";" + db + ";" +ca_cap;
			
			soicauBean = CommonUtil.getSoicauValueByKey(key);
			if (soicauBean == null) {
				soicauBean = soicauService.getSoiCauLotoMT(exactlimit, limit, nhay, lon, db,  lotteryResults1);
				CommonUtil.putSoicauValue(key, soicauBean);
			}
			
			lotteryResults = new LotteryResultDAO().findResultByCode(code, soicauBean.getTongsoNgay(), new BigDecimal("1000000"));
			
			lotteries = lotteryRequest.parserLotteryResult(lotteryCompany.getCode().toUpperCase(), 
					lotteryResults.get(lotteryResults.size()-1).getOpenDate(), ngay1);
			
			for(Lottery lottery : lotteries) {
				List<String> listLotteryDuoiMB = StringPro.filterDauOrDuoi(lottery, true);
				listLotterysDuoiMB.add(listLotteryDuoiMB);
				
				List<String> listLotteryDauMB = StringPro.filterDauOrDuoi(lottery, false);
				listLotterysDauMB.add(listLotteryDauMB);
				
			}
		}
		
		mod.addObject("linkXS", linkXS);
		mod.addObject("startDate", ngay);
		mod.addObject("soicauBean", soicauBean);
		mod.addObject("lotteryResults", lotteries);
		mod.addObject("duoiResults", listLotterysDuoiMB);
		mod.addObject("dauResults", listLotterysDauMB);
		mod.addObject("nhay", nhay);
		mod.addObject("lon", lon);
		mod.addObject("ca_cap", ca_cap);

		mod.setViewName("/thongke_lo_gan");

		return mod;
	}

	private Loto[] bubbleSort(Loto arrLoto[], int size, boolean isAsc) {
		Loto tmp = null;
		for (int i = 0; i < size - 1; i++) {
			for (int j = size - 1; j > i; j--) {
				if (isAsc && (arrLoto[j].getSolanxuathien() < arrLoto[j - 1].getSolanxuathien())) {
					tmp = arrLoto[j - 1];
					arrLoto[j - 1] = arrLoto[j];
					arrLoto[j] = tmp;
				} else if (!isAsc && (arrLoto[j].getSolanxuathien() > arrLoto[j - 1].getSolanxuathien())) {
					tmp = arrLoto[j - 1];
					arrLoto[j - 1] = arrLoto[j];
					arrLoto[j] = tmp;
				}
			}
		}
		return arrLoto;
	}

}
