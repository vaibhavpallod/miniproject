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

@WebServlet("/EditAchievement")
public class EditAchievement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditAchievement() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		Dao dao = new Dao();
		try {
			String id = "", ach_name = "", ach_des = "";
			Date ach_date;
			Blob ach_image;

			Connection con = ConnectionProvider.getConnection(); // DriverManager.getConnection(url, sqlUsername,
																	// sqlPassword);
			HttpSession session = request.getSession();
			id = (String) session.getAttribute("UserID");

			ach_name = request.getParameter("ach_name");
			ach_des = request.getParameter("ach_desc");
//			ach_image= request.get("ach_image");
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
			Achievement achievement = new Achievement(id,ach_name,ach_des,ach_date,new ByteArrayInputStream(request.getParameter("ach_image").getBytes()),new Timestamp(System.currentTimeMillis()));
			dao.addAchievement(achievement);
			session.setAttribute("User", dao.getUser(session.getAttribute("UserID").toString()));
			response.sendRedirect("editprofile.jsp");
			
			System.out.println("*******************SUCCESS*********************");
			
			con.close();

		} catch (Exception e) {
			System.out.println("*******************CAUGHT ERROR*********************");

			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		System.out.println(
				"***************************************************Clicked in post Method************************************************************");

	}

}
//String insertValue = "INSERT INTO achievement VALUES(?,?,?,?,?,?)";
//PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(insertValue);
//pstmt.setString(1, id);
//pstmt.setString(2, ach_name);
//pstmt.setString(3, ach_des);
//pstmt.setDate(4, new java.sql.Date(ach_date.getTime()));
//pstmt.setBinaryStream(5, new ByteArrayInputStream(request.getParameter("ach_image").getBytes()));
//pstmt.setDate(6, java.sql.Date.valueOf(java.time.LocalDate.now()));
//pstmt.executeUpdate();


//DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
//Calendar calobj = Calendar.getInstance();
//System.out.println(df.format(calobj.getTime()));

