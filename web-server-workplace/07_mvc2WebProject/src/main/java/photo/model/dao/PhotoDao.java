package photo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import photo.model.vo.Photo;

public class PhotoDao {

	public int insertPhoto(Connection conn, Photo photo) {
		String query = "insert into photo values(photo_seq.nextval,?,?,to_char(sysdate,'yyyy-mm-dd'),?)";

		int r = -1;

		try (PreparedStatement pstmt = conn.prepareStatement(query);) {
			int i = 1;
			pstmt.setString(i++, photo.getPhotoWriter());
			pstmt.setString(i++, photo.getPhotoComment());
			pstmt.setString(i++, photo.getFilepath());

			r = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return r;
	}

	public int selectTotalCount(Connection conn) {
		String query = "select count(*) from photo";
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		int totalCount = 0;

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			totalCount = rset.next()? rset.getInt(1):0;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return totalCount;
	}

	public ArrayList<Photo> selectMorePhoto(Connection conn, int start, int end) {
		String query = "select * from (select rownum as rnum, p.* from (select * from photo order by 1 desc)p) where rnum between ? and ?";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Photo> photos = new ArrayList<Photo>();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				int i = 2;
				photos.add(new Photo(rset.getInt(i++),rset.getString(i++),rset.getString(i++),rset.getString(i++),rset.getString(i++)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return photos;
	}

}
