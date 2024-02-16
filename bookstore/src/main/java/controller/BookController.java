package controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BookService;

/**
 * Servlet implementation class BookController
 */
@WebServlet("/book")
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     	
		private BookService bookService;
    /**
     * @see HttpServlet#HttpServlet()
     */
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		
		
		bookService = new BookService();
	}
    public BookController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		String view = "";
		
		if(request.getParameter("action") == null) {
			getServletContext().getRequestDispatcher("/book?action=list").forward(request, response);
		} else {
			switch(action) {
			case "list" : view = list(request, response); break;
			case "register" : view = register(request, response); break;
			case "delete" : view = delete(request, response); break;
			}
		}
		
		getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}
	
	public String list(HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("books",bookService.getAll());
		return "index.jsp";
	}
	public String register(HttpServletRequest request, HttpServletResponse response){
		bookService.register(request, response);
		return list(request, response);
	}
	public String delete(HttpServletRequest request, HttpServletResponse response){
		bookService.delete(request, response);
		return list(request, response);
	}
}
