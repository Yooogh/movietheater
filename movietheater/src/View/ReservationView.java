package View;


import Controller.ReservationController;
import Controller.SeatController;
import Model.MyPageVO;
import Model.PlexDAO;
import Model.ReservationVO;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ReservationView{

    private ReservationController reservationController = new ReservationController();

    Scanner scanner = new Scanner(System.in);

    public void mainMenu(MyPageVO myPageVO) {

        MyPageView myPageView = new MyPageView();

        int select = 0;
        System.out.println("예약시스템을 시작합니다.");
        System.out.println("=========== 메뉴 선택 ===========");
        System.out.println("1. 예약하기 2. 예약확인 3. 예약취소 4.돌아가기");
        select = scanner.nextInt();

        while(!(select ==1 || select ==2 || select == 3 || select == 4)){
            System.out.println("다시 입력해주십시오");
            select = scanner.nextInt();
        }

        switch (select){
            case 1:
                ReservationCMD(myPageVO);
                break;
            case 2:
                ReservationCheck(myPageVO);
                break;
            case 3:
                ReservationCancel(myPageVO);
                break;
            case 4:
                myPageView.userMain(myPageVO);
                scanner.close();
                break;
        }

    }

    public void ReservationCMD(MyPageVO myPageVO){
        ReservationVO reserve = new ReservationVO();
        List<String> plexList = reservationController.loadTheaterString();
        SeatController seatController = new SeatController();
        PlexDAO plexDAO = new PlexDAO();

        String[] movie = tempMovie();
        String[] seat = tempSeat();

        int selectTheater = 0;
        int selectMovie = 0;
        String selectSeat;
        int selectPeople = 0;

        LocalDate reserveNow = LocalDate.now();

        String userId = myPageVO.getId();

        for(int i = 0 ; i<movie.length; i++){
            System.out.print(movie[i] + "\t");
        }

        System.out.println();
        System.out.print("예약 할 영화를 고르세요 : ");
        selectMovie = scanner.nextInt();

        for(int i =0; i<plexList.size(); i++)
            System.out.print(plexList.get(i) + "\t");

        System.out.println();
        System.out.print("상영관을 고르세요 : ");
        selectTheater = scanner.nextInt();

        //좌석 뷰코드
        //int plexNo = plexDAO.selectByNo(selectTheater).getPlexNo();
        PlexDAO pdao = new PlexDAO();
        if(plexList.size() < selectTheater) {
             System.out.println("잘못된 상영관입니다.");
             return;
        }
        String name = plexList.get(selectTheater - 1);
        seatController.SeatLeftPrint(name, reserveNow);

        System.out.print("좌석을 고르세요 : ");
        selectSeat = scanner.next();


//        System.out.print("예약할 인원을 선택하세요 : ");
//        selectPeople = scanner.nextInt();
//
        reserve.setTheaterName(plexList.get(selectTheater-1));
        reserve.setMovieName(movie[selectMovie-1]);
        reserve.setSeat(selectSeat);
        reserve.setReserveDay(reserveNow);
        reserve.setPeople(1);
        reserve.setTotalPrice(selectPeople * 10000);
        reserve.setUserId(userId);

        reservationController.save(reserve);
        System.out.println("예약이 완료 되었습니다.");

        mainMenu(myPageVO);

    }

    public void ReservationCheck(MyPageVO myPageVO) {
        System.out.printf("현재 예매현황을 확인");
        System.out.println("1을 누르면 돌아갑니다.");
        System.out.println("=========================================");
        int select = 0;

        List<ReservationVO> test = reservationController.findAll(myPageVO.getId());

        for(int i = 0; i<test.size(); i++){
            System.out.print("예약번호 : " + test.get(i).getReserve_id()+ "\t");
            System.out.print("상영관 : " + test.get(i).getTheaterName() + "\t");
            System.out.print("영화 : " + test.get(i).getMovieName()+ "\t");
            System.out.print("예약일 : " + test.get(i).getReserveDay()+ "\t");
            System.out.print("예매 인원 : " + test.get(i).getPeople()+ "\t");
            System.out.println("좌석 : " + test.get(i).getSeat());
        }
        select = scanner.nextInt();

        if(select == 1)
            mainMenu(myPageVO);
    }

    public void ReservationCancel(MyPageVO myPageVO){
        System.out.println("예약을 취소합니다.");
        Long select = 0L;
        List<ReservationVO> test = reservationController.findAll(myPageVO.getId());

        for(int i = 0; i<test.size(); i++){
            System.out.print("예약번호 : " + test.get(i).getReserve_id()+ "  |  ");
            System.out.print("상영관 : " + test.get(i).getTheaterName() + "\t");
            System.out.print("영화 : " + test.get(i).getMovieName()+ "\t" + "\t");
            System.out.print("예약일 : " + test.get(i).getReserveDay()+ "\t");
            System.out.print("예매 인원 : " + test.get(i).getPeople()+ "\t");
            System.out.println("좌석 : " + test.get(i).getSeat());
        }

        System.out.print("예약번호를 입력하세요 : ");
        select = scanner.nextLong();

        reservationController.remove(select);
        System.out.println(select + "번의 예약이 취소되었습니다.");

        mainMenu(myPageVO);

    }

    public void ReservationDelete(){
        System.out.println("전체 예약을 취소합니다.");
        reservationController.deleteAll();

    }

    private String[] tempTheater(){
        String[] theater = {"1관", "2관", "3관", "4관"};
        return theater;
    }

    private String[] tempMovie(){
        String[] Movie = {"분노의 질주", "파이프라인","500일의 썸머", "크루엘라"};
        return Movie;
    }

    private String[] tempSeat(){
        String[] seat = {"A1", "A2","B1","B2"};
        return seat;
    }

}
