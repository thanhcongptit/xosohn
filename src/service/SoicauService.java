package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.soicaupro.thongkebacnho.Constant;

import inet.bean.CauDetailBean;
import inet.bean.CauOverviewBean;
import inet.bean.LotteryResult;
import inet.bean.SoicauBean;
import inet.bean.SoicauDetailBean;
import inet.bean.SoicauNum;
import inet.bean.SoicauNumDetail;
import inet.model.LotteryResultDAO;
import inet.util.LotoUtils;
import inet.util.StringConvert;

public class SoicauService {

	private LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();

	public SoicauBean getSoiCauLoto(int exactlimit, int limit, String ngay, int nhay, int lon, int db, int ca_cap) {
		SoicauBean soicauBean = new SoicauBean();
		boolean isLon = lon > 0 ? true : false;
		boolean isDb = db > 0 ? true : false;
		int day = 0;
		boolean isExactLimit = exactlimit > 0 ? true : false;

		List<LotteryResult> lotteryResults = lotteryResultDAO.findLotteryResultLimit("XSTD", "2");
		List<LotteryResult> lotteryResultsCopy = new ArrayList<>(lotteryResults);
		

		if (lotteryResults.size() > 0) {

			List<SoicauDetailBean> soicauDetailBeans = new ArrayList<>();
			SoicauDetailBean soicauDetailBeanDay1 = new SoicauDetailBean();
			List<CauDetailBean> detailCauListDay1 = getCauDetailInit(lotteryResults, nhay, isLon, isDb);

			if (detailCauListDay1.size() > 0) {


				soicauDetailBeanDay1.setDetailCauList(detailCauListDay1);
				soicauDetailBeanDay1.setSongay(++day);
				soicauDetailBeans.add(soicauDetailBeanDay1);

				boolean isRun = true;
				LotteryResult initialLottery = lotteryResults.get(lotteryResults.size() - 1);
				lotteryResults = getLotteriesInput(initialLottery);

				List<CauDetailBean> cauDetailBeans = new ArrayList<>(detailCauListDay1);
				while (isRun) {
					cauDetailBeans = getCauDetailList(cauDetailBeans, lotteryResults, nhay, isLon, isDb);

					if (cauDetailBeans.isEmpty()) {
						isRun = false;
					} else {
						SoicauDetailBean soicauDetailBean = new SoicauDetailBean();
						List<CauDetailBean> cauDetailBeansCopy = new ArrayList<>(cauDetailBeans);
						Collections.sort(cauDetailBeansCopy);
						soicauDetailBean.setSongay(++day);
						soicauDetailBean.setDetailCauList(cauDetailBeansCopy);
						soicauDetailBeans.add(soicauDetailBean);

						initialLottery = lotteryResults.get(lotteryResults.size() - 1);
						lotteryResults = getLotteriesInput(initialLottery);
					}
				}
			}
			
			if(isExactLimit) {
				for(int i = 0; i < soicauDetailBeans.size() - 1; i++) {
					SoicauDetailBean soicauDetailBean1 = soicauDetailBeans.get(i);
					SoicauDetailBean soicauDetailBean2 = soicauDetailBeans.get(i+1);
					List<CauDetailBean> cauDetailBeans = filterCauDetail(soicauDetailBean1.getDetailCauList(),
							soicauDetailBean2.getDetailCauList());
					
					soicauDetailBean1.setDetailCauList(cauDetailBeans);
					soicauDetailBeans.set(i, soicauDetailBean1);
				}
			}
			soicauBean.setSoicauDetailBeans(soicauDetailBeans);

		}
		soicauBean.setTongsoNgay(day);
		soicauBean.setDB(isDb);
		soicauBean.setExactlimit(isExactLimit);
		soicauBean.setLimit(limit);
		soicauBean.setLon(isLon);
		soicauBean.setNhay(nhay);
		soicauBean.setOpenDate(ngay);
		
		if(ca_cap == 1) {
			lotteryResultsCopy.addAll(lotteryResults);
			filterCaCap(soicauBean, lotteryResultsCopy);
		}
		
		filterSoicauBean(soicauBean);
		return soicauBean;
	}
	
