package login.service;



import java.sql.Connection;

import common.JDBCTemplate;
import login.dao.LoginDao;
import table.model.vo.Member;

public class LoginService {

	public Member selectOneMember(String loginEmail, String pw) {
		Connection conn = JDBCTemplate.getConnection();
		Member member =new LoginDao().selectOneMember(conn,loginEmail,pw);
		JDBCTemplate.close(conn);
		return member;
	}

}
