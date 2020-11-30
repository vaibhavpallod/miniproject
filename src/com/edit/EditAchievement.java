package com.edit;

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
@MultipartConfig(maxFileSize = 16177215)

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
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int achievementId = Integer.parseInt(request.getParameter("ach-id"));
		
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
			
			if(achievementId == 0) {
				dao.addAchievement(achievement);
			}else {
				achievement.setAchievementID(achievementId);
				dao.updateAchievement(achievement);
			}
			
			
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

