/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.controller;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import inet.bean.LotteryResult;
import inet.model.LotteryResultDAO;
import inet.util.RequestUtil;

/**
 *
 * @author hanhlm
 */
public class SiteMapPostController extends AbstractController {

	public SiteMapPostController() {
	}

	

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mod = new ModelAndView();
		int year = RequestUtil.getInt(request, "year", 0);
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		
		// System.out.println("page====================="+iPage);
		mod.addObject("year", year);
		mod.addObject("currentYear", currentYear);
		
		if(year >=2005) {
			String startDate =  year + "-01-01";
			String endDate = year + "-12-31";
			
			List<LotteryResult> lotteries = new LotteryResultDAO().findResultInTime(startDate, endDate);
			mod.addObject("lotteries", lotteries);
		}
		
		mod.setViewName("/sitemap/post-sitemap");

		return mod;
	}

}
