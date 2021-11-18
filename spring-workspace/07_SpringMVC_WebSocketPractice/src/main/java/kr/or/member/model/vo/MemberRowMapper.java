package kr.or.member.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MemberRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Member m = new Member();
		m.setMemberId(rs.getString("member_id"));
		m.setMemberPw(rs.getString("member_pw"));
		m.setMemberName(rs.getString("member_name"));
		m.setAddress(rs.getString("address"));
		m.setEnrollDate(rs.getString("enroll_date"));

		return m;
	}
	
	

}
