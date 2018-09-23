package inet.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Description {
	public static final int POSITION_MB= 0;
    public static final int POSITION_MT = 1;
    public static final int POSITION_MN = 2;
    public static final int POSITION_SKQ = 3;
    public static final int POSITION_BDB= 4;
    public static final int POSITION_BACNHO = 5;
    public static final int POSITION_CAULOTO = 6;
    public static final int POSITION_THONGKE = 7;
    public static final int POSITION_TINTUC = 8;
    public static final int POSITION_BACNHO_CHONLOC = 9;
    
    private String code = "";
    private int position;
    private Timestamp genDate;
    private Timestamp lastUpdated;
    private BigDecimal id;
    
	public Timestamp getGenDate() {
		return genDate;
	}
	public void setGenDate(Timestamp genDate) {
		this.genDate = genDate;
	}
	public Timestamp getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
    
	public String getPositionName(){
        switch (position){
            case POSITION_MB: return "Trang chủ";
            case POSITION_MT: return "Trang chủ miền trung";
            case POSITION_MN: return "Trang chủ miền nam";
            case POSITION_SKQ: return "Sổ kết quả";
            case POSITION_BDB: return "Bảng đặc biệt";
            case POSITION_BACNHO: return "Bạc nhớ";
            case POSITION_CAULOTO: return "Cầu loto";
            case POSITION_THONGKE: return "Thống ke cơ bản";
            case POSITION_TINTUC: return "Tin tức";
            case POSITION_BACNHO_CHONLOC: return "Bạc nhớ chọn lọc";
        }
        return "";
    }
}
