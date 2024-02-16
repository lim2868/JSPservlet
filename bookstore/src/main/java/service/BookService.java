package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import repository.BookDTO;
import repository.BookRepository;

public class BookService implements BookServiceImpl{
	
	private BookRepository repo;
	
	public BookService() {
		repo = new BookRepository();
	}
	
	public List<BookDTO> getAll(){
		return repo.getAll();
	}
	
	public void register(HttpServletRequest request, HttpServletResponse response) {
		BookDTO b = new BookDTO();
		try {
			BeanUtils.populate(b,request.getParameterMap());
		} catch(Exception e) {
			e.printStackTrace();
		}
		repo.register(b);
	}
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		BookDTO b = new BookDTO();
		try {
			BeanUtils.populate(b, request.getParameterMap());
		} catch(Exception e) {
			e.printStackTrace();
		}
		repo.delete(b);
	}
}
