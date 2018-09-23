/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.model;

import inet.bean.Admin;
import inet.pool.DBPoolX;
import inet.pool.DBPoolXName; 
import inet.util.Logger;
import inet.util.Md5;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author 24h
 */
public class AdminDAO {
    
    private DBPoolX poolX = null;
    Logger logger = new Logger(this.getClass().getName());

    public AdminDAO() {
        try {
            poolX = DBPoolX.getInstance(DBPoolXName.SERVICE_LOTTERY);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
    public boolean create(Admin admin){
        
        Connection conn = null;
        PreparedStatement ps = null;
        
        try{
            String sql = "insert into admin(username, password, name, status, right_)"
                    + "   values(?, ?, ?, ?, ?)";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, admin.getUsername());
            ps.setString(i++, Md5.Hash(admin.getPassword()));
            ps.setString(i++, admin.getName());
            ps.setInt(i++, admin.getStatus());
            ps.setInt(i++, admin.getRight());
            
            return ps.executeUpdate() == 1;
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps);
        }
        
        return false;
    }
    
    public boolean checkExistUsername(String username){
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            String sql = "select id from admin where upper(username) = upper(?)";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            
            return rs.next();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        
        return false;
    }
    
    public boolean update(Admin admin){
        
        Connection conn = null;
        PreparedStatement ps = null;
        
        try{
            String sql = "update admin "
                    + "   set password = ?, name = ?, status = ?, right_ = ?"
                    + "   where id = ?";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, Md5.Hash(admin.getPassword()));
            ps.setString(i++, admin.getName());
            ps.setInt(i++, admin.getStatus());
            ps.setInt(i++, admin.getRight());
            ps.setBigDecimal(i++, admin.getId());
            
            return ps.executeUpdate() == 1;
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps);
        }
        
        return false;
    }
    
    public boolean updateQT(Admin admin){
        
        Connection conn = null;
        PreparedStatement ps = null;
        
        try{
            String sql = "update admin "
                    + "   set name = ?, right_ = ?"
                    + "   where id = ?";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, admin.getName());
            ps.setInt(i++, admin.getRight());
            ps.setBigDecimal(i++, admin.getId());
            
            return ps.executeUpdate() == 1;
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps);
        }
        
        return false;
    }
    
    public boolean updateStatus(BigDecimal id, int status){
        
        Connection conn = null;
        PreparedStatement ps = null;
        
        try{
            String sql = "update admin "
                    + "   set status = ?"
                    + "   where id = ?";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setBigDecimal(2, id);
            
            return ps.executeUpdate() == 1;
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps);
        }
        
        return false;
    }
    
    public boolean updatePassword(BigDecimal id, String newPass){
        
        Connection conn = null;
        PreparedStatement ps = null;
        
        try{
            String sql = "update admin "
                    + "   set password = ?"
                    + "   where id = ?";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, Md5.Hash(newPass));
            ps.setBigDecimal(2, id);
            
            return ps.executeUpdate() == 1;
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps);
        }
        
        return false;
    }
    
    public boolean delete(BigDecimal id){
        
        Connection conn = null;
        PreparedStatement ps = null;
        
        try{
            String sql = "delete from admin "
                    + "   where id = ?";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setBigDecimal(1, id);
            
            return ps.executeUpdate() == 1;
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps);
        }
        
        return false;
    }
    
    public List<Admin> find(String username, Integer status){
        
        List<Admin> list = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            String sqlBonus = "";
            Vector vParams = new Vector();
            if(username != null && !"".equals(username)){
                sqlBonus += "and upper(username) like ?";
                vParams.add("%" + username.toUpperCase() + "%");
            }
            if(status != null){
                sqlBonus += " and status = ?";
                vParams.add(status);
            }
            
            String sql = "select id, username, password, name, status, right_"
                    + "   where 1 = 1 " + sqlBonus;
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            int i = 1;
            for(Object o : vParams){
                ps.setString(i++, o.toString());
            }
            rs = ps.executeQuery();
            Admin admin = null;
            while(rs.next()){
                i = 1;
                if(list == null) list = new ArrayList<>();
                admin = new Admin();
                admin.setId(rs.getBigDecimal(i++));
                admin.setUsername(rs.getString(i++));
                admin.setPassword(rs.getString(i++));
                admin.setName(rs.getString(i++));
                admin.setStatus(rs.getInt(i++));
                admin.setRight(rs.getInt(i++));
                list.add(admin);
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        
        return list;
    }
    
    public Admin login(String username, String password){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Admin admin = null;
        
        try{
            String sql = "select id, username, password, name, right_, status"
                    + "          from admin"
                    + "   where upper(username) = upper(?) and password = ?";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, Md5.Hash(password));
            rs = ps.executeQuery();
                        
            if(rs.next()){                
                int i = 1;
                admin = new Admin();
                admin.setId(rs.getBigDecimal(i++));
                admin.setUsername(rs.getString(i++));
                admin.setPassword(rs.getString(i++));
                admin.setName(rs.getString(i++));
                admin.setRight(rs.getInt(i++));
                admin.setStatus(rs.getInt(i++));
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        return admin;
    }
    
    public List<Admin> find(String username, Integer status, int currentPage, int rowsPerPage){
        
        List<Admin> list = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int startRow = (currentPage - 1) * rowsPerPage;
        
        try{
            String sqlBonus = "";
            Vector vParams = new Vector();
            if(username != null && !"".equals(username)){
                sqlBonus += "and upper(username) like ?";
                vParams.add("%" + username.toUpperCase() + "%");
            }
            if(status != null){
                sqlBonus += " and status = ?";
                vParams.add(status);
            }
            
            String sql = "select id, username, password, name, status, right_"
                    + "   from admin"
                    + "   where 1 = 1 " + sqlBonus
                    + "   order by id desc"
                    + "   limit ?,?";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            int i = 1;
            for(Object o : vParams){
                ps.setString(i++, o.toString());
            }
            ps.setInt(i++, startRow);
            ps.setInt(i++, rowsPerPage);
            rs = ps.executeQuery();
            Admin admin = null;
            while(rs.next()){
                i = 1;
                if(list == null) list = new ArrayList<>();
                admin = new Admin();
                admin.setId(rs.getBigDecimal(i++));
                admin.setUsername(rs.getString(i++));
                admin.setPassword(rs.getString(i++));
                admin.setName(rs.getString(i++));
                admin.setStatus(rs.getInt(i++));
                admin.setRight(rs.getInt(i++));
                list.add(admin);
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        
        return list;
    }
    
    public int count(String username, Integer status){
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            String sqlBonus = "";
            Vector vParams = new Vector();
            if(username != null && !"".equals(username)){
                sqlBonus += " and upper(username) like ?";
                vParams.add("%" + username.toUpperCase() + "%");
            }
            if(status != null){
                sqlBonus += " and status = ?";
                vParams.add(status);
            }
            
            String sql = "select count(id)"
                    + "   from admin"
                    + "   where 1 = 1 " + sqlBonus;
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            int i = 1;
            for(Object o : vParams){
                ps.setString(i++, o.toString());
            }
            rs = ps.executeQuery();
            if(rs.next()) return rs.getInt(1);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        
        return 0;
    }
    
    public Admin findById(BigDecimal id){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Admin admin = null;
        
        try{
            String sql = "select id, username, password, name, right_, status"
                    + "          from admin"
                    + "   where id = ?";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setBigDecimal(1, id);
            rs = ps.executeQuery();
                        
            if(rs.next()){                
                int i = 1;
                admin = new Admin();
                admin.setId(rs.getBigDecimal(i++));
                admin.setUsername(rs.getString(i++));
                admin.setPassword(rs.getString(i++));
                admin.setName(rs.getString(i++));
                admin.setRight(rs.getInt(i++));
                admin.setStatus(rs.getInt(i++));
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        return admin;
    }
    
    public static void main(String[] args) {
        AdminDAO adminDAO = new AdminDAO();
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setName("admin");
        admin.setStatus(Admin.STATUS_ACTIVE);
        admin.setRight(1);
        //System.out.println(adminDAO.create(admin));
    }
}
