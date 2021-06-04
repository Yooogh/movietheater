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
    
	@Override
	public void signUpMember(MyPageVO mp) {
		// TODO 회원 가입
    	connDB();
    	ResultSet rs = null;
    	
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
    
	public MyPageVO loginMember(String id, String pw) {
		connDB();
		ResultSet rs = null;
		
		MyPageVO mp = new MyPageVO();
		
		String query = "SELECT * FROM  MYPAGE where ID = ? and PW = ?";
		//쿼리
		
		try {
			PreparedStatement pstmt = con.prepareStatement(query);//con으로부터 가져옴
			pstmt.setString(1, id); //?에 값 넣기
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();//쿼리 실행
			
			if(rs.next()) {//다음것이 있는지 true false 반환
				mp.setId(rs.getString("ID"));
				mp.setPw(rs.getString("PW"));
				mp.setName(rs.getString("NAME"));
				mp.setBirth(rs.getString("BIRTH"));
			}
				if(!rs.getString(2).equals(pw)) //실행 비번과 접속시도 비번 맞으면 성공
					return null;//로그인 성공
							
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mp;//데이터베이스 오류
		//없으면 null
	}
	
	
	@Override
	public MyPageVO viewMember(String ID) {
		// TODO 내 정보 조회
			connDB();
			MyPageVO mp = new MyPageVO();
			
			String query = "SELECT ID, NAME, BIRTH FROM MYPAGE WHERE ID LIKE ?";
			
			try {
				
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1, ID);
				
				ResultSet rs = null;
				rs = pstmt.executeQuery();
				
				rs.next();
				
				mp.setId(rs.getString("id"));
				mp.setName(rs.getString("name"));
				mp.setBirth(rs.getString("birth"));

				rs.close();
				state.close();
				con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}

		return mp;
		
	}


	@Override
	public void modifyMember(MyPageVO mp) {
		// TODO 정보 수정
		try {

			connDB();
			ResultSet rs = null;
			
			String query = "UPDATE MYPAGE SET PW = ?, NAME = ?, BIRTH = ? WHERE ID = ?";

			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, mp.getPw());
			pstmt.setString(2, mp.getName());
			pstmt.setString(3, mp.getBirth());
			pstmt.setString(4, mp.getId());

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
			ResultSet rs = null;
			
			String query = "DELETE FROM MYPAGE WHERE ID = ? AND PW = ?";
			
			PreparedStatement pstmt = con.prepareStatement(query);
								
			pstmt.setString(1, mp.getId());//입력한 걸 저장
			pstmt.setString(2, mp.getPw());

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
		ResultSet rs = null;
		
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
