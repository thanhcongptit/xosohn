/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import com.soicaupro.thongkebacnho.CommonUtil;

import inet.bean.LoXien;
import inet.bean.Lottery;
import inet.bean.LotteryResult;
import inet.bean.ThongKeBean;

/**
 *
 * @author hanhlm
 */
public class LotoUtils {

	private static final List<String> LOTO_LIST = new ArrayList<>();

	static {
		for (int i = 0; i < 100; i++) {
			if (i < 10) {
				LOTO_LIST.add("0" + i);
			} else {
				LOTO_LIST.add("" + i);
			}
		}
	}
	
	private static final List<String> LOTO_KEP_LIST = new ArrayList<>();

	static {
		for (int i = 0; i < 10; i++) {
			LOTO_KEP_LIST.add(i+""+i);
		}
	}

	public static String getLotoDao(String loto) {
		String lotoDao = loto.substring(1, 2) + loto.substring(0, 1);

		return lotoDao;
	}

	public static boolean isLoto(String loto) {
		return loto != null && LOTO_LIST.contains(loto);
	}

	public static String getAllLoto(String seperator) {
		String result = "";
		if (LOTO_LIST != null && LOTO_LIST.size() > 0) {
			int size = LOTO_LIST.size();
			for (int i = 0; i < size; i++) {
				if (i < (size - 1)) {
					result += LOTO_LIST.get(i) + seperator;
				} else {
					result += LOTO_LIST.get(i);
				}
			}
		}
		return result;
	}

	public static boolean isTongLe(String loto) {
		int first = Integer.parseInt(String.valueOf(loto.charAt(0)));
		int second = Integer.parseInt(String.valueOf(loto.charAt(1)));
		int sum = first + second;
		return (sum % 2 != 0);
	}

	public static boolean isTongChan(String loto) {
		int first = Integer.parseInt(String.valueOf(loto.charAt(0)));
		int second = Integer.parseInt(String.valueOf(loto.charAt(1)));
		int sum = first + second;
		return (sum % 2 == 0);
	}

	public static boolean isBoChanChan(String loto) {
		int first = Integer.parseInt(String.valueOf(loto.charAt(0)));
		int second = Integer.parseInt(String.valueOf(loto.charAt(1)));
		return (first % 2 == 0) && (second % 2 == 0);
	}

	public static boolean isBoLeLe(String loto) {
		int first = Integer.parseInt(String.valueOf(loto.charAt(0)));
		int second = Integer.parseInt(String.valueOf(loto.charAt(1)));
		return (first % 2 != 0) && (second % 2 != 0);
	}

	public static boolean isBoChanLe(String loto) {
		int first = Integer.parseInt(String.valueOf(loto.charAt(0)));
		int second = Integer.parseInt(String.valueOf(loto.charAt(1)));
		return (first % 2 == 0) && (second % 2 != 0);
	}

	public static boolean isBoLeChan(String loto) {
		int first = Integer.parseInt(String.valueOf(loto.charAt(0)));
		int second = Integer.parseInt(String.valueOf(loto.charAt(1)));
		return (first % 2 != 0) && (second % 2 == 0);
	}

	public static boolean isBoKep(String loto) {
		int first = Integer.parseInt(String.valueOf(loto.charAt(0)));
		int second = Integer.parseInt(String.valueOf(loto.charAt(1)));
		return first == second;
	}

	public static boolean isBoSatKep(String loto) {
		int first = Integer.parseInt(String.valueOf(loto.charAt(0)));
		int second = Integer.parseInt(String.valueOf(loto.charAt(1)));
		return (first + 1 == second) || (first - 1 == second);
	}

	public static boolean isTong(String loto, int tong) {
		int first = Integer.parseInt(String.valueOf(loto.charAt(0)));
		int second = Integer.parseInt(String.valueOf(loto.charAt(1)));
		return (first + second == tong) || (first + second - 10 == tong);
	}

