package ch09.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
	
	Connection conn = null;
	PreparedStatement pstmt;
	
	final String JDBC_DRIVER ="org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/jwbookdb";
	final String JDBC_ID = "jwbook";
	final String JDBC_PASSWD = "1234";
	
	public void open() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL,JDBC_ID,JDBC_PASSWD);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			pstmt.close();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void insert(StudentDO s) {
		this.open();
		String sql = "INSERT INTO student(username, univ, birth, email) values(?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s.getUsername());
			pstmt.setString(2,  s.getUniv());
			pstmt.setDate(3, s.getBirth());
			pstmt.setString(4, s.getEmail());
			
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	// delete 첫번째 방법
	public void delete(StudentDO s) {
		this.open();
		String sql = "DELETE FROM student " + " where id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, s.getId());
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	public List<StudentDO> getAll(){
		this.open();
		List<StudentDO> students = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement("select * from student");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				StudentDO s = new StudentDO();
				s.setId(rs.getInt("id"));
				s.setUsername(rs.getString("username"));
				s.setUniv(rs.getString("univ"));
				s.setBirth(rs.getDate("birth"));
				s.setEmail(rs.getString("email"));
				
				students.add(s);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return students;
	}
}