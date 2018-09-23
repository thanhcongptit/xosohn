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

import inet.bean.Lottery;
import inet.bean.News;
import inet.constant.Constants;
import inet.request.LotteryRequest;
import inet.util.Contant;
import inet.util.DaiCaThang;
import inet.util.DatePro;
import inet.util.DateProc;
import inet.util.DateUtil;
import inet.util.Today;

/**
 *
 * @author hanhlm
 */
public class XSMtThuController extends BaseController {

	private static String sDDMMYYYY = null;

	public XSMtThuController() {
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
		String thu = request.getParameter("thu");

		String day = request.getParameter("d");
		String dayMT = "";
		if (day == null || "".equals(day)) {
			Today today = new Today();

			if (today.getHour() < 17 || (today.getHour() == 17 && today.getMinute() <= 44)) {
				dayMT = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -1);
			} else if (today.getHour() > 17 || (today.getHour() == 17 && today.getMinute() > 44)) {
				dayMT = ddmmyyyy;
			}

			day = ddmmyyyy;
		} else {
			day = day.replace("-", "/");
			if (ddmmyyyy.equals(day)) {
				day = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -1);
			}
			dayMT = day;
		}

		String dateQuery = "";
		String url_ca = "";
		
		// cho ngay chu nhat
		if (thu.equalsIgnoreCase("nhat")) {
			dateQuery = DatePro.getDateDDMMYYYY2(dayMT, "chu " + thu.trim());
			thu = "8";
			url_ca = "/xsmt-chu-nhat.html";
		} else {
			dateQuery = DatePro.getDateDDMMYYYY2(dayMT, "thu" + thu.trim());
			url_ca = "/xsmt-thu-" +thu+ ".html";
		}
		mod.addObject("url_ca", Constants.URL_CAN + url_ca);

		int i = 0;
		while (i < 3) {
			List<Lottery> listLotteryMT = lotteryRequest.parserLotteryResultOfRegion(dateQuery, "MT");
			while (listLotteryMT == null || listLotteryMT.size() == 0) {
				dateQuery = DateUtil.addDateFromString(dateQuery, "dd/MM/yyyy", -7);
				listLotteryMT = lotteryRequest.parserLotteryResultOfRegion(dateQuery, "MT");
			}

			if (listLotteryMT != null && !listLotteryMT.isEmpty()) {
				Lottery lotterysMT = findLotterys(listLotteryMT, dayMT);
				List<String> listLotteryDuoiMT = findDuoi(listLotteryMT);
				int numSizeMT = listLotteryMT.size();

				if (i == 0) {
					mod.addObject("lotterysMT", lotterysMT);
					mod.addObject("listLotteryDuoiMT", listLotteryDuoiMT);
					mod.addObject("numSizeMT", numSizeMT);
					
					String weekMT = DateUtil.getWeekFromDate(dateQuery);
			        mod.addObject("ddmmyyyyMT", dateQuery);
			        mod.addObject("dayOfWeekMT", weekMT);
			        String linkMTWeek = "xsmt-"+ DaiCaThang.toUrlFriendly(weekMT).toLowerCase() +".html";
					mod.addObject("linkMTThu",linkMTWeek);
					String linkMTDay = "xsmt-"+dateQuery.replace("/", "-") + ".html"; 
					mod.addObject("linkMTDay",linkMTDay);
				} else {
					mod.addObject("lotterysMT" + i, lotterysMT);
					mod.addObject("listLotteryDuoiMT" + i, listLotteryDuoiMT);
					mod.addObject("numSizeMT"+i, numSizeMT);
					
					String weekMT = DateUtil.getWeekFromDate(dateQuery);
			        mod.addObject("ddmmyyyyMT"+i, dateQuery);
			        mod.addObject("dayOfWeekMT"+i, weekMT);
			        String linkMTWeek = "xsmt-"+ DaiCaThang.toUrlFriendly(weekMT).toLowerCase() +".html";
					mod.addObject("linkMTThu"+i,linkMTWeek);
					String linkMTDay = "xsmt-"+dateQuery.replace("/", "-") + ".html"; 
					mod.addObject("linkMTDay"+i,linkMTDay);
				}

				i++;
			}

			dateQuery = DateUtil.addDateFromString(dateQuery, "dd/MM/yyyy", -7);
		}

		mod.setViewName("/xsmt_thu");
		mod.addObject("categoryNews", "MIEN TRUNG");
		List<News> listLotteryNews = lotteryRequest.parserLotteryNewRegion(Contant.SITE_ID, "1", Contant.MAX_NEWS, "MIEN TRUNG");
		mod.addObject("listLotteryNews", listLotteryNews);
		mod.addObject("activeMT", thu);
		mod.addObject("activeMenu", 3);

		return mod;
	}

}
