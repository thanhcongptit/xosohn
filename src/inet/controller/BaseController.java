/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import inet.bean.AdvertisingSite;
import inet.bean.Lottery;
import inet.bean.LotteryCompany;
import inet.bean.Member;
import inet.bean.News;
import inet.bean.Thongke;
import inet.constant.Constants;
import inet.model.AdvertisingSiteDao;
import inet.request.LotteryRequest;
import inet.util.Contant;
import inet.util.DatePro;
import inet.util.DateProc;
import inet.util.StringPro;

/**
 *
 * @author hanhlm
 */
public class BaseController extends AbstractController {
	// private final String strUrl="http://localhost:8084/xoso";

	private final String strUrl = "http://108.61.183.88/xoso";
	protected String canonical = "";
	private static List<LotteryCompany> listCompany = null;
	private static HashMap<String, List<LotteryCompany>> hListCompanyDayOfWeek = null; // ko can
	protected static List<List<LotteryCompany>> listCompanyOpenToday = null;// ko can
	protected static List<LotteryCompany> listLotteryCompanyMT = new ArrayList<LotteryCompany>();
	protected static List<LotteryCompany> listLotteryCompanyMN = new ArrayList<LotteryCompany>();
	private static int totalRowCompanyOpen = 3;
	private static List<List<LotteryCompany>> listCompanyOpenPrv = null;// ko can
	private static List<News> listLotteryNews = null;
	protected String ddmmyyyy = "";
	private String dayOfWeek = "";
	private String prvDayOfWeek = "";// ko can
	private String prvDay = "";// ko can
	private long loadTimeNews = 0;// ko can
	private Member memberLogin = null;
	private List<AdvertisingSite> advertisingSites = new ArrayList<>();

	public BaseController() {
		if (listCompany == null || listCompany.isEmpty()) {
			LotteryRequest lotteryRequest = new LotteryRequest();
			listCompany = lotteryRequest.parserLotteryCompany();
			if (listCompany != null && !listCompany.isEmpty()) {
				getCompanyDayOfWeek();
			}
		}

	}

	protected void loadBase() {
		// lay ngay thang hien tai
		String today = DateProc.getDateString(DateProc.createTimestamp());
		ddmmyyyy = DateProc.getDateString(DateProc.createTimestamp());

		if ("".equals(prvDay)) {
			prvDay = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(today), -1);
		}
		long timeCurrent = System.currentTimeMillis();

		if (listCompany == null || listCompany.isEmpty()) {
			LotteryRequest lotteryRequest = new LotteryRequest();
			listCompany = lotteryRequest.parserLotteryCompany();
			if (listCompany != null && !listCompany.isEmpty()) {
				getCompanyDayOfWeek();
			}
		}

		// lay cac tinh mo thuong ngay hom nay
		if (!today.equals(prvDay)) {

			prvDay = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(today), -1);
			dayOfWeek = DatePro.getDateOfWeekDDMMYYYY(today);
			prvDayOfWeek = DatePro.getDateOfWeekDDMMYYYY(prvDay);

			// System.out.println("Hom nay "+dayOfWeek+"==>>Hom qua "+prvDayOfWeek);
			if (listCompanyOpenPrv == null) {
				listCompanyOpenPrv = new ArrayList<List<LotteryCompany>>();
			}
			listCompanyOpenPrv.clear();
			listCompanyOpenPrv = getCompanyOpenDay(prvDayOfWeek);

			if (listCompanyOpenToday == null) {
				listCompanyOpenToday = new ArrayList<List<LotteryCompany>>();
			}
			listCompanyOpenToday.clear();
			listCompanyOpenToday = getCompanyOpenDay(dayOfWeek);

