package inet.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.soicaupro.thongkebacnho.CommonUtil;
import com.soicaupro.thongkebacnho.Constant;

import inet.bean.LotoGan;
import inet.bean.Lottery;
import inet.bean.LotteryCompany;
import inet.bean.LotteryResult;
import inet.bean.LotterySpecial;
import inet.bean.Mobat;
import inet.bean.PhantichLoto;
import inet.constant.Constants;
import inet.pool.DBPoolX;
import inet.pool.DBPoolXName;
import inet.util.DatePro;
import inet.util.DateProc;
import inet.util.DateUtil;
import inet.util.Logger;
import inet.util.StringPro;
import inet.util.Today;

public class LotteryResultDAO {

	Logger logger = null;
	private DBPoolX poolX = null;
	private List<LotteryResult> listLottery = null;

	// lay danh sach ket qua xo so theo ham phan tich loto
	public List<LotteryResult> getListLottery() {
		return listLottery;
	}

	public LotteryResultDAO() {
		logger = new Logger(this.getClass().getName());
		try {
			poolX = DBPoolX.getInstance(DBPoolXName.SERVICE_LOTTERY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<LotteryResult> findLotteryResultAsc(String code, String sDate, String eDate) {

		List<LotteryResult> list = null;
		String sql = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			sql = "select code,symbol,price,date_format(open_date,'%d/%m/%Y'),special,first,second,third,fourth,fifth,"
					+ "sixth,seventh,eighth,date_format(create_date,'%d/%m/%Y') from lottery_result "
					+ "where code=? and special !='' and date(open_date) between date(?) and date(?) order by open_date asc";
			conn = poolX.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, code);
			ps.setString(2, sDate);
			ps.setString(3, eDate);

			rs = ps.executeQuery();
			list = new ArrayList<>();
			LotteryResult lotteryResult = null;
			int index = 1;
			while (rs.next()) {
				index = 1;
				lotteryResult = new LotteryResult();
				lotteryResult.setCode(rs.getString(index++));
				lotteryResult.setSymbol(rs.getString(index++));
				lotteryResult.setOpenDate(rs.getString(index++));
				lotteryResult.setSpecial(rs.getString(index++));
				lotteryResult.setFirst(rs.getString(index++));
				lotteryResult.setSecond(rs.getString(index++));
				lotteryResult.setThird(rs.getString(index++));
				lotteryResult.setFourth(rs.getString(index++));
				lotteryResult.setFifth(rs.getString(index++));
				lotteryResult.setSixth(rs.getString(index++));
				lotteryResult.setSeventh(rs.getString(index++));
				lotteryResult.setEighth(rs.getString(index++));
				lotteryResult.setCreate_date(rs.getString(index++));

				list.add(lotteryResult);
			}

		} catch (SQLException e) {
			System.out.println(e.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}

		return list;
	}

	public List<LotteryResult> findLotteryResult(String code, String sDate, String eDate) {

		List<LotteryResult> list = null;
		String sql = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			sql = "select code,symbol,price,date_format(open_date,'%d/%m/%Y'),special,first,second,third,fourth,fifth,"
					+ "sixth,seventh,eighth,date_format(create_date,'%d/%m/%Y') from lottery_result "
					+ "where code=? and date(open_date) between date(?) and date(?) and special != ''  order by open_date desc";
			conn = poolX.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, code);
			ps.setString(2, sDate);
			ps.setString(3, eDate);

			rs = ps.executeQuery();
			list = new ArrayList<>();
			LotteryResult lotteryResult = null;
			int index = 1;
			while (rs.next()) {
				index = 1;
				lotteryResult = new LotteryResult();
				lotteryResult.setCode(rs.getString(index++));
				lotteryResult.setSymbol(rs.getString(index++));
				lotteryResult.setPrice(new BigDecimal(rs.getString(index++)));
				lotteryResult.setOpenDate(rs.getString(index++));
				lotteryResult.setSpecial(rs.getString(index++));
				lotteryResult.setFirst(rs.getString(index++));
				lotteryResult.setSecond(rs.getString(index++));
				lotteryResult.setThird(rs.getString(index++));
				lotteryResult.setFourth(rs.getString(index++));
				lotteryResult.setFifth(rs.getString(index++));
				lotteryResult.setSixth(rs.getString(index++));
				lotteryResult.setSeventh(rs.getString(index++));
				lotteryResult.setEighth(rs.getString(index++));
				lotteryResult.setCreate_date(rs.getString(index++));

				list.add(lotteryResult);
			}

		} catch (SQLException e) {
			System.out.println(e.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}

		return list;
	}

	public void deleteXSMB_Error() {
		StringBuilder sql = new StringBuilder();
		String openDate = DateUtil.date2String(new java.util.Date(), Constant.FORMAT_DATETIME);
		sql.append(" DELETE ");
		sql.append(" From lottery_result");
		sql.append(" where open_date = '").append(openDate).append("' and (special =''  or special is null)");

		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		try {
			conn = poolX.getConnection();
			preStmt = conn.prepareStatement(sql.toString());
			preStmt.executeUpdate();

		} catch (SQLException e) {
			logger.error("getRow: Error executing " + sql.toString() + ">>>" + e.toString());
		} catch (Exception e) {
		} finally {
			poolX.releaseConnection(conn, preStmt, rs);

		}

	}

	public void deleteDulicateRecords(String openDate) {
		List<Lottery> rs = getDulicateRecords(openDate);

		if (rs != null && rs.size() > 1) {
			boolean check = true;

			for (Lottery lottery : rs) {

				if (check) {
					if (checkDataValid(lottery)) {
						check = false;
					} else {
						removeRecords(lottery.getId());
					}
				} else {
					// remove
					removeRecords(lottery.getId());
				}
			}
		}
	}

	private boolean checkDataValid(Lottery lottery) {

		if (CommonUtil.isEmptyString(lottery.getSpecial())) {
			return false;
		}

		if (CommonUtil.isEmptyString(lottery.getFirst())) {
			return false;
		}

		if (CommonUtil.isEmptyString(lottery.getSecond())) {
			return false;
		}

		if (CommonUtil.isEmptyString(lottery.getThird())) {
			return false;
		}

		if (CommonUtil.isEmptyString(lottery.getFourth())) {
			return false;
		}

		if (CommonUtil.isEmptyString(lottery.getFifth())) {
			return false;
		}

		if (CommonUtil.isEmptyString(lottery.getSixth())) {
			return false;
		}

		if (CommonUtil.isEmptyString(lottery.getSeventh())) {
			return false;
		}

		return true;
	}

	public List<Lottery> getDulicateRecords(String openDate) {
		StringBuilder sql = new StringBuilder();

		sql.append(" Select id, special, first, second, third, fourth, fifth, sixth, seventh ");
		sql.append(" From lottery_result");
		sql.append(" where code = 'XSTD' and symbol = 'TD' and open_date = '").append(openDate)
				.append("' and special != ''");

		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		List<Lottery> results = new ArrayList<Lottery>();

		try {
			conn = poolX.getConnection();
			preStmt = conn.prepareStatement(sql.toString());
			rs = preStmt.executeQuery();

			Lottery lotResult = null;
			while (rs.next()) {
				lotResult = new Lottery();
				lotResult.setId(rs.getInt(1));
				lotResult.setSpecial(rs.getString(2));
				lotResult.setFirst(rs.getString(3));
				lotResult.setSecond(rs.getString(4));
				lotResult.setThird(rs.getString(5));
				lotResult.setFourth(rs.getString(6));
				lotResult.setFifth(rs.getString(7));
				lotResult.setSixth(rs.getString(8));
				lotResult.setSeventh(rs.getString(9));
				// lotResult.setUserName(rs.getString(16));

				if (lotResult != null && !CommonUtil.isEmptyString(lotResult.getSpecial())) {
					results.add(lotResult);
				}
			}

		} catch (SQLException e) {
			logger.error("getRow: Error executing " + sql.toString() + ">>>" + e.toString());
		} catch (Exception e) {
		} finally {
			poolX.releaseConnection(conn, preStmt, rs);
			return results;
		}
	}

	public List<Lottery> getDulicateRecordsMaTinh(String openDate, String symbol) {
		StringBuilder sql = new StringBuilder();

		sql.append(" Select id, special, first, second, third, fourth, fifth, sixth, seventh ");
		sql.append(" From lottery_result");
		sql.append(" where symbol = '").append(symbol).append("' and open_date = '").append(openDate)
				.append("' and special != ''");

		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		List<Lottery> results = new ArrayList<Lottery>();

		try {
			conn = poolX.getConnection();
			preStmt = conn.prepareStatement(sql.toString());
			rs = preStmt.executeQuery();

			Lottery lotResult = null;
			while (rs.next()) {
				lotResult = new Lottery();
				lotResult.setId(rs.getInt(1));
				lotResult.setSpecial(rs.getString(2));
				lotResult.setFirst(rs.getString(3));
				lotResult.setSecond(rs.getString(4));
				lotResult.setThird(rs.getString(5));
				lotResult.setFourth(rs.getString(6));
				lotResult.setFifth(rs.getString(7));
				lotResult.setSixth(rs.getString(8));
				lotResult.setSeventh(rs.getString(9));
				// lotResult.setUserName(rs.getString(16));

				if (lotResult != null && !CommonUtil.isEmptyString(lotResult.getSpecial())) {
					results.add(lotResult);
				}
			}

		} catch (SQLException e) {
			logger.error("getRow: Error executing " + sql.toString() + ">>>" + e.toString());
		} catch (Exception e) {
		} finally {
			poolX.releaseConnection(conn, preStmt, rs);
			return results;
		}
	}

	public void removeRecords(int id) {
		StringBuilder sql = new StringBuilder();

		sql.append(" DELETE  ");
		sql.append(" From lottery_result");
		sql.append(" where code = 'XSTD' and symbol = 'TD' and id = ").append(id);

		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		List<Lottery> results = new ArrayList<Lottery>();

		try {
			conn = poolX.getConnection();
			preStmt = conn.prepareStatement(sql.toString());
			preStmt.executeUpdate();

		} catch (SQLException e) {
			logger.error("getRow: Error executing " + sql.toString() + ">>>" + e.toString());
		} catch (Exception e) {
		} finally {
			poolX.releaseConnection(conn, preStmt, rs);
		}
	}

	public static void main(String[] agrs) {
		System.out.println(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp("03/08/20151979"), 0));
	}

