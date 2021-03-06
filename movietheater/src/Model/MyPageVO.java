package Model;


public class MyPageVO {
	//정보수정
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
