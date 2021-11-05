package table.model.vo;

public class FundingLike{

	private int likeNo;
	private int cMemberNo;
	private int likedProjectNo;

//			펀딩 좋아요 테이블(
//					좋아요누른멤버 number not null reference 회원(회원No)
//					좋아요된 펀딩게시물 number not null reference 기본정보(프로젝트No)
//					primary key(좋아요 누른 멤버,좋아요된 펀딩게시물)
//					)

	public FundingLike() {
		super();
	}

	public FundingLike(int likeNo, int cMemberNo, int likedProjectNo) {
		super();
		this.likeNo = likeNo;
		this.cMemberNo = cMemberNo;
		this.likedProjectNo = likedProjectNo;
	}

	public int getLikeNo() {
		return likeNo;
	}

	public void setLikeNo(int likeNo) {
		this.likeNo = likeNo;
	}

	public int getcMemberNo() {
		return cMemberNo;
	}

	public void setcMemberNo(int cMemberNo) {
		this.cMemberNo = cMemberNo;
	}

	public int getLikedProjectNo() {
		return likedProjectNo;
	}

	public void setLikedProjectNo(int likedProjectNo) {
		this.likedProjectNo = likedProjectNo;
	}

	@Override
	public String toString() {
		return "FundingLike [likeNo=" + likeNo + ", cMemberNo=" + cMemberNo + ", likedProjectNo=" + likedProjectNo
				+ "]";
	}
	
	

}
