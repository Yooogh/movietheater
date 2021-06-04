package Model;

import sun.reflect.annotation.ExceptionProxy;

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
            state.close();
            con.close();
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
            state.executeUpdate(query);
            state.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ReservationVO findOne(Long id) throws Exception{
        ReservationVO reserve = new ReservationVO();
        connDB();
        PreparedStatement pst = con.prepareStatement("select * from Reservation_Test where reserve_id like ?");
        pst.setLong(1,id);
        ResultSet rs = null;
        rs = pst.executeQuery();

        reserve = settingData(rs);

        rs.close();
        state.close();
        con.close();

       return reserve;
    }

    public List<ReservationVO> findAll(String userid) throws Exception{
        List<ReservationVO> reservationList = new ArrayList<>();
        connDB();
        PreparedStatement pst = con.prepareStatement("select * from Reservation_Test where UserId like ?");
        pst.setString(1,userid);
        ResultSet rs = null;
        rs = pst.executeQuery();

        while(rs.next()){
            reservationList.add(settingData(rs));
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
        try {
            state.executeUpdate(query);
            state.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int getSalesByDay(LocalDate date) throws Exception {
        int total = 0;
        connDB();
        PreparedStatement pst = con.prepareStatement("select sum(totalPrice), reserveDay from Reservation_Test group by reserveDay having reserveDay = ?");
        pst.setDate(1,Date.valueOf(date));
        ResultSet rs = null;
        rs = pst.executeQuery();
        rs.next();
        total = rs.getInt(1);

        return total;
    }

    public int getSalesByMonth(String startDay, String lastDay) throws Exception{
        int total = 0;
        connDB();
        PreparedStatement pst
                = con.prepareStatement("select sum(totalPrice) from Reservation_Test where reserveDay between to_date(?) and to_date(?)");
        pst.setString(1, startDay);
        pst.setString(2,lastDay);

        ResultSet rs = null;
        rs = pst.executeQuery();
        rs.next();
        total = rs.getInt(1);

        return total;
    }

    public int getSalesByMovie(String movieName) throws Exception{
        int total = 0;
        connDB();
        PreparedStatement pst = con.prepareStatement("select sum(totalPrice) from Reservation_Test where movieName like ? ");
        pst.setString(1,movieName);

        ResultSet rs = null;
        rs = pst.executeQuery();
        rs.next();

        total = rs.getInt(1);

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

    private ReservationVO settingData(ResultSet rs){
        ReservationVO reserve = new ReservationVO();

        try {
            reserve.setReserve_id(rs.getLong("reserve_id"));
            reserve.setTheaterName(rs.getString("theaterName"));
            reserve.setMovieName(rs.getString("movieName"));
            reserve.setSeat(rs.getString("seat"));
            reserve.setReserveDay(rs.getDate("reserveDay").toLocalDate());
            reserve.setReserveTime(rs.getString("reserveTime"));
            reserve.setPeople(rs.getInt("people"));
            reserve.setTotalPrice(rs.getInt("totalPrice"));
            reserve.setUserId(rs.getString("userId"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reserve;
    }

}
