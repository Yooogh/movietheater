package Model;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {
    //JDBC세팅
    private static final String driver = "oracle.jdbc.driver.OracleDriver";
    private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String user = "scott";
    private static final String pw = "tiger";
    private Connection con;
    private Statement state;
    private ResultSet rs;

    public Long save(ReservationVO reserve){
        connDB();
        String query = "INSERT INTO Reservation_Test" +
                "(theatername, moviename, seat, people, totalPrice, userId)" +
                "VALUES('"+reserve.getTheaterName()+"','"+
                reserve.getMovieName()+"','"+
                reserve.getSeat()+"','"+
                reserve.getPeople()+"','"+
                reserve.getTotalPrice()+"','"+
                reserve.getUserId()+"')";

        try {
            state.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1L;
    }

    public void remove(Long id){
        connDB();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE from Reservation_Test "
            + "where reserve_id like ?");
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ReservationVO findOne(Long id){
        ReservationVO reserve = new ReservationVO();
        connDB();
        String query = "select * from Reservation_Test where reserve_id like "+ id ;
        ResultSet rs = null;
        try {
            rs = state.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            reserve.setTheaterName(rs.getString("theaterName"));
            reserve.setMovieName(rs.getString("movieName"));
            reserve.setSeat(rs.getString("seat"));
            reserve.setReserveDay(rs.getDate("reserveDay"));
            reserve.setReserveTime(rs.getDate("reserveTime"));
            reserve.setPeople(rs.getInt("people"));
            reserve.setTotalPrice(rs.getInt("totalPrice"));
            reserve.setUserId(rs.getString("userId"));
        } catch (Exception e) {
            e.printStackTrace();
        }
       return reserve;
    }

    public List<ReservationVO> findAll(String userid){
        List<ReservationVO> reservationList = new ArrayList<>();
        connDB();
        String query = "select * from Reservation_Test";
        ResultSet rs = null;
        try {
            rs = state.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            while(rs.next()){
                ReservationVO reserve = new ReservationVO();
                reserve.setTheaterName(rs.getString("theaterName"));
                reserve.setMovieName(rs.getString("movieName"));
                reserve.setSeat(rs.getString("seat"));
                reserve.setReserveDay(rs.getDate("reserveDay"));
                reserve.setReserveTime(rs.getDate("reserveTime"));
                reserve.setPeople(rs.getInt("people"));
                reserve.setTotalPrice(rs.getInt("totalPrice"));
                reserve.setUserId(rs.getString("userId"));
                reservationList.add(reserve);
            }
            rs.close();
            state.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //예약 중 user id가 일치하는 예약 전부 반환
        return reservationList;
    }

    public int getSales(LocalDate date){
        int total = 0;
        connDB();
        String query = "select totalPrice from Reservation_Test where reserveDay like " + date ;
        ResultSet rs = null;
        try {
            total = rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
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
