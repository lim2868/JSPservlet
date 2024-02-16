package ch10.mybatis;

import java.util.List;

import ch10.model.NewsDTO;

public interface NewsMapper {
	
	public void addNews(NewsDTO n);
	
	public List<NewsDTO> getAllNews();
	
	public NewsDTO getOneNews(int aid);
	
	public void delete(int aid);
}
