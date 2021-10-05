package notice.model.service;

import java.sql.Connection;

import common.JDBCTemplate;
import notice.model.dao.NoticeDao;
import notice.model.vo.Notice;

public class NoticeService {

	public int insertNotice(Notice notice) {
		Connection conn = JDBCTemplate.getConnection();
		
		int r = new NoticeDao().insertNotice(conn, notice);
		
		if(r > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return r;
	}

}
