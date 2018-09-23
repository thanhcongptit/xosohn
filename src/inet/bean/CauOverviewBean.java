package inet.bean;

public class CauOverviewBean implements Comparable<CauOverviewBean> {
	private String value;
	private int count;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public int compareTo(CauOverviewBean o) {
		// TODO Auto-generated method stub
		return o.getCount() - this.getCount();
	}
	
}
