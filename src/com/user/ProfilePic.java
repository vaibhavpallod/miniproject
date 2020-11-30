package com.user;

import java.io.InputStream;

public class ProfilePic {

	String userID ;
	InputStream inputStream;
	String encodedString;
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public String getEncodedString() {
		return encodedString;
	}
	public void setEncodedString(String encodedString) {
		this.encodedString = encodedString;
	}
	
	
}
