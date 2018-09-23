/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import inet.bean.Dream;
import inet.constant.Constants;
import inet.model.NewsSCMBChinhXacDAO;

/**
 *
 * @author hanhlm
 */
public class DetailNewsSCMBChinhXacController extends BaseController {
	private static HashMap<String, Dream> hLotteryNews = null;
	private static List<Dream> listNews = null;
	private static String sDDMMYYYY = null;
	
	public DetailNewsSCMBChinhXacController() {
	}

	@Override
	protected void loadBase() {
		super.loadBase(); // To change body of generated methods, choose Tools |
							// Templates.
		if (hLotteryNews == null) {
			hLotteryNews = new HashMap<String, Dream>();
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

		String id = request.getParameter("id");
		String sid = request.getParameter("sid");
		String key = sid + "_" + id + "_" + ddmmyyyy.replace("/", "");

		Dream  dream  = null;
		NewsSCMBChinhXacDAO dreamDao = new NewsSCMBChinhXacDAO();
		listNews = new NewsSCMBChinhXacDAO().findByPage(1, 15);
		
		dream = dreamDao.getRow(new BigDecimal(id));
		if (dream != null) {
//			dream.setContent(dream.getContent().replaceAll("/userfiles/image/",
//					"http://img.xoso.wap.vn/userfiles/image/"));
			hLotteryNews.put(key, dream );

		}

		mod.addObject("lotteryNews", dream);
		mod.addObject("listNews", listNews);

		if (dream != null) {
			// seo
			String slogan = "Bản tin xổ số wap";
			String title = dream.getTitle();
			String description = dream.getDescription();

			mod.addObject("slogan", slogan);
			mod.addObject("title", title);
			mod.addObject("keywords", "ket qua xo so, so mo chinh xac");
			mod.addObject("description", description);
			
			String url_ca = "";
			url_ca = "/soi-cau-xsmb-chinh-xac-nhat/"+ dream.getUrl()+ "-"+id+".html";
			mod.addObject("url_ca", Constants.URL_CAN + url_ca);
		}
		
		
		mod.setViewName("/detail_news_scmb_chinhxac");
		return mod;
	}

}
