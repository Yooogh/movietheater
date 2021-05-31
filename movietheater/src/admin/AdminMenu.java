package admin;

import java.util.Scanner;

public class AdminMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MemberInquiry a  = new MemberInquiry();

        while (true) {
            System.out.println("=======관리 항목=======");
            System.out.println("1. 상영관 등록");
            System.out.println("2. 영화 등록");
            System.out.println("3. 회원정보 관리");
            System.out.println("4. 매출 조회");
            System.out.println("5. 프로그램 종료");
            System.out.print("실행 항목 선택: ");
            String i = sc.nextLine();   //  실행을 원하는 항목 번호 입력

            switch(i){      //  실행을 원하는 번호 입력시 해당 항목 실행

                case "1":
                    continue;

                case "2":
                    continue;

                case "3":
                    a.memberView();
                    continue;

                case "4":
                    continue;

                case "5":
                    System.out.println("==프로그램을 종료합니다==");
                    sc.close();
                    System.exit(0); //  프로그램 강제종료 구문
                    break;

            }// switch 종료
        }
    }
}
