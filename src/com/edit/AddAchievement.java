package com.edit;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.dao.ConnectionProvider;
import com.dao.Dao;
import com.mysql.cj.jdbc.Blob;
import com.user.Achievement;
import com.user.Internship;
/**
 * Servlet implementation class AddAchievement
 */
@WebServlet("/AddAchievement")
public class AddAchievement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAchievement() {
        super();
        // TODO Auto-generated constructor stub
    }
   
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dao dao = new Dao();
		try {
	        InputStream inputStream = null; // input stream of the upload file
	        Part filePart = request.getPart("ach_image");	      
	        String id = "", ach_name = "", ach_des = "";
			Date ach_date;

			Connection con = ConnectionProvider.getConnection();
			HttpSession session = request.getSession();
			id = (String) session.getAttribute("UserID");

	       
	        if (filePart != null) {
	            // prints out some information for debugging
	            System.out.println(filePart.getName());
	            System.out.println(filePart.getSize());
	            System.out.println(filePart.getContentType());

	            inputStream = filePart.getInputStream();
	        }

			ach_name = request.getParameter("ach_name");
			ach_des = request.getParameter("ach_desc");
			SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");
			String dateparameter = request.getParameter("ach_date");
			ach_date = (Date) in.parse(dateparameter);

			String tableName = "achievement";
			boolean tExists = dao.checkTable(con, tableName);

			if (!tExists) {
				// if Table achievement does not exists creating dynamic table // DEFAULT NOW()
				String createtable = "CREATE TABLE achievement(userid varchar(20),ach_name varchar(80),ach_des varchar(150),ach_date date,ach_cert longblob,saved_on DATETIME)";
				Statement stmt = (Statement) con.createStatement();
				stmt.executeUpdate(createtable);
				System.out.println("*******************TABLE CREATED*********************");
			}
				
			//"CREATE TABLE achievement(userid varchar(20),achid int NOT NULL AUTO_INCREMENT PRIMARY KEY,achname varchar(80),achdes varchar(150),achdate date,achcert longblob,savedon DATETIME,FOREIGN KEY(userid) REFERENCES studentdetails(userid))";
			Achievement achievement = new Achievement(id,ach_name,ach_des,ach_date,inputStream,new Timestamp(System.currentTimeMillis()));
			dao.addAchievement(achievement);
			session.setAttribute("User", dao.getUser(session.getAttribute("UserID").toString()));
			
			con.close();
			response.sendRedirect("editprofile.jsp");
			
			System.out.println("*******************SUCCESS*********************");
			
			
		} catch (Exception e) {
			System.out.println("*******************CAUGHT ERROR*********************");

			e.printStackTrace();
		}

	}

}