	public static String getAllLoto(String type, String seperator) {
		String result = "";
		if (LOTO_LIST != null && LOTO_LIST.size() > 0) {
			int size = LOTO_LIST.size();
			String loto = "";
			for (int i = 0; i < size; i++) {
				loto = LOTO_LIST.get(i);
				if ("TONGLE".equalsIgnoreCase(type) && isTongLe(loto)) {
					result += loto;
					if (i < (size - 1)) {
						result += seperator;
					}
				} else if ("TONGCHAN".equalsIgnoreCase(type) && isTongChan(loto)) {
					result += loto;
					if (i < (size - 1)) {
						result += seperator;
					}
				} else if ("BOCHANCHAN".equalsIgnoreCase(type) && isBoChanChan(loto)) {
					result += loto;
					if (i < (size - 1)) {
						result += seperator;
					}
				} else if ("BOLELE".equalsIgnoreCase(type) && isBoLeLe(loto)) {
					result += loto;
					if (i < (size - 1)) {
						result += seperator;
					}
				} else if ("BOCHANLE".equalsIgnoreCase(type) && isBoChanLe(loto)) {
					result += loto;
					if (i < (size - 1)) {
						result += seperator;
					}
				} else if ("BOLECHAN".equalsIgnoreCase(type) && isBoLeChan(loto)) {
					result += loto;
					if (i < (size - 1)) {
						result += seperator;
					}
				} else if ("BOKEP".equalsIgnoreCase(type) && isBoKep(loto)) {
					result += loto;
					if (i < (size - 1)) {
						result += seperator;
					}
				} else if ("BOSATKEP".equalsIgnoreCase(type) && isBoSatKep(loto)) {
					result += loto;
					if (i < (size - 1)) {
						result += seperator;
					}
				}
			}
		}
		return result;
	}

	public static String getAllLotoByTong(int tong, String seperator) {
		String result = "";
		if (LOTO_LIST != null && LOTO_LIST.size() > 0) {
			int size = LOTO_LIST.size();
			String loto = "";
			for (int i = 0; i < size; i++) {
				loto = LOTO_LIST.get(i);
				if (isTong(loto, tong)) {
					result += loto;
					if (i < (size - 1)) {
						result += seperator;
					}
				}
			}
		}
		return result;
	}

	public static boolean findNotLotoResult(String lotoResult, String loto, boolean dao) {
		int dem = 0;
		String lotoDao = loto.substring(1, 2) + loto.substring(0, 1);
		for (StringTokenizer stringTokenizer = new StringTokenizer(lotoResult, "-"); stringTokenizer.hasMoreTokens();) {
			String token = stringTokenizer.nextToken();
			if (dao) {
				if (token.equals(loto) || token.equals(lotoDao)) {
					dem++;
				}
			} else {
				if (token.equals(loto)) {
					dem++;
				}
			}

		}
		return dem == 0;
	}

	public static boolean findLotoResult(String lotoResult, String loto, int solan, boolean dao) {
		int dem = 0;
		String lotoDao = loto.substring(1, 2) + loto.substring(0, 1);
		for (StringTokenizer stringTokenizer = new StringTokenizer(lotoResult, "-"); stringTokenizer.hasMoreTokens();) {
			String token = stringTokenizer.nextToken();
			if (dao) {
				if (token.equals(loto) || token.equals(lotoDao)) {
					dem++;
				}
			} else {
				if (token.equals(loto)) {
					dem++;
				}
			}

		}
		return dem >= solan;
	}

	public static boolean findLotoResultDacbiet(String lotoResult, String loto, boolean cham) {

		if (cham) {
			String dau = loto.substring(0, 1);
			String duoi = loto.substring(1, 2);
			if (lotoResult.startsWith(dau) || lotoResult.endsWith(dau) || lotoResult.startsWith(duoi)
					|| lotoResult.endsWith(duoi)) {
				return true;
			}
		} else {
			if (lotoResult.equals(loto)) {
				return true;
			}
		}

		return false;
	}

	public static boolean findLotoResultChamDacbiet(String lotoResult, String loto, int cham) {

		for (int i = 0; i < cham; i++) {
			String s = loto.substring(i, i + 1);
			if (lotoResult.endsWith(s) || lotoResult.startsWith(s)) {
				return true;
			}
		}

		return false;
	}

