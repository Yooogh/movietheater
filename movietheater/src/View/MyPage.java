package View;

import Model.MyPageDAOImpl;
import Model.MyPageVO;

import java.util.Scanner;

public class MyPage {

	public static void main(String[] args) {
		
		MyPageDAOImpl dao = new MyPageDAOImpl();
		MyPageVO mp = new MyPageVO();
		MyPageUI ui = new MyPageUI();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("골라골라");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		int menu = sc.nextInt();
		switch(menu) {
			case 1 :
				ui.signUpMember();
				break;
			case 2 :
				ui.loginMember();
				//여기서 들어가면 마이페이지랑 예매화면 선택창
				break;
		}

	}

}
