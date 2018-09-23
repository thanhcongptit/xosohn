/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import inet.bean.News;
import inet.pool.DBPoolX;
import inet.pool.DBPoolXName;
import inet.util.LOBs;
import inet.util.Logger;

/**
 *
 * @author Huyen
 */
public class NewsDAO {
    private  DBPoolX poolX=null;
    Logger logger=new Logger(this.getClass().getName());
	 public NewsDAO(){
            try {
                poolX=DBPoolX.getInstance(DBPoolXName.SERVICE_LOTTERY);
            } catch (Exception e) {
                    // TODO: handle exception
            }	 
	 }
         public byte[] getImages(BigDecimal id){
            Connection conn = null;
            PreparedStatement preStmt = null;
            ResultSet rs = null;
            String strSQL = null;
            byte[] data = null;

            try {
                conn = poolX.getConnection();  
                strSQL = "SELECT IMAGE_TITLE FROM NEWS WHERE ID = ?";
                preStmt = conn.prepareStatement(strSQL);
                preStmt.setBigDecimal(1, id);
                rs = preStmt.executeQuery();
                if (rs.next()) {        
                    if(rs.getBlob(1)!=null){
                        data=LOBs.readByteBlob(rs.getBlob(1));
                    }    
                }
            } catch (SQLException e) {
                System.out.println("getImages: Error executing " + strSQL + ">>>" + e.toString());
            } finally {
                poolX.releaseConnection(conn, preStmt, rs);
            }
            return data;
        }
         public News getRow(BigDecimal id){
            Connection conn = null;
            PreparedStatement preStmt = null;
            ResultSet rs = null;
            String strSQL = null;
            News news = null;

            try {
                conn = poolX.getConnection();
                strSQL="update news set view_counter=view_counter+1 where id=?";
                preStmt = conn.prepareStatement(strSQL);
                preStmt.setBigDecimal(1, id);
                preStmt.executeUpdate();
                preStmt.close();
                strSQL = "   select id, title, image_title, description, content, " +
                         "          source,tags,view_counter,publish_date,image_url, province, titleSeo, metaSeo, keyword "
                       + "   from news "
                       + "   where id = ?";
                preStmt = conn.prepareStatement(strSQL);
                preStmt.setBigDecimal(1, id);
                rs = preStmt.executeQuery();
                if (rs.next()) {
                    news = new News();
                    news.setId(rs.getBigDecimal(1));
                    news.setTitle(rs.getString(2));
                    if(rs.getBlob(3)!=null){
                        news.setImageTitle(LOBs.readByteBlob(rs.getBlob(3)));
                    }
                    news.setDescription(rs.getString(4));
                    news.setContent(rs.getString(5));
                    news.setSource(rs.getString(6));
                    news.setTag(rs.getString(7));
                    news.setViewCounter(rs.getInt(8));
                    news.setPublishDate(rs.getTimestamp(9));
                    news.setImageUrl(rs.getString(10));
                    news.setProvince(rs.getString(11));
                    news.setTitleSeo(rs.getString(12));
                    news.setMetaSeo(rs.getString(13));
                    news.setKeyword(rs.getString(14));
                }
            } catch (SQLException e) {
                System.out.println("getRow: Error executing " + strSQL + ">>>" + e.toString());
            } finally {
                poolX.releaseConnection(conn, preStmt, rs);
            }
            return news;
        }
         
         
         public List<News> findAllIndex(){
            Connection conn = null;
            PreparedStatement preStmt = null;
            ResultSet rs = null;
            String strSQL = null;
            List<News> cNews=new Vector<News>();
            News news=null; 
            try {
                conn = poolX.getConnection();
                 strSQL = "SELECT ID, TITLE, IMAGE_TITLE, DESCRIPTION, CONTENT, " +
                            " SOURCE,TAGS,VIEW_COUNTER,PUBLISH_DATE FROM NEWS "
                         + "WHERE CATEGORY_ID=? AND ROWNUM<=10 AND STATUS=1 ORDER BY  PUBLISH_DATE DESC ";
                preStmt = conn.prepareStatement(strSQL);     
                preStmt.setInt(1, News.XOSO_CAT_ID);
                rs = preStmt.executeQuery();
                while (rs.next()) {
                    news=new News();
                    news.setId(rs.getBigDecimal(1));
                    news.setTitle(rs.getString(2));
                    if(rs.getBlob(3)!=null){
                        news.setImageTitle(LOBs.readByteBlob(rs.getBlob(3)));
                    }
                    news.setDescription(rs.getString(4));
                    news.setContent(rs.getString(5));
                    news.setSource(rs.getString(6));
                    news.setTag(rs.getString(7));
                    news.setViewCounter(rs.getInt(8));
                    news.setPublishDate(rs.getTimestamp(9));
                    cNews.add(news);
                }
            } catch (SQLException e) {
                 System.out.println("findAllIndex: Error executing " + strSQL + ">>>" + e.toString());
            }catch(Exception e){
                System.out.println("findAllIndex: Error executing " + e.toString());
            }finally {
                poolX.releaseConnection(conn, preStmt, rs);
            }
            return cNews;
    }
         
