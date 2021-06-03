package movieRegister;

import java.util.Scanner;

public class MovieExe {

	public static void main(String[] args) {
		MovieDAOIplm MDI = new MovieDAOIplm();
		Scanner sc = new Scanner(System.in);
		boolean bool = true;

		while(bool) {

			System.out.println("1.영화 등록");
			System.out.println("2.영화 삭제");
			System.out.println("3.영화 조회");
			System.out.println("4.종료");
			System.out.print("메뉴 선택 : ");

			int menuNo = sc.nextInt();

			switch(menuNo) {

			case 1 : 
				MovieVO movieVo = new MovieVO();
				System.out.print("제목 입력 : ");
				String title = sc.next();
				System.out.println("제목이 등록되었습니다.\n");

				System.out.print("감독 입력 : ");
				String director = sc.next();
				System.out.println("감독이 등록되었습니다.\n");

				System.out.print("장르 입력 : ");
				String genre = sc.next();
				System.out.println("장르가 등록되었습니다.\n");

				System.out.print("등급 입력(전체, 12, 15, 청불) : ");
				String rate = sc.next();
				System.out.println("등급이 등록되었습니다.\n");
				movieVo.setTitle(title);
				movieVo.setDirector(director);
				movieVo.setGenre(genre);
				movieVo.setRate(rate);
				MDI.addMovie(movieVo);
				System.out.println("영화 정보가 모두 저장되었습니다.\n");

				System.out.println("계속해서 등록하시겠습니까?(그만:0 계속:1)");

				if (sc.nextInt()==0) 
					break;
				else if(sc.nextInt()==1)
					continue;
				else {
					System.out.println("다시 입력하세요.");

				}


			case 2 :	
				System.out.println("삭제할 영화의 이름 : ");
				String mtitle = sc.next();
				MDI.delMovie(mtitle);
				System.out.println("영화가 삭제 되었습니다.");

				break;

			case 3 : 
				System.out.print("조회할 영화의 제목을 입력 : ");

				String mvtitle = sc.next();
				MovieVO mvvo = new MovieVO();
				try {
					mvvo = MDI.searchMovie(mvtitle);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(mvvo.getTitle());
				System.out.println(mvvo.getDirector());
				System.out.println(mvvo.getGenre());
				System.out.println(mvvo.getRate());

				String mvTitle = sc.next();
				MovieVO movieVO = new MovieVO();
				try {
					movieVO = MDI.searchMovie(mvTitle);
				} catch (Exception e) {
					e.printStackTrace();
				}

				System.out.println("영화 제목: " + movieVO.getTitle());
				System.out.println("영화 감독: " + movieVO.getDirector());
				System.out.println("영화 장르: " + movieVO.getGenre());
				System.out.println("영화 등급: " + movieVO.getRate());


				break;


			case 4 :
				break;

			}
			break;
		}

	}

}

