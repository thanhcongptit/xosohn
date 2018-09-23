package inet.bean;

public class Thongke implements Comparable<Thongke>{
	public Thongke(String loto) {
		this.loto = loto;
		this.times = 1;
		this.dacbiet = false;
	}
	private boolean dacbiet;
	private String loto;
	private int times;
	public String getLoto() {
		return loto;
	}
	public boolean isDacbiet() {
		return dacbiet;
	}
	public void setDacbiet(boolean dacbiet) {
		this.dacbiet = dacbiet;
	}
	public void setLoto(String loto) {
		this.loto = loto;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	@Override
	public int compareTo(Thongke o) {
		return this.loto.compareTo(o.loto);
	}
}
