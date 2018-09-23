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
public class XSMNNgayController extends BaseController {

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
		if (hLotteryDauDuoi == null) {
			hLotteryDauDuoi = new HashMap<String, List<String>>();
		}
		
		if (hNumSize == null) {
			hNumSize = new HashMap<String, String>();
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
		
		String url_ca = "/xsmn-"+day+".html";
		mod.addObject("url_ca", Constants.URL_CAN + url_ca);
		
		String dayMN = "";
		Today today = new Today();
		
		if (day == null || "".equals(day)) {
			
			if (today.getHour() < 16 || (today.getHour() == 16 && today.getMinute() <= 38)) {
				dayMN = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -1);
			} else if (today.getHour() > 17 || (today.getHour() == 17 && today.getMinute() > 38)) {
				dayMN = ddmmyyyy;
			}
			day = dayMN;
			
		} else {
			day = day.replace("-", "/");
			Date currentDate = DateUtil.toDate(ddmmyyyy, "dd/MM/yyyy");
			Date openDate = DateUtil.toDate(day, "dd/MM/yyyy");
			
			if (!openDate.before(currentDate)) {
				if (today.getHour() < 16 || (today.getHour() == 16 && today.getMinute() <= 38)) {
					day = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -1);
				}
			}
			dayMN = day;
		}

		String key = "";
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

		mod.addObject("lotterysMN", lotterysMN);
		mod.addObject("listLotteryDuoiMN", listLotteryDuoiMN);
		mod.addObject("numSizeMN", numSizeMN);
		String weekMN = DateUtil.getWeekFromDate(dayMN);
        mod.addObject("ddmmyyyyMN", dayMN);
        mod.addObject("dayOfWeekMN", weekMN);
        String linkMNWeek = "xsmn-"+ DaiCaThang.toUrlFriendly(weekMN).toLowerCase() +".html";
		mod.addObject("linkMNThu",linkMNWeek);
		String linkMNDay = "xsmn-"+dayMN.replace("/", "-") + ".html"; 
		mod.addObject("linkMNDay",linkMNDay);
        
        mod.addObject("categoryNews", "MIEN NAM");
        List<News> listLotteryNews = lotteryRequest.parserLotteryNewRegion(Contant.SITE_ID, "1", Contant.MAX_NEWS, "MIEN NAM");
		mod.addObject("listLotteryNews", listLotteryNews);
        mod.addObject("activeMenu", 4);
        mod.setViewName("/xsmn_ngay");

        return mod;
    }

}
