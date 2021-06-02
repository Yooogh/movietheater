<<<<<<< HEAD
package Model;

public interface MyPageDAO {
	//회원가입
	public void signUpMember(MyPageVO mb);
	//내 정보 조회
	public void viewMember(MyPageVO mb);
	//내 정보 수정
	public void modifyMember(MyPageVO mb);
	//회원 탈퇴
	public void deleteMember(MyPageVO mb);

=======


import java.util.Scanner;

public class MyPageDAO {
//crud create read update delete

	//변수 선언
	static String ID;
	static String PW;
	static String name;
	static String birth;
	
	String title;
	String date;
	String plex;
	String seat;

	static Scanner sc = new Scanner(System.in);
	
	//메서드 정리
	//회원 정보 조회
	public static void viewMember() {
		System.out.println("===마이페이지===");
		System.out.println("ID : " + ID);
		System.out.println("PW : 비밀번호 변경을 원한다면 C를 입력하세요");
		System.out.println("이름 : " + name);
		System.out.println("생일 : " + birth);
		System.out.println("-----------------");
		System.out.println("내 정보를 수정하시려면 1번, 예매 내역을 확인하시려면 2번을 입력하세요");
		System.out.println("-----------------\n");
		sc.nextLine();
		//switch
	}
	
	//회원 정보 수정  --> viewMember()에서 1 입력 시
	public static void modifyMember() {
		System.out.println("===회원 정보 수정===");
		//비밀번호, 이름, 생일(?)
		System.out.println("비밀번호를 ");
	}
	
	//회원 정보 삭제(탈퇴)
	public static void delMember() {
		System.out.println("탈퇴하시겠습니까?");
		System.out.println("탈퇴하시려면 비밀번호를 입력하세요");
		sc.nextLine();
		//스캐너 입력하라고 하고-> 입력하고-> 같은지 비교
		
		System.out.println();
	}
	//예매 내역 조회 --> viewMember()에서 2 입력 시
	public void viewReserve() {
		System.out.println("=== 예매 내역 조회 ===");
	}
	
	//예매 취소
	public void delReserve() {
		System.out.println("=== 예매 취소 ===");
	}
		




	public static void main(String[] args) {
		viewMember();
		modifyMember();
		delMember();
		
	}
	
>>>>>>> c3f3120a61c3db5ec7d77e21cb85f8d4ce2b8ca1
}
