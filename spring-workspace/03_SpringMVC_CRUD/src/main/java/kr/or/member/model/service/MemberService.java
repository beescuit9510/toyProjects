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
		// TODO Auto-generated constructor stub
		System.out.println("MemberService 객체 생성");
	}

	public Member selectOneMember(Member member) {
		Member m = dao.selecetOneMember(member);
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

}
