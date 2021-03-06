package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

		try (PreparedStatement pstmt = conn.prepareStatement(query);) {

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

	public int updateMember(Connection conn, Member member) {
		String query = "update member set member_pw=?, member_name=?, phone=?, address=? where member_id=?";
		int result = -1;

		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			int i = 1;
			pstmt.setString(i++, member.getMemberPw());
			pstmt.setString(i++, member.getMemberName());
			pstmt.setString(i++, member.getPhone());
			pstmt.setString(i++, member.getAddress());
			pstmt.setString(i++, member.getMemberId());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public int deleteMember(Connection conn, int memberNo) {
		String query = "delete from member where member_no=?";
		int result = -1;

		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, memberNo);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public Member findId(Connection conn, String memberName, String phone) {
		String query = "select * from member where member_name = ? and phone = ?";
		ResultSet r = null;
		PreparedStatement pstmt = null;
		Member member = null;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberName);
			pstmt.setString(2, phone);

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
			JDBCTemplate.close(pstmt);
		}

		return member;
	}

	public Member findPW(Connection conn, String memberId, String phone) {
		String query = "select * from member where member_id = ? and phone = ?";
		ResultSet r = null;
		PreparedStatement pstmt = null;
		Member member = null;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, phone);

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
			JDBCTemplate.close(pstmt);
		}

		return member;
	}

	public ArrayList<Member> selectAllMember(Connection conn) {
		String query = "select * from member";
		ArrayList<Member> members = new ArrayList<>();

		try (PreparedStatement pstmt = conn.prepareStatement(query); ResultSet r = pstmt.executeQuery();) {

			while (r.next()) {
				int i = 1;
				members.add(new Member(r.getInt(i++), r.getString(i++), r.getString(i++), r.getString(i++),
						r.getString(i++), r.getString(i++), r.getInt(i++), r.getString(i++)));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return members;
	}

	public int changeLevel(Connection conn, int memberNo, int memberLevel) {
		String query = "update member set member_level = ? where member_no = ?";
		int r = -1;

		try (PreparedStatement pstmt = conn.prepareStatement(query);) {

			pstmt.setInt(1, memberLevel);
			pstmt.setInt(2, memberNo);

			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return r;
	}

}
