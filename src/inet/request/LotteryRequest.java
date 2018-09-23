/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.request;

import java.math.BigDecimal;
import java.util.List;

import com.google.gson.Gson;

import inet.bean.LotoGan;
import inet.bean.LotoGanJson;
import inet.bean.Lottery;
import inet.bean.LotteryCompany;
import inet.bean.LotteryJson;
import inet.bean.LotterySpecial;
import inet.bean.LotterySpecialJson;
import inet.bean.News;
import inet.model.LotteryCompanyDAO;
import inet.model.LotteryResultDAO;
import inet.model.NewsDAO;
import inet.util.Contant;
import inet.util.HttpURLRequest;

/**
 *
 * @author iNET
 */
public class LotteryRequest {
	public List<LotteryCompany> parserLotteryCompany() {
		try {

			LotteryCompanyDAO lotteryCompanyDAO = new LotteryCompanyDAO();
			List<LotteryCompany> list = lotteryCompanyDAO.findLotteryCompany("all");
			return list;
		} catch (Exception e) {
		}
		return null;
	}

	public List<Lottery> parserLotteryResultOfRegion(String ddmmyy, String region) {
		try {

			LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
			List<Lottery> list = lotteryResultDAO.findResultByRegionInDay(region, ddmmyy);

			return list;
		} catch (Exception e) {
		}
		return null;
	}

	public List<Lottery> parserLotteryResultRegion(String sdate, String edate, String region) {
		try {
			// Gson gson=new Gson();
			// String url=Contant.URL_LOTTERY_RESULT_REGION;
			// url=url.replace("sdate", sdate);
			// url=url.replace("edate", edate);
			// url=url.replace("region", region);
			// String string=HttpURLRequest.sendGet(url);
			// if(string.contains("list")){
			// LotteryJson lotteryJson =(LotteryJson)gson.fromJson(string,
			// LotteryJson.class);
			// return lotteryJson.getList();
			// }

			LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
			List<Lottery> list = lotteryResultDAO.findResultByRegion(region, sdate, edate);
			return list;

		} catch (Exception e) {
		}
		return null;
	}

	public List<Lottery> parserLotteryResultNewest(String region) {
		try {
			Gson gson = new Gson();
			String url = Contant.URL_LOTTERY_NEWEST;
			url = url.replace("code", region);
			String string = HttpURLRequest.sendGet(url);
			if (string.contains("list")) {
				LotteryJson lotteryJson = (LotteryJson) gson.fromJson(string, LotteryJson.class);
				return lotteryJson.getList();
			}
		} catch (Exception e) {
			System.out.println("loi exep " + e.toString());
		}
		return null;
	}

	public List<Lottery> parserLotteryResultDayOfWeek(String code, String dayOfWeek, String numDay) {
		try {
			// Gson gson=new Gson();
			// String url=Contant.URL_LOTTERY_RESULT_DAY_OF_WEEK;
			// url=url.replace("code", code);
			// url=url.replace("dayofweek", dayOfWeek);
			// url=url.replace("numday", numDay);
			// String string=HttpURLRequest.sendGet(url);
			// if(string.contains("list")){
			// LotteryJson lotteryJson =(LotteryJson)gson.fromJson(string,
			// LotteryJson.class);
			// return lotteryJson.getList();
			// }

			LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
			List<Lottery> list = lotteryResultDAO.findResultDayOfWeek(code, dayOfWeek, Integer.parseInt(numDay));
			return list;

		} catch (Exception e) {
		}
		return null;
	}

	public List<Lottery> parserLotteryResult(String code, String start, String end) {
		try {
			// Gson gson=new Gson();
			// String url=Contant.URL_LOTTERY_RESULT_START_TO_END;
			// url=url.replace("code", code);
			// url=url.replace("sdate", start);
			// url=url.replace("edate", end);
			// String string=HttpURLRequest.sendGet(url);
			// if(string.contains("list")){
			// LotteryJson lotteryJson =(LotteryJson)gson.fromJson(string,
			// LotteryJson.class);
			// return lotteryJson.getList();
			// }

			LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
			List<Lottery> list = lotteryResultDAO.findResultByCodeInTime(code, start, end);
			return list;
		} catch (Exception e) {
		}
		return null;
	}
	
	public List<Lottery> parserLotteryResultLive(String code, String start, String end) {
		try {
			// Gson gson=new Gson();
			// String url=Contant.URL_LOTTERY_RESULT_START_TO_END;
			// url=url.replace("code", code);
			// url=url.replace("sdate", start);
			// url=url.replace("edate", end);
			// String string=HttpURLRequest.sendGet(url);
			// if(string.contains("list")){
			// LotteryJson lotteryJson =(LotteryJson)gson.fromJson(string,
			// LotteryJson.class);
			// return lotteryJson.getList();
			// }

			LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
			List<Lottery> list = lotteryResultDAO.findResultByCodeInTimeLive(code, start, end);
			return list;
		} catch (Exception e) {
		}
		return null;
	}

	public List<Lottery> parserLotteryResult(String code, String numrow) {
		try {
			Gson gson = new Gson();
			String url = Contant.URL_GET_RESULT_OF_CODE_NUM_ROW;
			url = url.replace("code", code);
			url = url.replace("numrow", numrow);
			String string = HttpURLRequest.sendGet(url);
			if (string.contains("list")) {
				LotteryJson lotteryJson = (LotteryJson) gson.fromJson(string, LotteryJson.class);
				return lotteryJson.getList();
			}
		} catch (Exception e) {
		}
		return null;
	}

