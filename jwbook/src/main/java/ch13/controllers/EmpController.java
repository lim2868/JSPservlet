package ch13.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch13.repositories.EmpDTO;
import ch13.services.EmpService;
import ch13.services.EmpServiceImpl;

/**
 * Servlet implementation class EmpController
 */
@WebServlet("/emp")
public class EmpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final String startPage ="ch13/view/index.jsp";
	private EmpService empService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String view = startPage;
		empService = new EmpServiceImpl();
		
		RequestDispatcher rd = null;
		
		String param = request.getParameter("empNo");
		
		if(param == null) {
			rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		} else {
			EmpDTO empDTO = empService.searchForEmp(Integer.parseInt(param));
			request.setAttribute("Employee", empDTO);
			
			if(empDTO != null) {
				view = "ch13/view/ok.jsp";
			} else {
				view = "ch13/view/no.jsp";
			}
			rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		}
	}
}