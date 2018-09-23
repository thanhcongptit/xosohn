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
import org.springframework.web.servlet.mvc.AbstractController;

import com.soicaupro.thongkebacnho.CommonUtil;

import inet.bean.Lottery;
import inet.bean.LotteryCompany;
import inet.model.LotteryResultDAO;
import inet.request.LotteryRequest;
import inet.util.DaiCaThang;
import inet.util.DatePro;
import inet.util.DateProc;
import inet.util.DateUtil;
import inet.util.StringPro;

/**
 *
 * @author conglt
 */
public class BuildXS_MTLiveController extends AbstractController {

	private Lottery listLotteryMT = null;
	private List<String> listDuoiMT = null;
	private int numSizeMT = 0;
	private LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
	private String sDDMMYYYY = "";

	private static List<LotteryCompany> listCompany = null;

	public BuildXS_MTLiveController() {
		if (listCompany == null || listCompany.isEmpty()) {
			LotteryRequest lotteryRequest = new LotteryRequest();
			listCompany = lotteryRequest.parserLotteryCompany();
		}
	}

	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mod = new ModelAndView();
		sDDMMYYYY = DateProc.getDateString(DateProc.createTimestamp());
		String region = request.getParameter("r");

		List<Lottery> listLottery = lotteryResultDAO.findLotteryNewestRegion(region, sDDMMYYYY);
		listLottery = checkLottery(listLottery);

		if (listLottery != null && !listLottery.isEmpty()) {
			synchronized (this) {
				List<Lottery> listMT = getListLoterry(listLottery);

				listLotteryMT = findLotterys(listMT, sDDMMYYYY);
				listDuoiMT = findDuoi(listMT);
				numSizeMT = listMT.size();
			}
		} else {
			List<LotteryCompany> listComp = getCompanyDayOfWeek("MT");
			if (listComp != null && !listComp.isEmpty()) {
				for (int i = 0; i < listComp.size(); i++) {
					if (listLottery == null) {
						listLottery = new ArrayList<Lottery>();
					}
					listLottery.add(addLotteryNull(listComp.get(i).getCompany(), listComp.get(i).getCode()));
				}
				listLotteryMT = findLotterys(listLottery, sDDMMYYYY);
				listDuoiMT = findDuoi(listLottery);
				numSizeMT = listLottery.size();
			}

		}

		mod.addObject("lotterysMT", listLotteryMT);
		mod.addObject("ddmmyyyyMT", sDDMMYYYY);
		
		String week = DateUtil.getWeekFromDate(sDDMMYYYY);
		mod.addObject("dayOfWeekMT", week);
		
