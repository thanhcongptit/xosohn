/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.soicaupro.thongkebacnho.CommonUtil;

import inet.bean.Lottery;
import inet.bean.News;
import inet.constant.Constants;
import inet.request.LotteryRequest;
import inet.util.Contant;
import inet.util.DaiCaThang;
import inet.util.DateProc;
import inet.util.DateUtil;
import inet.util.Today;

/**
 *
 * @author hanhlm
 */
public class XSMTNgayController extends BaseController {

	private static HashMap<String, Lottery> hLotterys = null;
	private static HashMap<String, List<String>> hLotteryDauDuoi = null;
	private static HashMap<String, String> hNumSize = null;
	private static String sDDMMYYYY = null;


	@Override
	protected void loadBase() {
		super.loadBase(); // To change body of generated methods, choose Tools | Templates.
		
		if (hLotterys == null) {
			hLotterys = new HashMap<String, Lottery>();
		}
		
		if (hNumSize == null) {
			hNumSize = new HashMap<String, String>();
		}
		
		if (hLotteryDauDuoi == null) {
			hLotteryDauDuoi = new HashMap<String, List<String>>();
		}
		
		if (sDDMMYYYY == null || !ddmmyyyy.equals(sDDMMYYYY)) {
			hLotterys.clear();
			hLotteryDauDuoi.clear();
			hNumSize.clear();
			sDDMMYYYY = ddmmyyyy;
		}
		
	}

	@Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		ModelAndView mod = super.handleRequestInternal(request, response);

		LotteryRequest lotteryRequest = new LotteryRequest();
		// lay ket qua xoso mien bac
		String day = request.getParameter("d");
		
		if(!CommonUtil.isEmptyString(day)) {
			day = DateUtil.formatDate(day);
		}
		
		String url_ca = "/xsmt-"+day+".html";
		mod.addObject("url_ca", Constants.URL_CAN + url_ca);
		String dayMT = "";
		Today today = new Today();
		
		if (day == null || "".equals(day)) {
			
			if (today.getHour() < 17 || (today.getHour() == 17 && today.getMinute() <= 38)) {
				dayMT = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -1);
			} else if (today.getHour() > 17 || (today.getHour() == 17 && today.getMinute() > 38)) {
				dayMT = ddmmyyyy;
			}
			day = dayMT;
			
		} else {
			day = day.replace("-", "/");
			Date currentDate = DateUtil.toDate(ddmmyyyy, "dd/MM/yyyy");
			Date openDate = DateUtil.toDate(day, "dd/MM/yyyy");
			
			if (!openDate.before(currentDate)) {
				if (today.getHour() < 17 || (today.getHour() == 17 && today.getMinute() <= 38)) {
					day = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -1);
				}
			}
			dayMT = day;
		}

        String key = "";
		Lottery lotterysMT = null;
		List<String> listLotteryDuoiMT = null;
		int numSizeMT = 0;
		key = "MT_" + dayMT.replace("/", "");
		if (hLotterys.containsKey(key)) {
			lotterysMT = hLotterys.get(key);
			listLotteryDuoiMT = hLotteryDauDuoi.get(key);
			try {
				numSizeMT = Integer.parseInt(hNumSize.get(key));
			} catch (Exception e) {
			}

		} else {
			List<Lottery> listLotteryMT = lotteryRequest.parserLotteryResultOfRegion(dayMT, "MT");
			if (listLotteryMT != null && !listLotteryMT.isEmpty()) {
				lotterysMT = findLotterys(listLotteryMT, dayMT);
				listLotteryDuoiMT = findDuoi(listLotteryMT);
				numSizeMT = listLotteryMT.size();
				try {
					hNumSize.put(key, String.valueOf(numSizeMT));
				} catch (Exception e) {
				}
				hLotterys.put(key, lotterysMT);
				hLotteryDauDuoi.put(key, listLotteryDuoiMT);

			}
		}


		mod.addObject("lotterysMT", lotterysMT);
		mod.addObject("listLotteryDuoiMT", listLotteryDuoiMT);

		mod.addObject("numSizeMT", numSizeMT);
		String weekMT = DateUtil.getWeekFromDate(dayMT);
        mod.addObject("ddmmyyyyMT", dayMT);
        mod.addObject("dayOfWeekMT", weekMT);
        String linkMTWeek = "xsmt-"+ DaiCaThang.toUrlFriendly(weekMT).toLowerCase() +".html";
		mod.addObject("linkMTThu",linkMTWeek);
		String linkMTDay = "xsmt-"+dayMT.replace("/", "-") + ".html"; 
		mod.addObject("linkMTDay",linkMTDay);
        
        
        mod.addObject("categoryNews", "MIEN TRUNG");
        List<News> listLotteryNews = lotteryRequest.parserLotteryNewRegion(Contant.SITE_ID, "1", Contant.MAX_NEWS, "MIEN TRUNG");
		mod.addObject("listLotteryNews", listLotteryNews);
        mod.addObject("activeMenu", 3);
        mod.setViewName("/xsmt_ngay");

        return mod;
    }

}
