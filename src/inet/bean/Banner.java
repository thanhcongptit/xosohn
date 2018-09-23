/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 *
 * @author 24h
 */
public class Banner implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal id;
    private String page;
    private String code;
    private int position;
    private Timestamp genDate;
    private Timestamp lastUpdated;
    private String desc;
    private int status;
    
    public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public static final int POSITION_HEADER = 0;
    public static final int POSITION_TOP_1 = 1;
    public static final int POSITION_TOP_2 = 2;
    public static final int POSITION_CONTENT = 3;
    public static final int POSITION_CONTENT1 = 4;
    public static final int POSITION_CONTENT2 = 5;
    public static final int POSITION_CONTENT3 = 6;
    public static final int POSITION_CONTENT4 = 7;
    public static final int POSITION_CONTENT5 = 8;
    public static final int POSITION_CONTENT6 = 9;
    public static final int POSITION_RIGHT = 10;
    public static final int POSITION_RIGHT1 = 11;
    public static final int POSITION_RIGHT2 = 12;
    public static final int POSITION_NEO = 13;

    public Banner() {
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
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
    
    public String getPositionName(){
        switch (position){
            case POSITION_HEADER: return "Banner Header";
            case POSITION_TOP_1: return "Banner Top 1";
            case POSITION_TOP_2: return "Banner Top 2";
            case POSITION_CONTENT: return "Banner Content 1";
            case POSITION_CONTENT1: return "Banner Content 2";
            case POSITION_CONTENT2: return "Banner Content 3";
            case POSITION_CONTENT3: return "Banner Content 4";
            case POSITION_CONTENT4: return "Banner Content 5";
            case POSITION_CONTENT5: return "Banner Content 6";
            case POSITION_CONTENT6: return "Banner Content 7";
            case POSITION_RIGHT: return "Banner Right";
            case POSITION_RIGHT1: return "Banner Right 1";
            case POSITION_RIGHT2: return "Banner Right 2";
            case POSITION_NEO: return "Banner Neo";
        }
        return "";
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
}
