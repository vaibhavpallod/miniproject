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
        <title>Change Password</title>        
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

            <!--CHANGE PASSWORD-->
            <div class="change_password">
                <form action="ChangePassword">
                <p style="text-align: center; font-size: 20px;">CHANGE PASSWORD</p>
                <hr>
                <label for="old_password">OLD PASSWORD</label>
                <input type="password" id="old_password" name="old_password">
                <p style="color:red; margin:0; margin-left: 19%; margin-top: 10px; font-size:19px;">
                	<%
                		String msg = "&nbsp;";
                		if(session.getAttribute("WrongPW")!=null){
                			msg="** Incorrect Password !!";
                		}
                	%>
        			<%=msg %>
            	</p>
                
                <label for="new_password" style="margin-top:15px;">NEW PASSWORD</label>
                <input type="password" id="new_password" name="new_password">
                <input type="checkbox" onclick="myFunction()" style="margin-left: 19%; margin-top: 10px;">Show Password
                <script>
                    function myFunction() {
                        var x = document.getElementById("new_password");
                        if (x.type === "password") {
                            x.type = "text";
                        } 
                        else {
                        x.type = "password";
                        }
                    }
                </script>
                <input type="submit" id="submit_password" value="SET PASSWORD">
                </form>
            </div>
            
            <div></div>
            <!--PLEASE ADD COMMENTS AT EACH STEP-->
        </div>

        
    </body>
</html>