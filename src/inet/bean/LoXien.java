package inet.bean;

import java.util.List;

public class LoXien implements Comparable<LoXien>{
	private String capLoto;
	private List<ThongKeBean> openDates;
	private int count;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getCapLoto() {
		return capLoto;
	}
	public void setCapLoto(String capLoto) {
		this.capLoto = capLoto;
	}
	
	public List<ThongKeBean> getOpenDates() {
		return openDates;
	}
	public void setOpenDates(List<ThongKeBean> openDates) {
		this.openDates = openDates;
	}
	@Override
	public int compareTo(LoXien o) {
		// TODO Auto-generated method stub
		return o.count - this.count;
	}
}
