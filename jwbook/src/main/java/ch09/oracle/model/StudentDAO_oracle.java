package ch09.oracle.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StudentDAO_oracle {
	
	Connection conn = null;
	PreparedStatement pstmt;
	
	final String JDBC_DRIVER ="oracle.jdbc.driver.OracleDriver";
	final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	final String JDBC_ID = "SCOTT";
	final String JDBC_PASSWD = "tiger";
	
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
	public void insert(StudentDO_oracle s) {
		this.open();
		String sql = "insert into student values(seq_student.nextval,?,?,?,?)";
		
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
	public void delete(StudentDO_oracle s) {
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
	public List<StudentDO_oracle> getAll(){
		this.open();
		List<StudentDO_oracle> students = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement("select * from student");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				StudentDO_oracle s = new StudentDO_oracle();
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