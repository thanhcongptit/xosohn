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

import com.soicaupro.thongkebacnho.CommonUtil;

import inet.bean.Lottery;
import inet.bean.LotteryCompany;
import inet.bean.Thongke;
import inet.request.LotteryRequest;
import inet.util.DaiCaThang;
import inet.util.DateProc;
import inet.util.DateUtil;
import inet.util.StringPro;

/**
 *
 * @author conglt
 */
public class BuildXS_TinhLiveController extends BaseController {

	private String sDDMMYYYY = "";

	public BuildXS_TinhLiveController() {
	}

	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mod = new ModelAndView();
		sDDMMYYYY = DateProc.getDateString(DateProc.createTimestamp());
		ddmmyyyy = sDDMMYYYY;
		String code = request.getParameter("r");

		if (!CommonUtil.isEmptyString(code)) {
			List<List<String>> listLotteryDuoi = null;
			List<List<String>> listLotteryDau = null;
			LotteryCompany lotteryCompany = null;
			// thong ke giai dac biet
			List<Lottery> listLottery = null;
			LotteryRequest lotteryRequest = new LotteryRequest();
			listLottery = lotteryRequest.parserLotteryResultLive(code, sDDMMYYYY, sDDMMYYYY);

			if (listLottery != null && !listLottery.isEmpty()) {
				List<String> listDuoiMB = null;
				List<String> listDauMB = null;
				for (int i = 0; i < listLottery.size(); i++) {
					listDuoiMB = StringPro.filterDauOrDuoiTrucTiep(listLottery.get(i), true);
					listDauMB = StringPro.filterDauOrDuoiTrucTiep(listLottery.get(i), false);
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

			listLottery = checkLottery(listLottery);
			Lottery lottery = listLottery.get(0);

			mod.addObject("lotteryCompany", lotteryCompany);
			mod.addObject("date1", sDDMMYYYY);

			String href = "xs" + lotteryCompany.getRegion().toLowerCase() + "-";
			String link = "xsmt-xo-so-mien-trung.html";
			if (lotteryCompany.getRegion().equalsIgnoreCase("MN")) {
				link = "xsmn-xo-so-mien-nam.html";
			}

			mod.addObject("linkXS", link);
			String week1 = DateUtil.getWeekFromDate(sDDMMYYYY);
			String linkMBWeek = href + DaiCaThang.toUrlFriendly(week1).toLowerCase() + ".html";
			mod.addObject("linkThu1", linkMBWeek);
			String linkMBDay = href + sDDMMYYYY.replace("/", "-") + ".html";
			mod.addObject("linkDay1", linkMBDay);

			mod.addObject("week1", week1);
			mod.addObject("lottery1", lottery);
			mod.addObject("listDau1", listLotteryDau.get(0));
			mod.addObject("listDuoi1", listLotteryDuoi.get(0));
			mod.addObject("isOpenToday", "1");
			mod.addObject("code", code);
			mod.addObject("region", lotteryCompany.getRegion());
		}
		mod.setViewName("/ajax/live_tinh");

		return mod;
	}

	protected List<String> findDuoi(List<Lottery> list) {
		List<String> rs = new ArrayList<>();

		if (list != null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				List<List<Thongke>> listDuoi = filterDauDuoi(list.get(i), true);
				List<String> temp = convertListDuoi(listDuoi);
				if (rs.isEmpty()) {
					rs.addAll(temp);
				} else {
					for (int j = 0; j < 10; j++) {
						rs.set(j, rs.get(j) + "+" + temp.get(j));
					}
				}
			}
		}
		return rs;
	}

