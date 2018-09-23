/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.model;

import inet.bean.Description;
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
public class DescriptionDao {
    
    private DBPoolX poolX = null;
    Logger logger = new Logger(this.getClass().getName());

    public DescriptionDao() {
        try {
            poolX = DBPoolX.getInstance(DBPoolXName.SERVICE_LOTTERY);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
    public boolean create(Description des){
    
        Connection conn = null;
        PreparedStatement ps = null;
        
        try{
            String sql = "insert into description(code, position, gen_date, last_updated)"
                    + "   values(?, ?, now(), now())";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, des.getCode());
            
            return ps.executeUpdate() == 1;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps);
        }
        
        return false;
    }
    
    public List<Description> findAll(){
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Description> list = null;
        
        try{
            String sql = "select *"
                    + "   from description "          
                    + "   order by position asc";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            Description des = null;
            int i = 1;
            while(rs.next()){
                if(list == null) list = new ArrayList<>();
                des = new Description();
                i = 1;
                des.setId(rs.getBigDecimal(i++));
                des.setCode(rs.getString(i++));
                des.setPosition(rs.getInt(i++));
                des.setGenDate(rs.getTimestamp(i++));
                des.setLastUpdated(rs.getTimestamp(i++));
                list.add(des);
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        return list;
    }
    
    public Description find(int position){
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Description des = null;
        
        try{
            String sql = "select id, code, position, gen_date, last_updated"
                    + "   from description "                    
                    + "   where position = ?";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, position);
            rs = ps.executeQuery();
            
            int i = 1;
            if(rs.next()){
            	des = new Description();
                i = 1;
                des.setId(rs.getBigDecimal(i++));
                des.setCode(rs.getString(i++));
                des.setPosition(rs.getInt(i++));
                des.setGenDate(rs.getTimestamp(i++));
                des.setLastUpdated(rs.getTimestamp(i++));
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        return des;
    }
    
    public Description findById(BigDecimal id){
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Description des = null;
        
        try{
            String sql = "select id, code, position, gen_date, last_updated"
                    + "   from description "                    
                    + "   where id = ?";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setBigDecimal(1, id);
            rs = ps.executeQuery();
            
            int i = 1;
            if(rs.next()){
            	des = new Description();
                i = 1;
                des.setId(rs.getBigDecimal(i++));
                des.setCode(rs.getString(i++));
                des.setPosition(rs.getInt(i++));
                des.setGenDate(rs.getTimestamp(i++));
                des.setLastUpdated(rs.getTimestamp(i++));
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        return des;
    }
    
    public boolean update(Description dest){
    
        Connection conn = null;
        PreparedStatement ps = null;
        
        try{
            String sql = "update description"
                    + "   set code = ?, last_updated = now()"
                    + "   where id = ?";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, dest.getCode());
            ps.setBigDecimal(2, dest.getId());
            
            return ps.executeUpdate() == 1;            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps);
        }
        
        return false;
    }
}
