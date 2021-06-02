package Controller;

import Model.ReservationDAO;
import Service.ReservationService;
import View.ReservationView;

public class ReservationController {

    public void reserveInitialize(){
        ReservationDAO dao = new ReservationDAO();
        ReservationService service = new ReservationService(dao);
        ReservationView view = new ReservationView(service);

        view.mainMenu();
    }

    public void loadTheater(){
        //Theater 가져오는 메소드
    }

    public void loadMovie(){
        //영화목록 가져오는 메소드
    }



}
