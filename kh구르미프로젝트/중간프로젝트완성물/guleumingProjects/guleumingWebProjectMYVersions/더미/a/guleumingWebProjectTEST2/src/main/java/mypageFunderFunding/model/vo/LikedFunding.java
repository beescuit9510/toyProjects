package mypageFunderFunding.model.vo;

public class LikedFunding extends Like{
	Funding funding;


	public LikedFunding(int likeNo, Funding funding) {
		super(likeNo);
		this.funding = funding;
	}

	public Funding getFunding() {
		return funding;
	}

	public void setFunding(Funding funding) {
		this.funding = funding;
	}
	

}
