/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.soicaupro.thongkebacnho.CommonUtil;
import com.thongke.ThongKeLoto;

import inet.bean.Loto;
import inet.bean.Lottery;
import inet.bean.LotteryCompany;
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
public class XSTinhNgayController extends BaseController {

	private static String sDDMMYYYY = null;

	public XSTinhNgayController() {
	}

	@Override
	protected void loadBase() {
		super.loadBase(); // To change body of generated methods, choose Tools | Templates.

		if (sDDMMYYYY == null || !ddmmyyyy.equals(sDDMMYYYY)) {
			sDDMMYYYY = ddmmyyyy;
		}
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mod = super.handleRequestInternal(request, response);
		String code = request.getParameter("province");
		
		String codes[] = code.split("-");
		code = "XS"+codes[0].toUpperCase();
		String d = request.getParameter("d");
		d = codes[1]+"-"+codes[2] +"-"+d;
		
		if(!CommonUtil.isEmptyString(d)) {
			d = DateUtil.formatDate(d);
		}
		
		String dayQuerry = d.replace("-", "/");
		
		String url_ca = "/"+code.toLowerCase()+"-"+d+".html";
		mod.addObject("url_ca", Constants.URL_CAN + url_ca);
		
		String day = null;

		if (code == null || "".equals(code)) {
			code = "XSHCM";
		} else {
			code = code.toUpperCase();
		}

		if (day == null) {
			day = ddmmyyyy;
		}

		List<Lottery> listLottery = null;
		List<List<String>> listLotteryDuoi = null;
		List<List<String>> listLotteryDau = null;
		LotteryCompany lotteryCompany = null;
		// thong ke giai dac biet
		String endDate = dayQuerry;
		String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(dayQuerry), -100);
		LotteryRequest lotteryRequest = new LotteryRequest();
		listLottery = lotteryRequest.parserLotteryResult(code, startDate, endDate);
		int length  = 0;
		if(listLottery.size() < 3) {
			length = listLottery.size();
		} else {
			length = 3;
		}
		
		listLottery = listLottery.subList(0, length);

		if (listLottery != null && !listLottery.isEmpty()) {
			List<String> listDuoiMB = null;
			List<String> listDauMB = null;
			for (int i = 0; i < length; i++) {
				listDuoiMB = StringPro.filterDauOrDuoi(listLottery.get(i), true);
				listDauMB = StringPro.filterDauOrDuoi(listLottery.get(i), false);
				if (listLotteryDuoi == null) {
					listLotteryDuoi = new ArrayList<List<String>>();
				}
				if (listLotteryDau == null) {
					listLotteryDau = new ArrayList<List<String>>();
				}
				listLotteryDuoi.add(listDuoiMB);
				listLotteryDau.add(listDauMB);
			}

			// code thuoc khu vuc nao
			lotteryCompany = findLotteryCompanyOfCode(code);
		}

		// -----Logan --------

