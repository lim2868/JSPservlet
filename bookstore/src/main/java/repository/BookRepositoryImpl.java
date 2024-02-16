package repository;

import java.util.List;

public interface BookRepositoryImpl {
	
	public List<BookDTO> getAll();
	
	public void register(BookDTO b);
	
	public void delete(BookDTO b);
}
