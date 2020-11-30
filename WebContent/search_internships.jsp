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
        <title>Search/Internships</title>        
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
					List internshiplist=new ArrayList();
                	internshiplist=(ArrayList)request.getAttribute("internshiplist");	
					if(internshiplist!=null && internshiplist.size()>0 ){				
				%>
				<p style="font-size: 25px;">Internship Results</p>
				<%
					for(int i=0;i<internshiplist.size();i++){
						List internship=(List)internshiplist.get(i);
						String id = internship.get(3).toString();
						ecspic=dao.getProfilePic(id);
						
				%>
                <button class="search_one_internship_button">
                    <div class="search_one_internship">
                        <img src="<%=(ecspic==null?"images/default_prof_pic.png":"data:image/png;base64,"+ecspic) %>" class="search_prof_pic">
                        <div class="search_int_info">
                            <p id="search_int_name" style="font-size: 20px; margin: 0;"><%=internship.get(0) %></p>
                            <p id="search_int_intname" style="font-size: 17px; margin: 5px 0 0 0; color: grey;"><%=internship.get(1) %></p>
                            <p id="search_int_intdescription" style="font-size: 15px; margin: 5px 0 0 0; color: grey;"><%=internship.get(2) %></p>
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