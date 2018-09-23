package inet.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.soicaupro.thongkebacnho.CommonUtil;

import inet.bean.CategorySitemap;
import inet.bean.LotteryResult;
import inet.pool.DBPoolX;
import inet.pool.DBPoolXName;
import inet.util.Logger;

public class CategorySiteMapDAO {

	Logger logger = null;
	private DBPoolX poolX = null;
	private List<LotteryResult> listLottery = null;

	// lay danh sach ket qua xo so theo ham phan tich loto
	public List<LotteryResult> getListLottery() {
		return listLottery;
	}

	public CategorySiteMapDAO() {
		logger = new Logger(this.getClass().getName());
		try {
			poolX = DBPoolX.getInstance(DBPoolXName.SERVICE_LOTTERY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public CategorySitemap getLastestResultOfEachProvince() {
		List<Integer> integers = getIdMaxOfEachProvince();
		String sql = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		CategorySitemap categorySitemap = new CategorySitemap();

		try {
			sql = " select id, code, open_date, last_updated from lottery_result "
					+ " where  code not in ('XSLD', 'XSBDI', 'XSQNG')" + " order by open_date desc LIMIT 80";
			conn = poolX.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			int index = 1;
			while (rs.next()) {
				index = 1;
				Integer id = rs.getInt(index++);
				String code = rs.getString(index++);
				String open_date = rs.getString(index++);
				String time_updated = rs.getString(index++);
				setTimeUpdatedForCategory(categorySitemap, code, open_date, time_updated, true, id, integers);
			}

		} catch (SQLException e) {
			System.out.println(e.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}

		return categorySitemap;
	}
	
	public List<Integer> getIdMaxOfEachProvince() {
		String sql = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		List<Integer> integers = new ArrayList<>();

		try {
			sql = " select max(id) from lottery_result where code not in ('XSLD', 'XSBDI', 'XSQNG') group by code desc";
					
			conn = poolX.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				integers.add(rs.getInt(1));
			}

		} catch (SQLException e) {
			System.out.println(e.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}

		return integers;
	}
	
	public CategorySitemap getLastestNews() {

		String sql = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		CategorySitemap categorySitemap = new CategorySitemap();

		try {
			sql = " SELECT ne.publish_date, co.code " + 
					" FROM xoso.news as ne , xoso.lottery_company as co " + 
					" where ne.id IN  (select max(id) from xoso.news group by(province))  and ne.province = co.province  " + 
					" order by publish_date asc";
			conn = poolX.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			int index = 1;
			while (rs.next()) {
				index = 1;
				String time_updated = rs.getString(index++);
				String code = rs.getString(index++);
				setTimeUpdatedForCategory(categorySitemap, code, "", time_updated, false, null, null);
				
				if(code.equalsIgnoreCase("XSTD")) {
					categorySitemap.setTimeXSTD(time_updated.replace(" ", "T") + "+07:00");
				} else if(CommonUtil.checkRegionMNByCode(code)) {
					categorySitemap.setTimeXSMN(time_updated.replace(" ", "T") + "+07:00");
				} else {
					categorySitemap.setTimeXSMT(time_updated.replace(" ", "T") + "+07:00");
				}
			}

		} catch (SQLException e) {
			System.out.println(e.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}

		return categorySitemap;
	}
	
	public String lastestRegion(String region) {
		String sql = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		String rsString = "";
		try {
			sql = " select ls.open_date, ls.last_updated " + 
					" from lottery_result ls, lottery_company lc " + 
					" where ls.code = lc.code and lc.region = ?" + 
					" order by ls.open_date desc" + 
					" Limit 1";
			conn = poolX.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, region);
			rs = ps.executeQuery();
			int index = 1;
			while (rs.next()) {
				index = 1;
				String open_date = rs.getString(index++);
				String time_updated = rs.getString(index++);
				
				if (CommonUtil.isEmptyString(time_updated)) {
					String open_dates[] = open_date.split(" ");
					
					if(region.equalsIgnoreCase("MT")) {
						time_updated = open_dates[0] + "T" + "16:45:00";
					} else if(region.equalsIgnoreCase("MT")) {
						time_updated = open_dates[0] + "T" + "17:45:00";
					} else {
						time_updated = open_dates[0] + "T" + "18:45:00";
					}
				} 
				
				rsString = time_updated.replace(" ", "T") + "+07:00";
			}

		} catch (SQLException e) {
			System.out.println(e.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}

		return rsString;
	}

	private void setTimeUpdatedForCategory(CategorySitemap categorySitemap, String code, String open_date,
			String time_updated, boolean isChechId, Integer id, List<Integer> integers) {
		if(isChechId) {
			if(!integers.contains(id)) {
				return;
			}
		}
		
		switch (code) {
		case "XSAG":

			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "16:45:00";
			}
			categorySitemap.setTimeXSAG(time_updated.replace(" ", "T") + "+07:00");
			break;
		case "XSTN":
			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "16:45:00";
			}
			categorySitemap.setTimeXSTN(time_updated.replace(" ", "T") + "+07:00");
			
			break;
		case "XSBTH":
			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "16:45:00";
			}
			categorySitemap.setTimeXSBTH(time_updated.replace(" ", "T") + "+07:00");
			break;
		case "XSBDH":
			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "16:45:00";
			}
			categorySitemap.setTimeXSBDH(time_updated.replace(" ", "T") + "+07:00");
			break;
		case "XSQB":
			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "17:45:00";
			}
			categorySitemap.setTimeXSQB(time_updated.replace(" ", "T") + "+07:00");
			break;
		case "XSQT":
			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "17:45:00";
			}
			categorySitemap.setTimeXSQT(time_updated.replace(" ", "T") + "+07:00");
			break;
		case "XSTD":
			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "18:45:00";
			}
			categorySitemap.setTimeXSTD(time_updated.replace(" ", "T") + "+07:00");
			break;
		case "XSDN":
			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "16:45:00";
			}
			categorySitemap.setTimeXSDN(time_updated.replace(" ", "T") + "+07:00");
			break;
		case "XSST":
			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "16:45:00";
			}
			categorySitemap.setTimeXSST(time_updated.replace(" ", "T") + "+07:00");
			break;
		case "XSCT":
			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "16:45:00";
			}
			categorySitemap.setTimeXSCT(time_updated.replace(" ", "T") + "+07:00");
			break;
		case "XSKH":
			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "17:45:00";
			}
			categorySitemap.setTimeXSKH(time_updated.replace(" ", "T") + "+07:00");
			break;
		case "XSDNG":
			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "17:45:00";
			}
			categorySitemap.setTimeXSDNG(time_updated.replace(" ", "T") + "+07:00");
			break;
		case "XSBTR":
			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "16:45:00";
			}
			categorySitemap.setTimeXSBTR(time_updated.replace(" ", "T") + "+07:00");
			
			break;
		case "XSVT":
			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "16:45:00";
			}
			categorySitemap.setTimeXSVT(time_updated.replace(" ", "T") + "+07:00");
			break;
		case "XSBL":
			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "16:45:00";
			}
			categorySitemap.setTimeXSBL(time_updated.replace(" ", "T") + "+07:00");
			break;
		case "XSDLK":
			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "17:45:00";
			}
			categorySitemap.setTimeXSDLK(time_updated.replace(" ", "T") + "+07:00");
			break;
		case "XSQNM":
			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "17:45:00";
			}
			categorySitemap.setTimeXSQNM(time_updated.replace(" ", "T") + "+07:00");
			break;
		case "XSHCM":
			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "16:45:00";
			}
			categorySitemap.setTimeXSHCM(time_updated.replace(" ", "T") + "+07:00");
			break;
		case "XSDT":
			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "16:45:00";
			}
			categorySitemap.setTimeXSDT(time_updated.replace(" ", "T") + "+07:00");
			break;
		case "XSCM":
			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "16:45:00";
			}
			categorySitemap.setTimeXSCM(time_updated.replace(" ", "T") + "+07:00");
			break;
		case "XSPY":
			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "17:45:00";
			}
			categorySitemap.setTimeXSPY(time_updated.replace(" ", "T") + "+07:00");
			break;
		case "XSTTH":
			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "17:45:00";
			}
			categorySitemap.setTimeXSTTH(time_updated.replace(" ", "T") + "+07:00");
			break;
		case "XSTG":
			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "16:45:00";
			}
			categorySitemap.setTimeXSTG(time_updated.replace(" ", "T") + "+07:00");
			break;
		case "XSKG":
			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "16:45:00";
			}
			categorySitemap.setTimeXSKG(time_updated.replace(" ", "T") + "+07:00");
			break;
		case "XSDL":
			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "16:45:00";
			}
			categorySitemap.setTimeXSDL(time_updated.replace(" ", "T") + "+07:00");
			break;
		case "XSKT":
			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "17:45:00";
			}
			categorySitemap.setTimeXSKT(time_updated.replace(" ", "T") + "+07:00");
			break;
		case "XSLA":
			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "17:45:00";
			}
			categorySitemap.setTimeXSLA(time_updated.replace(" ", "T") + "+07:00");
			break;
		case "XSBP":
			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "16:45:00";
			}
			categorySitemap.setTimeXSBP(time_updated.replace(" ", "T") + "+07:00");
			break;
		case "XSHG":
			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "16:45:00";
			}
			categorySitemap.setTimeXSHG(time_updated.replace(" ", "T") + "+07:00");
			break;
		case "XSQNI":
			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "17:45:00";
			}
			categorySitemap.setTimeXSQNI(time_updated.replace(" ", "T") + "+07:00");
			break;
		case "XSDNO":
			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "17:45:00";
			}
			categorySitemap.setTimeXSDNO(time_updated.replace(" ", "T") + "+07:00");
			break;
		case "XSVL":
			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "16:45:00";
			}
			categorySitemap.setTimeXSVL(time_updated.replace(" ", "T") + "+07:00");
			break;
		case "XSBD":
			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "16:45:00";
			}
			categorySitemap.setTimeXSBD(time_updated.replace(" ", "T") + "+07:00");
			break;
		case "XSTV":
			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "16:45:00";
			}
			categorySitemap.setTimeXSTV(time_updated.replace(" ", "T") + "+07:00");
			break;
		case "XSGL":
			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "17:45:00";
			}
			categorySitemap.setTimeXSGL(time_updated.replace(" ", "T") + "+07:00");
			
			break;
		case "XSNT":
			if (CommonUtil.isEmptyString(time_updated)) {
				String open_dates[] = open_date.split(" ");
				time_updated = open_dates[0] + "T" + "17:45:00";
			}
			categorySitemap.setTimeXSNT(time_updated.replace(" ", "T") + "+07:00");
			break;

		}
		
	}

}
