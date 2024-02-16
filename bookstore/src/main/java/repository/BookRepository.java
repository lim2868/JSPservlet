package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookRepository implements BookRepositoryImpl{
	
	Connection conn = null;
	PreparedStatement pstmt;
	ResultSet rs = null;
	
	final String driver ="oracle.jdbc.driver.OracleDriver";
	final String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	final String user = "scott";
	final String password = "tiger"; 
	
	public void open() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("데이터베이스 연결에 실패했습니다.");
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
	
	public List<BookDTO> getAll(){
		this.open();
		List<BookDTO> books = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement("SELECT * FROM book");
			rs = pstmt.executeQuery();
		
		
		while(rs.next()) {
			BookDTO b = new BookDTO();
			b.setBookNo(rs.getInt("bookno"));
			b.setBookName(rs.getString("bookname"));
			b.setAuthor(rs.getString("author"));
			b.setPrice(rs.getInt("price"));
			
			books.add(b);
		}
	} catch(Exception e) {
		e.printStackTrace();
	} finally {
		close();
	}
		return books;
	}
	
	public void register(BookDTO b) {
		this.open();
		String sql = "INSERT INTO BOOK VALUES(book_seq.nextval, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getBookName());
			pstmt.setString(2, b.getAuthor());
			pstmt.setInt(3, b.getPrice());
			
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public void delete(BookDTO b) {
		this.open();
		String sql = "DELETE FROM book WHERE bookno=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getBookNo());
			pstmt.executeUpdate();
	} catch(Exception e) {
		e.printStackTrace();
	} finally {
		close();
	}
	
	}
}