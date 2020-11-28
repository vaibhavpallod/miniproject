document.getElementById("search_bar").onkeypress = function(e) {
    if(e.keyCode == 13) {
        var field = document.getElementById("search-field").value;
        if(field.localeCompare("profile")==0){
            location.replace("SearchProfile");
        }
        else if(field.localeCompare("achievement")==0){
            location.replace("SearchAchievement");
        }
        else if(field.localeCompare("internship")==0){
            location.replace("SearchInternship");
        }
        
    }
}