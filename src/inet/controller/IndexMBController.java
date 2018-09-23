/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.thongke.ThongKeLoto;

import inet.bean.DacBiet;
import inet.bean.Loto;
import inet.bean.Lottery;
import inet.bean.News;
import inet.constant.Constants;
import inet.request.LotteryRequest;
import inet.util.Contant;
import inet.util.DaiCaThang;
import inet.util.DateProc;
import inet.util.DateUtil;
import inet.util.LotoUtils;
import inet.util.StringPro;
import inet.util.Today;

/**
 *
 * @author hanhlm
 */
public class IndexMBController extends BaseController {

	private static HashMap<String, List<Lottery>> hLotteryMB = null;
	private static HashMap<String, Lottery> hLotterys = null;
	//private static HashMap<String, List<List<Thongke>>> hLotterysDauDuoi = null;
	private static HashMap<String, List<String>> hLotterysDauDuoi = null;
	private List<News> listLotteryNews = null;

	private static String sDDMMYYYY = null;
	List<Lottery> listLoteryDB = new ArrayList<Lottery>();

	public IndexMBController() {
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

		if (hLotterysDauDuoi == null) {
			hLotterysDauDuoi = new HashMap<>();
		}


		if (sDDMMYYYY == null || !ddmmyyyy.equals(sDDMMYYYY)) {
			hLotteryMB.clear();
			hLotterys.clear();
			hLotterysDauDuoi.clear();

			sDDMMYYYY = ddmmyyyy;

		}
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mod = super.handleRequestInternal(request, response);
		LotteryRequest lotteryRequest = new LotteryRequest();
		// lay ket qua xoso mien bac
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

		int indexDay = 0; // dem 7 ngay
		String dayQuerry = new String(dayMB);
		
		while (indexDay < 7) {
			
			List<Lottery> listLotteryMB = null;
			List<String> listLotterysDuoiMB = null;
			List<String> listLotterysDauMB = null;
			String key = "MB_" + dayQuerry.replace("/", "");

			if (hLotteryMB.containsKey(key)) {
				listLotteryMB = hLotteryMB.get(key);
				listLotterysDuoiMB = hLotterysDauDuoi.get(key);
				listLotterysDauMB = hLotterysDauDuoi.get(key + "_dau");
				saveOBjectModel(listLotteryMB, listLotterysDuoiMB, listLotterysDauMB, dayQuerry, mod, indexDay);
				indexDay++;
				
			} else {
				listLotteryMB = lotteryRequest.parserLotteryResultOfRegion(dayQuerry, "MB");

				if (listLotteryMB != null && listLotteryMB.size() > 1) {
					listLotteryMB = listLotteryMB.subList(0, 0);
				}
				if (listLotteryMB != null && !listLotteryMB.isEmpty()) {
					hLotteryMB.put(key, listLotteryMB);
					listLotterysDuoiMB = StringPro.filterDauOrDuoi(listLotteryMB.get(0), true);
					hLotterysDauDuoi.put(key, listLotterysDuoiMB);
					listLotterysDauMB = StringPro.filterDauOrDuoi(listLotteryMB.get(0), false);
					hLotterysDauDuoi.put(key + "_dau", listLotterysDauMB);
					
					saveOBjectModel(listLotteryMB, listLotterysDuoiMB, listLotterysDauMB, dayQuerry, mod, indexDay);
					indexDay++;

				}
			}
			
			dayQuerry = DateUtil.addDateFromString(dayQuerry, "dd/MM/yyyy", -1);
		}
		// ---------------------------it ve, hay
		// ve-------------------------------------------
		String tungay = DateProc.TimestampYYYYMMDD(
				DateProc.String2Timestamp(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(day), -30)));
		String denngay = DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(day));
		String danlotoTK = LotoUtils.getAllLoto(",");
		ThongKeLoto thongKeLoto = new ThongKeLoto();
		List<Loto> listLoto = thongKeLoto.findLoto(danlotoTK, "XSTD", tungay, denngay);
		
		// Nhieu
		List<Loto> max30LotoList = new ArrayList<>();
		// It
		List<Loto> min30LotoList = new ArrayList<>();
		if (listLoto != null && listLoto.size() > 0) {
			Loto[] arrayMaxLoto = listLoto.toArray(new Loto[listLoto.size()]);
			arrayMaxLoto = bubbleSort(arrayMaxLoto, listLoto.size(), false);
			for (int i = 0 ; i < 10; i++) {
				max30LotoList.add(arrayMaxLoto[i]);
			}

			Loto[] arrayMinLoto = listLoto.toArray(new Loto[listLoto.size()]);
			arrayMinLoto = bubbleSort(arrayMinLoto, listLoto.size(), true);
			for (int i = 0; i < 10; i++) {
				min30LotoList.add(arrayMinLoto[i]);
			}
		}
		
		Collections.sort(listLoto);
		listLoto = listLoto.subList(0, 10);
		//-----------------------------------------
		String dateForBDB = DateUtil.addDateFromString(dayMB, "dd/MM/yyy", -75);
		listLoteryDB = lotteryRequest.parserLotteryResult("XSTD", dateForBDB, dayMB);
		listLoteryDB = listLoteryDB.subList(0, 70);
		mod.addObject("bdb", listLoteryDB);
		
		// ------------------ Cham dau DB 
		DacBiet[] dauDacBiet = thongKeLoto.findChamDau(listLoteryDB, dayMB);
		mod.addObject("dauDacBiet", dauDacBiet);
		
        //---------lay tin tuc--------
		listLotteryNews = lotteryRequest.parserLotteryNewsProvince(Contant.SITE_ID, "1", Contant.MAX_NEWS, "MIEN BAC");
		mod.addObject("listLotteryNews", listLotteryNews);
		
		//---------------------
		
        mod.addObject("listLoto", listLoto);
        mod.addObject("code", "XSTD");
		mod.addObject("max30LotoList", max30LotoList);
		mod.addObject("min30LotoList", min30LotoList);
		mod.addObject("categoryNews", "MIEN BAC");
		mod.addObject("activeMenu", 2);
		mod.addObject("linkXS", "xsmb");
		mod.addObject("linkXSGan", "xsmb");
		mod.setViewName("/index_mb");
		
		String url_ca = "/xsmb-xo-so-mien-bac.html";
        mod.addObject("url_ca", Constants.URL_CAN + url_ca);

		return mod;
	}

	public void saveOBjectModel(List<Lottery> listLotteryMB, List<String> listLotterysDuoiMB, 
			List<String> listLotterysDauMB, String dayQuerry, ModelAndView mod, int indexDay) {
		String index = "";
		
		if(indexDay > 0) {
			index = "" + indexDay;
		} 
		
		mod.addObject("listLotteryMB"+index, listLotteryMB);
		mod.addObject("listLotteryDuoiMB"+index, listLotterysDuoiMB);
		mod.addObject("listLotteryDauMB"+index, listLotterysDauMB);
		mod.addObject("ddmmyyyy"+index, dayQuerry);
		
		String weekMB = DateUtil.getWeekFromDate(dayQuerry);
		mod.addObject("dayOfWeek"+index, weekMB);
		
		String linkMBWeek = "xsmb-"+ DaiCaThang.toUrlFriendly(weekMB).toLowerCase() +".html";
		mod.addObject("linkMBThu"+index,linkMBWeek);
		String linkMBDay = "xsmb-"+dayQuerry.replace("/", "-") + ".html"; 
		mod.addObject("linkMBDay"+index,linkMBDay);
	}
	
	private Loto[] bubbleSort(Loto arrLoto[], int size, boolean isAsc) {
		Loto tmp = null;
		for (int i = 0; i < size - 1; i++) {
			for (int j = size - 1; j > i; j--) {
				if (isAsc && (arrLoto[j].getSolanxuathien() < arrLoto[j - 1].getSolanxuathien())) {
					tmp = arrLoto[j - 1];
					arrLoto[j - 1] = arrLoto[j];
					arrLoto[j] = tmp;
				} else if (!isAsc && (arrLoto[j].getSolanxuathien() > arrLoto[j - 1].getSolanxuathien())) {
					tmp = arrLoto[j - 1];
					arrLoto[j - 1] = arrLoto[j];
					arrLoto[j] = tmp;
				}
			}
		}
		return arrLoto;
	}
	
}
