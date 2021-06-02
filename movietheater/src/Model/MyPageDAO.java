package Model;

public interface MyPageDAO {
	//회원가입
	public void signUpMember(MyPageVO mb);
	//내 정보 조회
	public void viewMember(MyPageVO mb);
	//내 정보 수정
	public void modifyMember(MyPageVO mb);
	//회원 탈퇴
	public void deleteMember(MyPageVO mb);

}
