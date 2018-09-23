package inet.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Vector;

public class LotteryResult implements Serializable{

    private String code;
    private String eighth;
    private String fifth;
    private String first;
    private String fourth;
    private BigDecimal id;
    private String lastUpdated;
    private String openDate;
    private BigDecimal price;
    private String second;
    private String seventh;
    private String sixth;
    private String special;
    private int status;
    private String symbol;
    private String third;
    private String userName;
    private String province;

    private String create_date;

    public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
    private static Vector xsMienBacCodes = new Vector();

    static {
        xsMienBacCodes.add("XSTD");
        xsMienBacCodes.add("XSBN");
        xsMienBacCodes.add("XSHP");
        xsMienBacCodes.add("XSQN");
        xsMienBacCodes.add("XSND");
        xsMienBacCodes.add("XSTB");
    }

    public LotteryResult() {

    }

    public LotteryResult(BigDecimal id, String code, String symbol, BigDecimal price, String open_date, String special, String first, String second, String third, String fourth, String fifth, String sixth, String seventh, String eighth, int status) {
        this.id = id;
        this.code = code;
        this.symbol = symbol;
        this.price = price;
        this.openDate = open_date;
        this.special = special;
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;
        this.sixth = sixth;
        this.seventh = seventh;
        this.eighth = eighth;
        this.status = status;

    }

    public String getCode() {
        return this.code;
    }

    public String getEighth() {
        return this.eighth;
    }

    public String getFifth() {
        return this.fifth;
    }

    public String getFirst() {
        return this.first;
    }

    public String getFourth() {
        return this.fourth;
    }

    public BigDecimal getId() {
        return this.id;
    }

    public String getLastUpdated() {
        return this.lastUpdated;
    }

    public String getOpenDate() {
        return this.openDate;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public String getSecond() {
        return this.second;
    }

    public String getSeventh() {
        return this.seventh;
    }

    public String getSixth() {
        return this.sixth;
    }

    public String getSpecial() {
        return this.special;
    }

    public int getStatus() {
        return this.status;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String getThird() {
        return this.third;
    }

    public boolean hasFullResult() {
        return ((this.special != null) && (this.first != null) && (this.second != null) && (this.third != null) && (this.fourth != null) && (this.fifth != null) && (this.sixth != null) && (this.seventh != null) && (this.special.indexOf("null") < 0) && (this.seventh.indexOf("null") < 0));
    }

    public boolean hasFullResultButSpecial() {
        return ((this.seventh != null) && (this.seventh.indexOf("null") < 0) && (((this.special == null) || (this.special.indexOf("null") >= 0))));
    }

    public boolean hasHalfResult() {
        return ((((this.special == null) || (this.special.indexOf("null") >= 0))) && (this.sixth != null) && (this.sixth.indexOf("null") < 0));
    }

    public boolean isXoSoMienBac() {
        return xsMienBacCodes.contains(this.code);
    }

    public void setCode(String value) {
        if (value != null) {
            value = value.toUpperCase();
        }
        this.code = value;
    }

    public void setEighth(String value) {
        if ("null".equalsIgnoreCase(value)) {
            value = null;
        }

        this.eighth = value;
    }

    public void setFifth(String value) {
        if ("null".equalsIgnoreCase(value)) {
            value = null;
        }

        this.fifth = value;
    }

    public void setFirst(String value) {
        if ("null".equalsIgnoreCase(value)) {
            value = null;
        }

        this.first = value;
    }

    public void setFourth(String value) {
        if ("null".equalsIgnoreCase(value)) {
            value = null;
        }

        this.fourth = value;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public void setLastUpdated(String value) {
        this.lastUpdated = value;
    }

    public void setOpenDate(String open_date) {
        this.openDate = open_date;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setSecond(String value) {
        if ("null".equalsIgnoreCase(value)) {
            value = null;
        }

        this.second = value;
    }

    public void setSeventh(String value) {
        if ("null".equalsIgnoreCase(value)) {
            value = null;
        }

        this.seventh = value;
    }

    public void setSixth(String value) {
        if ("null".equalsIgnoreCase(value)) {
            value = null;
        }

        this.sixth = value;
    }

    public void setSpecial(String value) {
        if ("null".equalsIgnoreCase(value)) {
            value = null;
        }

        this.special = value;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setThird(String value) {
        if ("null".equalsIgnoreCase(value)) {
            value = null;
        }

        this.third = value;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
