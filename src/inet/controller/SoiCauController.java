/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.soicaupro.thongkebacnho.CommonUtil;

import inet.bean.Lottery;
import inet.bean.LotteryResult;
import inet.bean.SoicauBean;
import inet.constant.Constants;
import inet.model.LotteryResultDAO;
import inet.request.LotteryRequest;
import inet.util.DateProc;
import inet.util.RequestUtil;
import inet.util.StringPro;
import inet.util.Today;
import service.SoicauService;

/**
 *
 * @author hanhlm
 */
public class SoiCauController extends BaseController {

	
	public SoiCauController() {
	}

	@Override
	protected void loadBase() {
		super.loadBase(); // To change body of generated methods, choose Tools | Templates.
	}


	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mod = super.handleRequestInternal(request, response);
		Today today = new Today();
		String ngay = "";
		
		int type = RequestUtil.getInt(request, "type", 1);
		
		if (today.getHour() < 18 || (today.getHour() == 18 && today.getMinute() <= 44)) {
			ngay = ddmmyyyy;
			
		} else if (today.getHour() > 18 || (today.getHour() == 18 && today.getMinute() > 44)) {
			ngay = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), 1);
		}
		
		int exactlimit = 0;
		int nhay = 1;
		int db = 0;
		int lon = 1;
		int limit = 0;
		int ca_cap = 0;
		String url_ca = "/soi-cau-bach-thu.html";
		
		String text = "SOI CẦU BẠCH THỦ MIỀN BẮC";
		
		if(type == 2) {
			lon = 0;
			text = "SOI CẦU LẬT LIÊN TỤC XSMB";
			url_ca = "/cau-lat-lien-tuc.html";
		} else if(type == 3) {
			nhay = 2;
			text = "SOI CẦU NHIỀU NHÁY XSMB";
			url_ca = "/cau-ve-nhieu-nhay.html";
		} else if(type == 4) {
			text = "SOI CẦU VỀ CẢ CẶP XSMB";
			ca_cap = 1;
			nhay = 2;
			url_ca = "/cau-ve-ca-cap.html";
		}
		
		mod.addObject("url_ca", Constants.URL_CAN + url_ca);
		
		SoicauService soicauService = new SoicauService();
		String key = ngay + ";" + exactlimit + ";" + nhay + ";" + lon + ";" + db + ";" +ca_cap;
		SoicauBean soicauBean = CommonUtil.getSoicauValueByKey(key);
		if (soicauBean == null) {
			soicauBean = soicauService.getSoiCauLoto(exactlimit, limit, ngay, nhay, lon, db, ca_cap);
			CommonUtil.putSoicauValue(key, soicauBean);
		}
		
		LotteryRequest lotteryRequest = new LotteryRequest();
		List<LotteryResult> lotteryResults = new LotteryResultDAO().findResultByCode("XSTD", soicauBean.getTongsoNgay(), new BigDecimal("1000000"));
		List<Lottery> lotteries = lotteryRequest.parserLotteryResult("XSTD", 
				lotteryResults.get(lotteryResults.size()-1).getOpenDate(), ngay);
		
		List<List<String>> listLotterysDuoiMB = new ArrayList<>();
		List<List<String>> listLotterysDauMB = new ArrayList<>();
		for(Lottery lottery : lotteries) {
			List<String> listLotteryDuoiMB = StringPro.filterDauOrDuoi(lottery, true);
			listLotterysDuoiMB.add(listLotteryDuoiMB);
			
			List<String> listLotteryDauMB = StringPro.filterDauOrDuoi(lottery, false);
			listLotterysDauMB.add(listLotteryDauMB);
			
		}
		
		mod.addObject("startDate", ngay);
		mod.addObject("categoryNews", "MIEN BAC");
		mod.addObject("soicauBean", soicauBean);
		mod.addObject("lotteryResults", lotteries);
		mod.addObject("duoiResults", listLotterysDuoiMB);
		mod.addObject("dauResults", listLotterysDauMB);
		mod.addObject("nhay", nhay);
		mod.addObject("lon", lon);
		mod.addObject("ca_cap", ca_cap);
		mod.addObject("text_soicau", text);
		mod.addObject("activeMenu", 5);
		mod.setViewName("/soi_cau");

		return mod;
	}

}
