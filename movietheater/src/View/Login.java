package View;

import java.util.Scanner;

import javax.swing.JOptionPane;

import Model.MemberVO;
import dao.MemberDAO;


public class Login {
	Scanner scan = new Scanner(System.in);
	public static String currentUserId = "";
	public static String Currentname;

	public Login() {
		while(currentUserId == "") {
		String id = conInput("\n아이디");
		String pwd = conInput("비밀번호");
		MemberDAO mdao = new MemberDAO();
		String result = mdao.login( id, pwd); //String대신 login type으로 바꿀것
		if( result == null ) {
			if (id.equals("register")) {
				System.out.println("회원가입을 진행합니다.");
				new RegisterView();
			}else
				System.out.println("로그인 실패");
		}else {
			currentUserId = id;
			System.out.println("로그인 성공");
			MemberVO mvo = new MemberVO();
			mvo = mdao.selectById(currentUserId);
			Currentname = mvo.getName();
			if(mvo.getAdmin() == 1) {
				System.out.println("관리자 화면으로 로그인 되셨습니다.");
				new MainMenu();
			}else {
				System.out.println(Currentname+"님 안녕하세요");
				new MainMenu();
			}
		}
		}
	}
	public String conInput(String msg) {
		System.out.print(msg +" :");
		return scan.nextLine();
	}
}

