package com.iei.dao;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.SQLException;

import com.iei.vo.Member;

public class MemberDao {

	public int insertMember(Member member) {

		Connection conn = null;

		PreparedStatement pstmt = null;

		String query = "insert into member_tbl values(?,?,?)";

		int result = 0;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "examuser", "1234");

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, member.getMemberId());

			pstmt.setString(2, member.getMemberPw());

			pstmt.setString(3, member.getMemberName());

			if (result > 0) {

				conn.commit();

			} else {

				conn.rollback();

			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			try {

				pstmt.close();

				conn.close();

			} catch (SQLException e) {

				e.printStackTrace();

			}

		}

		return result;

	}

}