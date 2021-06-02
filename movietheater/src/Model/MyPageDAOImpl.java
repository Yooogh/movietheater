package Model;

import java.sql.*;
import java.util.Scanner;

public class MyPageDAOImpl implements MyPageDAO{
	
	//정보 수정 세분화
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
			closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
    }
    
    

	@Override
	public void viewMember(MyPageVO mp) {
		// TODO 내 정보 조회
		String PW = mp.getPw();
		String ID = mp.getId();

		try {

			connDB();

			String query = "SELECT ID, NAME, BIRTH FROM MYPAGE WHERE ID = ?";
			//실행 쿼리

			PreparedStatement pstmt = con.prepareStatement(query);
			
			Scanner sc = new Scanner(System.in);//입력
			String viewID;
			viewID = sc.next();

			pstmt.setString(1, viewID);
			ResultSet rs = pstmt.executeQuery();
			
			
			while(rs.next()) {//rs.next() 출력
				String id = rs.getString("ID");
				String name = rs.getString("NAME");
				String birth = rs.getString("BIRTH");
				System.out.println("ID : " + ID +
									", 이름 : " + name +
									", 생년월일 : " + birth);
			}
			
			

			closeDB();

		} catch (Exception e) {
			e.getMessage();
		}
		
	}



	@Override
	public void modifyMember(MyPageVO mp) {
		// TODO 정보 수정

		String PW = mp.getPw();
		String name = mp.getName();
		String birth = mp.getBirth();
		String ID = mp.getId();

		try {

			connDB();

		String query = "UPDATE MYPAGE SET PW = ?, NAME = ?, BIRTH = ? WHERE ID = ?";
		System.out.println(query);

			PreparedStatement pstmt = con.prepareStatement(query);
			// 뭐를 업데이트 해야하는지?
			

			System.out.println("해당 아이디");
			String whereID = mp.getId();
			System.out.println("수정할 비번");
			String setPW = mp.getPw();
			System.out.println("수정할 이름");
			String setName = mp.getName();
			System.out.println("수정할 생일");
			String setBirth = mp.getBirth();


			pstmt.setString(4, whereID);
			pstmt.setString(1, setPW);
			pstmt.setString(2, setName);
			pstmt.setString(3, setBirth);


			pstmt.executeUpdate();

			closeDB();

		} catch (Exception e) {
			e.getMessage();
		}
	}



	@Override
	public void deleteMember(MyPageVO mp) {
		// TODO 회원 탈퇴
		String PW = mp.getPw();
		String ID = mp.getId();

		try {

			connDB();

			String query = "DELETE FROM MYPAGE WHERE ID = ? AND PW = ?";
			
			PreparedStatement pstmt = con.prepareStatement(query);
				
			Scanner sc = new Scanner(System.in);//입력
			String delID = "";
			String delPW = "";
				
			pstmt.setString(1, delID);//입력한 걸 저장
			pstmt.setString(1, delPW);

			pstmt.executeUpdate();//처리

			closeDB();

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
			}
//				cnt가 1이면 중복 0이면 안 중복
			
			closeDB();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cntID;
		
	}
	
	//비번 이름 생일 변경
	public void updatePW(MyPageVO mp) {
		String PW = mp.getPw();

		try {

			connDB();

		String query = "UPDATE MYPAGE SET PW = ? WHERE ID = ?";
		System.out.println(query);

			PreparedStatement pstmt = con.prepareStatement(query);
			// 뭐를 업데이트 해야하는지?
			

			System.out.println("해당 아이디");
			String whereID = mp.getId();
			System.out.println("수정할 비번");
			String setPW = mp.getPw();

			pstmt.setString(4, whereID);
			pstmt.setString(1, setPW);

			pstmt.executeUpdate();

			closeDB();

		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	public void updateName() {
		
	}
	
	public void updateBirth() {
		
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
    
	public void closeDB() {
		try {
			state.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
