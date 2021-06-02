package Controller;

import Model.MyPageVO;

public interface MyPageController {
	
	public void signUpMember(MyPageVO mb);
	
	public void viewMember(MyPageVO mb);
	
	public void modifyMember(MyPageVO mb);
	
	public void deleteMember(MyPageVO mb);

}
