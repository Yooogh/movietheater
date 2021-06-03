package Controller;

import Model.ReservationDAO;
import Model.ReservationVO;
import java.time.LocalDate;
import java.util.List;

public class ReservationController {

    private ReservationDAO reserveDAO = new ReservationDAO();

    public Long save(ReservationVO reserve){
        reserveDAO.save(reserve);
        return reserve.getReserve_id();
    }

    public void remove(Long id){
        reserveDAO.remove(id);
    }

    public void deleteAll(){
        reserveDAO.deleteAll();
    }

    public ReservationVO findOne(Long id){
        ReservationVO one = null;
        try {
            one = reserveDAO.findOne(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return one;
    }

    public List<ReservationVO> findAll(String userid){
        List<ReservationVO> all = null;
        try {
            all = reserveDAO.findAll(userid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return all;
    }

    public int getSales(LocalDate date){
        int total =0;

        try {
            total = reserveDAO.getSalesByDay(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    public void loadTheater(){
        //Theater 가져오는 메소드
    }

    public void loadMovie(){
        //영화목록 가져오는 메소드
    }



}
