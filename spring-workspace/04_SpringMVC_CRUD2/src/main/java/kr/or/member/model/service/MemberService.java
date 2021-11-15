package kr.or.member.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.member.model.dao.MemberDao;
import kr.or.member.model.vo.Member;

@Service
public class MemberService {
	@Autowired
	MemberDao dao;
	
	public Member selectOneMember(Member member) {
		Member m = dao.selecOneMember(member);
		return m;
	}

	public int insertMember(Member member) {
		int result = dao.insertMember(member);
		return result;
	}

	public Member mypage(String memberId) {
		Member m = dao.selecOneMember(memberId);
		return m;
	}

	public int updateMember(Member member) {
		int result = dao.updateMember(member);
		return result;
	}

	public Member idCheck(String memberId) {
		Member m = dao.selecOneMember(memberId);
		return m;
	}

	public ArrayList<Member> allMemberAjax() {
		ArrayList<Member> list = dao.selectAllMember();
		return list;
	}

	public int deleteMember(String memberId) {
		int result = dao.deleteMember(memberId);
		return result;
	}

}
