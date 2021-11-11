package kr.or.member.model.dao;

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


}