	public static int countLotoResultDacBiet(String lotoResult, String loto, int opt) {
		// opt=0 la dau
		// opt=1 la duoi
		// opt=2 la tong
		if (lotoResult == null || "".equals(lotoResult)) {
			return 0;
		}

		lotoResult = lotoResult.trim();

		if (opt == 0) {
			if (lotoResult.startsWith(loto)) {
				return 1;
			}
		} else if (opt == 1) {
			if (lotoResult.endsWith(loto)) {
				return 1;
			}
		} else if (opt == 2) {
			if (lotoResult.length() > 1) {
				try {
					int so1 = Integer.parseInt(lotoResult.substring(0, 1));
					int so2 = Integer.parseInt(lotoResult.substring(1, 2));
					int tong = so1 + so2;
					if (tong > 10) {
						tong = tong - 10;
					}
					if (tong == Integer.parseInt(loto)) {
						return 1;
					}
				} catch (Exception e) {
					System.out.println("excep>>>>>>>>>" + lotoResult);
				}

			}
		} else if (opt == 3) {
			if (lotoResult.equals(loto)) {
				return 1;
			}
		}

		return 0;
	}

	public static int countLotoResult(String lotoResult, String loto, boolean dao) {
		int dem = 0;
		String lotoDao = loto.substring(1, 2) + loto.substring(0, 1);
		for (StringTokenizer stringTokenizer = new StringTokenizer(lotoResult, "-"); stringTokenizer.hasMoreTokens();) {
			String token = stringTokenizer.nextToken();
			if (dao) {
				if (token.equals(loto) || token.equals(lotoDao)) {
					dem++;
				}
			} else {
				if (token.equals(loto)) {
					dem++;
				}
			}

		}

		return dem;
	}

	public static int countLotoResult(String lotoResult, String loto) {
		// int dem = 0;
		int result = 0;
		for (StringTokenizer stringLoto = new StringTokenizer(loto, "-"); stringLoto.hasMoreTokens();) {
			String tokenLoto = stringLoto.nextToken();
			for (StringTokenizer stringTokenizer = new StringTokenizer(lotoResult, "-"); stringTokenizer
					.hasMoreTokens();) {
				String token = stringTokenizer.nextToken();
				if (tokenLoto.equals(token)) {
					result++;
				}
			}
		}

		return result;
	}

	public static int countDauDuoi(String lotoResult, String dauduoi, boolean isDauDuoi) {
		int result = 0;
		for (StringTokenizer stringTokenizer = new StringTokenizer(lotoResult, "-"); stringTokenizer.hasMoreTokens();) {
			String token = stringTokenizer.nextToken();
			if (isDauDuoi) {
				if (token.startsWith(dauduoi)) {
					result++;
				}
			} else {
				if (token.endsWith(dauduoi)) {
					result++;
				}
			}
		}
		return result;
	}

	public static List<String> ghepXien(String input, int opt) {
		List<String> result = null;
		String[] arr = input.split(",");

		if (opt == 2) {
			if (arr.length < 2) {
				return null;
			}
			for (int i = 0; i < arr.length - 1; i++) {
				for (int j = i + 1; j < arr.length; j++) {
					if (result == null) {
						result = new ArrayList<>();
					}
					result.add(arr[i] + "," + arr[j]);
				}
			}
		}

		if (opt == 3) {
			if (arr.length < 3) {
				return null;
			}
			for (int i = 0; i < arr.length - 2; i++) {
				for (int j = i + 1; j < arr.length - 1; j++) {
					for (int k = j + 1; k < arr.length; k++) {
						if (result == null) {
							result = new ArrayList<>();
						}
						result.add(arr[i] + "," + arr[j] + "," + arr[k]);
					}
				}
			}
		}

		if (opt == 4) {
			if (arr.length < 4) {
				return null;
			}
			for (int i = 0; i < arr.length - 3; i++) {
				for (int j = i + 1; j < arr.length - 2; j++) {
					for (int k = j + 1; k < arr.length - 1; k++) {
						for (int n = k + 1; n < arr.length; n++) {
							if (result == null) {
								result = new ArrayList<>();
							}
							result.add(arr[i] + "," + arr[j] + "," + arr[k] + "," + arr[n]);
						}
					}
				}
			}
		}

		return result;
	}

