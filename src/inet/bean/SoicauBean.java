package inet.bean;

import java.util.List;

public class SoicauBean {
	private List<SoicauDetailBean> soicauDetailBeans;
	private String openDate;
	private int limit;
	private boolean exactlimit;
	private int nhay; 
	private boolean isLon;
	private boolean isDB;
	private int tongsoNgay;
	public int getTongsoNgay() {
		return tongsoNgay;
	}
	public void setTongsoNgay(int tongsoNgay) {
		this.tongsoNgay = tongsoNgay;
	}
	public List<SoicauDetailBean> getSoicauDetailBeans() {
		return soicauDetailBeans;
	}
	public void setSoicauDetailBeans(List<SoicauDetailBean> soicauDetailBeans) {
		this.soicauDetailBeans = soicauDetailBeans;
	}
	public String getOpenDate() {
		return openDate;
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public boolean isExactlimit() {
		return exactlimit;
	}
	public void setExactlimit(boolean exactlimit) {
		this.exactlimit = exactlimit;
	}
	public int getNhay() {
		return nhay;
	}
	public void setNhay(int nhay) {
		this.nhay = nhay;
	}
	public boolean isLon() {
		return isLon;
	}
	public void setLon(boolean isLon) {
		this.isLon = isLon;
	}
	public boolean isDB() {
		return isDB;
	}
	public void setDB(boolean isDB) {
		this.isDB = isDB;
	}
	
}
