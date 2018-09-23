package inet.bean;

public class TanSuatXuatHien implements Comparable<TanSuatXuatHien> {
	public TanSuatXuatHien() {
		// TODO Auto-generated constructor stub
	}
	
	public TanSuatXuatHien(String loto, int count) {
		this.loto = loto;
		this.count = count;
	}

	private String loto;
	private int count;
	public String getLoto() {
		return loto;
	}
	public void setLoto(String loto) {
		this.loto = loto;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public int compareTo(TanSuatXuatHien o) {
		// TODO Auto-generated method stub
		return o.count - this.count;
	}
}
