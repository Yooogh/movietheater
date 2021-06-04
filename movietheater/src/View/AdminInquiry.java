package View;


import Controller.ReservationController;
import Model.MyPageDAOImpl;
import Model.MyPageVO;
import Model.ReservationVO;

import java.util.List;
import java.util.Scanner;

public class AdminInquiry {

    public void memberView() {      //  호출되는 메소드

    Scanner sc = new Scanner(System.in);
    MyPageDAOImpl MPDI = new MyPageDAOImpl();
    ReservationController RC = new ReservationController();

        while (true) {
            System.out.println("=========회원정보 관리=========");
            System.out.println("1. 전체 회원 조회");
            System.out.println("2. 회원 ID 조회");
            System.out.println("3. 얘매 내역 조회");
            System.out.println("4. 초기화면으로 돌아가기");
            System.out.println("5. 프로그램 종료");
            System.out.print("조회 항목 선택: ");
            String i = sc.nextLine();   //  조회를 원하는 항목 번호 입력

            switch(i) {     //  조회를 원하는 번호를 입력시 해당 항목 출력

                case "1":
                    System.out.println("========전체 회원 조회========");
                    System.out.println("=======ID|이름|생년월일=======");
                    String uid = sc.nextLine(); //  회원 ID 입력
                    List<MyPageVO> memberList = MPDI.viewMemberList();
                    for (int j=0; j<memberList.size(); j++){
                        System.out.print(memberList.get(j).getId()+"|");
                        System.out.print(memberList.get(j).getName()+"|");
                        System.out.println(memberList.get(j).getBirth()+"|");
                    }
                    System.out.print("처음으로 돌아가려면 아무키 입력: ");
                    i = sc.nextLine();
                    continue;

                case "2":
                    System.out.println("=========회원ID 조회=========");
                    System.out.println("조회 할 회원 ID: ");
                    String id = sc.nextLine();// scanner를 이용하여 조회할 ID입력
                    MyPageVO MVO = new MyPageVO();

                    try{
                        MVO = MPDI.viewMember(id);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    System.out.println("회원 ID: " + MVO.getId());
                    System.out.println("회원 이름: " + MVO.getName());
                    System.out.println("회원 생년월일: " + MVO.getBirth());
                    System.out.print("처음으로 돌아가려면 아무키 입력: ");
                    i = sc.nextLine();
                    continue;

                case "3":
                    System.out.println("=========얘매내역 조회=========");
                    System.out.println("조회 할 회원 ID: ");
                    String rid = sc.nextLine();
                    List<ReservationVO> reservationVOList = RC.findAll(rid);

                    for(int k=0; k< reservationVOList.size(); k++){
                        System.out.print("영화 이름: " + reservationVOList.get(k).getMovieName());
                        System.out.print("상영관: " + reservationVOList.get(k).getTheaterName());
                        System.out.println("상영회차: " + reservationVOList.get(k).getReserveTime());
                    }

                    System.out.print("처음으로 돌아가려면 아무키 입력: ");
                    i = sc.nextLine();
                    continue;

                case "4":
                    System.out.println("관리항목 선택으로 돌아갑니다.");
                    return;

                case "5":
                    System.out.println("=======프로그램을 종료합니다=======");
                    sc.close();
                    System.exit(0);     //  프로그램을 강제종료하는 구문
                    break;
            }// switch 종료
        }//while 종료
   }//memberView 메소드 종료
}
