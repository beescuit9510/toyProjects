package photo.model.service;

import java.sql.Connection;

import common.JDBCTemplate;
import photo.model.dao.PhotoDao;
import photo.model.vo.Photo;

public class PhotoService {

	public int insertPhoto(Photo photo) {
		Connection conn = JDBCTemplate.getConnection();

		int r = new PhotoDao().insertPhoto(conn, photo);

		if (r > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.close(conn);

		return r;
	}
	
	public int selectTotalCount() {
		Connection conn = JDBCTemplate.getConnection();
		
		int totalCount = new PhotoDao().selectTotalCount(conn);
				
		JDBCTemplate.close(conn);
		
		return totalCount;
	}

}
