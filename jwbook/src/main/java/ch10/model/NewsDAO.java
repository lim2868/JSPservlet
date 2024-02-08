package ch10.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsDAO {
	
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/jwbookdb";
	
	public Connection open() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL,"jwbook","1234");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public void addNews(NewsDTO n) throws Exception{
		Connection conn = open();
		
		String sql ="insert into news(title,img,date,content) values(?,?,CURRENT_TIMESTAMP(),?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		try(conn;pstmt){
			pstmt.setString(1, n.getTitle());
			pstmt.setString(2, n.getImg());
			pstmt.setString(3, n.getContent());
			pstmt.executeUpdate();
		}
	}
	public List<NewsDTO> getAll() throws Exception{
		Connection conn = open();
		List<NewsDTO> newsList = new ArrayList<>();
		
		String sql = "select aid, title, FORMATDATETIME(date, 'yyyy-mm-dd hh:mm:ss') as cdate from news"; // formatdatetime
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		try(conn; pstmt; rs){
			while(rs.next()) {
				NewsDTO n = new NewsDTO();
				n.setAid(rs.getInt("aid"));
				n.setTitle(rs.getString("title"));
				n.setDate(rs.getString("cdate"));
				newsList.add(n);
			}
			return newsList;
		}
	}
	
	public NewsDTO getNews(int aid) throws SQLException{
		Connection conn = open();
		NewsDTO n = new NewsDTO();
		String sql = "select aid, title, img, FORMATDATETIME(date,'yyyy-mm-dd hh:mm:ss') as cdate, content from news where aid=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, aid);
		ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		
		try(conn;pstmt;rs){
			n.setAid(rs.getInt("aid"));
			n.setTitle(rs.getString("title"));
			n.setImg(rs.getString("img"));
			n.setDate(rs.getString("cdate"));
			n.setContent(rs.getString("content"));
			// pstmt.executeQuery(); 책에 나와있지만 오류라함
			return n;
		}
	}
	public void delNews(int aid) throws SQLException{
		Connection conn = open();
		
		String sql = "delete from news where aid=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, aid);
		
		try(conn;pstmt){
			if(pstmt.executeUpdate() == 0) {
				throw new SQLException("DB에러");
			}
		}
	}
}