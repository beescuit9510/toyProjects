package mypageFunderFunding.model.vo;

import table.model.vo.FundingComment;
import table.model.vo.Member;

public class MyOwnProjectComment {
	FundingComment fundingComment;
	Member member;

	public FundingComment getFundingComment() {
		return fundingComment;
	}

	public void setFundingComment(FundingComment fundingComment) {
		this.fundingComment = fundingComment;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public MyOwnProjectComment(FundingComment fundingComment, Member member) {
		super();
		this.fundingComment = fundingComment;
		this.member = member;
	}

	@Override
	public String toString() {
		return "MyOwnProjectComment [fundingComment=" + fundingComment.toString() + ", member=" + member.toString() + "]";
	}
	
	

}
