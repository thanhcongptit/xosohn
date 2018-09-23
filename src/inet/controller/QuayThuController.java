/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.soicaupro.thongkebacnho.CommonUtil;

import inet.bean.LotteryCompany;
import inet.bean.News;
import inet.constant.Constants;
import inet.model.LotteryCompanyDAO;
import inet.request.LotteryRequest;
import inet.util.Contant;

/**
 *
 * @author hanhlm
 */
public class QuayThuController extends BaseController {

	private static String sDDMMYYYY = null;

	public QuayThuController() {
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
		LotteryCompanyDAO lotteryCompanyDAO = new LotteryCompanyDAO();
		
		List<LotteryCompany> lotteryCompanies = lotteryCompanyDAO.findLotteryCompany("MT");
		List<LotteryCompany> lotteryCompanies1 = lotteryCompanyDAO.findLotteryCompany("MN");
		lotteryCompanies.addAll(lotteryCompanies1);
		mod.addObject("lotteryCompanies", lotteryCompanies);

		String code = request.getParameter("code");
		String temp[] = code.split("-");
		code = temp[temp.length-1];
		
		String categoryNews = "";
		String choice = "1";
		
		String url_ca = "/quay-thu-ket-qua-xo-so-";
        
		if (code.equalsIgnoreCase("XSMB") || CommonUtil.isEmptyString(code)) {
			categoryNews = "MIEN BAC";
			mod.setViewName("/xsmb_quay_thu");
			url_ca = url_ca + "mien-bac-xsmb.html";

		} else if (code.equalsIgnoreCase("XSMT")) {
			categoryNews = "MIEN TRUNG";
			mod.setViewName("/xsmt_quay_thu");
			mod.addObject("company", "xổ số miền trung");
			mod.addObject("companies", listLotteryCompanyMT);
			mod.addObject("numSize", listLotteryCompanyMT.size());
			url_ca = url_ca + "mien-trung-xsmt.html";

		} else if (code.equalsIgnoreCase("XSMN")) {
			categoryNews = "MIEN NAM";
			mod.setViewName("/xsmt_quay_thu");
			mod.addObject("company", "xổ số miền nam");
			mod.addObject("companies", listLotteryCompanyMN);
			mod.addObject("numSize", listLotteryCompanyMN.size());
			url_ca = url_ca + "mien-nam-xsmn.html";
		} else {
			choice = "2";
			LotteryCompany lotteryCompany = lotteryCompanyDAO.findCompanyCode(code);
			categoryNews = lotteryCompany.getCompanyLink().replaceAll("-", " ").toUpperCase();
			mod.addObject("region", lotteryCompany.getRegion());
			
			url_ca = url_ca + lotteryCompany.getCompanyLink().toLowerCase()+"-" + code.toLowerCase()+ ".html";
			mod.addObject("company", lotteryCompany.getCompany());
			mod.setViewName("/xs_tinh_quay_thu");
		}
		
		mod.addObject("url_ca", Constants.URL_CAN + url_ca);

		List<News> listLotteryNews = lotteryRequest.parserLotteryNewsProvince(Contant.SITE_ID, "1", Contant.MAX_NEWS,
				categoryNews);
		mod.addObject("listLotteryNews", listLotteryNews);
		mod.addObject("categoryNews", categoryNews);
		mod.addObject("code", code.toLowerCase().trim());
		mod.addObject("choice", choice);
		mod.addObject("activeMenu", 8);

		return mod;
	}

}
