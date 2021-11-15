package kr.or.member.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MemberRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Member member = new Member();
		member.setMemberId(rs.getString("member_id"));
		member.setMemberPw(rs.getString("member_pw"));
		member.setMemberName(rs.getString("member_name"));
		member.setAddress(rs.getString("address"));
		member.setEnrollDate(rs.getString("enroll_date"));
		
		return member;
	}
	
	
	
	

}
