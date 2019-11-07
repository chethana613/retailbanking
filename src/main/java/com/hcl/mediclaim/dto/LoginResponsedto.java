 package com.hcl.mediclaim.dto;

public class LoginResponsedto {

	private String userName;
	private String pass;
	private String message;
	private Integer statusCode;
	
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	
}
