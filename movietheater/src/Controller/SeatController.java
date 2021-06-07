package Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Model.PlexDAO;
import Model.PlexVO;
import Model.ReservationDAO;
import Model.SeatDAO;
import Model.SeatVO;

public class SeatController {
	
	public void seatsStatus(int plexNO, LocalDate ReserveDay, String reserveTime) {
		PlexDAO pdao = new PlexDAO();
		SeatDAO sdao = new SeatDAO();
		PlexVO pvo = pdao.selectByNo(plexNO);
		int r = pvo.getRow();
		int c = pvo.getColumn();
		int total = r*c;
		int reserved;
		reserved = sdao.getReservedSeat(pvo.getName(), ReserveDay, reserveTime).size();
		System.out.print("(전체좌석: " + total+" / 예약가능 좌석: "+ (total-reserved) +")\n" );
	}
	
	public void PlexList() {
		ArrayList<PlexVO> plist = new ArrayList< PlexVO >();
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
	

	public void SeatLeftPrint(int plexNO, LocalDate ReserveDay, String reserveTime) {
		System.out.println("좌석 배치 출력");
		PlexDAO pdao = new PlexDAO();
		PlexVO pvo = pdao.selectByNo(plexNO);
		if(pvo == null) {
			System.out.println("존재하지 않는 상영관입니다.");
			return;
		}
		SeatDAO sdao = new SeatDAO();
		int r = pvo.getRow();
		int c = pvo.getColumn();
		ArrayList<SeatVO> rlist = new ArrayList< SeatVO >();
		rlist = sdao.getReservedSeat(pvo.getName(), ReserveDay, reserveTime);
		ArrayList <String> takednSeat = new ArrayList<String>();
		for(SeatVO vo: rlist) {
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
			if(j == 0) {
				System.out.print(i+1);
			}
			char cchar = (char)('a'+j);
			String seat = "" + cchar + (i+1);
			System.out.print(" ");
			if(containsCaseInsensitive(seat,takednSeat)) {
				System.out.print("X");
			}else 
				System.out.print("O");
			}
			System.out.println();
		}
		int total = r*c;
		int reserved = rlist.size();
		System.out.print("(전체좌석: " + total+" / 예약가능 좌석: "+ (total-reserved) +")\n" );

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
