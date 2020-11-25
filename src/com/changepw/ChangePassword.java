package com.changepw;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.Dao;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChangePassword() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = (String)request.getSession().getAttribute("UserID");
		String oldPassword = request.getParameter("old_password");
		String newPassword = request.getParameter("new_password");
		HttpSession session = request.getSession();
		
		Dao dao = new Dao();
		if(dao.checkIdAndPassword(id, oldPassword)) {
			dao.setPassword(id,newPassword);
			response.sendRedirect("login.jsp");
			session.removeAttribute("WrongPW");
		}
		else {
			session.setAttribute("WrongPW",true);
			response.sendRedirect("changepw.jsp");
		}
	}

}