	public List<LotterySpecial> parserLotteryResultSpecial(String code, String start, String end) {
		try {
			Gson gson = new Gson();
			String url = Contant.URL_LOTTERY_RESULT_SPECIAL_START_TO_END;
			url = url.replace("code", code);
			url = url.replace("sdate", start);
			url = url.replace("edate", end);
			String string = HttpURLRequest.sendGet(url);
			if (string.contains("list")) {
				LotterySpecialJson lotteryJson = (LotterySpecialJson) gson.fromJson(string, LotterySpecialJson.class);
				List<LotterySpecial> list = lotteryJson.getList();
				if (list != null && !list.isEmpty()) {
					String str = "";
					for (int i = 0; i < list.size(); i++) {
						str = list.get(i).getSpecial();
						if (!"&nbsp;".equals(str)) {
							str = str.substring(0, 3) + "<b>" + str.substring(3, 5) + "</b>";
							list.get(i).setSpecial(str);
						}
					}
				}
				return list;
			}
		} catch (Exception e) {
		}
		return null;
	}

	public List<LotoGan> parserLotoGan(String code, String numWeek, String max) {
		try {
			Gson gson = new Gson();
			String url = Contant.URL_LOTO_GAN;
			url = url.replace("code", code);
			url = url.replace("numweek", numWeek);
			url = url.replace("max", max);
			String string = HttpURLRequest.sendGet(url);
			if (string.contains("list")) {
				LotoGanJson lotoGanJson = (LotoGanJson) gson.fromJson(string, LotoGanJson.class);
				return lotoGanJson.getList();
			}
		} catch (Exception e) {
		}
		return null;
	}

	public List<LotoGan> parserStatisticalSpecial(String code, String numWeek) {
		try {
			Gson gson = new Gson();
			String url = Contant.URL_STATISTICAL_SPECIAL;
			url = url.replace("code", code);
			url = url.replace("numweek", numWeek);
			String string = HttpURLRequest.sendGet(url);
			if (string.contains("list")) {
				LotoGanJson lotoGanJson = (LotoGanJson) gson.fromJson(string, LotoGanJson.class);
				return lotoGanJson.getList();
			}
		} catch (Exception e) {
		}
		return null;
	}

	

	public List<News> parserLotteryNews(String sid, String start, String end) {
		try {

			NewsDAO newsDAO = new NewsDAO();
			List<News> list = newsDAO.findByPage(Integer.parseInt(start), Integer.parseInt(end));
			return list;

		} catch (Exception e) {
			System.out.println("parserLotteryNews==>>" + e.toString());
		}
		return null;
	}
	
	public List<News> parserLotteryNewsProvince(String sid, String start, String end, String province) {
		try {

			NewsDAO newsDAO = new NewsDAO();
			List<News> list = newsDAO.findByPageProvince(Integer.parseInt(start), Integer.parseInt(end), province);
			return list;

		} catch (Exception e) {
			System.out.println("parserLotteryNews==>>" + e.toString());
		}
		return null;
	}
	
	public List<News> parserLotteryNewRegion(String sid, String start, String end, String province) {
		try {

			NewsDAO newsDAO = new NewsDAO();
			List<News> list = newsDAO.findByPageRegion(Integer.parseInt(start), Integer.parseInt(end), province);
			return list;

		} catch (Exception e) {
			System.out.println("parserLotteryNews==>>" + e.toString());
		}
		return null;
	}

	public News parserLotteryNewsDetail(String sid, String nid) {
		try {
			// Gson gson=new Gson();
			// String url=Contant.URL_LOTTERY_NEWS_DETAIL;
			// url=url.replace("sid",sid);
			// url=url.replace("nid",nid);
			// String string=HttpURLRequest.sendGet(url);
			// if(string.contains("list")){
			// LotteryNewsDetailJson lotteryNewsJson
			// =(LotteryNewsDetailJson)gson.fromJson(string,
			// LotteryNewsDetailJson.class);
			// return lotteryNewsJson.getList();
			// }

			NewsDAO newsDAO = new NewsDAO();
			News news = newsDAO.getRow(new BigDecimal(nid));

			return news;
		} catch (Exception e) {
			System.out.println("parserLotteryNewsDetail==>>" + e.toString());
		}
		return null;
	}



	public List<Lottery> parserLotteryCode(String code, String date) {
		try {
			Gson gson = new Gson();
			String url = Contant.URL_GET_RESULT_OF_CODE;
			url = url.replace("code", code);
			url = url.replace("date", date);
			String string = HttpURLRequest.sendGet(url);
			if (string.contains("list")) {
				LotteryJson lotteryJson = (LotteryJson) gson.fromJson(string, LotteryJson.class);
				return lotteryJson.getList();
			}
		} catch (Exception e) {
			System.out.println("parserLotteryCode==>>" + e.toString());
		}
		return null;
	}

	public static void main(String[] arg) {
//		LotteryRequest lotteryCompanyRequest = new LotteryRequest();
//		List<Lottery> list = lotteryCompanyRequest.parserLotteryResultNewest("MB");
//		if (list != null && !list.isEmpty()) {
//			System.out.println("co ket qua");
//		} else {
//			System.out.println("ko co ket qua");
//		}
//		// if(list==null){
//		// System.out.println("null");
//		// }else{
//		// for(int i=0;i<list.size();i++){
//		// System.out.println(list.get(i).getCompany()+"-"+list.get(i).getCode());
//		// }
//		// }
//
//		String string = "12345";
//		System.out.println("---" + string.substring(0, 3));
//		System.out.println("---" + string.substring(3, 5));
		LotteryRequest lotteryCompanyRequest = new LotteryRequest();
		List<Lottery> lotteries = lotteryCompanyRequest.parserLotteryResultDayOfWeek("XSTD", "thu2", "1");
		System.out.println(new Gson().toJson(lotteries));
		
	}
}
