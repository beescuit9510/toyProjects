package join.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import join.dao.JoinDao;
import table.model.vo.Business;
import table.model.vo.Member;

public class JoinService {

	public int selectOneId(String joinId) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new JoinDao().selectOneId(conn,joinId);
		JDBCTemplate.close(conn);
		return result;
	}

	public int insertMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new JoinDao().insertMember(conn,m);
		if(result > 0) {
			//가장 최근 회원번호 조회   -> select max(member_no) as member_no from member;
			result = new JoinDao().selectOneNo(conn,m);
			JDBCTemplate.commit(conn);
			
		}else {
			JDBCTemplate.rollback(conn);
		}JDBCTemplate.close(conn);
		return result;
	}

	public int insertBusiness(Member m, Business b) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new JoinDao().insertMember(conn,m);
		int memNo = 0;
		if(result>0) {
			memNo = new JoinDao().selectOneNo(conn,m);
			result = new JoinDao().insertBusiness(conn, b,memNo);
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		}else {
			JDBCTemplate.rollback(conn);
		}JDBCTemplate.close(conn);
		return result;
	}

}