	private void filterCaCap(SoicauBean soicauBean, List<LotteryResult> lotteries) {
		List<SoicauDetailBean> soicauDetailBeans = soicauBean.getSoicauDetailBeans();
		
		if(soicauDetailBeans != null) {
			for(SoicauDetailBean soicauDetailBean: soicauDetailBeans) {
				
				int songay = soicauDetailBean.getSongay();
				if(songay > 1) {
					List<CauDetailBean> cauDetailBeans = soicauDetailBean.getDetailCauList();
					List<CauDetailBean>filtedCauDetailBeans = filterDetail (lotteries, cauDetailBeans,songay);
					soicauDetailBean.setDetailCauList(filtedCauDetailBeans);
				}
			}
		}
	}

	private List<CauDetailBean> filterDetail(List<LotteryResult> lotteries, List<CauDetailBean> cauDetailBeans, int songay) {

		List<CauDetailBean> filtedCauDetailBeans = new ArrayList<>();
		if(cauDetailBeans != null) {
			
			for(CauDetailBean cauDetailBean : cauDetailBeans) {
				if(cauDetailBean.getValue().equals("66")) {
					System.out.println("66");
				}
				if(checkCauDetail(cauDetailBean, lotteries, songay)) {
					filtedCauDetailBeans.add(cauDetailBean);
				}
			}
		}
		
		return filtedCauDetailBeans;
	}

	private boolean checkCauDetail(CauDetailBean cauDetailBean, List<LotteryResult> lotteries, int songay) {
		int size = lotteries.size();
		for(int i = (size - songay); i > 0; i--) {
			
			LotteryResult lotteryResultLoto = lotteries.get(i);
			String concatLoto = StringConvert.stringConcat(lotteryResultLoto);
			String loto = concatLoto.charAt(cauDetailBean.getVitri()[0]) + "" + concatLoto.charAt(cauDetailBean.getVitri()[1]);
			String lotoDao = concatLoto.charAt(cauDetailBean.getVitri()[1]) + "" + concatLoto.charAt(cauDetailBean.getVitri()[0]);
			
			LotteryResult lotteryResult  = lotteries.get(i-1);
			if(!LotoUtils.checkAwardInLottery(loto, lotteryResult)
					|| !LotoUtils.checkAwardInLottery(lotoDao, lotteryResult)) {
				return false;
			}
		}
		return true;
	}

	private List<CauDetailBean> filterCauDetail(List<CauDetailBean> list1, List<CauDetailBean> list2) {
		List<CauDetailBean> cauDetailBeans = new ArrayList<>();
		for(CauDetailBean cauDetailBean: list1) {
			String value  = cauDetailBean.getValue();
			int vitri[] = cauDetailBean.getVitri();
			if(!checkInList(list2, vitri, value)) {
				cauDetailBeans.add(cauDetailBean);
			}
		}
			
		return cauDetailBeans;	
	}

	public boolean checkInList(List<CauDetailBean> cauDetailBeans, int vitri[], String value) {
		for(CauDetailBean cauDetailBean: cauDetailBeans) {
			if(cauDetailBean.getValue().equals(value)
					&& cauDetailBean.getVitri()[0] == vitri[0]
					&&	cauDetailBean.getVitri()[1] == vitri[1]) {
				return true;
			}
		}
		
		return false;
	}
	
	private List<LotteryResult> getLotteriesInput(LotteryResult input) {
		List<LotteryResult> lotteryResults = lotteryResultDAO.findResultByCode(input.getCode().toUpperCase(), 2, input.getId());
		return lotteryResults;

	}

