package View;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import VO.MemberVO;
import VO.PlexVO;
import VO.ReservationVO;
import dao.MemberDAO;
import dao.PlexDAO;
import dao.ReservationDAO;

public class PlexMenu {
	//ArrayList<MemberVO> mlist = new ArrayList< MemberVO >();
	ArrayList<PlexVO> plist = new ArrayList< PlexVO >();
	//ArrayList<ReservationVO> rlist = new ArrayList< ReservationVO >();


	//Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		new PlexMenu();
	}

	// public PlexMenu(){
	// System.out.println("상영관 관리 페이지");
	// String menu = conInput("\n \t MENU \t 0.전체 영화 목록 1.영화 관리 2.종료 하기");
	// }

	public PlexMenu() {
		//TODO
		//상영관 수정(이름변경 제외)과 삭제시 상영관에서 상영중인 영화의 티켓들을 취소해야 됨
		//상영관의 시청여부는 티켓과 현재시간을 비교하여 판단
		do {
			System.out.println("상영관 관리 페이지");
			System.out.println("MENU \n0.상영관 조회\n1.상영관 관리\n2.종료하기");
			Scanner scan = new Scanner(System.in);
			String menu = scan.next();
			if (menu.equals("0")) {
				// 전체 상영관 목록
				PlexList();

			} else if (menu.equals("1")) {
				// 상영관 관리
				System.out.println("1.상영관 관리 \na:등록, b:수정, c:삭제, d: 상영관 좌석 출력");
				String submenu = scan.next();

				if (submenu.equals("a")) {
					//PlexList();
					if(PlexRegister() == 1) {
					System.out.println("새로운 상영관이 등록되었습니다.");
					PlexList();
					}
				} else if (submenu.equals("b")) {
					PlexList();
					PlexEdit();
					//System.out.println("정상적으로 수정되었습니다.");
					PlexList();
				} else if (submenu.equals("c")) {
					PlexRemove();
					System.out.println("정상적으로 삭제되었습니다.");
					PlexList();
				} else if (submenu.equals("d")) {
					PlexList();
					PlexPrintMenu();
					System.out.println("좌석을 출력했습니다.");
				} else {
					System.out.println("에러");
				}

			} else if (menu.equals("2")) {
				// 종료 하기
				System.out.println("프로그램이 종료되었습니다."); // 종료
				break;
			} else {
				System.out.println("에러");
			}

		} while (true);
	}


	public void PlexList() {
		System.out.println("상영관 리스트");
		PlexDAO pdao = new PlexDAO();
		plist = pdao.selectAll();
		for(PlexVO vo: plist) {
			System.out.print(vo.getPlexNo()+". ");
			System.out.print(vo.getName()+"관 ");
			System.out.print((char)(vo.getColumn()+'a'-1)+"열 ");
			System.out.print(vo.getRow()+"번 ");
			System.out.println();
		}
	}
	
	private void seatsStatus(int plexNO, LocalDate ReserveDay, String reserveTime) {
		PlexDAO pdao = new PlexDAO();
		PlexVO pvo = pdao.selectByNo(plexNO);
		int r = pvo.getRow();
		int c = pvo.getColumn();
		int total = r*c;
		int reserved;
		//ReservationDAO rdao = new ReservationDAO();
		//ReservationVO rvo = new ReservationVO();
		reserved = pdao.getReservedSeat(plexNO, ReserveDay, reserveTime).size();
		//int reserved = rvo.getALL.size();
		System.out.print("(전체좌석: " + total+" / 예약가능 좌석: "+ (total-reserved) +")\n" );
		
	}

	public void PlexPrintMenu(){
		System.out.println("상영관 번호를 입력해주세요:");
		Scanner scan = new Scanner(System.in);
		int plexNo = scan.nextInt();
		PlexDAO pdao = new PlexDAO();
		//PlexVO vo = pdao.selectByNo(plexNo);
		//if(vo != null) {
		if(pdao.IsExist(plexNo)){
			PlexPrint(plexNo);
			//printLeft 테스트용
			/*
			ArrayList <String> takednSeat = new ArrayList<String>();
			takednSeat.add("a1");
			takednSeat.add("b1");
			takednSeat.add("c2");
			takednSeat.add("d4");
			printLeft(plexNo,takednSeat);
			*/
		}else {
			System.out.println("상영관 번호를 잘못 입력하였습니다. 다시 입력하세요.");
		}
	}
	
	public void PlexPrint(int plexNO) {
		System.out.println("좌석 배치 출력");
		PlexDAO pdao = new PlexDAO();
		PlexVO pvo = pdao.selectByNo(plexNO);
		int r = pvo.getRow();
		int c = pvo.getColumn();
		for (int i = 0; i < c; i++) {
			char k = (char)('a'+i);
			System.out.print(" ");
			System.out.print(k);
		}
		System.out.println();
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if(j == 0) {
					System.out.print(i+1);
				}
				System.out.print(" ");
				System.out.print("O");
			}
			System.out.println();
		}
	}
	

