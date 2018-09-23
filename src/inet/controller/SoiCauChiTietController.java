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

public class SoiCauChiTietController extends BaseController {
	private SoicauService soicauService = new SoicauService();

	public SoiCauChiTietController() {
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

		int exactlimit = 0;
		int db = 0;
		int limit = 0;

		String key = ngay + ";" + exactlimit + ";" + nhay + ";" + lon + ";" + db + ";" + ca_cap;
		SoicauBean soicauBean = CommonUtil.getSoicauValueByKey(key);
		if (soicauBean == null) {
			soicauBean = soicauService.getSoiCauLoto(exactlimit, limit, ngay, nhay, lon, db, ca_cap);
			CommonUtil.putSoicauValue(key, soicauBean);
		} 

		LotteryRequest lotteryRequest = new LotteryRequest();
		SoicauDetailBean soicauDetailBeans = soicauBean.getSoicauDetailBeans().get(indexList);
		CauDetailBean cauDetailBean = soicauDetailBeans.getDetailCauList()
				.get(indeCau);
		
		List<LotteryResult> lotteryResults = new LotteryResultDAO().findResultByCode("XSTD", soicauBean.getTongsoNgay(), new BigDecimal("1000000"));
		
		List<Lottery> lotteries = lotteryRequest.parserLotteryResult("XSTD", 
				lotteryResults.get(lotteryResults.size()-1).getOpenDate(), ngay);
		
		List<List<String>> listLotterysDuoiMB = new ArrayList<>();
		List<List<String>> listLotterysDauMB = new ArrayList<>();
		if(null != lotteries) {
			for(Lottery lottery : lotteries) {
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
		mod.setViewName("/ajax/soicauchitiet");

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
			}

			lotteryResults.set(i, lotteryResult);

			resultStart = StringConvert.stringConcatLottery(lotteryResults.get(i));
			int realVitri1 = getRealIndex(vitri[0], resultStart);
			int realVitri2 = getRealIndex(vitri[1], resultStart);
			caulo = resultStart.substring(realVitri1, (realVitri1 + 1))
					+ resultStart.substring(realVitri2, (realVitri2 + 1));
		}
	}

	private void makeBlueColor(CauDetailBean cauDetailBean, List<Lottery> lotteryResults) {
		int size = lotteryResults.size();
		for (int i = size - 1; i >= 0; i--) {
			Lottery lotteryResult = lotteryResults.get(i);
			int vitri[] = cauDetailBean.getVitri();
			for (int index : vitri) {
				lotteryResult = LotoUtils.replaceMBLottery(lotteryResult, CommonUtil.getVitriToString(index).trim());
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
