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
public class IndexController extends BaseController {

	private static HashMap<String, List<Lottery>> hLotteryMB = null;
	private static HashMap<String, Lottery> hLotterysMT = null;
	
	private static HashMap<String, Lottery> hLotterys = null;
	private static HashMap<String, List<String>> hLotterysDauDuoi = null;
	
	private static HashMap<String, String> hNumSizeMT = null;
	private static HashMap<String, List<String>> hLotteryDauDuoiMT = null;
	
	private static HashMap<String, Lottery> hLotterysMN = null;
    private static HashMap<String, List<String>> hLotteryDauDuoiMN = null; //temp se bo di
    private static HashMap<String, String> hNumSizeMN = null;

	private static String sDDMMYYYY = null;

	public IndexController() {
	}

	@Override
	protected void loadBase() {
		super.loadBase(); // To change body of generated methods, choose Tools | Templates.
		if (hLotteryMB == null) {
			hLotteryMB = new HashMap<String, List<Lottery>>();
		}
		
		if (hLotterysMT == null) {
            hLotterysMT = new HashMap<String, Lottery>();
        }
		
		if (hLotterys == null) {
			hLotterys = new HashMap<String, Lottery>();
		}

		if (hLotterysDauDuoi == null) {
			hLotterysDauDuoi = new HashMap<>();
		}
		
		if (hNumSizeMT == null) {
            hNumSizeMT = new HashMap<String, String>();
        }

		if (hLotteryDauDuoiMT == null) {
			hLotteryDauDuoiMT = new HashMap<String, List<String>>();
        }
		
		if (hLotterysMN == null) {
            hLotterysMN = new HashMap<String, Lottery>();
        }
        if (hLotteryDauDuoiMN == null) {
            hLotteryDauDuoiMN = new HashMap<String, List<String>>();
        }
        
        if (hNumSizeMN == null) {
            hNumSizeMN = new HashMap<String, String>();
        }
		
		if (sDDMMYYYY == null || !ddmmyyyy.equals(sDDMMYYYY)) {
			hLotteryMB.clear();
			hLotterys.clear();
			hLotterysDauDuoi.clear();
			
			hLotteryDauDuoiMT.clear();
			hNumSizeMT.clear();
			hLotterysMT.clear();
			
			hLotteryDauDuoiMN.clear();
			hNumSizeMN.clear();
			hLotterysMN.clear();
			
			sDDMMYYYY = ddmmyyyy;
			
			
		}
	}

	@Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod = super.handleRequestInternal(request, response);
        LotteryRequest lotteryRequest = new LotteryRequest();
        //lay ket qua xoso mien bac
        String day = request.getParameter("d");
        String day1 = request.getParameter("d"); //for MT
        String day2 = request.getParameter("d"); //for MN
        
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

        List<Lottery> listLotteryMB = null;
        List<String> listLotterysDuoiMB = null;
        List<String> listLotterysDauMB = null;
        String key = "MB_" + dayMB.replace("/", "");
        
        if (hLotteryMB.containsKey(key)) {
            listLotteryMB = hLotteryMB.get(key);
            listLotterysDuoiMB = hLotterysDauDuoi.get(key);
            //ban mobile lay dau
            //if("mobile".equals(strMobile)){
            listLotterysDauMB = hLotterysDauDuoi.get(key + "_dau");
            
        } else {
            listLotteryMB = lotteryRequest.parserLotteryResultOfRegion(dayMB, "MB");
            		
            if(listLotteryMB != null && listLotteryMB.size() > 1) {
                listLotteryMB = listLotteryMB.subList(0, 0);
            }
            if (listLotteryMB != null && !listLotteryMB.isEmpty()) {
            		hLotteryMB.put(key, listLotteryMB);
                listLotterysDuoiMB = StringPro.filterDauOrDuoi(listLotteryMB.get(0), true);
                hLotterysDauDuoi.put(key, listLotterysDuoiMB);
                listLotterysDauMB = StringPro.filterDauOrDuoi(listLotteryMB.get(0), false);
                hLotterysDauDuoi.put(key + "_dau", listLotterysDauMB);
                
             
            }
        }
        
        
        ////------------ Mien trung -------------------------------
        String dayMT = "";
        if (day1 == null || "".equals(day1)) {
            Today today = new Today();

            if (today.getHour() < 17 || (today.getHour() == 17 && today.getMinute() <= 44)) {
                dayMT = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -1);
            } else if (today.getHour() > 17 || (today.getHour() == 17 && today.getMinute() >44)) {
                dayMT = ddmmyyyy;
            }

