package View;


import java.util.Scanner;

public class AdminInquiry {

    public void memberView() {      //  호출되는 메소드
    Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("=========회원 정보 관리=========");
            System.out.println("1. 전체 회원 조회");
            System.out.println("2. 회원 등급 조회");
            System.out.println("3. 회원 ID 조회");
            System.out.println("4. 회원 등급 설정");
            System.out.println("5. 얘매 내역 조회");
            System.out.println("6. 초기화면으로 돌아가기");
            System.out.println("7. 프로그램 종료");
            System.out.print("조회 항목 선택: ");
            String i = sc.nextLine();   //  조회를 원하는 항목 번호 입력

            switch(i) {     //  조회를 원하는 번호를 입력시 해당 항목 출력

                case "1":
                    System.out.println("=======전체 회원 조회=======");
                    System.out.println("ID|이름|생년월일|회원등급");
                    //DB에 저장된 회원정보 출력
                    System.out.print("처음으로 돌아가려면 0을 입력: ");
                    i = sc.nextLine();
                    continue;

                case "2":
                    System.out.println("=======회원 등급 조회=======");
                    System.out.println("조회를 원하는 회원 ID: ");
//                    ID = sc.nextLine(); scanner를 이용하여 조회할 ID입력
                    System.out.println("회원 이름: ");
                    System.out.println("회원 등급: ");
                    System.out.print("처음으로 돌아가려면 0을 입력: ");
                    i = sc.nextLine();
                    continue;

                case "3":
                    System.out.println("=======회원 ID 조회=======");
                    System.out.println("조회 하고자 하는 회원 ID: ");
                    //i = sc.nextLine();// scanner를 이용하여 조회할 ID입력
                    System.out.println("회원 이름: ");
                    System.out.println("회원 생년월일: ");
                    System.out.print("처음으로 돌아가려면 0을 입력: ");
                    i = sc.nextLine();
                    continue;

                case "4":
                    System.out.println("=======회원 등급 설정=======");
                    System.out.println("조회 하고자 하는 회원 ID: ");
                    //sc를 이용해서 ID를 입력
                    System.out.println("회원 등급: ");//DB에 저장된 회원의 등급 출력 silver, gold, vip
                    System.out.println("수정할 회원 등급: ");
                    //String G = sc.nextLine();//수정할 등급 입력
                    System.out.print("처음으로 돌아가려면 0을 입력: ");
                    i = sc.nextLine();
                    continue;

                case "5":
                    System.out.println("=======얘매 내역 조회=======");
                    System.out.println("조회 하고자 하는 회원 ID: ");
                    String uid = sc.nextLine();
                    //sc를 이용해서 ID를 입력
                    System.out.println("영화 이름: ");
                    System.out.println("상영관: ");
                    System.out.println("상영시간: ");
                    System.out.print("처음으로 돌아가려면 0을 입력: ");
                    i = sc.nextLine();
                    continue;

                case "6":
                    System.out.println("관리항목 선택으로 돌아갑니다.");
                    return;

                case "7":
                    System.out.println("==프로그램을 종료합니다==");
                    sc.close();
                    System.exit(0);     //  프로그램을 강제종료하는 구문
                    break;
            }// switch 종료
        }//while 종료
   }//memberView 메소드 종료
}
