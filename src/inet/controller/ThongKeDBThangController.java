/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import inet.bean.Lottery;
import inet.request.LotteryRequest;
import inet.util.DateProc;
import inet.util.DateUtil;
import inet.util.Today;

/**
 *
 * @author hanhlm
 */
public class ThongKeDBThangController extends BaseController {

	private static HashMap<String, Lottery> hLotterys = null;

	private static String sDDMMYYYY = null;
	public ThongKeDBThangController() {
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

		// -----------------Lay kq thang--------------
		Date openDate = DateUtil.toDate(dayMB, "dd/MM/yyyy");
		@SuppressWarnings("deprecation")
		int month = openDate.getMonth() + 1;
		
		
		String year = temp[2];
		int yearP = Integer.parseInt(year);
		
		String monthParram = request.getParameter("month");
		int monthParamInt = Integer.parseInt(monthParram);
		int lastDay = getLastDayOfMonth(monthParamInt);
		
		if(monthParamInt > month) {
			yearP = yearP - 1;
			year = String.valueOf(yearP);
		}
		
		if(monthParamInt < 10) {
			monthParram = "0"+monthParamInt;
		}
		
		String startDate = "01/"+monthParram+"/"+year;
		String endDate = lastDay+"/"+monthParram+"/"+year;
		List<Lottery> lotteries = lotteryRequest.parserLotteryResult("XSTD", startDate, endDate);
		
		

		// ---------------------
		mod.addObject("activeMenu", '7');
		mod.addObject("lotteryMonth", lotteries);
		mod.setViewName("/include/thongke_db_thang");

		return mod;
	}

}
