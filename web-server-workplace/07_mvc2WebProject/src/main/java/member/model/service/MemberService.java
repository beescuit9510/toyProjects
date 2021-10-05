package member.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import member.model.dao.MemberDao;
import member.model.vo.Member;

public class MemberService {

	public Member selectOneMember(String memberId, String memberPw) {
		Connection conn = JDBCTemplate.getConnection();

		Member member = new MemberDao().selectOneMember(conn, memberId, memberPw);

		JDBCTemplate.close(conn);

		return member;
	}

	public int insertMember(Member member) {
		Connection conn = JDBCTemplate.getConnection();
//		
		int result = new MemberDao().insetMember(conn, member);
//		
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
//		
		JDBCTemplate.close(conn);
//				
		return result;
//	}

	}

	public Member selectOneMember(String memberId) {
		Connection conn = JDBCTemplate.getConnection();

		Member member = new MemberDao().selectOneMember(conn, memberId);

		JDBCTemplate.close(conn);

		return member;
	}

	public int updateMember(Member member) {
		Connection conn = JDBCTemplate.getConnection();

		int result = new MemberDao().updateMember(conn, member);

		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.close(conn);

		return result;
	}

	public int deleteMember(int memberNo) {
		Connection conn = JDBCTemplate.getConnection();

		int result = new MemberDao().deleteMember(conn, memberNo);

		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.close(conn);

		return result;
	}

	public Member findId(String memberName, String phone) {
		Connection conn = JDBCTemplate.getConnection();

		Member member = new MemberDao().findId(conn, memberName, phone);

		JDBCTemplate.close(conn);

		return member;
	}

	public Member findPw(String memberId, String phone) {
		Connection conn = JDBCTemplate.getConnection();

		Member member = new MemberDao().findPW(conn, memberId, phone);

		JDBCTemplate.close(conn);

		return member;
	}

	public ArrayList<Member> selectAllMember() {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Member> members = new MemberDao().selectAllMember(conn);
		
		JDBCTemplate.close(conn);
		
		return members;
	}

	public int changeLevel(int memberNo, int memberLevel) {
		Connection conn = JDBCTemplate.getConnection();
		
		int r = new MemberDao().changeLevel(conn, memberNo, memberLevel);
		
		if(r>0) {
			JDBCTemplate.commit(conn);
			
		}else {
			JDBCTemplate.rollback(conn);
			
		}
		
		JDBCTemplate.close(conn);
		
		return r;
	}

}
