package com.edit;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.Dao;

/**
 * Servlet implementation class DeleteInternship
 */
@WebServlet("/DeleteInternship")
public class DeleteInternship extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteInternship() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int internshipId = Integer.parseInt(request.getParameter("int-id"));
		Dao dao = new Dao();
		dao.deleteInternship(internshipId);
		HttpSession session = request.getSession();
		session.setAttribute("User", dao.getUser(session.getAttribute("UserID").toString()));
		response.sendRedirect("editprofile.jsp");
	}

}