	private List<List<Thongke>> filterDauDuoi(Lottery lottery, boolean isDauOrDuoi) {
		List<List<Thongke>> results = new ArrayList<>();

		if (isDauOrDuoi) {
			for (int i = 0; i < 10; i++) {
				String str = "";
				HashMap<String, Thongke> thongkes = new HashMap<>();
				thongkes.clear();

				if ((lottery.getSpecial() != null) && (!"".equals(lottery.getSpecial()))
						&& !lottery.getSpecial().contains("<img")) {
					str = subRight(lottery.getSpecial(), 2);
					if (str.startsWith(i + "")) {
						if (thongkes.containsKey(str)) {
							Thongke thongke = thongkes.get(str);
							thongke.setTimes(thongke.getTimes() + 1);
							thongkes.put(str, thongke);
						} else {
							Thongke thongke = new Thongke(str);
							thongke.setDacbiet(true);
							thongkes.put(str, thongke);
						}
					}

				}

				if ((lottery.getFirst() != null) && (!"".equals(lottery.getFirst()))
						&& !lottery.getFirst().contains("<img")) {
					str = subRight(lottery.getFirst(), 2);
					if (str.startsWith(i + "")) {
						if (thongkes.containsKey(str)) {
							Thongke thongke = thongkes.get(str);
							thongke.setTimes(thongke.getTimes() + 1);
							thongkes.put(str, thongke);
						} else {
							Thongke thongke = new Thongke(str);
							thongkes.put(str, thongke);
						}
					}

				}

				String[] arrlottery = null;
				if ((lottery.getSecond() != null) && (!"".equals(lottery.getSecond()))) {
					arrlottery = lottery.getSecond().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
						if (!arrlottery[j].startsWith("<img")) {
							str = subRight(arrlottery[j], 2);
							if (str.startsWith(i + "")) {
								if (thongkes.containsKey(str)) {
									Thongke thongke = thongkes.get(str);
									thongke.setTimes(thongke.getTimes() + 1);
									thongkes.put(str, thongke);
								} else {
									Thongke thongke = new Thongke(str);
									thongkes.put(str, thongke);
								}
							}
						}

					}

				}

				if ((lottery.getThird() != null) && (!"".equals(lottery.getThird()))) {
					arrlottery = lottery.getThird().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
						if (!arrlottery[j].startsWith("<img")) {
							str = subRight(arrlottery[j], 2);
							if (str.startsWith(i + "")) {
								if (thongkes.containsKey(str)) {
									Thongke thongke = thongkes.get(str);
									thongke.setTimes(thongke.getTimes() + 1);
									thongkes.put(str, thongke);
								} else {
									Thongke thongke = new Thongke(str);
									thongkes.put(str, thongke);
								}
							}
						}

					}

				}

				if ((lottery.getFourth() != null) && (!"".equals(lottery.getFourth()))) {
					arrlottery = lottery.getFourth().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
						if (!arrlottery[j].startsWith("<img")) {
							str = subRight(arrlottery[j], 2);
							if (str.startsWith(i + "")) {
								if (thongkes.containsKey(str)) {
									Thongke thongke = thongkes.get(str);
									thongke.setTimes(thongke.getTimes() + 1);
									thongkes.put(str, thongke);
								} else {
									Thongke thongke = new Thongke(str);
									thongkes.put(str, thongke);
								}
							}
						}
					}

				}

