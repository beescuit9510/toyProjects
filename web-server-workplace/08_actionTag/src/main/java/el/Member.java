package el;

public class Member {
	private String name;
	private int age;
	private String addr;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getTEST() {
		return "테스트 getTEST";
	}
	public String getTest() {
		return "테스트 getTest";
	}
	public String gettest() {
		return "테스트 gettest";
	}

	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name;
	}

	
}
