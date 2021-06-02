package VO;

public class MemberVO {
	private String id;
	private String password;
	private String name;
	private String birth;
	private int points;
	private int admin;
	private String created;
	
	public MemberVO() {
		super();
	}
	public MemberVO(String id, String password, String name, String birth, int admin) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.birth = birth;
		this.admin = admin;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public int getPoint() {
		return points;
	}
	public void setPoint(int points) {
		this.points = points;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	
}
