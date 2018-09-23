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
public class IndexMTController extends BaseController {

	private static HashMap<String, Lottery> hLotteryMT = null;
	private static HashMap<String, List<String>> hLotteryDauDuoi = null; //temp se bo di
    private static HashMap<String, String> hNumSize = null;
	
	private List<News> listLotteryNews = null;

	private static String sDDMMYYYY = null;

	public IndexMTController() {
	}

	@Override
	protected void loadBase() {
		super.loadBase(); // To change body of generated methods, choose Tools | Templates.
		if (hLotteryMT == null) {
			hLotteryMT = new HashMap<String, Lottery>();
		}

		if (hLotteryDauDuoi == null) {
            hLotteryDauDuoi = new HashMap<String, List<String>>();
        }
        
        if (hNumSize == null) {
        		hNumSize = new HashMap<String, String>();
        }

		if (sDDMMYYYY == null || !ddmmyyyy.equals(sDDMMYYYY)) {
			hLotteryMT.clear();
            hLotteryDauDuoi.clear();
            hNumSize.clear();
            sDDMMYYYY = ddmmyyyy;

		}
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mod = super.handleRequestInternal(request, response);
		LotteryRequest lotteryRequest = new LotteryRequest();
        //lay ket qua xoso mien bac
        String day = request.getParameter("d");
        String dayMT = "";
        if (day == null || "".equals(day)) {
            Today today = new Today();

            if (today.getHour() < 17 || (today.getHour() == 17 && today.getMinute() <= 44)) {
                dayMT = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -1);
            } else if (today.getHour() > 17 || (today.getHour() == 17 && today.getMinute() >44)) {
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

		int indexDay = 0; // dem 7 ngay
		String dayQuerry = new String(dayMT);
		
		while (indexDay < 3) {
			
			
			Lottery lotterysMT = null;
	        List<String> listLotteryDuoiMT = null;
	        int numSizeMT = 0;
	        String key = "MT_" + dayQuerry.replace("/", "");
	        if (hLotteryMT.containsKey(key)) {
	        		lotterysMT = hLotteryMT.get(key);
	            listLotteryDuoiMT = hLotteryDauDuoi.get(key);
	            try {
	                numSizeMT = Integer.parseInt(hNumSize.get(key));
	            } catch (Exception e) {
	            }
	            
	            saveOBjectModel(lotterysMT, listLotteryDuoiMT, dayQuerry, mod, indexDay, numSizeMT);
	            indexDay++;
	        } else {
	            List<Lottery> listLotteryMT = lotteryRequest.parserLotteryResultOfRegion(dayQuerry, "MT");
	            if (listLotteryMT != null && !listLotteryMT.isEmpty()) {
	                lotterysMT = findLotterys(listLotteryMT, dayMT);
	                listLotteryDuoiMT = findDuoi(listLotteryMT);
	                numSizeMT = listLotteryMT.size();
	                try {
	                    hNumSize.put(key, String.valueOf(numSizeMT));
	                } catch (Exception e) {
	                }
	                hLotteryMT.put(key, lotterysMT);
	                hLotteryDauDuoi.put(key, listLotteryDuoiMT);
	                
	                saveOBjectModel(lotterysMT, listLotteryDuoiMT, dayQuerry, mod, indexDay, numSizeMT);
					indexDay++;
	            }
	        }

			dayQuerry = DateUtil.addDateFromString(dayQuerry, "dd/MM/yyyy", -1);
		}
		
		
        //---------lay tin tuc--------
		listLotteryNews = lotteryRequest.parserLotteryNewRegion(Contant.SITE_ID, "1", Contant.MAX_NEWS, "MIEN TRUNG");
		mod.addObject("listLotteryNews", listLotteryNews);
		mod.addObject("categoryNews", "MIEN TRUNG");
		mod.addObject("activeMenu", 3);
		mod.setViewName("/index_mt");
		
		String url_ca = "/xsmt-xo-so-mien-trung.html";
        mod.addObject("url_ca", Constants.URL_CAN + url_ca);

		return mod;
	}

	public void saveOBjectModel(Lottery listLotteryMT, List<String> listLotteryDuoiMT,
			String dayQuerry, ModelAndView mod, int indexDay, int numSizeMT) {
		String index = "";
		
		if(indexDay > 0) {
			index = "" + indexDay;
		} 
		
		mod.addObject("lotterysMT"+index, listLotteryMT);
		mod.addObject("listLotteryDuoiMT"+index, listLotteryDuoiMT);
		mod.addObject("ddmmyyyyMT"+index, dayQuerry);
		
		String weekMT = DateUtil.getWeekFromDate(dayQuerry);
		mod.addObject("dayOfWeekMT"+index, weekMT);
		mod.addObject("numSizeMT"+index, numSizeMT);
		
		String linkMTWeek = "xsmt-"+ DaiCaThang.toUrlFriendly(weekMT).toLowerCase() +".html";
		mod.addObject("linkMTThu"+index,linkMTWeek);
		String linkMTDay = "xsmt-"+dayQuerry.replace("/", "-") + ".html"; 
		mod.addObject("linkMTDay"+index,linkMTDay);
	}
}
