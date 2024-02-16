package repositories;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import data.EmpMapper;

public class EmpRepositoryImpl implements EmpRepository{

//	private Connection conn = null;
//	private PreparedStatement pstmt = null;
//	private ResultSet rs = null;
//	
//	public EmpRepositoryImpl() {
//		super();
//		String driver = "oracle.jdbc.driver.OracleDriver";
//		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
//		String user = "employee";
//		String password = "tiger";
//		try {
//			Class.forName(driver);
//			conn = DriverManager.getConnection(url,user,password);
//		}catch(Exception e) {
//			e.printStackTrace();
//			System.out.println("데이터베이스 연결에 문제가 발생하였습니다.");
//		}
//	}
//	@Override
//	public EmpDTO selectEmp(int empNo) {
//		EmpDTO emp = new EmpDTO();
//		String query = "SELECT * FROM Emp where empno=?";
//		try {
//			pstmt = conn.prepareStatement(query);
//			pstmt.setInt(1, empNo);
//			rs = pstmt.executeQuery();
//			if(rs.next()) {
//				emp.setEmpNo(rs.getInt("empno"));
//				emp.setEmpName(rs.getString("empname"));
//				emp.setAge(rs.getInt("age"));
//				emp.setDepartName(rs.getString("department"));
//			}else {
//				emp = null;
//			}
//			rs.close();
//			pstmt.close();
//			conn.close();
//		}catch(Exception e) {
//			e.printStackTrace();
//			System.out.println("select문 수행중 예외가 발생");
//		}
//		return emp;
//	}
// ------------------------------------------------------------------------------
// mybatis 적용
	
	private final String resource = "data/mybatis-config.xml";
	private InputStream is;
	private SqlSessionFactoryBuilder builder;
	private SqlSessionFactory sf;
	private SqlSession ss;
	private EmpMapper em;
	
	public EmpRepositoryImpl() {
		super();
		try {
			is = Resources.getResourceAsStream(resource);
			builder = new SqlSessionFactoryBuilder();
			sf = builder.build(is);
			ss = sf.openSession(true);
			em = ss.getMapper(EmpMapper.class);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public EmpDTO selectEmp(int empNo) {
		return em.getEmp(empNo);
	}
}