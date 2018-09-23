package inet.bean;

import java.util.List;

public class SoicauNum {
	private String capso;
	private List<SoicauNumDetail> soicauNumDetails;
	public String getCapso() {
		return capso;
	}
	public void setCapso(String capso) {
		this.capso = capso;
	}
	public List<SoicauNumDetail> getSoicauNumDetails() {
		return soicauNumDetails;
	}
	public void setSoicauNumDetails(List<SoicauNumDetail> soicauNumDetails) {
		this.soicauNumDetails = soicauNumDetails;
	}
	
}
