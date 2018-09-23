/*
fi * To change this license header, choose License Headers in Project Properties.
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

import inet.bean.Dream;
import inet.pool.DBPoolX;
import inet.pool.DBPoolXName;
import inet.util.LOBs;
import inet.util.Logger;

/**
 *
 * @author Huyen
 */
public class NewsSCMBChinhXacDAO {
	private DBPoolX poolX = null;
	Logger logger = new Logger(this.getClass().getName());

	public NewsSCMBChinhXacDAO() {
		try {
			poolX = DBPoolX.getInstance(DBPoolXName.SERVICE_LOTTERY);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public byte[] getImages(BigDecimal id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		String strSQL = null;
		byte[] data = null;

		try {
			conn = poolX.getConnection();
			strSQL = "SELECT IMAGE_TITLE FROM news_scmb_chinhxac WHERE ID = ?";
			preStmt = conn.prepareStatement(strSQL);
			preStmt.setBigDecimal(1, id);
			rs = preStmt.executeQuery();
			if (rs.next()) {
				if (rs.getBlob(1) != null) {
					data = LOBs.readByteBlob(rs.getBlob(1));
				}
			}
		} catch (SQLException e) {
			System.out.println("getImages: Error executing " + strSQL + ">>>" + e.toString());
		} finally {
			poolX.releaseConnection(conn, preStmt, rs);
		}
		return data;
	}

	public Dream getRow(BigDecimal id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		String strSQL = null;
		Dream news_scmb_chinhxac = null;

		try {
			conn = poolX.getConnection();
			strSQL = "   select id, title, description, content, " + "          publish_date,image_url, titleSeo, metaSeo, keyword "
					+ "   from news_scmb_chinhxac " + "   where id = ?";
			preStmt = conn.prepareStatement(strSQL);
			preStmt.setBigDecimal(1, id);
			rs = preStmt.executeQuery();

			if (rs.next()) {
				news_scmb_chinhxac = new Dream();
				news_scmb_chinhxac.setId(rs.getBigDecimal(1));
				news_scmb_chinhxac.setTitle(rs.getString(2));

				news_scmb_chinhxac.setDescription(rs.getString(3));
				news_scmb_chinhxac.setContent(rs.getString(4));
				news_scmb_chinhxac.setPublishDate(rs.getTimestamp(5));
				news_scmb_chinhxac.setImageUrl(rs.getString(6));
				news_scmb_chinhxac.setTitleSeo(rs.getString(7));
				news_scmb_chinhxac.setMetaSeo(rs.getString(8));
				news_scmb_chinhxac.setKeyword(rs.getString(9));
			}
		} catch (SQLException e) {
			System.out.println("getRow: Error executing " + strSQL + ">>>" + e.toString());
		} finally {
			poolX.releaseConnection(conn, preStmt, rs);
		}
		return news_scmb_chinhxac;
	}

	public Dream getRowTitle(BigDecimal id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		String strSQL = null;
		Dream news_scmb_chinhxac = null;

		try {
			conn = poolX.getConnection();
			strSQL = "   select  title, description, titleSeo, metaSeo, keyword, publish_date" + "   from news_scmb_chinhxac " + "   where id = ?";
			preStmt = conn.prepareStatement(strSQL);
			preStmt.setBigDecimal(1, id);
			rs = preStmt.executeQuery();
			if (rs.next()) {
				news_scmb_chinhxac = new Dream();
				news_scmb_chinhxac.setTitle(rs.getString(1));
				news_scmb_chinhxac.setDescription(rs.getString(2));
				news_scmb_chinhxac.setTitleSeo(rs.getString(3));
				news_scmb_chinhxac.setMetaSeo(rs.getString(4));
				news_scmb_chinhxac.setKeyword(rs.getString(5));
				news_scmb_chinhxac.setPublishDate(rs.getTimestamp(6));
			}
		} catch (SQLException e) {
			System.out.println("getRow: Error executing " + strSQL + ">>>" + e.toString());
		} finally {
			poolX.releaseConnection(conn, preStmt, rs);
		}
		return news_scmb_chinhxac;
	}

	public List<Dream> findAllIndex() {
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		String strSQL = null;
		List<Dream> cnews_scmb_chinhxacs = new Vector<Dream>();
		Dream news_scmb_chinhxac = null;
		try {
			conn = poolX.getConnection();
			strSQL = "SELECT ID, TITLE, DESCRIPTION, CONTENT, " + " PUBLISH_DATE FROM news_scmb_chinhxac "
					+ "WHERE ROWNUM<=10 AND STATUS=1 ORDER BY  PUBLISH_DATE DESC ";
			preStmt = conn.prepareStatement(strSQL);
			rs = preStmt.executeQuery();
			while (rs.next()) {
				news_scmb_chinhxac = new Dream();
				news_scmb_chinhxac.setId(rs.getBigDecimal(1));
				news_scmb_chinhxac.setTitle(rs.getString(2));

				news_scmb_chinhxac.setDescription(rs.getString(3));
				news_scmb_chinhxac.setContent(rs.getString(4));

				news_scmb_chinhxac.setPublishDate(rs.getTimestamp(5));
				cnews_scmb_chinhxacs.add(news_scmb_chinhxac);
			}
		} catch (SQLException e) {
			System.out.println("findAllIndex: Error executing " + strSQL + ">>>" + e.toString());
		} catch (Exception e) {
			System.out.println("findAllIndex: Error executing " + e.toString());
		} finally {
			poolX.releaseConnection(conn, preStmt, rs);
		}
		return cnews_scmb_chinhxacs;
	}

	public int count() {
		Connection conn = null;
		PreparedStatement preStmt = null;
		String strSQL = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = poolX.getConnection();
			strSQL = " select count(id) from news_scmb_chinhxac " + " where status=1 ";
			preStmt = conn.prepareStatement(strSQL);
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

	public List findByPage(int currPage, int rowsPerPage) {
		if (currPage < 1 || rowsPerPage < 1) {
			return null;
		}
		int startRow = (currPage - 1) * rowsPerPage;
		// int stopRow = currPage * rowsPerPage;

		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		String strSQL = null;
		List<Dream> cnews_scmb_chinhxacs = new Vector<Dream>();
		Dream news_scmb_chinhxac = null;
		try {
			conn = poolX.getConnection();
			strSQL = " select id, title, description, content, " + "        publish_date,image_url " + " from news_scmb_chinhxac "
					+ " where status=1 " + " order by publish_date desc " + " limit ?, ?";
			preStmt = conn.prepareStatement(strSQL);
			preStmt.setInt(1, startRow);
			preStmt.setInt(2, rowsPerPage);
			rs = preStmt.executeQuery();
			while (rs.next()) {
				news_scmb_chinhxac = new Dream();
				news_scmb_chinhxac.setId(rs.getBigDecimal(1));
				news_scmb_chinhxac.setTitle(rs.getString(2));

				news_scmb_chinhxac.setDescription(rs.getString(3));
				news_scmb_chinhxac.setContent(rs.getString(4));

				news_scmb_chinhxac.setPublishDate(rs.getTimestamp(5));
				news_scmb_chinhxac.setImageUrl(rs.getString(6));
				cnews_scmb_chinhxacs.add(news_scmb_chinhxac);
			}
		} catch (SQLException e) {
			System.out.println("findSearch: Error executing SQL " + strSQL + ">>>" + e.toString());
		} finally {
			poolX.releaseConnection(conn, preStmt, rs);
		}
		return cnews_scmb_chinhxacs;
	}

	public List<Dream> find(int currentPage, int rowsPerPage, String title) {

		int startRow = (currentPage - 1) * rowsPerPage;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Dream> list = null;
		try {
			String sqlBonus = "";
			Vector vParams = new Vector();
			if (title != null && !"".equals(title)) {
				sqlBonus += " upper(title) like ?";
				vParams.add("%" + title.toUpperCase() + "%");
			}
			String sql = " select id, title, image_url, description, content, " + "            publish_date,status, keyword "
					+ "     from news_scmb_chinhxac " + "  order by publish_date desc "
					+ "      limit ?, ?";
			conn = poolX.getConnection();
			ps = conn.prepareStatement(sql);
			System.out.println("sql=" + sql);
			int i = 1;
			for (Object o : vParams) {
				ps.setString(i++, o.toString());
			}
			ps.setInt(i++, startRow);
			ps.setInt(i++, rowsPerPage);
			rs = ps.executeQuery();
			Dream news_scmb_chinhxac = null;

			while (rs.next()) {
				news_scmb_chinhxac = new Dream();
				if (list == null)
					list = new ArrayList<>();
				i = 1;
				news_scmb_chinhxac.setId(rs.getBigDecimal(i++));
				news_scmb_chinhxac.setTitle(rs.getString(i++));
				news_scmb_chinhxac.setImageUrl(rs.getString(i++));
				news_scmb_chinhxac.setDescription(rs.getString(i++));
				news_scmb_chinhxac.setContent(rs.getString(i++));
				news_scmb_chinhxac.setPublishDate(rs.getTimestamp(i++));
				news_scmb_chinhxac.setStatus(rs.getInt(i++));
				news_scmb_chinhxac.setKeyword(rs.getString(i++));
				list.add(news_scmb_chinhxac);
			}
			// System.out.println("list="+list.size());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}
		return list;
	}

	public int count(int currentPage, int rowsPerPage, String title) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sqlBonus = "";
			Vector vParams = new Vector();
			if (title != null && !"".equals(title)) {
				sqlBonus += " upper(title) like ?";
				vParams.add("%" + title.toUpperCase() + "%");
			}
			String sql = " select count(id) " + "     from news_scmb_chinhxac " ;
			conn = poolX.getConnection();
			ps = conn.prepareStatement(sql);
			int i = 1;
			for (Object o : vParams) {
				ps.setString(i++, o.toString());
			}
			rs = ps.executeQuery();
			if (rs.next())
				return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}
		return 0;
	}

	public boolean create(Dream news_scmb_chinhxac) {

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String sql = "INSERT INTO news_scmb_chinhxac(title, description, gen_date, content, status, "
					+ "                     publish_date, image_url, titleSeo, metaSeo, keyword) "
					+ "   VALUES (?,?,now(),?,?,now(),?, ?, ?,?)";
			conn = poolX.getConnection();
			ps = conn.prepareStatement(sql);
			int i = 1;
			ps.setString(i++, news_scmb_chinhxac.getTitle());
			ps.setString(i++, news_scmb_chinhxac.getDescription());
			ps.setString(i++, news_scmb_chinhxac.getContent());
			ps.setInt(i++, news_scmb_chinhxac.getStatus());
			ps.setString(i++, news_scmb_chinhxac.getImageUrl());
			ps.setString(i++, news_scmb_chinhxac.getTitleSeo());
			ps.setString(i++, news_scmb_chinhxac.getMetaSeo());
			ps.setString(i++, news_scmb_chinhxac.getKeyword());

			return ps.executeUpdate() == 1;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			poolX.releaseConnection(conn, ps);
		}

		return false;
	}

	public boolean updateStatus(int status, BigDecimal id) {

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String sql = "update news_scmb_chinhxac set status = ?" + "   where id = ?";
			conn = poolX.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, status);
			ps.setBigDecimal(2, id);

			return ps.executeUpdate() == 1;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			poolX.releaseConnection(conn, ps);
		}

