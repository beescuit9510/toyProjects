package login.service;



import java.sql.Connection;

import common.JDBCTemplate;
import login.dao.LoginDao;
import table.model.vo.Member;

public class LoginService {

	public Member selectOneMember(String loginEmail, String pw) {
		Connection conn = JDBCTemplate.getConnection();
		Member member =new LoginDao().selectOneMember(conn,loginEmail,pw);
		if(member.getcLevel()==3) {
			int busiNo = member.getcMemberNo();
			new LoginDao().BusinessMember(conn,busiNo,member);
		}
		JDBCTemplate.close(conn);
		return member;
	}
	public Member selectOneId(String loginEmail, String pw) {
		Connection conn = JDBCTemplate.getConnection();
		Member m =new LoginDao().selectOneMember(conn,loginEmail,pw);
		JDBCTemplate.close(conn);
		return m;
	}


}
