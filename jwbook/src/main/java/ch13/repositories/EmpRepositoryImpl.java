package ch13.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmpRepositoryImpl implements EmpRepository{
	
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public EmpRepositoryImpl() {
		super();
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "employee";
		String password = "tiger";
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,password);
		} catch(Exception e) {
			System.out.println("데이터베이스 연결에 문제가 발생하였습니다.");
		}
	}
	
	@Override
	public EmpDTO selectEmp(int empNo) {
		EmpDTO emp = new EmpDTO();
		String query = "SELECT * FROM emp where empno=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, empNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				emp.setEmpNo(rs.getInt("empno"));
				emp.setEmpName(rs.getString("empname"));
				emp.setAge(rs.getInt("age"));
				emp.setDepartName(rs.getString("departname"));
			} else {
				emp = null;
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("select문 수행중 예외가 발생");
		}
		return emp;
	}
}