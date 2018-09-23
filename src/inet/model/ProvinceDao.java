/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import inet.bean.LotteryCompany;
import inet.pool.DBPoolX;
import inet.pool.DBPoolXName;
import inet.util.Logger;
import inet.util.UTF8Tool;

/**
 *
 * @author iNET
 */
public class ProvinceDao {

	private Logger logger = null;
	private DBPoolX poolX = null;

	public ProvinceDao() {
		try {
			logger = new Logger(this.getClass().getName());
			poolX = DBPoolX.getInstance(DBPoolXName.SERVICE_LOTTERY);
		} catch (Exception e) {
		}
	}

	public List<LotteryCompany> findLotteryCompany() {
		List<LotteryCompany> list = null;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		LotteryCompany lotteryCompany = null;
		try {
			conn = poolX.getConnection();
			sql = "select code,name from province order by id desc";
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			list = new ArrayList<LotteryCompany>();
			while (rs.next()) {
				lotteryCompany = new LotteryCompany();
				lotteryCompany.setCode(rs.getString(1));
				lotteryCompany.setCompany(rs.getString(2));
				if (!lotteryCompany.getCode().equalsIgnoreCase("XS")) {
					lotteryCompany.setCompanyLink(UTF8Tool.coDau2KoDau(lotteryCompany.getCompany())
							.replaceAll("[^A-Za-z0-9-]", " ").toUpperCase().trim());
				} else {
					lotteryCompany.setCompanyLink("");
				}
				list.add(lotteryCompany);
			}
		} catch (SQLException e) {
			logger.error("loi sql " + e.toString());
			System.out.println("loi sql " + e.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("loi ngoai le " + e.toString());
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}

		return list;
	}

}
