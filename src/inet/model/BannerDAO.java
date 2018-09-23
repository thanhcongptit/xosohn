/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.model;

import inet.bean.Banner;
import inet.pool.DBPoolX;
import inet.pool.DBPoolXName;
import inet.util.Logger;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 24h
 */
public class BannerDAO {
    
    private DBPoolX poolX = null;
    Logger logger = new Logger(this.getClass().getName());

    public BannerDAO() {
        try {
            poolX = DBPoolX.getInstance(DBPoolXName.SERVICE_LOTTERY);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
    public boolean create(Banner banner){
    
        Connection conn = null;
        PreparedStatement ps = null;
        
        try{
            String sql = "insert into banner(page, code, position, gen_date, description)"
                    + "   values(?, ?, ?, now(), ?)";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, banner.getPage());
            ps.setString(2, banner.getCode());
            ps.setInt(3, banner.getPosition());
            ps.setString(4, banner.getDesc());
            
            return ps.executeUpdate() == 1;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps);
        }
        
        return false;
    }
    
    public List<Banner> findAll(String page){
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Banner> list = null;
        
        try{
            String sql = "select id, page, code, position, gen_date, last_updated, description, status"
                    + "   from banner "                    
                    + "   where upper(page) = upper(?) "
                    + "   order by position asc";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, page);
            rs = ps.executeQuery();
            
            Banner banner = null;
            int i = 1;
            while(rs.next()){
                if(list == null) list = new ArrayList<>();
                banner = new Banner();
                i = 1;
                banner.setId(rs.getBigDecimal(i++));
                banner.setPage(rs.getString(i++));
                banner.setCode(rs.getString(i++));
                banner.setPosition(rs.getInt(i++));
                banner.setGenDate(rs.getTimestamp(i++));
                banner.setLastUpdated(rs.getTimestamp(i++));
                banner.setDesc(rs.getString(i++));
                banner.setStatus(rs.getInt(i++));
                list.add(banner);
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        return list;
    }
    
    public Banner find(String page, int position){
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Banner banner = null;
        
        try{
            String sql = "select id, page, code, position, gen_date, last_updated, description"
                    + "   from banner "                    
                    + "   where upper(page) = upper(?) and code !='' and position = ? and status = 1";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, page);
            ps.setInt(2, position);
            rs = ps.executeQuery();
            
            int i = 1;
            if(rs.next()){
                banner = new Banner();
                i = 1;
                banner.setId(rs.getBigDecimal(i++));
                banner.setPage(rs.getString(i++));
                banner.setCode(rs.getString(i++));
                banner.setPosition(rs.getInt(i++));
                banner.setGenDate(rs.getTimestamp(i++));
                banner.setLastUpdated(rs.getTimestamp(i++));
                banner.setDesc(rs.getString(i++));
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        return banner;
    }
    
    public Banner findById(BigDecimal id){
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Banner banner = null;
        
        try{
            String sql = "select id, page, code, position, gen_date, last_updated, description"
                    + "   from banner "                    
                    + "   where id = ?";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setBigDecimal(1, id);
            rs = ps.executeQuery();
            
            int i = 1;
            if(rs.next()){
                banner = new Banner();
                i = 1;
                banner.setId(rs.getBigDecimal(i++));
                banner.setPage(rs.getString(i++));
                banner.setCode(rs.getString(i++));
                banner.setPosition(rs.getInt(i++));
                banner.setGenDate(rs.getTimestamp(i++));
                banner.setLastUpdated(rs.getTimestamp(i++));
                banner.setDesc(rs.getString(i++));
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        return banner;
    }
    
    public boolean update(String code, BigDecimal id){
    
        Connection conn = null;
        PreparedStatement ps = null;
        
        try{
            String sql = "update banner"
                    + "   set code = ?, last_updated = now()"
                    + "   where id = ?";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, code);
            ps.setBigDecimal(2, id);
            
            return ps.executeUpdate() == 1;            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps);
        }
        
        return false;
    }
    
    public boolean updateStatus(int status, BigDecimal id){
        
        Connection conn = null;
        PreparedStatement ps = null;
        
        try{
            String sql = "update banner set status = ?"
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
}
