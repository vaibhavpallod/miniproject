package com.user;

import java.sql.Timestamp;

import com.mysql.cj.jdbc.Blob;

import java.sql.Date;

public class Internship {
	private String id;
	private String name;
	private Blob certificate;
	private int internshipId;
	private String description;
	private Date startDate;
	private Date endDate;
	private Timestamp timestamp;
	
	public Internship() {
		id=name=description=null;
		startDate=endDate=null;
		timestamp=null;
		certificate = null;
	}
	
	//setter methods
	public void setCertificate(Blob certificate) {
		this.certificate = certificate;
	}
	public void setInternshipID(int internshipId){
		this.internshipId=internshipId;
	}
	public void setID(String id){
		this.id=id;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void setDescription(String description) {
		this.description=description;
	}
	public void setStartDate(Date date) {
		this.startDate=date;
	}
	public void setEndDate(Date date) {
		this.endDate=date;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp=timestamp;
	}
	
	//getter methods
	public Blob getCertificate() {
		return certificate;
	}
	public int getInternshipID(){
		return internshipId;
	}
	public String getID() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public Date getStartDate() {
		return startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	
}
