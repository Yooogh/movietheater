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

        // 비밀번호 확인 일치 체크
        while (true) {
            System.out.print("비밀번호: ");
            pw1 = sc.next();
            System.out.print("비밀번호 확인: ");
            pw2 = sc.next();

            if (!pw1.equals(pw2)) {     //  비밀번호가 서로 일치하지 않는 경우 비밀번호 재입력
                System.out.println("비밀번호가 일치하지 않습니다");
            }else {     //  비밀번호 일치시 반복문 탈출
                System.out.println("비밀번호가 일치합니다");
                break;
            }
        }
        System.out.print("이름: ");
        String name = sc.next();
        System.out.print("생년원일(6자리): ");
        String birth = sc.next();
    }
}