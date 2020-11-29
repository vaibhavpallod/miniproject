package com.user;

import java.sql.Timestamp;

import com.mysql.cj.jdbc.Blob;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;

public class Achievement {
	private String id;
	private int achievementId;
	private Blob certificate;
	private String name;
	private String description;
	private Date date;
	private Timestamp timestamp;
	private InputStream inputStream;
	private File file;
	private String encodedString;

	public Achievement(String id, String ach_name, String ach_des, Date date,InputStream inputStream, Timestamp timestamp) {
		this.id=id;
//		this.achievementId=ach_name;
		this.name=ach_name;
		this.description=ach_des;
		this.date=date;
		this.inputStream=inputStream;
		this.timestamp=timestamp;
	}

	public Achievement() {
	}

	//setter methods
	public void setCertificate(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public void setAchievementID(int achievementId){
		this.achievementId=achievementId;
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
	public void setDate(Date date) {
		this.date=date;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp=timestamp;
	}
	
	public void setFile(File file) {
		this.file=file;
	}
	
	public void setEncodedString(String encString) {
		this.encodedString=encString;
	}
	//getter methods
	public InputStream getCertificate() {
		return inputStream;
	}
	public int getAchievementID(){
		return achievementId;
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
	public Date getDate() {
		return date;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	
	public File getFile() {
		return file;
	}
	
	public String getEncodedString() {
		return encodedString;
	}
}


//public Achievement(String id,int achievementId,String name,String description,Date date,Blob certificate,Timestamp timestamp) {
//this.id=id;
//this.achievementId=achievementId;
//this.name=name;
//this.description=description;
//this.date=date;
//this.certificate=certificate;
//this.timestamp=timestamp;
////id=name=description=null;
////date=null;
////timestamp=null;
////certificate = null;
//}
