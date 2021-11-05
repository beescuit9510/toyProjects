
package kh.model.dao;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import kh.model.vo.Kh;

public class Dao {

	public Kh selectOneKh(String khName) {

		Connection conn = null;

		PreparedStatement pstmt = null;

		ResultSet rset = null;

		String query = "select * from kh_tbl where kh_name=?";

		Kh kh = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "examuser", "1234");

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, khName);

			rset = pstmt.executeQuery();

			if (rset.next()) {

				kh = new Kh();

				kh.setKhNo(rset.getInt("kh_no"));

				kh.setKhName(rset.getString("kh_name"));

				kh.setAddr(rset.getString("addr"));

				kh.setFax(rset.getString("fax"));

			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			try {

				rset.close();

				pstmt.close();

				conn.close();

			} catch (SQLException e) {

				e.printStackTrace();

			}

		}

		return kh;

	}

}