	public static void main(String[] args) {
		List<String> list = LotoUtils.ghepXien("02,03,04,05,06", 4);
		for (String string : list) {
			System.out.println(string);
		}
	}

	public static String findKhungLotoResult(String lotoResult, String loto, boolean isSongThu) {
		String awards[] = lotoResult.split("-");
		for (String s : awards) {
			if (!isSongThu) {
				if (loto.equalsIgnoreCase(s)) {
					return s;
				}
			} else {
				String reverse = new StringBuffer(s).reverse().toString();
				if (s.equalsIgnoreCase(loto) || reverse.equalsIgnoreCase(loto)) {
					return s;
				}
			}
		}
		return "";
	}

	public static LotteryResult replaceMB(LotteryResult lotteryResult, String vitri) {
		String special = lotteryResult.getSpecial();
		String first = lotteryResult.getFirst();
		String second = lotteryResult.getSecond();
		String third = lotteryResult.getThird();
		String fourth = lotteryResult.getFourth();
		String fifth = lotteryResult.getFifth();
		String sixth = lotteryResult.getSixth();
		String seventh = lotteryResult.getSeventh();
		String[] temp;
		vitri = vitri.replace(".", "-");

		if (vitri.startsWith("G0")) {
			temp = vitri.split("-");
			String index = temp[1];
			String award = mark(special, Integer.parseInt(index), 1);
			lotteryResult.setSpecial(award);

		} else if (vitri.startsWith("G1")) {
			temp = vitri.split("-");
			String index = temp[1];
			String award = mark(first, Integer.parseInt(index), 1);
			lotteryResult.setFirst(award);

		} else if (vitri.startsWith("G2")) {
			temp = vitri.split("-");
			String award = mark(second, Integer.parseInt(temp[2]), Integer.parseInt(temp[1]));
			lotteryResult.setSecond(award);

		} else if (vitri.startsWith("G3")) {
			temp = vitri.split("-");
			String award = mark(third, Integer.parseInt(temp[2]), Integer.parseInt(temp[1]));
			lotteryResult.setThird(award);

		} else if (vitri.startsWith("G4")) {
			temp = vitri.split("-");
			String award = mark(fourth, Integer.parseInt(temp[2]), Integer.parseInt(temp[1]));
			lotteryResult.setFourth(award);

		} else if (vitri.startsWith("G5")) {
			temp = vitri.split("-");
			String award = mark(fifth, Integer.parseInt(temp[2]), Integer.parseInt(temp[1]));
			lotteryResult.setFifth(award);
		} else if (vitri.startsWith("G6")) {
			temp = vitri.split("-");
			String award = mark(sixth, Integer.parseInt(temp[2]), Integer.parseInt(temp[1]));
			lotteryResult.setSixth(award);
		} else if (vitri.startsWith("G7")) {
			temp = vitri.split("-");
			String award = mark(seventh, Integer.parseInt(temp[2]), Integer.parseInt(temp[1]));
			lotteryResult.setSeventh(award);
		}

		return lotteryResult;
	}

