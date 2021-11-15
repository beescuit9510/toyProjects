package kr.or.member.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.member.model.vo.Member;
import kr.or.member.model.vo.MemberRowMapper;

@Repository
public class MemberDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public Member selecOneMember(Member member) {
		String query = "select * from member where member_id = ? and member_pw = ?";
		Object[] param = {member.getMemberId(),member.getMemberPw()};
		
		List list = jdbcTemplate.query(query, param,new MemberRowMapper());
		
		if(list.isEmpty()) {
			return null;			
		}else {
			return (Member)list.get(0);
		}
	}

	public ArrayList<Member> selectAllMember() {
		String query = "select * from member";
		List list = jdbcTemplate.queryForList(query, new MemberRowMapper());
		
		return (ArrayList<Member>)list;
	}

	public Member selecOneMember(String memberId) {
		String query = "select * from member where member_id = ?";
		
		List list = jdbcTemplate.query(query,new Object[] {memberId}, new MemberRowMapper());

		if(list.isEmpty()) {
			return null;
		}else {
			return (Member)list.get(0);			
		}
	}

	public int updateMember(Member m) {
		String query = "update member set member_pw = ?, address = ? where member_id = ?";
		
		int result = jdbcTemplate.update(query,new Object[] {m.getMemberPw(),m.getAddress(),m.getMemberId()});
		
		return result;
	}

	public int insertMember(Member m) {
		String query = "insert into member values(?,?,?,?,to_char(sysdate,'yyyy-mm-dd'))";
		
		int result = jdbcTemplate.update(query,new Object[] {m.getMemberId(),m.getMemberPw(),m.getMemberName(),m.getAddress()});
		
		return result;
	}

	public int deleteMember(String memberId) {
		String query = "delete member where member_id = ?";
		
		int result = jdbcTemplate.update(query,new Object[] {memberId});
		
		return result;
	}


}
