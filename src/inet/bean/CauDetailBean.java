package inet.bean;

public class CauDetailBean implements Comparable<CauDetailBean> {
	private int vitri[];
	private String value;
	public int[] getVitri() {
		return vitri;
	}
	public void setVitri(int[] vitri) {
		this.vitri = vitri;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public int compareTo(CauDetailBean o) {
		
		return this.getValue().compareTo(o.getValue());
	}
	
}
