package inet.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.soicaupro.thongkebacnho.CommonUtil;

import inet.bean.CauDetailBean;
import inet.bean.Lottery;
import inet.bean.LotteryResult;
import inet.bean.SoicauBean;
import inet.bean.SoicauDetailBean;
import inet.model.LotteryResultDAO;
import inet.request.LotteryRequest;
import inet.util.LotoUtils;
import inet.util.RequestUtil;
import inet.util.StringConvert;
import inet.util.StringPro;
import service.SoicauService;

public class SoiCauChiTietMTController extends BaseController {
	private SoicauService soicauService = new SoicauService();

	public SoiCauChiTietMTController() {
	}

	@Override
	protected void loadBase() {
		super.loadBase(); // To change body of generated methods, choose Tools |

	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mod = super.handleRequestInternal(request, response);

		String ngay = RequestUtil.getString(request, "openDate", "");
		int indeCau = RequestUtil.getInt(request, "indexCau", 0);
		int indexList = RequestUtil.getInt(request, "indexList", 0);
		int lon = RequestUtil.getInt(request, "lon", 1);
		int nhay = RequestUtil.getInt(request, "nhay", 1);
		int ca_cap = RequestUtil.getInt(request, "ca_cap", 0);
		String code = RequestUtil.getString(request, "code", "");

		int exactlimit = 0;
		int db = 0;
		int limit = 0;

		String key = code.toUpperCase() + ";" + ngay + ";" + exactlimit + ";" + nhay + ";" + lon + ";" + db + ";"
				+ ca_cap;
		SoicauBean soicauBean = CommonUtil.getSoicauValueByKey(key);
		if (soicauBean == null) {
			LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
			List<LotteryResult> lotteryResults = lotteryResultDAO.findResultByCode(code, 2, new BigDecimal("1000000"));
			if (lotteryResults.size() > 0) {
				soicauBean = soicauService.getSoiCauLotoMT(exactlimit, limit, nhay, lon, db, lotteryResults);
				CommonUtil.putSoicauValue(key, soicauBean);
			}
		}

		LotteryRequest lotteryRequest = new LotteryRequest(); 
		List<Lottery> lotteries = null;
		List<List<String>> listLotterysDuoiMB = null;
		List<List<String>> listLotterysDauMB = null;
		
		if (null != soicauBean) {
			SoicauDetailBean soicauDetailBeans = soicauBean.getSoicauDetailBeans().get(indexList);
			CauDetailBean cauDetailBean = soicauDetailBeans.getDetailCauList().get(indeCau);

			List<LotteryResult> lotteryResults = new LotteryResultDAO().findResultByCode(code.toUpperCase(),
					soicauBean.getTongsoNgay(), new BigDecimal("1000000"));

			lotteries = lotteryRequest.parserLotteryResult(code.toUpperCase(),
					lotteryResults.get(lotteryResults.size() - 1).getOpenDate(), ngay);
			listLotterysDuoiMB = new ArrayList<>();
			listLotterysDauMB = new ArrayList<>();

			for (Lottery lottery : lotteries) {
				List<String> listLotteryDuoiMB = StringPro.filterDauOrDuoi(lottery, true);
				listLotterysDuoiMB.add(listLotteryDuoiMB);

				List<String> listLotteryDauMB = StringPro.filterDauOrDuoi(lottery, false);
				listLotterysDauMB.add(listLotteryDauMB);

			}

			makeYellowColor(cauDetailBean, lotteries, db, lon);
			makeBlueColor(cauDetailBean, lotteries);
		}
		mod.addObject("lotteryResults", lotteries);
		mod.addObject("duoiResults", listLotterysDuoiMB);
		mod.addObject("dauResults", listLotterysDauMB);
		mod.setViewName("/ajax/soicauchitiet_mt");

		return mod;
	}

