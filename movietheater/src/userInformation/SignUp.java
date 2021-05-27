package userInformation;

import java.util.Scanner;

public class SignUp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String pw1;
        String pw2;

        System.out.println("==========회원가입==========");
        System.out.print("아이디: ");
        String id = sc.next();
        System.out.print("비밀번호: ");
        pw1 = sc.next();
        System.out.print("비밀번호 확인: ");
        pw2 = sc.next();


        System.out.print("이름: ");
        String name = sc.next();
        System.out.print("생년원일(6자리): ");
        String birth = sc.next();
    }
}