	public static Lottery replaceMT(Lottery lotteryResult, String vitri) {
		String special = lotteryResult.getSpecial();
		String first = lotteryResult.getFirst();
		String second = lotteryResult.getSecond();
		String third = lotteryResult.getThird();
		String fourth = lotteryResult.getFourth();
		String fifth = lotteryResult.getFifth();
		String sixth = lotteryResult.getSixth();
		String seventh = lotteryResult.getSeventh();
		String eight = lotteryResult.getEighth();

		String[] temp;
		vitri = vitri.replace(".", "-");

		if (vitri.startsWith("G0")) {
			temp = vitri.split("-");
			String index = temp[1];
			String award = mark(special, Integer.parseInt(index), 1);
			lotteryResult.setSpecial(award);

		} else if (vitri.startsWith("G1")) {
			temp = vitri.split("-");
			String index = temp[1];
			String award = mark(first, Integer.parseInt(index), 1);
			lotteryResult.setFirst(award);

		} else if (vitri.startsWith("G2")) {
			temp = vitri.split("-");
			String index = temp[1];
			String award = mark(second, Integer.parseInt(index), 1);
			lotteryResult.setSecond(award);

		} else if (vitri.startsWith("G3")) {
			temp = vitri.split("-");
			String award = mark(third, Integer.parseInt(temp[2]), Integer.parseInt(temp[1]));
			lotteryResult.setThird(award);

		} else if (vitri.startsWith("G4")) {
			temp = vitri.split("-");
			String award = mark(fourth, Integer.parseInt(temp[2]), Integer.parseInt(temp[1]));
			lotteryResult.setFourth(award);

		} else if (vitri.startsWith("G5")) {
			temp = vitri.split("-");
			String index = temp[1];
			String award = mark(fifth, Integer.parseInt(index), 1);
			lotteryResult.setFifth(award);
		} else if (vitri.startsWith("G6")) {
			temp = vitri.split("-");
			String award = mark(sixth, Integer.parseInt(temp[2]), Integer.parseInt(temp[1]));
			lotteryResult.setSixth(award);
		} else if (vitri.startsWith("G7")) {
			temp = vitri.split("-");
			String index = temp[1];
			String award = mark(seventh, Integer.parseInt(index), 1);
			lotteryResult.setSeventh(award);
		} else if (vitri.startsWith("G8")) {
			temp = vitri.split("-");
			String index = temp[1];
			String award = mark(eight, Integer.parseInt(index), 1);
			lotteryResult.setEighth(award);
		}

		return lotteryResult;
	}

	public static Lottery replaceMBLottery(Lottery lotteryResult, String vitri) {
		String special = lotteryResult.getSpecial();
		String first = lotteryResult.getFirst();
		String second = lotteryResult.getSecond();
		String third = lotteryResult.getThird();
		String fourth = lotteryResult.getFourth();
		String fifth = lotteryResult.getFifth();
		String sixth = lotteryResult.getSixth();
		String seventh = lotteryResult.getSeventh();
		String[] temp;
		vitri = vitri.replace(".", "-");

		if (vitri.startsWith("G0")) {
			temp = vitri.split("-");
			String index = temp[1];
			String award = mark(special, Integer.parseInt(index), 1);
			lotteryResult.setSpecial(award);

		} else if (vitri.startsWith("G1")) {
			temp = vitri.split("-");
			String index = temp[1];
			String award = mark(first, Integer.parseInt(index), 1);
			lotteryResult.setFirst(award);

		} else if (vitri.startsWith("G2")) {
			temp = vitri.split("-");
			String award = mark(second, Integer.parseInt(temp[2]), Integer.parseInt(temp[1]));
			lotteryResult.setSecond(award);

		} else if (vitri.startsWith("G3")) {
			temp = vitri.split("-");
			String award = mark(third, Integer.parseInt(temp[2]), Integer.parseInt(temp[1]));
			lotteryResult.setThird(award);

		} else if (vitri.startsWith("G4")) {
			temp = vitri.split("-");
			String award = mark(fourth, Integer.parseInt(temp[2]), Integer.parseInt(temp[1]));
			lotteryResult.setFourth(award);

		} else if (vitri.startsWith("G5")) {
			temp = vitri.split("-");
			String award = mark(fifth, Integer.parseInt(temp[2]), Integer.parseInt(temp[1]));
			lotteryResult.setFifth(award);
		} else if (vitri.startsWith("G6")) {
			temp = vitri.split("-");
			String award = mark(sixth, Integer.parseInt(temp[2]), Integer.parseInt(temp[1]));
			lotteryResult.setSixth(award);
		} else if (vitri.startsWith("G7")) {
			temp = vitri.split("-");
			String award = mark(seventh, Integer.parseInt(temp[2]), Integer.parseInt(temp[1]));
			lotteryResult.setSeventh(award);
		}

		return lotteryResult;
	}

