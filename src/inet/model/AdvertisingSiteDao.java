package inet.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import inet.bean.AdvertisingSite;

import inet.pool.DBPoolX;
import inet.pool.DBPoolXName;
import inet.util.Logger;

public class AdvertisingSiteDao {
	Logger logger = null;
	private DBPoolX poolX = null;

	public AdvertisingSiteDao() {
		logger = new Logger(this.getClass().getName());
		try {
			poolX = DBPoolX.getInstance(DBPoolXName.SERVICE_LOTTERY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<AdvertisingSite> getAvertisingSite() {
		List<AdvertisingSite> list = null;

		String sql = "select * from avertisingSite";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			conn = poolX.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			list = new ArrayList<>();

			while (rs.next()) {
				AdvertisingSite advertisingSite = new AdvertisingSite();
				advertisingSite.setId(rs.getInt("id"));
				advertisingSite.setIndex(rs.getInt("indexAd"));
				advertisingSite.setLink(rs.getString("link"));
				advertisingSite.setTitle(rs.getString("title"));
				list.add(advertisingSite);
			}

		} catch (SQLException e) {
			logger.log(e.toString());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.toString());
			logger.log(e.toString());
			e.printStackTrace();
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}

		return list;
	}
	
	public List<AdvertisingSite> getAvertisingSite(int index) {
		List<AdvertisingSite> list = null;

		String sql = "select * from avertisingSite where indexAd = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			conn = poolX.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, index);
			rs = ps.executeQuery();
			list = new ArrayList<>();

			while (rs.next()) {
				AdvertisingSite advertisingSite = new AdvertisingSite();
				advertisingSite.setId(rs.getInt("id"));
				advertisingSite.setIndex(rs.getInt("indexAd"));
				advertisingSite.setLink(rs.getString("link"));
				advertisingSite.setTitle(rs.getString("title"));
				list.add(advertisingSite);
			}

		} catch (SQLException e) {
			logger.log(e.toString());
			e.printStackTrace();
		} catch (Exception e) {
			logger.log(e.toString());
			e.printStackTrace();
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}

		return list;
	}

	public boolean deleteLink(int id) {

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String sql = "delete FROM  avertisingSite " + "  where id = ?";
			conn = poolX.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			return ps.executeUpdate() == 1;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			poolX.releaseConnection(conn, ps);
		}

		return false;
	}
	
	public boolean create(AdvertisingSite advertisingSite){
        
        Connection conn = null;
        PreparedStatement ps = null;
        
        try{
            String sql = "insert into avertisingSite(indexAd, link, title)"
                    + "   values(?, ?, ?)";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setInt(i++, advertisingSite.getIndex());
            ps.setString(i++, advertisingSite.getLink());
            ps.setString(i++, advertisingSite.getTitle());
            
            return ps.executeUpdate() == 1;
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps);
        }
        
        return false;
    }
}
