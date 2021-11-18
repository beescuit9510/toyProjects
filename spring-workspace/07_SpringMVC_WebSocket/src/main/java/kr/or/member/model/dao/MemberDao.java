package kr.or.member.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.member.model.vo.Member;
import kr.or.member.model.vo.MemberRowMapper;

@Repository
public class MemberDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
//	@Autowired
//	private SqlSessionTemplate sqlSession;
	
	

//	public Member selecetOneMember(Member member) {
//		return sqlSession.selectOne("member.selectOneMember", member);
//	}
	

	public int insertMember(Member m) {
		// TODO Auto-generated method stub
		String query = "insert into member values(?,?,?,?,to_char(sysdate, 'yyyy-mm-dd'))";
		Object[] params = {m.getMemberId(),m.getMemberPw(),m.getMemberName(),m.getAddress()};
		
		//insert,update,delete jdbcTemplate.update();
		int result = jdbcTemplate.update(query,params);
		
		return result;
	}

	public int updateMember(Member m) {
		String query = "update member set address = ? where member_id = ?";
		Object[] params = {m.getAddress(), m.getMemberId()};
		
		int result = jdbcTemplate.update(query, params);
		
		return result;
	}

	public Member mypage(String memberId) {
		String query = "select * from member where member_id = ?";
	//	Object[] params = {memberId};
		
		List list = jdbcTemplate.query(query,new Object[]{memberId}, new MemberRowMapper());
		
		if(list.isEmpty()) {
			return null;
		}else {
			return (Member)list.get(0);
		}
	}

	public ArrayList<Member> selecetAllMember() {
		String query = "select * from member";
		List list = jdbcTemplate.query(query, new MemberRowMapper());		
		return (ArrayList<Member>)list;
	}


	public int updatePw(Member m) {
		String query = "update member set member_pw = ? where member_id = ?";
		int result = jdbcTemplate.update(query, new Object[] {m.getMemberPw(), m.getMemberId()});		
		return result;
	}


	public Member checkPw(Member m) {
		String query = "select * from member where member_id = ? and member_pw = ?";
		List list = jdbcTemplate.query(query, new Object[] {m.getMemberId(), m.getMemberPw()},new MemberRowMapper());		
		if(list.isEmpty()) {
			return null;
		}	
		return (Member)list.get(0);
	}


	public int pwChangeMember(Member m) {
		String query ="update member set member_pw = ? where member_id = ?";
		int result = jdbcTemplate.update(query,new Object[] {m.getMemberPw(), m.getMemberId()});
		return result;
	}



	public Member selecetOneMember(String memberId) {
		String query ="select * from member where member_id = ?";

		List list = jdbcTemplate.query(query,new Object[]{memberId}, new MemberRowMapper());
		
		if(list.isEmpty()) {
			return null;
		}else {
			return (Member)list.get(0);
		}
	}
	public Member selecetOneMember(Member member) {
		//1. PraparedStatement 방식으로 쿼리문 작성
		String query = "select * from member where member_id = ? and member_pw = ?";
		
		//2. 위치홀더에 대체되어야 할 값을 순서대로 Object 배열로 생성
		Object[] params = {member.getMemberId(),member.getMemberPw()};
		
		//3. 조회인 경우 -> jdbcTemplate.query() 메소드 사용
		//-> 매개변수 : 1) 쿼리문, 2) 위치홀더에 들어갈 값 (Object[]), 3)결과를 처리 할 객체
		// 조회결과 객수에 관계없이ㅣ 무조건 List(java.utill.List)타입으로 리턴
		List list = jdbcTemplate.query(query, params,new MemberRowMapper());
		
		if(list.isEmpty()) {
			return null;
		}else {
			Member m =(Member)list.get(0);
			return m;
		}
		
	}
	public Member login2(String memberId, String memberPw) {
		String query = "select * from member where member_id = ? and member_pw = ?";
		Object[] params = {memberId,memberPw};
		List list = jdbcTemplate.query(query, params,new MemberRowMapper());
		
		if(list.isEmpty()) {
			return null;
		}else {
			Member m =(Member)list.get(0);
			return m;
		}
	}

}