	private static String mark(String str, int index, int indexInAward) {
		String[] tempAwards = str.split("-");
		String tempStr = "";
		boolean isFind = false;
		String result = "";
		for (int i = 0; i < tempAwards.length; i++) {
			if ((i + 1) == indexInAward) {
				String award = tempAwards[i];
				int count = 0;
				for (int j = 0; j < award.length(); j++) {
					char character = award.charAt(j);
					String c = character + "";
					if (Character.isDigit(character)) {
						count++;
					}

					if (count == index && !isFind) {
						isFind = true;
						c = "";
						c = "<span class='txt_green'>" + character + "</span>";
					}
					tempStr += c;
				}

				result += tempStr + "-";
			} else {
				result += tempAwards[i] + "-";
			}
		}

		result = result.substring(0, result.length() - 1);
		return result;
	}

	public static boolean checkAwardInLottery(String loto, LotteryResult lotteryResult) {
		String special = lotteryResult.getSpecial();

		if (special.endsWith(loto)) {
			return true;
		} else if (lotteryResult.getFirst().endsWith(loto)) {
			return true;
		}

		String second = lotteryResult.getSecond();
		String seconds[] = second.split("-");

		for (String award : seconds) {
			if (award.endsWith(loto)) {
				return true;
			}
		}

		String third = lotteryResult.getThird();
		String thirds[] = third.split("-");

		for (String award : thirds) {
			if (award.endsWith(loto)) {
				return true;
			}
		}

		String fourth = lotteryResult.getFourth();
		String fourths[] = fourth.split("-");

		for (String award : fourths) {
			if (award.endsWith(loto)) {
				return true;
			}
		}

		String fifth = lotteryResult.getFifth();
		String fifths[] = fifth.split("-");

		for (String award : fifths) {
			if (award.endsWith(loto)) {
				return true;
			}
		}

		String sixth = lotteryResult.getSixth();
		String sixths[] = sixth.split("-");

		for (String award : sixths) {
			if (award.endsWith(loto)) {
				return true;
			}
		}

		String seventh = lotteryResult.getSeventh();
		String sevenths[] = seventh.split("-");

		for (String award : sevenths) {
			if (award.endsWith(loto)) {
				return true;
			}
		}

		String eight = lotteryResult.getEighth();
		if (!CommonUtil.isEmptyString(eight)) {
			String eights[] = eight.split("-");

			for (String award : eights) {
				if (award.endsWith(loto)) {
					return true;
				}
			}
		}

		return false;
	}

	public static List<String> getAllOfAwardInLottery(LotteryResult result) {
		Set<String> sets = new HashSet<>();
		String special = result.getSpecial();
		getDetailInAward(special, sets);

		String first = result.getFirst();
		getDetailInAward(first, sets);

		String second = result.getSecond();
		getDetailInAward(second, sets);

		String third = result.getThird();
		getDetailInAward(third, sets);

		String fourth = result.getFourth();
		getDetailInAward(fourth, sets);

		String fifth = result.getFifth();
		getDetailInAward(fifth, sets);

		String sixth = result.getSixth();
		getDetailInAward(sixth, sets);

		String seventh = result.getSeventh();
		getDetailInAward(seventh, sets);

		String eight = result.getEighth();
		getDetailInAward(eight, sets);

		return new ArrayList<>(sets);
	}

	private static void getDetailInAward(String award, Set<String> sets) {
		if (!CommonUtil.isEmptyString(award)) {
			String awards[] = award.split("-");

			for (String temp : awards) {
				String loto = StringPro.subRight(temp, 2);
				sets.add(loto);
			}
		}
	}

