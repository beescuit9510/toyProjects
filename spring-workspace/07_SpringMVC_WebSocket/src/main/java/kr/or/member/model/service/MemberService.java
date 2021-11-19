package kr.or.member.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.member.model.dao.MemberDao;
import kr.or.member.model.vo.Member;
import kr.or.member.model.vo.PwChangeVO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDao dao;
	
//	public Member login2(String memberId, String memberPw) {
//		System.out.println(memberId);
//		System.out.println(memberPw);
//		Member m = dao.login2(memberId,memberPw);
//		System.out.println(memberId);
//		System.out.println(memberPw);
//		return null;
//	}


	public Member selectOneMember(Member member) throws IllegalArgumentException {
		System.out.println("로그인 서비스 시작");
/*

		System.out.println("memberPw :"+member.getMemberPw());
		if(member.getMemberId().isEmpty() || member.getMemberPw().isEmpty()) {
			throw new IllegalArgumentException("아이디 또는 패스워드를 입력해야합니다.");
		}
*/
		Member m = dao.selecetOneMember(member);
		System.out.println("로그인 서비스 끝");
		return m;
	}

	@Transactional
	public int insertMember(Member member) {
		int result = dao.insertMember(member);
		return result;
	}
	
	@Transactional
	public int memberUpdate(Member m) {
		int result = dao.updateMember(m);
		return result;
	}

	public Member mypage(String memberId) {
		Member m = dao.mypage(memberId);
		return m;
	}

	public ArrayList<Member> selectAllMember() {
		ArrayList<Member> list = dao.selecetAllMember();		
		return list;
	}

	public Member selectOneMember(String memberId) {
		Member m = dao.mypage(memberId);
		return m;
	}

	public int updatePw(Member m) {
		int result = dao.updatePw(m);
		return result;
	}

	public Member checkPw(Member m) {
		Member mToReturn = dao.checkPw(m);
		return mToReturn;
	}

	public int pwChangeMember(Member m) {
		return dao.pwChangeMember(m);
	}

	public int changePw(PwChangeVO pc) {
		//1. 기존 비밀번호가 맞는지 확인
		Member m = new Member();
		m.setMemberId(pc.getMemberId());
		m.setMemberPw(pc.getOldPassword());
	
		if(dao.selecetOneMember(m) == null) {
			return -1;
		}else {
			m.setMemberPw(pc.getNewPassword());
			return dao.pwChangeMember(m);
		}
	}


}
