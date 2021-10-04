package member.model.service;

import java.sql.Connection;

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
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
//		
		JDBCTemplate.close(conn);
//				
		return result;
//	}

}


}
