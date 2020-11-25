package com.user;

import java.sql.Timestamp;

import com.mysql.cj.jdbc.Blob;

import java.sql.Date;

public class Achievement {
	private String id;
	private int achievementId;
	private Blob certificate;
	private String name;
	private String description;
	private Date date;
	private Timestamp timestamp;
	
	public Achievement() {
		id=name=description=null;
		date=null;
		timestamp=null;
		certificate = null;
	}
	
	//setter methods
	public void setCertificate(Blob certificate) {
		this.certificate = certificate;
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
	
	//getter methods
	public Blob getCertificate() {
		return certificate;
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
	
}
