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
import inet.util.StringPro;
import inet.util.Today;

/**
 *
 * @author hanhlm
 */
public class XSMbThuController extends BaseController {

	private static String sDDMMYYYY = null;

	public XSMbThuController() {
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
		String dayMB = "";
		if (day == null || "".equals(day)) {
			Today today = new Today();
			if (today.getHour() < 18 || (today.getHour() == 18 && today.getMinute() <= 44)) {
				dayMB = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -1);
			} else if (today.getHour() > 18 || (today.getHour() == 18 && today.getMinute() > 44)) {
				dayMB = ddmmyyyy;
			}

			day = ddmmyyyy;
		} else {
			day = day.replace("-", "/");
			if (ddmmyyyy.equals(day)) {
				day = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -1);
			}
			dayMB = day;
		}

		String dateQuery = "";
		String url_ca = "";
		// cho ngay chu nhat
		if (thu.equalsIgnoreCase("nhat")) {
			dateQuery = DatePro.getDateDDMMYYYY2(dayMB, "chu " + thu.trim());
			thu = "8";
			url_ca = "/xsmb-chu-nhat.html";
		} else {
			dateQuery = DatePro.getDateDDMMYYYY2(dayMB, "thu" + thu.trim());
			url_ca = "/xsmb-thu-" +thu+ ".html";
		}

		mod.addObject("url_ca", Constants.URL_CAN + url_ca);
		
		int i = 0;
		while (i < 7) {
			List<Lottery> listLotteryMB = lotteryRequest.parserLotteryResultOfRegion(dateQuery, "MB");
			while (listLotteryMB == null || listLotteryMB.size() == 0) {
				dateQuery = DateUtil.addDateFromString(dateQuery, "dd/MM/yyyy", -7);
				listLotteryMB = lotteryRequest.parserLotteryResultOfRegion(dateQuery, "MB");
			}

			if(listLotteryMB != null && listLotteryMB.size() > 1) {
                listLotteryMB = listLotteryMB.subList(0, 0);
            }
			
			if (listLotteryMB != null && !listLotteryMB.isEmpty()) {
				List<String> listLotterysDuoiMB = null;
				List<String> listLotterysDauMB = null;
				listLotterysDuoiMB = StringPro.filterDauOrDuoi(listLotteryMB.get(0), true);
				listLotterysDauMB = StringPro.filterDauOrDuoi(listLotteryMB.get(0), false);

				if (i == 0) {
					mod.addObject("listLotteryMB", listLotteryMB);
					mod.addObject("listLotteryDuoiMB", listLotterysDuoiMB);
					mod.addObject("listLotteryDauMB", listLotterysDauMB);
					
					String weekMB = DateUtil.getWeekFromDate(dateQuery);
			        mod.addObject("ddmmyyyy", dateQuery);
			        mod.addObject("dayOfWeek", weekMB);
			        String linkMBWeek = "xsmb-"+ DaiCaThang.toUrlFriendly(weekMB).toLowerCase() +".html";
					mod.addObject("linkMBThu",linkMBWeek);
					String linkMBDay = "xsmb-"+dateQuery.replace("/", "-") + ".html"; 
					mod.addObject("linkMBDay",linkMBDay);
					
				} else {
					mod.addObject("listLotteryMB"+i, listLotteryMB);
					mod.addObject("listLotteryDuoiMB" + i, listLotterysDuoiMB);
					mod.addObject("listLotteryDauMB" + i, listLotterysDauMB);
					
					String weekMB = DateUtil.getWeekFromDate(dateQuery);
			        mod.addObject("ddmmyyyy"+i, dateQuery);
			        mod.addObject("dayOfWeek"+i, weekMB);
			        String linkMBWeek = "xsmb-"+ DaiCaThang.toUrlFriendly(weekMB).toLowerCase() +".html";
					mod.addObject("linkMBThu"+i,linkMBWeek);
					String linkMBDay = "xsmb-"+dateQuery.replace("/", "-") + ".html"; 
					mod.addObject("linkMBDay"+i,linkMBDay);
				}

				i++;
			}

			dateQuery = DateUtil.addDateFromString(dateQuery, "dd/MM/yyyy", -7);
		}

		List<News> listLotteryNews = lotteryRequest.parserLotteryNewsProvince(Contant.SITE_ID, "1", Contant.MAX_NEWS, "MIEN BAC");
		mod.addObject("listLotteryNews", listLotteryNews);
		mod.setViewName("/xsmb_thu");
		mod.addObject("categoryNews", "MIEN BAC");
		mod.addObject("activeMB", thu);
		mod.addObject("activeMenu", 2);

		return mod;
	}

}
