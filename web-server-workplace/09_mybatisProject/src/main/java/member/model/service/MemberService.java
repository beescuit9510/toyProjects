package member.model.service;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import member.model.dao.MemberDao;
import member.model.vo.Member;

public class MemberService {
	
	private SqlSession getSqlSession() {
		SqlSession session = null;
		String resource = "/mybatis-config.xml";//마이바티스 설정파일 경로;
		try {
			InputStream is = Resources.getResourceAsStream(resource);
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			SqlSessionFactory factory = builder.build(is);
			session = factory.openSession(false); //오토커밋 해제를 위해 매개변수 false 전달;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return session;
	}
	
	
	public Member selectOneMember(Member temptMember) {
		
		//Connection 대신 SqlSession을 사용한다;
		SqlSession session = getSqlSession();
		Member member = new MemberDao().selectOneMember(session, temptMember);
		
		session.close();

		return member;
	}


	public ArrayList<Member> selectAllMember() {
		SqlSession session = getSqlSession();
		ArrayList<Member> members = new MemberDao().selectAllMember(session);
		
		session.close();
		
		return members;
	}


	public int insertMember(Member temptMember) {
		SqlSession session = getSqlSession();
		int r = new MemberDao().insertMember(session, temptMember);
		
		if(r > 0) {
			session.commit();
		}else {
			session.rollback();
		}
		session.close();
		
		return r;
	}


	public int deleteMember(String memberId) {
		SqlSession session = getSqlSession();
		int r = new MemberDao().deleteMember(session,memberId);
		
		if(r > 0) {
			session.commit();			
		}else {
			session.rollback();						
		}
		
		session.close();
		return r;
	}


	public int updateMember(Member temptMember) {
		SqlSession session = getSqlSession();
		int r = new MemberDao().updateMember(session, temptMember);
		
		if(r > 0) {
			session.commit();
		}else {
			session.rollback();			
		}
		
		return r;
	}


	public Member selectOneMember(int memberNo) {
		SqlSession session = getSqlSession();
		Member member = new MemberDao().selectOneMember(session, memberNo);
		
		session.close();

		return member;
	}

}
