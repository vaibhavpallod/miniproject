package com.save;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.Dao;
import com.user.User;

/**
 * Servlet implementation class SaveChanges
 */
@WebServlet("/SaveChanges")
public class SaveChanges extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveChanges() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("edit_name");
		String bio = request.getParameter("edit_bio");
		String email = request.getParameter("edit_email");
		String mobile = request.getParameter("edit_contact");
		
		System.out.println(name+" "+bio+" "+email+" "+mobile);
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("User");
		user.setName(name);
		user.setBio(bio);
		user.setEmail(email);
		user.setContact(mobile);
		Dao dao = new Dao();
		dao.updateDetails(user);
		
		session.setAttribute("User", dao.getUser(session.getAttribute("UserID").toString()));
		response.sendRedirect("editprofile.jsp");
		
	}

}
