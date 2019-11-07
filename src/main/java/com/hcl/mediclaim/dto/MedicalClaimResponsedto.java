package com.hcl.mediclaim.dto;

import java.time.LocalDate;

public class MedicalClaimResponsedto {

	
	private Integer medicalClaimId;
	private String message;
	private Integer statusCode;
	public Integer getMedicalClaimId() {
		return medicalClaimId;
	}
	public void setMedicalClaimId(Integer medicalClaimId) {
		this.medicalClaimId = medicalClaimId;
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
