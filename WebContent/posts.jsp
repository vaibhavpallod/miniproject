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
        <link rel="stylesheet" href="posts.css">

        <title>Posts</title>        
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

            <!-- TODO -- ADD HERE THE POSTS DIVS -->

            <!--PLEASE ADD COMMENTS AT EACH STEP-->

            <!--POSTS SECTION-->
            <article  class="postarticle" role="presentation" style=" width: 600px;height: 600px; background-color: white;">
                <header style="border-bottom: 1px solid #000000;">
                    <div class="headerclass">
        
                        <div style="height: 80px; width: 80px;">
                            <!-- <canvas height="80" width="80" style="position: absolute;top: -5px;
                               left: -5px;height: 82px;width: 82px;">
        
                            </canvas> -->
                            <span>
                                <img src="images/mypic.png" style="border-radius: 50%; width: 70px;height: 70px;">
                            </span>
        
                        </div>
        
                        <div class="headerdetails">
                            <div>
                                <h4>Vaibhav Pallod</h4>
        
                            </div>
                            <div>
                                <p>Intern at StablX | Team Captain PICT Robotics </p>
        
                            </div>
        
                        </div>
                    </div>
                </header>
        
                <span  class="description">
                    <span dir="ltr">Here is my <a
                            href="https://www.linkedin.com/feed/hashtag/?keywords=opensource&amp;highlightedUpdateUrns=urn%3Ali%3Aactivity%3A6727113396921208832"
                            data-attribute-index="0">#OpenSource</a> contribution!<br><br>The best part of contributing to
                        OpenSource is to you get exposure to developer community all round the globe and learn a lot.<br><br>I
                        have developed a MicroByte stated as Traffic Cop : Learn Load Balancing using HAProxy in which I try to
                        bring out the activity tasks to be performed at beginner level to understand using Learn By Doing
                        Approach. Head out to the repository <a href="https://lnkd.in/gsTRvNf"
                            data-attribute-index="7">https://lnkd.in/gsTRvNf</a> to experience it.<br><a
                            href="https://www.linkedin.com/feed/hashtag/?keywords=learners&amp;highlightedUpdateUrns=urn%3Ali%3Aactivity%3A6727113396921208832"
                            data-attribute-index="1">#Learners</a> feel free to improve the existing Micro Byte by submitting a
                        PR if you find any new ideas and exciting suggestions.<br><br>If debugging and sharing feedback to
                        better the existing projects sounds intriguing to you, then come &amp; join the force!<br><br>All you
                        have to do-<br><br></span>
                </span>
                <span class="readmore">
                    read more...</span>
                <div class="descimage">
                    <img  src="images/nature.jpg" style=" width: 500px;height: 300px;">
                </div>
            </article>
    
            <article  class="postarticle" role="presentation" style=" width: 600px;height: 600px; background-color: white;">
                <header style="border-bottom: 1px solid #000000;">
                    <div class="headerclass">
        
                        <div style="height: 80px; width: 80px;">
                            <!-- <canvas height="80" width="80" style="position: absolute;top: -5px;
                               left: -5px;height: 82px;width: 82px;">
        
                            </canvas> -->
                            <span>
                                <img src="images/mypic.png" style="border-radius: 50%; width: 70px;height: 70px;">
                            </span>
        
                        </div>
        
                        <div class="headerdetails">
                            <div>
                                <h4>Vaibhav Pallod</h4>
        
                            </div>
                            <div>
                                <p>Intern at StablX | Team Captain PICT Robotics </p>
        
                            </div>
        
                        </div>
                    </div>
                </header>
        
                <span  class="description">
                    <span dir="ltr">Here is my <a
                            href="https://www.linkedin.com/feed/hashtag/?keywords=opensource&amp;highlightedUpdateUrns=urn%3Ali%3Aactivity%3A6727113396921208832"
                            data-attribute-index="0">#OpenSource</a> contribution!<br><br>The best part of contributing to
                        OpenSource is to you get exposure to developer community all round the globe and learn a lot.<br><br>I
                        have developed a MicroByte stated as Traffic Cop : Learn Load Balancing using HAProxy in which I try to
                        bring out the activity tasks to be performed at beginner level to understand using Learn By Doing
                        Approach. Head out to the repository <a href="https://lnkd.in/gsTRvNf"
                            data-attribute-index="7">https://lnkd.in/gsTRvNf</a> to experience it.<br><a
                            href="https://www.linkedin.com/feed/hashtag/?keywords=learners&amp;highlightedUpdateUrns=urn%3Ali%3Aactivity%3A6727113396921208832"
                            data-attribute-index="1">#Learners</a> feel free to improve the existing Micro Byte by submitting a
                        PR if you find any new ideas and exciting suggestions.<br><br>If debugging and sharing feedback to
                        better the existing projects sounds intriguing to you, then come &amp; join the force!<br><br>All you
                        have to do-<br><br></span>
                </span>
                <span class="readmore">
                    read more...</span>
                <div class="descimage">
                    <img  src="images/nature.jpg" style=" width: 500px;height: 300px;">
                </div>
            </article>
            <article  class="postarticle" role="presentation" style=" width: 600px;height: 600px; background-color: white;">
                <header style="border-bottom: 1px solid #000000;">
                    <div class="headerclass">
        
                        <div style="height: 80px; width: 80px;">
                            <!-- <canvas height="80" width="80" style="position: absolute;top: -5px;
                               left: -5px;height: 82px;width: 82px;">
        
                            </canvas> -->
                            <span>
                                <img src="images/mypic.png" style="border-radius: 50%; width: 70px;height: 70px;">
                            </span>
        
                        </div>
        
                        <div class="headerdetails">
                            <div>
                                <h4>Vaibhav Pallod</h4>
        
                            </div>
                            <div>
                                <p>Intern at StablX | Team Captain PICT Robotics </p>
        
                            </div>
        
                        </div>
                    </div>
                </header>
        
                <span  class="description">
                    <span dir="ltr">Here is my <a
                            href="https://www.linkedin.com/feed/hashtag/?keywords=opensource&amp;highlightedUpdateUrns=urn%3Ali%3Aactivity%3A6727113396921208832"
                            data-attribute-index="0">#OpenSource</a> contribution!<br><br>The best part of contributing to
                        OpenSource is to you get exposure to developer community all round the globe and learn a lot.<br><br>I
                        have developed a MicroByte stated as Traffic Cop : Learn Load Balancing using HAProxy in which I try to
                        bring out the activity tasks to be performed at beginner level to understand using Learn By Doing
                        Approach. Head out to the repository <a href="https://lnkd.in/gsTRvNf"
                            data-attribute-index="7">https://lnkd.in/gsTRvNf</a> to experience it.<br><a
                            href="https://www.linkedin.com/feed/hashtag/?keywords=learners&amp;highlightedUpdateUrns=urn%3Ali%3Aactivity%3A6727113396921208832"
                            data-attribute-index="1">#Learners</a> feel free to improve the existing Micro Byte by submitting a
                        PR if you find any new ideas and exciting suggestions.<br><br>If debugging and sharing feedback to
                        better the existing projects sounds intriguing to you, then come &amp; join the force!<br><br>All you
                        have to do-<br><br></span>
                </span>
                <span class="readmore">
                    read more...</span>
                <div class="descimage">
                    <img  src="images/nature.jpg" style=" width: 500px;height: 300px;">
                </div>
            </article>

            <div>
               
       
        	</div>
       </div>

        
    </body>
</html>
