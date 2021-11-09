package member.model.service;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import member.controller.PwSearchServlet;
import member.model.dao.MemberDao;
import member.model.vo.Member;
import member.model.vo.PwSearchVO;

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
		
		session.close();
		
		return r;
	}


	public Member selectOneMember(int memberNo) {
		SqlSession session = getSqlSession();
		Member member = new MemberDao().selectOneMember(session, memberNo);
		
		session.close();

		return member;
	}


	public String searchId(String memberName, String phone) {
		SqlSession session = getSqlSession();
		//mybatis는 여러값을 전달해야할때 1개의 객체로 전달
		//1) 데이터 전송용 vo생성(memberName,phone 변수 2개짜리 vo)
		//2) Member타입사용 (memberName,phone 2개 변수만 사용)
		//3) Map 사용
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("memberName", memberName);
		map.put("phone", phone);
		
		String memberId = new MemberDao().searchId(session, map);
//		String memberId = new MemberDao().searchId(session, memberName, phone);
		
		session.close();

		return memberId;
	}


	public String searchPw(String memberId, String phone) {
		SqlSession session = getSqlSession();
		String memberPw = new MemberDao().searchPw(session, new PwSearchVO(memberId,phone) );
		session.close();
		return memberPw;
	}


	public ArrayList<Member> ifTest(String ckName, String ckPhone, String ckAddress) {
		SqlSession session = getSqlSession();
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("ckName",ckName);
		map.put("chPhone",ckPhone);
		map.put("ckAddress",ckAddress);
		
//		ArrayList<Member> list = new MemberDao()
//				.ifTest(session, Map.of("ckName",ckName,"chPhone",ckPhone,"ckAddress",ckAddress));
		ArrayList<Member> list = new MemberDao()
				.ifTest(session, map);
		
		session.close();
		return list;
	}


	public ArrayList<Member> chooseTest(String type, String keyword) {
		SqlSession session = getSqlSession();
		
		if(type==null || keyword== null) {
			return null;
		}
		
		ArrayList<Member> list = new MemberDao().chooseTest(session, Map.of("type",type,"keyword",keyword));
		
		session.close();
		
		return list;
	}


	public ArrayList<Member> trimTest(String memberName, String address) {
		SqlSession session = getSqlSession();
				
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		map.put("memberName",memberName);
//		map.put("address",address);

		ArrayList<Member> list = new MemberDao()
				.trimTest(session, Map.of("memberName",memberName,"address",address));
		
		session.close();
		
		return list;
	}


	public ArrayList<Member> foreachTest(String[] memberIds) {
		SqlSession session = getSqlSession();
		ArrayList<Member> list = new MemberDao().foreachTest(session, memberIds);

		session.close();		
		return list;
	}

}
