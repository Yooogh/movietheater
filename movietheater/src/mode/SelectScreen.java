package mode;

import admin.AdminMenu;

import java.util.Scanner;

public class SelectScreen {
    public static void main(String[] args) {
        AdminMenu AM = new AdminMenu();
        Scanner sc = new Scanner(System.in);
        AdminLogin AL = new AdminLogin();

        while(true){
                System.out.println("==========접근 선택==========");
                System.out.println("1. 사용자");
                System.out.println("2. 관리자");
                System.out.println("3. 프로그램 종료");
                System.out.print("항목 선택: ");
                String i = sc.nextLine();

            switch(i){
                case "1":
                    continue;

                case "2":
                    AL.adminLogin();
//                    AM.AdminView();
                    continue;

                case "3":
                    System.out.println("=====프로그램을 종료합니다=====");
                    sc.close();
                    System.exit(0);
                    break;
            }
        }
    }
}