			// lay lich mo thuong
			if (listCompanyOpenToday != null && !listCompanyOpenToday.isEmpty()) {
				List<LotteryCompany> list = null;
				listLotteryCompanyMT.clear();
				listLotteryCompanyMN.clear();
				for (int i = 0; i < listCompanyOpenToday.size(); i++) {
					list = listCompanyOpenToday.get(i);
					for (int j = 0; j < list.size(); j++) {
						if ("MN".equals(list.get(j).getRegion())) {
							listLotteryCompanyMN.add(list.get(j));
						} else if ("MT".equals(list.get(j).getRegion())) {
							listLotteryCompanyMT.add(list.get(j));
						}
					}
				}
			}
		}

		// lay phan tin tuc
		loadTimeNews = 0;
		if (listLotteryNews == null || !ddmmyyyy.equals(prvDay)) {
			LotteryRequest lotteryRequest = new LotteryRequest();
			listLotteryNews = lotteryRequest.parserLotteryNews(Contant.SITE_ID, "1", Contant.MAX_NEWS);
			loadTimeNews = System.currentTimeMillis();

		} else if (timeCurrent - loadTimeNews > (60 * 60 * 1000)) {
			LotteryRequest lotteryRequest = new LotteryRequest();
			listLotteryNews = lotteryRequest.parserLotteryNews(Contant.SITE_ID, "1", Contant.MAX_NEWS);
			loadTimeNews = timeCurrent;
		}

		prvDay = today;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ModelAndView mod = new ModelAndView();
		canonical = (String) request.getAttribute("javax.servlet.forward.request_uri");
		if (canonical != null) {
			if (checkToUpcase(canonical)) {
				try {
					canonical = canonical.toLowerCase();
					response.sendRedirect(canonical);
				} catch (Exception e) {
				}
			}
		}
		// loadbase
		loadBase();

