/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xs.parsing.kqvn;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.utils.Logger;

import inet.bean.LotteryResult;
import inet.listener.XSCache;
import inet.model.LotteryResultDAO;
import inet.util.DateUtil;

/**
 *
 * @author thangdm
 */
public class KQVNParsingXS {

	static Logger logger = new Logger("KQVNParsingXS");

	public static void main(String[] args) throws IOException {
		String url = "http://ketqua.vn/truc-tiep-xo-so-mien-bac";
		antrom(url);
	}

	public static void antrom(String url) throws IOException {
		System.out.println("url=" + url);
		// Document doc = Jsoup.connect(url)
		// .data("query", "Java")
		// .userAgent("Mozilla")
		// .cookie("auth", "token")
		// .timeout(5000)
		// .post();

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

		Elements tables = doc.select("div.xo_so_mien div.result-box table");
		String openDate = "";
		Elements trs;
		int i = 1;
		LotteryResult lottery = null;
		for (Element table : tables) {
			if (table.select("tr").size() < 6)
				continue;
			openDate = parseDate(table.previousElementSibling().select("h4.mb_date_info").get(0).text());
			trs = table.select("tr");
			i = 1;
			lottery = new LotteryResult();
			// lay ngay quay thuong
			System.out.println("Ngay: " + openDate);
			lottery.setOpenDate(openDate);
			for (Element tr : trs) {
				i++;
				System.out.println("text=" + tr.text());
				if (i == 2) {
					// giai dac biet
					lottery.setSpecial(getListKQ(tr.text()));
					System.out.println("dacbiet=" + getListKQ(lottery.getSpecial()));
				} else if (i == 3) {
					// giai nhat
					lottery.setFirst(getListKQ(tr.text()));
				} else if (i == 4) {
					// giai nhi
					lottery.setSecond(getListKQ(tr.text()));
				} else if (i == 5 || i == 6) {
					// giai ba
					if (i == 5) {
						lottery.setThird(getListKQ(tr.text()));
					} else {
						lottery.setThird(lottery.getThird() + "-" + getListKQ(tr.text()));
					}
				} else if (i == 7) {
					// giai tu
					lottery.setFourth(getListKQ(tr.text()));
				} else if (i == 8 || i == 9) {
					// giai nam
					if (i == 8) {
						lottery.setFifth(getListKQ(tr.text()));
					} else {
						lottery.setFifth(lottery.getFifth() + "-" + getListKQ(tr.text()));
					}
				} else if (i == 10) {
					// giai sau
					lottery.setSixth(getListKQ(tr.text()));
				} else if (i == 11) {
					// giai bay
					lottery.setSeventh(getListKQ(tr.text()));
				}
			}
			lottery.setCode("XSTD");
			lottery.setSymbol("TD");
			lottery.setPrice(new BigDecimal(5000));
			lottery.setStatus(1);
			lottery.setUserName("kqvn");
			break;
		}

		if (lottery != null) {
			LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
			// kiem tra da co trong DB chua
			// khong co moi insert
			LotteryResult tmp = null;
			// tmp = lotteryResultDAO.findResultByCodeInDay("XSTD",
			// lottery.getOpenDate());
			tmp = XSCache.getLotteryByCodeAndOpenDate("XSTD", lottery.getOpenDate());
			if (tmp == null && !XSCache.checkInsert("XSTD", lottery.getOpenDate())) {
				LotteryResult lr = lotteryResultDAO.createWithOpenDate77(lottery);
				if (lr != null) {
					XSCache.addLottery(lr);
					XSCache.saveInsert("XSTD", lottery.getOpenDate());
				}
				System.out.println("create=" + (lr != null));
			} else {
				boolean checkUpdate = false;
				// giai dac biet
				if (tmp.getSpecial() == null)
					tmp.setSpecial("");
				if (lottery.getSpecial() != null && !"".equals(lottery.getSpecial())
						&& lottery.getSpecial().length() > tmp.getSpecial().length()) {
					tmp.setSpecial(lottery.getSpecial());
					checkUpdate = true;
				}
				// giai nhat
				if (tmp.getFirst() == null)
					tmp.setFirst("");
				if (lottery.getFirst() != null && !"".equals(lottery.getFirst())
						&& lottery.getFirst().length() > tmp.getFirst().length()) {
					tmp.setFirst(lottery.getFirst());
					checkUpdate = true;
				}
				// giai nhi
				if (tmp.getSecond() == null)
					tmp.setSecond("");
				if (lottery.getSecond() != null && !"".equals(lottery.getSecond())
						&& lottery.getSecond().length() > tmp.getSecond().length()) {
					tmp.setSecond(lottery.getSecond());
					checkUpdate = true;
				}
				// giai 3
				if (tmp.getThird() == null)
					tmp.setThird("");
				if (lottery.getThird() != null && !"".equals(lottery.getThird())
						&& lottery.getThird().length() > tmp.getThird().length()) {
					tmp.setThird(lottery.getThird());
					checkUpdate = true;
				}
				// giai 4
				if (tmp.getFourth() == null)
					tmp.setFourth("");
				if (lottery.getFourth() != null && !"".equals(lottery.getFourth())
						&& lottery.getFourth().length() > tmp.getFourth().length()) {
					tmp.setFourth(lottery.getFourth());
					checkUpdate = true;
				}
				// giai 5
				if (tmp.getFifth() == null)
					tmp.setFifth("");
				if (lottery.getFifth() != null && !"".equals(lottery.getFifth())
						&& lottery.getFifth().length() > tmp.getFifth().length()) {
					tmp.setFifth(lottery.getFifth());
					checkUpdate = true;
				}
				// giai 6
				if (tmp.getSixth() == null)
					tmp.setSixth("");
				if (lottery.getSixth() != null && !"".equals(lottery.getSixth())
						&& lottery.getSixth().length() > tmp.getSixth().length()) {
					tmp.setSixth(lottery.getSixth());
					checkUpdate = true;
				}
				// giai 7
				if (tmp.getSeventh() == null)
					tmp.setSeventh("");
				if (lottery.getSeventh() != null && !"".equals(lottery.getSeventh())
						&& lottery.getSeventh().length() > tmp.getSeventh().length()) {
					tmp.setSeventh(lottery.getSeventh());
					checkUpdate = true;
				}
				// giai 8
				if (tmp.getEighth() == null)
					tmp.setEighth("");
				if (lottery.getEighth() != null && !"".equals(lottery.getEighth())
						&& lottery.getEighth().length() > tmp.getEighth().length()) {
					tmp.setEighth(lottery.getEighth());
					checkUpdate = true;
				}
				if (checkUpdate) {
					tmp.setLastUpdated(DateUtil.date2String(new Date(), "yyyy-MM-dd hh:mm:ss" ));
					System.out.println("update=" + lotteryResultDAO.updateLiveXS(tmp));
				}else {
					System.out.println("Khong can update, du lieu van nhu cu");
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