	private void makeYellowColor(CauDetailBean cauDetailBean, List<Lottery> lotteryResults, int isDb, int isLon) {
		int vitri[] = cauDetailBean.getVitri();
		int size = lotteryResults.size();
		String resultStart = StringConvert.stringConcatLottery(lotteryResults.get(size - 1));
		String caulo = resultStart.substring(vitri[0], (vitri[0] + 1))
				+ resultStart.substring(vitri[1], (vitri[1] + 1));
		for (int i = size - 2; i >= 0; i--) {
			Lottery lotteryResult = lotteryResults.get(i);

			lotteryResult.setSpecial(handleAward(caulo, lotteryResult.getSpecial(), isLon));
			if (isDb == 0) {
				lotteryResult.setFirst(handleAward(caulo, lotteryResult.getFirst(), isLon));
				lotteryResult.setSecond(handleAward(caulo, lotteryResult.getSecond(), isLon));
				lotteryResult.setThird(handleAward(caulo, lotteryResult.getThird(), isLon));
				lotteryResult.setFourth(handleAward(caulo, lotteryResult.getFourth(), isLon));
				lotteryResult.setFifth(handleAward(caulo, lotteryResult.getFifth(), isLon));
				lotteryResult.setSixth(handleAward(caulo, lotteryResult.getSixth(), isLon));
				lotteryResult.setSeventh(handleAward(caulo, lotteryResult.getSeventh(), isLon));
				lotteryResult.setEighth(handleAward(caulo, lotteryResult.getEighth(), isLon));
			}

			lotteryResults.set(i, lotteryResult);

			resultStart = StringConvert.stringConcatLottery(lotteryResults.get(i));
			int realVitri1 = getRealIndex(vitri[0], resultStart);
			int realVitri2 = getRealIndex(vitri[1], resultStart);
			caulo = resultStart.substring(realVitri1, (realVitri1 + 1))
					+ resultStart.substring(realVitri2, (realVitri2 + 1));
		}
	}

	private String getVitriMTMN(int i) {
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
			s = "G0.6";
			break;
		case 6:
			s = "G1.1";
			break;
		case 7:
			s = "G1.2";
			break;
		case 8:
			s = "G1.3";
			break;
		case 9:
			s = "G1.4";
			break;
		case 10:
			s = "G1.5";
			break;
		case 11:
			s = "G2.1";
			break;
		case 12:
			s = "G2.2";
			break;
		case 13:
			s = "G2.3";
			break;
		case 14:
			s = "G2.4";
			break;
		case 15:
			s = "G2.5";
			break;
		case 16:
			s = "G3.1.1";
			break;
		case 17:
			s = "G3.1.2";
			break;
		case 18:
			s = "G3.1.3";
			break;
		case 19:
			s = "G3.1.4";
			break;
		case 20:
			s = "G3.1.5";
			break;
		case 21:
			s = "G3.2.1";
			break;
		case 22:
			s = "G3.2.2";
			break;
		case 23:
			s = "G3.2.3";
			break;
		case 24:
			s = "G3.2.4";
			break;
		case 25:
			s = "G3.2.5";
			break;
		case 26:
			s = "G4.1.1";
			break;
		case 27:
			s = "G4.1.2";
			break;
		case 28:
			s = "G4.1.3";
			break;
		case 29:
			s = "G4.1.4";
			break;
		case 30:
			s = "G4.1.5";
			break;
		case 31:
			s = "G4.2.1";
			break;
		case 32:
			s = "G4.2.2";
			break;
		case 33:
			s = "G4.2.3";
			break;
		case 34:
			s = "G4.2.4";
			break;
		case 35:
			s = "G4.2.5";
			break;
		case 36:
			s = "G4.3.1";
			break;
		case 37:
			s = "G4.3.2";
			break;
		case 38:
			s = "G4.3.3";
			break;
		case 39:
			s = "G4.3.4";
			break;
		case 40:
			s = "G4.3.5";
			break;
		case 41:
			s = "G4.4.1";
			break;
		case 42:
			s = "G4.4.2";
			break;
		case 43:
			s = "G4.4.3";
			break;
		case 44:
			s = "G4.4.4";
			break;
		case 45:
			s = "G4.4.5";
			break;
		case 46:
			s = "G4.5.1";
			break;
		case 47:
			s = "G4.5.2";
			break;
		case 48:
			s = "G4.5.3";
			break;
		case 49:
			s = "G4.5.4";
			break;
		case 50:
			s = "G4.5.5";
			break;
		case 51:
			s = "G4.6.1";
			break;
		case 52:
			s = "G4.6.2";
			break;
		case 53:
			s = "G4.6.3";
			break;
		case 54:
			s = "G4.6.4";
			break;
		case 55:
			s = "G4.6.5";
		case 56:
			s = "G4.7.1";
			break;
		case 57:
			s = "G4.7.2";
			break;
		case 58:
			s = "G4.7.3";
			break;
		case 59:
			s = "G4.7.4";
			break;
		case 60:
			s = "G4.7.5";
			break;
		case 61:
			s = "G5.1";
			break;
		case 62:
			s = "G5.2";
			break;
		case 63:
			s = "G5.3";
			break;
		case 64:
			s = "G5.4";
			break;
		case 65:
			s = "G6.1.1";
			break;
		case 66:
			s = "G6.1.2";
			break;
		case 67:
			s = "G6.1.3";
			break;
		case 68:
			s = "G6.1.4";
			break;
		case 69:
			s = "G6.2.1";
			break;
		case 70:
			s = "G6.2.2";
			break;
		case 71:
			s = "G6.2.3";
			break;
		case 72:
			s = "G6.2.4";
			break;
		case 73:
			s = "G6.3.1";
			break;
		case 74:
			s = "G6.3.2";
			break;
		case 75:
			s = "G6.3.3";
			break;
		case 76:
			s = "G6.3.4";
			break;
		case 77:
			s = "G7.1";
			break;
		case 78:
			s = "G7.2";
			break;
		case 79:
			s = "G7.3";
			break;
		case 80:
			s = "G8.1";
			break;
		case 81:
			s = "G8.2";
			break;

		default:
			break;
		}

