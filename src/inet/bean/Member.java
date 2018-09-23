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
public class Member implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal id;
    private String facebookId;
    private String googleId;
    private String fullname;
    private String email;    
    private String avatar;
    private String introduction;
    private int status;
    private String username;
    private String password;
    private Timestamp genDate;
    private BigDecimal money;
    private int xu;
    
    public int getXu() {
		return xu;
	}

	public void setXu(int xu) {
		this.xu = xu;
	}

	public static final int STATUS_ACTIVE = 1;
    public static final int STATUS_BLOCK = 0;

    public Member() {
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getGenDate() {
        return genDate;
    }

    public void setGenDate(Timestamp genDate) {
        this.genDate = genDate;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
 
    
}
