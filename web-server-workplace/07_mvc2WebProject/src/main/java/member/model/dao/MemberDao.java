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


	public int insetMember(Connection conn, Member member) {
		String query = "insert into member values(member_seq.nextval,?,?,?,?,?,3,to_char(sysdate,'yyyy-mm-dd'))";
		int result = -1;
		
		try (PreparedStatement pstmt = conn.prepareStatement(query);){

			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getAddress());
			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}


	public Member selectOneMember(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet r = null;
		Member member = null;
		String query = "select * from member where member_id = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);

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
