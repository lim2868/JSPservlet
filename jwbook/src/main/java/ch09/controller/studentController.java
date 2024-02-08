package ch09.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch09.service.StudentService;

/**
 * Servlet implementation class studentController
 */
@WebServlet("/studentControl")
public class studentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
//	StudentDAO dao;
	private StudentService ss;
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		
//		dao = new StudentDAO();
		ss = new StudentService();
	}
    public studentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");// 한국어로 패치
		String action = request.getParameter("action");
		String view = "";
		if(request.getParameter("action") == null) {
			getServletContext().getRequestDispatcher("/studentControl?action=list").forward(request,response);
		} else {
			switch(action) {
			case "list" : view = list(request, response); break;
			case "insert" : view = insert(request, response); break;
			case "delete" : view = delete(request, response); break;
			}
			
			getServletContext().getRequestDispatcher("/ch09/view/studentInfo.jsp" ).forward(request, response);
		}
	}
	public String list(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("students",ss.getAll()); //list가 실행되면 getAll()을 실행한다
		return "studentInfo.jsp";
	}
	
	public String insert(HttpServletRequest request, HttpServletResponse response) {
		ss.insert(request, response);
		return list(request,response);
	}
	public String delete(HttpServletRequest request, HttpServletResponse response) {
		ss.delete(request, response);
		return list(request,response);
	}
}