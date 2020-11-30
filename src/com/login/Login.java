package com.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ConnectionProvider;
import com.dao.Dao;
import com.updateDB.UpdateDatabase;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("st_id");
		String password = request.getParameter("st_pw");
		HttpSession session = request.getSession();
		System.out.println(new Timestamp(System.currentTimeMillis()));
		Dao dao = new Dao();
		if (dao.checkIdAndPassword(id, password)) {
			UpdateDatabase.checkdabase();
//			UpdateDatabase.checkFunction();
			if (UpdateDatabase.Checktables()) {
				session.setAttribute("User", dao.getUser(id));
				session.setAttribute("UserID", id);
				response.sendRedirect("profile_achievements.jsp");
				session.removeAttribute("WrongCredentials");
			} 

		} else {
			session.setAttribute("WrongCredentials", true);
			response.sendRedirect("login.jsp");
		}
	}

}
