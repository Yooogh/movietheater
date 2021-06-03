package Model;

import java.sql.*;
import java.util.Scanner;

public class MyPageDAOImpl implements MyPageDAO{
	
    private static final String driver = "oracle.jdbc.driver.OracleDriver";
    private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String user = "scott";
    private static final String pw = "tiger";
    private Connection con;
    private Statement state;
    private ResultSet rs;
    
	@Override
	public void signUpMember(MyPageVO mp) {
		// TODO 회원 가입
    	connDB();
    	
    	String id = mp.getId();
    	String pw = mp.getPw();
    	String name = mp.getName();
    	String birth = mp.getBirth();
    	
    	String query = "INSERT INTO MYPAGE" +
    					"(id, pw, name, birth)" +
    					"VALUES(" +"'" +
    					id + "','" +
    					pw + "','" +
    					name + "','" +
    					birth + "')";
    	
    	try {
			state.executeUpdate(query);
			state.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}	
    }
    
	
	@Override
	public void viewMember(MyPageVO mp) {
		// TODO 내 정보 조회
			connDB();
			
			String ID = mp.getId();
			
			String query = "SELECT ID, NAME, BIRTH FROM MYPAGE WHERE ID = ?";
			//실행 쿼리
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, ID);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {//rs.next() 출력
				String id = rs.getString("ID");
				String name = rs.getString("NAME");
				String birth = rs.getString("BIRTH");
				System.out.println("ID : " + ID +
									", 이름 : " + name +
									", 생년월일 : " + birth);

				MyPageVO myPageVO = new MyPageVO();
				myPageVO.setId(id);

				rs.close();
				state.close();
				con.close();
			}

		} catch (Exception e) {
			e.getMessage();
		}
		
	}


	@Override
	public void modifyMember(MyPageVO mp) {
		// TODO 정보 수정
		try {

			connDB();

			String query = "UPDATE MYPAGE SET PW = ?, NAME = ?, BIRTH = ? WHERE ID = ?";

			PreparedStatement pstmt = con.prepareStatement(query);
			
			String whereID = mp.getId();//해당 아이디
			String setPW = mp.getPw();//수정할 비번
			String setName = mp.getName();//수정할 이름
			String setBirth = mp.getBirth();//수정할 생일

			pstmt.setString(4, whereID);
			pstmt.setString(1, setPW);
			pstmt.setString(2, setName);
			pstmt.setString(3, setBirth);

			pstmt.executeUpdate();

			state.close();
			con.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}


	@Override
	public void deleteMember(MyPageVO mp) {
		// TODO 회원 탈퇴
		try {

			connDB();
			
			String query = "DELETE FROM MYPAGE WHERE ID = ? AND PW = ?";
			
			PreparedStatement pstmt = con.prepareStatement(query);
				
			String delID = mp.getId();
			String delPW = mp.getPw();
				
			pstmt.setString(1, delID);//입력한 걸 저장
			pstmt.setString(2, delPW);

			pstmt.executeUpdate();//처리

			state.close();
			con.close();

		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	
	
	//아이디 중복 검사
	public int redupleID(String id) {
		
		connDB();
		
		int cntID = 0;
		String query = "select count(id) as cnt from mypage where id = ?";
		
		try {

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next() == true) {
				cntID = rs.getInt("cnt");
			} //cnt가 1이면 중복 0이면 안 중복

			state.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cntID;
		
	}
	
	public void connDB(){
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,pw);
            state = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
    

}
