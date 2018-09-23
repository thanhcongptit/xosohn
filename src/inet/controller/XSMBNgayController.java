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
import inet.constant.Constants;
import inet.request.LotteryRequest;
import inet.util.DaiCaThang;
import inet.util.DateProc;
import inet.util.DateUtil;
import inet.util.StringPro;
import inet.util.Today;

/**
 *
 * @author hanhlm
 */
public class XSMBNgayController extends BaseController {

	private static HashMap<String, List<Lottery>> hLotteryMB = null;
	private static HashMap<String, List<String>> hLotteryDauDuoiMB = null;
	private static String sDDMMYYYY = null;

	public XSMBNgayController() {
	}

	@Override
	protected void loadBase() {
		super.loadBase(); // To change body of generated methods, choose Tools | Templates.
		if (hLotteryMB == null) {
			hLotteryMB = new HashMap<String, List<Lottery>>();
		}
		if (hLotteryDauDuoiMB == null) {
			hLotteryDauDuoiMB = new HashMap<>();
		}
		
		if (sDDMMYYYY == null || !ddmmyyyy.equals(sDDMMYYYY)) {
			hLotteryMB.clear();
			hLotteryDauDuoiMB.clear();
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
		
		String url_ca = "/xsmb-"+day+".html";
		mod.addObject("url_ca", Constants.URL_CAN + url_ca);
		String dayMB = "";
		Today today = new Today();
		
		if (day == null || "".equals(day)) {
			
			if (today.getHour() < 18 || (today.getHour() == 18 && today.getMinute() <= 38)) {
				dayMB = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -1);
			} else if (today.getHour() > 18 || (today.getHour() == 18 && today.getMinute() > 38)) {
				dayMB = ddmmyyyy;
			}
			day = dayMB;
			
		} else {
			day = day.replace("-", "/");
			Date currentDate = DateUtil.toDate(ddmmyyyy, "dd/MM/yyyy");
			Date openDate = DateUtil.toDate(day, "dd/MM/yyyy");
			
			if (!openDate.before(currentDate)) {
				if (today.getHour() < 18 || (today.getHour() == 18 && today.getMinute() <= 38)) {
					day = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -1);
				}
			}
			dayMB = day;
		}

        List<Lottery> listLotteryMB = null;
        String key = "MB_" + dayMB.replace("/", "");
		List<String> listLotteryDuoiMB = null;
		List<String> listLotteryDauMB = null;
		
		if (hLotteryMB.containsKey(key)) {
			listLotteryMB = hLotteryMB.get(key);
			listLotteryDuoiMB = hLotteryDauDuoiMB.get(key);
			// ban mobile lay dau
			// if("mobile".equals(strMobile)){
			listLotteryDauMB = hLotteryDauDuoiMB.get(key + "_dau");
			// }
		} else {
			listLotteryMB = lotteryRequest.parserLotteryResultOfRegion(dayMB, "MB");
			if (listLotteryMB != null && !listLotteryMB.isEmpty()) {
				listLotteryDuoiMB = StringPro.filterDauOrDuoi(listLotteryMB.get(0), true);
				hLotteryMB.put(key, listLotteryMB);
				hLotteryDauDuoiMB.put(key, listLotteryDuoiMB);

				// ban mobile lay dau
				// if("mobile".equals(strMobile)){
				listLotteryDauMB = StringPro.filterDauOrDuoi(listLotteryMB.get(0), false);
				hLotteryDauDuoiMB.put(key + "_dau", listLotteryDauMB);
				// }
			}
		}


		mod.addObject("listLotteryMB", listLotteryMB);

		mod.addObject("listLotteryDuoiMB", listLotteryDuoiMB);

		mod.addObject("listLotteryDauMB", listLotteryDauMB);

        
		String weekMB = DateUtil.getWeekFromDate(dayMB);
        mod.addObject("ddmmyyyy", dayMB);
        mod.addObject("dayOfWeek", weekMB);
        String linkMBWeek = "xsmb-"+ DaiCaThang.toUrlFriendly(weekMB).toLowerCase() +".html";
		mod.addObject("linkMBThu",linkMBWeek);
		String linkMBDay = "xsmb-"+dayMB.replace("/", "-") + ".html"; 
		mod.addObject("linkMBDay",linkMBDay);
        
        mod.addObject("categoryNews", "MIEN BAC");
        mod.addObject("activeMenu", 2);
        mod.setViewName("/xsmb_ngay");

        return mod;
    }

}
