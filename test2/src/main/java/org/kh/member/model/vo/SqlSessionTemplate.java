package org.kh.member.model.vo;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.kh.member.mapper.MemberMapper;


public class SqlSessionTemplate {

	private final String resource = "data/mybatis-config.xml";
	private InputStream is;
	private SqlSessionFactoryBuilder builder;
	private SqlSessionFactory sf;
	private SqlSession ss;
	private MemberMapper mm;
	
	public SqlSessionTemplate() {
		try {
			is = Resources.getResourceAsStream(resource);
			builder = new SqlSessionFactoryBuilder();
			sf = builder.build(is);
			ss = sf.openSession(true);
			mm = ss.getMapper(MemberMapper.class);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public MemberVO insertMember(int userNo) {
		return mm.insertMember(userNo);
	}
}
