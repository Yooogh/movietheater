package Model;

import java.util.Scanner;

public class MyPageUI {
	
	
	static Scanner sc = new Scanner(System.in);
	static MyPageVO mp = new MyPageVO();
	static MyPageDAOImpl dao = new MyPageDAOImpl();
	
//고객 메인
	public static void userMain() {
		System.out.println("메인화면뿅");
	}

	
//회원가입
	public static void signUpMember() {
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
		userMain();
	}
	



//내 정보 조회
	public static void viewMember() {
		
		String id = "a123123123a123123123";
		String name = "김나나";
		String birth = "950120";
		
//		String id = mp.getId();//로그인된 아이디에서 값받아오는법?
//		String name = mp.getName();
//		String birth = mp.getBirth();
		System.out.println("---------====마이 페이지====--------");
		System.out.println("\t▶ ID : " + id);
		System.out.println("\t▶ 이름 : " + name);
		System.out.println("\t▶ 생일 : " + birth);
		System.out.println("──────────────────────────────────");
		System.out.println("내 정보 수정(1) │ 회원 탈퇴(2) │ 메인(0)");
		System.out.println("----------------------------------");
		System.out.print("메뉴를 입력하세요 >");
		
		//메뉴 이동
		
		while(true) {
			String menu = sc.nextLine();
			menu:switch(menu) {
				case "1" :
					modifyMember();
					break;
			
				case "2" :
					deleteMember();
					break;
					
				case "0" :
					userMain();
					break;
					
				default :
					System.out.print("메뉴를 입력하세요 >");
					break menu;
			}
		}
	}
	
//내 정보 수정
	public static void modifyMember() {
		System.out.println("===내 정보 수정===");
		System.out.println("비밀번호 변경(1) │ 이름 변경(2) │ 생일 변경(3) │ 뒤로 가기(0)");
		System.out.print("메뉴를 입력하세요 >");

		while(true) {
		int menu = sc.nextInt();
		menu: switch(menu) {
			case 1 :
				System.out.println("비밀번호 변경");
//				updatePW();
				String setPW =sc.nextLine();
				mp.setPw(setPW);
				break;
			case 2 :
				System.out.println("이름 변경");
//				updateName();
				String setName = sc.nextLine();
				mp.setName(setName);
				break;
			case 3 : 
				System.out.println("생일 변경");
//				updateBirth();
				String setBirth = sc.nextLine();
				mp.setBirth(setBirth);
				break;
			case 0 :
				System.out.println("뒤로 가기");
				viewMember();
				break;
			default :
				System.out.println("메뉴를 입력하세요 >");
				break menu;
			}
		}
	}
	
//회원 탈퇴
	public static void deleteMember() {
		String pw = "a456456";
		
		System.out.println("====회원 탈퇴====");
		System.out.println("탈퇴하시려면 비밀번호를 입력하세요");
		System.out.println("내 정보 조회로 돌아가시려면 0을 입력하세요");
		
		
		do {
			System.out.print("▶ 비밀번호 입력 : ");
			String inputPW = sc.nextLine();
			
			String back = "0";//뒤로 가기
			if(inputPW.equals(back)) {
				System.out.println("────────────────────────────────");
				viewMember();
				break;
			}

//			if (!inputPW.equals(mp.getPw())) {
			if (!inputPW.equals(pw)) {
				System.out.println("비밀번호가 일치하지 않습니다");
				System.out.println("----------------------------------");
			} else {
				System.out.println("비밀번호가 일치합니다");
				dao.deleteMember(mp);
				System.out.println("탈퇴되었습니다");
				//DB에서 삭제
				//회원 메인
				System.out.println("────────────────────────────────");
				userMain();
				
				break;//반복문 탈출
			}
		} while(true);
		
	}
	
	
	
	//아이디 타당성 검사
	private static void checkID(String ID) {

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
	private static void checkPW(String PW) {
		
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
	private static void checkPW(String PW, String rePW) { 
		
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


	
	public static void main(String[] args) {
//		signUpMember();
		viewMember();
		
	}

}
