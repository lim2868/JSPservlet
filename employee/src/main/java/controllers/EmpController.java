package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import repositories.EmpDTO;
import services.EmpService;
import services.EmpServiceImpl;

/**
 * Servlet implementation class EmpController
 */
@WebServlet("/emp")
public class EmpController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String startPage = "index.jsp";
	private EmpService empService;

	public EmpController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String view = startPage;
		empService = new EmpServiceImpl();

		RequestDispatcher rd = null;

		String param = request.getParameter("empNo");

		if (param == null) {
			rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		} else {
			EmpDTO empDTO = empService.searchForEmp(Integer.parseInt(param));
			request.setAttribute("Employee", empDTO);

			if (empDTO != null) {
				view = "ok.jsp";
			} else {
				view = "no.jsp";
			}
			rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		}
	}
}