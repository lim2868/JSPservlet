package edu.kh.test.user.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import edu.kh.test.user.model.vo.UserDTO;

public class UserDAO {
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	private final String driver = "oracle.jdbc.driver.OracleDriver";
	private final String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private final String user = "scott";
	private final String password = "tiger";
	
	public void open() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,password);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("데이터베이스 연결에 문제가 발생하였습니다.");
		}
	}
	
	public void close() {
		try {
			conn.close();
			pstmt.close();
			rs.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public UserDTO select(int userNo) {
		this.open();
		UserDTO user = new UserDTO();
		String sql = "SELECT * FROM tb_user where user_no=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				user.setUserNo(rs.getInt("user_no"));
				user.setUserId(rs.getString("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setUserAge(rs.getInt("user_age"));
			} else {
				user = null;
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("select문 수행 불가");
		} finally {
			close();
		}
		
		return user;
	}
}