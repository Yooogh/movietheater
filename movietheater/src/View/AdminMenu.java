package View;

import java.util.Scanner;

public class AdminMenu {
	Scanner scan = new Scanner(System.in);
	public AdminMenu(){
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
	public static void main(String[] args) {
		new AdminMenu();
	}
	public String conInput(String msg) {
		System.out.println(msg +" :");
		return scan.nextLine();
	}
}
