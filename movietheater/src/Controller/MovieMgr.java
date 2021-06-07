package Controller;


import Model.MovieVO;

public interface MovieMgr {
	
	
	public void addMovie(MovieVO movievo); //영화 등록
	
	public void searchMovie(MovieVO movievo); //영화 조회
	
	public void delMovie(MovieVO movievo); //영화 삭제
	
	public void listMovie(MovieVO movievo); //영화 리스트 열람
	
	
			
}
