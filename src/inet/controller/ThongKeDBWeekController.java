/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.thongke.ThongKeLoto;

import inet.bean.Loto;
import inet.util.DateProc;
import inet.util.LotoUtils;
import inet.util.Today;

/**
 *
 * @author hanhlm
 */
public class ThongKeDBWeekController extends BaseController {

	private static String sDDMMYYYY = null;

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
		// lay ket qua xoso mien bac
		String dayMB = "";
		Today today = new Today();
		if (today.getHour() < 18 || (today.getHour() == 18 && today.getMinute() <= 44)) {
			dayMB = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -1);
		} else {
			dayMB = ddmmyyyy;
		}
		String week = request.getParameter("week");
		int weekP = Integer.parseInt(week);
		// ---------------------------it ve, hay
		// ve-------------------------------------------
		String tungay = DateProc.TimestampYYYYMMDD(
				DateProc.String2Timestamp(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(dayMB), -(weekP*7))));
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

		mod.addObject("max30LotoList", max30LotoList);
		mod.addObject("min30LotoList", min30LotoList);
		mod.addObject("activeMenu", '7');
		mod.setViewName("/include/thongke_db_haisocuoi");

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
