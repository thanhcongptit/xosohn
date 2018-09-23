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
public class IndexMNController extends BaseController {

	private static HashMap<String, Lottery> hLotteryMN = null;
	private static HashMap<String, List<String>> hLotteryDauDuoiMN = null; //temp se bo di
    private static HashMap<String, String> hNumSize = null;
	
	private List<News> listLotteryNews = null;

	private static String sDDMMYYYY = null;

	public IndexMNController() {
	}

	@Override
	protected void loadBase() {
		super.loadBase(); // To change body of generated methods, choose Tools | Templates.
		if (hLotteryMN == null) {
			hLotteryMN = new HashMap<String, Lottery>();
		}

		if (hLotteryDauDuoiMN == null) {
			hLotteryDauDuoiMN = new HashMap<String, List<String>>();
        }
        
        if (hNumSize == null) {
        		hNumSize = new HashMap<String, String>();
        }

		if (sDDMMYYYY == null || !ddmmyyyy.equals(sDDMMYYYY)) {
			hLotteryMN.clear();
			hLotteryDauDuoiMN.clear();
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

		int indexDay = 0; // dem 7 ngay
		String dayQuerry = new String(dayMN);
		
		while (indexDay < 3) {
			
			
			Lottery lotterysMT = null;
	        List<String> listLotteryDuoiMT = null;
	        int numSizeMT = 0;
	        String key = "MN_" + dayQuerry.replace("/", "");
	        if (hLotteryMN.containsKey(key)) {
	        		lotterysMT = hLotteryMN.get(key);
	            listLotteryDuoiMT = hLotteryDauDuoiMN.get(key);
	            try {
	                numSizeMT = Integer.parseInt(hNumSize.get(key));
	            } catch (Exception e) {
	            }
	            
	            saveOBjectModel(lotterysMT, listLotteryDuoiMT, dayQuerry, mod, indexDay, numSizeMT);
	            indexDay++;
	        } else {
	            List<Lottery> listLotteryMT = lotteryRequest.parserLotteryResultOfRegion(dayQuerry, "MN");
	            if (listLotteryMT != null && !listLotteryMT.isEmpty()) {
	                lotterysMT = findLotterys(listLotteryMT, dayMN);
	                listLotteryDuoiMT = findDuoi(listLotteryMT);
	                numSizeMT = listLotteryMT.size();
	                try {
	                    hNumSize.put(key, String.valueOf(numSizeMT));
	                } catch (Exception e) {
	                }
	                hLotteryMN.put(key, lotterysMT);
	                hLotteryDauDuoiMN.put(key, listLotteryDuoiMT);
	                
	                saveOBjectModel(lotterysMT, listLotteryDuoiMT, dayQuerry, mod, indexDay, numSizeMT);
					indexDay++;
	            }
	        }

			dayQuerry = DateUtil.addDateFromString(dayQuerry, "dd/MM/yyyy", -1);
		}
		
		
        //---------lay tin tuc--------
		listLotteryNews = lotteryRequest.parserLotteryNewRegion(Contant.SITE_ID, "1", Contant.MAX_NEWS, "MIEN NAM");
		mod.addObject("listLotteryNews", listLotteryNews);
		mod.addObject("categoryNews", "MIEN NAM");
		mod.addObject("activeMenu", 4);
		mod.setViewName("/index_mn");
		String url_ca = "/xsmn-xo-so-mien-nam.html";
        mod.addObject("url_ca", Constants.URL_CAN + url_ca);
		return mod;
	}

	public void saveOBjectModel(Lottery listLotteryMT, List<String> listLotteryDuoiMT,
			String dayQuerry, ModelAndView mod, int indexDay, int numSizeMT) {
		String index = "";
		
		if(indexDay > 0) {
			index = "" + indexDay;
		} 
		
		mod.addObject("lotterysMN"+index, listLotteryMT);
		mod.addObject("listLotteryDuoiMN"+index, listLotteryDuoiMT);
		mod.addObject("ddmmyyyyMN"+index, dayQuerry);
		mod.addObject("numSizeMN"+index, numSizeMT);
		String weekMN = DateUtil.getWeekFromDate(dayQuerry);
		mod.addObject("dayOfWeekMN"+index, weekMN);
		
		String linkMNWeek = "xsmn-"+ DaiCaThang.toUrlFriendly(weekMN).toLowerCase() +".html";
		mod.addObject("linkMNThu"+index,linkMNWeek);
		String linkMNDay = "xsmn-"+dayQuerry.replace("/", "-") + ".html"; 
		mod.addObject("linkMNDay"+index,linkMNDay);
	}
}
