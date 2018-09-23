/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.soicaupro.thongkebacnho.CommonUtil;

import inet.bean.LotteryCompany;
import inet.bean.News;
import inet.constant.Constants;
import inet.model.LotteryCompanyDAO;
import inet.model.NewsDAO;
import inet.request.LotteryRequest;
import inet.util.Contant;

/**
 *
 * @author hanhlm
 */
public class TinTucController extends BaseController {

	private static List<News> sListLottery = null;
	private static int totalPage = 0;

	public TinTucController() {
	}

	@Override
	protected void loadBase() {
		super.loadBase(); // To change body of generated methods, choose Tools |
		// Templates.
		if (sListLottery == null) {
			sListLottery = new ArrayList<News>();
		}

	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mod = super.handleRequestInternal(request, response);
		String categoryNews = request.getParameter("categoryNews");

		String page = request.getParameter("p");
		String url_ca = "";
		int iPage = 1;
		if(CommonUtil.isEmptyString(categoryNews)) {
			
			if (page != null && !"".equals(page)) {
				try {
					iPage = Integer.parseInt(page);
					
				} catch (Exception e) {
				}
				
			}
			
			url_ca = "/du-doan.html";
		} else {
			if (page != null && !"".equals(page)) {
				try {
					iPage = Integer.parseInt(page);
					
				} catch (Exception e) {
				}
				
			} 
			
			url_ca = "/du-doan-"+categoryNews.toLowerCase()+".html";
		}
		
		List<News> listNews = null;

		LotteryRequest lotteryRequest = new LotteryRequest();
		
		String code = "";
		
		if(!CommonUtil.isEmptyString(categoryNews) && categoryNews.toLowerCase().startsWith("xs")) {
			code = "-" +categoryNews.toLowerCase();
			if(categoryNews.equalsIgnoreCase("xsmt")) {
				categoryNews = "MIEN TRUNG";
			} else if(categoryNews.equalsIgnoreCase("xsmn")) {
				categoryNews = "MIEN NAM";
			} else {
				if(categoryNews.equalsIgnoreCase("xsmb")) {
					categoryNews = "xstd";
				}
				
				LotteryCompany lotteryCompany = new LotteryCompanyDAO().findCompanyCode(categoryNews.toUpperCase());
				categoryNews = lotteryCompany.getProvince();
			}
			
			
		}
		
		if(CommonUtil.isEmptyString(categoryNews)) {
			categoryNews = "";
			listNews = lotteryRequest.parserLotteryNews(Contant.SITE_ID, "" + iPage, "10");
		} else if(categoryNews.equalsIgnoreCase("MIEN TRUNG") || categoryNews.equalsIgnoreCase("MIEN NAM")) {
			categoryNews = categoryNews.replace("-", " ").toUpperCase();
			listNews = lotteryRequest.parserLotteryNewRegion(Contant.SITE_ID, "" + iPage, "10", categoryNews);
		} else {
			categoryNews = categoryNews.replace("-", " ").toUpperCase();
			listNews = lotteryRequest.parserLotteryNewsProvince(Contant.SITE_ID, "" + iPage, "10", categoryNews);
		}
		
		if(CommonUtil.isEmptyString(code)) {
			if(!CommonUtil.isEmptyString(categoryNews))  {
				code = "-" + categoryNews.replace(" ", "-");
			}
		}
		
		if (listNews != null && !listNews.isEmpty()) {
			sListLottery = listNews;
			NewsDAO newsDAO = new NewsDAO();
			int countRow = 0;
			
			if(CommonUtil.isEmptyString(categoryNews)) {
				countRow = newsDAO.count();
			} else if(categoryNews.equalsIgnoreCase("MIEN TRUNG") || categoryNews.equalsIgnoreCase("MIEN NAM")) {
				countRow = newsDAO.countRegion(categoryNews);
			} else {
				countRow = newsDAO.countProvince(categoryNews);
			}

			if (countRow % 10 > 0) {
				totalPage = (countRow / 10) + 1;
			} else {
				totalPage = (countRow / 10);
			}
		}
		
		// System.out.println("page====================="+iPage);
		mod.addObject("listNews", listNews);
		mod.addObject("iPage", iPage);
		mod.addObject("totalPage", totalPage);
		mod.addObject("activeMenu", 6);
		mod.addObject("url_ca", Constants.URL_CAN + url_ca);
		mod.addObject("categoryNews", categoryNews);
		mod.addObject("code", code);
		
		mod.setViewName("/du_doan");

		return mod;
	}

}
