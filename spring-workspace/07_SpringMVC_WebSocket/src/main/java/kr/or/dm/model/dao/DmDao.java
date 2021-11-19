package kr.or.dm.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.dm.model.vo.DirectMessage;

@Repository
public class DmDao {
	@Autowired
	private SqlSessionTemplate sqlSession;

	public ArrayList<DirectMessage> selectDmList(String memberId) {
		List<DirectMessage> list = sqlSession.selectList("direct.selectDmList", memberId);
		return (ArrayList<DirectMessage>)list;
	}

	public int insertDm(DirectMessage dm) {
		return sqlSession.insert("direct.insertDm", dm);
	}

	public int dmCount(String memberId) {
		return sqlSession.selectOne("direct.dmCount",memberId);
	}
}