	private List<CauDetailBean> getCauDetailInit(List<LotteryResult> lotteryResults, int nhay, boolean isLon, boolean isSpecial) {
		List<CauDetailBean> detailCauListDay1 = new ArrayList<>();
		String resultStart = StringConvert.stringConcat(lotteryResults.get(0));
		String result = "";
		String lotoResult = "";
		String capLoto = "";
		String loto = "";

		for (int i = 0; i < resultStart.length(); i++) {
			int j = 0;

			if (isLon) {
				j = i + 1;
			}

			for (; j < resultStart.length(); j++) {
				if (i != j) {

					result = resultStart;
					capLoto = result.substring(i, i + 1) + result.substring(j, j + 1);
					for (int k = 0; k < lotteryResults.size() - 1; k++) {
						if(isSpecial) {
							lotoResult = StringConvert.subRight(lotteryResults.get(k).getSpecial(), 2);
						} else {
							lotoResult = StringConvert.lotoResult(lotteryResults.get(k));
						}
						result = StringConvert.stringConcat(lotteryResults.get(k + 1));
						loto = result.substring(i, i + 1) + result.substring(j, j + 1);
						if (LotoUtils.findLotoResult(lotoResult, loto, nhay, isLon)) {
							CauDetailBean cauDetailBean = new CauDetailBean();
							int vitri[] = { i, j };
							cauDetailBean.setVitri(vitri);
							cauDetailBean.setValue(capLoto);
							detailCauListDay1.add(cauDetailBean);
						}

					}
				}

			}
		}

		return detailCauListDay1;
	}

	private List<CauDetailBean> getCauDetailList(List<CauDetailBean> soicauDetailBeans,
			List<LotteryResult> lotteryResults, int nhay, boolean isLon, boolean isSpecial) {
		List<CauDetailBean> cauDetailBeans = new ArrayList<CauDetailBean>();

		String resultStart = StringConvert.stringConcat(lotteryResults.get(0));
		String result = "";
		String lotoResult = "";
		String loto = "";

		for (CauDetailBean temp : soicauDetailBeans) {
			int i = temp.getVitri()[0];
			int j = temp.getVitri()[1];
			result = resultStart;
			for (int k = 0; k < lotteryResults.size() - 1; k++) {
				if(isSpecial) {
					lotoResult = StringConvert.subRight(lotteryResults.get(k).getSpecial(), 2);
				} else {
					lotoResult = StringConvert.lotoResult(lotteryResults.get(k));
				}
				result = StringConvert.stringConcat(lotteryResults.get(k + 1));
				loto = result.substring(i, i + 1) + result.substring(j, j + 1);
				if (LotoUtils.findLotoResult(lotoResult, loto, nhay, isLon)) {
					CauDetailBean cauDetailBean = new CauDetailBean();
					int vitri[] = { i, j };
					cauDetailBean.setVitri(vitri);
					cauDetailBean.setValue(temp.getValue());
					cauDetailBeans.add(cauDetailBean);
				}

			}
		}

		return cauDetailBeans;
	}

	private List<CauOverviewBean> getCauOverviewBeans(List<CauDetailBean> cauDetailBeans, boolean isLon) {
		List<CauOverviewBean> cauOverviewBeans = new ArrayList<>();
		for (CauDetailBean cauDetailBean : cauDetailBeans) {
			updateCauOverview(cauDetailBean, cauOverviewBeans, isLon);
		}

		return cauOverviewBeans;
	}

	private void updateCauOverview(CauDetailBean cauDetailBean, List<CauOverviewBean> cauOverviewBeans, boolean isLon) {
		String beanDetailValue = cauDetailBean.getValue();
		int index = getIndexCauOverview(cauOverviewBeans, beanDetailValue);

		if (index > -1) {
			CauOverviewBean cauOverviewBean = cauOverviewBeans.get(index);
			cauOverviewBean.setCount(cauOverviewBean.getCount() + 1);
			cauOverviewBeans.set(index, cauOverviewBean);
		} else {
			CauOverviewBean cauOverviewBean = new CauOverviewBean();
			if (!isLon) {
				cauOverviewBean.setValue(beanDetailValue);
			} else {
				String[] values = getValueCoupleLoto(beanDetailValue);
				if(!checkKep(values)) {
					cauOverviewBean.setValue(values[0] + "," + values[1]);
				} else {
					cauOverviewBean.setValue(beanDetailValue);
				}
			}
			cauOverviewBean.setCount(1);
			cauOverviewBeans.add(cauOverviewBean);
		}

	}

