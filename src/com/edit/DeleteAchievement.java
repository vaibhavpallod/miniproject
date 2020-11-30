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
 * Servlet implementation class DeleteAchievement
 */
@WebServlet("/DeleteAchievement")
public class DeleteAchievement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAchievement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("a : "+request.getParameter("ach-id"));
		int achievementId = Integer.parseInt(request.getParameter("ach-id"));
		System.out.println("b");
		Dao dao = new Dao();
		dao.deleteAchievement(achievementId);
		HttpSession session = request.getSession();
		session.setAttribute("User", dao.getUser(session.getAttribute("UserID").toString()));
		response.sendRedirect("editprofile.jsp");
	}

}
