package ch12;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch10.model.NewsDAO;
import ch10.model.NewsDTO;

@Path("/news")
public class NewsApiService {
	
	NewsDAO dao;
	
	public NewsApiService() {
		dao = new NewsDAO();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String addNews(NewsDTO news) {
		try {
			dao.addNews(news);
		} catch(Exception e) {
			e.printStackTrace();
			return "News API : 뉴스 등록 실패";
		}
		return "News API : 뉴스 등록됨";
	}
	
	@DELETE
	@Path("{aid}")
	public String delNews(@PathParam("aid") int aid) {
		try {
			dao.delNews(aid);
		} catch(SQLException e) {
			e.printStackTrace();
			return "News API : 뉴스 삭제 실패 - " + aid;
		}
		return "News API : 뉴스 삭제됨 - " + aid;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<NewsDTO> getNewsList(){
		List<NewsDTO> newsList = null;
		try {
			newsList = dao.getAll();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return newsList;
	}
	
	@GET
	@Path("{aid}")
	@Produces(MediaType.APPLICATION_JSON)
	public NewsDTO getNews(@PathParam("aid") int aid) {
		NewsDTO news = null;
		try {
			news = dao.getNews(aid);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return news;
	}
}