    public int count(){    	
        Connection conn = null;
        PreparedStatement preStmt = null;
        String strSQL = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = poolX.getConnection();
            strSQL = " select count(id) from news " +
            		 " where category_id=?  AND status=1 ";
            preStmt = conn.prepareStatement(strSQL);
            preStmt.setInt(1, News.XOSO_CAT_ID);
            rs = preStmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            poolX.releaseConnection(conn, preStmt, rs);
        }
        return count;
    }
    
    public int countProvince(String province){    	
        Connection conn = null;
        PreparedStatement preStmt = null;
        String strSQL = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = poolX.getConnection();
            strSQL = " select count(id) from news " +
            		 " where category_id=? and PROVINCE = ? AND status=1 ";
            preStmt = conn.prepareStatement(strSQL);
            preStmt.setInt(1, News.XOSO_CAT_ID);
            preStmt.setString(2, province.toUpperCase());
            rs = preStmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            poolX.releaseConnection(conn, preStmt, rs);
        }
        return count;
    }
    
    public int countRegion(String region){    	
        Connection conn = null;
        PreparedStatement preStmt = null;
        String strSQL = null;
        ResultSet rs = null;
        int count = 0;
        if(region.equalsIgnoreCase("MIEN TRUNG")) {
        		region = "MT";
        } 
        
        if(region.equalsIgnoreCase("MIEN NAM")) {
        		region = "MN";
        }
        try {
            conn = poolX.getConnection();
            strSQL = " select count(id) from news " +
            		 " where category_id=? and PROVINCE IN (select province from lottery_company where region = ? ) AND status=1 ";
            preStmt = conn.prepareStatement(strSQL);
            preStmt.setInt(1, News.XOSO_CAT_ID);
            preStmt.setString(2, region.toUpperCase());
            rs = preStmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            poolX.releaseConnection(conn, preStmt, rs);
        }
        return count;
    }
    
    public List findByPage( int currPage, int rowsPerPage){
    	if (currPage < 1 || rowsPerPage < 1) {
            return null;
        } 
        int startRow = (currPage - 1) * rowsPerPage;
        //int stopRow  = currPage * rowsPerPage;
        
        Connection conn = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        String strSQL = null;
        List<News> cNews=new Vector<News>();
        News news=null; 
        try {
            conn = poolX.getConnection();
            strSQL = " select id, title, image_title, description, content, " +
                     "        source,tags,view_counter,publish_date,image_url " +
                     " from news " +
	             " where status=1 and category_id=? " +
                    " order by publish_date desc " +
                    " limit ?, ?";
            preStmt = conn.prepareStatement(strSQL);
            preStmt.setInt(1, News.XOSO_CAT_ID);
            preStmt.setInt(2, startRow);
            preStmt.setInt(3, rowsPerPage);        
            rs = preStmt.executeQuery();
            while (rs.next()) {
                news=new News();
                news.setId(rs.getBigDecimal(1));
                news.setTitle(rs.getString(2));
                if(rs.getBlob(3)!=null){
                    news.setImageTitle(LOBs.readByteBlob(rs.getBlob(3)));
                }
                news.setDescription(rs.getString(4));
                news.setContent(rs.getString(5));
                news.setSource(rs.getString(6));
                news.setTag(rs.getString(7));
                news.setViewCounter(rs.getInt(8));
                news.setPublishDate(rs.getTimestamp(9));
                news.setImageUrl(rs.getString(10));
                cNews.add(news);
            }
        } catch(SQLException e) {
            System.out.println("findSearch: Error executing SQL " + strSQL + ">>>" + e.toString());
        } finally {
            poolX.releaseConnection(conn, preStmt, rs);
        }
        return cNews;
    }
    
    public List findByPageProvince( int currPage, int rowsPerPage, String province){
    	if (currPage < 1 || rowsPerPage < 1) {
            return null;
        } 
        int startRow = (currPage - 1) * rowsPerPage;
        //int stopRow  = currPage * rowsPerPage;
        
        Connection conn = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        String strSQL = null;
        List<News> cNews=new Vector<News>();
        News news=null; 
        try {
            conn = poolX.getConnection();
            strSQL = " select id, title, image_title, description, content, " +
                     "        source,tags,view_counter,publish_date,image_url" +
                     " from news " +
	             " where status=1 and category_id=? and province = ?" +
                    " order by publish_date desc " +
                    " limit ?, ?";
            preStmt = conn.prepareStatement(strSQL);
            preStmt.setInt(1, News.XOSO_CAT_ID);
            preStmt.setString(2, province.toUpperCase());
            preStmt.setInt(3, startRow);
            preStmt.setInt(4, rowsPerPage);        
            rs = preStmt.executeQuery();
            while (rs.next()) {
                news=new News();
                news.setId(rs.getBigDecimal(1));
                news.setTitle(rs.getString(2));
                if(rs.getBlob(3)!=null){
                    news.setImageTitle(LOBs.readByteBlob(rs.getBlob(3)));
                }
                news.setDescription(rs.getString(4));
                news.setContent(rs.getString(5));
                news.setSource(rs.getString(6));
                news.setTag(rs.getString(7));
                news.setViewCounter(rs.getInt(8));
                news.setPublishDate(rs.getTimestamp(9));
                news.setImageUrl(rs.getString(10));
                news.setProvince(province);
                
                cNews.add(news);
            }
        } catch(SQLException e) {
            System.out.println("findSearch: Error executing SQL " + strSQL + ">>>" + e.toString());
        } finally {
            poolX.releaseConnection(conn, preStmt, rs);
        }
        return cNews;
    }
    
    public List findByPageRegion( int currPage, int rowsPerPage, String region){
    	if (currPage < 1 || rowsPerPage < 1) {
            return null;
        } 
        int startRow = (currPage - 1) * rowsPerPage;
        //int stopRow  = currPage * rowsPerPage;
        if(region.equalsIgnoreCase("MIEN TRUNG")) {
	    		region = "MT";
	    } 
	    
	    if(region.equalsIgnoreCase("MIEN NAM")) {
	    		region = "MN";
	    }
        Connection conn = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        String strSQL = null;
        List<News> cNews=new Vector<News>();
        News news=null; 
        try {
            conn = poolX.getConnection();
            strSQL = " select id, title, image_title, description, content, " +
                     "        source,tags,view_counter,publish_date,image_url, province" +
                     " from news " +
	             " where status=1 and category_id=? and  PROVINCE IN (select province from lottery_company where region = ? )" +
                    " order by publish_date desc " +
                    " limit ?, ?";
            preStmt = conn.prepareStatement(strSQL);
            preStmt.setInt(1, News.XOSO_CAT_ID);
            preStmt.setString(2, region.toUpperCase());
            preStmt.setInt(3, startRow);
            preStmt.setInt(4, rowsPerPage);        
            rs = preStmt.executeQuery();
            while (rs.next()) {
                news=new News();
                news.setId(rs.getBigDecimal(1));
                news.setTitle(rs.getString(2));
                if(rs.getBlob(3)!=null){
                    news.setImageTitle(LOBs.readByteBlob(rs.getBlob(3)));
                }
                news.setDescription(rs.getString(4));
                news.setContent(rs.getString(5));
                news.setSource(rs.getString(6));
                news.setTag(rs.getString(7));
                news.setViewCounter(rs.getInt(8));
                news.setPublishDate(rs.getTimestamp(9));
                news.setImageUrl(rs.getString(10));
                news.setProvince(rs.getString(11));
                
                cNews.add(news);
            }
        } catch(SQLException e) {
            System.out.println("findSearch: Error executing SQL " + strSQL + ">>>" + e.toString());
        } finally {
            poolX.releaseConnection(conn, preStmt, rs);
        }
        return cNews;
    }
    
    public List<News> find(int currentPage, int rowsPerPage, String title){
        
        int startRow = (currentPage - 1) * rowsPerPage;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<News> list = null;
        try {
            String sqlBonus = "";
            Vector vParams = new Vector();
            if(title != null && !"".equals(title)){
                sqlBonus += " and upper(title) like ?";
                vParams.add("%"+title.toUpperCase()+"%");
            }
            String sql = " select id, title, image_url, description, content, " +
                     "            source,tags,view_counter,publish_date,status, province, keyword " +
                     "     from news " +
	             "     where category_id= ? " + sqlBonus +
                    "      order by publish_date desc " +
                    "      limit ?, ?";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            System.out.println("sql="+sql);
            int i = 1;
            ps.setInt(i++, News.XOSO_CAT_ID);
            for(Object o : vParams){
                ps.setString(i++, o.toString());
            }
            ps.setInt(i++, startRow);
            ps.setInt(i++, rowsPerPage);
            rs = ps.executeQuery();
            News news = null;
            while (rs.next()) {
                news = new News();
                if(list == null) list = new ArrayList<>();
                i = 1;
                news.setId(rs.getBigDecimal(i++));
                news.setTitle(rs.getString(i++));
                news.setImageUrl(rs.getString(i++));
                news.setDescription(rs.getString(i++));
                news.setContent(rs.getString(i++));
                news.setSource(rs.getString(i++));
                news.setTag(rs.getString(i++));
                news.setViewCounter(rs.getInt(i++));
                news.setPublishDate(rs.getTimestamp(i++));
                news.setStatus(rs.getInt(i++));
                news.setProvince(rs.getString(i++));
                news.setKeyword(rs.getString(i++));
                list.add(news);
            }
            //System.out.println("list="+list.size());
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            poolX.releaseConnection(conn, ps, rs);
        }
        return list;
    }
    
    public int count(int currentPage, int rowsPerPage, String title){
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sqlBonus = "";
            Vector vParams = new Vector();
            if(title != null && !"".equals(title)){
                sqlBonus += " and upper(title) like ?";
                vParams.add("%"+title.toUpperCase()+"%");
            }
            String sql = " select count(id) " +
                     "     from news " +
	             "     where category_id= ? " + sqlBonus;
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setInt(i++, News.XOSO_CAT_ID);
            for(Object o : vParams){
                ps.setString(i++, o.toString());
            }
            rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            poolX.releaseConnection(conn, ps, rs);
        }
        return 0;
    }
         
    public boolean create(News news){
        
        Connection conn = null;
        PreparedStatement ps = null;
        
        try{
            String sql = "INSERT INTO news(title, description, gen_date, source, content, status, "
                    + "                    created_user, tags, publish_date, image_url, province, titleSeo, metaSeo, keyword) "
                    + "   VALUES (?,?,now(),?,?,?,?,?,now(),?,?,?,?, ?)";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, news.getTitle());
            ps.setString(i++, news.getDescription());
            ps.setString(i++, news.getSource());
            ps.setString(i++, news.getContent());
            ps.setInt(i++, news.getStatus());
            ps.setString(i++, news.getCreatedUser());
            ps.setString(i++, news.getTag());
            ps.setString(i++, news.getImageUrl());
            ps.setString(i++, news.getProvince());
            ps.setString(i++, news.getTitleSeo());
            ps.setString(i++, news.getMetaSeo());
            ps.setString(i++, news.getKeyword());
            
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
            String sql = "update news set status = ?"
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
    
    public boolean delete(BigDecimal id){
        
        Connection conn = null;
        PreparedStatement ps = null;
        
        try{
            String sql = "delete from news"
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
    
    public boolean update(News news){
        
        Connection conn = null;
        PreparedStatement ps = null;
        
        try{
            String sql = "update news "
                    + "   set title = ?, description = ?, content = ?, image_url = ?, province = ?, titleSeo = ?, metaSeo = ?, keyword = ?"
                    + "   where id = ?";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, news.getTitle());
            ps.setString(i++, news.getDescription());
            ps.setString(i++, news.getContent());
            ps.setString(i++, news.getImageUrl());
            ps.setString(i++, news.getProvince());
            ps.setString(i++, news.getTitleSeo());
            ps.setString(i++, news.getMetaSeo());
            ps.setString(i++, news.getKeyword());
            ps.setBigDecimal(i++, news.getId());
            
            return ps.executeUpdate() == 1;
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps);
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        NewsDAO newsDAO = new NewsDAO();
        for(int i=0;i<20;i++){
            News news = new News();
            news.setTitle("Tin tức xổ số test " + i);
            news.setDescription("Mô tả của tin tức xổ số test " + i);
            news.setSource("Source của tin tức xổ số test " + i);
            news.setContent("Nội dung của tin tức xổ số test " + i);
            news.setStatus(1);
            news.setCreatedUser("quyetnv");
            news.setTag("tin tuc xo so;du doan xo so");
            System.out.println(i+".rs=" + newsDAO.create(news));
        }
    }
    
    public News getRowTitle(BigDecimal id){
        Connection conn = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        String strSQL = null;
        News news = null;

        try {
            conn = poolX.getConnection();
            strSQL = "   select  title, description, titleSeo, metaSeo, keyword, image_url, publish_date"
                   + "   from news "
                   + "   where id = ?";
            preStmt = conn.prepareStatement(strSQL);
            preStmt.setBigDecimal(1, id);
            rs = preStmt.executeQuery();
            if (rs.next()) {
                news = new News();
                news.setTitle(rs.getString(1));
                news.setDescription(rs.getString(2));
                news.setTitleSeo(rs.getString(3));
                news.setMetaSeo(rs.getString(4));
                news.setKeyword(rs.getString(5));
                news.setImageUrl(rs.getString(6));
                news.setPublishDate(rs.getTimestamp(7));
            }
        } catch (SQLException e) {
            System.out.println("getRow: Error executing " + strSQL + ">>>" + e.toString());
        } finally {
            poolX.releaseConnection(conn, preStmt, rs);
        }
        return news;
    }
}
