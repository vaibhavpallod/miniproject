package com.edit;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ConnectionProvider;
import com.dao.Dao;
import com.mysql.cj.jdbc.Blob;
import com.user.Achievement;
import com.user.Internship;


/**
 * Servlet implementation class EditInternship
 */
@WebServlet("/EditInternship")
public class EditInternship extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public EditInternship() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		Dao dao = new Dao();
		try {
			String id = "", intern_name = "", intern_des = "",intern_status="",intern_nor="";
			Date start_date,end_date;
			Blob intern_image;

			Connection con = ConnectionProvider.getConnection(); // DriverManager.getConnection(url, sqlUsername,
																	// sqlPassword);
			HttpSession session = request.getSession();
			id = (String) session.getAttribute("UserID");

			intern_name = request.getParameter("intern_name");
			intern_des = request.getParameter("intern_desc");
			intern_status = request.getParameter("intern_status");
			intern_nor = request.getParameter("intern_nor");
//			intern_image= request.get("ach_image");
			SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");
			String dateparameter = request.getParameter("intern_startdate");
			start_date = (Date) in.parse(dateparameter);
			dateparameter = request.getParameter("intern_enddate");
			end_date = (Date) in.parse(dateparameter);

			String tableName = "internship";
			boolean tExists = dao.checkTable(con, tableName);

			if (!tExists) {
				// if Table internship does not exists creating dynamic table // DEFAULT NOW()
				String createtable = "CREATE TABLE internship(userid varchar(20) ,intrnid int NOT NULL AUTO_INCREMENT PRIMARY KEY,intrnname varchar(80),intrndes varchar(150),startdate date,enddate date,status varchar(30),nor varchar(10),intrncert longblob,savedon DATETIME,FOREIGN KEY(userid) REFERENCES studentdetails(userid))";
				Statement stmt = (Statement) con.createStatement();
				stmt.executeUpdate(createtable);
				System.out.println("*******************TABLE CREATED*********************");
			}
			
			Internship internship = new Internship(id,intern_name,intern_des,intern_status,intern_nor,start_date,end_date,new ByteArrayInputStream(request.getParameter("intern_image").getBytes()),new Timestamp(System.currentTimeMillis()));
			dao.addInternship(internship);
			session.setAttribute("User", dao.getUser(session.getAttribute("UserID").toString()));
			response.sendRedirect("editprofile.jsp");
			
			System.out.println("*******************SUCCESS*********************");
			
			con.close();

		} catch (Exception e) {
			System.out.println("*******************CAUGHT ERROR*********************");

			e.printStackTrace();
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		System.out.println(
				"***************************************************Clicked in post Method************************************************************");

	}

}
