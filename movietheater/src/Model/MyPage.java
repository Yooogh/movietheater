package Model;

import java.util.Scanner;

public class MyPage {

	public static void main(String[] args) {
		System.out.println("되냐?");
		
		MyPageDAOImpl dao = new MyPageDAOImpl();
		
		MyPageVO member = new MyPageVO();
		
		member.setId("a123123123a123123123");
		member.setPw("a456456");
		member.setName("김나나");
		member.setBirth("950120");
		
		dao.signUpMember(member);
		dao.viewMember(member);
		dao.modifyMember(member);
		dao.deleteMember(member);
		
//		Scanner scan = new Scanner(System.in);
//		scan.next();
//		dao.signUpMember(member);
		
//		scan.close();
		
		
	}

}
