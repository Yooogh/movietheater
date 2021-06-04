package Model;

import java.util.Scanner;

import View.ReservationView;

//import View.*;


public class MyPageUI {
	private Scanner sc = new Scanner(System.in);
	private MyPageDAOImpl dao = new MyPageDAOImpl();
	ReservationView rv = new ReservationView();
	
//고객 메인
	public void userMain(MyPageVO mp) {

		System.out.println("--------=====고객화면=====--------");
		System.out.println("\t1. 마이 페이지");
		System.out.println("\t2. 영화 예매");
		System.out.println("--------------------------------");
		System.out.print("메뉴를 입력하세요 > ");
		
		
		int menu = sc.nextInt();
		switch(menu) {
			case 1 :
				System.out.println("────────────────────────────────\n");
				viewMember(mp);
				break;
			case 2 :
				System.out.println("────────────────────────────────\n");
				rv.mainMenu(mp);
				break;
		}
	}

	
//회원가입
	public void signUpMember() {
		MyPageVO mp = new MyPageVO();
		System.out.println("--------=====회원가입=====--------");
		
		System.out.print("\t▶ 아이디 : ");
			String ID = sc.nextLine();
			checkID(ID);
			mp.setId(ID);
			
		System.out.print("\t▶ 비밀번호 : ");
			String PW = sc.nextLine();
			checkPW(PW);
		System.out.print("\t▶ 비밀번호 확인 : ");
			String rePW = sc.nextLine();
			checkPW(PW, rePW);
			mp.setPw(PW);
			
		System.out.print("\t▶ 이름 : ");
			String name = "";
			do {
				
				name = sc.nextLine();
				
				if(6 < name.length()) {
					System.out.println("6자 이내의 이름만 가능합니다.");
					System.out.print("\t▶ 이름 : ");
				} else {
					break;
				}
				
			} while(true);
			mp.setName(name);

		System.out.print("\t▶ 생일 : ");
			String birth = "";
			do {
				
				birth = sc.nextLine();
				
				if(birth.length() != 6) {
					System.out.println("YYMMDD 형식으로 입력하십시오");
					System.out.print("\t▶ 생일 : ");
				} else {
					break;
				}
				
			} while(true);
			mp.setBirth(birth);

			dao.signUpMember(mp);
		System.out.println("──────────────────────────────");
		System.out.println("회원가입 되었습니다.");
		
		//회원 메인으로
		userMain(mp);
	}
	
	
	public MyPageVO loginMember() {
		MyPageVO mp = new MyPageVO();
		
		System.out.println("-----------====로그인====----------");
		System.out.print("\t▶ ID : ");
		String id = sc.nextLine();
		System.out.print("\t▶ PW : ");
		String pw = sc.nextLine();
		
		mp = dao.loginMember(id, pw);
		
		
		if(mp != null) {
			userMain(mp);
		} else {
			System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
		}
		
		return mp;
	}

//내 정보 조회
	public MyPageVO viewMember(MyPageVO mp) {
		
		String ID = mp.getId();
		String name = mp.getName();
		String birth = mp.getBirth();
		
		
		System.out.println("---------====마이 페이지====--------");
		System.out.println("\t▶ ID : " + ID);
		System.out.println("\t▶ 이름 : " + name);
		System.out.println("\t▶ 생일 : " + birth);
		System.out.println("──────────────────────────────────");
		System.out.println("내 정보 수정(1) │ 회원 탈퇴(2) │ 메인(0)");
		System.out.println("----------------------------------");
		System.out.print("메뉴를 입력하세요 >");
		
		//메뉴 이동
		
		while(true) {
			String menu = sc.nextLine();
			System.out.println();
			menu:switch(menu) {
				case "1" :
					modifyMember(mp);
					break;
			
				case "2" :
					deleteMember(mp);
					break;
					
				case "0" :
					userMain(mp);
					break;
					
				default :
					break menu;
			}
		}
	}
	
//내 정보 수정	
	public void modifyMember(MyPageVO mp) {

		System.out.println("--------====내 정보 수정====--------");
		System.out.println("수정할 정보를 입력하세요");
		
		System.out.print("\t▶ PW : ");
		String PW = sc.nextLine();
		mp.setPw(PW);
		
		
		System.out.print("\t▶ 이름 : ");
		String name = sc.nextLine();
		mp.setName(name);
		
		System.out.print("\t▶ 생일 : ");
		String birth = sc.nextLine();
		mp.setBirth(birth);
		
		dao.modifyMember(mp);
		
		viewMember(mp);//정보 조회 화면으로 돌아감
		
	}

	
//회원 탈퇴
	public void deleteMember(MyPageVO mp) {
		String id = mp.getId();
		String pw = mp.getPw();
		
		System.out.println("----------====회원탈퇴====----------");
		System.out.println("탈퇴하시려면 비밀번호를 입력하세요");
		System.out.println("내 정보 조회로 돌아가시려면 0을 입력하세요");
		
		do {
			System.out.print("▶ 비밀번호 입력 : ");
			String inputPW = sc.nextLine();
			
			String back = "0";//뒤로 가기
			if(inputPW.equals(back)) {
				System.out.println("────────────────────────────────");
				System.out.println();
				viewMember(mp);
				break;
			}

			if (!inputPW.equals(pw)) {
				System.out.println("비밀번호가 일치하지 않습니다");
				System.out.println("---------------------------------");
			} else {
				
				dao.deleteMember(mp); //DB에서 삭제
				System.out.println("비밀번호가 일치합니다");
				System.out.println("탈퇴되었습니다");
				System.out.println("────────────────────────────────");
				userMain(mp); //회원 메인
				
				break;//반복문 탈출
			}
		} while(true);
		
	}
	
	
	
	//아이디 타당성 검사
	private void checkID(String ID) {

		do{
			//1. 글자수 체크
			if(20 < ID.length()) {
				System.out.println("20자 이내의 아이디만 가능합니다.");
				System.out.print("\t▶ 아이디 : ");
				ID = sc.nextLine();
			} else {
				break;
			}
			
			int chkID = dao.redupleID(ID);//아이디중복검사 중복=1, 중복아니면=0
			//2. 중복 체크
			if(chkID > 0) {
				System.out.println("이미 있는 아이디입니다");
				System.out.print("\t▶ 아이디 : ");
				ID = sc.nextLine();
			} else {
				System.out.println("사용 가능한 아이디입니다.");
				break;
			}
			
		} while(true);
	}
	
	//비밀번호 길이 검사
	private void checkPW(String PW) {
		
		do {
			
			if(20 < PW.length()) {
				System.out.println("20자 이내의 비밀번호만 가능합니다.");
				System.out.print("\t▶ 비밀번호 : ");
				PW = sc.nextLine();
			} else {
				break;
			}
			
		} while(true);
	}
	
	//비밀번호 일치 여부 검사
	private void checkPW(String PW, String rePW) {
		
		do {
			
			if( !rePW.equals(PW)) {
				System.out.println("비밀번호가 일치하지 않습니다.");
				System.out.println("\t▶ 비밀번호 확인 : ");
				rePW = sc.nextLine();
			} else {
				break;
			}
			
		} while(true);
	}


}
