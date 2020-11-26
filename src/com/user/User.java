package com.user;
import java.util.*;

public class User {
	private String id;
	private String name;
	private String department;
	private String year;
	private String email;
	private String contact;
	private String bio;
	
	public ArrayList<Achievement>achievements;
	public ArrayList<Internship>internships;
	
	public User() {
		id=name=department=year=email=bio=null;
		achievements=new ArrayList<Achievement>();
		internships=new ArrayList<Internship>();
	}
	
	//setter methods
	public void setAchievements(ArrayList<Achievement>achievements){
		this.achievements=achievements;
	}
	public void setInternships(ArrayList<Internship>internships){
		this.internships=internships;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public void setID(String id){
		this.id=id;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void setBio(String bio) {
		this.bio=bio;
	}
	public void setDepartment(String department) {
		this.department=department;
	}
	public void setYear(String rollno) {
		
//		if(rollno.startsWith("1")) {
//			year="First Year";
//		}
//		else if(rollno.startsWith("2")) {
//			year="Second Year";			
//		}
//		else if() {
//			year="Second Year";			
//		}
//		
		switch (rollno.charAt(0)) {
		case '1':
			year="First Year";
			break;
		case '2':
			year="Second Year";
			break;
		case '3':
			year="Third Year";
			break;
		case '4':
			year="Fourth Year";
			break;

		default:
			year="NULL";
		}
	}
	public void setEmail(String email) {
		this.email=email;
	}
	
	//getter methods
	public ArrayList<Achievement>getAchievements(){
		return achievements;
	}
	public ArrayList<Internship> getInternships(){
		return internships;
	}
	public String getContact() {
		return contact;
	}
	public String getID() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getBio() {
		return bio;
	}
	public String getDepartment() {
		return department;
	}
	public String getYear() {
		return year;
	}
	public String getEmail() {
		return email;
	}
	
}
