/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import com.soicaupro.thongkebacnho.CommonUtil;

import inet.bean.Lottery;
import inet.bean.Thongke;

/**
 *
 * @author hanhlm
 */
public class StringPro {

	// kiem tra so cap so da ve trong ket qua loto
	public static boolean checkResultLoto(String str, String str1, int nhay) {
		boolean result = false;

		String[] arr = str.split(",");
		int dem = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(str1)) {
				dem++;
			}
			if (dem == nhay) {
				result = true;
				break;
			}
		}

		return result;
	}

	// kiem tra ket qua mo bat loto
	public static String checkMobatLoto(String loto, String lotoMobat) {
		String result = "";
		String[] arr = lotoMobat.split("-");
		for (int i = 0; i < arr.length; i++) {
			if (checkResultLoto(loto, arr[i], 1)) {
				if ("".equals(result)) {
					result = arr[i];
				} else {
					result = result + "-" + arr[i];
				}
			}
		}

		return result;
	}

	public static String checkMobatDacBiet(String lotodacbiet, String lotoMobatDacBiet) {
		String result = "";
		String[] arr = lotoMobatDacBiet.split("-");
		for (int i = 0; i < arr.length; i++) {
			if (lotodacbiet.equals(arr[i])) {
				if ("".equals(result)) {
					result = arr[i];
				} else {
					result = result + "-" + arr[i];
				}
			}
		}

		return result;
	}

	public static String checkMobatChamDacBiet(String lotodacbiet, String lotoMobatChamDacBiet) {
		String result = "";
		String[] arr = lotoMobatChamDacBiet.split("-");
		for (int i = 0; i < arr.length; i++) {
			if (lotodacbiet.contains(arr[i])) {
				if ("".equals(result)) {
					result = arr[i];
				} else {
					result = result + "-" + arr[i];
				}
			}
		}

		return result;
	}

	public static String sub2RightString(String special, String first, String second, String third, String fourth,
			String fifth, String sixth, String seventh, String eighth) {

		// giai dac biet
		String result = subRight(special, 2);
		// giai nhat
		result = result + "," + subRight(first, 2);
		// giai nhi
		String[] arrSecond = second.split("-");
		for (int i = 0; i < arrSecond.length; i++) {
			result = result + "," + subRight(arrSecond[i], 2);
		}

		// giai ba
		String[] arrThird = third.split("-");
		for (int i = 0; i < arrThird.length; i++) {
			result = result + "," + subRight(arrThird[i], 2);
		}

		// giai bon
		String[] arrFourth = fourth.split("-");
		for (int i = 0; i < arrFourth.length; i++) {
			result = result + "," + subRight(arrFourth[i], 2);
		}

		// giai nam
		String[] arrFifth = fifth.split("-");
		for (int i = 0; i < arrFifth.length; i++) {
			result = result + "," + subRight(arrFifth[i], 2);
		}

		// giai sau
		String[] arrSixth = sixth.split("-");
		for (int i = 0; i < arrSixth.length; i++) {
			result = result + "," + subRight(arrSixth[i], 2);
		}

		// giai bay
		String[] arrSeventh = seventh.split("-");
		for (int i = 0; i < arrSeventh.length; i++) {
			result = result + "," + subRight(arrSeventh[i], 2);
		}

		// giai tam
		if (eighth != null && !"".equals(eighth)) {
			// giai tam
			String[] arrEighth = eighth.split("-");
			for (int i = 0; i < arrEighth.length; i++) {
				result = result + "," + subRight(arrEighth[i], 2);
			}
		}

		return result;
	}

	public static String subRight(String temp, int i) {
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

	public static List<String> subResult(String str) {
		List result = new ArrayList();
		String[] arr = str.split("-");
		for (int i = 0; i < arr.length; i++) {
			result.add(arr[i]);
		}

		return result;
	}

	public List<String> sub2Right(String special, String first, String second, String third, String fourth,
			String fifth, String sixth, String seventh, String eighth) {
		List result = new ArrayList();

		result.add(subRight(special, 2));

		result.add(subRight(first, 2));

		String[] arrSecond = second.split("-");
		for (int i = 0; i < arrSecond.length; i++) {
			result.add(subRight(arrSecond[i], 2));
		}

		String[] arrThird = third.split("-");
		for (int i = 0; i < arrThird.length; i++) {
			result.add(subRight(arrThird[i], 2));
		}

		String[] arrFourth = fourth.split("-");
		for (int i = 0; i < arrFourth.length; i++) {
			result.add(subRight(arrFourth[i], 2));
		}

		String[] arrFifth = fifth.split("-");
		for (int i = 0; i < arrFifth.length; i++) {
			result.add(subRight(arrFifth[i], 2));
		}

		String[] arrSixth = sixth.split("-");
		for (int i = 0; i < arrSixth.length; i++) {
			result.add(subRight(arrSixth[i], 2));
		}

		String[] arrSeventh = seventh.split("-");
		for (int i = 0; i < arrSeventh.length; i++) {
			result.add(subRight(arrSeventh[i], 2));
		}

		if (!"no".equals(eighth)) {
			String[] arrEighth = eighth.split("-");
			for (int i = 0; i < arrEighth.length; i++) {
				result.add(subRight(arrEighth[i], 2));
			}
		}
		return result;
	}

	public String concatResult(String special, String first, String second, String third, String fourth, String fifth,
			String sixth, String seventh, String eighth) {
		String result = special + first + second + third + fourth + fifth + sixth + seventh + eighth;
		result = result.replace("-", "");

		return result;
	}

	public List<String> getVitri(String str, String value) {
		List list = new ArrayList();
		String sub = "";
		for (int i = 0; i < str.length(); i++) {
			sub = str.substring(i, i + 1);
			if (sub.equals(value)) {
				list.add("" + i);
			}
		}
		return list;
	}

	public static String reverseNumber(String number) {
		if ((number == null) || (number.length() != 2)) {
			return "00";
		}
		return number.substring(1) + number.substring(0, 1);
	}

	public static void main(String[] arg) {
		List list = new ArrayList();
		list.add("4 4 7 7 7 6");
		list.add("0 1 3 9");

		for (int i = 0; i < list.size(); i++) {
			System.out.println((String) list.get(i));
		}
	}

	public int findCountDauOrDuoi(String str, String str1, boolean isDauOrDuoi) {
		int result = 0;
		String[] arr = str.split(",");
		String dau = str1.substring(0, 1);
		String duoi = str1.substring(1, 2);
		for (int i = 0; i < arr.length; i++) {
			if (isDauOrDuoi) {
				if (!arr[i].startsWith(dau)) {
					continue;
				}
				result++;
			} else {
				if (!arr[i].endsWith(duoi)) {
					continue;
				}
				result++;
			}

		}

		return result;
	}

	public static List<String> filterDauOrDuoi(Lottery lottery, boolean isDauOrDuoi) {
		List result = new ArrayList();
		String str = "";
		String str1 = "";
		String str2 = "";
		String strDacbiet = "";
		int dauDacbiet = 0;

		if (isDauOrDuoi) {
			for (int i = 0; i < 10; i++) {
				str1 = "";
				str2 = "";

				if ((lottery.getSpecial() != null) && (!"".equals(lottery.getSpecial()))) {
					str = subRight(lottery.getSpecial(), 2);
					if (str.startsWith(i + "")) {
						//str1 = "<span class=\"red\">" + str.substring(1, 2) + "</span> ";
						str1 = str.substring(1, 2) + ",";
						strDacbiet = str.substring(1, 2);
						dauDacbiet = i;
					}

				}

				if ((lottery.getFirst() != null) && (!"".equals(lottery.getFirst()))) {
					str = subRight(lottery.getFirst(), 2);
					if (str.startsWith(i + "")) {
						str2 = str.substring(1, 2);

						str1 = str1 + str2 + ",";
					}

				}

				String[] arrlottery = null;
				if ((lottery.getSecond() != null) && (!"".equals(lottery.getSecond()))) {
					arrlottery = lottery.getSecond().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
						str = subRight(arrlottery[j], 2);
						if (str.startsWith(i + "")) {
							str2 = str.substring(1, 2);

							str1 = str1 + str2 + ",";
						}

					}

				}

				if ((lottery.getThird() != null) && (!"".equals(lottery.getThird()))) {
					arrlottery = lottery.getThird().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
						str = subRight(arrlottery[j], 2);
						if (str.startsWith(i + "")) {
							str2 = str.substring(1, 2);

							str1 = str1 + str2 + ",";
						}

					}

				}

				if ((lottery.getFourth() != null) && (!"".equals(lottery.getFourth()))) {
					arrlottery = lottery.getFourth().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
						str = subRight(arrlottery[j], 2);
						if (str.startsWith(i + "")) {
							str2 = str.substring(1, 2);

							str1 = str1 + str2 + ",";
						}

					}

				}

				if ((lottery.getFifth() != null) && (!"".equals(lottery.getFifth()))) {
					arrlottery = lottery.getFifth().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
						str = subRight(arrlottery[j], 2);
						if (str.startsWith(i + "")) {
							str2 = str.substring(1, 2);

							str1 = str1 + str2 + ",";
						}

					}

				}

				if ((lottery.getSixth() != null) && (!"".equals(lottery.getSixth()))) {
					arrlottery = lottery.getSixth().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
						str = subRight(arrlottery[j], 2);
						if (str.startsWith(i + "")) {
							str2 = str.substring(1, 2);

							str1 = str1 + str2 + ",";
						}

					}

				}

				if ((lottery.getSeventh() != null) && (!"".equals(lottery.getSeventh()))) {
					arrlottery = lottery.getSeventh().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
						str = subRight(arrlottery[j], 2);
						if (str.startsWith(i + "")) {
							str2 = str.substring(1, 2);

							str1 = str1 + str2 + ",";
						}

					}

				}

				if ((lottery.getEighth() != null) && (!"".equals(lottery.getEighth()))) {
					arrlottery = lottery.getEighth().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
						str = subRight(arrlottery[j], 2);
						if (str.startsWith(i + "")) {
							if (str.length() >= 2) {
								str2 = str.substring(1, 2);
							} else {
								str2 = str;
							}
							str1 = str1 + str2 + ",";
						}
					}
				}

				if (!"".equals(str1)) {
					result.add(str1.substring(0, str1.length() - 1));
				} else {
					result.add("&nbsp;");
				}
			}
		} else {
			for (int i = 0; i < 10; i++) {
				str1 = "";
				str2 = "";

				if ((lottery.getSpecial() != null) && (!"".equals(lottery.getSpecial()))) {
					str = subRight(lottery.getSpecial(), 2);
					if (str.endsWith(i + "")) {
						//str1 = "<span class=\"red\">" + str.substring(0, 1) + "</span> ";
						str1 = str.substring(0, 1) + ",";
						strDacbiet = str.substring(0, 1);
						dauDacbiet = i;
					}

				}

				if ((lottery.getFirst() != null) && (!"".equals(lottery.getFirst()))) {
					str = subRight(lottery.getFirst(), 2);
					if (str.endsWith(i + "")) {
						str2 = str.substring(0, 1);

						str1 = str1 + str2 + ",";
					}

				}

				String[] arrlottery = null;
				if ((lottery.getSecond() != null) && (!"".equals(lottery.getSecond()))) {
					arrlottery = lottery.getSecond().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
						str = subRight(arrlottery[j], 2);
						if (str.endsWith(i + "")) {
							str2 = str.substring(0, 1);

							str1 = str1 + str2 + ",";
						}

					}

				}

				if ((lottery.getThird() != null) && (!"".equals(lottery.getThird()))) {
					arrlottery = lottery.getThird().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
						str = subRight(arrlottery[j], 2);
						if (str.endsWith(i + "")) {
							str2 = str.substring(0, 1);

							str1 = str1 + str2 + ",";
						}

					}

				}

				if ((lottery.getFourth() != null) && (!"".equals(lottery.getFourth()))) {
					arrlottery = lottery.getFourth().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
						str = subRight(arrlottery[j], 2);
						if (str.endsWith(i + "")) {
							str2 = str.substring(0, 1);

							str1 = str1 + str2 + ",";
						}

					}

				}

				if ((lottery.getFifth() != null) && (!"".equals(lottery.getFifth()))) {
					arrlottery = lottery.getFifth().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
						str = subRight(arrlottery[j], 2);
						if (str.endsWith(i + "")) {
							str2 = str.substring(0, 1);

							str1 = str1 + str2 + ",";
						}

					}

				}

				if ((lottery.getSixth() != null) && (!"".equals(lottery.getSixth()))) {
					arrlottery = lottery.getSixth().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
						str = subRight(arrlottery[j], 2);
						if (str.endsWith(i + "")) {
							str2 = str.substring(0, 1);

							str1 = str1 + str2 + ",";
						}

					}

				}

				if ((lottery.getSeventh() != null) && (!"".equals(lottery.getSeventh()))) {
					arrlottery = lottery.getSeventh().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
						str = subRight(arrlottery[j], 2);
						if (str.endsWith(i + "")) {
							str2 = str.substring(0, 1);

							str1 = str1 + str2 + ",";
						}

					}

				}

				if ((lottery.getEighth() != null) && (!"".equals(lottery.getEighth()))) {
					arrlottery = lottery.getEighth().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
						str = subRight(arrlottery[j], 2);
						if (str.endsWith(i + "")) {
							str2 = str.substring(0, 1);

							str1 = str1 + str2 + ",";
						}
					}
				}

				if (!"".equals(str1)) {
					result.add(str1.substring(0, str1.length() - 1));
				} else {
					result.add("&nbsp;");
				}
			}

		}
		// return result;
		return shortAscOrDesc(result, true, strDacbiet, dauDacbiet);
	}

	public static String createStringLink(String string, int id) {
		String str = UTF8Tool.coDau2KoDau(string).toLowerCase();
		String[] arrStr = str.split(" ");
		if (arrStr.length > 0) {
			String str1 = "";
			for (int i = 0; i < arrStr.length; i++) {
				if (!"".equals(arrStr[i])) {
					str1 = str1 + arrStr[i] + "-";
				}
			}
			return str1.substring(0, str1.length() - 1) + "-" + id;
		}
		return str;
	}

	public static List<String> shortAscOrDesc(List<String> list, boolean ascOrDesc, String strDacbiet, int dauDacbiet) {
		List list1 = null;
		if ((list == null) || (list.isEmpty())) {
			return null;
		}
		String string = "";
		String str = "";
		String[] arrStr = null;
		int tg1 = 0;
		int tg2 = 0;
		if (ascOrDesc) {
			for (int i = 0; i < list.size(); i++) {
				string = (String) list.get(i);
				if (!string.contains(" ")) {
					if (list1 == null) {
						list1 = new ArrayList();
					}
					list1.add(string);
				} else {
					arrStr = string.split(" ");
					for (int j = 0; j < arrStr.length - 1; j++) {
						for (int k = j + 1; k < arrStr.length; k++) {

							if (!CommonUtil.isEmptyString(arrStr[j]) && !CommonUtil.isEmptyString(arrStr[k])) {
								tg1 = Integer.parseInt(arrStr[j]);
								tg2 = Integer.parseInt(arrStr[k]);
								if (tg1 > tg2) {
									arrStr[j] = ("" + tg2);
									arrStr[k] = ("" + tg1);
								}
							}

						}
					}

					if (list1 == null) {
						list1 = new ArrayList();
					}
					str = "";
					for (int j = 0; j < arrStr.length; j++) {
						str = str + arrStr[j] + " ";
					}
					list1.add(str.substring(0, str.length() - 1));
				}
			}
		} else {
			for (int i = 0; i < list.size(); i++) {
				string = (String) list.get(i);
				if (!string.contains(" ")) {
					if (list1 == null) {
						list1 = new ArrayList();
					}
					list1.add(string);
				} else {
					arrStr = string.split(" ");
					for (int j = 0; j < arrStr.length - 1; j++) {
						for (int k = j + 1; k < arrStr.length; k++) {
							tg1 = Integer.parseInt(arrStr[j]);
							tg2 = Integer.parseInt(arrStr[k]);
							if (tg1 < tg2) {
								arrStr[j] = ("" + tg2);
								arrStr[k] = ("" + tg1);
							}
						}
					}

					if (list1 == null) {
						list1 = new ArrayList();
					}
					str = "";
					for (int j = 0; j < arrStr.length; j++) {
						str = str + arrStr[j] + " ";
					}
					list1.add(str.substring(0, str.length() - 1));
				}
			}
		}

		if (list1 != null) {
			for (int i = 0; i < list1.size(); i++) {
				if (i == dauDacbiet) {
					if (strDacbiet != null && !"".equals(strDacbiet)) {
						str = strDacbiet;
						str = "<span style=\"color: #FF3322\">" + str + "</span>";
						list1.set(i, ((String) list1.get(i)).replaceFirst(strDacbiet, str));
						break;
					}
				}
			}
		}

		return list1;
	}

	public Lottery replaceResultLottery(Lottery lottery, String loto) {
		Lottery l = lottery;
		String stempl = "<span class=\"PTLT_text_Trung\">";
		String etempl = "</span>";
		String templ = stempl + loto + etempl;
		boolean isLoto = false;

		String giai = l.getSpecial();

		String str = subRight(giai, 2);
		if (str.equals(loto)) {
			giai = giai.substring(0, giai.length() - 2) + templ;
			isLoto = true;
		}
		l.setSpecial(giai);

		giai = l.getFirst();

		if (!isLoto) {
			str = subRight(giai, 2);
			if (str.equals(loto)) {
				giai = giai.substring(0, giai.length() - 2) + templ;
				isLoto = true;
			}
		}
		l.setFirst(giai);

		for (StringTokenizer stringTokenizer = new StringTokenizer(l.getSecond(), "-"); stringTokenizer
				.hasMoreTokens();) {
			giai = stringTokenizer.nextToken();
			String giai1 = giai;

			if (!isLoto) {
				str = subRight(giai, 2);
				if (str.equals(loto)) {
					giai = giai.substring(0, giai.length() - 2) + templ;
					isLoto = true;
					l.setSecond(l.getSecond().replace(giai1, giai));
				}
			}
		}

		for (StringTokenizer stringTokenizer = new StringTokenizer(l.getThird(), "-"); stringTokenizer
				.hasMoreTokens();) {
			giai = stringTokenizer.nextToken();
			String giai1 = giai;

			if (!isLoto) {
				str = subRight(giai, 2);
				if (str.equals(loto)) {
					giai = giai.substring(0, giai.length() - 2) + templ;
					isLoto = true;
					l.setThird(l.getThird().replace(giai1, giai));
				}
			}
		}

		for (StringTokenizer stringTokenizer = new StringTokenizer(l.getFourth(), "-"); stringTokenizer
				.hasMoreTokens();) {
			giai = stringTokenizer.nextToken();
			String giai1 = giai;

			if (!isLoto) {
				str = subRight(giai, 2);
				if (str.equals(loto)) {
					giai = giai.substring(0, giai.length() - 2) + templ;
					isLoto = true;
					l.setFourth(l.getFourth().replace(giai1, giai));
				}
			}

		}

		for (StringTokenizer stringTokenizer = new StringTokenizer(l.getFifth(), "-"); stringTokenizer
				.hasMoreTokens();) {
			giai = stringTokenizer.nextToken();
			String giai1 = giai;

			if (!isLoto) {
				str = subRight(giai, 2);
				if (str.equals(loto)) {
					giai = giai.substring(0, giai.length() - 2) + templ;
					isLoto = true;
					l.setFifth(l.getFifth().replace(giai1, giai));
				}
			}
		}

		for (StringTokenizer stringTokenizer = new StringTokenizer(l.getSixth(), "-"); stringTokenizer
				.hasMoreTokens();) {
			giai = stringTokenizer.nextToken();
			String giai1 = giai;

			if (!isLoto) {
				str = subRight(giai, 2);
				if (str.equals(loto)) {
					giai = giai.substring(0, giai.length() - 2) + templ;
					isLoto = true;
					l.setSixth(l.getSixth().replace(giai1, giai));
				}
			}

		}

		for (StringTokenizer stringTokenizer = new StringTokenizer(l.getSeventh(), "-"); stringTokenizer
				.hasMoreTokens();) {
			giai = stringTokenizer.nextToken();
			String giai1 = giai;

			if (!isLoto) {
				str = subRight(giai, 2);
				if (str.equals(loto)) {
					giai = giai.substring(0, giai.length() - 2) + templ;
					isLoto = true;
					l.setSeventh(l.getSeventh().replace(giai1, giai));
				}
			}

		}

		return l;
	}

	// public static List<List<Thongke>> filterDauDuoi(Lottery lottery, boolean
	// isDauOrDuoi) {
	private static List<List<Thongke>> filterDauDuoi(Lottery lottery, boolean isDauOrDuoi) {
		List<List<Thongke>> results = new ArrayList<>();

		if (isDauOrDuoi) {
			for (int i = 0; i < 10; i++) {
				String str = "";
				HashMap<String, Thongke> thongkes = new HashMap<>();
				thongkes.clear();

				if ((lottery.getSpecial() != null) && (!"".equals(lottery.getSpecial()))) {
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

				if ((lottery.getFirst() != null) && (!"".equals(lottery.getFirst()))) {
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

				if ((lottery.getThird() != null) && (!"".equals(lottery.getThird()))) {
					arrlottery = lottery.getThird().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
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

				if ((lottery.getFourth() != null) && (!"".equals(lottery.getFourth()))) {
					arrlottery = lottery.getFourth().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
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

				if ((lottery.getFifth() != null) && (!"".equals(lottery.getFifth()))) {
					arrlottery = lottery.getFifth().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
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

				if ((lottery.getSixth() != null) && (!"".equals(lottery.getSixth()))) {
					arrlottery = lottery.getSixth().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
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

				if ((lottery.getSeventh() != null) && (!"".equals(lottery.getSeventh()))) {
					arrlottery = lottery.getSeventh().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
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

				if ((lottery.getEighth() != null) && (!"".equals(lottery.getEighth()))) {
					arrlottery = lottery.getEighth().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
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

				List<Thongke> temp = new ArrayList<>(thongkes.values());
				Collections.sort(temp);
				results.add(temp);
			}
		} else {
			for (int i = 0; i < 10; i++) {
				String str = "";
				HashMap<String, Thongke> thongkes = new HashMap<>();
				thongkes.clear();

				if ((lottery.getSpecial() != null) && (!"".equals(lottery.getSpecial()))) {
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

				if ((lottery.getFirst() != null) && (!"".equals(lottery.getFirst()))) {
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

				if ((lottery.getThird() != null) && (!"".equals(lottery.getThird()))) {
					arrlottery = lottery.getThird().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
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

				if ((lottery.getFourth() != null) && (!"".equals(lottery.getFourth()))) {
					arrlottery = lottery.getFourth().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
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

				if ((lottery.getFifth() != null) && (!"".equals(lottery.getFifth()))) {
					arrlottery = lottery.getFifth().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
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

				if ((lottery.getSixth() != null) && (!"".equals(lottery.getSixth()))) {
					arrlottery = lottery.getSixth().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
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

				if ((lottery.getSeventh() != null) && (!"".equals(lottery.getSeventh()))) {
					arrlottery = lottery.getSeventh().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
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

				if ((lottery.getEighth() != null) && (!"".equals(lottery.getEighth()))) {
					arrlottery = lottery.getEighth().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
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

				List<Thongke> temp = new ArrayList<>(thongkes.values());
				Collections.sort(temp);
				results.add(temp);
			}

		}

		return results;
	}

	// truc tiep
	public static List<String> filterDauOrDuoiTrucTiep(Lottery lottery, boolean isDauOrDuoi) {
		List result = new ArrayList();
		String str = "";
		String str1 = "";
		String str2 = "";
		String strDacbiet = "";
		int dauDacbiet = 0;

		if (isDauOrDuoi) {
			for (int i = 0; i < 10; i++) {
				str1 = "";
				str2 = "";

				if ((lottery.getSpecial() != null) && (!"".equals(lottery.getSpecial()))
						&& !lottery.getSpecial().contains("<img")) {
					str = subRight(lottery.getSpecial(), 2);
					if (str.startsWith(i + "")) {
						str1 = str.substring(1, 2) + ",";
						//str1 = "<span class=\"red\">" + str.substring(1, 2) + "</span> ";
						strDacbiet = str.substring(1, 2);
						dauDacbiet = i;
					}

				}

				if ((lottery.getFirst() != null) && (!"".equals(lottery.getFirst()))
						&& !lottery.getFirst().contains("<img")) {
					str = subRight(lottery.getFirst(), 2);
					if (str.startsWith(i + "")) {
						str2 = str.substring(1, 2);

						str1 = str1 + str2 + ",";
					}

				}

				String[] arrlottery = null;
				if ((lottery.getSecond() != null) && (!"".equals(lottery.getSecond()))) {
					arrlottery = lottery.getSecond().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
						if (!arrlottery[j].startsWith("<img")) {
							str = subRight(arrlottery[j], 2);
							if (str.startsWith(i + "")) {
								str2 = str.substring(1, 2);

								str1 = str1 + str2 + ",";
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
								str2 = str.substring(1, 2);

								str1 = str1 + str2 + ",";
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
								str2 = str.substring(1, 2);

								str1 = str1 + str2 + ",";
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
								str2 = str.substring(1, 2);

								str1 = str1 + str2 + ",";
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
								str2 = str.substring(1, 2);

								str1 = str1 + str2 + ",";
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
								str2 = str.substring(1, 2);

								str1 = str1 + str2 + ",";
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
								if (str.length() >= 2) {
									str2 = str.substring(1, 2);
								} else {
									str2 = str;
								}
								str1 = str1 + str2 + ",";
							}
						}
					}
				}

				if (!"".equals(str1)) {
					result.add(str1.substring(0, str1.length() - 1));
				} else {
					result.add("&nbsp;");
				}
			}
		} else {
			for (int i = 0; i < 10; i++) {
				str1 = "";
				str2 = "";

				if ((lottery.getSpecial() != null) && (!"".equals(lottery.getSpecial()))
						&& !lottery.getSpecial().contains("<img")) {
					str = subRight(lottery.getSpecial(), 2);
					if (str.endsWith(i + "")) {
						//str1 = "<span class=\"red\">" + str.substring(0, 1) + "</span> ";
						str1 = str.substring(0, 1) + ",";
						strDacbiet = str.substring(0, 1);
						dauDacbiet = i;
					}

				}

				if ((lottery.getFirst() != null) && (!"".equals(lottery.getFirst()))
						&& !lottery.getFirst().contains("<img")) {
					str = subRight(lottery.getFirst(), 2);
					if (str.endsWith(i + "")) {
						str2 = str.substring(0, 1);

						str1 = str1 + str2 + ",";
					}

				}

				String[] arrlottery = null;
				if ((lottery.getSecond() != null) && (!"".equals(lottery.getSecond()))) {
					arrlottery = lottery.getSecond().split("-");
					for (int j = 0; j < arrlottery.length; j++) {
						if (!arrlottery[j].startsWith("<img")) {
							str = subRight(arrlottery[j], 2);
							if (str.endsWith(i + "")) {
								str2 = str.substring(0, 1);

								str1 = str1 + str2 + ",";
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
								str2 = str.substring(0, 1);

								str1 = str1 + str2 + ",";
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
								str2 = str.substring(0, 1);

								str1 = str1 + str2 + ",";
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
								str2 = str.substring(0, 1);

								str1 = str1 + str2 + ",";
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
								str2 = str.substring(0, 1);

								str1 = str1 + str2 + ",";
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
								str2 = str.substring(0, 1);

								str1 = str1 + str2 + ",";
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
								str2 = str.substring(0, 1);

								str1 = str1 + str2 + ",";
							}
						}
					}
				}

				if (!"".equals(str1)) {
					result.add(str1.substring(0, str1.length() - 1));
				} else {
					result.add("&nbsp;");
				}
			}

		}
		// return result;
		return shortAscOrDesc(result, true, strDacbiet, dauDacbiet);
	}
}
