package kr.or.member.model.vo;

public class MemberMgr {
	private Member M;

	public MemberMgr(Member m) {
		super();
		M = m;
	}

	public MemberMgr() {
		super();
	}

	public Member getM() {	
		return M;
	}

	public void setM(Member m) {
		M = m;
	}

}
