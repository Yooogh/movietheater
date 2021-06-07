package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SeatDAO {
	
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

	public ArrayList<SeatVO> getReservedSeat(String plexName, LocalDate ReserveDay, String reserveTime) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<SeatVO> list = new ArrayList<SeatVO>();

		try {
			conn = this.getConnection();
			String sql = "select * from Reservation_Test " + "where TheaterName = ? and ReserveDay = TO_DATE(?,'YYYYMMDD') and reserveTime = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, plexName);
			String ReserveDate = ReserveDay.format(DateTimeFormatter.ofPattern("YYYYMMdd"));
			ps.setString(2, ReserveDate);
			ps.setString(3, reserveTime);
			rs = ps.executeQuery();
			while (rs.next()) {
				SeatVO vo = new SeatVO();
				vo.setReserve_id(rs.getLong("reserve_id"));
				vo.setSeat(rs.getString("seat"));
				vo.setReserveDay(rs.getDate("reserveDay").toLocalDate());
				vo.setReserveTime(rs.getString("reserveTime"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps, rs, conn);
		}
		return list;
	}
	
	public static void main(String[] args) {
	}

}