		String danlotoTK = LotoUtils.getAllLoto(",");
		ThongKeLoto thongKeLoto = new ThongKeLoto();
		String tungay = DateProc.TimestampYYYYMMDD(
				DateProc.String2Timestamp(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(day), -210)));
		String denngay = DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(day));
		List<Loto> listLoto = thongKeLoto.findLoto(danlotoTK, code, tungay, denngay);
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
		// ---------------------------

		mod.addObject("listLottery", listLottery);
		mod.addObject("listLotteryDuoi", listLotteryDuoi);
		mod.addObject("listLotteryDau", listLotteryDau);
		mod.addObject("lotteryCompany", lotteryCompany);
		mod.addObject("ddmmyyyy", day);

		

		String href = "xs" + lotteryCompany.getRegion().toLowerCase() + "-";
		String hrefTinh = lotteryCompany.getCodeLowerCase() + "-";
		
		//String link = "xsmt-xo-so-mien-trung.html";
		String link = lotteryCompany.getCodeLowerCase()+"-ket-qua-xo-so-"+lotteryCompany.getLinkKq247()+".html";
		
		int activeMenu = 3;
		if(lotteryCompany.getRegion().equalsIgnoreCase("MN")) {
			//link = "xsmn-xo-so-mien-nam.html";
			activeMenu = 4;
		}
		
		mod.addObject("linkXS",link);
		mod.addObject("linkXSGan", lotteryCompany.getCodeLowerCase());
		
		

		if(length > 2) {
			mod.addObject("lottery1", listLottery.get(0));
			mod.addObject("listDau1", listLotteryDau.get(0));
			mod.addObject("listDuoi1", listLotteryDuoi.get(0));
			
			mod.addObject("lottery2", listLottery.get(1));
			mod.addObject("lottery3", listLottery.get(2));

			
			mod.addObject("listDau2", listLotteryDau.get(1));
			mod.addObject("listDau3", listLotteryDau.get(2));

			
			mod.addObject("listDuoi2", listLotteryDuoi.get(1));
			mod.addObject("listDuoi3", listLotteryDuoi.get(2));
			
			String date1 = listLottery.get(0).getOpenDate();
			String date2 = listLottery.get(1).getOpenDate();
			String date3 = listLottery.get(2).getOpenDate();
			String week1 = DateUtil.getWeekFromDate(date1);
			String linkMBWeek = href + DaiCaThang.toUrlFriendly(week1).toLowerCase() +".html";
			mod.addObject("linkThu1",linkMBWeek);
			String linkMBDay = hrefTinh+ date1.replace("/", "-") + ".html"; 
			mod.addObject("linkDay1",linkMBDay);
			
			String week2 = DateUtil.getWeekFromDate(date2);
			String linkMBWeek2 = href + DaiCaThang.toUrlFriendly(week2).toLowerCase() +".html";
			mod.addObject("linkThu2",linkMBWeek2);
			String linkMBDay2 = hrefTinh+ date2.replace("/", "-") + ".html"; 
			mod.addObject("linkDay2",linkMBDay2);
			
			String week3 = DateUtil.getWeekFromDate(date3);
			String linkMBWeek3 = href + DaiCaThang.toUrlFriendly(week3).toLowerCase() +".html";
			mod.addObject("linkThu3",linkMBWeek3);
			String linkMBDay3 = hrefTinh+ date3.replace("/", "-") + ".html"; 
			mod.addObject("linkDay3",linkMBDay3);
			
			mod.addObject("date1", date1);
			mod.addObject("date2", date2);
			mod.addObject("date3", date3);
			mod.addObject("week1", week1);
			mod.addObject("week2", week2);
			mod.addObject("week3", week3);
		} else if(length == 2) {
			mod.addObject("lottery1", listLottery.get(0));
			mod.addObject("listDau1", listLotteryDau.get(0));
			mod.addObject("listDuoi1", listLotteryDuoi.get(0));
			
			mod.addObject("lottery2", listLottery.get(1));
			mod.addObject("listDau2", listLotteryDau.get(1));
			mod.addObject("listDuoi2", listLotteryDuoi.get(1));
			
			String date1 = listLottery.get(0).getOpenDate();
			String date2 = listLottery.get(1).getOpenDate();
			String week1 = DateUtil.getWeekFromDate(date1);
			String linkMBWeek = href + DaiCaThang.toUrlFriendly(week1).toLowerCase() +".html";
			mod.addObject("linkThu1",linkMBWeek);
			String linkMBDay = hrefTinh+ date1.replace("/", "-") + ".html"; 
			mod.addObject("linkDay1",linkMBDay);
			
			String week2 = DateUtil.getWeekFromDate(date2);
			String linkMBWeek2 = href + DaiCaThang.toUrlFriendly(week2).toLowerCase() +".html";
			mod.addObject("linkThu2",linkMBWeek2);
			String linkMBDay2 = hrefTinh+ date2.replace("/", "-") + ".html"; 
			mod.addObject("linkDay2",linkMBDay2);
			
			mod.addObject("date1", date1);
			mod.addObject("date2", date2);
			mod.addObject("week1", week1);
			mod.addObject("week2", week2);
		} else if(length == 1) {
			mod.addObject("lottery1", listLottery.get(0));
			mod.addObject("listDau1", listLotteryDau.get(0));
			mod.addObject("listDuoi1", listLotteryDuoi.get(0));
			
			String date1 = listLottery.get(0).getOpenDate();
			String week1 = DateUtil.getWeekFromDate(date1);
			String linkMBWeek = href + DaiCaThang.toUrlFriendly(week1).toLowerCase() +".html";
			mod.addObject("linkThu1",linkMBWeek);
			String linkMBDay = hrefTinh+ date1.replace("/", "-") + ".html"; 
			mod.addObject("linkDay1",linkMBDay);
			
			
			mod.addObject("date1", date1);
			mod.addObject("week1", week1);
		}

		mod.addObject("listLoto", listLoto);
		mod.addObject("code", code);
		mod.addObject("max30LotoList", max30LotoList);
		mod.addObject("min30LotoList", min30LotoList);
		String categoryNews = lotteryCompany.getCompanyLink().replaceAll("-", " ").toUpperCase();
		
		mod.addObject("region", lotteryCompany.getRegion());
		mod.addObject("isOpenToday", isOpenToday(lotteryCompany.getRegion(), code));
		
		if(categoryNews.equalsIgnoreCase("TPHCM")) {
			categoryNews = "HO CHI MINH";
		}
		
		List<News> listLotteryNews = lotteryRequest.parserLotteryNewsProvince(Contant.SITE_ID, "1", Contant.MAX_NEWS, categoryNews);
		mod.addObject("listLotteryNews", listLotteryNews);
		mod.addObject("categoryNews", categoryNews);
		mod.addObject("activeMenu", activeMenu);
		mod.addObject("linXS", "xs"+lotteryCompany.getRegion().toLowerCase());
		mod.setViewName("/xs_tinh_ngay");
		return mod;
	}

	private int isOpenToday(String region, String code) {
		if (region.equalsIgnoreCase("MT")) {
			for (LotteryCompany lotteryCompany : listLotteryCompanyMT) {
				if (lotteryCompany.getCode().trim().equalsIgnoreCase(code.trim())) {
					return 1;
				}
			}
		} else {
			for (LotteryCompany lotteryCompany : listLotteryCompanyMN) {
				if (lotteryCompany.getCode().trim().equalsIgnoreCase(code.trim())) {
					return 1;
				}
			}
		}

		return 0;
	}

	private String checkTimeOpen(List<LotteryCompany> list, List<LotteryCompany> listMN, String code) {
		String result = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -1);
		Today today = new Today();
		if (list == null || list.isEmpty() || code == null || "".equals(code)) {
			return result;
		}
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getCode().equalsIgnoreCase(code)) {
				if (list.get(i).getRegion().equals("MT")) {
					if (today.getHour() > 17 || (today.getHour() == 17 && today.getMinute() > 40)) {
						return ddmmyyyy;
					}
				}
			}
		}

		if (listMN == null || listMN.isEmpty() || code == null || "".equals(code)) {
			return result;
		}
		for (int i = 0; i < listMN.size(); i++) {
			if (listMN.get(i).getCode().equalsIgnoreCase(code)) {
				if (listMN.get(i).getRegion().equals("MN")) {
					if (today.getHour() > 16 || (today.getHour() == 16 && today.getMinute() > 40)) {
						return ddmmyyyy;
					}
				}
			}
		}

		if ("XSTD".equalsIgnoreCase(code)) {
			return ddmmyyyy;
		}

		return result;
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
