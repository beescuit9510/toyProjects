package mypageFunderFunding.model.vo;

import java.util.ArrayList;

public class MyOwnProject {
	Funding funding;
	ArrayList<MyOwnProjectComment> myOwnProjectComment;
	ArrayList<MyOwnProjectCustomer> myOwnProjectCustomers;

	public MyOwnProject(Funding funding, ArrayList<MyOwnProjectComment> myOwnProjectComment,
			ArrayList<MyOwnProjectCustomer> myOwnProjectCustomers) {
		super();
		this.funding = funding;
		this.myOwnProjectComment = myOwnProjectComment;
		this.myOwnProjectCustomers = myOwnProjectCustomers;
	}

	public ArrayList<MyOwnProjectComment> getMyOwnProjectComment() {
		return myOwnProjectComment;
	}

	public void setMyOwnProjectComment(ArrayList<MyOwnProjectComment> myOwnProjectComment) {
		this.myOwnProjectComment = myOwnProjectComment;
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

	public ArrayList<MyOwnProjectCustomer> getMyOwnProjectCustomers() {
		return myOwnProjectCustomers;
	}

	public void setMyOwnProjectCustomers(ArrayList<MyOwnProjectCustomer> myOwnProjectCustomers) {
		this.myOwnProjectCustomers = myOwnProjectCustomers;
	}

	@Override
	public String toString() {
		return "MyOwnProject [funding=" + funding + ", myOwnProjectComment=" + myOwnProjectComment
				+ ", myOwnProjectCustomers=" + myOwnProjectCustomers + "]";
	}

}
