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

import inet.bean.Dream;
import inet.constant.Constants;
import inet.model.NewsDBDAO;

/**
 *
 * @author hanhlm
 */

public class NewsDBController extends BaseController {

	private static List<Dream> sListLottery = null;
	private static int totalPage = 0;

	public NewsDBController() {
	}

	@Override
	protected void loadBase() {
		super.loadBase(); // To change body of generated methods, choose Tools |
							// Templates.
		if (sListLottery == null) {
			sListLottery = new ArrayList<Dream>();
		}

	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mod = super.handleRequestInternal(request, response);

		String url_ca = "";
		String page = request.getParameter("p");
		int iPage = 1;
		if (page != null && !"".equals(page)) {
			try {
				iPage = Integer.parseInt(page);
			} catch (Exception e) {
			}

		}
		url_ca = "/thong-ke-2-so-cuoi-giai-dac-biet.html";
		List<Dream> listNews = null;
		listNews = new NewsDBDAO().findByPage(iPage, 10);

		if (listNews != null && !listNews.isEmpty()) {
			sListLottery = listNews;
			NewsDBDAO newsDAO = new NewsDBDAO();
			int countRow = newsDAO.count();

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

		// seo
		String slogan = "Sổ mơ ";
		String title = "Sổ mơ";
		String keywords = "ket qua xo so,so xo,xo so mien bac,xổ số miền bắc,xstd,xsmb,xo so mien trung,kqxs,xo so mien nam,xo so,xsmt,xsmn,kết quả xổ số";
		String description = "so mo, so mo chinh xac";

		mod.addObject("slogan", slogan);
		mod.addObject("title", title);
		mod.addObject("keywords", keywords);
		mod.addObject("description", description);
		mod.setViewName("/news_db");
		mod.addObject("url_ca", Constants.URL_CAN + url_ca);

		return mod;
	}

}
