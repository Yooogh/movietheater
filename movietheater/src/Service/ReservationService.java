package Service;

import Model.ReservationDAO;
import Model.ReservationVO;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class ReservationService {

    private final ReservationDAO reserveDAO;

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
            total = reserveDAO.getSales(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    //상영관 리스트 전체 조회

    //영화 리스트 전체 조회





}
