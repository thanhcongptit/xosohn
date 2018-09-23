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

import inet.bean.Dream;
import inet.model.DreamsDAO;

/**
 *
 * @author hanhlm
 */
public class SiteMapDreamsController extends AbstractController {

	private static int totalPage = 0;
	private final int NUMBER_ITEM = 5000;

	public SiteMapDreamsController() {
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
		
		DreamsDAO dreamDAO = new DreamsDAO();
		int countRow = dreamDAO.count();
		

		if (countRow % NUMBER_ITEM > 0) {
			totalPage = (countRow / NUMBER_ITEM) + 1;
		} else {
			totalPage = (countRow / NUMBER_ITEM);
		}
		
		List<Dream> listNews = null;
		if(totalPage == 1) {
			listNews = dreamDAO.findByPage(1, NUMBER_ITEM);
		} else {
			if(iPage > 0) {
				listNews = dreamDAO.findByPage(iPage, NUMBER_ITEM);
			}
		}
	
		
		// System.out.println("page====================="+iPage);
		mod.addObject("listNews", listNews);
		mod.addObject("iPage", iPage);
		mod.addObject("totalPage", totalPage);
		
		mod.setViewName("/sitemap/post-sitemap-dreams");

		return mod;
	}

}