	private int getIndexCauOverview(List<CauOverviewBean> cauOverviewBeans, String beanDetailValue) {
		for (int i = 0; i < cauOverviewBeans.size(); i++) {
			if (cauOverviewBeans.get(i).getValue().contains(beanDetailValue)) {
				return i;
			}
		}

		return -1;
	}

	private String[] getValueCoupleLoto(String detailBean) {
		for (int i = 0; i < Constant.coupleLoto.length; i++) {
			if (Constant.coupleLoto[i][0].equals(detailBean) || Constant.coupleLoto[i][1].equals(detailBean)) {
				return Constant.coupleLoto[i];
			}
		}
		return null;

	}

	public boolean checkKep(String values[]) {
		boolean rs = false;
		switch (values[0]) {
		case "00":
			rs = true;
			break;
		case "11":
			rs = true;
			break;
		case "22":
			rs = true;
			break;
		case "33":
			rs = true;
			break;
		case "44":
			rs = true;
			break;
		case "55":
			rs = true;
			break;
		case "66":
			rs = true;
			break;
		case "77":
			rs = true;
			break;
		case "88":
			rs = true;
			break;
		case "99":
			rs = true;
			break;
		}
		return rs;
	}

	public int getSoCapCauKhacNhau(List<CauDetailBean> cauDetailBeans, boolean isLon) {
		List<CauOverviewBean> cauOverviewBeans = getCauOverviewBeans(cauDetailBeans, isLon);
		return cauOverviewBeans.size();
	}
	
	//--------------------------------------
	
	public static void main(String args[]) {
		String capso = "33,78,58";
		SoicauService soicauService = new SoicauService();
		List<String> capsoList = soicauService.getCapso(capso);
		
		SoicauBean soicauBean = soicauService.getSoiCauLotoMT(0, 1, 1, 1, 0,null);
		
		 capsoList = soicauService.filterInputList(capsoList, 1);
	        List<SoicauNum> soicauNums = new ArrayList<>();
	        if(!capsoList.isEmpty()) {
	        	for(String capsoTemp : capsoList) {
	        		SoicauNum soicauNum = soicauService.getSoiCauNum(capsoTemp, soicauBean, 3, 1);
	        		soicauNums.add(soicauNum);
	        	}
	        }
	     System.out.println(soicauNums);   
	}
	
	private List<String> filterInputList(List<String> capsos, int isLon) {
		List<String> rs = new ArrayList<>();
		for(String capso : capsos) {
			if(checkCapSoInList(capso, capsos, isLon)) {
				rs.add(capso);
			}
		}
		
		return rs;
	}
	
