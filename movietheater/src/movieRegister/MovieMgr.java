package movieRegister;


public interface MovieMgr {
	
	
	public void addMovie(MovieVO movievo); //영화 등록
	
	public void searchMovie(MovieVO movievo); //영화 조회
	
	public void delMovie(MovieVO movievo); //영화 삭제
			
}
