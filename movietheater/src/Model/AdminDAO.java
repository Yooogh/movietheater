package Model;

import java.sql.*;
import java.util.ArrayList;

public class AdminDAO {
    private static final String driver = "oracle.jdbc.driver.OracleDriver";
    private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String user = "scott";
    private static final String pw = "tiger";
    private Connection con;
    private Statement state;

    public void saveID(AdminVO AVO){
        connDB();   //DB에 연결
        String query = "INSERT INTO AdminAccount(adminID, adminPW, adminNAME)"
                        + "values('"+AVO.getAdminID() +"', '"+ AVO.getAdminPW()+"', '" + AVO.getAdminNAME()+"')";
        //AdminVO의 adminID와 adminPW를 가져와 AdminAccount의 ID와 PW칸에 추가

        try {
            state.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteID(String ID){
        connDB();
        String query = "DELETE from AdminAccount where adminID like" + "'" + ID + "'";
        // 쿼리문을 이용하여 DB내 AdminAccount에서 일치하는 ID를 삭제
        ResultSet rs = null;

        try {
            rs = state.executeQuery(query);
            rs.close();
            state.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean LoginID(String ID, String PW){
        connDB();
        ResultSet rs = null;
        try {
            PreparedStatement PS = con.prepareStatement("SELECT * FROM  AdminAccount where adminID like ? and adminPW like ?");
            PS.setString(1, ID);
            PS.setString(2, PW);
            rs = PS.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<AdminVO> viewAdminAccountList() {    //전체 관리자 조회
        ArrayList<AdminVO> ADMlist = new ArrayList<AdminVO>();

        connDB();
        ResultSet rs = null;
        String query = "SELECT adminID, adminNAME FROM adminaccount";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next()) {
                AdminVO avo = new AdminVO();
                avo.setAdminID(rs.getString("adminID"));
                avo.setAdminNAME(rs.getString("adminNAME"));
                ADMlist.add(avo);
            }

            rs.close();
            state.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ADMlist;
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
