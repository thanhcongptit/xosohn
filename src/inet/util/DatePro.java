/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author hanhlm
 */
public class DatePro {
	public static String convertDayOfWeek(String input) {
		if ("hai".equalsIgnoreCase(input)) {
			return "2";
		}
		if ("ba".equalsIgnoreCase(input)) {
			return "3";
		}
		if ("tu".equalsIgnoreCase(input)) {
			return "4";
		}
		if ("nam".equalsIgnoreCase(input)) {
			return "5";
		}
		if ("sau".equalsIgnoreCase(input)) {
			return "6";
		}
		if ("bay".equalsIgnoreCase(input)) {
			return "7";
		}
		if ("cn".equalsIgnoreCase(input)) {
			return "cn";
		}

		return null;
	}

	public static String getDateOfWeek(int d, int m, int y) {
		Today today = new Today(d, m, y);
		String strThu = today.getDayOfWeek().toLowerCase().replace("thu", "");
		strThu = strThu.replace("chu nhat", "cn");
		return strThu.trim();
	}

	public static String getDateOfWeek(String date) {
		if (date == null) {
			return null;
		}
		String[] arrDate = date.split("/");
		if (arrDate == null || arrDate.length < 3) {
			return null;
		}
		int day = Integer.valueOf(arrDate[0]);
		int month = Integer.valueOf(arrDate[1]);
		int year = Integer.valueOf(arrDate[2]);
		Today today = new Today(day, month, year);
		return today.getDayOfWeek();
	}

	// startdate dd/mm/yyyy
	// enddate dd/mm/yyyy

	// lay ngay hom truoc
	public static String getDatePrevToday(int prev) {
		Today today = new Today();
		String ddmmyyyy = "";
		if (today.getDay() > 10) {
			ddmmyyyy = today.getDay() + "/";
		} else {
			ddmmyyyy = "0" + today.getDay() + "/";
		}

		if (today.getMonth() > 10) {
			ddmmyyyy = ddmmyyyy + today.getMonth() + "/";
		} else {
			ddmmyyyy = ddmmyyyy + "0" + today.getMonth() + "/";
		}
		ddmmyyyy = ddmmyyyy + today.getYear();

		ddmmyyyy = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -prev);

		return ddmmyyyy;
	}

	public static List<String> getListDateDDMMYYYY(String dayOfWeek, int numDay) {
		List<String> list = new ArrayList<String>();
		Today today = new Today();
		String ddmmyyyy = "";
		if (today.getDay() > 10) {
			ddmmyyyy = today.getDay() + "/";
		} else {
			ddmmyyyy = "0" + today.getDay() + "/";
		}

		if (today.getMonth() > 10) {
			ddmmyyyy = ddmmyyyy + today.getMonth() + "/";
		} else {
			ddmmyyyy = ddmmyyyy + "0" + today.getMonth() + "/";
		}
		ddmmyyyy = ddmmyyyy + today.getYear();

		ddmmyyyy = getDateDDMMYYYY(ddmmyyyy, dayOfWeek);

		for (int i = 0; i < numDay; i++) {
			list.add(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -(7 * i)));
		}

		return list;
	}

	public static String getDateOfWeek2(int d, int m, int y) {
		Today today = new Today(d, m, y);
		String strThu = today.getDayOfWeek().toLowerCase().replace("thu", "");
		return strThu.trim().toUpperCase();
	}

	public static String getDateOfWeek1(int d, int m, int y) {
		Today today = new Today(d, m, y);
		String strThu = today.getDayOfWeek().toLowerCase().replace("thu", "");
		strThu = strThu.replace("chu nhat", "cn");
		return strThu.trim().toUpperCase();
	}

	public static String getDateOfWeekDDMMYYYY(String date) {
		String[] arrDate = date.split("/");
		int day = Integer.valueOf(arrDate[0]).intValue();
		int month = Integer.valueOf(arrDate[1]).intValue();
		int year = Integer.valueOf(arrDate[2]).intValue();

		return getDateOfWeek1(day, month, year);
	}

	public static String getDateOfWeekDDMMYYYY2(String date) {
		String[] arrDate = date.split("/");
		int day = Integer.valueOf(arrDate[0]).intValue();
		int month = Integer.valueOf(arrDate[1]).intValue();
		int year = Integer.valueOf(arrDate[2]).intValue();

		return getDateOfWeek2(day, month, year);
	}

	public static long getNumberDay(String startdate, String enddate) {
		long result = 0L;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		Date d1 = null;
		Date d2 = null;
		try {
			d1 = format.parse(startdate);
			d2 = format.parse(enddate);

			long diff = d2.getTime() - d1.getTime();

			result = diff / 86400000L;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static String getDateDDMMYYYY(String ddmmyyyy, String dayofweek) {
		String dd = ddmmyyyy;
		String dow = getDateOfWeekDDMMYYYY(dd);
		while (!dayofweek.toUpperCase().contains(dow.toUpperCase())) {
			dd = DateProc.getDateString(DateProc.getPreviousDate(DateProc.String2Timestamp(dd)));

			dow = getDateOfWeekDDMMYYYY(dd);
		}

		return dd;
	}
	
	public static String getDateYYYYMMDDEvent(String ddmmyyyy, String dayofweek) {
		String dd = ddmmyyyy;
		String dow = getDateOfWeekDDMMYYYY(dd);
		while (!dayofweek.toUpperCase().contains(dow.toUpperCase())) {
			dd = DateProc.getDateString(DateProc.getPreviousDate(DateProc.String2Timestamp(dd)));

			dow = getDateOfWeekDDMMYYYY(dd);
		}

		Date rsDate = DateUtil.toDate(dd, "dd/MM/yyyy");
		
		return DateUtil.date2String(rsDate, "yyyy-MM-dd");
	}

	public static String getDateDDMMYYYY2(String ddmmyyyy, String dayofweek) {
		String dd = ddmmyyyy;
		String dow = getDateOfWeekDDMMYYYY2(dd);
		while (!dayofweek.toUpperCase().contains(dow.toUpperCase())) {
			dd = DateProc.getDateString(DateProc.getPreviousDate(DateProc.String2Timestamp(dd)));

			dow = getDateOfWeekDDMMYYYY2(dd);
		}

		return dd;
	}

	public static List<String> getListDateDDMMYYYY(String dayOfWeek) {
		List list = new ArrayList();
		Today today = new Today();
		String ddmmyyyy = today.getDay() + "/" + today.getMonth() + "/" + today.getYear();
		ddmmyyyy = getDateDDMMYYYY(ddmmyyyy, dayOfWeek);
		list.add(ddmmyyyy);
		list.add(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -7));
		list.add(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -14));
		list.add(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -21));
		list.add(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -28));
		list.add(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -35));
		list.add(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -42));
		list.add(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -49));
		return list;
	}

	public static void main(String[] agr) {
		String dow = getDateDDMMYYYY("08/07/2018", "cn");
		System.out.println("s : " + dow);
	}
}