package mypageFunderFunding.model.vo;

import java.util.ArrayList;

import table.model.vo.FundingComment;
import table.model.vo.PaymentInfo;

public class MyOwnProject {
	Funding funding;
	ArrayList<FundingComment> comments;
	ArrayList<MyOwnProjectCustomer> myOwnProjectCustomers;

	public MyOwnProject(Funding funding, ArrayList<FundingComment> comments,
			ArrayList<MyOwnProjectCustomer> myOwnProjectCustomers) {
		super();
		this.funding = funding;
		this.comments = comments;
		this.myOwnProjectCustomers = myOwnProjectCustomers;
	}

	public MyOwnProject() {
		super();
	}

	public Funding getFunding() {
		return funding;
	}

	public void setFunding(Funding funding) {
		this.funding = funding;
	}

	public ArrayList<FundingComment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<FundingComment> comments) {
		this.comments = comments;
	}

	public ArrayList<MyOwnProjectCustomer> getMyOwnProjectCustomers() {
		return myOwnProjectCustomers;
	}

	public void setMyOwnProjectCustomers(ArrayList<MyOwnProjectCustomer> myOwnProjectCustomers) {
		this.myOwnProjectCustomers = myOwnProjectCustomers;
	}

	@Override
	public String toString() {
		return "MyOwnProject [funding=" + funding.toString() + ", comments=" + comments.toString()
				+ ", myOwnProjectCustomers=" + myOwnProjectCustomers.toString() + "]";
	}

}
