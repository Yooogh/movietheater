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

    public Long save(ReservationVO reserve){
        connDB();
        String query = "INSERT INTO Reservation_Test" +
                "(reserve_id,theatername, moviename, seat, reserveDay, reserveTime, people, totalPrice, userId)" +
                "VALUES(reserve_id.nextval,"+"'"+
                reserve.getTheaterName()+"','"+
                reserve.getMovieName()+"','"+
                reserve.getSeat()+"','"+
                reserve.getReserveDay()+"','"+
                reserve.getReserveTime()+"','"+
                reserve.getPeople()+"','"+
                reserve.getTotalPrice()+"','"+
                reserve.getUserId()+"')";

        try {
            state.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reserve.getReserve_id();
    }

    public void remove(Long id){
        connDB();
            String query = "DELETE from Reservation_Test where reserve_id like " + "'" + id + "'";
            ResultSet rs= null;
        try {
            rs = state.executeQuery(query);
            rs.close();
            state.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ReservationVO findOne(Long id) throws Exception{
        ReservationVO reserve = new ReservationVO();
        connDB();
        String query = "select * from Reservation_Test where reserve_id like " + "'" + id + "'";
        ResultSet rs = null;
        rs = state.executeQuery(query);

        reserve.setReserve_id(rs.getLong("reserve_id"));
        reserve.setTheaterName(rs.getString("theaterName"));
        reserve.setMovieName(rs.getString("movieName"));
        reserve.setSeat(rs.getString("seat"));
        reserve.setReserveDay(rs.getDate("reserveDay").toLocalDate());
        reserve.setReserveTime(rs.getString("reserveTime"));
        reserve.setPeople(rs.getInt("people"));
        reserve.setTotalPrice(rs.getInt("totalPrice"));
        reserve.setUserId(rs.getString("userId"));

        rs.close();
        state.close();
        con.close();

       return reserve;
    }

    public List<ReservationVO> findAll(String userid) throws Exception{
        List<ReservationVO> reservationList = new ArrayList<>();
        connDB();
        String query = "select * from Reservation_Test where userId like " + "\'" + userid + "\'";
        ResultSet rs = null;
        rs = state.executeQuery(query);

        while(rs.next()){
            ReservationVO reserve = new ReservationVO();

            reserve.setReserve_id(rs.getLong("reserve_id"));
            reserve.setTheaterName(rs.getString("theaterName"));
            reserve.setMovieName(rs.getString("movieName"));
            reserve.setSeat(rs.getString("seat"));
            reserve.setReserveDay(rs.getDate("reserveDay").toLocalDate());
            reserve.setReserveTime(rs.getString("reserveTime"));
            reserve.setPeople(rs.getInt("people"));
            reserve.setTotalPrice(rs.getInt("totalPrice"));
            reserve.setUserId(rs.getString("userId"));

            reservationList.add(reserve);
        }
        rs.close();
        state.close();
        con.close();

        //예약 중 user id가 일치하는 예약 전부 반환
        return reservationList;
    }

    public void deleteAll(){
        connDB();
        String query = "DELETE from Reservation_Test";
        ResultSet rs= null;
        try {
            rs = state.executeQuery(query);
            rs.close();
            state.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int getSales(LocalDate date){
        int total = 0;
        connDB();
        String query = "select * from Reservation_Test where reserveDay like " + "\'" + date + "\'";
        ResultSet rs = null;
        try {
            rs = state.executeQuery(query);
            while(rs.next())
                total += rs.getInt(1);
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
