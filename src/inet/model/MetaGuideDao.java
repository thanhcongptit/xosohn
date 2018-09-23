package inet.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import inet.bean.MetaGuide;

import inet.pool.DBPoolX;
import inet.pool.DBPoolXName;
import inet.util.Logger;

public class MetaGuideDao {
	private DBPoolX poolX = null;
	Logger logger = new Logger(this.getClass().getName());

	public MetaGuideDao() {
		try {
			poolX = DBPoolX.getInstance(DBPoolXName.SERVICE_LOTTERY);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public List<MetaGuide> findAll() {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<MetaGuide> list = null;

		try {
			String sql = "select id, title, guide, footer, meta, position, page, last_updated, keyword" + "   from meta_guide "
					+ "   order by position asc";
			conn = poolX.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				if (list == null) {
					list = new ArrayList<>();
				}

				MetaGuide metaGuide = new MetaGuide();
				metaGuide.setId(rs.getInt(1));
				metaGuide.setTitle(rs.getString(2));
				metaGuide.setGuide(rs.getString(3));
				metaGuide.setFooter(rs.getString(4));
				metaGuide.setMeta(rs.getString(5));
				metaGuide.setPosition(rs.getInt(6));
				metaGuide.setPage(rs.getString(7));
				metaGuide.setLastUpdated(rs.getTimestamp(8));
				metaGuide.setKeyword(rs.getString(9));
				list.add(metaGuide);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}
		return list;
	}

	public MetaGuide findByPosition(int position) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MetaGuide metaGuide = null;

		try {
			String sql = "select title, guide, footer, meta, page, keyword" + "   from meta_guide " + "   where position = ?";
			conn = poolX.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, position);
			rs = ps.executeQuery();

			if (rs.next()) {
				metaGuide = new MetaGuide();
				metaGuide.setTitle(rs.getString(1));
				metaGuide.setGuide(rs.getString(2));
				metaGuide.setFooter(rs.getString(3));
				metaGuide.setMeta(rs.getString(4));
				metaGuide.setPage(rs.getString(5));
				metaGuide.setKeyword(rs.getString(6));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}
		return metaGuide;
	}

	public boolean update(MetaGuide metaGuide, int position) {

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String sql = "update meta_guide" + "   set title = ?, meta = ?, guide = ?, footer =?, keyword = ?, last_updated = now()"
					+ "   where position = ?";
			conn = poolX.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, metaGuide.getTitle());
			ps.setString(2, metaGuide.getMeta());
			ps.setString(3, metaGuide.getGuide());
			ps.setString(4, metaGuide.getFooter());
			ps.setString(5, metaGuide.getKeyword());
			ps.setInt(6, position);

			return ps.executeUpdate() == 1;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			poolX.releaseConnection(conn, ps);
		}

		return false;
	}
}
