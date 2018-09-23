/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.soicaupro.thongkebacnho.CommonUtil;

import inet.bean.News;
import inet.constant.Constants;
import inet.request.LotteryRequest;
import inet.util.Contant;

/**
 *
 * @author hanhlm
 */
public class DetailNewsController extends BaseController {
	private static HashMap<String, News> hLotteryNews = null;
	private static List<News> listNews = null;
	private static String sDDMMYYYY = null;
	
	public DetailNewsController() {
	}

	@Override
	protected void loadBase() {
		super.loadBase(); // To change body of generated methods, choose Tools |
							// Templates.
		if (hLotteryNews == null) {
			hLotteryNews = new HashMap<String, News>();
		}
		if (sDDMMYYYY == null || !ddmmyyyy.equals(sDDMMYYYY)) {
			hLotteryNews.clear();
			sDDMMYYYY = ddmmyyyy;
		}
	}
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mod = super.handleRequestInternal(request, response);
		String categoryNews = request.getParameter("categoryNews");
		String id = request.getParameter("id");
		String sid = request.getParameter("sid");
		String key = sid + "_" + id + "_" + ddmmyyyy.replace("/", "");

		News lotteryNews = null;
		LotteryRequest lotteryRequest = new LotteryRequest();
		
		if(CommonUtil.isEmptyString(categoryNews)) {
			listNews = lotteryRequest.parserLotteryNews(Contant.SITE_ID, "1", "10");
		} else {
			listNews = lotteryRequest.parserLotteryNewsProvince(Contant.SITE_ID, "1", "10", categoryNews);
		}
		
		lotteryNews = lotteryRequest.parserLotteryNewsDetail(sid, id);
		if (lotteryNews != null) {
			lotteryNews.setContent(lotteryNews.getContent().replaceAll("/userfiles/image/",
					"http://img.xoso.wap.vn/userfiles/image/"));
			hLotteryNews.put(key, lotteryNews);

		}
		
		mod.addObject("lotteryNews", lotteryNews);
		mod.addObject("listNews", listNews);

		if (lotteryNews != null) {
			// seo
			String slogan = "Bản tin xổ số wap";
			String title = lotteryNews.getTitle();
			String keywords = lotteryNews.getTag();
			String description = lotteryNews.getDescription();

			mod.addObject("slogan", slogan);
			mod.addObject("title", title);
			mod.addObject("keywords", keywords);
			mod.addObject("description", description);
			
			String url_ca = "";
			url_ca = "/du-doan/"+ lotteryNews.getUrl()+ "-"+id+".html";
			mod.addObject("url_ca", Constants.URL_CAN + url_ca);
			
		}
		mod.addObject("activeMenu", 6);
		mod.addObject("categoryNews", categoryNews);
		mod.setViewName("/detail_news");
		return mod;
	}

}
