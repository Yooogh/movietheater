package View;

import Model.MovieDAOIplm;
import Model.MovieVO;

import java.util.ArrayList;
import java.util.Scanner;

public class MovieExe {

	public void movieExe() {
		MovieDAOIplm MDI = new MovieDAOIplm();
		Scanner sc = new Scanner(System.in);
		boolean bool = true;
		MovieVO movieVo = new MovieVO();
		ArrayList<MovieVO> movieVoList = new ArrayList<MovieVO>();

		while(bool) {
			System.out.println("==========영화 관리==========");
			System.out.println("1.영화 등록");
			System.out.println("2.영화 삭제");
			System.out.println("3.영화 조회");
			System.out.println("4.영화 리스트 열람");
			System.out.println("5.초기화면으로 돌아가기");
			System.out.println("6.프로그램 종료");
			System.out.print("메뉴 선택 : ");

			int menuNo = sc.nextInt();
			System.out.println("");

			switch(menuNo) {

			case 1 : 

				boolean b1 = true;
				while(b1) {
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
					int j = sc.nextInt();
					if (j==0) {
						break;
					}else if(j==1) {
						continue;
					}else {
						System.out.println("잘못 입력하여 관리자 메뉴로 돌아갑니다.");
						System.out.println("");
					}
				}
				break;

			case 2 :

				boolean b2 = true;
				while(b2) {
					System.out.println("========현재 저장된 영화 목록========");
					try {
						movieVoList = MDI.listMovie();

						for(int i=0; i<movieVoList.size(); i++) {
							System.out.println("제목 : " + movieVoList.get(i).getTitle() + " / 감독 : " + movieVoList.get(i).getDirector() + " / 장르 : " + movieVoList.get(i).getGenre() + " / 등급 : " + movieVoList.get(i).getRate());
						}
					}
					catch (Exception e) {
						e.printStackTrace();
					}

					System.out.println("");
					System.out.println("==========영화 삭제==========");
					System.out.print("삭제할 영화의 이름 : ");
					String mtitle = sc.next();
					MDI.delMovie(mtitle);
					System.out.println("영화가 삭제 되었습니다.");
					System.out.println("");
					System.out.println("계속해서 삭제하시겠습니까?");
					System.out.print("[그만:0 계속:1]: ");
					int k = sc.nextInt();
					System.out.println("");

					if (k==0) {
						break;
					}else if(k==1) {
						continue;
					}else {
						System.out.println("잘못 입력하여 영화관리 메뉴로 돌아갑니다.");
						System.out.println("");
					}
				}
				break;

			case 3 :
				System.out.println("========현재 저장된 영화 목록========");
				try {
					movieVoList = MDI.listMovie();

					for(int i=0; i<movieVoList.size(); i++) {
						System.out.println("제목 : " + movieVoList.get(i).getTitle() + " / 감독 : " + movieVoList.get(i).getDirector() + " / 장르 : " + movieVoList.get(i).getGenre() + " / 등급 : " + movieVoList.get(i).getRate());
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("");
				System.out.println("==========영화 조회==========");
				System.out.print("조회할 영화의 제목을 입력 : ");

				String mvtitle = sc.next();
				MovieVO mvvo = new MovieVO();
				try {
					mvvo = MDI.searchMovie(mvtitle);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("");
				System.out.println("영화 이름: " + mvvo.getTitle());
				System.out.println("영화 감독: " + mvvo.getDirector());
				System.out.println("영화 장르: " + mvvo.getGenre());
				System.out.println("영화 등급: " + mvvo.getRate());
				System.out.println("");
				System.out.println("계속해서 조회하시겠습니까?");
				System.out.print("[그만:0 계속:1]: ");
				int l = sc.nextInt();
				if (l==0) 
					break;
				else if(l==1) 
					continue;
				else {
					System.out.println("잘못 입력하여 영화관리 메뉴로 돌아갑니다.");
					System.out.println("");
				}
				break;

			case 4 : 
				System.out.println("==========영화 목록==========");
				try {
					movieVoList = MDI.listMovie();

					for(int i=0; i<movieVoList.size(); i++) {
						System.out.println("제목 : " + movieVoList.get(i).getTitle() + " / 감독 : " + movieVoList.get(i).getDirector() + " / 장르 : " + movieVoList.get(i).getGenre() + " / 등급 : " + movieVoList.get(i).getRate());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("");
				break;

			case 5 :
				System.out.println("상위항목 선택으로 돌아갑니다.");
				return;

			case 6 :
				System.out.println("=====프로그램을 종료합니다=====");
				sc.close();
				System.exit(0); //  프로그램 강제종료 구문
				break;

			}

		}

	}

}

