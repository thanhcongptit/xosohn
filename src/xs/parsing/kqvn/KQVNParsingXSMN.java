/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xs.parsing.kqvn;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.utils.Logger;

import inet.bean.LotteryResult;
import inet.listener.XSCache;
import inet.model.LotteryResultDAO;
import inet.util.DateUtil;
import inet.util.XosoUtil;

/**
 *
 * @author thangdm
 */
public class KQVNParsingXSMN {

	static Logger logger = new Logger("KQVNParsingXSMN");

	public static void main(String[] args) throws IOException {
		// String url = "http://ketqua.vn/truc-tiep-xo-so-mien-nam";
		String url = "http://ketqua.vn/truc-tiep-xo-so-mien-trung";
		antrom(url);
	}

	public static void antrom(String url) throws IOException {
		System.out.println("url=" + url);
		Document doc = null;

		try {
			doc = Jsoup.connect(url).data("query", "Java").userAgent("Mozilla").cookie("auth", "token").timeout(5000)
					.post();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		if (doc == null) {
			return;
		}

		Elements tables = doc.select("div.table-tructiep table");
		String openDate = "";
		try {
			String title = doc.select("div.caption-tructiep").get(0).text();
			openDate = parseDate(title.split("-")[0].trim());
			System.out.println("openDate=" + openDate);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		if (openDate == null || "".equals(openDate)) {
			System.out.println("Khong lay duoc ngay hien tai, yeu cau xu ly gap!!!");
		} else {
			Elements trs, tds, ths;
			int i = 0;
			int checkGiai = 0;
			HashMap<Integer, LotteryResult> hmLottery = new HashMap<>();
			String symbol;
			for (Element table : tables) {
				trs = table.select("tr");
				if (trs.size() == 10) {
					checkGiai = 0;
					for (Element tr : trs) {
						checkGiai++;
						tds = tr.select("td");
						ths = tr.select("th");
						// show th - danh sach cac tinh
						i = 0;
						for (Element th : ths) {
							i++;
							if (i >= 2) {
								if (hmLottery.get(i) == null) {
									hmLottery.put(i, new LotteryResult());
								}
								symbol = XosoUtil.hmXosoCityCode.get(th.text().trim());
								System.out.print("symbol=" + symbol + " | ");
								hmLottery.get(i).setCode("XS" + symbol);
								hmLottery.get(i).setSymbol(symbol);
								hmLottery.get(i).setPrice(new BigDecimal(5000));
								hmLottery.get(i).setStatus(1);
								hmLottery.get(i).setUserName("kqvn");
								hmLottery.get(i).setOpenDate(openDate);
							}
						}
						// System.out.println("");
						// show danh sach ket qua
						// System.out.println("checkGiai="+checkGiai);
						i = 0;
						for (Element td : tds) {
							i++;
							// System.out.print("i="+i+"||");
							if (i >= 2) {
								// System.out.print("td text="+td.text()+" | ");
								if (checkGiai == 2) {
									// giai tam
									hmLottery.get(i).setEighth(getListKQ(td.text()));
								} else if (checkGiai == 3) {
									// giai 7
									hmLottery.get(i).setSeventh(getListKQ(td.text()));
								} else if (checkGiai == 4) {
									// giai 6
									hmLottery.get(i).setSixth(getListKQ(td.text()));
								} else if (checkGiai == 5) {
									// giai 5
									hmLottery.get(i).setFifth(getListKQ(td.text()));
								} else if (checkGiai == 6) {
									// giai 4
									hmLottery.get(i).setFourth(getListKQ(td.text()));
								} else if (checkGiai == 7) {
									// giai 3
									hmLottery.get(i).setThird(getListKQ(td.text()));
								} else if (checkGiai == 8) {
									// giai nhi
									hmLottery.get(i).setSecond(getListKQ(td.text()));
								} else if (checkGiai == 9) {
									// giai nhat
									hmLottery.get(i).setFirst(getListKQ(td.text()));
								} else if (checkGiai == 10) {
									// giai db
									hmLottery.get(i).setSpecial(getListKQ(td.text()));
									System.out.println("hmLottery.get(i).setSpecial=" + hmLottery.get(i).getSpecial());
								}
							}
						}
						// System.out.println("");
					}
				}
				break;
			}
			// System.out.println("hmLottery.size="+hmLottery.size());
			LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
			LotteryResult lotteryLive = null;
			if (!hmLottery.isEmpty())
				for (Map.Entry<Integer, LotteryResult> entry : hmLottery.entrySet()) {
					// kiem tra da co trong DB chua
					// khong co moi insert
					lotteryLive = entry.getValue();
					LotteryResult tmp = null;
					// tmp = lotteryResultDAO.findResultByCodeInDay(lotteryLive.getCode(),
					// lotteryLive.getOpenDate());
					tmp = XSCache.getLotteryByCodeAndOpenDate(lotteryLive.getCode(), lotteryLive.getOpenDate());
					if (tmp == null && !XSCache.checkInsert(lotteryLive.getCode(), lotteryLive.getOpenDate())) {
						// LotteryResult lr = lotteryResultDAO.createWithOpenDate77(lotteryLive);
						// if(lr != null){
						// XSCache.addLottery(lr);
						// XSCache.saveInsert(lotteryLive.getCode(), lotteryLive.getOpenDate());
						// }
						// System.out.println("create="+(lr != null));
					} else {
						boolean checkUpdate = false;
						// giai dac biet
						if (tmp.getSpecial() == null)
							tmp.setSpecial("");
						if (lotteryLive.getSpecial() != null && !"".equals(lotteryLive.getSpecial())
								&& lotteryLive.getSpecial().length() > tmp.getSpecial().length()) {
							tmp.setSpecial(lotteryLive.getSpecial());
							checkUpdate = true;
						}
						// giai nhat
						if (tmp.getFirst() == null)
							tmp.setFirst("");
						if (lotteryLive.getFirst() != null && !"".equals(lotteryLive.getFirst())
								&& lotteryLive.getFirst().length() > tmp.getFirst().length()) {
							tmp.setFirst(lotteryLive.getFirst());
							checkUpdate = true;
						}
						// giai nhi
						if (tmp.getSecond() == null)
							tmp.setSecond("");
						if (lotteryLive.getSecond() != null && !"".equals(lotteryLive.getSecond())
								&& lotteryLive.getSecond().length() > tmp.getSecond().length()) {
							tmp.setSecond(lotteryLive.getSecond());
							checkUpdate = true;
						}
						// giai 3
						if (tmp.getThird() == null)
							tmp.setThird("");
						if (lotteryLive.getThird() != null && !"".equals(lotteryLive.getThird())
								&& lotteryLive.getThird().length() > tmp.getThird().length()) {
							tmp.setThird(lotteryLive.getThird());
							checkUpdate = true;
						}
						// giai 4
						if (tmp.getFourth() == null)
							tmp.setFourth("");
						if (lotteryLive.getFourth() != null && !"".equals(lotteryLive.getFourth())
								&& lotteryLive.getFourth().length() > tmp.getFourth().length()) {
							tmp.setFourth(lotteryLive.getFourth());
							checkUpdate = true;
						}
						// giai 5
						if (tmp.getFifth() == null)
							tmp.setFifth("");
						if (lotteryLive.getFifth() != null && !"".equals(lotteryLive.getFifth())
								&& lotteryLive.getFifth().length() > tmp.getFifth().length()) {
							tmp.setFifth(lotteryLive.getFifth());
							checkUpdate = true;
						}
						// giai 6
						if (tmp.getSixth() == null)
							tmp.setSixth("");
						if (lotteryLive.getSixth() != null && !"".equals(lotteryLive.getSixth())
								&& lotteryLive.getSixth().length() > tmp.getSixth().length()) {
							tmp.setSixth(lotteryLive.getSixth());
							checkUpdate = true;
						}
						// giai 7
						if (tmp.getSeventh() == null)
							tmp.setSeventh("");
						if (lotteryLive.getSeventh() != null && !"".equals(lotteryLive.getSeventh())
								&& lotteryLive.getSeventh().length() > tmp.getSeventh().length()) {
							tmp.setSeventh(lotteryLive.getSeventh());
							checkUpdate = true;
						}
						// giai 8
						if (tmp.getEighth() == null)
							tmp.setEighth("");
						if (lotteryLive.getEighth() != null && !"".equals(lotteryLive.getEighth())
								&& lotteryLive.getEighth().length() > tmp.getEighth().length()) {
							tmp.setEighth(lotteryLive.getEighth());
							checkUpdate = true;
						}
						if (checkUpdate) {
							tmp.setLastUpdated(DateUtil.date2String(new Date(), "yyyy-MM-dd hh:mm:ss" ));
							System.out.println("update=" + lotteryResultDAO.updateLiveXS(tmp));
						} else {
							System.out.println("Khong can update, du lieu van nhu cu");
						}
					}
				}
		}
	}

	public static String parseDate(String title) {
		String[] split = title.split(" ");
		if (split != null && split.length > 0)
			return split[split.length - 1];
		return "";
	}

	public static String getListKQ(String text) {
		String[] split = text.split(" ");
		String rs = "";
		int i = 0;
		if (split != null && split.length > 0)
			for (String s : split) {
				if (isNumber(s)) {
					if (i == 0) {
						rs += s.trim();
					} else {
						rs += "-" + s.trim();
					}
					i++;
				}
			}
		return rs;
	}

	public static boolean isNumber(String text) {
		try {
			int i = Integer.parseInt(text);
			return true;
		} catch (Exception ex) {
		}
		return false;
	}
}
