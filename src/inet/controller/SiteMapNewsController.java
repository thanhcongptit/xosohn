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
import org.springframework.web.servlet.mvc.AbstractController;

import inet.bean.News;
import inet.model.NewsDAO;
import inet.request.LotteryRequest;
import inet.util.Contant;

/**
 *
 * @author hanhlm
 */
public class SiteMapNewsController extends AbstractController {

	private static int totalPage = 0;
	private final int NUMBER_ITEM = 5000;

	public SiteMapNewsController() {
	}

	

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mod = new ModelAndView();
		String page = request.getParameter("p");
		
		// Trang dau
		int iPage = 0;
		if (page != null && !"".equals(page)) {
			try {
				iPage = Integer.parseInt(page);
				
			} catch (Exception e) {
			}
			
		} 
		
		NewsDAO newsDAO = new NewsDAO();
		int countRow = newsDAO.count();
		

		if (countRow % NUMBER_ITEM > 0) {
			totalPage = (countRow / NUMBER_ITEM) + 1;
		} else {
			totalPage = (countRow / NUMBER_ITEM);
		}
		
		List<News> listNews = null;
		if(totalPage == 1) {

			LotteryRequest lotteryRequest = new LotteryRequest();
			listNews = lotteryRequest.parserLotteryNews(Contant.SITE_ID, "" + 1, "" + NUMBER_ITEM);
		} else {
			if(iPage > 0) {
				LotteryRequest lotteryRequest = new LotteryRequest();
				listNews = lotteryRequest.parserLotteryNews(Contant.SITE_ID, "" + iPage, "" + NUMBER_ITEM);
			}
		}
	
		
		// System.out.println("page====================="+iPage);
		mod.addObject("listNews", listNews);
		mod.addObject("iPage", iPage);
		mod.addObject("totalPage", totalPage);
		
		mod.setViewName("/sitemap/post-sitemap-news");

		return mod;
	}

}
