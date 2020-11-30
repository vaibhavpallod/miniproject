<%@page import="com.dao.Dao"%>
<%@page import="java.awt.Window"%>
<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.user.*"%>
<%@page import="java.util.*"%>
<%
	if (session.getAttribute("UserID") == null) {
		response.sendRedirect("login.jsp");
		return;
	}
%>
<%
	User user = (User) session.getAttribute("User");
%>

<%
	Achievement currentEditAchievement;
	Internship currentEditInternship;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<title>Posts</title>

<script type="text/javascript">
	function CallServlet(methodType) {
		document.getElementById("save_achievement").action = "EditProfile";
		document.getElementById("save_achievement").method = methodType;
		document.getElementById("save_achievement").submit();
	}
	
</script>

</head>
<body>

	<!--TITLE OF THE SITE-->
	<div id="title">
		<b class="first_letter_in_title">C</b>ollege <b
			class="first_letter_in_title">N</b>etwork <b
			class="first_letter_in_title">S</b>ystem
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
					<a href="editprofile.jsp">Edit Profile</a> <a href="changepw.jsp">Change
						Password</a> <a href="Logout">Logout</a>
				</div>
			</div>

			<!--MY PROFILE OPTION-->
			<a href="profile_achievements.jsp">My Profile</a>

			<!--POSTS OPTION-->
			<a href="posts.jsp">Posts</a>
		</div>

		<!--EDIT PROFILE-->
		<div class="edit_profile">
		<%	Dao dao = new Dao();
            String ecspic = "&nbsp;";
            ecspic=dao.getProfilePic(user.getID());

       	%>
			<!--PROFILE PICTURE WITH UPLOAD OPTION-->
			<div class="prof_pic_input">
			<form name="profileform" action="EditPic" method="post" enctype="multipart/form-data">
				<p>
					<input type="file" accept="image/*" name="profpic"
						id="input_prof_pic_file" onchange="loadFile(event)"
						style="display: none; cursor: pointer;">
				</p>
				<p>
				<% if(ecspic==null)  { System.out.print("null in profile pic"); %>
					<img id="input_prof_pic" width="150px" height="150px"
						style="border: 1px solid black; border-radius: 75px;"
						src="images/default_prof_pic.png" />
				<% }else{ %>
					<img id="input_prof_pic" width="150px" height="150px"
						style="border: 1px solid black; border-radius: 75px;"
						src="data:image/png;base64,<%=ecspic %>" />
				<%} %>
				</p>
				<p >
					<label for="input_prof_pic_file" style="cursor: pointer;">Upload Picture</label>
				</p>
				<!-- <input type="submit" id="save_achievement" value="Save">
				 -->
				 <input type="submit" id="callservletforpic" value="Save" style="display: none" > 					
			
			</form>
				<script>
					var loadFile = function(event) {
						var image = document.getElementById('input_prof_pic');
						image.src = URL.createObjectURL(event.target.files[0]);
					/* 	document.getElementById("callservletforpic").action = "EditPic";
						document.getElementById("callservletforpic").method = "POST"; */		
						document.getElementById("callservletforpic").click();
						};
				</script>
			</div>

			<!--FIELDS INPUT-->
			<div class="fields_input">
			
				<form action="SaveChanges">
				
				<label for="edit_name">Name</label> <input type="text"
					id="edit_name" name="edit_name"
					value="<%=(user.getName() == null ? "" : user.getName())%>">
				<label for="edit_bio"
					style="display: inline-block; margin-top: 20px;">Bio</label>
				<textarea id="edit_bio" name="edit_bio"><%=(user.getBio() == null ? "" : user.getBio())%></textarea>

				<label for="edit_email"
					style="display: inline-block; margin-top: 20px;">Email</label> <input
					type="email" id="edit_email" name="edit_email"
					value="<%=(user.getEmail() == null ? "" : user.getEmail())%>"
					style="width: 63%"> <label for="edit_contact"
					style="display: inline-block; margin-top: 20px;">Contact</label> <input
					type="tel" id="edit_contact" name="edit_contact"
					value="<%=(user.getContact() == null ? "" : user.getContact())%>">
					
				<!--SAVE BUTTON-->
				<input type="submit" id="save_changes" value="Save Changes">	
				
				</form>
					

				<!--EDIT ACHIEVEMENTS-->
				<div class="edit_achievements">
					<p style="font-size: 22px; margin: 0; margin-top: 30px;">
						Achievements
						<button class="plus_button" id="plus_button_ach">
							<img src="images/plus.png" width="30px" height="30px">
						</button>
					</p>
					<hr style="margin-bottom: 30px;">
					<!--TODO DYNAMIC HERE-->
					<%
						ArrayList<Achievement> achievements = user.getAchievements();

						for (Achievement achievement : achievements) {
					%>
					<button class="edit_one_achievement_button"
						id="<%=achievement.getAchievementID()%>">
						<div class="edit_one_achievement">
							<p class="edit_ach_name" style="font-size: 20px;"><%=achievement.getName()%></p>
							<p class="edit_ach_date" style="font-size: 15px;"><%=achievement.getDate()%></p>
							<p class="edit_ach_description" style="font-size: 15px;"><%=achievement.getDescription()%></p>
							<p class="edit_ach_cert" style="font-size: 15px;">
								<a href="" style="text-decoration: none;">View Certificate</a>
							</p>
						</div>
					</button>
					<%
						}
					%>

					<!--DYNAMIC ENDS HERE-->

					<!--POP UP DIV FOR ACHIEVEMENT-->
					<div id="modal_for_achievement" class="modal_edit_one_achievement">
						<!-- Modal content -->
						<div class="modal-content">
							<span class="close_achievement">&times;</span>
							<div id="form_for_achievements">


								<p style="font-size: 25px;" id="title_edit_ach">Edit
									Achievement</p>
								<p style="font-size: 25px; display: none;" id="title_add_ach">Add
									Achievement</p>
								
        							<!-- <form id="ach-form" name="fileform" action="EditAchievement" method="post" enctype="multipart/form-data">
        							
        							 -->


									<form id="ach-form" name="fileform" method="post" action="EditAchievement" enctype="multipart/form-data">
									
									<input type="text" name="ach-id" id="ach-id" value="0" style="display:none;" required>
									
									<label for="edit_ach_name_input">Name</label> <input
										name="ach_name" type="text" id="edit_ach_name_input" required>

									<label for="edit_ach_description_input">Description</label>
									<textarea name="ach_desc" id="edit_ach_description_input" required></textarea>

									<label for="edit_ach_date_input">Date of Accomplishment</label>
									<input name="ach_date" type="date" id="edit_ach_date_input" required>

									<label for="edit_ach_image_input">Attach Image</label> 
									
									<input
										name="ach_image" type="file" onchange="readURL(this)"
										accept="image/*" id="edit_ach_image_input">
									
									<div class="modal_buttons">
										<input type="submit" id="save_achievement" value="Save">
										<button id="delete_ach" formaction="DeleteAchievement" formmethod="get">Delete</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>

				<!--EDIT INTERNSHIPS-->
				<div class="edit_internships">
					<p style="font-size: 22px; margin: 0; margin-top: 30px;">
						Internships
						<button class="plus_button" id="plus_button_int">
							<img src="images/plus.png" width="30px" height="30px">
						</button>
					</p>
					<hr style="margin-bottom: 30px;">

					<!--TODO DYNAMIC HERE-->
					<%
						ArrayList<Internship> internships = user.getInternships();
						System.out.println(internships);
						for (Internship internship : internships) {
					%>
					<button class="edit_one_internship_button"
					id=<%=internship.getInternshipID()%>>                                   <!--SOLVED == sanket ye line dekh kai ki hai mene comment ki hai  -->
						<div class="edit_one_internship">
							<p class="edit_int_name" style="font-size: 20px;"><%=internship.getName()%></p>
							
							<p class="edit_int_description" style="font-size: 15px;"><%=internship.getDescription()%></p>
							From&nbsp;:
							<p class="edit_int_startdate"
								style="font-size: 15px; display: inline-block;"><%=internship.getStartDate()%></p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;To&nbsp;&nbsp;:
							<p class="edit_int_enddate"
								style="font-size: 15px; display: inline-block;"><%=internship.getEndDate()%></p>
				         	<%
		                		String ecs = "&nbsp;";
								ecs=internship.getEndcodeString();

				        	%>
							 <p id="viewCertificate" class="edit_int_cert" onclick="setImage('<%=ecs %>')" style="font-size: 15px;">View Certificate</p>
							 
						</div>
					</button>
					<%
						}
					%>
					
					<!-- 	<div id="showImage" class="modal-content2">
							<span class="close_image">&times;</span>
							<img id="showinImagetag" alt="image">
						</div> -->
						
					<script>
					/*
					var viewcertificate =  document.getElementById("viewCertificate");

					viewcertificate.onclick = function() {
						document.getElementById("showImage").style.display = "block";
						document.getElementById("showinImagetag").src = "data:image/png;base64," + encodedString;
						window.alert("CLICKED");
					}
					*/
					/*
					document.getElementById("closeCert").onclick = function(){
						document.getElementById("showImage").style.display = "none";
					}

					function setImage(encodedString) {
						document.getElementById("showImage").style.display = "block";
						document.getElementById("showinImagetag").src = "data:image/png;base64," + encodedString;
//						window.alert(encodedString);
						console.log(encodedString);
					}
					*/

					function setImage(encodedString) {
						document.getElementById("showImage").style.display = "block";
						document.getElementById("showinImagetag").src = "data:image/png;base64," + encodedString;
//						window.alert(encodedString);
						console.log(encodedString);
					}
					</script>
						
						
					<!--DYNAMIC ENDS HERE-->

					<!--POP UP DIV FOR INTERNSHIP-->
					<div id="modal_for_internship" class="modal_edit_one_internship">
						<!-- Modal content -->
						<div class="modal-content">
							<span class="close_internship">&times;</span>
							<div id="form_for_internships">
								<p style="font-size: 25px;" id="title_edit_int">Edit Internship</p>
								<p style="font-size: 25px; display: none;" id="title_add_int">Add Internship</p>
				<form id="int-form" name="fileform"  action="EditInternship"  method="post" enctype="multipart/form-data">
				
						<input type="text" name="int-id" id="int-id" value="0" style="display:none;">
				
								<label for="edit_int_name_input">Name</label> <input name="intern_name" type="text"
									id="edit_int_name_input" required><label
									for="edit_int_startdate_input">Start Date</label> <input
									name="intern_startdate"	type="date" id="edit_int_startdate_input" required> <label
									for="edit_int_enddate_input">End Date</label> <input
									name="intern_enddate" type="date" id="edit_int_enddate_input" required> <label
									for="edit_int_description_input">Description</label>
									<textarea name="intern_desc" type="text" id="edit_int_description_input" required></textarea> 
									<label for="edit_int_status_input">Status</label>
									<input name="intern_status" type="text" id="edit_int_status_input">
									<label for="edit_int_nor_input">NOR</label>
									<input name="intern_nor" type="text" id="edit_int_nor_input">
									
								<label for="edit_int_image_input">Attach Image</label> 
								<input
									name="intern_image" type="file" onchange="readURL(this)"
									accept="image/*" id="edit_int_image_input">
								<div class="modal_buttons">
									<input type="submit" id="save_internship" value="Save">
									<button id="delete_int" formaction="DeleteInternship" formmethod="get">Delete</button>
									<!--DONE WITH DELETE-->
								</div>
								</form>
							</div>
						</div>
					</div>
				</div>

				
				
			</div>
		</div>

		<!--PLEASE ADD COMMENTS AT EACH STEP-->
	</div>
	<script type="text/javascript" src="validation.js"></script>
	<script type="text/javascript" src="modal_edit.js"></script>
	
</body>
</html>

<!-- ====================================================================================================================================== -->