	public static List<LoXien> getLoXien2(List<LotteryResult> lotteryResults) {
		List<LoXien> loXiens = new ArrayList<>();

		for (int i = 0; i < LOTO_LIST.size() - 1; i++) {
			for (int j = i + 1; j < LOTO_LIST.size(); j++) {

				String loto1 = LOTO_LIST.get(i);
				String loto2 = LOTO_LIST.get(j);
				LoXien loXien = new LoXien();
				int count = 0;

				loXien.setCapLoto(loto1 + "-" + loto2);
				List<ThongKeBean> thongKeBeans = new ArrayList<>();
				
				for (LotteryResult lotteryResult : lotteryResults) {
					if (checkAwardInLottery(loto1, lotteryResult) && checkAwardInLottery(loto2, lotteryResult)) {
						count++;
						
						ThongKeBean thongKeBean = new ThongKeBean();
						thongKeBean.setDateOpen(lotteryResult.getOpenDate().replace("/", "-"));
						thongKeBean.setDateView(lotteryResult.getOpenDate());
						thongKeBeans.add(thongKeBean);
					}
				}

				loXien.setCount(count);

				loXien.setOpenDates(thongKeBeans);
				loXiens.add(loXien);
			}
		}

		Collections.sort(loXiens);
		loXiens = loXiens.subList(0, 10);

		return loXiens;
	}

	public static List<LoXien> getLoXien3(List<LotteryResult> lotteryResults) {
		List<LoXien> loXiens = new ArrayList<>();

		for (int i = 0; i < LOTO_LIST.size() - 1; i++) {
			for (int j = i + 1; j < LOTO_LIST.size(); j++) {
				for (int z = j + 1; z < LOTO_LIST.size(); z++) {

					String loto1 = LOTO_LIST.get(i);
					String loto2 = LOTO_LIST.get(j);
					String loto3 = LOTO_LIST.get(z);
					
					LoXien loXien = new LoXien();
					int count = 0;
					List<ThongKeBean> thongKeBeans = new ArrayList<>();
					
					loXien.setCapLoto(loto1 + "-" + loto2 + "-" + loto3);

					for (LotteryResult lotteryResult : lotteryResults) {
						if (checkAwardInLottery(loto1, lotteryResult) && checkAwardInLottery(loto2, lotteryResult)
								&& checkAwardInLottery(loto3, lotteryResult)) {
							count++;
							
							ThongKeBean thongKeBean = new ThongKeBean();
							thongKeBean.setDateOpen(lotteryResult.getOpenDate().replace("/", "-"));
							thongKeBean.setDateView(lotteryResult.getOpenDate());
							thongKeBeans.add(thongKeBean);
						}
					}

					loXien.setCount(count);

					loXien.setOpenDates(thongKeBeans);
					loXiens.add(loXien);
				}
			}
		}

		Collections.sort(loXiens);
		loXiens = loXiens.subList(0, 10);

		return loXiens;
	}
	
	public static List<LoXien> getLoKep(List<LotteryResult> lotteryResults) {
		List<LoXien> loXiens = new ArrayList<>();

		for (int i = 0; i < LOTO_KEP_LIST.size() - 1; i++) {
			for (int j = i + 1; j < LOTO_KEP_LIST.size(); j++) {

				String loto1 = LOTO_KEP_LIST.get(i);
				String loto2 = LOTO_KEP_LIST.get(j);
				
				LoXien loXien = new LoXien();
				int count = 0;

				loXien.setCapLoto(loto1 + "-" + loto2);
				List<ThongKeBean> thongKeBeans = new ArrayList<>();
				
				for (LotteryResult lotteryResult : lotteryResults) {
					if (checkAwardInLottery(loto1, lotteryResult) && checkAwardInLottery(loto2, lotteryResult)) {
						count++;
						ThongKeBean thongKeBean = new ThongKeBean();
						thongKeBean.setDateOpen(lotteryResult.getOpenDate().replace("/", "-"));
						thongKeBean.setDateView(lotteryResult.getOpenDate());
						thongKeBeans.add(thongKeBean);
					}
				}

				loXien.setCount(count);

				loXien.setOpenDates(thongKeBeans);
				loXiens.add(loXien);
			}
		}

		Collections.sort(loXiens);
		loXiens = loXiens.subList(0, 10);

		return loXiens;
	}
}