	private boolean checkCapSoInList(String capso, List<String> capsos, int isLon) {
		for(String s: capsos) {
			if(s.equalsIgnoreCase(capso)) {
				return true;
			}
			
			if(isLon > 0) {
				String s1 = capso.substring(1, 2);
				String s2 = capso.substring(0, 1);
				String lotoDao = s1+s2;
				if(s.equalsIgnoreCase(lotoDao)) {
					return true;
				}
				
				if(s1.equals(s2)) {
					int number1 = Integer.parseInt(lotoDao);
					int number2 = Integer.parseInt(capso);
					
					if(Math.abs(number1 - number2) == 55) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	private SoicauNum getSoiCauNum(String cau, SoicauBean soicauBean, int limit, int isLon) {
		SoicauNum soicauNum = new SoicauNum();
		List<SoicauNumDetail> soicauNumDetails = new ArrayList<>();
		List<SoicauDetailBean> soicauDetailBeans = soicauBean.getSoicauDetailBeans();
		for(int i=soicauDetailBeans.size() - 1; i>=(limit-1); i--) {
			SoicauDetailBean soicauDetailBean = soicauDetailBeans.get(i);
			List<CauDetailBean> caDetailBeans = soicauDetailBean.getDetailCauList();
			List<CauDetailBean> tempCau = checkInCauDetailList(cau, caDetailBeans, isLon);
			if( !tempCau.isEmpty()) {
				for(CauDetailBean cauDetailBean : tempCau) {
					if(checkCauVitri(cauDetailBean.getVitri(), soicauNumDetails)) {
						SoicauNumDetail soicauNumDetail = new SoicauNumDetail();
						soicauNumDetail.setVitri(cauDetailBean.getVitri());
						soicauNumDetail.setNgayHienthi((i+1));
						
						soicauNumDetails.add(soicauNumDetail);
					}
				}
			}
			
		}
		
		soicauNum.setSoicauNumDetails(soicauNumDetails);
		if(isLon > 0) {
			String[] capsos = getValueCoupleLoto(cau);
			soicauNum.setCapso(capsos[0] + "," + capsos[1]);
		} else {
			soicauNum.setCapso(cau);
		}
		
		
		return soicauNum;
	}
	
	
	private boolean checkCauVitri(int[] vitri, List<SoicauNumDetail> soicauNumDetails) {
		for(SoicauNumDetail soicauNumDetail: soicauNumDetails) {
			if(soicauNumDetail.getVitri()[0] == vitri[0]
					&& soicauNumDetail.getVitri()[1] == vitri[1]) {
				return false;
			}
		}
		return true;
	}

	private List<CauDetailBean> checkInCauDetailList(String cau, List<CauDetailBean> caDetailBeans, int lon) {
		List<CauDetailBean> rs = new ArrayList<>();
		for(CauDetailBean cauDetailBean : caDetailBeans) {
			if(cauDetailBean.getValue().equals(cau)) {
				rs.add(cauDetailBean);
			} else {
				if(lon > 0) {
					String dao = LotoUtils.getLotoDao(cau);
					if(cauDetailBean.getValue().equals(dao)) {
						rs.add(cauDetailBean);
					} 
				}				
			}
		}
		
		return rs;
	}
	
	private List<String> getCapso(String capso) {
		List<String> capsos = new ArrayList<>();
		String[] capsoList1 = capso.split(" ");
		for(String cap1 : capsoList1) {
			String[] capsoList2 = cap1.split(",");
			
			for(String cap2 : capsoList2) {
				String[] capsoList3 = cap2.split(";");
				
				for(String cap3 : capsoList3) {
					String[] capsoList4 = cap3.split("-");
					
					for(String cap4: capsoList4) {
						if(checkNumberFromString(cap4)) {
							capsos.add(cap4);
						}
					}
				}
			}
			
		}
		
		return capsos;
	}
	
	private boolean checkNumberFromString(String temp) {
		int x = -1;
		
		try {
			x = Integer.parseInt(temp);
		} catch (NumberFormatException e) {
			x = - 1;
		}
		
		return x < 0 ? false : true;
	}
	
	//-------------------------------------------------------------------
	public SoicauBean getSoiCauLotoMT(int exactlimit, int limit, int nhay, int lon, int db, List<LotteryResult> lotteryResults) {
		SoicauBean soicauBean = new SoicauBean();
		boolean isLon = lon > 0 ? true : false;
		boolean isDb = db > 0 ? true : false;
		int day = 0;
		boolean isExactLimit = exactlimit > 0 ? true : false;

		if (lotteryResults.size() > 0) {

			List<SoicauDetailBean> soicauDetailBeans = new ArrayList<>();
			SoicauDetailBean soicauDetailBeanDay1 = new SoicauDetailBean();
			List<CauDetailBean> detailCauListDay1 = getCauDetailInit(lotteryResults, nhay, isLon, isDb);

			if (detailCauListDay1.size() > 0) {


				soicauDetailBeanDay1.setDetailCauList(detailCauListDay1);
				soicauDetailBeanDay1.setSongay(++day);
				soicauDetailBeans.add(soicauDetailBeanDay1);

				boolean isRun = true;
				LotteryResult initialLottery = lotteryResults.get(lotteryResults.size() - 1);
				lotteryResults = getLotteriesInputMT(initialLottery);

				List<CauDetailBean> cauDetailBeans = new ArrayList<>(detailCauListDay1);
				while (isRun) {
					cauDetailBeans = getCauDetailList(cauDetailBeans, lotteryResults, nhay, isLon, isDb);

					if (cauDetailBeans.isEmpty()) {
						isRun = false;
					} else {
						SoicauDetailBean soicauDetailBean = new SoicauDetailBean();
						List<CauDetailBean> cauDetailBeansCopy = new ArrayList<>(cauDetailBeans);
						Collections.sort(cauDetailBeansCopy);
						soicauDetailBean.setSongay(++day);
						soicauDetailBean.setDetailCauList(cauDetailBeansCopy);
						soicauDetailBeans.add(soicauDetailBean);

						initialLottery = lotteryResults.get(lotteryResults.size() - 1);
						lotteryResults = getLotteriesInputMT(initialLottery);
					}
				}
			}
			
			if(isExactLimit) {
				for(int i = 0; i < soicauDetailBeans.size() - 1; i++) {
					SoicauDetailBean soicauDetailBean1 = soicauDetailBeans.get(i);
					SoicauDetailBean soicauDetailBean2 = soicauDetailBeans.get(i+1);
					List<CauDetailBean> cauDetailBeans = filterCauDetail(soicauDetailBean1.getDetailCauList(),
							soicauDetailBean2.getDetailCauList());
					
					soicauDetailBean1.setDetailCauList(cauDetailBeans);
					soicauDetailBeans.set(i, soicauDetailBean1);
				}
			}
			soicauBean.setSoicauDetailBeans(soicauDetailBeans);

		}
		soicauBean.setTongsoNgay(day);
		soicauBean.setDB(isDb);
		soicauBean.setExactlimit(isExactLimit);
		soicauBean.setLimit(limit);
		soicauBean.setLon(isLon);
		soicauBean.setNhay(nhay);
		//soicauBean.setOpenDate(ngay);
		filterSoicauBean(soicauBean);
		return soicauBean;
	}
	
	private void filterSoicauBean(SoicauBean soicauBean) {
		List<SoicauDetailBean> soicauDetailBeans = soicauBean.getSoicauDetailBeans();
		for(int index = 0; index < soicauDetailBeans.size() -1 ;index++) {
			SoicauDetailBean soicauDetailBean = soicauDetailBeans.get(index);
			List<CauDetailBean> cauDetailBeans = soicauDetailBean.getDetailCauList();
			List<CauDetailBean> cauDetailBeansFilter = new ArrayList<>();
			
			for(CauDetailBean cauDetailBean : cauDetailBeans) {
				if(checkCauDetailBefore(cauDetailBean, soicauDetailBeans.get(index+1).getDetailCauList())) {
					cauDetailBeansFilter.add(cauDetailBean);
				}
			}
			
			soicauDetailBean.setDetailCauList(cauDetailBeansFilter);
		}
		
	}

	private boolean checkCauDetailBefore(CauDetailBean cauDetailBean, List<CauDetailBean> detailCauList) {
		
		for(CauDetailBean cauTomorrow: detailCauList) {
			if(cauTomorrow.getVitri()[0] == cauDetailBean.getVitri()[0]
					&& cauTomorrow.getVitri()[1] == cauDetailBean.getVitri()[1]) {
				return false;
			}
		}
		return true;
	}

	private List<LotteryResult> getLotteriesInputMT(LotteryResult input) {
		List<LotteryResult> lotteryResults = lotteryResultDAO.findResultByCode(input.getCode().toUpperCase(), 2, input.getId());
		return lotteryResults;

	}
}
