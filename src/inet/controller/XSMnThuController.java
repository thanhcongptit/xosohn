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
public class XSMnThuController extends BaseController {

	private static String sDDMMYYYY = null;

	public XSMnThuController() {
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
		String dayMN = "";
        if (day == null || "".equals(day)) {
            Today today = new Today();

            if (today.getHour() < 16 || (today.getHour() == 16 && today.getMinute() <= 44)) {
                dayMN = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -1);
            } else if (today.getHour() > 16 || (today.getHour() == 16 && today.getMinute() > 44)) {
                dayMN = ddmmyyyy;
            }

            day = ddmmyyyy;
        } else {
            day = day.replace("-", "/");
            if (ddmmyyyy.equals(day)) {
                day = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -1);
            }
            dayMN = day;
        }

		String dateQuery = "";
		String url_ca = "";
        
		
		// cho ngay chu nhat
		if (thu.equalsIgnoreCase("nhat")) {
			dateQuery = DatePro.getDateDDMMYYYY2(dayMN, "chu " + thu.trim());
			
			url_ca = "/xsmn-chu-nhat.html";
		} else {
			dateQuery = DatePro.getDateDDMMYYYY2(dayMN, "thu" + thu.trim());
			url_ca = "/xsmn-thu-" +thu+ ".html";
		}
		mod.addObject("url_ca", Constants.URL_CAN + url_ca);

		int i = 0;
		while (i < 3) {
			List<Lottery> listLotteryMT = lotteryRequest.parserLotteryResultOfRegion(dateQuery, "MN");
			while (listLotteryMT == null || listLotteryMT.size() == 0) {
				dateQuery = DateUtil.addDateFromString(dateQuery, "dd/MM/yyyy", -7);
				listLotteryMT = lotteryRequest.parserLotteryResultOfRegion(dateQuery, "MN");
			}

			if (listLotteryMT != null && !listLotteryMT.isEmpty()) {
				Lottery lotterysMT = findLotterys(listLotteryMT, dayMN);
				List<String> listLotteryDuoiMN = findDuoi(listLotteryMT);
				int numSizeMT = listLotteryMT.size();

				if (i == 0) {
					mod.addObject("lotterysMN", lotterysMT);
					mod.addObject("listLotteryDuoiMN", listLotteryDuoiMN);
					mod.addObject("numSizeMN", numSizeMT);
					
					String weekMN = DateUtil.getWeekFromDate(dateQuery);
			        mod.addObject("ddmmyyyyMN", dateQuery);
			        mod.addObject("dayOfWeekMN", weekMN);
			        String linkMNWeek = "xsmn-"+ DaiCaThang.toUrlFriendly(weekMN).toLowerCase() +".html";
					mod.addObject("linkMNThu",linkMNWeek);
					String linkMNDay = "xsmn-"+dateQuery.replace("/", "-") + ".html"; 
					mod.addObject("linkMNDay",linkMNDay);
				} else {
					mod.addObject("lotterysMN" + i, lotterysMT);
					mod.addObject("listLotteryDuoiMN" + i, listLotteryDuoiMN);
					mod.addObject("numSizeMN"+i, numSizeMT);
					
					String weekMN = DateUtil.getWeekFromDate(dateQuery);
			        mod.addObject("ddmmyyyyMN"+i, dateQuery);
			        mod.addObject("dayOfWeekMN"+i, weekMN);
			        String linkMNWeek = "xsmn-"+ DaiCaThang.toUrlFriendly(weekMN).toLowerCase() +".html";
					mod.addObject("linkMNThu"+i,linkMNWeek);
					String linkMNDay = "xsmn-"+dateQuery.replace("/", "-") + ".html"; 
					mod.addObject("linkMNDay"+i,linkMNDay);
				}

				i++;
			}

			dateQuery = DateUtil.addDateFromString(dateQuery, "dd/MM/yyyy", -7);
		}

		mod.setViewName("/xsmn_thu");
		mod.addObject("categoryNews", "MIEN NAM");
		List<News> listLotteryNews = lotteryRequest.parserLotteryNewRegion(Contant.SITE_ID, "1", Contant.MAX_NEWS, "MIEN NAM");
		mod.addObject("listLotteryNews", listLotteryNews);
		mod.addObject("activeMN", thu);
		mod.addObject("activeMenu", 4);

		return mod;
	}

}