		String linkMNWeek = "xsmt-"+ DaiCaThang.toUrlFriendly(week).toLowerCase() +".html";
		mod.addObject("linkMTThu",linkMNWeek);
		String linkMNDay = "xsmt-"+sDDMMYYYY.replace("/", "-") + ".html"; 
		mod.addObject("linkMTDay",linkMNDay);
		
		
		mod.addObject("listLotteryDuoiMT", listDuoiMT);
		mod.addObject("numSizeMT", numSizeMT);
		mod.addObject("listCompany", listCompany);
		mod.setViewName("/ajax/live_mt");
		return mod;
	}

	private List<LotteryCompany> getCompanyDayOfWeek(String region) {
		LotteryCompany lotteryCompany = null;
		List<LotteryCompany> listComp = null;
		String dayOfWeek = DatePro.getDateOfWeekDDMMYYYY(sDDMMYYYY);
		for (int i = 0; i < listCompany.size(); i++) {
			lotteryCompany = listCompany.get(i);
			if (lotteryCompany.getOpendate().contains(dayOfWeek) && lotteryCompany.getRegion().equals(region)) {
				if (listComp == null) {
					listComp = new ArrayList<LotteryCompany>();
				}
				listComp.add(lotteryCompany);
			}
		}

		return listComp;
	}

	protected List<String> findDuoi(List<Lottery> list) {
		List<String> rs = new ArrayList<>();

		if (list != null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				List<String> listDuoi = StringPro.filterDauOrDuoiTrucTiep(list.get(i), true);
				// List<String> temp = convertListDuoi(listDuoi);
				// if (rs.isEmpty()) {
				// rs.addAll(temp);
				// } else {
				// for (int j = 0; j < 10; j++) {
				// rs.set(j, rs.get(j) + "+" + temp.get(j));
				// }
				// }

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

	private List<Lottery> getListLoterry(List<Lottery> listLottery) {
		List<Lottery> rs = new ArrayList<>();
		if (listLottery != null && listLottery.size() > 0) {

			for (Lottery lottery : listLottery) {
				if (checkInListLoterry(rs, lottery)) {
					rs.add(lottery);
				} else {
					int check = checkDataLoterry(lottery, rs);
					if (check != -1) {
						rs.set(check, lottery);
					}
				}
			}
		}

		return rs;
	}

	private int checkDataLoterry(Lottery item, List<Lottery> rs) {
		int check = -1;

		if (rs != null && rs.size() > 0) {

			for (int i = 0; i < rs.size(); i++) {
				Lottery lottery = rs.get(i);

				if (lottery.getCode().equalsIgnoreCase(item.getCode())) {
					int totalLoterry = getNullInAward(lottery);
					int totalItem = getNullInAward(item);

					if (totalItem < totalLoterry) {
						return i;
					}
				}
			}
		}

		return check;
	}

	private int getNullInAward(Lottery lottery) {
		int total = 0;

		if (CommonUtil.isEmptyString(lottery.getSpecial())) {
			total++;
		}

		if (CommonUtil.isEmptyString(lottery.getFifth())) {
			total++;
		}
		if (CommonUtil.isEmptyString(lottery.getSecond())) {
			total++;
		}
		if (CommonUtil.isEmptyString(lottery.getThird())) {
			total++;
		}
		if (CommonUtil.isEmptyString(lottery.getFourth())) {
			total++;
		}
		if (CommonUtil.isEmptyString(lottery.getFifth())) {
			total++;
		}
		if (CommonUtil.isEmptyString(lottery.getSixth())) {
			total++;
		}
		if (CommonUtil.isEmptyString(lottery.getSeventh())) {
			total++;
		}
		if (CommonUtil.isEmptyString(lottery.getEighth())) {
			total++;
		}

		return total;
	}

	private boolean checkInListLoterry(List<Lottery> rs, Lottery item) {
		if (rs != null && rs.size() > 0) {

			for (Lottery lottery : rs) {
				if (lottery.getCode().equalsIgnoreCase(item.getCode())) {
					return false;
				}
			}
		}

		return true;
	}

	private List<Lottery> checkLottery(List<Lottery> listLottery) {
		if (listLottery == null || listLottery.isEmpty()) {
			return null;
		}
		if (!sDDMMYYYY.equals(listLottery.get(0).getOpenDate())) {
			return null;
		}

		Lottery lottery = null;
		for (int i = 0; i < listLottery.size(); i++) {
			lottery = listLottery.get(i);
			lottery.setSpecial(checkAddLoading(lottery.getSpecial()));
			lottery.setFirst(checkAddLoading(lottery.getFirst()));
			lottery.setSecond(checkAddLoading(lottery.getSecond()));
			lottery.setThird(checkAddLoading(lottery.getThird()));
			lottery.setFourth(checkAddLoading(lottery.getFourth()));
			lottery.setFifth(checkAddLoading(lottery.getFifth()));
			lottery.setSixth(checkAddLoading(lottery.getSixth()));
			lottery.setSeventh(checkAddLoading(lottery.getSeventh()));
			lottery.setEighth(checkAddLoading(lottery.getEighth()));

			listLottery.set(i, lottery);
		}

		return listLottery;
	}

	private String checkAddLoading(String str) {
		String linkImageLoading = "<img height='12px' width='12px' src='http://sv1.upsieutoc.com/2018/04/01/loading.gif'>";
		String result = "";
		if (str == null || "".equals(str)) {
			return linkImageLoading;
		}

		String[] arrStr = str.split("-");
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '-') {
				count++;
			}
		}

		if (arrStr.length == 0) {
			for (int i = 0; i < count; i++) {
				result = result + "-" + linkImageLoading;
			}
		} else {
			for (int i = 0; i < arrStr.length; i++) {
				result = result + "-" + arrStr[i];
			}

			int addCount = count - (arrStr.length - 1);
			for (int i = 0; i < addCount; i++) {
				result = result + "-" + linkImageLoading;
			}
		}
		result = result.replaceFirst("-", "");
		return result;
	}

	private Lottery addLotteryNull(String tinh, String code) {
		Lottery lottery = new Lottery();
		String linkImageLoading = "<img height='12px' width='12px' src='http://sv1.upsieutoc.com/2018/04/01/loading.gif'>";
		if ("XSTD".equals(code)) {
			lottery.setCode(code);
			lottery.setProvince(tinh);
			lottery.setOpenDate(sDDMMYYYY);
			lottery.setSpecial(linkImageLoading);
			lottery.setFirst(linkImageLoading);
			lottery.setSecond(linkImageLoading + "-" + linkImageLoading);
			lottery.setThird(linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading
					+ "-" + linkImageLoading + "-" + linkImageLoading);
			lottery.setFourth(
					linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
			lottery.setFifth(linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading
					+ "-" + linkImageLoading + "-" + linkImageLoading);
			lottery.setSixth(linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
			lottery.setSeventh(
					linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
		} else {
			lottery.setCode(code);
			lottery.setProvince(tinh);
			lottery.setOpenDate(sDDMMYYYY);
			lottery.setSpecial(linkImageLoading);
			lottery.setFirst(linkImageLoading);
			lottery.setSecond(linkImageLoading);
			lottery.setThird(linkImageLoading + "-" + linkImageLoading);
			lottery.setFourth(linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-"
					+ linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
			lottery.setFifth(linkImageLoading);
			lottery.setSixth(linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
			lottery.setSeventh(linkImageLoading);
			lottery.setEighth(linkImageLoading);
		}

		return lottery;
	}

	private Lottery findLotterys(List<Lottery> list, String ddmmyyyy) {
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
					lotterys.setLink(lotteryCompany.getCodeLowerCase() + "-ket-qua-xo-so-"
							+ lotteryCompany.getLinkKq247() + ".html");
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
					lotterys.setLink(lotteryCompany.getCodeLowerCase() + "-ket-qua-xo-so-"
							+ lotteryCompany.getLinkKq247() + ".html");
				}
			}

		}

		return lotterys;
	}

	private LotteryCompany findLotteryCompanyOfCode(String code) {
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
}
