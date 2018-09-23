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
public class KQXSNgayController extends BaseController {

	private static HashMap<String, List<Lottery>> hLotteryMB = null;
	private static HashMap<String, Lottery> hLotterys = null;
	private static HashMap<String, List<String>> hLotteryDauDuoi = null;
	private static HashMap<String, List<String>> hLotteryDauDuoiMB = null;
	private static HashMap<String, String> hNumSize = null;
	private static String sDDMMYYYY = null;

	public KQXSNgayController() {
	}

	@Override
	protected void loadBase() {
		super.loadBase(); // To change body of generated methods, choose Tools | Templates.
		if (hLotteryMB == null) {
			hLotteryMB = new HashMap<String, List<Lottery>>();
		}
		if (hLotterys == null) {
			hLotterys = new HashMap<String, Lottery>();
		}
		if (hLotteryDauDuoi == null) {
			hLotteryDauDuoi = new HashMap<String, List<String>>();
		}
		
		if (hLotteryDauDuoiMB == null) {
			hLotteryDauDuoiMB = new HashMap<>();
		}
		if (hNumSize == null) {
			hNumSize = new HashMap<String, String>();
		}
		if (sDDMMYYYY == null || !ddmmyyyy.equals(sDDMMYYYY)) {
			hLotteryMB.clear();
			hLotterys.clear();
			hLotteryDauDuoi.clear();
			hLotteryDauDuoiMB.clear();
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
		
		String url_ca = "/kqxs-"+day+".html";
		mod.addObject("url_ca", Constants.URL_CAN + url_ca);
		
		String dayMB = "";
		String dayMT = "";
		String dayMN = "";
		if (day == null || "".equals(day)) {
			Today today = new Today();
			if (today.getHour() < 18 || (today.getHour() == 18 && today.getMinute() <= 38)) {
				dayMB = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -1);
			} else if (today.getHour() > 18 || (today.getHour() == 18 && today.getMinute() > 38)) {
				dayMB = ddmmyyyy;
			}

			if (today.getHour() < 17 || (today.getHour() == 17 && today.getMinute() <= 38)) {
				dayMT = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -1);
			} else if (today.getHour() > 17 || (today.getHour() == 17 && today.getMinute() > 38)) {
				dayMT = ddmmyyyy;
			}

			if (today.getHour() < 16 || (today.getHour() == 16 && today.getMinute() <= 38)) {
				dayMN = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -1);
			} else if (today.getHour() > 16 || (today.getHour() == 16 && today.getMinute() > 38)) {
				dayMN = ddmmyyyy;
			}

			day = ddmmyyyy;
		} else {
			day = day.replace("-", "/");
			if (ddmmyyyy.equals(day)) {
				day = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -1);
			}
			dayMB = day;
			dayMT = day;
			dayMN = day;
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

		Lottery lotterysMN = null;
		List<String> listLotteryDuoiMN = null;
		int numSizeMN = 0;
		key = "MN_" + dayMN.replace("/", "");
		if (hLotterys.containsKey(key)) {
			lotterysMN = hLotterys.get(key);
			listLotteryDuoiMN = hLotteryDauDuoi.get(key);
			try {
				numSizeMN = Integer.parseInt(hNumSize.get(key));
			} catch (Exception e) {
			}
		} else {
			List<Lottery> listLotteryMN = lotteryRequest.parserLotteryResultOfRegion(dayMN, "MN");
			if (listLotteryMN != null && !listLotteryMN.isEmpty()) {
				lotterysMN = findLotterys(listLotteryMN, dayMN);
				listLotteryDuoiMN = findDuoi(listLotteryMN);
				numSizeMN = listLotteryMN.size();
				try {
					hNumSize.put(key, String.valueOf(numSizeMN));
				} catch (Exception e) {
				}
				hLotterys.put(key, lotterysMN);
				hLotteryDauDuoi.put(key, listLotteryDuoiMN);
			}
		}

		mod.addObject("linkXS", "kqxs");
		mod.addObject("linkXSGan", "kqxs");
		mod.addObject("listLotteryMB", listLotteryMB);
		mod.addObject("lotterysMT", lotterysMT);
		mod.addObject("lotterysMN", lotterysMN);

		mod.addObject("listLotteryDuoiMB", listLotteryDuoiMB);
		mod.addObject("listLotteryDuoiMT", listLotteryDuoiMT);
		mod.addObject("listLotteryDuoiMN", listLotteryDuoiMN);

		mod.addObject("listLotteryDauMB", listLotteryDauMB);

		mod.addObject("numSizeMT", numSizeMT);
		mod.addObject("numSizeMN", numSizeMN);
        
        String weekMB = DateUtil.getWeekFromDate(dayMB);
        mod.addObject("ddmmyyyy", dayMB);
        mod.addObject("dayOfWeek", weekMB);
        String linkMBWeek = "xsmb-"+ DaiCaThang.toUrlFriendly(weekMB).toLowerCase() +".html";
		mod.addObject("linkMBThu",linkMBWeek);
		String linkMBDay = "xsmb-"+dayMB.replace("/", "-") + ".html"; 
		mod.addObject("linkMBDay",linkMBDay);
        
		String weekMT = DateUtil.getWeekFromDate(dayMT);
        mod.addObject("ddmmyyyyMT", dayMT);
        mod.addObject("dayOfWeekMT", weekMT);
        String linkMTWeek = "xsmt-"+ DaiCaThang.toUrlFriendly(weekMT).toLowerCase() +".html";
		mod.addObject("linkMTThu",linkMTWeek);
		String linkMTDay = "xsmt-"+dayMT.replace("/", "-") + ".html"; 
		mod.addObject("linkMTDay",linkMTDay);
        
		String weekMN = DateUtil.getWeekFromDate(dayMN);
        mod.addObject("ddmmyyyyMN", dayMN);
        mod.addObject("dayOfWeekMN", weekMN);
        String linkMNWeek = "xsmn-"+ DaiCaThang.toUrlFriendly(weekMN).toLowerCase() +".html";
		mod.addObject("linkMNThu",linkMNWeek);
		String linkMNDay = "xsmn-"+dayMN.replace("/", "-") + ".html"; 
		mod.addObject("linkMNDay",linkMNDay);
        
        mod.addObject("categoryNews", "");
        mod.setViewName("/kqxs_ngay");

        return mod;
    }

}
