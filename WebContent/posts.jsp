<%@page import="java.util.Collections"%>
<%@page import="com.user.Internship"%>
<%@page import="com.user.Achievement"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.dao.Dao"%>
<%@page import="com.user.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
	if(session.getAttribute("UserID")==null){
		response.sendRedirect("login.jsp");
		return;
	}
	Dao dao = new Dao();
	ArrayList<User> users= dao.getAllUsers();
	ArrayList<Achievement> achievements = new ArrayList<Achievement>();
	ArrayList<Internship> internships = new ArrayList<Internship>();
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" href="style.css">
        <link rel="stylesheet" href="posts.css">

        <title>Posts</title>  
              
    </head>
    <body>
	
	<%
	int totalPosts=0;
	for(int i=0;i<users.size();i++){
		totalPosts+=users.get(i).getAchievements().size() + users.get(i).getInternships().size();
		achievements.addAll(users.get(i).getAchievements());
		internships.addAll(users.get(i).getInternships());
		Collections.reverse(achievements);
		Collections.reverse(internships);
		
		
	}
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

            <!-- TODO -- ADD HERE THE POSTS DIVS -->

            <!--********************************************POSTS SECTION***********************************************-->
         
         <% 
         int i=0,a=0;
        while(i<internships.size() && a<achievements.size()){
         
				String name= "",bio= "",id= "",encodedString= "",image= "",description = "";
        		
				System.out.println(i + " "+ a);
				//System.out.println(achievements.get(a).getTimestamp());
				//System.out.println(internships.get(i).getTimestamp());
				//System.out.println(dao.compareTimestamp(achievements.get(a).getTimestamp(),internships.get(i).getTimestamp()));
				if(dao.compareTimestamp(achievements.get(a).getTimestamp(),internships.get(i).getTimestamp())){
        			if(i<internships.size()){
					id=internships.get(i).getID();
        			name=dao.getName(id);
        			bio = dao.getUser(id).getBio();
        			encodedString=dao.getProfilePic(id);
        			image = internships.get(i).getEndcodeString();
        			description=internships.get(i).getDescription();
        			i++;	
        			}
        		}
        		else if(a<achievements.size()){
        			id=achievements.get(a).getID();
        			name=dao.getName(id);
        			bio = dao.getUser(id).getBio();
        			encodedString=dao.getProfilePic(id);
        			image = achievements.get(a).getEncodedString();
        			description=achievements.get(a).getDescription();
        			a++;	
        		}
        	 
        	 
        	// String id =users.get(j).getID();
            // String espic=dao.getProfilePic(id);
         	//System.out.println(users.get(i).getID());
         
         %>
         
            <article  class="postarticle" role="presentation" style=" width: 600px;height: 600px; background-color: white;">
                <header style="border-bottom: 1px solid #000000;">
                    <div class="headerclass">
        
                        <div style="height: 80px; width: 80px;">
                            <!-- <canvas height="80" width="80" style="position: absolute;top: -5px;
                               left: -5px;height: 82px;width: 82px;">
        
                            </canvas> -->
                            <span>
        	            	    <img src="data:image/png;base64,<%=encodedString%>" style="border-radius: 50%; width: 70px;height: 70px;">
 		    			     </span>
        
                        </div>
        
                        <div class="headerdetails">
                            <div >
                                <h3 style="margin: 0;padding: 0"><%=name %></h3>
        
                            </div>
                            <div>
                                <p style="font-size: 13;color: gray;margin-top: 10px "><%=bio %></p>
        
                            </div>
        
                        </div>
                    </div>
                </header>
        
                <span  class="description">
                    <span dir="ltr" style="font-size: 20px;"><%=description %><br><br></span>
                </span>
                <span class="readmore">
                    read more...</span>
                <div class="descimage">
                    <img  src="data:image/png;base64,<%=image%>" style=" width: 500px;height: 300px;">
                </div>
            </article>
<%} %>

 <% 
        while(i<internships.size()){
         
				String name= "",bio= "",id= "",encodedString= "",image= "",description = "";
        		
				id=internships.get(i).getID();
    			name=dao.getName(id);
    			bio = dao.getUser(id).getBio();
    			encodedString=dao.getProfilePic(id);
    			image = internships.get(i).getEndcodeString();
    			description=internships.get(i).getDescription();
    			i++;	
				
        	 
        	// String id =users.get(j).getID();
            // String espic=dao.getProfilePic(id);
         	//System.out.println(users.get(i).getID());
         
         %>
         
            <article  class="postarticle" role="presentation" style=" width: 600px;height: 600px; background-color: white;">
                <header style="border-bottom: 1px solid #000000;">
                    <div class="headerclass">
        
                        <div style="height: 80px; width: 80px;">
                            <!-- <canvas height="80" width="80" style="position: absolute;top: -5px;
                               left: -5px;height: 82px;width: 82px;">
        
                            </canvas> -->
                            <span>
        	            	    <img src="data:image/png;base64,<%=encodedString%>" style="border-radius: 50%; width: 70px;height: 70px;">
 		    			     </span>
        
                        </div>
        
                         <div class="headerdetails">
                            <div >
                                <h3 style="margin: 0;padding: 0"><%=name %></h3>
        
                            </div>
                            <div>
                                <p style="font-size: 13;color: gray;margin-top: 10px "><%=bio %></p>
        
                            </div>
        
                        </div>
                    </div>
                </header>
        
                <span  class="description">
                    <span dir="ltr"><%=description %><br><br></span>
                </span>
                <span class="readmore">
                    read more...</span>
                <div class="descimage">
                    <img  src="data:image/png;base64,<%=image%>" style=" width: 500px;height: 300px;">
                </div>
            </article>
<%} %>

<% 
        while(a<achievements.size()){
         
				String name= "",bio= "",id= "",encodedString= "",image= "",description = "";
        		
				id=achievements.get(a).getID();
    			name=dao.getName(id);
    			bio = dao.getUser(id).getBio();
    			encodedString=dao.getProfilePic(id);
    			image = achievements.get(a).getEncodedString();
    			description=achievements.get(a).getDescription();
    			a++;	
				
        	 
        	// String id =users.get(j).getID();
            // String espic=dao.getProfilePic(id);
         	//System.out.println(users.get(i).getID());
         
         %>
         
            <article  class="postarticle" role="presentation" style=" width: 600px;height: 600px; background-color: white;">
                <header style="border-bottom: 1px solid #000000;">
                    <div class="headerclass">
        
                        <div style="height: 80px; width: 80px;">
                            <!-- <canvas height="80" width="80" style="position: absolute;top: -5px;
                               left: -5px;height: 82px;width: 82px;">
        
                            </canvas> -->
                            <span>
        	            	    <img src="data:image/png;base64,<%=encodedString%>" style="border-radius: 50%; width: 70px;height: 70px;">
 		    			     </span>
        
                        </div>
        
                         <div class="headerdetails">
                            <div >
                                <h3 style="margin: 0;padding: 0"><%=name %></h3>
        
                            </div>
                            <div>
                                <p style="font-size: 13;color: gray;margin-top: 10px "><%=bio %></p>
        
                            </div>
        
                        </div>
                    </div>
                </header>
        
                <span  class="description">
                    <span dir="ltr"><%=description %><br><br></span>
                </span>
                <span class="readmore">
                    read more...</span>
                <div class="descimage">
                    <img  src="data:image/png;base64,<%=image%>" style=" width: 500px;height: 300px;">
                </div>
            </article>
<%} %>
            <div>
               
       
        	</div>
       </div>

        
    </body>
</html>
