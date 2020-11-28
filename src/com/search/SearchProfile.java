package com.search;

import java.io.*;

import java.sql.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ConnectionProvider;
/**
 * Servlet implementation class SearchProfile
 */
@WebServlet(description = "To search profile by name", urlPatterns = { "/SearchProfile" })
public class SearchProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchProfile() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	try {
    		String search = request.getParameter("search_bar");
    		System.out.println(search);
    		Connection con = ConnectionProvider.getConnection();
    		List studentlist=new ArrayList();
    		String sqlquery="SELECT * FROM studentdetails";
    		Statement st = con.createStatement();
    		ResultSet rs = st.executeQuery(sqlquery);
    		while (rs.next()) {
	    		List student=new ArrayList();
	    		student.add(rs.getString(2));
	    		student.add(rs.getString(6));
	    		studentlist.add(student);
    		}
    		System.out.println(studentlist);
    		request.setAttribute("studentlist",studentlist); 
    		RequestDispatcher view = request.getRequestDispatcher("search_profiles.jsp");
    		view.forward(request, response);
            con.close();
    	}
    	catch (Exception e){
    		e.printStackTrace();
    	}
	}

}
