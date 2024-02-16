package ch09.model;


import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import ch09.mybatis.StudentMapper;

public class StudentDAO {
	final private String resource = "ch09/mybatis/mybatis-config.xml";
	InputStream inputStream;
	SqlSessionFactoryBuilder builder;
	SqlSessionFactory sqlSessionFactory;
	public StudentDAO() {
		try {
			inputStream = Resources.getResourceAsStream(resource);
			builder = new SqlSessionFactoryBuilder();
			sqlSessionFactory = builder.build(inputStream);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public SqlSession open() {
		return sqlSessionFactory.openSession(true);
	}
	public void close(SqlSession s) {
		s.close();
	}
	public void insert(StudentDO s) {
		this.open().getMapper(StudentMapper.class).insert(s);
	}
	public List<StudentDO> getAll(){
		return this.open().getMapper(StudentMapper.class).getAll();
	}
	public void deleteStudent(String id) {
		this.open().getMapper(StudentMapper.class).delete(id);
	}
}
