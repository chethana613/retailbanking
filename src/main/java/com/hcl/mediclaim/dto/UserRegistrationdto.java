package com.hcl.mediclaim.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationdto implements Serializable{
	private static final long serialVersionUID=1L;
	
	private String userMail;
	private String pass;
	private Long phone;
	
	
	 public String getUserMail() { return userMail; } public void
	 setUserMail(String userMail) { this.userMail = userMail; } public String
	 getPass() { return pass; } public void setPass(String pass) { this.pass
	 =pass; } public Long getPhone() { return phone; } public void setPhone(Long
	 phone) { this.phone = phone; }
	 
	 

}
