package View;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

//import Book.BookDataSet;
//import Book.BookVO;

public class MainMenu {
	Scanner scan = new Scanner(System.in);
	public MainMenu(){
		do {
			String menu = conInput("\n \t MENU \t 0.전체 영화 목록 1.영화 관리 2.종료 하기");
			if(menu.equals("0")) {
				//전체 도서 목록
				//bookList();
				//MovieList();
				
			}else if(menu.equals("1")) {
				//도서 관리
				String submenu1 = conInput("1.영화 관리 a:등록, b:수정, c:삭제");
				
				if(submenu1.equals("a")) {
					//MovieResister();
					System.out.println("새로운 영화가 등록되었습니다.");
					//MovieList();
				}else if(submenu1.equals("b")) {
					//MovieEdit();
					System.out.println("정상적으로 수정되었습니다.");
					//MovieList();
				}else if(submenu1.equals("c")) {
					//MovieRemove();
					System.out.println("정상적으로 삭제되었습니다.");
					//MoviekList();
				}else {
					
				}				
				
			}else if(menu.equals("2")) {
				//종료 하기
				System.out.println("프로그램이 종료되었습니다."); //종료
				break;
			}else {
				
			}
			
		}while(true);
	}
	
	public void bookList() {
		/*
		//전체 도서 오름차순 정렬
		TreeMap<String, MovieVO> bookAsc = new TreeMap<String, MovieVO>(MovieDataSet.list);
		Set<String> key = bookAsc.keySet();
		
		System.out.println(" 총 " +key.size() +"건이 검색되었습니다.\n");
		System.out.println("\t도서명 \t\t\t 저자 \t\t\t 출판사 \t\t 발행연도 \t\t 장르");
		
		Iterator<String> keyList = key.iterator();
		while(keyList.hasNext()) {
			BookVO vo = bookAsc.get(keyList.next());
			System.out.printf("%-20s %20s %20s %20d %20s \n",
							  vo.getTitle(), vo.getAuthor(), vo.getPublisher(), vo.getYear(), vo.getGenre());
		}
		*/
	}
	public static void main(String[] args) {
		new MainMenu();
	}
	public String conInput(String msg) {
		System.out.println(msg +" :");
		return scan.nextLine();
	}
}
