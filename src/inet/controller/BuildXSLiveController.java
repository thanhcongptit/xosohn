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
import org.springframework.web.servlet.mvc.AbstractController;

import com.soicaupro.thongkebacnho.CommonUtil;

import inet.bean.Lottery;
import inet.bean.Thongke;
import inet.model.LotteryResultDAO;
import inet.util.DaiCaThang;
import inet.util.DateProc;
import inet.util.DateUtil;
import inet.util.StringPro;

/**
 *
 * @author hanhlm
 */
public class BuildXSLiveController extends AbstractController {

	List<Lottery> listLottery = null;
	List<String> listLotterysDuoiMB = null;
	List<String> listLotteryDauMB = null;
	private String ddmmyyyy = "";
	LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();

	public BuildXSLiveController() {
	}

	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mod = new ModelAndView();
		ddmmyyyy = DateProc.getDateString(DateProc.createTimestamp());
		String region = request.getParameter("r");
		listLottery = lotteryResultDAO.findLotteryNewestRegion(region, ddmmyyyy);
		listLottery = checkLottery(listLottery);

		if (listLottery != null && !listLottery.isEmpty()) {
			synchronized (this) {

				if (listLottery.size() > 1) {
					listLottery = listLottery.subList(0, 0);
				}

//				listLotterysDuoiMB = filterDauDuoi(listLottery.get(0), true);
//				listLotteryDauMB = filterDauDuoi(listLottery.get(0), false);
				listLotterysDuoiMB = StringPro.filterDauOrDuoiTrucTiep(listLottery.get(0), true);
				listLotteryDauMB = StringPro.filterDauOrDuoiTrucTiep(listLottery.get(0), false);
				
			}
		} else {
			listLottery = new ArrayList<Lottery>();
			listLottery.add(addLotteryNull("Mien Bac", "XSTD"));
			listLotterysDuoiMB = StringPro.filterDauOrDuoiTrucTiep(listLottery.get(0), true);
			listLotteryDauMB = StringPro.filterDauOrDuoiTrucTiep(listLottery.get(0), false);
		}

		if (region == null || "".equals(region) || "all".equalsIgnoreCase(region)) {
			mod.setViewName("/ajax/live_mb");
		} else if ("mb".equalsIgnoreCase(region)) {
			mod.setViewName("/ajax/live_mb");
		}

		mod.addObject("ddmmyyyy", ddmmyyyy);
		
		String week = DateUtil.getWeekFromDate(ddmmyyyy);
		mod.addObject("dayOfWeek", week);
		
		String linkMNWeek = "xsmb-"+ DaiCaThang.toUrlFriendly(week).toLowerCase() +".html";
		mod.addObject("linkMBThu",linkMNWeek);
		String linkMNDay = "xsmb-"+ddmmyyyy.replace("/", "-") + ".html"; 
		mod.addObject("linkMBDay",linkMNDay);

		mod.addObject("listLotteryMB", listLottery);
		mod.addObject("listLotteryDuoiMB", listLotterysDuoiMB);
		mod.addObject("listLotteryDauMB", listLotteryDauMB);

		return mod;
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
			String third = lottery.getThird();
			if (!CommonUtil.isEmptyString(third)) {
				lottery.setThird(getAward(third));

			} else {
				lottery.setThird(checkAddLoading(third));
			}

			lottery.setFourth(checkAddLoading(lottery.getFourth()));
			// System.out.println("lottery.setFourth====>>>>"+lottery.getFourth());
			String fifth = lottery.getFifth();

			if (!CommonUtil.isEmptyString(fifth)) {
				lottery.setFifth(getAward(fifth));
			} else {
				lottery.setFifth(checkAddLoading(lottery.getFifth()));
			}

			lottery.setSixth(checkAddLoading(lottery.getSixth()));
			lottery.setSeventh(checkAddLoading(lottery.getSeventh()));
			lottery.setEighth(checkAddLoading(lottery.getEighth()));

			listLottery.set(i, lottery);
		}

		return listLottery;
	}

	private String getAward(String third) {
		String linkImageLoading = "<img height='12px' width='12px' src='http://sv1.upsieutoc.com/2018/04/01/loading.gif'>";
		StringBuilder rs = new StringBuilder(third);
		String[] temp = third.split("-");
		int total = 6 - temp.length;
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

	private Lottery addLotteryNull(String tinh, String code) {
		Lottery lottery = new Lottery();
		String linkImageLoading = "<img height='12px' width='12px' src='http://sv1.upsieutoc.com/2018/04/01/loading.gif'>";
		if ("XSTD".equals(code)) {
			lottery.setCode(code);
			lottery.setProvince(tinh);
			lottery.setOpenDate(ddmmyyyy);
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
			lottery.setOpenDate(ddmmyyyy);
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

}
