package notice.model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import notice.model.vo.Notice;

public class NoticeDao {

	public ArrayList<Notice> selectNoticeList(SqlSession session, Map<String, Integer> map) {
		List<Notice> list = session.selectList("selectList", map);
		return (ArrayList<Notice>)list;
	}

	public int totalCount(SqlSession session) {
		return session.selectOne("notice.totalCount");
	}

}