				if ((lottery.getFifth() != null) && (!"".equals(lottery.getFifth()))) {
					arrlottery = lottery.getFifth().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
						if (!arrlottery[j].startsWith("<img")) {
							str = subRight(arrlottery[j], 2);
							if (str.startsWith(i + "")) {
								if (thongkes.containsKey(str)) {
									Thongke thongke = thongkes.get(str);
									thongke.setTimes(thongke.getTimes() + 1);
									thongkes.put(str, thongke);
								} else {
									Thongke thongke = new Thongke(str);
									thongkes.put(str, thongke);
								}
							}
						}
					}

				}

				if ((lottery.getSixth() != null) && (!"".equals(lottery.getSixth()))) {
					arrlottery = lottery.getSixth().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
						if (!arrlottery[j].startsWith("<img")) {
							str = subRight(arrlottery[j], 2);
							if (str.startsWith(i + "")) {
								if (thongkes.containsKey(str)) {
									Thongke thongke = thongkes.get(str);
									thongke.setTimes(thongke.getTimes() + 1);
									thongkes.put(str, thongke);
								} else {
									Thongke thongke = new Thongke(str);
									thongkes.put(str, thongke);
								}
							}
						}
					}

				}

				if ((lottery.getSeventh() != null) && (!"".equals(lottery.getSeventh()))) {
					arrlottery = lottery.getSeventh().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
						if (!arrlottery[j].startsWith("<img")) {
							str = subRight(arrlottery[j], 2);
							if (str.startsWith(i + "")) {
								if (thongkes.containsKey(str)) {
									Thongke thongke = thongkes.get(str);
									thongke.setTimes(thongke.getTimes() + 1);
									thongkes.put(str, thongke);
								} else {
									Thongke thongke = new Thongke(str);
									thongkes.put(str, thongke);
								}
							}
						}
					}

				}

				if ((lottery.getEighth() != null) && (!"".equals(lottery.getEighth()))) {
					arrlottery = lottery.getEighth().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
						if (!arrlottery[j].startsWith("<img")) {
							str = subRight(arrlottery[j], 2);
							if (str.startsWith(i + "")) {
								if (thongkes.containsKey(str)) {
									Thongke thongke = thongkes.get(str);
									thongke.setTimes(thongke.getTimes() + 1);
									thongkes.put(str, thongke);
								} else {
									Thongke thongke = new Thongke(str);
									thongkes.put(str, thongke);
								}
							}
						}
					}
				}

				List<Thongke> temp = new ArrayList<>(thongkes.values());
				Collections.sort(temp);
				results.add(temp);
			}
		} else {
			for (int i = 0; i < 10; i++) {
				String str = "";
				HashMap<String, Thongke> thongkes = new HashMap<>();
				thongkes.clear();

				if ((lottery.getSpecial() != null) && (!"".equals(lottery.getSpecial()))
						&& !lottery.getSpecial().contains("<img")) {
					str = subRight(lottery.getSpecial(), 2);
					if (str.endsWith(i + "")) {
						if (thongkes.containsKey(str)) {
							Thongke thongke = thongkes.get(str);
							thongke.setTimes(thongke.getTimes() + 1);
							thongkes.put(str, thongke);
						} else {
							Thongke thongke = new Thongke(str);
							thongke.setDacbiet(true);
							thongkes.put(str, thongke);
						}
					}

				}

				if ((lottery.getFirst() != null) && (!"".equals(lottery.getFirst()))
						&& !lottery.getFirst().contains("<img")) {
					str = subRight(lottery.getFirst(), 2);
					if (str.endsWith(i + "")) {
						if (thongkes.containsKey(str)) {
							Thongke thongke = thongkes.get(str);
							thongke.setTimes(thongke.getTimes() + 1);
							thongkes.put(str, thongke);
						} else {
							Thongke thongke = new Thongke(str);
							thongkes.put(str, thongke);
						}
					}

				}

				String[] arrlottery = null;
				if ((lottery.getSecond() != null) && (!"".equals(lottery.getSecond()))) {
					arrlottery = lottery.getSecond().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
						if (!arrlottery[j].startsWith("<img")) {
							str = subRight(arrlottery[j], 2);
							if (str.endsWith(i + "")) {
								if (thongkes.containsKey(str)) {
									Thongke thongke = thongkes.get(str);
									thongke.setTimes(thongke.getTimes() + 1);
									thongkes.put(str, thongke);
								} else {
									Thongke thongke = new Thongke(str);
									thongkes.put(str, thongke);
								}
							}
						}
					}

				}

				if ((lottery.getThird() != null) && (!"".equals(lottery.getThird()))) {
					arrlottery = lottery.getThird().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
						if (!arrlottery[j].startsWith("<img")) {
							str = subRight(arrlottery[j], 2);
							if (str.endsWith(i + "")) {
								if (thongkes.containsKey(str)) {
									Thongke thongke = thongkes.get(str);
									thongke.setTimes(thongke.getTimes() + 1);
									thongkes.put(str, thongke);
								} else {
									Thongke thongke = new Thongke(str);
									thongkes.put(str, thongke);
								}
							}
						}
					}

				}

				if ((lottery.getFourth() != null) && (!"".equals(lottery.getFourth()))) {
					arrlottery = lottery.getFourth().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
						if (!arrlottery[j].startsWith("<img")) {
							str = subRight(arrlottery[j], 2);
							if (str.endsWith(i + "")) {
								if (thongkes.containsKey(str)) {
									Thongke thongke = thongkes.get(str);
									thongke.setTimes(thongke.getTimes() + 1);
									thongkes.put(str, thongke);
								} else {
									Thongke thongke = new Thongke(str);
									thongkes.put(str, thongke);
								}
							}
						}
					}

				}

				if ((lottery.getFifth() != null) && (!"".equals(lottery.getFifth()))) {
					arrlottery = lottery.getFifth().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
						if (!arrlottery[j].startsWith("<img")) {
							str = subRight(arrlottery[j], 2);
							if (str.endsWith(i + "")) {
								if (thongkes.containsKey(str)) {
									Thongke thongke = thongkes.get(str);
									thongke.setTimes(thongke.getTimes() + 1);
									thongkes.put(str, thongke);
								} else {
									Thongke thongke = new Thongke(str);
									thongkes.put(str, thongke);
								}
							}
						}
					}

				}

				if ((lottery.getSixth() != null) && (!"".equals(lottery.getSixth()))) {
					arrlottery = lottery.getSixth().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
						if (!arrlottery[j].startsWith("<img")) {
							str = subRight(arrlottery[j], 2);
							if (str.endsWith(i + "")) {
								if (thongkes.containsKey(str)) {
									Thongke thongke = thongkes.get(str);
									thongke.setTimes(thongke.getTimes() + 1);
									thongkes.put(str, thongke);
								} else {
									Thongke thongke = new Thongke(str);
									thongkes.put(str, thongke);
								}
							}
						}
					}

				}

				if ((lottery.getSeventh() != null) && (!"".equals(lottery.getSeventh()))) {
					arrlottery = lottery.getSeventh().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
						if (!arrlottery[j].startsWith("<img")) {
							str = subRight(arrlottery[j], 2);
							if (str.endsWith(i + "")) {
								if (thongkes.containsKey(str)) {
									Thongke thongke = thongkes.get(str);
									thongke.setTimes(thongke.getTimes() + 1);
									thongkes.put(str, thongke);
								} else {
									Thongke thongke = new Thongke(str);
									thongkes.put(str, thongke);
								}
							}
						}
					}

				}

				if ((lottery.getEighth() != null) && (!"".equals(lottery.getEighth()))) {
					arrlottery = lottery.getEighth().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
						if (!arrlottery[j].startsWith("<img")) {
							str = subRight(arrlottery[j], 2);
							if (str.endsWith(i + "")) {
								if (thongkes.containsKey(str)) {
									Thongke thongke = thongkes.get(str);
									thongke.setTimes(thongke.getTimes() + 1);
									thongkes.put(str, thongke);
								} else {
									Thongke thongke = new Thongke(str);
									thongkes.put(str, thongke);
								}
							}
						}
					}
				}

				List<Thongke> temp = new ArrayList<>(thongkes.values());
				Collections.sort(temp);
				results.add(temp);
			}

		}

		return results;
	}

	private String subRight(String temp, int i) {
		if (temp == null) {
			return "";
		}
		String result = "";
		if (temp.length() - i >= 0) {
			result = temp.substring(temp.length() - i, temp.length());
		} else {
			result = temp;
		}
		return result;
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

	private List<Lottery> checkLottery(List<Lottery> listLottery) {
		if (listLottery == null || listLottery.isEmpty()) {
			return null;
		}
		if (!ddmmyyyy.equals(listLottery.get(0).getOpenDate())) {
			return null;
		}

		Lottery lottery = null;
		for (int i = 0; i < listLottery.size(); i++) {
			lottery = listLottery.get(i);
			lottery.setSpecial(checkAddLoading(lottery.getSpecial()));
			lottery.setFirst(checkAddLoading(lottery.getFirst()));
			lottery.setSecond(checkAddLoading(lottery.getSecond()));
			lottery.setThird(checkAddLoading(lottery.getThird()));

			String fourth = lottery.getFourth();

			if (!CommonUtil.isEmptyString(fourth)) {
				lottery.setFourth(getAward(fourth, 7));
			} else {
				lottery.setFourth(checkAddLoading(lottery.getFourth()));
			}

			lottery.setFifth(checkAddLoading(lottery.getFifth()));
			lottery.setSixth(checkAddLoading(lottery.getSixth()));
			lottery.setSeventh(checkAddLoading(lottery.getSeventh()));
			lottery.setEighth(checkAddLoading(lottery.getEighth()));

			listLottery.set(i, lottery);
		}

		return listLottery;
	}

	private String getAward(String third, int length) {
		String linkImageLoading = "<img height='12px' width='12px' src='http://sv1.upsieutoc.com/2018/04/01/loading.gif'>";
		StringBuilder rs = new StringBuilder(third);
		String[] temp = third.split("-");
		int total = length - temp.length;
		for (int i = 1; i <= total; i++) {
			rs.append("-").append(linkImageLoading);
		}
		return rs.toString();
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

}
