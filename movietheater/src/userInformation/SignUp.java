package userInformation;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SignUp extends JFrame{
	public SignUp() {
		setTitle("용석시네마 회원가입");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		c.add(new JLabel("아이디"));
		c.add(new JTextField(20));
		c.add(new JLabel("비밀번호"));
		c.add(new JTextField(20));
		c.add(new JLabel("이름"));
		c.add(new JTextField(20));
		c.add(new JLabel("생년월일"));
		c.add(new JTextField(20));
		
		setSize(300, 150);
		setVisible(true);
	}
	public static void main(String[] args) {
		new SignUp();
	}
}