package kh.model.vo;

public class Kh {
	private int khNo;
	private String khName;
	private String addr;
	private String fax;

	public int getKhNo() {
		return khNo;
	}

	public void setKhNo(int khNo) {
		this.khNo = khNo;
	}

	public String getKhName() {
		return khName;
	}

	public void setKhName(String khName) {
		this.khName = khName;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Kh(int khNo, String khName, String addr, String fax) {
		super();
		this.khNo = khNo;
		this.khName = khName;
		this.addr = addr;
		this.fax = fax;
	}

	public Kh() {
		super();
	}

}
