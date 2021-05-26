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

    public ReservationVO findOne(Long id){
        ReservationVO one = reserveDAO.findOne(id);
        return one;
    }

    public List<ReservationVO> findAll(String userid){
        List<ReservationVO> all = reserveDAO.findAll(userid);
        return all;
    }

    public int getSales(LocalDate date){
        return reserveDAO.getSales(date);
    }

    //상영관 리스트 전체 조회

    //영화 리스트 전체 조회





}
