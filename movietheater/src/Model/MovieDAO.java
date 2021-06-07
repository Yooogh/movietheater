package Model;

import java.util.ArrayList;

public interface MovieDAO {

	void addMovie(MovieVO movievo); //영화 등록

	MovieVO searchMovie(String title) throws Exception; //영화 조회

	void delMovie(String title) ; //영화 삭제
	
	ArrayList<MovieVO> listMovie() throws Exception; //영화 리스트 열람

}
