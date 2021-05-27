

public class MyPageVO {
	//정보수정
	private String ID;//final
	private String PW;
	private String name;
	private String birth;//final
	//예매확인
	private String title;
	private String date;
	private String plex;
	private String seat;
	

//Getter, Setter
//내 정보
	public String getID() {	return ID; }

	public void setID(String iD) {	ID = iD; }

	public String getPW() {	return PW; }

	public void setPW(String pW) { PW = pW; }

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

	public String getBirth() { return birth; }

	public void setBirth(String birth) { this.birth = birth; }
//영화 예매
	public String getTitle() { return title; }

	public void setTitle(String title) { this.title = title; }

	public String getDate() { return date; }

	public void setDate(String date) { this.date = date; }

	public String getPlex() { return plex; }

	public void setPlex(String plex) { this.plex = plex; }

	public String getSeat() { return seat; }

	public void setSeat(String seat) { this.seat = seat; }
	
	
	public static void main(String[] args) {
		//예매 확인
//		MyPage mypage = new MyPage();

	}
}
