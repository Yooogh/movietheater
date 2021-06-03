package View;

import Model.AdminVO;
import Model.AdminDAO;
import movieRegister.MovieExe;

import java.util.Scanner;

public class AdminMenu {
    public void AdminView() {
        Scanner sc = new Scanner(System.in);
        AdminInquiry MI = new AdminInquiry();
        SalesInquiry RI = new SalesInquiry();
        AdminVO avo = new AdminVO();
        AdminDAO adao = new AdminDAO();
        MovieExe ME = new MovieExe();

        while (true) {
            System.out.println("==========관리 항목==========");
            System.out.println("1. 상영관 등록");
            System.out.println("2. 영화 관리");
            System.out.println("3. 회원정보 관리");
            System.out.println("4. 매출 조회");
            System.out.println("5. 관리자 등록");
            System.out.println("6. 관리자 삭제");
            System.out.println("7. 프로그램 종료");
            System.out.print("실행 항목 선택: ");
            String i = sc.nextLine();

            switch(i){      //  실행을 원하는 번호 입력시 해당 항목 호출하여 실행

                case "1":
                    continue;

                case "2":
                    ME.Movieadd();      //  Movieadd 메소드 호출 실행
                    continue;

                case "3":
                    MI.memberView();    //  memberView 메소드 호출 실행
                    continue;

                case "4":
                    RI.salesInquiry();  //   salesInquiry 메소드 호출 실행
                    continue;

                case "5":
                    System.out.println("=========관리자 등록=========");
                    System.out.print("사용할 ID: " );
                    String ID = sc.nextLine();
                    avo.setAdminID(ID);//AdminVO의 adminID로 세팅
                    System.out.print("사용할 PW: " );
                    String PW = sc.nextLine();
                    avo.setAdminPW(PW);// AdminVO의 adminPW로 세팅
                    adao.saveID(avo);
                    System.out.print("처음으로 돌아가려면 아무키 입력: ");
                    i = sc.nextLine();
                    System.out.println("===관리 항목으로 돌아갑니다====");
                    continue;

                case "6":
                    System.out.println("=========관리자 삭제=========");
                    System.out.print("삭제할 ID: ");
                    String id = sc.next();
                    adao.deleteID(id);//String형식의 id를 입력받아 DB의 Adminaccount의 ID형식일 경우 삭제
                    System.out.println("==관리자 ID가 삭제 되었습니다==");
                    System.out.println("===관리 항목으로 돌아갑니다====");
                    i = sc.nextLine();
                    continue;

                case "7":
                    System.out.println("=====프로그램을 종료합니다=====");
                    sc.close();
                    System.exit(0); //  프로그램 강제종료 구문
                    break;

            }   // switch 종료
        }   //  while 종료
    }   // main 종료
}
