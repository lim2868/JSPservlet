package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import repository.BookDTO;

public interface BookServiceImpl {
	
	public List<BookDTO> getAll();
	
	public void register(HttpServletRequest request, HttpServletResponse response);
	
	public void delete(HttpServletRequest request, HttpServletResponse response);
}
