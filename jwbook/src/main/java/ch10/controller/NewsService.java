package ch10.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import ch10.model.NewsDAO;
import ch10.model.NewsDTO;

public class NewsService {
	
	private NewsDAO dao;
	private NewsDTO n;
	
	public NewsService() {
		dao = new NewsDAO();
	}
	
	
	public String getNews(HttpServletRequest request) {
		int aid = Integer.parseInt(request.getParameter("aid"));
		try {
			n = dao.getNews(aid);
			request.setAttribute("news",n);
		} catch(SQLException e) {
			e.printStackTrace();
			System.err.println("뉴스를 가져오는 과정에서 문제 발생");
			request.setAttribute("error", "뉴스를 정상적으로 가져오지 못했습니다.");
		}
		return "ch10/view/newsView.jsp";
	}
	public String deleteNews(HttpServletRequest request) {
		int aid = Integer.parseInt(request.getParameter("aid"));
		try {
			dao.delNews(aid);
		} catch(SQLException e) {
			e.printStackTrace();
			System.err.println("뉴스 삭제 과정에서 문제 발생");
			request.setAttribute("error", "뉴스가 정상적으로 삭제되지 않았습니다.");
			return "error";
		}
		return "redirect:/news.nhn?action=listNews";
	}
}
