package Controller;

import Model.MyPageDAO;
import Model.MyPageDAOImpl;
import Model.MyPageVO;

public class MyPageControllerImpl implements MyPageController{
	public MyPageDAO dao;

	@Override
	public void signUpMember(MyPageVO myPageVO) {
		dao = new MyPageDAOImpl();
		dao.signUpMember(myPageVO);
	}

	@Override
	public void viewMember(String ID) {
		dao = new MyPageDAOImpl();
		dao.viewMember(ID);
	}

	@Override
	public void modifyMember(MyPageVO myPageVO) {
		dao = new MyPageDAOImpl();
		dao.modifyMember(myPageVO);
	}

	@Override
	public void deleteMember(MyPageVO myPageVO) {
		dao = new MyPageDAOImpl();
		dao.deleteMember(myPageVO);
	}

}
