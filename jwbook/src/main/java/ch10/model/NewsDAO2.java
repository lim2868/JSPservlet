package ch10.model;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import ch10.mybatis.NewsMapper;

public class NewsDAO2 {
	
	final private String resource = "ch10/mybatis/mybatis-config.xml";
	InputStream inputStream;
	SqlSessionFactoryBuilder builder;
	SqlSessionFactory sqlSessionFactory;
	SqlSession sqlSession;
	NewsMapper newsMapper;
	
	public NewsDAO2() {
		try {
			inputStream = Resources.getResourceAsStream(resource);
			builder = new SqlSessionFactoryBuilder();
			sqlSessionFactory = builder.build(inputStream);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public SqlSession open() {
		return sqlSessionFactory.openSession(true);
	}
	public void close(SqlSession s) {
		s.close();
	}
	
	public void addNews(NewsDTO n){
		sqlSession = open();
		newsMapper = sqlSession.getMapper(NewsMapper.class);
		newsMapper.addNews(n);
	}
	public List<NewsDTO> getAll(){
		sqlSession = open();
		newsMapper = sqlSession.getMapper(NewsMapper.class);
		return newsMapper.getAllNews();
	}
	
	public NewsDTO getNews(int aid){
		sqlSession = open();
		newsMapper = sqlSession.getMapper(NewsMapper.class);
		return newsMapper.getOneNews(aid);
	}
	public void delNews(int aid) throws SQLException{
		sqlSession = open();
		newsMapper = sqlSession.getMapper(NewsMapper.class);
		newsMapper.delete(aid);
	}
}