<%@page import="java.util.Base64"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="java.sql.*"%>
  <%@page import="java.util.*"%>
<%@page import="com.user.*" %>
 <%@page import="javax.*"%>
 <%@page import="com.dao.*" %>
 
 
<%
if(session.getAttribute("UserID")==null){
	response.sendRedirect("login.jsp");
	return;
}
%>
<%
User user=(User)session.getAttribute("User");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
        <title>Profile/Achievements</title>        
    </head>
    <body>

        <!--TITLE OF THE SITE-->
        <div id="title">
            <b class="first_letter_in_title">C</b>ollege <b class="first_letter_in_title">N</b>etwork <b class="first_letter_in_title">S</b>ystem
        </div>

        <!--DISPLAY AREA-->
        <div id="display_area">

            <!--MENU BAR-->
            <div class="top_menu">

                <!--SEARCH BAR-->
                <div class="search">
                	<form action="SearchProfile">
                    	<input id="search_bar" name="search_bar" type="text" placeholder="Search"/>
                    	<select data-trigger="" name="search-field" id="search-field">
                        	<option selected value="profile">Profile</option>
                        	<option value="achievement">Achievement</option>
                        	<option value="internship">Internship</option>
                    	</select>
                    </form>
                </div>

                <!--SETTINGS OPTION-->
                <div class="dropdown">
                    <button class="dropbtn">Settings</button>
                    <div class="dropdown_content">
                        <a href="editprofile.jsp">Edit Profile</a>
                        <a href="changepw.jsp">Change Password</a>
                        <a href="Logout">Logout</a>
                    </div>
                </div>

                <!--MY PROFILE OPTION-->
                <a href="profile_achievements.jsp">My Profile</a>

                <!--POSTS OPTION-->
                <a href="posts.jsp">Posts</a>
            </div>
                     
            <!--PROFILE INFO-->
            <div class="profile_info">
	            <%	Dao dao = new Dao();
	            String ecspic = "&nbsp";
	            ecspic=dao.getProfilePic(user.getID());
	
	      	 	%>
            
            	<img src="<%=(ecspic==null?"images/default_prof_pic.png":"data:image/png;base64,"+ecspic) %>" id="profile_pic">
			
            
            
                <div class="profile_details">
                    <p id="name" style="font-size: 30px;"><%=user.getName() %></p>
                    <p id="department" style="font-size: 20px; color: grey;"><%=user.getDepartment()
                     %></p>
                    <p id="year" style="font-size: 20px; color: grey;"><%=user.getYear()%></p>
                    <p id="bio" style="font-size: 17px; color: grey;"><%=user.getBio()==null?"&nbsp;":user.getBio()%></p>
                </div>
            </div>

            <hr style="color: grey; width: 80%; margin-top: 30px; margin-bottom: 30px;">

            <!--FIELD NAVIGATION-->
            <div class="field_menu">
                <ul>
                    <li style="color:black;"><a href="profile_achievements.jsp">Achievements</a></li>
                    <li><a href="profile_internships.jsp">Internships</a></li>
                    <li><a href="">Projects</a></li>
                </ul>
            </div>

            <!--DATA NOT AVAILABLE if no data has been entered yet-->
            <!--div class="data_not_available">
                Data Not Available
            </div-->

            <!--ACHIEVEMENTS-->
            <div class="achievements">

                <!--ONE ACHIEVEMENT RECORD-->
                <%
                	ArrayList<Achievement> achievements=user.getAchievements();
                	if(achievements.isEmpty()){
                		%>
                		<div class="data_not_available">
                		Data Not Available
            			</div>
                		<%
                	}
                	else{
                		for(Achievement achievement : achievements){
                			%>
                			<div class="one_achievement">
                        	<p id="ach_name" style="font-size: 20px;"><%=achievement.getName() %></p>
                        	<p id="ach_date" style="font-size: 15px;"><%=achievement.getDate() %></p>
                        	<p id="ach_description" style="font-size: 15px;"><%=achievement.getDescription() %></p>
                        	<p id="ach_cert" style="font-size: 15px; "><a href="" style="text-decoration:none;">View Certificate</a></p>
                    		</div>
                    		<%
                		}
                    }
                %>
            </div>

            <!--PLEASE ADD COMMENTS AT EACH STEP-->
        </div>
    </body>
</html>