		return s;
	}

	private void makeBlueColor(CauDetailBean cauDetailBean, List<Lottery> lotteryResults) {
		int size = lotteryResults.size();
		for (int i = size - 1; i >= 0; i--) {
			Lottery lotteryResult = lotteryResults.get(i);
			int vitri[] = cauDetailBean.getVitri();
			for (int index : vitri) {
				lotteryResult = LotoUtils.replaceMT(lotteryResult, getVitriMTMN(index).trim());
			}
			lotteryResults.set(i, lotteryResult);
		}
	}

	private int getRealIndex(int index, String s) {
		int dem = -1;
		for (int i = 0; i < s.length(); i++) {
			if (Character.isDigit(s.charAt(i))) {
				dem++;

				if (dem == index) {
					return i;
				}
			}
		}
		return index;

	}

	public String handleAward(String loto, String awards, int isLon) {
		String awardTemp[] = awards.split("-");
		String rs = "";
		for (int i = 0; i < awardTemp.length; i++) {
			String temp = makeColor(loto, awardTemp[i], isLon);
			rs += temp + "-";
		}

		return rs.substring(0, rs.length() - 1);
	}

	private String makeColor(String loto, String award, int isLon) {
		boolean isCheck = false;
		int length = award.length();
		if (award.endsWith(loto)) {
			isCheck = true;
		} else {
			if (isLon > 0) {
				String temp = award.substring(length - 1) + award.substring(length - 2, length - 1);
				if (temp.equals(loto)) {
					isCheck = true;
				}
			}
		}

		if (isCheck) {

			if (length == 2) {
				award = "<span class = 'cau_lo'>" + award + "</span>";
			} else {
				award = award.substring(0, length - 2) + "<span class = 'txt_red'>" + award.substring(length - 2)
						+ "</span>";
			}
		}

		return award;
	}
}
