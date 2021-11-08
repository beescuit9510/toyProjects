package member.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import member.model.vo.Member;

public class MemberDao {

	public Member selectOneMember(SqlSession session, Member temptMember) {
		//로그인은 select 쿼리문 수행 -> 수행결과는 없거나 무조건 1명 정보 -> 사용메소드 selectOne
		//매개변수 1 -> 어떤 mapper에서 어떤 쿼리문을 수행할지, 쿼리문 수행에 필요한 값 전달
		Member member = session.selectOne("member.selectOneMember",temptMember);		
		return member;
	}

	public ArrayList<Member> selectAllMember(SqlSession session) {
		//전체회원 조회 select 쿼리문 수행 -> 수행결과는 여러 row -> 사용하는 메소드 selectList
		//전달해줄 데이터가 없는 경우 생략		
		List<Member> list = session.selectList("member.selectAllMember");
		
		ArrayList<Member> members = (ArrayList<Member>)list;
		return members;
	}

	public int insertMember(SqlSession session, Member temptMember) {
		int r = session.insert("member.insertMember", temptMember);
		return r;
	}

	public int deleteMember(SqlSession session, String memberId) {
		int r = session.delete("member.deleteMember",memberId);
		return r;
	}

	public int updateMember(SqlSession session, Member temptMember) {
		int r = session.update("member.updateMember",temptMember);
		return r;
	}

}
