package mypageFunderFunding.model.vo;

public class Like implements Comparable<Like>{
	int likeNo;

	public Like(int likeNo) {
		super();
		this.likeNo = likeNo;
	}

	public int getLikeNo() {
		return likeNo;
	}

	public void setLikeNo(int likeNo) {
		this.likeNo = likeNo;
	}

	@Override
	public int compareTo(Like like) {
		return  like.likeNo - this.likeNo;
	}

	
	
	
	
}
