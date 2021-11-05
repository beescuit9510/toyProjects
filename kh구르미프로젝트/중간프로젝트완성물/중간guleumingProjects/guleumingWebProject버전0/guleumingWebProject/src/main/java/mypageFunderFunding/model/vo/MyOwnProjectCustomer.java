package mypageFunderFunding.model.vo;

import table.model.vo.Member;
import table.model.vo.PaymentInfo;

public class MyOwnProjectCustomer {
	PaymentInfo paymentInfo;
	Member member;

	public MyOwnProjectCustomer(PaymentInfo paymentInfo, Member member) {
		super();
		this.paymentInfo = paymentInfo;
		this.member = member;
	}

	public PaymentInfo getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Override
	public String toString() {
		return "MyOwnProjectCustomers [paymentInfo=" + paymentInfo.toString() + ", member=" + member.toString() + "]";
	}

	public MyOwnProjectCustomer() {
		super();
	}

}
