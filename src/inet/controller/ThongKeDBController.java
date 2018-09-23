/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.thongke.ThongKeDacBiet;
import com.thongke.ThongKeLoto;

import inet.bean.Loto;
import inet.bean.Lottery;
import inet.bean.News;
import inet.constant.Constants;
import inet.request.LotteryRequest;
import inet.util.Contant;
import inet.util.DateProc;
import inet.util.DateUtil;
import inet.util.LotoUtils;
import inet.util.Today;

/**
 *
 * @author hanhlm
 */
public class ThongKeDBController extends BaseController {

	private static HashMap<String, Lottery> hLotterys = null;
	private List<News> listLotteryNews = null;

	private static String sDDMMYYYY = null;
	public ThongKeDBController() {
	}

	@Override
	protected void loadBase() {
		super.loadBase(); // To change body of generated methods, choose Tools | Templates.

		if (hLotterys == null) {
			hLotterys = new HashMap<String, Lottery>();
		}

		if (sDDMMYYYY == null || !ddmmyyyy.equals(sDDMMYYYY)) {
			hLotterys.clear();

			sDDMMYYYY = ddmmyyyy;

		}
	}

	private int getLastDayOfMonth(int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);

		Date date = calendar.getTime();

		return date.getDate();
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mod = super.handleRequestInternal(request, response);
		LotteryRequest lotteryRequest = new LotteryRequest();
		// lay ket qua xoso mien bac
		String dayMB = "";
		Today today = new Today();
		if (today.getHour() < 18 || (today.getHour() == 18 && today.getMinute() <= 44)) {
			dayMB = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -1);
		} else {
			dayMB = ddmmyyyy;
		}
		String temp[] = dayMB.split("/");
		
		String url_ca = "/thong-ke-giai-dac-biet.html";
		mod.addObject("url_ca", Constants.URL_CAN + url_ca);

		// -----------------Lay kq thang--------------
		Date openDate = DateUtil.toDate(dayMB, "dd/MM/yyyy");
		@SuppressWarnings("deprecation")
		int month = openDate.getMonth() + 1;
		int lastDay = getLastDayOfMonth(month);
		
		String months = "";
		if(month < 10) {
			months = "0"+month;
		}
		String year = temp[2];
		
		String startDate = "01/"+months+"/"+year;
		String endDate = lastDay+"/"+months+"/"+year;
		List<Lottery> lotteries = lotteryRequest.parserLotteryResult("XSTD", startDate, endDate);
		
		ThongKeDacBiet thongKeDacBiet = new ThongKeDacBiet();
        String[][] kqYear = thongKeDacBiet.findBangDacBietThang("01/01/"+year, "31/12/"+year);
		
		//---------------------end lay kq thang ---------------------------
		

		// ---------------------------it ve, hay
		// ve-------------------------------------------
		String tungay = DateProc.TimestampYYYYMMDD(
				DateProc.String2Timestamp(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(dayMB), -70)));
		String denngay = DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(dayMB));
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
			for (int i = 0; i < 10; i++) {
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
		// -----------------------------------------
//		String dateForBDB = DateUtil.addDateFromString(dayMB, "dd/MM/yyy", -70);
//		listLoteryDB = lotteryRequest.parserLotteryResult("XSTD", dateForBDB, dayMB);
//		mod.addObject("bdb", listLoteryDB);

		// ---------lay tin tuc--------
		listLotteryNews = lotteryRequest.parserLotteryNewsProvince(Contant.SITE_ID, "1", Contant.MAX_NEWS, "MIEN BAC");
		mod.addObject("listLotteryNews", listLotteryNews);

		// ---------------------
		mod.addObject("activeMenu", 7);
		mod.addObject("lotteryMonth", lotteries);
		mod.addObject("month", month);
		mod.addObject("year", year);
		mod.addObject("week", 10);
		mod.addObject("lotteryYear", kqYear);
		//lo gan
		mod.addObject("listLoto", listLoto);
		mod.addObject("code", "XSTD");
		mod.addObject("max30LotoList", max30LotoList);
		mod.addObject("min30LotoList", min30LotoList);
		mod.addObject("categoryNews", "MIEN BAC");
		mod.addObject("linkXS", "xsmb");
		mod.addObject("linkXSGan", "xsmb");
		mod.setViewName("/thongke_db_mb");

		return mod;
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