//		// kiem tra dang nhap
//		if (request.getSession() != null) {
//			memberLogin = request.getSession().getAttribute(Constants.LOGIN_SESSION) != null
//					? (Member) request.getSession().getAttribute(Constants.LOGIN_SESSION)
//					: null;
//		}

		if (listLotteryCompanyMN != null && listLotteryCompanyMT != null) {
			if (listLotteryCompanyMN.size() > listLotteryCompanyMT.size()) {
				totalRowCompanyOpen = listLotteryCompanyMN.size();
			} else {
				totalRowCompanyOpen = listLotteryCompanyMT.size();
			}
		}
		
		advertisingSites = new AdvertisingSiteDao().getAvertisingSite();
		mod.addObject("advertisingSites", advertisingSites);
		mod.addObject("listCompany", listCompany);
		mod.addObject("listCompanyOpenToday", listCompanyOpenToday);
		mod.addObject("listLotteryCompanyMT", listLotteryCompanyMT);
		mod.addObject("listLotteryCompanyMN", listLotteryCompanyMN);
		mod.addObject("totalRowCompanyOpen", totalRowCompanyOpen);
		mod.addObject("listCompanyOpenPrv", listCompanyOpenPrv);
		mod.addObject("listLotteryNews", listLotteryNews);
		mod.addObject("today", ddmmyyyy);
		mod.addObject("ddmmyyyy", ddmmyyyy);
		mod.addObject("canonical", canonical);
		mod.addObject("dayOfWeek", dayOfWeek);
		mod.addObject("strUrl", strUrl);
		mod.addObject("memberLogin", memberLogin);
		return mod;
	}

	protected List<List<LotteryCompany>> getCompanyOpenDay(String day) {
		List<List<LotteryCompany>> listComp = null;
		if (hListCompanyDayOfWeek != null) {
			listComp = new ArrayList<List<LotteryCompany>>();
			String key = day + "_MB";
			listComp.add(hListCompanyDayOfWeek.get(key));

			key = day + "_MT";
			listComp.add(hListCompanyDayOfWeek.get(key));

			key = day + "_MN";
			listComp.add(hListCompanyDayOfWeek.get(key));
		}

		return listComp;
	}

	private void getCompanyDayOfWeek() {
		String key = "";
		if (hListCompanyDayOfWeek == null) {
			hListCompanyDayOfWeek = new HashMap<String, List<LotteryCompany>>();
		}
		LotteryCompany lotteryCompany = null;
		List<LotteryCompany> listComp = null;
		for (int i = 0; i < listCompany.size(); i++) {
			lotteryCompany = listCompany.get(i);
			if (lotteryCompany.getOpendate().contains("2")) {
				key = "2_" + lotteryCompany.getRegion();
				if (hListCompanyDayOfWeek.containsKey(key)) {
					listComp = hListCompanyDayOfWeek.get(key);
					listComp.add(lotteryCompany);
				} else {
					listComp = new ArrayList<LotteryCompany>();
					listComp.add(lotteryCompany);
				}
				hListCompanyDayOfWeek.put(key, listComp);
			}
			if (lotteryCompany.getOpendate().contains("3")) {
				key = "3_" + lotteryCompany.getRegion();
				if (hListCompanyDayOfWeek.containsKey(key)) {
					listComp = hListCompanyDayOfWeek.get(key);
					listComp.add(lotteryCompany);
				} else {
					listComp = new ArrayList<LotteryCompany>();
					listComp.add(lotteryCompany);
				}
				hListCompanyDayOfWeek.put(key, listComp);
			}
			if (lotteryCompany.getOpendate().contains("4")) {
				key = "4_" + lotteryCompany.getRegion();
				if (hListCompanyDayOfWeek.containsKey(key)) {
					listComp = hListCompanyDayOfWeek.get(key);
					listComp.add(lotteryCompany);
				} else {
					listComp = new ArrayList<LotteryCompany>();
					listComp.add(lotteryCompany);
				}
				hListCompanyDayOfWeek.put(key, listComp);
			}
			if (lotteryCompany.getOpendate().contains("5")) {
				key = "5_" + lotteryCompany.getRegion();
				if (hListCompanyDayOfWeek.containsKey(key)) {
					listComp = hListCompanyDayOfWeek.get(key);
					listComp.add(lotteryCompany);
				} else {
					listComp = new ArrayList<LotteryCompany>();
					listComp.add(lotteryCompany);
				}
				hListCompanyDayOfWeek.put(key, listComp);
			}
			if (lotteryCompany.getOpendate().contains("6")) {
				key = "6_" + lotteryCompany.getRegion();
				if (hListCompanyDayOfWeek.containsKey(key)) {
					listComp = hListCompanyDayOfWeek.get(key);
					listComp.add(lotteryCompany);
				} else {
					listComp = new ArrayList<LotteryCompany>();
					listComp.add(lotteryCompany);
				}
				hListCompanyDayOfWeek.put(key, listComp);
			}
			if (lotteryCompany.getOpendate().contains("7")) {
				key = "7_" + lotteryCompany.getRegion();
				if (hListCompanyDayOfWeek.containsKey(key)) {
					listComp = hListCompanyDayOfWeek.get(key);
					listComp.add(lotteryCompany);
				} else {
					listComp = new ArrayList<LotteryCompany>();
					listComp.add(lotteryCompany);
				}
				hListCompanyDayOfWeek.put(key, listComp);
			}
			if (lotteryCompany.getOpendate().contains("CN")) {
				key = "CN_" + lotteryCompany.getRegion();
				if (hListCompanyDayOfWeek.containsKey(key)) {
					listComp = hListCompanyDayOfWeek.get(key);
					listComp.add(lotteryCompany);
				} else {
					listComp = new ArrayList<LotteryCompany>();
					listComp.add(lotteryCompany);
				}
				hListCompanyDayOfWeek.put(key, listComp);
			}

		}
	}

	protected Lottery findLotterys(List<Lottery> list, String ddmmyyyy) {
		Lottery lotterys = null;
		LotteryCompany lotteryCompany = null;
		if (list != null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				if (lotterys == null) {
					lotterys = new Lottery();
					lotterys.setOpenDate(ddmmyyyy);
					lotterys.setProvince(list.get(i).getProvince());
					lotterys.setSpecial(list.get(i).getSpecial());
					lotterys.setFirst(list.get(i).getFirst());
					lotterys.setSecond(list.get(i).getSecond());
					lotterys.setThird(list.get(i).getThird());
					lotterys.setFourth(list.get(i).getFourth());
					lotterys.setFifth(list.get(i).getFifth());
					lotterys.setSixth(list.get(i).getSixth());
					lotterys.setSeventh(list.get(i).getSeventh());
					lotterys.setEighth(list.get(i).getEighth());
					lotteryCompany = findLotteryCompanyOfCode(list.get(i).getCode());
					
					if("XSHCM".equalsIgnoreCase(list.get(i).getCode())) {
						lotterys.setLink(
								lotteryCompany.getCodeLowerCase() + "-ket-qua-xo-so-tp" + lotteryCompany.getCompanyLink() + ".html");
					} else {
						lotterys.setLink(
								lotteryCompany.getCodeLowerCase() + "-ket-qua-xo-so-" + lotteryCompany.getCompanyLink() + ".html");
					}

				} else {
					lotterys.setProvince(lotterys.getProvince() + "+" + list.get(i).getProvince());
					lotterys.setSpecial(lotterys.getSpecial() + "+" + list.get(i).getSpecial());
					lotterys.setFirst(lotterys.getFirst() + "+" + list.get(i).getFirst());
					lotterys.setSecond(lotterys.getSecond() + "+" + list.get(i).getSecond());
					lotterys.setThird(lotterys.getThird() + "+" + list.get(i).getThird());
					lotterys.setFourth(lotterys.getFourth() + "+" + list.get(i).getFourth());
					lotterys.setFifth(lotterys.getFifth() + "+" + list.get(i).getFifth());
					lotterys.setSixth(lotterys.getSixth() + "+" + list.get(i).getSixth());
					lotterys.setSeventh(lotterys.getSeventh() + "+" + list.get(i).getSeventh());
					lotterys.setEighth(lotterys.getEighth() + "+" + list.get(i).getEighth());
					lotteryCompany = findLotteryCompanyOfCode(list.get(i).getCode());
					
					if("XSHCM".equalsIgnoreCase(list.get(i).getCode())) {
						lotterys.setLink(
								lotteryCompany.getCodeLowerCase() + "-ket-qua-xo-so-tp" + lotteryCompany.getCompanyLink() + ".html");
					} else {
						lotterys.setLink(
								lotteryCompany.getCodeLowerCase() + "-ket-qua-xo-so-" + lotteryCompany.getCompanyLink() + ".html");
					}
				}
			}

		}

		return lotterys;
	}

