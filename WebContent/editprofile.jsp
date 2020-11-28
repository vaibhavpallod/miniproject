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
		/* 		alert("Form has been submitted");
		 */
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
				<input id="search_bar" type="text" placeholder="Search" /> <select
					data-trigger="" name="search-field" id="search-field">
					<option selected value="profile">Profile</option>
					<option value="achievement">Achievement</option>
					<option value="internship">Internship</option>
				</select>
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

			<!--PROFILE PICTURE WITH UPLOAD OPTION-->
			<div class="prof_pic_input">
				<p>
					<input type="file" accept="image/*" name="image"
						id="input_prof_pic_file" onchange="loadFile(event)"
						style="display: none; cursor: pointer;">
				</p>
				<p>
					<img id="input_prof_pic" width="150px" height="150px"
						style="border: 1px solid black; border-radius: 75px;"
						src="images/default_prof_pic.png" />
				</p>
				<p>
					<label for="input_prof_pic_file" style="cursor: pointer;">Upload
						Picture</label>
				</p>
				<script>
					var loadFile = function(event) {
						var image = document.getElementById('input_prof_pic');
						image.src = URL.createObjectURL(event.target.files[0]);
					};
				</script>
			</div>

			<!--FIELDS INPUT-->
			<div class="fields_input">
				<label for="edit_name">Name</label> <input type="text"
					id="edit_name"
					value="<%=(user.getName() == null ? "" : user.getName())%>">
				<label for="edit_bio"
					style="display: inline-block; margin-top: 20px;">Bio</label>
				<textarea id="edit_bio"><%=(user.getBio() == null ? "" : user.getBio())%></textarea>

				<label for="edit_email"
					style="display: inline-block; margin-top: 20px;">Email</label> <input
					type="email" id="edit_email"
					value="<%=(user.getEmail() == null ? "" : user.getEmail())%>"
					style="width: 63%"> <label for="edit_contact"
					style="display: inline-block; margin-top: 20px;">Contact</label> <input
					type="tel" id="edit_contact"
					value="<%=(user.getContact() == null ? "" : user.getContact())%>">

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
								<form action="EditAchievement">


									<label for="edit_ach_name_input">Name</label> <input
										name="ach_name" type="text" id="edit_ach_name_input">

									<label for="edit_ach_description_input">Description</label>
									<textarea name="ach_desc" id="edit_ach_description_input"></textarea>

									<label for="edit_ach_date_input">Date of Accomplishment</label>
									<input name="ach_date" type="date" id="edit_ach_date_input">

									<label for="edit_ach_image_input">Attach Image</label> <input
										name="ach_image" type="file" onchange="readURL(this)"
										accept="image/*" id="edit_ach_image_input">
									<div class="modal_buttons">
										<input type="submit" id="save_achievement" value="Save">
										<button id="delete_ach">Delete</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>

				<!-- ----------------------------------------VAIBHAV'S CODE - IT DOESNT WORK-------------------------------------------------- -->
				<!-- --------------------------------------------BELOW-------------------------------------------------- -->

				<!-- div id="modal_for_achievement" class="modal_edit_one_achievement">
						
						<div class="modal-content">
							<span class="close_achievement">&times;</span>
							<div id="form_for_achievements">
								<p style="font-size: 25px;" id="title_edit_ach">Edit
									Achievement</p>
								<p style="font-size: 25px; display: none;" id="title_add_ach">Add
									Achievement</p>
								<form action="EditProfile" class="modal_buttons" id="save_achievement">

									<div class="row">
										<div class="col-25">
											<label for="edit_ach_name_input">Name</label>
										</div>
										<div class="col-75">
											<input name="ach_name" type="text" id="edit_ach_name_input">
										</div>
									</div>

									<div class="row">
										<div class="col-25">
											<label for="edit_ach_Desc_input">Description</label>
										</div>
										<div class="col-75">
											<textarea name="ach_desc" id="edit_ach_description_input"></textarea>
										</div>
									</div>

									<div class="row">
										<div class="col-25">
											<label for="edit_ach_Date_input">Date of
												Accomplishment</label>
										</div>
										<div class="col-75">
											<input name="ach_date" type="date" id="edit_ach_name_input">
										</div>
									</div>

									<div class="row">
										<div class="col-25">
											<label for="edit_ach_image_input">Attach Image</label>
										</div>
										<div class="col-75">
											<input name="ach_image" type="file" onchange="readURL(this)"
												accept="image/*" id="edit_ach_name_input">
										</div>
									</div>



									<!-- 								<form class="modal_buttons" id="save_achievement"> -->
				<!-- //name="save_ach" -->
				<!-- div class="row">
										<button type="submit">Save</button>
									</div>
							
									<!--  onclick="CallServlet('GET')" -->

				<!-- /form>
							</div>
						</div>
					</div-->

				<!-- ----------------------------------------VAIBHAV'S CODE-------------------------------------------------- -->
				<!-- --------------------------------------------ABOVE-------------------------------------------------- -->


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
						for (Internship internship : internships) {
					%>
					<button class="edit_one_internship_button">
					id="<%=internship.getInternshipID()%>">
						<div class="edit_one_internship">
							<p class="edit_int_name" style="font-size: 20px;"><%=internship.getName()%></p>
							From&nbsp;:
							<p class="edit_int_startdate"
								style="font-size: 15px; display: inline-block;"><%=internship.getStartDate()%></p>
							<br> To&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:
							<p class="edit_int_enddate"
								style="font-size: 15px; display: inline-block;"><%=internship.getEndDate()%></p>
							<p class="edit_int_description" style="font-size: 15px;"><%=internship.getDescription()%></p>
							<p class="edit_int_cert" style="font-size: 15px;">
								<a href="" style="text-decoration: none;">View Certificate</a>
							</p>
						</div>
					</button>
					<%
						}
					%>
					<!--DYNAMIC ENDS HERE-->

					<!--POP UP DIV FOR INTERNSHIP-->
					<div id="modal_for_internship" class="modal_edit_one_internship">
						<!-- Modal content -->
						<div class="modal-content">
							<span class="close_internship">&times;</span>
							<div id="form_for_internships">
								<p style="font-size: 25px;" id="title_edit_int">Edit
									Internship</p>
								<p style="font-size: 25px; display: none;" id="title_add_int">Add
									Internship</p>
								<form action="EditInternship">
								<label for="edit_int_name_input">Name</label> <input name="intern_name" type="text"
									id="edit_int_name_input"> <label
									for="edit_int_startdate_input">Start Date</label> <input
									name="intern_startdate"	type="date" id="edit_int_startdate_input"> <label
									for="edit_int_enddate_input">End Date</label> <input
									name="intern_enddate" type="date" id="edit_int_enddate_input"> <label
									for="edit_int_description_input">Description</label>
									<input name="intern_desc" type="text" id="edit_int_description_input">
									<label for="edit_int_status_input">Status</label>
									<input name="intern_status" type="text" id="edit_int_status_input">
									<label for="edit_int_nor_input">NOR</label>
									<input name="intern_nor" type="text" id="edit_int_nor_input">
									
								<textarea id="edit_int_description_input"></textarea>
								<label for="edit_int_image_input">Attach Image</label> <input
									name="intern_image" type="file" onchange="readURL(this)"
									accept="image/*" id="edit_int_image_input">
								<div class="modal_buttons">
									<input type="submit" id="save_internship" value="Save">
									<button id="delete_int">Delete</button>
									<!--DONE WITH DELETE-->
								</div>
								</form>
							</div>
						</div>
					</div>
				</div>

				<!--SAVE BUTTON-->
				<input type="submit" id="save_changes" value="Save Changes">
			</div>
		</div>

		<!--PLEASE ADD COMMENTS AT EACH STEP-->
	</div>

	<script type="text/javascript" src="modal_edit.js"></script>
	<script type="text/javascript" src="search.js"></script>
</body>
</html>

<!-- ====================================================================================================================================== -->

