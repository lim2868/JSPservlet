package edu.kh.test.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.test.user.model.dao.UserDAO;
import edu.kh.test.user.model.vo.UserDTO;

/**
 * Servlet implementation class SelectUserServlet
 */
@WebServlet("/user")
public class SelectUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String startPage = "WEB-INF/views/index.jsp";
	private UserDAO userDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String view = startPage;
		
		userDAO = new UserDAO();
		
		RequestDispatcher rd = null;
		
		String param = request.getParameter("userNo");
		
		if(param == null) {
			rd = request.getRequestDispatcher(view);
		} else {
			UserDTO userDTO = userDAO.select(Integer.parseInt(param));
			request.setAttribute("User", userDTO);
			
			if (userDTO != null) {
				view = "WEB-INF/views/searchSuccess.jsp";
			} else {
				view = "WEB-INF/views/searchFail.jsp";
			}
		
		}
		
		rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

}
