package com.edit;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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

@MultipartConfig(maxFileSize = 16177215)

@WebServlet("/EditPic")
public class EditPic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditPic() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("************* get called *****************");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("************* post called *****************");
		String id = (String) request.getSession().getAttribute("UserID");
		Dao dao = new Dao();
		boolean isavailable = false;
		try {
			InputStream inputStream = null; // input stream of the upload file
			Part filePart = request.getPart("profpic");
			String ach_name = "", ach_des = "";
			Date ach_date;

			Connection con = ConnectionProvider.getConnection();
			HttpSession session = request.getSession();
			id = (String) session.getAttribute("UserID");

			if (filePart != null) {
				// prints out some information for debugging
				inputStream = filePart.getInputStream();
			}

			isavailable=dao.checkProfilepic(id);
			if (isavailable) {

				String update = "UPDATE profilepic SET pic = ? WHERE profilepic.userid = "+id;
				PreparedStatement pstmt = con.prepareStatement(update);
				pstmt.setBlob(1, inputStream);
				pstmt.executeUpdate();
				System.out.println("Profile pic Updated Successfully *****************");

			} else {
				String insert = "INSERT INTO profilepic VALUES(?,?)";
				PreparedStatement pstmt = con.prepareStatement(insert);
				pstmt.setString(1, id);
				pstmt.setBlob(2, inputStream);
				pstmt.executeUpdate();
				System.out.println("Profile pic was Not Available Added Successfully *****************");
			}

//			PreparedStatement stmt = con.prepareStatement(check);
//			stmt.execute()
//			
			
			con.close();
			System.out.println("*******************SUCCESS*********************");

			response.sendRedirect("editprofile.jsp");
			
		} catch (Exception e) {
			System.out.println("*******************CAUGHT ERROR*********************");

			e.printStackTrace();
		}
	}
}
