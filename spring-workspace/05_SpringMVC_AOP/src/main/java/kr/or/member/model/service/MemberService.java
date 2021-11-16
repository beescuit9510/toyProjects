package kr.or.member.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.member.model.dao.MemberDao;
import kr.or.member.model.vo.Member;

@Service
public class MemberService {
	
	@Autowired
	private MemberDao dao;
	
	
	public MemberService() {
//		System.out.println("MemberService 객체 생성");
		super();
	}

	public Member selectOneMember(Member member) throws IllegalArgumentException {
		System.out.println("로그인 서비스 시작");
//		System.out.println("memberPw :"+member.getMemberPw());

		if(member.getMemberId().isEmpty() || member.getMemberPw().isEmpty()) {
			throw new IllegalArgumentException("아이디 또는 패스워드를 입력해야합니다.");
		}

		Member m = dao.selecetOneMember(member);
		System.out.println("로그인 서비스 끝");
		return m;
	}

	public int insertMember(Member member) {
		int result = dao.insertMember(member);
		return result;
	}

	public int updateMember(Member m) {
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

}
