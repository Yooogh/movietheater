package View;

import java.time.LocalDate;
import java.util.ArrayList;
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


	Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		new PlexMenu();
	}

	// public PlexMenu(){
	// System.out.println("상영관 관리 페이지");
	// String menu = conInput("\n \t MENU \t 0.전체 영화 목록 1.영화 관리 2.종료 하기");
	// }

	public PlexMenu() {
		//상영관 수정(이름변경 제외)과 삭제시 상영관에서 상영중인 영화의 티켓들을 취소해야 됨
		//상영관의 시청여부는 티켓과 현재시간을 비교하여 판단
		do {
			System.out.println("상영관 관리 페이지");
			String menu = conInput("\n \t MENU \t 0.상영관 조회 1.상영관 관리 2.종료하기");
			if (menu.equals("0")) {
				// 전체 상영관 목록
				PlexList();

			} else if (menu.equals("1")) {
				// 상영관 관리
				String submenu1 = conInput("1.상영관 관리 a:등록, b:수정, c:삭제, d: 상영관 좌석 출력");

				if (submenu1.equals("a")) {
					//PlexList();
					PlexRegister();
					System.out.println("새로운 상영관이 등록되었습니다.");
					PlexList();
				} else if (submenu1.equals("b")) {
					PlexList();
					PlexEdit();
					System.out.println("정상적으로 수정되었습니다.");
					PlexList();
				} else if (submenu1.equals("c")) {
					PlexRemove();
					System.out.println("정상적으로 삭제되었습니다.");
					PlexList();
				} else if (submenu1.equals("d")) {
					PlexList();
					PlexPrintMenu();
					System.out.println("좌석을 출력했습니다.");
				} else {

				}

			} else if (menu.equals("2")) {
				// 종료 하기
				System.out.println("프로그램이 종료되었습니다."); // 종료
				break;
			} else {

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
		int plexNo = scan.nextInt();
		PlexDAO pdao = new PlexDAO();
		PlexVO vo = pdao.selectByNo(plexNo);
		if(vo != null) {
			PlexPrint(plexNo);
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
			char cchar = (char)('a'+c);
			String seat = "" + cchar + r;
			System.out.print(" ");
			if(takednSeat.contains(seat)) {
				System.out.print("X");
			}else 
				System.out.print("O");
			}
			
		}
	}
	
	public void PlexRegister() {
		//들어올 수 있는 값인지 확인해줘야 함
		PlexVO vo = new PlexVO();
		String name;
		int rows;
		int column;
		System.out.println("상영관 등록 페이지");
		System.out.println("상영관 이름: ");
		name = scan.nextLine();
		vo.setName(name);
		System.out.println("좌석 열의 개수: ");
		column = scan.nextInt();
		System.out.println("좌석 행의 개수: ");
		rows = scan.nextInt();
		vo.setColumn(column);
		vo.setRow(rows);
		PlexDAO pdao = new PlexDAO();
		pdao.regPlex(vo);
		System.out.println();
	}
	
	private void PlexEdit() {
		System.out.println("상영관 수정");
		System.out.println("수정할 상영관 번호를 입력해주세요.");
		int plexNo = scan.nextInt();
		PlexDAO pdao = new PlexDAO();
		PlexVO vo = pdao.selectByNo(plexNo);
		if(vo != null) {
			String part = conInput("수정 항목 선택 [1:상영관 이름 2:열의 개수 3.행의 개수]");
			
			if(part.contentEquals("1")) {  //상영관 이름 수정
				System.out.println("상영관 이름: ");
				String name = scan.nextLine();
				vo.setName(name);
				pdao.update(vo);
			}else if(part.equals("2")){  //상영관 열의 개수 수정
				System.out.println("좌석 열의 개수: ");
				int column = scan.nextInt();
				vo.setColumn(column);
				pdao.update(vo);
			}else if(part.equals("3")){  //상영관 열의 개수 수정
				System.out.println("좌석 행의 개수: ");
				int rows = scan.nextInt();
				vo.setRow(rows);
				pdao.update(vo);
			}else {
				
			}
			
		}else {
			System.out.println("상영관 번호를 잘못 입력하였습니다. 다시 입력하세요.");
		}

	}
	

	private void PlexRemove() {
		System.out.println("상영관 삭제");
		System.out.println("삭제할 상영관 번호를 입력하세요");
		int plexNo = scan.nextInt();
		PlexDAO pdao = new PlexDAO();
		pdao.delete(plexNo);

	}

	public String conInput(String msg) {
		System.out.print(msg + " :");
		return scan.nextLine();
	}

}