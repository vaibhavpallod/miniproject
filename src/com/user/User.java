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
	public void setYear(String year) {
		this.year=year;
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
