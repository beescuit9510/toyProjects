package table.model.vo;

public class PaymentInfo {

	private Long paymentNo;
	private int quantity;
	private String receiveAddr;
	private String orderDate;
	private String receiveName;
	private String receivePhone;
	private int projectNo;
	private int cMemberNo;

//			주문목록(펀딩한 프로젝트) 테이블(
//					주문번호 varchar(30) primary key //주문번호(날짜+번호)
//					수량 number not null
//					주소 varchar2(1500) not null
//					주문일자 char(19) not null
//				----to_char(sysdate, 'YYYY-MM-DD HH24:MI:SS') 형식으로

//					받는 사람 varchar2(50) not null
//					연락처 char(13) not null
//					프로젝트No not null number reference 기본정보(프로젝트No)
//					회원No number not null reference 회원(회원No)
//					)

	public PaymentInfo() {
		super();
	}

	public PaymentInfo(Long paymentNo, int quantity, String receiveAddr, String orderDate, String receiveName,
			String receivePhone, int projectNo, int cMemberNo) {
		super();
		this.paymentNo = paymentNo;
		this.quantity = quantity;
		this.receiveAddr = receiveAddr;
		this.orderDate = orderDate;
		this.receiveName = receiveName;
		this.receivePhone = receivePhone;
		this.projectNo = projectNo;
		this.cMemberNo = cMemberNo;
	}

	public Long getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(Long paymentNo) {
		this.paymentNo = paymentNo;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getReceiveAddr() {
		return receiveAddr;
	}

	public void setReceiveAddr(String receiveAddr) {
		this.receiveAddr = receiveAddr;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public String getReceivePhone() {
		return receivePhone;
	}

	public void setReceivePhone(String receivePhone) {
		this.receivePhone = receivePhone;
	}

	public int getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(int projectNo) {
		this.projectNo = projectNo;
	}

	public int getcMemberNo() {
		return cMemberNo;
	}

	public void setcMemberNo(int cMemberNo) {
		this.cMemberNo = cMemberNo;
	}

	@Override
	public String toString() {
		return "PaymentInfo [paymentNo=" + paymentNo + ", quantity=" + quantity + ", receiveAddr=" + receiveAddr
				+ ", orderDate=" + orderDate + ", receiveName=" + receiveName + ", receivePhone=" + receivePhone
				+ ", projectNo=" + projectNo + ", cMemberNo=" + cMemberNo + "]";

	}

}
