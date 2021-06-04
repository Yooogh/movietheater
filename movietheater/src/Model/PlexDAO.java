package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.DriverManager;

import Model.PlexVO;
import Model.ReservationVO;

public class PlexDAO {
	ArrayList<ReservationVO> Rlist = new ArrayList< ReservationVO >();
	
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private static final String user = "scott";
	private static final String pwd = "tiger";
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,pwd);
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public void close(Statement stmt, ResultSet rs, Connection conn) {
		try {
			if (stmt != null)
				stmt.close();
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close(PreparedStatement ps, ResultSet rs, Connection conn) {
		try {
			if (ps != null)
				ps.close();
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int regPlex(PlexVO vo) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = this.getConnection();
			String sql = "insert into Plex " + "(PlexNo, name, C, R) "
					+ "values (?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getPlexNo());
			pstmt.setString(2, vo.getName());
			pstmt.setInt(3, vo.getColumn());
			pstmt.setInt(4, vo.getRow());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		} finally {
			close(pstmt, null, conn);
		}
		return result;
	}

	public PlexVO selectByNo(int plexNo) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PlexVO vo = null;

		try {
			conn = this.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from Plex where PlexNo = '" + plexNo + "'";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				vo = new PlexVO();
				vo.setPlexNo(rs.getInt("PlexNo"));
				vo.setName(rs.getString("name"));
				vo.setRow(rs.getInt("R"));
				vo.setColumn(rs.getInt("C"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt, rs, conn);
		}
		return vo;
	}

	public boolean IsExist(int plexNo) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		boolean exist = false;
		try {
			conn = this.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from Plex where PlexNo = '" + plexNo + "'";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				exist = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt, rs, conn);
		}
		return exist;
	}
	
	public boolean IsExistName(String name) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		boolean exist = false;
		try {
			conn = this.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from Plex where name = '" + name + "'";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				exist = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt, rs, conn);
		}
		return exist;
	}

	public int delete(int plexNo) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		try {
			conn = this.getConnection();
			String sql = "delete from Plex " + "where PlexNo = '" + plexNo + "'";
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt, null, conn);
		}

		return result;
	}
/*	
	public int deleteVer2(int plexNo) {
		ArrayList<ReservationVO> plist = new ArrayList< ReservationVO >();
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		try {
			conn = DBConnection.getConnection();
	        conn.setAutoCommit(false);
	        MemberDAO mdao = new MemberDAO();
	        MemberVO mvo = new MemberVO();
	        for(ReservationVO vo : Rlist){
	        	System.out.println();
	        	Long reserveId = vo.getReserve_id();
	        	String id = vo.getUserId();
	        	int money = vo.getTotalPrice();
	        	mdao.selectById(id);
	        	int point = mvo.getPoint();
	        	mvo.setPoint(point+money);
	        	mdao.update(mvo);
				String sql2 = "delete * from Reservation where PlexNo = '" + plexNo + "and reserve_id ='" + reserveId + "'";
				stmt = conn.createStatement();
				stmt.executeUpdate(sql2);
	        }
	        String sql ="";
			//String sql = "delete from Plex " + "where PlexNo = '" + plexNo + "'";
	        sql = "delete from Plex " + "where PlexNo = '" + plexNo + "'";
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt, null, conn);
		}

		return result;
	}
*/
///*	
	public ArrayList<ReservationVO> LeftReserve(int plexNo) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<ReservationVO> list = null;

		try {
			conn = this.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from Reservation where PlexNo = '" + plexNo + "'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ReservationVO vo = new ReservationVO();
				vo.setReserve_id(rs.getLong("reserve_id"));
				vo.setTheaterName(rs.getString("theaterName"));
				vo.setMovieName(rs.getString("movieName"));
				vo.setSeat(rs.getString("seat"));
				vo.setReserveDay(rs.getDate("reserveDay").toLocalDate());
				vo.setReserveTime(rs.getString("reserveTime"));
				vo.setPeople(rs.getInt("people"));
				vo.setTotalPrice(rs.getInt("totalPrice"));
				vo.setUserId(rs.getString("userId"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt, rs, conn);
		}
		return list;
	}
//*/
//ReservationDAO로 옮겨야 함
///*
	public ArrayList<ReservationVO> getReservedSeat(int plexNo, LocalDate ReserveDay, String reserveTime) {
		Connection conn = null;
		//Statement stmt = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ReservationVO> list = null;

		try {
			conn = this.getConnection();
			String sql = "select * from Reservation " + "where plexNo = ? and ReserveDay = ? and reserveTime = ?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, plexNo);
			Date ReserveDate = Date.valueOf(ReserveDay);
			ps.setDate(2, ReserveDate);
			ps.setString(3, reserveTime);
			rs = ps.executeQuery();
			while (rs.next()) {
				ReservationVO vo = new ReservationVO();
				vo.setReserve_id(rs.getLong("reserve_id"));
				vo.setTheaterName(rs.getString("theaterName"));
				vo.setMovieName(rs.getString("movieName"));
				vo.setSeat(rs.getString("seat"));
				vo.setReserveDay(rs.getDate("reserveDay").toLocalDate());
				vo.setReserveTime(rs.getString("reserveTime"));
				vo.setPeople(rs.getInt("people"));
				vo.setTotalPrice(rs.getInt("totalPrice"));
				vo.setUserId(rs.getString("userId"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps, rs, conn);
		}
		return list;
	}
//*/
	
	public ArrayList<PlexVO> selectAll() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<PlexVO> list = null;

		try {
			conn = this.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from Plex order by PlexNo";
			rs = stmt.executeQuery(sql);
			list = new ArrayList<PlexVO>();
			while (rs.next()) {
				PlexVO vo = new PlexVO();
				vo.setPlexNo(rs.getInt("PlexNo"));
				vo.setName(rs.getString("name"));
				vo.setRow(rs.getInt("R"));
				vo.setColumn(rs.getInt("C"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt, rs, conn);
		}
		return list;
	}

	public int update(PlexVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = this.getConnection();
			String sql = "update Plex " + "set PlexNo = ?, name = ?, C = ?, R = ?"
					+ "where PlexNo = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getPlexNo());
			pstmt.setString(2, vo.getName());
			pstmt.setInt(3, vo.getColumn());
			pstmt.setInt(4, vo.getRow());
			pstmt.setInt(5, vo.getPlexNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, null, conn);
		}
		return result;
	}
}