            day1 = ddmmyyyy;
        } else {
            day1 = day1.replace("-", "/");
            if (ddmmyyyy.equals(day)) {
                day1 = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -1);
            }
            dayMT = day1;
        }

        Lottery lotterysMT = null;
        List<String> listLotteryDuoiMT = null;
        int numSizeMT = 0;
        String keyMT = "MT_" + dayMT.replace("/", "");
        if (hLotterysMT.containsKey(keyMT)) {
            lotterysMT = hLotterysMT.get(keyMT);
            listLotteryDuoiMT = hLotteryDauDuoiMT.get(keyMT);
            try {
                numSizeMT = Integer.parseInt(hNumSizeMT.get(keyMT));
            } catch (Exception e) {
            }

        } else {
            List<Lottery> listLotteryMT = lotteryRequest.parserLotteryResultOfRegion(dayMT, "MT");
            if (listLotteryMT != null && !listLotteryMT.isEmpty()) {
                lotterysMT = findLotterys(listLotteryMT, dayMT);
                listLotteryDuoiMT = findDuoi(listLotteryMT);
                numSizeMT = listLotteryMT.size();
                try {
                    hNumSizeMT.put(keyMT, String.valueOf(numSizeMT));
                } catch (Exception e) {
                }
                hLotterysMT.put(keyMT, lotterysMT);
                hLotteryDauDuoiMT.put(keyMT, listLotteryDuoiMT);

            }
        }
        
        //-----------------Mien Nam ----------------------
        String dayMN = "";
        if (day2 == null || "".equals(day2)) {
            Today today = new Today();

            if (today.getHour() < 16 || (today.getHour() == 16 && today.getMinute() <= 44)) {
                dayMN = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -1);
            } else if (today.getHour() > 16 || (today.getHour() == 16 && today.getMinute() > 44)) {
                dayMN = ddmmyyyy;
            }

            day2 = ddmmyyyy;
        } else {
            day2 = day2.replace("-", "/");
            if (ddmmyyyy.equals(day)) {
                day2 = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -1);
            }
            dayMN = day2;
        }


        Lottery lotterysMN = null;
        List<String> listLotteryDuoiMN = null;
        int numSizeMN = 0;
        String keyMN = "MN_" + dayMN.replace("/", "");
        if (hLotterysMN.containsKey(keyMN)) {
            lotterysMN = hLotterysMN.get(keyMN);
            listLotteryDuoiMN = hLotteryDauDuoiMN.get(keyMN);
            try {
                numSizeMN = Integer.parseInt(hNumSizeMN.get(keyMN));
            } catch (Exception e) {
            }
        } else {
            List<Lottery> listLotteryMN = lotteryRequest.parserLotteryResultOfRegion(dayMN, "MN");
            if (listLotteryMN != null && !listLotteryMN.isEmpty()) {
                lotterysMN = findLotterys(listLotteryMN, dayMN);
                listLotteryDuoiMN = findDuoi(listLotteryMN);
                numSizeMN = listLotteryMN.size();
                try {
                    hNumSizeMN.put(keyMN, String.valueOf(numSizeMN));
                } catch (Exception e) {
                }
                hLotterysMN.put(keyMN, lotterysMN);
                hLotteryDauDuoiMN.put(keyMN, listLotteryDuoiMN);
            }
        }
        
        mod.addObject("listLotteryMB", listLotteryMB);

        mod.addObject("listLotteryDuoiMB", listLotterysDuoiMB);
        mod.addObject("listLotteryDauMB", listLotterysDauMB);
        
        mod.addObject("lotterysMT", lotterysMT);
        mod.addObject("listLotteryDuoiMT", listLotteryDuoiMT);
        mod.addObject("numSizeMT", numSizeMT);
        
        mod.addObject("lotterysMN", lotterysMN);
        mod.addObject("listLotteryDuoiMN", listLotteryDuoiMN);
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
        
        mod.setViewName("/index");
        
        //String url_ca = "/trang-chu.html";
        String url_ca = "/";
        mod.addObject("url_ca", Constants.URL_CAN + url_ca);

        return mod;
    }

}
