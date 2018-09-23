package inet.bean;

import java.util.List;

public class SoicauDetailBean {
	private int songay;
	private List<CauDetailBean> detailCauList;
	private List<CauOverviewBean> cauOverviewList;
	public int getSongay() {
		return songay;
	}
	public void setSongay(int songay) {
		this.songay = songay;
	}
	public List<CauDetailBean> getDetailCauList() {
		return detailCauList;
	}
	public void setDetailCauList(List<CauDetailBean> detailCauList) {
		this.detailCauList = detailCauList;
	}
	public List<CauOverviewBean> getCauOverviewList() {
		return cauOverviewList;
	}
	public void setCauOverviewList(List<CauOverviewBean> cauOverviewList) {
		this.cauOverviewList = cauOverviewList;
	}
}
