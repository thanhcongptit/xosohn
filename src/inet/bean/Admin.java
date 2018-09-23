/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.bean;
 
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author 24h
 */
public class Admin implements Serializable{
    
    private BigDecimal id;
    private String username;
    private String password;
    private String name;
    private int status;
    private int right;
    
    public static final int STATUS_ACTIVE = 1;
    public static final int STATUS_BLOCK = 0;
    
    public static final int RIGHT_ADMIN = 1;
    public static final int RIGHT_POSTER = 0;

    public Admin() {
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }
    
    
            
}
