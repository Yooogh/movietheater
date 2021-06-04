package Model;

public interface MyPageDAO {
	//회원가입
	void signUpMember(MyPageVO mb);
	//내 정보 조회
	MyPageVO viewMember(String id);
	//내 정보 수정
	 void modifyMember(MyPageVO mb);
	//회원 탈퇴
	void deleteMember(MyPageVO mb);
	
	
	
	

}
