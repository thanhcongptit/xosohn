/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soicaupro.thongkebacnho;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;

import inet.bean.Lottery;
import inet.bean.SoicauBean;
import inet.util.DateUtil;
import inet.util.RequestUtil;

/**
 *
 * @author conglt
 */
public class CommonUtil {
	private static HashMap<String, SoicauBean> soicauBeans = new HashMap<>();

	public static SoicauBean getSoicauValueByKey(String key) {
		if (soicauBeans != null) {
			return soicauBeans.get(key);
		}

		return null;
	}

	public static void putSoicauValue(String key, SoicauBean results) {
		if (soicauBeans == null) {
			soicauBeans = new HashMap<>();
		}
		soicauBeans.put(key, results);
	}

	public static boolean isEmptyString(String s) {
		if (null == s || s.trim().equals("")) {
			return true;
		}

		return false;
	}

	public static String convertDatetoSitemap(String sourceDate) {
		String [] strings = sourceDate.trim().split("-");
		return strings[2] + "-" + strings[1] + "-" + strings[0];
	}
	
	public static String convertDatetoSitemap2(String sourceDate) {
		String [] strings = sourceDate.trim().split("/");
		return strings[2] + "-" + strings[1] + "-" + strings[0];
	} 
	public static void validateSSLHTTPS() {
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(X509Certificate[] certs, String authType) {
			}

		} };

		SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("SSL");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
		} catch (KeyManagementException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		// Create all-trusting host name verifier
		HostnameVerifier allHostsValid = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};
		// Install the all-trusting host verifier
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
	}

	public static boolean checkDate() {
		java.util.Date date = new java.util.Date();
		String dateNow = DateUtil.date2String(new java.util.Date(), "yyyy-MM-dd");
		java.util.Date test = DateUtil.toDate(dateNow + " 18:45", "yyyy-MM-dd HH:mm");
		if (date.before(test)) {
			return true;
		} else {
			return false;
		}
	}

	public String addDate(Date date, int day) {
		SimpleDateFormat sdf = new SimpleDateFormat(Constant.FORMAT_DATETIME);
		Calendar c = Calendar.getInstance();
		c.setTime(date); // Now use today date.
		c.add(Calendar.DATE, day);
		return sdf.format(c.getTime());
	}

	public String addDate1(java.util.Date date, int day) {
		SimpleDateFormat sdf = new SimpleDateFormat(Constant.FORMAT_DATETIME1);
		Calendar c = Calendar.getInstance();
		c.setTime(date); // Now use today date.
		c.add(Calendar.DATE, day);
		return sdf.format(c.getTime());
	}

	public java.util.Date addDate2(java.util.Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTime(date); // Now use today date.
		c.add(Calendar.DATE, day);
		return c.getTime();
	}

	public java.sql.Date addDate3(java.sql.Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTime(date); // Now use today date.
		c.add(Calendar.DATE, day);
		return new Date(c.getTime().getTime());
	}

	public String getStringRS(String[] loto, String special, String first, String second, String third, String fourth,
			String fifth, String sixth, String seventh) {
		StringBuilder rs = new StringBuilder();

		rs.append(getString(special, loto));
		rs.append(getString(first, loto));
		rs.append(getString(second, loto));
		rs.append(getString(third, loto));
		rs.append(getString(fourth, loto));
		rs.append(getString(fifth, loto));
		rs.append(getString(sixth, loto));
		rs.append(getString(seventh, loto));

		String result = rs.toString();
		return result.substring(0, result.length() - 1);
	}

	private String getString(String award, String loto[]) {
		StringBuilder sb = new StringBuilder();
		String[] awards = award.split("-");

		for (String a : awards) {
			if (a.endsWith(loto[0])) {
				sb.append(loto[0]).append(",");
			}

			if (a.endsWith(loto[1])) {
				sb.append(loto[1]).append(",");
			}
		}
		return sb.toString();
	}

	public String getStringLotoRS(String loto, String special, String first, String second, String third, String fourth,
			String fifth, String sixth, String seventh) {
		StringBuilder rs = new StringBuilder();

		rs.append(getStringLotoRS(special, loto));
		rs.append(getStringLotoRS(first, loto));
		rs.append(getStringLotoRS(second, loto));
		rs.append(getStringLotoRS(third, loto));
		rs.append(getStringLotoRS(fourth, loto));
		rs.append(getStringLotoRS(fifth, loto));
		rs.append(getStringLotoRS(sixth, loto));
		rs.append(getStringLotoRS(seventh, loto));

		String result = rs.toString();
		return result.substring(0, result.length() - 1);
	}

	private String getStringLotoRS(String award, String loto) {
		StringBuilder sb = new StringBuilder();
		String[] awards = award.split("-");

		for (String a : awards) {
			if (a.endsWith(loto)) {
				sb.append(loto).append(",");
			}

		}
		return sb.toString();
	}

	public java.util.Date convertStringToDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(Constant.FORMAT_DATETIME1);
		try {
			return sdf.parse(date);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public String getStringFromList(List<String> finalList) {

		StringBuilder rs = new StringBuilder("");

		for (int k = 0; k < finalList.size(); k++) {
			if (k < finalList.size() - 1) {
				rs.append(finalList.get(k)).append(",");
			} else {
				rs.append(finalList.get(k));
			}
		}

		return rs.toString();
	}

	public String getStringFromArray(String[] finalList) {

		StringBuilder rs = new StringBuilder("");

		for (int k = 0; k < finalList.length; k++) {
			if (k < finalList.length - 1) {
				rs.append(finalList[k]).append("-");
			} else {
				rs.append(finalList[k]);
			}
		}

		return rs.toString();
	}

	public boolean checkTop10InResult(Lottery item, String value) {
		if (item.getSpecial().trim().endsWith(value)) {
			return true;
		}

		if (item.getFirst().trim().endsWith(value)) {
			return true;
		}

		String seconds[] = item.getSecond().split("-");
		String third[] = item.getThird().split("-");
		String fourth[] = item.getFourth().split("-");
		String fifth[] = item.getFifth().split("-");
		String sixth[] = item.getSixth().split("-");
		String seventh[] = item.getSeventh().split("-");

		if (checkInAwards(seconds, value)) {
			return true;
		}

		if (checkInAwards(third, value)) {
			return true;
		}

		if (checkInAwards(fourth, value)) {
			return true;
		}

		if (checkInAwards(fifth, value)) {
			return true;
		}

		if (checkInAwards(sixth, value)) {
			return true;
		}

		return checkInAwards(seventh, value);
	}

	private boolean checkInAwards(String[] awards, String value) {
		for (String s : awards) {
			if (s.trim().endsWith(value)) {
				return true;
			}
		}

		return false;
	}

	public static String getPosition(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String postion = "1";

		if (uri.endsWith("index.jsp")) {
			return postion;
		}
		
		if (uri.contains("index_mb.jsp")) {
			postion = "2";
			return postion;
		} else if (uri.contains("xsmb_thu.jsp")) {
			
			String thu = request.getParameter("thu");
			postion = "9";
			
			if(thu.endsWith("2")) {
				postion = "3";
			} else if(thu.endsWith("3")) {
				postion = "4";
			} else if(thu.endsWith("4")) {
				postion = "5";
			} else if(thu.endsWith("5")) {
				postion = "6";
			} else if(thu.endsWith("6")) {
				postion = "7";
			} else if(thu.endsWith("7")) {
				postion = "8";
			}
			
			return postion;
		} else if (uri.contains("index_mt.jsp")) {
			postion = "10";
			return postion;
		} else if (uri.contains("xsmt_thu.jsp")) {
			
			String thu = request.getParameter("thu");
			postion = "17";
			
			if(thu.endsWith("2")) {
				postion = "11";
			} else if(thu.endsWith("3")) {
				postion = "12";
			} else if(thu.endsWith("4")) {
				postion = "13";
			} else if(thu.endsWith("5")) {
				postion = "14";
			} else if(thu.endsWith("6")) {
				postion = "15";
			} else if(thu.endsWith("7")) {
				postion = "16";
			}
			
			return postion;
		} else if (uri.contains("index_mn.jsp")) {
			postion = "18";
			return postion;
		} else if (uri.contains("xsmn_thu.jsp")) {
			
			String thu = request.getParameter("thu");
			postion = "25";
			
			if(thu.endsWith("2")) {
				postion = "19";
			} else if(thu.endsWith("3")) {
				postion = "20";
			} else if(thu.endsWith("4")) {
				postion = "21";
			} else if(thu.endsWith("5")) {
				postion = "22";
			} else if(thu.endsWith("6")) {
				postion = "23";
			} else if(thu.endsWith("7")) {
				postion = "24";
			}
			
			return postion;
		} else if (uri.contains("kqxs_ngay.jsp")) {
			String d = request.getParameter("d");
			postion = "26" +"_" + d;
			return postion;
		} else if (uri.contains("xsmb_ngay.jsp")) {
			String d = request.getParameter("d");
			postion = "27"+"_" + d;
			return postion;
		} else if (uri.contains("xsmt_ngay.jsp")) {
			String d = request.getParameter("d");
			postion = "28"+"_" + d;
			return postion;
		} else if (uri.contains("xsmn_ngay.jsp")) {
			String d = request.getParameter("d");
			postion = "29"+"_" + d;
			return postion;
		} else if (uri.contains("xs_tinh.jsp")) {
			String province = request.getParameter("province");
			postion = "30" + "_" + province;
			return postion;
		} else if(uri.contains("soi_cau.jsp")) {
			String type = request.getParameter("type");
			if(type.equalsIgnoreCase("1")) {
				postion = "31";
				return postion;
			} else if(type.equalsIgnoreCase("2")) {
				postion = "32";
				return postion;
				
			} else if(type.equalsIgnoreCase("3")) {
				postion = "33";
				return postion;
				
			} else if(type.equalsIgnoreCase("4")) {
				postion = "34";
				return postion;
				
			}
		} else if(uri.contains("soi_cau_mt.jsp")) {
			String code = request.getParameter("code");
			String codes[] = code.split("-");
			
			if(code.equalsIgnoreCase("mien-trung")) {
				code = "xsbdh";
			} else if(code.equalsIgnoreCase("mien-nam")) {
				code = "xsag";
			} else {
				code = codes[codes.length-1];
			}
			
			postion = "35" + "_" + code;
			return postion;
		} else if(uri.contains("du_doan.jsp")) {
			String categoryNews = RequestUtil.getString(request, "categoryNews", "");
			if(CommonUtil.isEmptyString(categoryNews)) {
				postion = "36";
			} else {
				postion = "48_"+categoryNews;
			}
			return postion;
		} else if(uri.contains("so_mo.jsp")) {
			postion = "37";
			return postion;
		} else if(uri.contains("thongke_lo_gan.jsp")) {
			String code = RequestUtil.getString(request, "code", "xsmb");
			String codes[] = code.split("-");
			code = codes[codes.length-1];
			
			postion = "38"+"_"+code;
			return postion;
		} else if(uri.contains("thongke_lo_kep.jsp")) {
			String code = RequestUtil.getString(request, "code", "xsmb");
			String codes[] = code.split("-");
			code = codes[codes.length-1];
			
			postion = "39"+"_"+code;
			return postion;
		} else if(uri.contains("thongke_db_mb.jsp")) {
			postion = "40";
			return postion;
		} else if(uri.contains("thongke_tan_suat.jsp")) {
			String code = RequestUtil.getString(request, "code", "xsmb");
			String codes[] = code.split("-");
			code = codes[codes.length-1];
			
			postion = "41"+"_"+code;
			return postion;
		} else if(uri.contains("thongke_lo_xien.jsp")) {
			String code = RequestUtil.getString(request, "code", "xsmb");
			String codes[] = code.split("-");
			code = codes[codes.length-1];
			
			postion = "42"+"_"+code;
			return postion;
		} else if(uri.contains("thongke_00_99.jsp")) {
			String code = RequestUtil.getString(request, "code", "xsmb");
			String codes[] = code.split("-");
			code = codes[codes.length-1];
			
			postion = "43"+"_"+code;
			return postion;
		} else if(uri.contains("quay_thu.jsp")) {
			String code = RequestUtil.getString(request, "code", "xsmb");
			String temp[] = code.split("-");
			code = temp[temp.length-1];
			
			postion = "44"+"_"+code;
			return postion;
		} else if(uri.contains("detail_news.jsp")) {
			String id = request.getParameter("id");
			
			postion = "45"+"_"+id;
			return postion;
		} else if(uri.contains("detail_dream.jsp")) {
			String id = request.getParameter("id");
			
			postion = "46"+"_"+id;
			return postion;
		} else if(uri.contains("xs_tinh_ngay.jsp")) {
			String code = request.getParameter("province");
			String codes[] = code.split("-");
			code = "XS"+codes[0].toUpperCase();
			String d = request.getParameter("d");
			d = codes[1]+"-"+codes[2] +"-"+d;
			
			postion = "47_" + code + "_"+d;
			return postion;
		} else if(uri.contains("news_db.jsp")) {
			postion = "49";
			return postion;
		} else if(uri.contains("news_99.jsp")) {
			postion = "50";
			return postion;
		} else if(uri.contains("news_888.jsp")) {
			postion = "51";
			return postion;
		} else if(uri.contains("news_scmb.jsp")) {
			postion = "52";
			return postion;
		} else if(uri.contains("news_scmb_chinhxac.jsp")) {
			postion = "53";
			return postion;
		} else if(uri.contains("detail_news_db.jsp")) {
			String id = request.getParameter("id");
			
			postion = "54"+"_"+id;
			return postion;
		} else if(uri.contains("detail_news_99.jsp")) {
			String id = request.getParameter("id");
			
			postion = "55"+"_"+id;
			return postion;
		} else if(uri.contains("detail_news_888.jsp")) {
			String id = request.getParameter("id");
			
			postion = "56"+"_"+id;
			return postion;
		} else if(uri.contains("detail_news_scmb.jsp")) {
			String id = request.getParameter("id");
			
			postion = "57"+"_"+id;
			return postion;
		} else if(uri.contains("detail_news_scmb_chinhxac.jsp")) {
			String id = request.getParameter("id");
			
			postion = "58"+"_"+id;
			return postion;
		} 

		return "-1";
	}

	public static String getVitriToString(int i) {
		String s = "";
		switch (i) {
		case 0:
			s = "G0.1";
			break;
		case 1:
			s = "G0.2";
			break;
		case 2:
			s = "G0.3";
			break;
		case 3:
			s = "G0.4";
			break;
		case 4:
			s = "G0.5";
			break;
		case 5:
			s = "G1.1";
			break;
		case 6:
			s = "G1.2";
			break;
		case 7:
			s = "G1.3";
			break;
		case 8:
			s = "G1.4";
			break;
		case 9:
			s = "G1.5";
			break;
		case 10:
			s = "G2.1.1";
			break;
		case 11:
			s = "G2.1.2";
			break;
		case 12:
			s = "G2.1.3";
			break;
		case 13:
			s = "G2.1.4";
			break;
		case 14:
			s = "G2.1.5";
			break;
		case 15:
			s = "G2.2.1";
			break;
		case 16:
			s = "G2.2.2";
			break;
		case 17:
			s = "G2.2.3";
			break;
		case 18:
			s = "G2.2.4";
			break;
		case 19:
			s = "G2.2.5";
			break;
		case 20:
			s = "G3.1.1";
			break;
		case 21:
			s = "G3.1.2";
			break;
		case 22:
			s = "G3.1.3";
			break;
		case 23:
			s = "G3.1.4";
			break;
		case 24:
			s = "G3.1.5";
			break;
		case 25:
			s = "G3.2.1";
			break;
		case 26:
			s = "G3.2.2";
			break;
		case 27:
			s = "G3.2.3";
			break;
		case 28:
			s = "G3.2.4";
			break;
		case 29:
			s = "G3.2.5";
			break;
		case 30:
			s = "G3.3.1";
			break;
		case 31:
			s = "G3.3.2";
			break;
		case 32:
			s = "G3.3.3";
			break;
		case 33:
			s = "G3.3.4";
			break;
		case 34:
			s = "G3.3.5";
			break;
		case 35:
			s = "G3.4.1";
			break;
		case 36:
			s = "G3.4.2";
			break;
		case 37:
			s = "G3.4.3";
			break;
		case 38:
			s = "G3.4.4";
			break;
		case 39:
			s = "G3.4.5";
			break;
		case 40:
			s = "G3.5.1";
			break;
		case 41:
			s = "G3.5.2";
			break;
		case 42:
			s = "G3.5.3";
			break;
		case 43:
			s = "G3.5.4";
			break;
		case 44:
			s = "G3.5.5";
			break;
		case 45:
			s = "G3.6.1";
			break;
		case 46:
			s = "G3.6.2";
			break;
		case 47:
			s = "G3.6.3";
			break;
		case 48:
			s = "G3.6.4";
			break;
		case 49:
			s = "G3.6.5";
			break;
		case 50:
			s = "G4.1.1";
			break;
		case 51:
			s = "G4.1.2";
			break;
		case 52:
			s = "G4.1.3";
			break;
		case 53:
			s = "G4.1.4";
			break;
		case 54:
			s = "G4.2.1";
			break;
		case 55:
			s = "G4.2.2";
			break;
		case 56:
			s = "G4.2.3";
			break;
		case 57:
			s = "G4.2.4";
			break;
		case 58:
			s = "G4.3.1";
			break;
		case 59:
			s = "G4.3.2";
			break;
		case 60:
			s = "G4.3.3";
			break;
		case 61:
			s = "G4.3.4";
			break;
		case 62:
			s = "G4.4.1";
			break;
		case 63:
			s = "G4.4.2";
			break;
		case 64:
			s = "G4.4.3";
			break;
		case 65:
			s = "G4.4.4";
			break;
		case 66:
			s = "G5.1.1";
			break;
		case 67:
			s = "G5.1.2";
			break;
		case 68:
			s = "G5.1.3";
			break;
		case 69:
			s = "G5.1.4";
			break;
		case 70:
			s = "G5.2.1";
			break;
		case 71:
			s = "G5.2.2";
			break;
		case 72:
			s = "G5.2.3";
			break;
		case 73:
			s = "G5.2.4";
			break;
		case 74:
			s = "G5.3.1";
			break;
		case 75:
			s = "G5.3.2";
			break;
		case 76:
			s = "G5.3.3";
			break;
		case 77:
			s = "G5.3.4";
			break;
		case 78:
			s = "G5.4.1";
			break;
		case 79:
			s = "G5.4.2";
			break;
		case 80:
			s = "G5.4.3";
			break;
		case 81:
			s = "G5.4.4";
			break;
		case 82:
			s = "G5.5.1";
			break;
		case 83:
			s = "G5.5.2";
			break;
		case 84:
			s = "G5.5.3";
			break;
		case 85:
			s = "G5.5.4";
			break;
		case 86:
			s = "G5.6.1";
			break;
		case 87:
			s = "G5.6.2";
			break;
		case 88:
			s = "G5.6.3";
			break;
		case 89:
			s = "G5.6.4";
			break;
		case 90:
			s = "G6.1.1";
			break;
		case 91:
			s = "G6.1.2";
			break;
		case 92:
			s = "G6.1.3";
			break;
		case 93:
			s = "G6.2.1";
			break;
		case 94:
			s = "G6.2.2";
			break;
		case 95:
			s = "G6.2.3";
			break;
		case 96:
			s = "G6.3.1";
			break;
		case 97:
			s = "G6.3.2";
			break;
		case 98:
			s = "G6.3.3";
			break;
		case 99:
			s = "G7.1.1";
			break;
		case 100:
			s = "G7.1.2";
			break;
		case 101:
			s = "G7.2.1";
			break;
		case 102:
			s = "G7.2.2";
			break;
		case 103:
			s = "G7.3.1";
			break;
		case 104:
			s = "G7.3.2";
			break;
		case 105:
			s = "G7.4.1";
			break;
		case 106:
			s = "G7.4.2";
			break;
		default:
			break;
		}

		return s;
	}

	
	public static boolean checkRegionMNByCode(String code) {
		if(code.equalsIgnoreCase("XSHCM") ||
				code.equalsIgnoreCase("XSDN") ||
				code.equalsIgnoreCase("XSAG") ||
				code.equalsIgnoreCase("XSTG") ||
				code.equalsIgnoreCase("XSKG") ||
				code.equalsIgnoreCase("XSTN") ||
				code.equalsIgnoreCase("XSST") ||
				code.equalsIgnoreCase("XSVL") ||
				code.equalsIgnoreCase("XSBTR") ||
				code.equalsIgnoreCase("XSBTH") ||
				code.equalsIgnoreCase("XSDT") ||
				code.equalsIgnoreCase("XSLA") ||
				code.equalsIgnoreCase("XSDL") ||
				code.equalsIgnoreCase("XSBD") ||
				code.equalsIgnoreCase("XSTV") ||
				code.equalsIgnoreCase("XSCT") ||
				code.equalsIgnoreCase("XSVT") ||
				code.equalsIgnoreCase("XSBL") ||
				code.equalsIgnoreCase("XSCM") ||
				code.equalsIgnoreCase("XSBP") ||
				code.equalsIgnoreCase("XSHG") ) {
			return true;
			
		}
		
		return false;
		
	}
}
