package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MemberDAO {

	public String login(String id, String password) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String result = null;

		try {
			conn = DBConnection.getConnection();
			String sql = "select id from member " + "where id = ? and password = ? ";

			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getString("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps, rs, null);
		}
		return result;
	}

	public void close(Statement stmt, ResultSet rs, Connection conn) {
		try {
			if (stmt != null)
				stmt.close();
			if (rs != null)
				stmt.close();
			if (conn != null)
				stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close(PreparedStatement stmt, ResultSet rs, Connection conn) {
		try {
			if (stmt != null)
				stmt.close();
			if (rs != null)
				stmt.close();
			if (conn != null)
				stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int regMember(MemberVO vo) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "insert into Member " + "(id, password, name, birth) " + "values ( ?,?,?,TO_DATE(?, 'YYYY-MM-DD))";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getBirth());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, null, null);
		}
		return result;
	}

	public MemberVO selectById(String id) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		MemberVO vo = new MemberVO();

		try {
			conn = DBConnection.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from Member where id = '" + id + "'";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				vo.setId(rs.getString("id"));
				vo.setPassword(rs.getString("password"));
				vo.setName(rs.getString("name"));
				vo.setBirth(rs.getString("birth").split(" ")[0]);
				vo.setPoint(rs.getInt("points"));
				vo.setAdmin(rs.getInt("admin"));
				vo.setCreated(rs.getString("created").split(" ")[0]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt, rs, null);
		}
		return vo;
	}

	public int delete(String id) {
		Connection conn = null;
		Statement stmt = null;

		if (id.equals("admin")) {
			return -1;
		}

		int result = 0;
		try {
			conn = DBConnection.getConnection();
			String sql = "delete from Member " + "where id = '" + id + "'";
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt, null, null);
		}

		return result;
	}

	public ArrayList<MemberVO> selectAll() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<MemberVO> clist = null;

		try {
			conn = DBConnection.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from Member";
			rs = stmt.executeQuery(sql);
			clist = new ArrayList<MemberVO>();
			while (rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setPassword(rs.getString("password"));
				vo.setName(rs.getString("name"));
				vo.setBirth(rs.getString("birth").split(" ")[0]);
				vo.setPoint(rs.getInt("points"));
				vo.setAdmin(rs.getInt("admin"));
				vo.setCreated(rs.getString("created").split(" ")[0]);
				clist.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt, rs, null);
		}
		return clist;
	}

	public int update(MemberVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = DBConnection.getConnection();
			String sql = "update Member " + "set name = ?, password = ?, birth = TO_DATE(?, 'YYYY-MM-DD), points =?" + "where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getBirth());
			pstmt.setLong(4, vo.getPoint());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, null, null);
		}
		return result;
	}
}