		return false;
	}

	public boolean delete(BigDecimal id) {

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String sql = "delete from news_scmb_chinhxac" + "   where id = ?";
			conn = poolX.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setBigDecimal(1, id);

			return ps.executeUpdate() == 1;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			poolX.releaseConnection(conn, ps);
		}

		return false;
	}

	public boolean update(Dream news_scmb_chinhxac) {

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String sql = "update news_scmb_chinhxac " + "   set title = ?, description = ?, content = ?, image_url = ?, titleSeo = ?, metaSeo = ?, keyword =?"
					+ "   where id = ?";
			conn = poolX.getConnection();
			ps = conn.prepareStatement(sql);
			int i = 1;
			ps.setString(i++, news_scmb_chinhxac.getTitle());
			ps.setString(i++, news_scmb_chinhxac.getDescription());
		 	ps.setString(i++, news_scmb_chinhxac.getContent());
			ps.setString(i++, news_scmb_chinhxac.getImageUrl());
			ps.setString(i++, news_scmb_chinhxac.getTitleSeo());
			ps.setString(i++, news_scmb_chinhxac.getMetaSeo());
			ps.setString(i++, news_scmb_chinhxac.getKeyword());
			ps.setBigDecimal(i++, news_scmb_chinhxac.getId());

			return ps.executeUpdate() == 1;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			poolX.releaseConnection(conn, ps);
		}

		return false;
	}
	
}
