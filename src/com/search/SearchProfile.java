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
import com.dao.Dao;
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
    		String selected = request.getParameter("search-field");
    		
    		if(selected.equals("profile"))
    		{
    			/*
    			 *
					mysql> DELIMITER $$
					mysql> CREATE PROCEDURE get_profile_details (IN search_name VARCHAR(100))
					    -> BEGIN
					    -> SELECT name,bio FROM studentdetails WHERE name LIKE CONCAT ('%',search_name,'%');
					    -> END $$
					
					mysql> CREATE PROCEDURE get_achievement_details (IN search_name VARCHAR(100))
					    -> BEGIN
					    -> SELECT userid,achname,achdes FROM achievement WHERE achname LIKE CONCAT ('%',search_name,'%');
					    -> END $$
					
					
					mysql> CREATE PROCEDURE get_internship_details (IN search_name VARCHAR(100))
					    -> BEGIN
					    -> SELECT userid,intrnname,intrndes FROM internship WHERE intrnname LIKE CONCAT('%',search_name,'%');
					    -> END $$
					mysql-> DELIMITER ;
    			 */
    			Connection con = ConnectionProvider.getConnection();
    			List studentlist=new ArrayList();
        		String sqlquery="{CALL get_profile_details('"+ search +"')}";
        		Statement st = con.createStatement();
        		ResultSet rs = st.executeQuery(sqlquery);
        		while (rs.next()) {
    	    		List student=new ArrayList();
    	    		student.add(rs.getString(1));
    	    		student.add(rs.getString(2));
    	    		studentlist.add(student);
        		}
        		request.setAttribute("studentlist",studentlist); 
        		RequestDispatcher view = request.getRequestDispatcher("search_profiles.jsp");
        		view.forward(request, response);
                con.close();
    		}
    		else if(selected.equals("achievement"))
    		{
    			Connection con = ConnectionProvider.getConnection();
    			Dao dao = new Dao();
    			List achievementlist=new ArrayList();
        		String sqlquery="{CALL get_achievement_details('"+ search +"')}";
        		Statement st = con.createStatement();
        		ResultSet rs = st.executeQuery(sqlquery);
        		while (rs.next()) {
    	    		List achievement=new ArrayList();
    	    		String id=rs.getString(1);
    	    		achievement.add(dao.getName(id));
    	    		achievement.add(rs.getString(2));
    	    		achievement.add(rs.getString(3));
    	    		achievementlist.add(achievement);
        		}
        		request.setAttribute("achievementlist",achievementlist); 
        		RequestDispatcher view = request.getRequestDispatcher("search_achievements.jsp");
        		view.forward(request, response);
                con.close();
    		}
    		else
    		{
    			Connection con = ConnectionProvider.getConnection();
    			Dao dao = new Dao();
    			List internshiplist=new ArrayList();
        		String sqlquery="{CALL get_internship_details('"+ search +"')}";
        		Statement st = con.createStatement();
        		ResultSet rs = st.executeQuery(sqlquery);
        		while (rs.next()) {
    	    		List internship=new ArrayList();
    	    		String id=rs.getString(1);
    	    		internship.add(dao.getName(id));
    	    		internship.add(rs.getString(2));
    	    		internship.add(rs.getString(3));
    	    		internshiplist.add(internship);
        		}
        		request.setAttribute("internshiplist",internshiplist); 
        		RequestDispatcher view = request.getRequestDispatcher("search_internships.jsp");
        		view.forward(request, response);
                con.close();
    		}
    	}
    	catch (Exception e){
    		e.printStackTrace();
    	}
	}

}
