package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import member.model.vo.Member;

public class MemberDao {

	public Member selectOneMember(Connection conn, String memberId, String memberPw) {

		PreparedStatement pstmt = null;
		ResultSet r = null;
		Member member = null;
		String query = "select * from member where member_id = ? and member_pw = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);

			r = pstmt.executeQuery();

			if (r.next()) {
				int i = 1;
				member = new Member(r.getInt(i++), r.getString(i++), r.getString(i++), r.getString(i++),
						r.getString(i++), r.getString(i++), r.getInt(i++), r.getString(i++));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(r);
		}

		return member;
	}

}
