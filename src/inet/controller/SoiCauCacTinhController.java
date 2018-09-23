/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.soicaupro.thongkebacnho.CommonUtil;

import inet.bean.Lottery;
import inet.bean.LotteryCompany;
import inet.bean.LotteryResult;
import inet.bean.SoicauBean;
import inet.constant.Constants;
import inet.model.LotteryCompanyDAO;
import inet.model.LotteryResultDAO;
import inet.request.LotteryRequest;
import inet.util.StringPro;
import service.SoicauService;

/**
 *
 * @author hanhlm
 */
public class SoiCauCacTinhController extends BaseController {

	
	public SoiCauCacTinhController() {
	}

	@Override
	protected void loadBase() {
		super.loadBase(); // To change body of generated methods, choose Tools | Templates.
	}


	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mod = super.handleRequestInternal(request, response);
		String code = request.getParameter("code");
		String codes[] = code.split("-");
		String url_ca = "/";
		LotteryCompanyDAO lotteryCompanyDAO = new LotteryCompanyDAO();
		LotteryCompany lotteryCompany = null;
		boolean isSoiCauTinh = false;
		
		if(code.equalsIgnoreCase("mien-trung")) {
			code = "xsbdh";
			url_ca = "/soi-cau-mien-trung.html";
		} else if(code.equalsIgnoreCase("mien-nam")) {
			code = "xsag";
			url_ca = "/soi-cau-mien-nam.html";
		} else {
			isSoiCauTinh = true;
			code = codes[codes.length-1];
			
		}
		lotteryCompany = lotteryCompanyDAO.findCompanyCode(code);
		
		if(isSoiCauTinh) {
			url_ca = "/soi-cau-"+lotteryCompany.getCompanyLink().toLowerCase() + "-"+ lotteryCompany.getCodeLowerCase()+".html";
		}
		mod.addObject("url_ca", Constants.URL_CAN + url_ca);
		
		LotteryRequest lotteryRequest = new LotteryRequest();
		List<LotteryCompany> lotteryCompanies = lotteryCompanyDAO.findLotteryCompany(lotteryCompany.getRegion());
		
		
		int exactlimit = 0;
		int nhay = 1;
		int db = 0;
		int lon = 1;
		int limit = 0;
		int ca_cap = 0;
		
		SoicauService soicauService = new SoicauService();
		LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
		List<LotteryResult> lotteryResults = lotteryResultDAO.findResultByCode(code, 2, new BigDecimal("1000000"));
		String ngay = lotteryResults.get(0).getOpenDate();
		
		String key = code.toUpperCase() + ";" + ngay + ";" + exactlimit + ";" + nhay + ";" + lon + ";" + db + ";" +ca_cap;
		SoicauBean soicauBean = CommonUtil.getSoicauValueByKey(key);
		if (soicauBean == null) {
			soicauBean = soicauService.getSoiCauLotoMT(exactlimit, limit, nhay, lon, db, lotteryResults);
			CommonUtil.putSoicauValue(key, soicauBean);
		}
		
		lotteryResults = new LotteryResultDAO().findResultByCode(code, soicauBean.getTongsoNgay(), new BigDecimal("1000000"));
		
		List<Lottery> lotteries = lotteryRequest.parserLotteryResult(lotteryCompany.getCode().toUpperCase(), 
				lotteryResults.get(lotteryResults.size()-1).getOpenDate(), ngay);
		
		List<List<String>> listLotterysDuoiMB = new ArrayList<>();
		List<List<String>> listLotterysDauMB = new ArrayList<>();
		for(Lottery lottery : lotteries) {
			List<String> listLotteryDuoiMB = StringPro.filterDauOrDuoi(lottery, true);
			listLotterysDuoiMB.add(listLotteryDuoiMB);
			
			List<String> listLotteryDauMB = StringPro.filterDauOrDuoi(lottery, false);
			listLotterysDauMB.add(listLotteryDauMB);
			
		}
		String categoryNews = lotteryCompany.getCompanyLink().replaceAll("-", " ").toUpperCase();
		
		mod.addObject("startDate", ngay);
		mod.addObject("categoryNews", categoryNews);
		mod.addObject("soicauBean", soicauBean);
		mod.addObject("lotteryResults", lotteries);
		mod.addObject("duoiResults", listLotterysDuoiMB);
		mod.addObject("dauResults", listLotterysDauMB);
		mod.addObject("lotteryCompanies", lotteryCompanies);
		mod.addObject("company", lotteryCompany);
		mod.addObject("nhay", nhay);
		mod.addObject("lon", lon);
		mod.addObject("ca_cap", ca_cap);
		mod.addObject("code", code.toLowerCase());
		mod.addObject("activeMenu", 5);
		mod.setViewName("/soi_cau_mt");

		return mod;
	}

}
