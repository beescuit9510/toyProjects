package photo.model.service;

import java.sql.Connection;
import java.util.ArrayList;

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

	public ArrayList<Photo> morePhoto(int start) {
		Connection conn = JDBCTemplate.getConnection();
		
		int length = 5; //한번에 추가로 가지고  올 게시물 수;
		
		int end = start+length-1;

		ArrayList<Photo> photos = new PhotoDao().selectMorePhoto(conn, start, end);
		
		JDBCTemplate.close(conn);
		
		return photos;
	}

}
