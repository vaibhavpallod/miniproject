document.getElementById("search_bar").onkeypress = function(e) {
    if(e.keyCode == 13) {
        var field = document.getElementById("search-field").value;
        if(field.localeCompare("profile")==0){
            location.replace("./search_profiles.jsp");
        }
        else if(field.localeCompare("achievement")==0){
            location.replace("./search_achievements.jsp");
        }
        else if(field.localeCompare("internship")==0){
            location.replace("./search_internships.jsp");
        }
    }
}