/*	
	public void PlexPrint2(int plexNO, LocalDate ReserveDay, String reserveTime) {
		System.out.println("좌석 배치 출력");
		PlexDAO pdao = new PlexDAO();
		PlexVO pvo = pdao.selectByNo(plexNO);
		int r = pvo.getRow();
		int c = pvo.getColumn();
		ReservationDAO rdao = new ReservationDAO();
		ArrayList<ReservationVO> rlist = new ArrayList< ReservationVO >();
		rlist = pdao.getReservedSeat(plexNO, ReserveDay, reserveTime);
		ArrayList <String> takednSeat = new ArrayList<String>();
		//ArrayList<ReservationVO> rlist = new ArrayList< ReservationVO >();
		for(ReservationVO vo: rlist) {
			takednSeat.add(vo.getSeat());
		}
		for (int i = 0; i < c; i++) {
			char k = (char)('a'+i);
			System.out.print(" ");
			System.out.print(k);
		}
		System.out.println();
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
			char cchar = (char)('a'+j);
			String seat = "" + cchar + (i+1);
			System.out.print(" ");
			if(takednSeat.contains(seat)) {
				System.out.print("X");
			}else 
				System.out.print("O");
			}
			
		}
	}
*/	
	public void printLeft(int plexNO, ArrayList <String> takednSeat) {
		System.out.println("좌석 배치 출력");
		PlexDAO pdao = new PlexDAO();
		PlexVO pvo = pdao.selectByNo(plexNO);
		int r = pvo.getRow();
		int c = pvo.getColumn();
		ReservationDAO rdao = new ReservationDAO();
		for (int i = 0; i < c; i++) {
			char k = (char)('a'+i);
			System.out.print(" ");
			System.out.print(k);
		}
		System.out.println();
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
			char cchar = (char)('a'+j);
			String seat = "" + cchar + (i+1);
			System.out.print(" ");
			String a = new String();
			//if(takednSeat.contains(seat)) {
			if(containsCaseInsensitive(seat,takednSeat)) {
				System.out.print("X");
			}else 
				System.out.print("O");
			}
			System.out.println();
			//System.out.println(seat);
		//}
		}
	}
	
	public int PlexRegister() {
		//들어올 수 있는 값인지 확인해줘야 함
		PlexVO vo = new PlexVO();
		String name;
		int rows;
		int column;
		int plexNO;
		try {
		PlexDAO pdao = new PlexDAO();
		Scanner scan = new Scanner(System.in);
		System.out.println("상영관 등록 페이지");
		System.out.println("상영관 번호: ");
		plexNO = scan.nextInt();
		if(pdao.IsExist(plexNO)) {
			System.out.println("이미 존재하는 상영관 번호입니다.");
			return -1;
		}
		System.out.println("상영관 이름: ");
		name = scan.next();
		if(name.length() > 12) {
			Exception e = new Exception();
			throw e;
		}
		vo.setName(name);
		System.out.println("좌석 열의 개수: ");
		column = scan.nextInt();
		if(name.length() > 20) {
			Exception e = new Exception();
			throw e;
		}
		System.out.println("좌석 행의 개수: ");
		rows = scan.nextInt();
		if(name.length() > 20) {
			Exception e = new Exception();
			throw e;
		}
		vo.setColumn(column);
		vo.setRow(rows);
		//PlexDAO pdao = new PlexDAO();
		pdao.regPlex(vo);
		System.out.println();
		return 1;
		}catch(Exception e) {
			System.out.println("잘못된 값을 입력했습니다. 처음부터 다시 시도해주세요");
			return -1;
		}
	}
	
	private void PlexEdit() {
		System.out.println("상영관 수정");
		System.out.println("수정할 상영관 번호를 입력해주세요.");
		Scanner scan = new Scanner(System.in);
		int plexNo = scan.nextInt();
		PlexDAO pdao = new PlexDAO();
		PlexVO vo = pdao.selectByNo(plexNo);
		if(vo != null) {
		//if(pdao.IsExist(plexNo)){
			//PlexVO vo = pdao.selectByNo(plexNo);
			System.out.println("수정 항목 선택 [1:상영관 이름 2:열의 개수 3.행의 개수]");
			String part = scan.next();
			if(part.contentEquals("1")) {  //상영관 이름 수정
				System.out.println("상영관 이름: ");
				String name = scan.next();
				vo.setName(name);
				pdao.update(vo);
				System.out.println("상영관 이름이 수정되었습니다.");
			}else if(part.equals("2")){  //상영관 열의 개수 수정
				System.out.println("좌석 열의 개수: ");
				int column = scan.nextInt();
				vo.setColumn(column);
				pdao.update(vo);
				System.out.println("상영관 열의 개수가 수정되었습니다.");
			}else if(part.equals("3")){  //상영관 열의 개수 수정
				System.out.println("좌석 행의 개수: ");
				int rows = scan.nextInt();
				vo.setRow(rows);
				pdao.update(vo);
				System.out.println("상영관 행의 개수가 수정되었습니다.");
			}else {
				System.out.println("잘못된 번호를 입력했습니다. 다시 시도해주세요");
			}
			
		}else {
			System.out.println("상영관 번호를 잘못 입력하였습니다. 다시 입력하세요.");
		}

	}
	

	private void PlexRemove() {
		Scanner scan = new Scanner(System.in);
		System.out.println("상영관 삭제");
		System.out.println("삭제할 상영관 번호를 입력하세요");
		int plexNo = scan.nextInt();
		PlexDAO pdao = new PlexDAO();
		pdao.delete(plexNo);

	}
	
	public boolean containsCaseInsensitive(String s, List<String> l){
	     for (String string : l){
	        if (string.equalsIgnoreCase(s)){
	            return true;
	         }
	     }
	    return false;
	  }
}