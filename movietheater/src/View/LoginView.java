package View;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class LoginView extends JFrame implements ActionListener {
	private JPanel Panel = new JPanel();
	
	private JLabel registerLabel = new JLabel("회원가입");
	private JLabel forgotIdLabel = new JLabel("아이디찾기");
	private JLabel forgotPwdLabel = new JLabel("비밀번호찾기");
	private JButton loginButton = new JButton("로그인");
	private String id = "";
	private String password = "";
	private JTextField idText = new JTextField("",10);
	private JPasswordField PwText= new JPasswordField("",10);
	
	public LoginView() {
		super("YongSeok CINEMA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 320);
		
		getContentPane().add(Panel);

		Panel.add(idText);
		Panel.add(PwText);
		Panel.add(registerLabel);
		Panel.add(forgotPwdLabel);
		Panel.add(loginButton);
		setResizable(false);
		setVisible(true);

	}
	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
