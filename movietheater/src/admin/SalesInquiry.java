package admin;

import java.util.Scanner;

public class SalesInquiry {
    Scanner sc = new Scanner(System.in);
    public void salesInquiry() {

        while (true) {
            System.out.println("==========매출 조회==========");
            System.out.println("1. 월별 매출");
            System.out.println("2. 일별 매출");
            System.out.println("3. 영화별 매출");
            System.out.println("4. 초기화면으로 돌아가기");
            System.out.println("5. 프로그램 종료");
            System.out.print("조회 항목 선택: ");
            String i = sc.nextLine();

            switch (i){

                case "1":
                    System.out.println("==========월별 매출==========");
                    System.out.print("조회를 원하는 월: ");
                    i = sc.nextLine();//조회를 원하는 달을 입력
                    //DB에 저장된 매출 출력
                    continue;

                case "2":
                    System.out.println("==========일별 매출==========");
                    System.out.println("조회를 원하는 일: ");
                    i = sc.nextLine();//sc를 이용하여 조회를 원하는 날짜를 입력후
                    //해당 날짜의 매출 출력
                    continue;

                case "3":
                    System.out.println("=========영화별 매출=========");
                    System.out.println("조회를 원하는 영화:");
                    i = sc.nextLine();//sc를 이용하여 조회를 원하는 영화 이름 입력
                    //해당 영화의 매출 출력
                    continue;

                case "4":
                    System.out.println("관리항목 선택으로 돌아갑니다.");
                    return;

                case "5":
                    System.out.println("==프로그램을 종료합니다==");
                    sc.close();
                    System.exit(0); // 프로그램 강제종료 구문
                    break;
            }//switch 종료
        }//while 종료
    }//salesInquiry 종료
}
