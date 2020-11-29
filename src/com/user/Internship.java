package com.user;

import java.sql.Timestamp;

import com.mysql.cj.jdbc.Blob;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;

public class Internship {
	private String id;
	private String name;
	private int internshipId;
	private Blob certificate;
	private String description;
	private Date startDate;
	private Date endDate;
	private String status;
	private String nor;
	private Timestamp timestamp;
	private InputStream inputStream;
	private String encodeString;

	

	public Internship(String id,String intern_name,String intern_des,String status,String nor,Date start_date,Date end_date,InputStream inputStream, Timestamp timestamp)
	{
		this.id=id;
		this.name=intern_name;
		this.description=intern_des;
		this.status=status;
		this.nor=nor;
		this.startDate=start_date;
		this.endDate=end_date;
		this.inputStream=inputStream;
		this.timestamp=timestamp;
	}
	
	public Internship()
	{
		
	}
	

	//setter methods
	public void setCertificate(InputStream inputStream) {
		this.inputStream =inputStream;
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
	public void setStatus(String status) {
		this.status=status;
	}
	public void setNor(String nor) {
		this.nor=nor;
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
	public void setEncodedString(String encodeString) {
		this.encodeString = encodeString;
	}
	
	//getter methods
	public InputStream getCertificate() {
		return inputStream;
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
	public String getStatus() {
		return status;
	}
	public String getNor() {
		return nor;
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
	
	public String getEndcodeString() {
		return encodeString;
	}

}
