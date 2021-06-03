package movieRegister;

import java.util.Scanner;

public class MovieExe {

	public void Movieadd() {
		MovieDAOIplm MDI = new MovieDAOIplm();
		Scanner sc = new Scanner(System.in);
		boolean bool = true;

		while(bool) {
			System.out.println("==========영화 관리==========");
			System.out.println("1.영화 등록");
			System.out.println("2.영화 삭제");
			System.out.println("3.영화 조회");
			System.out.println("4. 프로그램 종료");
			System.out.print("항목 선택 : ");

			int menuNo = sc.nextInt();

			switch(menuNo) {

			case 1 : 
				MovieVO movieVo = new MovieVO();
				System.out.println("==========영화 등록==========");
				System.out.print("제목 입력: ");
				String title = sc.next();
				System.out.println("제목이 등록되었습니다.");

				System.out.print("감독 입력: ");
				String director = sc.next();
				System.out.println("감독이 등록되었습니다.");

				System.out.print("장르 입력: ");
				String genre = sc.next();
				System.out.println("장르가 등록되었습니다.");

				System.out.print("등급 입력(전체, 12, 15, 청불) : ");
				String rate = sc.next();
				System.out.println("등급이 등록되었습니다.");
				movieVo.setTitle(title);
				movieVo.setDirector(director);
				movieVo.setGenre(genre);
				movieVo.setRate(rate);
				MDI.addMovie(movieVo);
				System.out.println("영화 정보가 모두 저장되었습니다.");

				System.out.println("계속해서 등록하시겠습니까?");
				System.out.print("[그만:0 계속:1]: ");

				if (sc.nextInt()==0) {
					break;
				}else if(sc.nextInt()==1) {
					continue;
				}else {
					System.out.println("다시 입력하세요.");
				}


			case 2 :
				System.out.println("==========영화 삭제==========");
				System.out.println("삭제할 영화의 이름 : ");
				String mtitle = sc.next();
				MDI.delMovie(mtitle);
				System.out.println("영화가 삭제 되었습니다.");
				System.out.println("계속해서 삭제하시겠습니까?");
				System.out.print("[그만:0 계속:1]: ");

				if (sc.nextInt()==0) {
					break;
				}else if(sc.nextInt()==1) {
					continue;
				}else {
					System.out.println("다시 입력하세요.");
				}


			case 3 :
				System.out.println("==========영화 조회==========");
				System.out.print("조회할 영화의 제목을 입력 : ");

				String mvtitle = sc.next();
				MovieVO mvvo = new MovieVO();
				try {
					mvvo = MDI.searchMovie(mvtitle);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("영화 이름: " + mvvo.getTitle());
				System.out.println("영화 감독: " + mvvo.getDirector());
				System.out.println("영화 장르: " + mvvo.getGenre());
				System.out.println("영화 등급: " + mvvo.getRate());
				System.out.println("계속해서 삭제하시겠습니까?");
				System.out.print("[그만:0 계속:1]: ");

				if (sc.nextInt()==0) {
					break;
				}else if(sc.nextInt()==1) {
					continue;
				}else {
					System.out.println("다시 입력하세요.");
				}


			case 4 :
				System.out.println("=====프로그램을 종료합니다=====");
				sc.close();
				System.exit(0); //  프로그램 강제종료 구문
				break;

			}
			break;
		}

	}

}

