package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
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
}