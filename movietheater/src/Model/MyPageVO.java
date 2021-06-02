<<<<<<< HEAD
package Model;
=======
>>>>>>> c3f3120a61c3db5ec7d77e21cb85f8d4ce2b8ca1


public class MyPageVO {
	//정보수정
<<<<<<< HEAD
	private String id;//final
	private String pw;
	private String name;
	private String birth;
	
	//생성자
	public MyPageVO() {
		
	}
	
	public MyPageVO(String id, String pw, String name, String birth) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.birth = birth;
	}

	//Getter, Setter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}
	
	@Override
	public String toString() {
		return "MyPageVO [ID=" + id + ", name=" + name + ", birth=" + birth + "]";
	}
	
	public String getMember() {
		StringBuffer sb = new StringBuffer();
		sb.append("ID : " + id +"\n");
		sb.append("이름 : " + name +"\n");
		sb.append("생일 : " + birth +"\n");
		sb.append("이름 : " + name +"\n");
		
		return sb.toString();
	}

}
=======
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
>>>>>>> c3f3120a61c3db5ec7d77e21cb85f8d4ce2b8ca1