	/**
	 * Get lottery result by code
	 *
	 * @param code
	 * @return LotteryResult object
	 */
	public LotteryResult getRowByCode(String code) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		String strSQL = null;
		LotteryResult lotResult = null;
		try {
			conn = poolX.getConnection();
			strSQL = " select id, code, symbol, price, open_date, special, first, second, third, fourth, fifth, sixth, seventh, eighth, status "
					+ " from lottery_result where upper(code) = ? and open_date = ("
					+ " select max(open_date) from lottery_result where upper(code) = upper('" + code + "'))";
			preStmt = conn.prepareStatement(strSQL);
			preStmt.setString(1, code.toUpperCase());
			rs = preStmt.executeQuery();
			if (rs.next()) {
				lotResult = new LotteryResult();
				lotResult.setId(rs.getBigDecimal(1));
				lotResult.setCode(rs.getString(2));
				lotResult.setSymbol(rs.getString(3));
				lotResult.setPrice(rs.getBigDecimal(4));
				lotResult.setOpenDate(DateProc.Timestamp2DDMMYYYY(rs.getTimestamp(5)));
				lotResult.setSpecial(rs.getString(6));
				lotResult.setFirst(rs.getString(7));
				lotResult.setSecond(rs.getString(8));
				lotResult.setThird(rs.getString(9));
				lotResult.setFourth(rs.getString(10));
				lotResult.setFifth(rs.getString(11));
				lotResult.setSixth(rs.getString(12));
				lotResult.setSeventh(rs.getString(13));
				lotResult.setEighth(rs.getString(14));
				lotResult.setStatus(rs.getInt(15));

			}
		} catch (SQLException e) {
			logger.error("getRow: Error executing " + strSQL + ">>>" + e.toString());
		} catch (Exception e) {
		} finally {
			poolX.releaseConnection(conn, preStmt, rs);

		}
		return lotResult;
	}

	/**
	 * get result of province by date
	 *
	 * @param code
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<Lottery> findResultByCodeInTime(String code, String startDate, String endDate) {
		if (startDate == null || startDate.length() != 10 || endDate == null || endDate.length() != 10) {
			return null;
		}
		startDate = startDate + " 00:00:00";
		endDate = endDate + " 23:59:59";
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		String strSQL = null;
		List<Lottery> result = null;
		try {
			conn = poolX.getConnection();
			strSQL = "  select a.id, a.code, symbol, price, open_date, special, first, second, third, "
					+ "        fourth, fifth, sixth, seventh, eighth, status,user_name,a.last_updated,UPPER(b.name) "
					+ " from lottery_result a join lottery_company b on a.code=b.code "
					+ " where special<>'' and special != '' and  a.code=? and open_date >= str_to_date(?, '%d/%m/%Y %H:%i:%s') "
					+ "       and open_date <= str_to_date(?, '%d/%m/%Y %H:%i:%s') " + " order by open_date desc, id";
			preStmt = conn.prepareStatement(strSQL);
			preStmt.setString(1, code);
			preStmt.setString(2, startDate);
			preStmt.setString(3, endDate);
			rs = preStmt.executeQuery();
			result = new Vector<Lottery>();
			Lottery lotResult = null;
			while (rs.next()) {
				lotResult = new Lottery();
				lotResult.setId(rs.getInt(1));
				lotResult.setCode(rs.getString(2));
				lotResult.setSymbol(rs.getString(3));
				lotResult.setPrice(rs.getInt(4));
				lotResult.setOpenDate(DateProc.Timestamp2DDMMYYYY(rs.getTimestamp(5)));
				lotResult.setSpecial(rs.getString(6));
				lotResult.setFirst(rs.getString(7));
				lotResult.setSecond(rs.getString(8));
				lotResult.setThird(rs.getString(9));
				lotResult.setFourth(rs.getString(10));
				lotResult.setFifth(rs.getString(11));
				lotResult.setSixth(rs.getString(12));
				lotResult.setSeventh(rs.getString(13));
				lotResult.setEighth(rs.getString(14));
				lotResult.setStatus(rs.getInt(15));
				// lotResult.setUserName(rs.getString(16));
				lotResult.setLastUpdated(DateProc.Timestamp2DDMMYYYY(rs.getTimestamp(17)));
				lotResult.setProvince(rs.getString(18));

				result.add(lotResult);

			}
		} catch (SQLException e) {
			logger.error("findResultByCodeAndTime: Error executing " + strSQL + ">>>" + e.toString());
			System.out.println("findResultByCodeAndTime: Error executing " + strSQL + ">>>" + e.toString());
		} catch (Exception e) {
		} finally {
			poolX.releaseConnection(conn, preStmt, rs);
		}
		return result;
	}
	
	/**
	 * get result of province by date
	 *
	 * @param code
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<Lottery> findResultByCodeInTimeLive(String code, String startDate, String endDate) {
		if (startDate == null || startDate.length() != 10 || endDate == null || endDate.length() != 10) {
			return null;
		}
		startDate = startDate + " 00:00:00";
		endDate = endDate + " 23:59:59";
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		String strSQL = null;
		List<Lottery> result = null;
		try {
			conn = poolX.getConnection();
			strSQL = "  select a.id, a.code, symbol, price, open_date, special, first, second, third, "
					+ "        fourth, fifth, sixth, seventh, eighth, status,user_name,a.last_updated,UPPER(b.name) "
					+ " from lottery_result a join lottery_company b on a.code=b.code "
					+ " where a.code=? and open_date >= str_to_date(?, '%d/%m/%Y %H:%i:%s') "
					+ "       and open_date <= str_to_date(?, '%d/%m/%Y %H:%i:%s') " + " order by open_date desc, id";
			preStmt = conn.prepareStatement(strSQL);
			preStmt.setString(1, code);
			preStmt.setString(2, startDate);
			preStmt.setString(3, endDate);
			rs = preStmt.executeQuery();
			result = new Vector<Lottery>();
			Lottery lotResult = null;
			while (rs.next()) {
				lotResult = new Lottery();
				lotResult.setId(rs.getInt(1));
				lotResult.setCode(rs.getString(2));
				lotResult.setSymbol(rs.getString(3));
				lotResult.setPrice(rs.getInt(4));
				lotResult.setOpenDate(DateProc.Timestamp2DDMMYYYY(rs.getTimestamp(5)));
				lotResult.setSpecial(rs.getString(6));
				lotResult.setFirst(rs.getString(7));
				lotResult.setSecond(rs.getString(8));
				lotResult.setThird(rs.getString(9));
				lotResult.setFourth(rs.getString(10));
				lotResult.setFifth(rs.getString(11));
				lotResult.setSixth(rs.getString(12));
				lotResult.setSeventh(rs.getString(13));
				lotResult.setEighth(rs.getString(14));
				lotResult.setStatus(rs.getInt(15));
				// lotResult.setUserName(rs.getString(16));
				lotResult.setLastUpdated(DateProc.Timestamp2DDMMYYYY(rs.getTimestamp(17)));
				lotResult.setProvince(rs.getString(18));

				result.add(lotResult);

			}
		} catch (SQLException e) {
			logger.error("findResultByCodeAndTime: Error executing " + strSQL + ">>>" + e.toString());
			System.out.println("findResultByCodeAndTime: Error executing " + strSQL + ">>>" + e.toString());
		} catch (Exception e) {
		} finally {
			poolX.releaseConnection(conn, preStmt, rs);
		}
		return result;
	}

	public List<Lottery> findResultByRegionInDay(String region, String date) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		List<Lottery> vLottery = new Vector<Lottery>();
		date = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(date), 0);
		String startDate = date + " 00:00:00";
		String endDate = date + " 23:59:59";
		try {
			conn = poolX.getConnection();
			sql = " select a.id, a.code, symbol, price, open_date, special, first, second, third, "
					+ " fourth, fifth, sixth, seventh, eighth, status, user_name, a.last_updated,b.name  "
					+ " from lottery_result  a join lottery_company b on a.code  = b.code "
					+ " where b.region = ? and open_date >= str_to_date(?, '%d/%m/%Y %H:%i:%s') "
					+ " and open_date < str_to_date(?, '%d/%m/%Y %H:%i:%s')";
			ps = conn.prepareStatement(sql);
			ps.setString(1, region);
			ps.setString(2, startDate);
			ps.setString(3, endDate);
			rs = ps.executeQuery();
			Lottery lotResult = null;
			while (rs.next()) {
				lotResult = new Lottery();
				lotResult.setId(rs.getInt(1));
				lotResult.setCode(rs.getString(2));
				lotResult.setSymbol(rs.getString(3));
				lotResult.setPrice(rs.getInt(4));
				lotResult.setOpenDate(DateProc.Timestamp2DDMMYYYY(rs.getTimestamp(5)));
				lotResult.setSpecial(rs.getString(6));
				lotResult.setFirst(rs.getString(7));
				lotResult.setSecond(rs.getString(8));
				lotResult.setThird(rs.getString(9));
				lotResult.setFourth(rs.getString(10));
				lotResult.setFifth(rs.getString(11));
				lotResult.setSixth(rs.getString(12));
				lotResult.setSeventh(rs.getString(13));
				lotResult.setEighth(rs.getString(14));
				lotResult.setStatus(rs.getInt(15));
				// lotResult.setUserName(rs.getString(16));
				lotResult.setLastUpdated(DateProc.Timestamp2DDMMYYYY(rs.getTimestamp(17)));
				lotResult.setProvince(rs.getString(18));

				vLottery.add(lotResult);

			}
		} catch (SQLException e) {
			logger.error("findResultByRegionInDay: SQLException Error executing " + sql + ">>>" + e.toString());
			System.out
					.println("findResultByRegionInDay: Error executing " + date + ">>>>" + sql + ">>>" + e.toString());
		} catch (Exception ex) {
			logger.error("findResultByRegionInDay: Exception Error executing " + sql + ">>>" + ex.toString());
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}
		return vLottery;
	}

	public List<Lottery> findResultByRegion(String region, String sDate, String eDate) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		sDate = sDate + " 00:00:00";
		eDate = eDate + " 23:59:59";
		List<Lottery> vLottery = new Vector<Lottery>();
		try {
			conn = poolX.getConnection();
			sql = " select a.id, a.code, symbol, price, open_date, special, first, second, third, "
					+ " fourth, fifth, sixth, seventh, eighth, status, user_name, a.last_updated,b.name  "
					+ " from lottery_result  a join lottery_company b on a.code  = b.code "
					+ " where  b.region = ? and open_date between str_to_date(?, '%d-%m-%Y %H:%i:%s') "
					+ " and str_to_date(?, '%d-%m-%Y %H:%i:%s') order by open_date desc";
			ps = conn.prepareStatement(sql);
			ps.setString(1, region);
			ps.setString(2, sDate);
			ps.setString(3, eDate);
			rs = ps.executeQuery();
			Lottery lotResult = null;
			while (rs.next()) {
				lotResult = new Lottery();
				lotResult.setId(rs.getInt(1));
				lotResult.setCode(rs.getString(2));
				lotResult.setSymbol(rs.getString(3));
				lotResult.setPrice(rs.getInt(4));
				lotResult.setOpenDate(DateProc.Timestamp2DDMMYYYY(rs.getTimestamp(5)));
				lotResult.setSpecial(rs.getString(6));
				lotResult.setFirst(rs.getString(7));
				lotResult.setSecond(rs.getString(8));
				lotResult.setThird(rs.getString(9));
				lotResult.setFourth(rs.getString(10));
				lotResult.setFifth(rs.getString(11));
				lotResult.setSixth(rs.getString(12));
				lotResult.setSeventh(rs.getString(13));
				lotResult.setEighth(rs.getString(14));
				lotResult.setStatus(rs.getInt(15));
				// lotResult.setUserName(rs.getString(16));
				lotResult.setLastUpdated(DateProc.Timestamp2DDMMYYYY(rs.getTimestamp(17)));
				lotResult.setProvince(rs.getString(18));

				vLottery.add(lotResult);

			}
		} catch (SQLException e) {
			logger.error("findResultByRegionInDay: SQLException Error executing " + sql + ">>>" + e.toString());
			System.out.println("findResultByRegionInDay: Error executing " + sDate + ">>>" + eDate + ">>>" + sql + ">>>"
					+ e.toString());
		} catch (Exception ex) {
			logger.error("findResultByRegionInDay: Exception Error executing " + sql + ">>>" + ex.toString());
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}
		return vLottery;
	}

	public Lottery findResultByCodeInDay(String code, String date) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		Lottery lotResult = null;
		date = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(date), 0);
		String startDate = date + " 00:00:00";
		String endDate = date + " 23:59:59";

		try {
			conn = poolX.getConnection();
			sql = " select a.id, a.code, symbol, price, open_date, special, first, second, third, "
					+ " fourth, fifth, sixth, seventh, eighth, status, user_name, a.last_updated,b.name  "
					+ " from lottery_result  a join lottery_company b on a.code  = b.code "
					+ " where a.code = ? and open_date >= str_to_date(?, '%d/%m/%Y %H:%i:%s') "
					+ " and open_date < str_to_date(?, '%d/%m/%Y %H:%i:%s')";
			ps = conn.prepareStatement(sql);
			ps.setString(1, code);
			ps.setString(2, startDate);
			ps.setString(3, endDate);
			rs = ps.executeQuery();

			if (rs.next()) {
				lotResult = new Lottery();
				lotResult.setId(rs.getInt(1));
				lotResult.setCode(rs.getString(2));
				lotResult.setSymbol(rs.getString(3));
				lotResult.setPrice(rs.getInt(4));
				lotResult.setOpenDate(DateProc.Timestamp2DDMMYYYY(rs.getTimestamp(5)));
				lotResult.setSpecial(rs.getString(6));
				lotResult.setFirst(rs.getString(7));
				lotResult.setSecond(rs.getString(8));
				lotResult.setThird(rs.getString(9));
				lotResult.setFourth(rs.getString(10));
				lotResult.setFifth(rs.getString(11));
				lotResult.setSixth(rs.getString(12));
				lotResult.setSeventh(rs.getString(13));
				lotResult.setEighth(rs.getString(14));
				lotResult.setStatus(rs.getInt(15));
				// lotResult.setUserName(rs.getString(16));
				lotResult.setLastUpdated(DateProc.Timestamp2DDMMYYYY(rs.getTimestamp(17)));
				lotResult.setProvince(rs.getString(18));
			}
		} catch (SQLException e) {
			logger.error("findResultByRegionInDay: SQLException Error executing " + sql + ">>>" + e.toString());
			System.out.println("findResultByRegionInDay: Error executing " + date + ">>>" + sql + ">>>" + e.toString());
		} catch (Exception ex) {
			logger.error("findResultByRegionInDay: Exception Error executing " + sql + ">>>" + ex.toString());
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}
		return lotResult;
	}

	public List<Lottery> findResultByRegionInDay(String region, String sdate, String edate) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		List<Lottery> vLottery = new Vector<Lottery>();
		sdate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(sdate), 0);
		edate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(edate), 0);
		sdate = sdate + " 00:00:00";
		edate = edate + " 23:59:59";
		try {
			conn = poolX.getConnection();
			sql = " select a.id, a.code, symbol, price, open_date, special, first, second, third, "
					+ " fourth, fifth, sixth, seventh, eighth, status, user_name, a.last_updated,b.name  "
					+ " from lottery_result  a join lottery_company b on a.code  = b.code "
					+ " where b.region = ? and  open_date >= str_to_date(?, '%d-%m-%Y %H:%i:%s') "
					+ " and open_date <= str_to_date(?, '%d-%m-%Y %H:%i:%s') " + " order by code  desc,open_date desc";
			ps = conn.prepareStatement(sql);
			ps.setString(1, region);
			ps.setString(2, sdate);
			ps.setString(3, edate);
			rs = ps.executeQuery();
			Lottery lotResult = null;
			while (rs.next()) {
				lotResult = new Lottery();
				lotResult.setId(rs.getInt(1));
				lotResult.setCode(rs.getString(2));
				lotResult.setSymbol(rs.getString(3));
				lotResult.setPrice(rs.getInt(4));
				lotResult.setOpenDate(DateProc.Timestamp2DDMMYYYY(rs.getTimestamp(5)));
				lotResult.setSpecial(rs.getString(6));
				lotResult.setFirst(rs.getString(7));
				lotResult.setSecond(rs.getString(8));
				lotResult.setThird(rs.getString(9));
				lotResult.setFourth(rs.getString(10));
				lotResult.setFifth(rs.getString(11));
				lotResult.setSixth(rs.getString(12));
				lotResult.setSeventh(rs.getString(13));
				lotResult.setEighth(rs.getString(14));
				lotResult.setStatus(rs.getInt(15));
				// lotResult.setUserName(rs.getString(16));
				lotResult.setLastUpdated(DateProc.Timestamp2DDMMYYYY(rs.getTimestamp(17)));
				lotResult.setProvince(rs.getString(18));

				vLottery.add(lotResult);
			}
		} catch (SQLException e) {
			logger.error("findResultByRegionInDay: SQLException Error executing " + sql + ">>>" + e.toString());
			System.out.println("findResultByRegionInDay: Error executing " + sdate + ">>" + edate + ">>" + sql + ">>>"
					+ e.toString());
		} catch (Exception ex) {
			logger.error("findResultByRegionInDay: Exception Error executing " + sql + ">>>" + ex.toString());
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}
		return vLottery;
	}

	public Vector<LotteryResult> findResultByCode(String code, int numrow) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		Vector<LotteryResult> vLottery = new Vector<LotteryResult>();
		try {
			conn = poolX.getConnection();
			sql = " select id, code, symbol, price, open_date, special, first, second, third, "
					+ " fourth, fifth, sixth, seventh, eighth, status, user_name, last_updated from "
					+ " (select id, code, symbol, price, open_date, special, first, second, third, "
					+ " fourth, fifth, sixth, seventh, eighth, status, user_name, last_updated, "
					+ " row_number() over(order by open_date desc) r" + " from lottery_result "
					+ " where code = ? ) where  r < ? order by open_date desc";
			ps = conn.prepareStatement(sql);
			ps.setString(1, code);
			ps.setInt(2, numrow);
			rs = ps.executeQuery();
			LotteryResult lotResult = null;
			while (rs.next()) {
				lotResult = new LotteryResult();
				lotResult.setId(rs.getBigDecimal(1));
				lotResult.setCode(rs.getString(2));
				lotResult.setSymbol(rs.getString(3));
				lotResult.setPrice(rs.getBigDecimal(4));
				lotResult.setOpenDate(DateProc.Timestamp2DDMMYYYY(rs.getTimestamp(5)));
				lotResult.setSpecial(rs.getString(6));
				lotResult.setFirst(rs.getString(7));
				lotResult.setSecond(rs.getString(8));
				lotResult.setThird(rs.getString(9));
				lotResult.setFourth(rs.getString(10));
				lotResult.setFifth(rs.getString(11));
				lotResult.setSixth(rs.getString(12));
				lotResult.setSeventh(rs.getString(13));
				lotResult.setEighth(rs.getString(14));
				lotResult.setStatus(rs.getInt(15));
				lotResult.setUserName(rs.getString(16));
				lotResult.setLastUpdated(DateProc.Timestamp2DDMMYYYY(rs.getTimestamp(17)));

				vLottery.addElement(lotResult);

			}
		} catch (SQLException e) {
			logger.error("findResultByCode: SQLException Error executing " + sql + ">>>" + e.toString());
			System.out.println("findResultByCode: Error executing " + sql + ">>>" + e.toString());
		} catch (Exception ex) {
			logger.error("findResultByCode: Exception Error executing " + sql + ">>>" + ex.toString());
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}
		return vLottery;
	}

	//for soi cau bach thu MT -MN lay 2 ngay tiep theo
	public Vector<LotteryResult> findResultByCode(String code, int numrow, BigDecimal id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		Vector<LotteryResult> vLottery = new Vector<LotteryResult>();
		try {
			conn = poolX.getConnection();
			sql = " select id, code, symbol, price, open_date, special, first, second, third, "
					+ " fourth, fifth, sixth, seventh, eighth, status, user_name, last_updated "
					+ " from lottery_result "
					+ " where code = ? and id<= ? and special<>'' and special != ''  order by open_date desc limit ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, code);
			ps.setBigDecimal(2, id);
			ps.setInt(3, numrow);
			rs = ps.executeQuery();
			LotteryResult lotResult = null;
			while (rs.next()) {
				lotResult = new LotteryResult();
				lotResult.setId(rs.getBigDecimal(1));
				lotResult.setCode(rs.getString(2));
				lotResult.setSymbol(rs.getString(3));
				lotResult.setPrice(rs.getBigDecimal(4));
				lotResult.setOpenDate(DateProc.Timestamp2DDMMYYYY(rs.getTimestamp(5)));
				lotResult.setSpecial(rs.getString(6));
				lotResult.setFirst(rs.getString(7));
				lotResult.setSecond(rs.getString(8));
				lotResult.setThird(rs.getString(9));
				lotResult.setFourth(rs.getString(10));
				lotResult.setFifth(rs.getString(11));
				lotResult.setSixth(rs.getString(12));
				lotResult.setSeventh(rs.getString(13));
				lotResult.setEighth(rs.getString(14));
				lotResult.setStatus(rs.getInt(15));
				lotResult.setUserName(rs.getString(16));
				lotResult.setLastUpdated(DateProc.Timestamp2DDMMYYYY(rs.getTimestamp(17)));

				vLottery.addElement(lotResult);

			}
		} catch (SQLException e) {
			logger.error("findResultByCode: SQLException Error executing " + sql + ">>>" + e.toString());
			System.out.println("findResultByCode: Error executing " + sql + ">>>" + e.toString());
		} catch (Exception ex) {
			logger.error("findResultByCode: Exception Error executing " + sql + ">>>" + ex.toString());
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}
		return vLottery;
	}

	// lay ket qua xo so theo thu cua tung tinh thanh
	// code-> ma tinh thanh can lay
	// dayOfWeek-> thu trong tuan
	// numDay->so ngay can lay
	public List<Lottery> findResultDayOfWeek(String code, String dayOfWeek, int numDay) {
		List<Lottery> result = null;
		if (dayOfWeek == null || "".equals(dayOfWeek) || numDay < 0) {
			return result;
		}

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		Lottery lotteryResult = null;
		try {
			List<String> listDate = DatePro.getListDateDDMMYYYY(dayOfWeek.toUpperCase(), numDay);
			conn = poolX.getConnection();
			if ("MN".equalsIgnoreCase(code) || "MT".equalsIgnoreCase(code) || "MB".equalsIgnoreCase(code)) {
				sql = "select a.id,a.code, symbol, price, open_date, special, first, second, third, "
						+ "    fourth, fifth, sixth, seventh, eighth, status, user_name,a.last_updated,b.name  "
						+ "    from lottery_result a join lottery_company b on a.code=b.code  where special != '' and  b.region=? and "
						+ "    b.open_days like upper('%" + dayOfWeek + "%')"
						+ "    and to_char(open_date, 'dd/mm/yyyy') in(";

			} else {
				sql = "select a.id,a.code, symbol, price, open_date, special, first, second, third, "
						+ "    fourth, fifth, sixth, seventh, eighth, status, user_name,a.last_updated,b.name  "
						+ "    from lottery_result a join  lottery_company b on a.code=b.code"
						+ "   where a.code=? and special != '' " + " and to_char(open_date, 'dd/mm/yyyy') in(";

			}

			for (int i = 0; i < listDate.size(); i++) {
				sql = sql + "'" + listDate.get(i) + "',";
			}

			sql = sql.substring(0, sql.length() - 1);
			sql = sql + ") order by open_date desc";

			ps = conn.prepareStatement(sql);
			ps.setString(1, code);
			rs = ps.executeQuery();
			result = new Vector<Lottery>();
			while (rs.next()) {
				lotteryResult = new Lottery();
				lotteryResult.setId(rs.getInt(1));
				lotteryResult.setCode(rs.getString(2));
				lotteryResult.setSymbol(rs.getString(3));
				lotteryResult.setPrice(rs.getInt(4));
				lotteryResult.setOpenDate(DateProc.Timestamp2DDMMYYYY(rs.getTimestamp(5)));
				lotteryResult.setSpecial(rs.getString(6));
				lotteryResult.setFirst(rs.getString(7));
				lotteryResult.setSecond(rs.getString(8));
				lotteryResult.setThird(rs.getString(9));
				lotteryResult.setFourth(rs.getString(10));
				lotteryResult.setFifth(rs.getString(11));
				lotteryResult.setSixth(rs.getString(12));
				lotteryResult.setSeventh(rs.getString(13));
				lotteryResult.setEighth(rs.getString(14));
				lotteryResult.setStatus(rs.getInt(15));
				// lotteryResult.setUserName(rs.getString(16));
				lotteryResult.setLastUpdated(DateProc.Timestamp2DDMMYYYY(rs.getTimestamp(17)));
				lotteryResult.setProvince(rs.getString(18));

				if (lotteryResult != null && !CommonUtil.isEmptyString(lotteryResult.getSpecial())) {
					result.add(lotteryResult);
				}
			}

		} catch (SQLException e) {
			logger.error(" loi sql " + e.toString());
			// System.out.println("loi sql "+e.toString());
		} catch (Exception e) {
			logger.error(" loi ngoai le " + e.toString());
			// System.out.println("loi ngoai le "+e.toString());
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}

		return result;
	}

	public Vector<LotteryResult> findResultOfCode(String code, String ddmmyyyy) {
		Vector<LotteryResult> result = null;
		LotteryCompanyDAO lotteryCompanyDAO = new LotteryCompanyDAO();
		LotteryCompany lotteryCompany = lotteryCompanyDAO.findCompanyCode(code);

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		LotteryResult lotteryResult = null;
		String strtoday = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
		Today today = new Today();
		int hour = today.getHour();
		int minute = today.getMinute();
		try {
			if (strtoday.equalsIgnoreCase(ddmmyyyy)) {
				if ("MN".equalsIgnoreCase(lotteryCompany.getRegion())) {
					if (hour < 16 || (hour == 16 && minute < 36)) {
						ddmmyyyy = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -1);
					}
				} else if ("MT".equalsIgnoreCase(lotteryCompany.getRegion())) {
					if (hour < 17 || (hour == 17 && minute < 36)) {
						ddmmyyyy = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -1);
					}
				} else if ("MB".equalsIgnoreCase(lotteryCompany.getRegion())) {
					if (hour < 18 || (hour == 18 && minute < 36)) {
						ddmmyyyy = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -1);
					}
				}
			}

			String listDate = DatePro.getDateDDMMYYYY(ddmmyyyy, lotteryCompany.getOpendate().toUpperCase());
			conn = poolX.getConnection();

			sql = " select a.id,a.code, symbol, price, open_date, special, first, second, third, "
					+ " fourth, fifth, sixth, seventh, eighth, status, user_name,a.last_updated,b.name  "
					+ " from lottery_result a join  lottery_company b on a.code=b.code where  a.code=? "
					+ " and open_date >= STR_TO_DATE(?,'%d/%m/%Y') and open_date < STR_TO_DATE(?,'%d/%m/%Y')+1";

			ps = conn.prepareStatement(sql);
			ps.setString(1, code);
			ps.setString(2, listDate);
			ps.setString(3, listDate);
			rs = ps.executeQuery();
			result = new Vector<LotteryResult>();
			while (rs.next()) {
				lotteryResult = new LotteryResult();
				lotteryResult.setId(rs.getBigDecimal(1));
				lotteryResult.setCode(rs.getString(2));
				lotteryResult.setSymbol(rs.getString(3));
				lotteryResult.setPrice(rs.getBigDecimal(4));
				lotteryResult.setOpenDate(DateProc.Timestamp2DDMMYYYY(rs.getTimestamp(5)));
				lotteryResult.setSpecial(rs.getString(6));
				lotteryResult.setFirst(rs.getString(7));
				lotteryResult.setSecond(rs.getString(8));
				lotteryResult.setThird(rs.getString(9));
				lotteryResult.setFourth(rs.getString(10));
				lotteryResult.setFifth(rs.getString(11));
				lotteryResult.setSixth(rs.getString(12));
				lotteryResult.setSeventh(rs.getString(13));
				lotteryResult.setEighth(rs.getString(14));
				lotteryResult.setStatus(rs.getInt(15));
				lotteryResult.setUserName(rs.getString(16));
				lotteryResult.setLastUpdated(DateProc.Timestamp2DDMMYYYY(rs.getTimestamp(17)));
				lotteryResult.setProvince(rs.getString(18));

				result.add(lotteryResult);

			}

		} catch (SQLException e) {
			logger.error(" loi sql " + e.toString());
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(" loi ngoai le " + e.toString());
			e.printStackTrace();
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}

		return result;
	}

	public Lottery findLotteryNewest(String code, String maxDate) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		Lottery lotResult = null;
		try {
			conn = poolX.getConnection();
			sql = " select id, code, symbol, price, open_date, special, first, second, third, "
					+ " fourth, fifth, sixth, seventh, eighth, status, user_name, last_updated "
					+ " from lottery_result "
					+ " where upper(code) = upper(?) and open_date >= STR_TO_DATE(?,'%d/%m/%Y') and open_date < STR_TO_DATE(?,'%d/%m/%Y') +1 "
					+ " order by open_date desc" + "";
			ps = conn.prepareStatement(sql);
			ps.setString(1, code);
			ps.setString(2, maxDate);
			ps.setString(3, maxDate);
			rs = ps.executeQuery();
			if (rs.next()) {
				lotResult = new Lottery();
				lotResult.setId(rs.getInt(1));
				lotResult.setCode(rs.getString(2));
				lotResult.setSymbol(rs.getString(3));
				lotResult.setPrice(rs.getInt(4));
				lotResult.setOpenDate(DateProc.Timestamp2DDMMYYYY(rs.getTimestamp(5)));
				lotResult.setSpecial(rs.getString(6));
				lotResult.setFirst(rs.getString(7));
				lotResult.setSecond(rs.getString(8));
				lotResult.setThird(rs.getString(9));
				lotResult.setFourth(rs.getString(10));
				lotResult.setFifth(rs.getString(11));
				lotResult.setSixth(rs.getString(12));
				lotResult.setSeventh(rs.getString(13));
				lotResult.setEighth(rs.getString(14));
				lotResult.setStatus(rs.getInt(15));
				// lotResult.setUserName(rs.getString(16));
				lotResult.setLastUpdated(DateProc.Timestamp2DDMMYYYY(rs.getTimestamp(17)));
			}
		} catch (SQLException e) {
			logger.error("findLotteryNewest: SQLException Error executing " + sql + ">>>" + e.toString());
			System.out.println("findLotteryNewest: Error executing " + sql + ">>>" + e.toString());
			poolX.releaseConnection(conn, ps, rs);
		} catch (Exception ex) {
			logger.error("findLotteryNewest: Exception Error executing " + sql + ">>>" + ex.toString());
			poolX.releaseConnection(conn, ps, rs);
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}
		return lotResult;
	}

	public List<Lottery> findLotteryNewestRegion(String region, String maxOpen) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		Lottery lotResult = null;
		List<Lottery> vLottery = null;
		String sdate = maxOpen + " 00:00:00";
		String edate = maxOpen + " 23:59:59";
		try {
			conn = poolX.getConnection();
			sql = "select a.id, a.code, symbol, price, open_date, special, first, second, third, "
					+ "fourth, fifth, sixth, seventh, eighth, status, user_name, a.last_updated ,b.name "
					+ "from lottery_result  a join lottery_company b on a.code  = b.code "
					+ "where b.region = ? and open_date >= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') "
					+ "and open_date < STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') " + "order by open_date desc";
			ps = conn.prepareStatement(sql);
			ps.setString(1, region);
			ps.setString(2, sdate);
			ps.setString(3, edate);
			rs = ps.executeQuery();
			vLottery = new Vector<Lottery>();
			while (rs.next()) {
				lotResult = new Lottery();
				lotResult.setId(rs.getInt(1));
				lotResult.setCode(rs.getString(2));
				lotResult.setSymbol(rs.getString(3));
				lotResult.setPrice(rs.getInt(4));
				lotResult.setOpenDate(DateProc.Timestamp2DDMMYYYY(rs.getTimestamp(5)));

				if (rs.getString(6) == null || "".equals(rs.getString(6)) || "null".equals(rs.getString(6))) {
					lotResult.setSpecial("-");
				} else {
					lotResult.setSpecial(rs.getString(6));
				}
				if (rs.getString(7) == null || "".equals(rs.getString(7)) || "null".equals(rs.getString(7))) {
					lotResult.setFirst("-");
				} else {
					lotResult.setFirst(rs.getString(7));
				}

				if (rs.getString(8) == null || "".equals(rs.getString(8)) || "null".equals(rs.getString(8))) {
					if ("MB".equalsIgnoreCase(region)) {
						lotResult.setSecond("--");
					} else {
						lotResult.setSecond("-");
					}
				} else {
					if ("MB".equalsIgnoreCase(region)) {
						if (rs.getString(8).length() > 5) {
							lotResult.setSecond(rs.getString(8));
						} else {
							lotResult.setSecond(rs.getString(8) + "-");
						}
					} else {
						lotResult.setSecond(rs.getString(8));
					}
				}

				if (rs.getString(9) == null || "".equals(rs.getString(9)) || "null".equals(rs.getString(9))) {
					if ("MB".equalsIgnoreCase(region)) {
						lotResult.setThird("------");
					} else {
						lotResult.setThird("--");
					}
				} else {
					if ("MB".equalsIgnoreCase(region)) {
						if (rs.getString(9).length() == 5) {
							lotResult.setThird(rs.getString(9) + "-----");
						} else if (rs.getString(9).length() > 5 && rs.getString(9).length() < 12) {
							lotResult.setThird(rs.getString(9) + "----");
						} else if (rs.getString(9).length() > 12 && rs.getString(9).length() < 18) {
							lotResult.setThird(rs.getString(9) + "---");
						} else if (rs.getString(9).length() > 18 && rs.getString(9).length() < 24) {
							lotResult.setThird(rs.getString(9) + "--");
						} else if (rs.getString(9).length() > 24 && rs.getString(9).length() < 30) {
							lotResult.setThird(rs.getString(9) + "-");
						} else {
							lotResult.setThird(rs.getString(9));
						}
					} else {
						if (rs.getString(9).length() > 5) {
							lotResult.setThird(rs.getString(9));
						} else {
							lotResult.setThird(rs.getString(9) + "-");
						}
					}
				}

				if (rs.getString(10) == null || "".equals(rs.getString(10)) || "null".equals(rs.getString(10))) {
					if ("MB".equalsIgnoreCase(region)) {
						lotResult.setFourth("----");
					} else {
						lotResult.setFourth("-------");
					}
				} else {
					if ("MB".equalsIgnoreCase(region)) {
						if (rs.getString(10).length() == 4) {
							lotResult.setFourth(rs.getString(10) + "---");
						} else if (rs.getString(10).length() > 4 && rs.getString(10).length() < 10) {
							lotResult.setFourth(rs.getString(10) + "--");
						} else if (rs.getString(10).length() > 10 && rs.getString(10).length() < 15) {
							lotResult.setFourth(rs.getString(10) + "-");
						} else {
							lotResult.setFourth(rs.getString(10));
						}
					} else {
						if (rs.getString(10).length() == 5) {
							lotResult.setFourth(rs.getString(10) + "------");
						} else if (rs.getString(10).length() > 5 && rs.getString(10).length() < 12) {
							lotResult.setFourth(rs.getString(10) + "-----");
						} else if (rs.getString(10).length() > 12 && rs.getString(10).length() < 18) {
							lotResult.setFourth(rs.getString(10) + "----");
						} else if (rs.getString(10).length() > 18 && rs.getString(10).length() < 24) {
							lotResult.setFourth(rs.getString(10) + "---");
						} else if (rs.getString(10).length() > 24 && rs.getString(10).length() < 30) {
							lotResult.setFourth(rs.getString(10) + "--");
						} else if (rs.getString(10).length() > 30 && rs.getString(10).length() < 36) {
							lotResult.setFourth(rs.getString(10) + "-");
						} else {
							lotResult.setFourth(rs.getString(10));
						}
					}
				}

				if (rs.getString(11) == null || "".equals(rs.getString(11)) || "null".equals(rs.getString(11))) {
					if ("MB".equalsIgnoreCase(region)) {
						lotResult.setFifth("------");
					} else {
						lotResult.setFifth("-");
					}
				} else {
					if ("MB".equalsIgnoreCase(region)) {
						if (rs.getString(11).length() == 4) {
							lotResult.setFifth(rs.getString(11) + "-----");
						} else if (rs.getString(11).length() > 4 && rs.getString(11).length() < 10) {
							lotResult.setFifth(rs.getString(11) + "----");
						} else if (rs.getString(11).length() > 10 && rs.getString(11).length() < 16) {
							lotResult.setFifth(rs.getString(11) + "---");
						} else if (rs.getString(11).length() > 16 && rs.getString(11).length() < 22) {
							lotResult.setFifth(rs.getString(11) + "--");
						} else if (rs.getString(11).length() > 24 && rs.getString(11).length() < 28) {
							lotResult.setFifth(rs.getString(11) + "-");
						} else {
							lotResult.setFifth(rs.getString(11));
						}
					} else {
						lotResult.setFifth(rs.getString(11));
					}
				}

				if (rs.getString(12) == null || "".equals(rs.getString(12)) || "null".equals(rs.getString(12))) {
					if ("MB".equalsIgnoreCase(region)) {
						lotResult.setSixth("---");
					} else {
						lotResult.setSixth("---");
					}
				} else {
					if ("MB".equalsIgnoreCase(region)) {
						if (rs.getString(12).length() == 3) {
							lotResult.setSixth(rs.getString(12) + "--");
						} else if (rs.getString(12).length() > 3 && rs.getString(12).length() < 8) {
							lotResult.setSixth(rs.getString(12) + "-");
						} else {
							lotResult.setSixth(rs.getString(12));
						}
					} else {
						if (rs.getString(12).length() == 4) {
							lotResult.setSixth(rs.getString(12) + "--");
						} else if (rs.getString(12).length() > 4 && rs.getString(12).length() < 10) {
							lotResult.setSixth(rs.getString(12) + "-");
						} else {
							lotResult.setSixth(rs.getString(12));
						}
					}
				}

				if (rs.getString(13) == null || "".equals(rs.getString(13)) || "null".equals(rs.getString(13))) {
					if ("MB".equalsIgnoreCase(region)) {
						lotResult.setSeventh("----");
					} else {
						lotResult.setSeventh("-");
					}
				} else {
					if ("MB".equalsIgnoreCase(region)) {
						if (rs.getString(13).length() == 2) {
							lotResult.setSeventh(rs.getString(13) + "---");
						} else if (rs.getString(13).length() > 2 && rs.getString(13).length() < 6) {
							lotResult.setSeventh(rs.getString(13) + "--");
						} else if (rs.getString(13).length() > 6 && rs.getString(13).length() < 10) {
							lotResult.setSeventh(rs.getString(13) + "-");
						} else {
							lotResult.setSeventh(rs.getString(13));
						}

					} else {
						lotResult.setSeventh(rs.getString(13));
					}
				}

				if (!"MB".equalsIgnoreCase(region)) {
					if (rs.getString(14) == null || "".equalsIgnoreCase(rs.getString(14))
							|| "null".equals(rs.getString(14))) {
						lotResult.setEighth("-");
					} else {
						lotResult.setEighth(rs.getString(14));
					}

				}

				lotResult.setStatus(rs.getInt(15));
				// lotResult.setUserName(rs.getString(16));
				lotResult.setLastUpdated(DateProc.Timestamp2DDMMYYYY(rs.getTimestamp(17)));
				lotResult.setProvince(rs.getString(18));
				vLottery.add(lotResult);
			}
		} catch (SQLException e) {
			logger.error("findLotteryNewestRegion: SQLException Error executing " + sql + ">>>" + e.toString());
			e.printStackTrace();
		} catch (Exception ex) {
			logger.error("findLotteryNewestRegion: Exception Error executing " + sql + ">>>" + ex.toString());
			ex.printStackTrace();
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}
		
		if(vLottery != null && !vLottery.isEmpty()) {
			Collections.sort(vLottery);
		}
		return vLottery;
	}

	public String getOpenDateNewest() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		String maxOpen = "";
		try {
			conn = poolX.getConnection();
			sql = " select to_char(max(open_date), 'dd/mm/yyyy') from lottery_result ";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				maxOpen = rs.getString(1);
			}
		} catch (SQLException e) {
			logger.error("getOpenDateNewest: SQLException Error executing " + sql + ">>>" + e.toString());
			System.out.println("getOpenDateNewest: Error executing " + sql + ">>>" + e.toString());
		} catch (Exception ex) {
			logger.error("getOpenDateNewest: Exception Error executing " + sql + ">>>" + ex.toString());
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}
		return maxOpen;
	}

	// khoi tao phan tich loto
	private List<PhantichLoto> initPhantichLoto() {
		List<PhantichLoto> list = new ArrayList<PhantichLoto>();
		PhantichLoto phantichLoto = null;
		for (int i = 0; i < Constants.LOTO.length; i++) {
			phantichLoto = new PhantichLoto();
			phantichLoto.setCapso(Constants.LOTO[i]);
			phantichLoto.setDodai(0);
			phantichLoto.setVitri1(0);
			phantichLoto.setVitri2(0);
			phantichLoto.setLoto("");
			list.add(phantichLoto);
		}

		return list;
	}

	// phan tich loto
	// @startdate bat dau tu ngay
	// @enddate den ngay
	// @nhay=1,2,3,4,5
	// @isLon co lon hay khong
	// @cau do dai cua cau
	public List<PhantichLoto> findLoto(String startdate, String enddate, int nhay, boolean isLon, int cau) {
		List<PhantichLoto> list = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String startDate = startdate + " 00:00:00";
		String endDate = enddate + " 23:59:59";
		String sql = "";

		try {
			conn = poolX.getConnection();
			sql = "select id,code,symbol,price,to_char(open_date,'DD/MM/YYYY'),special,first,second,third,fourth,fifth,sixth,seventh,eighth"
					+ " from lottery_result  where code =? and special != ''  "
					+ " and open_date between to_date(?,'DD/MM/YYYY HH24:MI:SS')"
					+ " and to_date(?,'DD/MM/YYYY HH24:MI:SS') order by open_date desc";

			ps = conn.prepareStatement(sql);
			ps.setString(1, "XSTD");
			ps.setString(2, startDate);
			ps.setString(3, endDate);
			rs = ps.executeQuery();

			list = initPhantichLoto();
			List<String> resultLoto = new ArrayList<String>();
			List<String> result = new ArrayList<String>();
			StringPro sp = new StringPro();
			listLottery = new ArrayList<LotteryResult>();
			LotteryResult lottery = null;
			while (rs.next()) {
				result.add(sp.concatResult(rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
						rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), ""));

				// lay ket qua loto
				resultLoto.add(sp.sub2RightString(rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
						rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), "no"));

				// lay ket qua xo so
				lottery = new LotteryResult();
				lottery.setOpenDate(rs.getString(5));
				lottery.setSpecial(rs.getString(6));
				lottery.setFirst(rs.getString(7));
				lottery.setSecond(rs.getString(8));

				lottery.setThird(rs.getString(9));
				lottery.setFourth(rs.getString(10));
				lottery.setFifth(rs.getString(11));
				lottery.setSixth(rs.getString(12));
				lottery.setSeventh(rs.getString(13));

				listLottery.add(lottery);

			}
			// phan tich loto
			String dau = "";
			String duoi = "";
			int vitri1 = -1;
			int vitri2 = -1;
			String strLoto = "";
			String strLotoLon = "";
			int dodai = 0;
			String strResult = "";
			List<String> listVitri1 = null;
			List<String> listVitri2 = null;
			boolean isResultLoto = false;
			boolean isResultLotoLon = false;
			String strResultLoto = "";
			for (int i = 0; i < list.size(); i++) {
				dodai = 0;
				dau = list.get(i).getCapso().substring(0, 1);
				duoi = list.get(i).getCapso().substring(1, 2);
				strResult = result.get(0);
				listVitri1 = sp.getVitri(strResult, dau);
				listVitri2 = sp.getVitri(strResult, duoi);
				if (listVitri1.size() > 0 && listVitri2.size() > 0) {
					for (int vt1 = 0; vt1 < listVitri1.size(); vt1++) {
						vitri1 = Integer.parseInt(listVitri1.get(vt1));
						for (int vt2 = 0; vt2 < listVitri2.size(); vt2++) {
							vitri2 = Integer.parseInt(listVitri2.get(vt2));
							if (vitri1 != vitri2) {
								for (int j = 1; j < result.size(); j++) {
									strResult = result.get(j);
									strLoto = strResult.substring(vitri1, vitri1 + 1)
											+ strResult.substring(vitri2, vitri2 + 1);
									// kiem tra xem con lon ko
									if (isLon) {
										// co lon
										// resultLoto.get(j-1).contains(strLoto)||resultLoto.get(j-1).contains(strLotoLon)
										strLotoLon = strResult.substring(vitri2, vitri2 + 1)
												+ strResult.substring(vitri1, vitri1 + 1);
										isResultLoto = sp.checkResultLoto(resultLoto.get(j - 1), strLoto, nhay);
										isResultLotoLon = sp.checkResultLoto(resultLoto.get(j - 1), strLotoLon, nhay);
										if (isResultLoto || isResultLotoLon) {
											dodai++;
											if (isResultLoto) {
												strResultLoto = strResultLoto + strLoto + "-";
											} else {
												strResultLoto = strResultLoto + strLotoLon + "-";
											}
											if (dodai >= cau) {
												list.get(i).setDodai(dodai);
												list.get(i).setVitri1(vitri1);
												list.get(i).setVitri2(vitri2);
												list.get(i).setLoto(strResultLoto);
											}

										} else {
											strResultLoto = "";
											break;
										}
									} else {
										// ko lon
										isResultLoto = sp.checkResultLoto(resultLoto.get(j - 1), strLoto, nhay);
										if (isResultLoto) {
											dodai++;
											strResultLoto = strResultLoto + strLoto + "-";
											if (dodai >= cau) {
												list.get(i).setDodai(dodai);
												list.get(i).setVitri1(vitri1);
												list.get(i).setVitri2(vitri2);
												list.get(i).setLoto(strResultLoto);
											}
										} else {
											strResultLoto = "";
											break;
										}
									}
								}
								dodai = 0;
							}
							// if(dodai==cau){break;}
						}
						// if(dodai==cau){break;}
					}
				}
			}

		} catch (SQLException e) {
			logger.error("loi sql " + e.toString());
		} catch (Exception e) {
			logger.error("loi ngoai le " + e.toString());
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}

		return list;
	}

	public List<LotterySpecial> findLotterySpecial(String code, String sdate, String edate) {
		List<LotterySpecial> list = null;
		List<Lottery> vLottery = findResultByCodeInTime(code, sdate, edate);
		if (vLottery != null && !vLottery.isEmpty()) {
			list = initLotterySpecial(DatePro.getDateOfWeekDDMMYYYY(vLottery.get(vLottery.size() - 1).getOpenDate()));
			Lottery lotteryResult = null;
			LotterySpecial lotterySpecial = null;
			for (int i = vLottery.size() - 1; i >= 0; i--) {
				if (list == null || list.isEmpty()) {
					list = new ArrayList<LotterySpecial>();
				}
				lotteryResult = vLottery.get(i);
				lotterySpecial = new LotterySpecial();
				lotterySpecial.setDayOfWeek(DatePro.getDateOfWeekDDMMYYYY(lotteryResult.getOpenDate()));
				lotterySpecial.setSpecial(lotteryResult.getSpecial());
				lotterySpecial.setOpenDate(lotteryResult.getOpenDate());

				if (lotteryResult != null && CommonUtil.isEmptyString(lotteryResult.getSpecial())) {
					list.add(lotterySpecial);
				}
			}
		}
		return list;
	}

	public List<LotterySpecial> initLotterySpecial(String dayOfWeek) {
		List<LotterySpecial> list = new ArrayList<LotterySpecial>();
		LotterySpecial lotterySpecial = null;
		int i = 0;
		if ("CN".equalsIgnoreCase(dayOfWeek)) {
			i = 6;
		}
		if ("7".equalsIgnoreCase(dayOfWeek)) {
			i = 5;
		}
		if ("6".equalsIgnoreCase(dayOfWeek)) {
			i = 4;
		}
		if ("5".equalsIgnoreCase(dayOfWeek)) {
			i = 3;
		}
		if ("4".equalsIgnoreCase(dayOfWeek)) {
			i = 2;
		}
		if ("3".equalsIgnoreCase(dayOfWeek)) {
			i = 1;
		}

		for (int j = 0; j < i; j++) {
			lotterySpecial = new LotterySpecial();
			lotterySpecial.setDayOfWeek("");
			lotterySpecial.setSpecial("&nbsp;");
			lotterySpecial.setOpenDate("&nbsp;");
			list.add(lotterySpecial);
		}

		return list;
	}

	public List<LotoGan> findLotoGan(String code, int numWeek, int maxgan) {
		if (numWeek < 10) {
			numWeek = 10;
		}
		List<LotoGan> listLotoGan = null;
		String edate = DateProc.getDateString(DateProc.createTimestamp());
		String sdate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(edate), -(numWeek * 7));
		List<Lottery> vLottery = findResultByCodeInTime(code, sdate, edate);
		Lottery lotteryResult = null;
		int gan = 0;
		String loto = "";
		LotoGan lotoGan = null;
		if (vLottery != null && !vLottery.isEmpty()) {
			listLotoGan = new ArrayList<LotoGan>();
			for (int i = 0; i < Constants.LOTO.length; i++) {
				gan = 0;
				lotoGan = new LotoGan();
				lotoGan.setCapso(Constants.LOTO[i]);
				lotoGan.setGan(0);
				for (int j = 0; j < vLottery.size(); j++) {
					lotteryResult = (Lottery) vLottery.get(j);
					loto = StringPro.sub2RightString(lotteryResult.getSpecial(), lotteryResult.getFirst(),
							lotteryResult.getSecond(), lotteryResult.getThird(), lotteryResult.getFourth(),
							lotteryResult.getFifth(), lotteryResult.getSixth(), lotteryResult.getSeventh(), null);
					if (!loto.contains(lotoGan.getCapso())) {
						gan++;
					} else {
						if (gan >= maxgan && gan > lotoGan.getGan()) {
							lotoGan.setGan(gan);
						}
						gan = 0;
					}
				}

				if (lotoGan.getGan() > 0) {
					listLotoGan.add(lotoGan);
				}
			}
		}

		return listLotoGan;
	}

	public List<LotoGan> statisticalSpecial(String code, int numWeek) {
		if (numWeek < 10) {
			numWeek = 10;
		}
		List<LotoGan> listLotoGan = null;
		String edate = DateProc.getDateString(DateProc.createTimestamp());
		String sdate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(edate), -(numWeek * 7));
		List<Lottery> vLottery = findResultByCodeInTime(code, sdate, edate);
		Lottery lotteryResult = null;
		int gan = 0;
		String loto = "";
		LotoGan lotoGan = null;
		if (vLottery != null && !vLottery.isEmpty()) {
			listLotoGan = new ArrayList<LotoGan>();
			for (int i = 0; i < Constants.LOTO.length; i++) {
				gan = 0;
				lotoGan = new LotoGan();
				lotoGan.setCapso(Constants.LOTO[i]);
				lotoGan.setGan(0);
				for (int j = 0; j < vLottery.size(); j++) {
					lotteryResult = (Lottery) vLottery.get(j);
					loto = StringPro.subRight(lotteryResult.getSpecial(), 2);
					if (loto.equals(lotoGan.getCapso())) {
						gan++;
					}
				}
				if (gan > 0) {
					lotoGan.setGan(gan);
					listLotoGan.add(lotoGan);
				}
			}
		}
		return listLotoGan;
	}

	public List<Mobat> findMobat(String code, String sDate) {

		if (code == null || "".equalsIgnoreCase(code)) {
			return null;
		}
		if (sDate == null || "".equalsIgnoreCase(sDate)) {
			return null;
		}

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		Mobat mobat = null;
		LotteryCompanyDAO lotteryCompanyDAO = null;
		LotteryCompany lotteryCompany = null;
		List<Mobat> list = null;
		try {
			conn = poolX.getConnection();
			if ("MB".equalsIgnoreCase(code)) {
				code = "'XSTD'";
			}
			if ("MN".equalsIgnoreCase(code)) {
				lotteryCompanyDAO = new LotteryCompanyDAO();
				String dayOfWeek = DatePro.getDateOfWeekDDMMYYYY(sDate).toUpperCase();
				Collection cCode = lotteryCompanyDAO.findCompanyByDay(dayOfWeek);
				for (Iterator i = cCode.iterator(); i.hasNext();) {
					lotteryCompany = (LotteryCompany) i.next();
					if (lotteryCompany == null) {
						continue;
					}
					if (lotteryCompany.getRegion().equalsIgnoreCase("MN")) {
						if ("MN".equalsIgnoreCase(code)) {
							code = "'" + lotteryCompany.getCode() + "'";
						} else {
							code = code + ",'" + lotteryCompany.getCode() + "'";
						}

					}
				}
			}

			if ("MT".equalsIgnoreCase(code)) {
				lotteryCompanyDAO = new LotteryCompanyDAO();
				String dayOfWeek = DatePro.getDateOfWeekDDMMYYYY(sDate).toUpperCase();
				Collection cCode = lotteryCompanyDAO.findCompanyByDay(dayOfWeek);
				for (Iterator i = cCode.iterator(); i.hasNext();) {
					lotteryCompany = (LotteryCompany) i.next();
					if (lotteryCompany == null) {
						continue;
					}
					if (lotteryCompany.getRegion().equalsIgnoreCase("MT")) {
						if ("MT".equalsIgnoreCase(code)) {
							code = "'" + lotteryCompany.getCode() + "'";
						} else {
							code = code + ",'" + lotteryCompany.getCode() + "'";
						}
					}
				}
			}

			sql = "select code, loto, cham, dacbiet, cau_loto, cau_dacbiet, to_char(gen_date,'dd/mm/yyyy'), to_char(last_updated,'dd/mm/yyyy') from lottery_mobat"
					+ "where gen_date >= STR_TO_DATE(?,'%d/%m/%Y') and gen_date < STR_TO_DATE(?,'%d/%m/%Y')+1 and code in("
					+ code + ") " + "";
			ps = conn.prepareStatement(sql);
			ps.setString(1, sDate);
			ps.setString(2, sDate);
			// ps.setString(2, code);
			rs = ps.executeQuery();
			while (rs.next()) {
				if (list == null) {
					list = new ArrayList<Mobat>();
				}
				mobat = new Mobat();
				mobat.setCode(rs.getString(1));
				mobat.setLoto(rs.getString(2));
				mobat.setCham(rs.getString(3));
				mobat.setDacbiet(rs.getString(4));
				mobat.setCau_loto(rs.getString(5));
				mobat.setCau_dacbiet(rs.getString(6));
				mobat.setGen_date(rs.getString(7));
				mobat.setLast_updated(rs.getString(8));

				if (mobat.getLoto().startsWith("-")) {
					mobat.setLoto("");
				}
				if (mobat.getCham().startsWith("-")) {
					mobat.setCham("");
				}
				if (mobat.getCau_loto().startsWith("--")) {
					mobat.setCau_loto("");
				}
				if (mobat.getCau_dacbiet().startsWith("--")) {
					mobat.setCau_dacbiet("");
				}

				if (mobat.getLoto().endsWith("-")) {
					mobat.setLoto(mobat.getLoto().substring(0, mobat.getLoto().length() - 1));
				}
				if (mobat.getCham().endsWith("-")) {
					mobat.setCham(mobat.getCham().substring(0, mobat.getCham().length() - 1));
				}
				if (mobat.getCau_loto().endsWith("|--")) {
					mobat.setCau_loto(mobat.getCau_loto().substring(0, mobat.getCau_loto().length() - 3));
				}
				if (mobat.getCau_dacbiet().endsWith("|--")) {
					mobat.setCau_dacbiet(mobat.getCau_dacbiet().substring(0, mobat.getCau_dacbiet().length() - 3));
				}
				list.add(mobat);
			}
		} catch (SQLException e) {
			logger.error("LotteryResultDAO==>>findMobat==>>loi sql " + e.toString());
			System.out.println("LotteryResultDAO==>>findMobat==>>loi sql " + e.toString());
		} catch (Exception e) {
			logger.error("LotteryResultDAO==>>findMobat==>>loi ngoai le " + e.toString());
			System.out.println("LotteryResultDAO==>>findMobat==>>loi ngoai le " + e.toString());
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}

		return list;
	}

	public List<Mobat> findHistoryMobat(String code, String sDate, int numday) {

		if (code == null || "".equalsIgnoreCase(code)) {
			return null;
		}
		if (sDate == null || "".equalsIgnoreCase(sDate)) {
			return null;
		}

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		Mobat mobat = null;
		List<Mobat> list = null;
		// sDate="10/05/2015";
		String edate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(sDate), -numday);
		System.out.println("edate==>" + edate);
		LotteryCompanyDAO lotteryCompanyDAO = null;
		LotteryCompany lotteryCompany = null;
		try {
			conn = poolX.getConnection();

			List<Lottery> listLotteryResult = null;

			String codeMobat = "";
			lotteryCompanyDAO = new LotteryCompanyDAO();
			Collection cCode = lotteryCompanyDAO.findLotteryCompany(code);
			for (Iterator i = cCode.iterator(); i.hasNext();) {
				lotteryCompany = (LotteryCompany) i.next();
				if (lotteryCompany == null) {
					continue;
				}
				if ("".equalsIgnoreCase(codeMobat)) {
					codeMobat = "'" + lotteryCompany.getCode() + "'";
				} else {
					codeMobat = codeMobat + ",'" + lotteryCompany.getCode() + "'";
				}
			}

			if (numday == 0) {
				sql = "select code, loto, cham, dacbiet, cau_loto, cau_dacbiet, to_char(gen_date,'dd/mm/yyyy'), to_char(last_updated,'dd/mm/yyyy') from lottery_mobat"
						+ "    where gen_date <= to_date(?, 'dd/mm/yyyy') and gen_date >= STR_TO_DATE(?,'%d/%m/%Y') and code in( "
						+ codeMobat + ") order by code desc,gen_date desc" + "";

				ps = conn.prepareStatement(sql);
				ps.setString(1, sDate);
				ps.setString(2, edate);
				// ps.setString(3, code);
				rs = ps.executeQuery();

				if (!rs.next()) {
					sDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(sDate), -1);
					edate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(sDate), -numday);
				}
			}

			if (!"MB".equalsIgnoreCase(code) && !"MN".equalsIgnoreCase(code) && !"MT".equalsIgnoreCase(code)) {
				listLotteryResult = findResultByCodeInTime(code, edate, sDate);
				code = "'" + code + "'";
			}
			if ("MB".equalsIgnoreCase(code)) {
				code = "'XSTD'";
				listLotteryResult = findResultByCodeInTime(code.replaceAll("'", ""), edate, sDate);
			}

			if ("MN".equalsIgnoreCase(code) || "MT".equalsIgnoreCase(code)) {
				listLotteryResult = findResultByRegionInDay(code, edate, sDate);
			}

			sql = "select code, loto, cham, dacbiet, cau_loto, cau_dacbiet, to_char(gen_date,'dd/mm/yyyy'), to_char(last_updated,'dd/mm/yyyy') from lottery_mobat"
					+ "where gen_date <= to_date(?, 'dd/mm/yyyy') and gen_date >= STR_TO_DATE(?,'%d/%m/%Y') and code in( "
					+ codeMobat + ") order by code desc,gen_date desc" + "";

			ps = conn.prepareStatement(sql);
			ps.setString(1, sDate);
			ps.setString(2, edate);

			// ps.setString(3, code);
			rs = ps.executeQuery();
			int i = 0;
			String loto = "";
			String lotodacbiet = "";
			Lottery lotteryResult = null;
			while (rs.next()) {
				if (list == null || list.isEmpty()) {
					list = new ArrayList<Mobat>();
				}

				mobat = new Mobat();
				mobat.setCode(rs.getString(1));
				mobat.setLoto(rs.getString(2));
				mobat.setCham(rs.getString(3));
				mobat.setDacbiet(rs.getString(4));
				mobat.setCau_loto(rs.getString(5));
				mobat.setCau_dacbiet(rs.getString(6));
				mobat.setGen_date(rs.getString(7));
				mobat.setLast_updated(rs.getString(8));
				mobat.setLototrung("");
				mobat.setDacbiettrung("");
				mobat.setChamtrung("");
				if (mobat.getLoto().startsWith("-")) {
					mobat.setLoto("");
				}
				if (mobat.getCham().startsWith("-")) {
					mobat.setCham("");
				}
				if (mobat.getCau_loto().startsWith("--")) {
					mobat.setCau_loto("");
				}
				if (mobat.getCau_dacbiet().startsWith("--")) {
					mobat.setCau_dacbiet("");
				}

				if (mobat.getLoto().endsWith("-")) {
					mobat.setLoto(mobat.getLoto().substring(0, mobat.getLoto().length() - 1));
				}
				if (mobat.getCham().endsWith("-")) {
					mobat.setCham(mobat.getCham().substring(0, mobat.getCham().length() - 1));
				}
				if (mobat.getCau_loto().endsWith("|--")) {
					mobat.setCau_loto(mobat.getCau_loto().substring(0, mobat.getCau_loto().length() - 3));
				}
				if (mobat.getCau_dacbiet().endsWith("|--")) {
					mobat.setCau_dacbiet(mobat.getCau_dacbiet().substring(0, mobat.getCau_dacbiet().length() - 3));
				}

				if (listLotteryResult != null && !listLotteryResult.isEmpty()) {
					lotteryResult = listLotteryResult.get(i);
					if (lotteryResult != null && lotteryResult.getOpenDate().equals(mobat.getGen_date())
							&& lotteryResult.getSpecial() != null && !"".equals(lotteryResult.getSpecial())) {
						loto = StringPro.sub2RightString(lotteryResult.getSpecial(), lotteryResult.getFirst(),
								lotteryResult.getSecond(), lotteryResult.getThird(), lotteryResult.getFourth(),
								lotteryResult.getFifth(), lotteryResult.getSixth(), lotteryResult.getSeventh(),
								lotteryResult.getEighth());
						lotodacbiet = StringPro.subRight(lotteryResult.getSpecial(), 2);

						mobat.setLototrung(StringPro.checkMobatLoto(loto, mobat.getLoto()));
						mobat.setDacbiettrung(StringPro.checkMobatDacBiet(lotodacbiet, mobat.getDacbiet()));
						mobat.setChamtrung(StringPro.checkMobatChamDacBiet(lotodacbiet, mobat.getCham()));
						i++;
					}
				}

				list.add(mobat);
			}
		} catch (SQLException e) {
			logger.error("LotteryResultDAO==>>findMobat==>>loi sql " + e.toString());
			System.out.println("LotteryResultDAO==>>findMobat==>>loi sql " + e.toString());
		} catch (Exception e) {
			logger.error("LotteryResultDAO==>>findMobat==>>loi ngoai le " + e.toString());
			System.out.println("LotteryResultDAO==>>findMobat==>>loi ngoai le " + e.toString());
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}

		return list;
	}

	public void insert(Lottery lottery) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "";

		try {
			conn = poolX.getConnection();
			sql = "insert into lottery_result(special,first,second,third,fourth,fifth,sixth,seventh,eighth,code,id,symbol,price,open_date,status)values(?,?,?,?,?,?,?,?,?,?,lottery_seo_seq.nextval,?,?,sysdate,1)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, lottery.getSpecial());
			ps.setString(2, lottery.getFirst());
			ps.setString(3, lottery.getSecond());
			ps.setString(4, lottery.getThird());
			ps.setString(5, lottery.getFourth());
			ps.setString(6, lottery.getFifth());
			ps.setString(7, lottery.getSixth());
			ps.setString(8, lottery.getSeventh());
			ps.setString(9, lottery.getEighth());
			ps.setString(10, lottery.getCode());
			ps.setString(11, lottery.getCode());
			ps.setString(12, "5000");

			ps.execute();

		} catch (SQLException e) {
			System.out.println("loi sql " + e.toString());
		} catch (Exception e) {
			System.out.println("loi ngoai le " + e.toString());
		} finally {
			poolX.releaseConnection(conn, ps);
		}
	}

	public boolean createWithOpenDate(LotteryResult lottery) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "";

		try {
			conn = poolX.getConnection();
			sql = "insert into lottery_result(special,first,second,third,fourth,fifth,sixth,"
					+ "                       seventh,eighth,code,symbol,price,open_date,status,"
					+ "                       user_name, create_date)"
					+ " values(?,?,?,?,?,?,?,?,?,?,?,?,str_to_date(?, '%d/%m/%Y'),1,?, now())";
			ps = conn.prepareStatement(sql);
			ps.setString(1, lottery.getSpecial());
			ps.setString(2, lottery.getFirst());
			ps.setString(3, lottery.getSecond());
			ps.setString(4, lottery.getThird());
			ps.setString(5, lottery.getFourth());
			ps.setString(6, lottery.getFifth());
			ps.setString(7, lottery.getSixth());
			ps.setString(8, lottery.getSeventh());
			ps.setString(9, lottery.getEighth());
			ps.setString(10, lottery.getCode());
			ps.setString(11, lottery.getSymbol());
			ps.setString(12, "5000");
			ps.setString(13, lottery.getOpenDate());
			ps.setString(14, lottery.getUserName());

			return ps.executeUpdate() == 1;

		} catch (SQLException e) {
			System.out.println("loi sql " + e.toString());
		} catch (Exception e) {
			System.out.println("loi ngoai le " + e.toString());
		} finally {
			poolX.releaseConnection(conn, ps);
		}
		return false;
	}

	public boolean updateLiveXS(LotteryResult lottery) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "";

		try {
			conn = poolX.getConnection();
			sql = "     update lottery_result"
					+ " set special = ?, first = ?, second = ?, third = ?, fourth = ?, fifth = ?, "
					+ "     sixth = ?, seventh = ?, eighth = ?" + " where id = ?";

			ps = conn.prepareStatement(sql);
			ps.setString(1, lottery.getSpecial());
			ps.setString(2, lottery.getFirst());
			ps.setString(3, lottery.getSecond());
			ps.setString(4, lottery.getThird());
			ps.setString(5, lottery.getFourth());
			ps.setString(6, lottery.getFifth());
			ps.setString(7, lottery.getSixth());
			ps.setString(8, lottery.getSeventh());
			ps.setString(9, lottery.getEighth());
			ps.setBigDecimal(10, lottery.getId());

			return ps.executeUpdate() == 1;

		} catch (SQLException e) {
			System.out.println("[updateLiveXS] loi sql " + e.toString());
		} catch (Exception e) {
			System.out.println("[updateLiveXS] loi ngoai le " + e.toString());
		} finally {
			poolX.releaseConnection(conn, ps);
		}
		return false;
	}

	public boolean updateLottery(Lottery lottery) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "";

		try {
			conn = poolX.getConnection();
			sql = "     update lottery_result"
					+ " set special = ?, first = ?, second = ?, third = ?, fourth = ?, fifth = ?, "
					+ "     sixth = ?, seventh = ?, eighth = ?" + " where id = ?";

			ps = conn.prepareStatement(sql);
			ps.setString(1, lottery.getSpecial());
			ps.setString(2, lottery.getFirst());
			ps.setString(3, lottery.getSecond());
			ps.setString(4, lottery.getThird());
			ps.setString(5, lottery.getFourth());
			ps.setString(6, lottery.getFifth());
			ps.setString(7, lottery.getSixth());
			ps.setString(8, lottery.getSeventh());
			ps.setString(9, lottery.getEighth());
			ps.setInt(10, lottery.getId());

			return ps.executeUpdate() == 1;

		} catch (SQLException e) {
			System.out.println("[updateLiveXS] loi sql " + e.toString());
		} catch (Exception e) {
			System.out.println("[updateLiveXS] loi ngoai le " + e.toString());
		} finally {
			poolX.releaseConnection(conn, ps);
		}
		return false;
	}

	public Lottery find4TraThuong() {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Lottery lottery = null;

		try {
			String sql = " select id, code, symbol, price, open_date, special, first, second, third, "
					+ "           fourth, fifth, sixth, seventh, eighth, status, user_name, last_updated "
					+ "    from lottery_result "
					+ "    where DATE_FORMAT(open_date,'%d/%m/%Y') = DATE_FORMAT(now(),'%d/%m/%Y') and symbol = 'TD' "
					+ "          and special is not null and special != '' ";
			conn = poolX.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.next()) {
				lottery = new Lottery();
				lottery.setId(rs.getInt(1));
				lottery.setCode(rs.getString(2));
				lottery.setSymbol(rs.getString(3));
				lottery.setPrice(rs.getInt(4));
				lottery.setOpenDate(DateProc.Timestamp2DDMMYYYY(rs.getTimestamp(5)));
				lottery.setSpecial(rs.getString(6));
				lottery.setFirst(rs.getString(7));
				lottery.setSecond(rs.getString(8));
				lottery.setThird(rs.getString(9));
				lottery.setFourth(rs.getString(10));
				lottery.setFifth(rs.getString(11));
				lottery.setSixth(rs.getString(12));
				lottery.setSeventh(rs.getString(13));
				lottery.setEighth(rs.getString(14));
				lottery.setStatus(rs.getInt(15));
				// lottery.setUserName(rs.getString(16));
				lottery.setLastUpdated(DateProc.Timestamp2DDMMYYYY(rs.getTimestamp(17)));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}

		return lottery;
	}

	public Lottery findResultByCodeInDay66(Connection conn, String code, String date) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		Lottery lotResult = null;
		date = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(date), 0);
		String startDate = date + " 00:00:00";
		String endDate = date + " 23:59:59";

		try {
			sql = "select a.id, a.code, symbol, price, open_date, special, first, second, third, "
					+ "fourth, fifth, sixth, seventh, eighth, status, user_name, a.last_updated,b.name  "
					+ "from lottery_result  a join lottery_company b on a.code  = b.code "
					+ "where a.code = ? and open_date >= str_to_date(?, '%d/%m/%Y %H:%i:%s') "
					+ "and open_date < str_to_date(?, '%d/%m/%Y %H:%i:%s')";
			ps = conn.prepareStatement(sql);
			ps.setString(1, code);
			ps.setString(2, startDate);
			ps.setString(3, endDate);
			rs = ps.executeQuery();

			if (rs.next()) {
				lotResult = new Lottery();
				lotResult.setId(rs.getInt(1));
				lotResult.setCode(rs.getString(2));
				lotResult.setSymbol(rs.getString(3));
				lotResult.setPrice(rs.getInt(4));
				lotResult.setOpenDate(DateProc.Timestamp2DDMMYYYY(rs.getTimestamp(5)));
				lotResult.setSpecial(rs.getString(6));
				lotResult.setFirst(rs.getString(7));
				lotResult.setSecond(rs.getString(8));
				lotResult.setThird(rs.getString(9));
				lotResult.setFourth(rs.getString(10));
				lotResult.setFifth(rs.getString(11));
				lotResult.setSixth(rs.getString(12));
				lotResult.setSeventh(rs.getString(13));
				lotResult.setEighth(rs.getString(14));
				lotResult.setStatus(rs.getInt(15));
				// lotResult.setUserName(rs.getString(16));
				lotResult.setLastUpdated(DateProc.Timestamp2DDMMYYYY(rs.getTimestamp(17)));
				lotResult.setProvince(rs.getString(18));
			}
		} catch (SQLException e) {
			logger.error("findResultByRegionInDay66: SQLException Error executing " + sql + ">>>" + e.toString());
			System.out
					.println("findResultByRegionInDay66: Error executing " + date + ">>>" + sql + ">>>" + e.toString());
		} catch (Exception ex) {
			logger.error("findResultByRegionInDay66: Exception Error executing " + sql + ">>>" + ex.toString());
		} finally {
			poolX.releaseConnection(null, ps, rs);
		}
		return lotResult;
	}

	public boolean createWithOpenDate66(LotteryResult lottery) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "";

		try {
			conn = poolX.getConnection();
			if (findResultByCodeInDay66(conn, lottery.getCode(), lottery.getOpenDate()) != null) {
				return true;
			}
			sql = "insert into lottery_result(special,first,second,third,fourth,fifth,sixth,"
					+ "                       seventh,eighth,code,symbol,price,open_date,status,"
					+ "                       user_name, create_date)"
					+ " values(?,?,?,?,?,?,?,?,?,?,?,?,str_to_date(?, '%d/%m/%Y'),1,?, now())";
			ps = conn.prepareStatement(sql);
			ps.setString(1, lottery.getSpecial());
			ps.setString(2, lottery.getFirst());
			ps.setString(3, lottery.getSecond());
			ps.setString(4, lottery.getThird());
			ps.setString(5, lottery.getFourth());
			ps.setString(6, lottery.getFifth());
			ps.setString(7, lottery.getSixth());
			ps.setString(8, lottery.getSeventh());
			ps.setString(9, lottery.getEighth());
			ps.setString(10, lottery.getCode());
			ps.setString(11, lottery.getSymbol());
			ps.setString(12, "5000");
			ps.setString(13, lottery.getOpenDate());
			ps.setString(14, lottery.getUserName());

			return ps.executeUpdate() == 1;

		} catch (SQLException e) {
			System.out.println("loi sql " + e.toString());
		} catch (Exception e) {
			System.out.println("loi ngoai le " + e.toString());
		} finally {
			poolX.releaseConnection(conn, ps);
		}
		return false;
	}

	public LotteryResult createWithOpenDate77(LotteryResult lottery) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "";

		try {
			conn = poolX.getConnection();
			conn.setAutoCommit(false);
			// sql = " delete from lottery_result"
			// + " where code = ? and open_date = str_to_date(?, '%d/%m/%Y')";
			// ps = conn.prepareStatement(sql);
			// ps.setString(1, lottery.getCode());
			// ps.setString(2, lottery.getOpenDate());
			// if(ps.executeUpdate() < 1) return null;

			Integer nextId = getNextId(conn);
			if (nextId == null) {
				return null;
			}
			lottery.setId(new BigDecimal(nextId));

			sql = "insert into lottery_result(special,first,second,third,fourth,fifth,sixth,"
					+ "                       seventh,eighth,code,symbol,price,open_date,status,"
					+ "                       user_name, create_date, id)"
					+ " values(?,?,?,?,?,?,?,?,?,?,?,?,str_to_date(?, '%d/%m/%Y'),1,?, now(), ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, lottery.getSpecial());
			ps.setString(2, lottery.getFirst());
			ps.setString(3, lottery.getSecond());
			ps.setString(4, lottery.getThird());
			ps.setString(5, lottery.getFourth());
			ps.setString(6, lottery.getFifth());
			ps.setString(7, lottery.getSixth());
			ps.setString(8, lottery.getSeventh());
			ps.setString(9, lottery.getEighth());
			ps.setString(10, lottery.getCode());
			ps.setString(11, lottery.getSymbol());
			ps.setString(12, "5000");
			ps.setString(13, lottery.getOpenDate());
			ps.setString(14, lottery.getUserName());
			ps.setBigDecimal(15, lottery.getId());

			if (ps.executeUpdate() == 1) {
				conn.commit();
				return lottery;
			}

		} catch (SQLException e) {
			System.out.println("loi sql " + e.toString());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("loi ngoai le " + e.toString());
			e.printStackTrace();
		} finally {
			poolX.releaseConnection(conn, ps);
		}
		return null;
	}

	public Integer getNextId(Connection conn) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql = "select max(id) from lottery_result";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt(1) + 1;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			poolX.releaseConnection(null, ps, rs);
		}

		return null;
	}

	public LotteryResult findLRByCodeInDay(String code, String date) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		LotteryResult lotResult = null;
		date = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(date), 0);
		String startDate = date + " 00:00:00";
		String endDate = date + " 23:59:59";

		try {
			conn = poolX.getConnection();
			sql = "select a.id, a.code, symbol, price, open_date, special, first, second, third, "
					+ "fourth, fifth, sixth, seventh, eighth, status, user_name, a.last_updated,b.name  "
					+ "from lottery_result  a join lottery_company b on a.code  = b.code "
					+ "where a.code = ? and open_date >= str_to_date(?, '%d/%m/%Y %H:%i:%s') "
					+ "and open_date < str_to_date(?, '%d/%m/%Y %H:%i:%s')";
			ps = conn.prepareStatement(sql);
			ps.setString(1, code);
			ps.setString(2, startDate);
			ps.setString(3, endDate);
			rs = ps.executeQuery();

			if (rs.next()) {
				lotResult = new LotteryResult();
				lotResult.setId(rs.getBigDecimal(1));
				lotResult.setCode(rs.getString(2));
				lotResult.setSymbol(rs.getString(3));
				lotResult.setPrice(rs.getBigDecimal(4));
				lotResult.setOpenDate(DateProc.Timestamp2DDMMYYYY(rs.getTimestamp(5)));
				lotResult.setSpecial(rs.getString(6));
				lotResult.setFirst(rs.getString(7));
				lotResult.setSecond(rs.getString(8));
				lotResult.setThird(rs.getString(9));
				lotResult.setFourth(rs.getString(10));
				lotResult.setFifth(rs.getString(11));
				lotResult.setSixth(rs.getString(12));
				lotResult.setSeventh(rs.getString(13));
				lotResult.setEighth(rs.getString(14));
				lotResult.setStatus(rs.getInt(15));
				// lotResult.setUserName(rs.getString(16));
				lotResult.setLastUpdated(DateProc.Timestamp2DDMMYYYY(rs.getTimestamp(17)));
				lotResult.setProvince(rs.getString(18));
			}
		} catch (SQLException e) {
			logger.error("findLRByCodeInDay: SQLException Error executing " + sql + ">>>" + e.toString());
			System.out.println("findLRByCodeInDay: Error executing " + date + ">>>" + sql + ">>>" + e.toString());
		} catch (Exception ex) {
			logger.error("findLRByCodeInDay: Exception Error executing " + sql + ">>>" + ex.toString());
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}
		return lotResult;
	}

	//
	public void deleteDulicateRecordsLoterry(String openDate) {
		List<Lottery> rs = getDulicateRecordsLoterry(openDate);
		List<Lottery> listExist = new ArrayList<>();

		if (rs != null && rs.size() > 0) {
			for (Lottery lottery : rs) {
				if (checkDataValid(lottery) && checkInNotList(lottery, listExist)) {
					listExist.add(lottery);
				} else {
					removeRecordsLoteyry(lottery.getId());
				}
			}
		}

	}

	private List<Lottery> getDulicateRecordsLoterry(String openDate) {
		StringBuilder sql = new StringBuilder();

		sql.append(" Select id, special, first, second, third, fourth, fifth, sixth, seventh, symbol, code");
		sql.append(" From lottery_result");
		sql.append(" where open_date = '").append(openDate).append("' and special != ''");

		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		List<Lottery> results = new ArrayList<Lottery>();

		try {
			conn = poolX.getConnection();
			preStmt = conn.prepareStatement(sql.toString());
			rs = preStmt.executeQuery();

			Lottery lotResult = null;
			while (rs.next()) {
				lotResult = new Lottery();
				lotResult.setId(rs.getInt(1));
				lotResult.setSpecial(rs.getString(2));
				lotResult.setFirst(rs.getString(3));
				lotResult.setSecond(rs.getString(4));
				lotResult.setThird(rs.getString(5));
				lotResult.setFourth(rs.getString(6));
				lotResult.setFifth(rs.getString(7));
				lotResult.setSixth(rs.getString(8));
				lotResult.setSeventh(rs.getString(9));
				lotResult.setSymbol(rs.getString(10));
				lotResult.setCode(rs.getString(11));
				// lotResult.setUserName(rs.getString(16));

				if (lotResult != null && !CommonUtil.isEmptyString(lotResult.getSpecial())) {
					results.add(lotResult);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			poolX.releaseConnection(conn, preStmt, rs);
			return results;
		}
	}

	private void removeRecordsLoteyry(int id) {
		StringBuilder sql = new StringBuilder();

		sql.append(" DELETE  ");
		sql.append(" From lottery_result");
		sql.append(" where id = ").append(id);

		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		List<Lottery> results = new ArrayList<Lottery>();

		try {
			conn = poolX.getConnection();
			preStmt = conn.prepareStatement(sql.toString());
			preStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			poolX.releaseConnection(conn, preStmt, rs);
		}
	}

	private boolean checkInNotList(Lottery lottery, List<Lottery> listExist) {
		if (listExist != null && listExist.size() > 0) {
			for (Lottery item : listExist) {
				if (lottery.getSymbol().equalsIgnoreCase(item.getSymbol())
						&& lottery.getCode().equalsIgnoreCase(item.getCode())
						&& lottery.getSpecial().equalsIgnoreCase(item.getSpecial())) {
					return false;
				}
			}
		}

		return true;
	}

	public LotteryResult findLotteryNewestRegionSMS(String region) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		LotteryResult lotResult = null;

		try {
			conn = poolX.getConnection();
			sql = " select nameEng, open_date,"
					+ " special, first, second, third, fourth, fifth, sixth, seventh, eighth"
					+ " from lottery_result as rs, lottery_company as com" + " where symbol = ? and rs.code = com.code"
					+ " order by open_date desc" + " LIMIT 1;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, region);
			rs = ps.executeQuery();

			if (rs.next()) {
				lotResult = new LotteryResult();
				lotResult.setCode(rs.getString(1));
				lotResult.setOpenDate(DateProc.Timestamp2DDMMYYYY(rs.getTimestamp(2)));
				lotResult.setSpecial(rs.getString(3));
				lotResult.setFirst(rs.getString(4));
				lotResult.setSecond(rs.getString(5));
				lotResult.setThird(rs.getString(6));
				lotResult.setFourth(rs.getString(7));
				lotResult.setFifth(rs.getString(8));
				lotResult.setSixth(rs.getString(9));
				lotResult.setSeventh(rs.getString(10));
				lotResult.setEighth(rs.getString(11));
			}
		} catch (SQLException e) {
			logger.error("findLRByCodeInDay1: SQLException Error executing " + sql + ">>>" + e.toString());
		} catch (Exception ex) {
			logger.error("findLRByCodeInDay1: Exception Error executing " + sql + ">>>" + ex.toString());
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}
		return lotResult;
	}

	public String[][] findLotteryResultMonthAsc(String sDate, String eDate) {

		String sql = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		String[][] kq = new String[32][13];
		try {
			sql = "select SUBSTRING(special, 1, 3) as firstSpecial, SUBSTRING(special, 4, 2) as secondSpecial, "
					+ "DAY(open_date) as day, MONTH(open_date) as month from lottery_result where code = 'XSTD'"
					+ " and special != '' and open_date >= str_to_date(?, '%d/%m/%Y %H:%i:%s') and open_date <= str_to_date(?, '%d/%m/%Y %H:%i:%s')"
					+ " order by open_date asc";
			conn = poolX.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, sDate);
			ps.setString(2, eDate);

			rs = ps.executeQuery();
			while (rs.next()) {
				String first = rs.getString(1);
				String second = rs.getString(2);

				String special = first + "<span class ='txt_red'>" + second + "</span>";
				int j = rs.getInt(4);
				int i = rs.getInt(3);
				kq[i][j] = special;
			}

		} catch (SQLException e) {
			System.out.println(e.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}

		return kq;
	}

	public List<LotteryResult> findLotteryResultLimit(String code, String limit) {

		List<LotteryResult> list = null;
		String sql = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			sql = "select id, code,symbol,price,date_format(open_date,'%d/%m/%Y'),special,first,second,third,fourth,fifth,"
					+ "sixth,seventh,eighth,date_format(create_date,'%d/%m/%Y') from lottery_result "
					+ "where code=? and special != ''  order by open_date desc LIMIT "
					+ limit;
			conn = poolX.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, code);

			rs = ps.executeQuery();
			list = new ArrayList<>();
			LotteryResult lotteryResult = null;
			int index = 1;
			while (rs.next()) {
				index = 1;
				lotteryResult = new LotteryResult();
				lotteryResult.setId(new BigDecimal(rs.getString(index++)));
				lotteryResult.setCode(rs.getString(index++));
				lotteryResult.setSymbol(rs.getString(index++));
				lotteryResult.setPrice(new BigDecimal(rs.getString(index++)));
				lotteryResult.setOpenDate(rs.getString(index++));
				lotteryResult.setSpecial(rs.getString(index++));
				lotteryResult.setFirst(rs.getString(index++));
				lotteryResult.setSecond(rs.getString(index++));
				lotteryResult.setThird(rs.getString(index++));
				lotteryResult.setFourth(rs.getString(index++));
				lotteryResult.setFifth(rs.getString(index++));
				lotteryResult.setSixth(rs.getString(index++));
				lotteryResult.setSeventh(rs.getString(index++));
				lotteryResult.setEighth(rs.getString(index++));
				lotteryResult.setCreate_date(rs.getString(index++));

				list.add(lotteryResult);
			}

		} catch (SQLException e) {
			System.out.println(e.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}

		return list;
	}
	
	public List<Lottery> findLotteryLimit(String code, String limit) {

		List<Lottery> list = null;
		String sql = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			sql = "select code,symbol,price,date_format(open_date,'%d/%m/%Y'),special,first,second,third,fourth,fifth,"
					+ "sixth,seventh,eighth,date_format(create_date,'%d/%m/%Y') from lottery_result "
					+ "where code=? and special != ''  order by open_date desc LIMIT "
					+ limit;
			conn = poolX.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, code);

			rs = ps.executeQuery();
			list = new ArrayList<>();
			Lottery lotteryResult = null;
			int index = 1;
			while (rs.next()) {
				index = 1;
				lotteryResult = new Lottery();
				lotteryResult.setCode(rs.getString(index++));
				lotteryResult.setSymbol(rs.getString(index++));
				lotteryResult.setPrice(rs.getInt(index++));
				lotteryResult.setOpenDate(rs.getString(index++));
				lotteryResult.setSpecial(rs.getString(index++));
				lotteryResult.setFirst(rs.getString(index++));
				lotteryResult.setSecond(rs.getString(index++));
				lotteryResult.setThird(rs.getString(index++));
				lotteryResult.setFourth(rs.getString(index++));
				lotteryResult.setFifth(rs.getString(index++));
				lotteryResult.setSixth(rs.getString(index++));
				lotteryResult.setSeventh(rs.getString(index++));
				lotteryResult.setEighth(rs.getString(index++));

				list.add(lotteryResult);
			}

		} catch (SQLException e) {
			System.out.println(e.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}

		return list;
	}
	
	
	/**
	 * for sitemap
	 * 
	 * get result of province by date
	 *
	 * @param code
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<LotteryResult> findResultInTime(String sDate, String eDate) {

		List<LotteryResult> list = null;
		String sql = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			sql = "select code,symbol,price,date_format(open_date,'%d-%m-%Y'),special,first,second,third,fourth,fifth,"
					+ "sixth,seventh,eighth,date_format(create_date,'%d-%m-%Y') from lottery_result "
					+ "where date(open_date) between date(?) and date(?)   order by open_date desc";
			conn = poolX.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, sDate);
			ps.setString(2, eDate);

			rs = ps.executeQuery();
			list = new ArrayList<>();
			LotteryResult lotteryResult = null;
			int index = 1;
			while (rs.next()) {
				index = 1;
				lotteryResult = new LotteryResult();
				lotteryResult.setCode(rs.getString(index++));
				lotteryResult.setSymbol(rs.getString(index++));
				lotteryResult.setPrice(new BigDecimal(rs.getString(index++)));
				lotteryResult.setOpenDate(rs.getString(index++));
				lotteryResult.setSpecial(rs.getString(index++));
				lotteryResult.setFirst(rs.getString(index++));
				lotteryResult.setSecond(rs.getString(index++));
				lotteryResult.setThird(rs.getString(index++));
				lotteryResult.setFourth(rs.getString(index++));
				lotteryResult.setFifth(rs.getString(index++));
				lotteryResult.setSixth(rs.getString(index++));
				lotteryResult.setSeventh(rs.getString(index++));
				lotteryResult.setEighth(rs.getString(index++));
				lotteryResult.setCreate_date(rs.getString(index++));

				list.add(lotteryResult);
			}

		} catch (SQLException e) {
			System.out.println(e.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}

		return list;
	}

}
