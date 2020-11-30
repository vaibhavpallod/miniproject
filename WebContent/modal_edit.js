//ACHIEVEMENTS

// Get the modal
var modal_ach = document.getElementById("modal_for_achievement");

// Get the button that opens the modal
var btns_ach = document.getElementsByClassName("edit_one_achievement_button");

// Get the <span> element that closes the modal
var spans_ach = document.getElementsByClassName("close_achievement");

// When the user clicks the button, open the modal 
editAchievementOnClick = function() {
  modal_ach.style.display = "block";
  document.getElementById("edit_ach_name_input").value = this.getElementsByClassName("edit_ach_name")[0].innerHTML.toString();
  document.getElementById("edit_ach_date_input").value = this.getElementsByClassName("edit_ach_date")[0].innerHTML;
  document.getElementById("edit_ach_description_input").value = this.getElementsByClassName("edit_ach_description")[0].innerHTML.toString();

  document.getElementById("title_edit_ach").style.display = "block";
  document.getElementById("title_add_ach").style.display = "none";

  document.getElementById("delete_ach").style.display = "inline-block";
  
  document.getElementById("ach-id").value=this.id.toString();
}

for(var i=0;i<btns_ach.length;i++){
    btns_ach[i].onclick = editAchievementOnClick;
}

// When the user clicks on <span> (x), close the modal
spanCloseOnClick = function() {
  modal_ach.style.display = "none";
}

for(var i=0;i<spans_ach.length;i++){
    spans_ach[i].onclick = spanCloseOnClick;
}


//INTERNSHIPS

// Get the modal
var modal_int = document.getElementById("modal_for_internship");

// Get the button that opens the modal
var btns_int = document.getElementsByClassName("edit_one_internship_button");

// Get the <span> element that closes the modal
var spans_int = document.getElementsByClassName("close_internship");

// When the user clicks the button, open the modal 
editInternshipOnClick = function() {
  modal_int.style.display = "block";
  document.getElementById("edit_int_name_input").value = this.getElementsByClassName("edit_int_name")[0].innerHTML.toString();
  document.getElementById("edit_int_startdate_input").value = this.getElementsByClassName("edit_int_startdate")[0].innerHTML;
  document.getElementById("edit_int_enddate_input").value = this.getElementsByClassName("edit_int_enddate")[0].innerHTML;
  document.getElementById("edit_int_description_input").value = this.getElementsByClassName("edit_int_description")[0].innerHTML.toString();

  document.getElementById("title_edit_int").style.display = "block";
  document.getElementById("title_add_int").style.display = "none";

  document.getElementById("delete_int").style.display = "inline-block";
  
  document.getElementById("int-id").value=this.id.toString();
}

for(var i=0;i<btns_int.length;i++){
    btns_int[i].onclick = editInternshipOnClick;
}

// When the user clicks on <span> (x), close the modal
spanCloseOnClick = function() {
  modal_int.style.display = "none";
}

for(var i=0;i<spans_int.length;i++){
    spans_int[i].onclick = spanCloseOnClick;
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal_ach) {
    modal_ach.style.display = "none";
  }
  if (event.target == modal_int) {
    modal_int.style.display = "none";
  }
}


/*PLUS BUTTON FOR ACHIEVEMENT*/
var plus_ach = document.getElementById("plus_button_ach");

plus_ach.onclick = function(){
  modal_ach.style.display = "block";
  document.getElementById("edit_ach_name_input").value = "";
  document.getElementById("edit_ach_date_input").value = "";
  document.getElementById("edit_ach_description_input").value = "";

  document.getElementById("title_edit_ach").style.display = "none";
  document.getElementById("title_add_ach").style.display = "block";

  document.getElementById("delete_ach").style.display = "none";
  
  document.getElementById("ach-id").value="0";
}

/*PLUS BUTTON FOR INTERNSHIP*/
var plus_int = document.getElementById("plus_button_int");

plus_int.onclick = function(){
  modal_int.style.display = "block";
  document.getElementById("edit_int_name_input").value = "";
  document.getElementById("edit_int_startdate_input").value = "";
  document.getElementById("edit_int_enddate_input").value = "";
  document.getElementById("edit_int_description_input").value = "";

  document.getElementById("title_edit_int").style.display = "none";
  document.getElementById("title_add_int").style.display = "block";

  document.getElementById("delete_int").style.display = "none";
  
  document.getElementById("int-id").value="0";
}



