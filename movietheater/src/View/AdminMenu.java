package View;

import Model.AdminVO;
import Model.AdminDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    public void AdminView() {
        Scanner sc = new Scanner(System.in);
        AdminInquiry MI = new AdminInquiry();
        SalesInquiry RI = new SalesInquiry();
        AdminVO avo = new AdminVO();
        AdminDAO adao = new AdminDAO();
        MovieExe ME = new MovieExe();
        PlexMenu PM = new PlexMenu();
        int q = 1;
        int r = 1;

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
            String i = sc.next();


            switch(i){      //  실행을 원하는 번호 입력시 해당 항목 호출하여 실행

                case "1":
                    PM.PlexManager();   //  PlexManager 메소드 호출 실행
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
                    while(q==1) {
                        System.out.println("=========관리자 등록=========");
                        System.out.print("사용할 ID: ");
                        String ID = sc.next();
                        avo.setAdminID(ID);//AdminVO의 adminID로 세팅
                        System.out.print("사용할 PW: ");
                        String PW = sc.next();
                        avo.setAdminPW(PW);// AdminVO의 adminPW로 세팅
                        System.out.print("관리자 이름: ");
                        String NAME = sc.next();
                        avo.setAdminNAME(NAME);// AdminVO의 NAME으로 세팅
                        adao.saveID(avo);
                        System.out.println("계속해서 등록하시겠습니까?");
                        System.out.print("[그만:0 계속:1]: ");
                        q = sc.nextInt();
                        if (q == 0) {
                            break;
                        } else if (q == 1) {
                            continue;
                        } else {
                            System.out.println("다시 입력하세요.");
                        }
                    }
                    break;

                case "6":
                    while (r == 1) {
                        System.out.println("=========관리자 삭제=========");
                        System.out.println("====관리자 ID|관리자 이름=====");
                        List<AdminVO> adminList = adao.viewAdminAccountList();
                        for (int j = 0; j < adminList.size(); j++) {
                            System.out.print("관리자 ID: " + adminList.get(j).getAdminID() + "|");
                            System.out.println("관리자 이름: " + adminList.get(j).getAdminNAME());
                        }
                        System.out.print("삭제할 ID: ");
                        String id = sc.next();
                        adao.deleteID(id);//String형식의 id를 입력받아 DB의 Adminaccount의 ID형식일 경우 삭제
                        System.out.println("==관리자 ID가 삭제 되었습니다==");
                        System.out.println("계속해서 삭제하시겠습니까?");
                        System.out.print("[그만:0 계속:1]: ");
                        r = sc.nextInt();
                        if (r == 0) {
                            System.out.println("===관리 항목으로 돌아갑니다====");
                            break;
                        } else if (r == 1) {
                            continue;
                        } else {
                            System.out.println("다시 입력하세요.");
                        }
                    }
                    break;

                case "7":
                    System.out.println("=====프로그램을 종료합니다=====");
                    sc.close();
                    System.exit(0); //  프로그램 강제종료 구문
                    break;

            }   // switch 종료
        }   //  while 종료
    }   // main 종료
}
