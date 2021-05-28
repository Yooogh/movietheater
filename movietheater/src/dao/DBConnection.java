package dao;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnection {
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
}