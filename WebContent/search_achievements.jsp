<%@page import="com.user.*"%>
<%@page import="java.util.*"%>
<%@page import="com.dao.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
	if(session.getAttribute("UserID")==null){
		response.sendRedirect("login.jsp");
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
        <title>Search/Achievements</title>        
    </head>
    <body>
<%
		Dao dao = new Dao();
		String ecspic = "&nbsp;";
		//ecspic=dao.getProfilePic(user.getID());
	%>
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

            <div class="search_list">

                

                <!--TODO ADD DYNAMIC HERE-->
                <%
					List achievementlist=new ArrayList();
                	achievementlist=(ArrayList)request.getAttribute("achievementlist");	
					if(achievementlist!=null && achievementlist.size()>0 ){				
				%>
				<p style="font-size: 25px;">Achievement Results</p>
				<%
					for(int i=0;i<achievementlist.size();i++){
						List achievement=(List)achievementlist.get(i);
						String id = achievement.get(3).toString();
						ecspic=dao.getProfilePic(id);
						
				%>
                <button class="search_one_achievement_button">
                    <div class="search_one_achievement">
                        <img src="data:image/png;base64,<%=ecspic %>" class="search_prof_pic">
                        <div class="search_ach_info">
                            <p id="search_ach_name" style="font-size: 20px; margin: 0;"><%=achievement.get(0) %></p>
                            <p id="search_ach_achname" style="font-size: 17px; margin: 5px 0 0 0; color: grey;"><%=achievement.get(1) %></p>
                            <p id="search_ach_achdescription" style="font-size: 15px; margin: 5px 0 0 0; color: grey;"><%=achievement.get(2) %></p>
                        </div>
                    </div>
                </button>
                <%
					}
				%>
            </div>
			<%
				}else{
			%>
            <div class="search_not_found">
                <p style="font-size: 30px;">No Results Found</p>
            </div>
			<%}%>
            <!--PLEASE ADD COMMENTS AT EACH STEP-->
        </div>

        
    </body>
</html>