package mode;

import admin.AdminMenu;

import java.util.Scanner;

public class AdminLogin {
    public void adminLogin(){
        AdminMenu AM = new AdminMenu();
        Scanner sc = new Scanner(System.in);

        System.out.println("=========관리자 로그인=========");
        System.out.println("관리자 ID: ");
        //SC를 이용하여 ID입력
        System.out.println("관리자 PW: ");
        //SC를 이용하여 PW입력
        //입력받은 ID를 DB내 관리자 ID와 동일한지 검사
        //입력받은 PW를 DB내 관리자 PW와 동일한지 검사
        //ID 또는 PW가 다를시 print문을 이용하여 일지하지 않음을 출력

        //ID와 PW가 일치하면 관리자의 관리 항목 선택을 호출하고 실행
    }
}
