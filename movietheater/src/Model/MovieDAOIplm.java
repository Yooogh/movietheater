package Model;

import Model.MovieDAO;
import Model.MovieVO;

import java.sql.*;


public class MovieDAOIplm implements MovieDAO {
	
	private static final String driver = "oracle.jdbc.driver.OracleDriver"; 
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE"; 
	private static final String user = "scott";
	private static final String pwd = "tiger"; 
	private Connection con;
	private Statement stmt;
	private ResultSet rs;	

	public MovieVO searchMovie(String title) throws Exception {
		connDB();  
		MovieVO movieVo = new MovieVO();
		PreparedStatement pst = con.prepareStatement("SELECT * FROM MOVIE WHERE TITLE LIKE ?" );
		pst.setString(1, title);



		ResultSet rs = null;
		rs = pst.executeQuery();
		rs.next();
		movieVo.setTitle(rs.getString("title"));
		movieVo.setDirector(rs.getString("director"));
		movieVo.setGenre(rs.getString("genre"));
		movieVo.setRate(rs.getString("rate"));

	
		return movieVo;
		}

	public void delMovie(String title) {
		connDB();
		String query = "DELETE FROM MOVIE WHERE TITLE LIKE" + "'" + title + "'";
		try {
			rs = stmt.executeQuery(query);
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void addMovie(MovieVO MVO) {
		connDB();
		String query = "INSERT INTO MOVIE(title, director, genre, rate)" + "values ('" + MVO.getTitle()+"', '"+ MVO.getDirector() + "', '" +MVO.getGenre() + "', '" + MVO.getRate() + "')";
	
		try {
			stmt.executeUpdate(query);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void connDB() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,pwd);
			stmt = con.createStatement();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}