//	protected List<String> findDuoi(List<Lottery> list) {
//		List<String> rs = new ArrayList<>();
//
//		if (list != null && !list.isEmpty()) {
//			for (int i = 0; i < list.size(); i++) {
//				List<List<Thongke>> listDuoi = StringPro.filterDauDuoi(list.get(i), true);
//				List<String> temp = convertListDuoi(listDuoi);
//				if (rs.isEmpty()) {
//					rs.addAll(temp);
//				} else {
//					for (int j = 0; j < 10; j++) {
//						rs.set(j, rs.get(j) + "+" + temp.get(j));
//					}
//				}
//			}
//		}
//		return rs;
//	}
	
	protected List<String> findDuoi(List<Lottery> list) {
		List<String> rs = new ArrayList<>();

		if (list != null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				List<String> listDuoi = StringPro.filterDauOrDuoi(list.get(i), true);
//				List<String> temp = convertListDuoi(listDuoi);
//				if (rs.isEmpty()) {
//					rs.addAll(temp);
//				} else {
//					for (int j = 0; j < 10; j++) {
//						rs.set(j, rs.get(j) + "+" + temp.get(j));
//					}
//				}
				
				if (rs.isEmpty()) {
					rs.addAll(listDuoi);
				} else {
					for (int j = 0; j < 10; j++) {
						rs.set(j, rs.get(j) + "+" + listDuoi.get(j));
					}
				}
			}
		}
		return rs;
	}

	private List<String> convertListDuoi(List<List<Thongke>> listDuoi) {
		List<String> rs = new ArrayList<>();

		for (int i = 0; i < listDuoi.size(); i++) {
			StringBuilder sbd = new StringBuilder("");
			List<Thongke> thongkes = listDuoi.get(i);

			for (int j = 0; j < thongkes.size(); j++) {
				Thongke thongke = thongkes.get(j);
				if (thongke.isDacbiet()) {
					sbd.append("<span  class=\"red\">").append(thongke.getLoto()).append("</span>");
				} else {
					sbd.append(thongke.getLoto());
				}

				if (thongke.getTimes() > 1) {
					sbd.append("<span class=\"small red\">(").append(thongke.getTimes()).append(")</span>");
				}

				if (j < thongkes.size() - 1) {
					sbd.append("; ");
				}
			}

			if (sbd.toString().isEmpty()) {
				rs.add("del");
			} else {
				rs.add(sbd.toString());
			}

		}
		return rs;
	}

	protected LotteryCompany findLotteryCompanyOfCode(String code) {
		LotteryCompany lotteryCompany = null;
		if (listCompany == null || listCompany.isEmpty()) {
			return lotteryCompany;
		}
		for (int i = 0; i < listCompany.size(); i++) {
			if (code.equalsIgnoreCase(listCompany.get(i).getCode())) {
				lotteryCompany = listCompany.get(i);
			}
		}

		return lotteryCompany;
	}

	private boolean checkToUpcase(String str) {
		boolean result = false;
		String[] arr = { "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L",
				"Z", "X", "C", "V", "B", "N", "M" };
		for (int i = 0; i < arr.length; i++) {
			if (str.contains(arr[i])) {
				result = true;
				break;
			}
		}

		return result;
	}
}
