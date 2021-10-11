package photo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
