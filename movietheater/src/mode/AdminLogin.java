package mode;

import admin.AdminMenu;
import dao.AdminDAO;

import java.util.Scanner;

public class AdminLogin {
    public void adminLogin(){
        AdminMenu AM = new AdminMenu();
        Scanner sc = new Scanner(System.in);
        AdminDAO AD = new AdminDAO();
        boolean bl = true;

        while (bl){ //  while이 true일 경우 로그인 반복
            System.out.println("=========관리자 로그인=========");
            System.out.print("관리자 ID: ");
            String AID = sc.nextLine();//SC를 이용하여 ID입력
            System.out.print("관리자 PW: ");
            String APW = sc.nextLine();//SC를 이용하여 PW입력
            //입력받은 ID를 DB내 관리자 ID와 동일한지 검사
            //입력받은 PW를 DB내 관리자 PW와 동일한지 검사
            if (AD.LoginID(AID, APW)) { //ID + PW의 일치값을 ture/ false
                bl = false; //  ID+PW가 일치할 경우 false로 전환
                AM.AdminView();//ID와 PW가 일치하면 관리자의 관리 항목 선택을 호출하고 실행
                break;
            } else {
                System.out.println("ID또는 PW가 일치하지 않습니다");
                //ID 또는 PW가 다를시 print문을 이용하여 일지하지 않음을 출력
                //일치하지 않는 경우 관리자 로그인 화면으로 보냄
            }
        }
    }
}
