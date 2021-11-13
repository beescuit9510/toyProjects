package kr.or.board.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class FileVORowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		FileVO file = new FileVO();
		
		file.setFileNo(rs.getInt("file_no"));
		file.setBoardNo(rs.getInt("board_no"));
		file.setFilename(rs.getString("filename"));
		file.setFilepath(rs.getString("filepath"));

		return file;
	}

}
