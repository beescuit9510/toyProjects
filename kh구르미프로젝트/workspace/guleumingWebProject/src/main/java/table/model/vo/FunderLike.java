package table.model.vo;

public class FunderLike {

	private int likeNo;
	private int likeMemberNo;
	private int likedBusinessNo;

//	펀더 좋아요 테이블(
//			좋아요누른멤버 number not null reference 회원(회원No)
//			좋아요된 펀더 number not null reference 사업자 등록(사업자 등록No) 
//			primary key(좋아요 누른 멤버,좋아요된 펀더)
//			)

	public FunderLike() {
		super();
	}

	public FunderLike(int likeNo, int likeMemberNo, int likedBusinessNo) {
		super();
		this.likeNo = likeNo;
		this.likeMemberNo = likeMemberNo;
		this.likedBusinessNo = likedBusinessNo;
	}

	public int getLikeNo() {
		return likeNo;
	}

	public void setLikeNo(int likeNo) {
		this.likeNo = likeNo;
	}

	public int getLikeMemberNo() {
		return likeMemberNo;
	}

	public void setLikeMemberNo(int likeMemberNo) {
		this.likeMemberNo = likeMemberNo;
	}

	public int getLikedBusinessNo() {
		return likedBusinessNo;
	}

	public void setLikedBusinessNo(int likedBusinessNo) {
		this.likedBusinessNo = likedBusinessNo;
	}

	@Override
	public String toString() {
		return "funderLike [likeMemberNo=" + likeMemberNo + ", likedBusinessNo=" + likedBusinessNo + "]";
	}

}
