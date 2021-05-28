package View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import Model.MemberVO;
import dao.MemberDAO;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterView {
	Scanner scan = new Scanner(System.in);	
	// yyyy-mm-dd 형식에 맞추기 위한 패턴
	Pattern p = Pattern.compile("^([0-9]{4})-([0-1]{1})([0-9]{1})-([0-9]{2})$");
	
	public RegisterView(){
		System.out.println("시네마 회원가입");
		if(register() == -1) {
			System.out.println("가입에 실패했습니다. 다시 시도해주세요.");
		}else {
			System.out.println("가입 성공");
		}
		return;
	}
	public int register() {
		MemberDAO mdao = new MemberDAO();
		String id="";
		int idcount = 0;
		boolean idcheck = false;
		while(!idcheck) {
		if(idcount >2) {
			return -1;
		}
		id= conInput("id를 입력하세요");
		if(id.trim().isEmpty()||id.length()> 10||id.equals("admin")||id.equals("register")) {
			System.out.println("사용하실 수 없는 아이디입니다.");
			idcount++;
			continue;
		}
		if(mdao.IsExist(id)) {
			System.out.println("중복된 아이디입니다.");
			idcount++;
			continue;
		}
		idcheck = true;
		}
		String password ="";
		String pwrepeat;
		boolean pwcheck = false;
		int pwcount = 0;
		while(!pwcheck) {
		if(pwcount >2) {
			return -1;
		}
		password = conInput("비밀번호를 입력하세요");
		if(password.trim().isEmpty()||password.equals(id)||password.length()> 20) {
			System.out.println("사용하실 수 없는 비밀번호입니다.");
			pwcount++;
			continue;
		}
		pwrepeat = conInput("비밀번호를 다시 입력하세요");
		if(password.equals(pwrepeat))
			pwcheck = true;
		else
			System.out.println("비밀번호를 잘못 입력했습니다. 다시 입력하세요.");
		pwcount++;
		}
		String birth ="";
		int birthcount = 0;
		boolean birthcheck = false;
		while(!birthcheck) {
			if(birthcount >2) {
				return -1;
			}
		birth = conInput("생년월일을 입력하세요.yyyy-mm-dd");
		Matcher m1 = p.matcher(birth);
		if(m1.find()) {
			//System.out.println("ok");
			int age;
			int day = 0;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date brithday;
			long diff = 0;
			try {
				brithday = format.parse(birth);
				Date today = new Date();
				diff = today.getTime()-brithday.getTime();
				day = (int) (diff/(24*60*60*1000));
				age = day/365;
				System.out.println(age);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if(day < 0)
				System.out.println("날짜를 형식에 맞게 입력해주세요.");	
			else
				birthcheck = true;
		} else {
			System.out.println("날짜를 형식에 맞게 입력해주세요.");
		}
		birthcount++;
		}
		/*나이 계산
		int age;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date brithday;
		try {
			brithday = format.parse(birth);
			Date today = new Date();
			long diff = today.getTime()-brithday.getTime();
			age = (int) (diff/(24*60*60*1000)/365);
			System.out.println(age);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		*/
		String name ="";
		boolean namecheck = false;
		int namecount=0;
		while(!namecheck) {
		if(namecount >2) {
			return -1;
		}
		name= conInput("이름을 입력하세요");
		if(name.trim().isEmpty()||name.length()> 5) {
			System.out.println("사용하실 수 없는 이름입니다.");
			namecount++;
			continue;
		}
		namecheck = true;
		}
		MemberVO mvo = new MemberVO(id, password, name, birth, 0);
		int result = mdao.regMember( mvo );
		//System.out.println("result: " + result);
		return result;
	}

	public static void main(String[] args) {
		new RegisterView();
	}
	public String conInput(String msg) {
		System.out.println(msg +" :");
		return scan.nextLine();
	}
}
