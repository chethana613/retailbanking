package com.hcl.mediclaim.dto;

import java.io.Serializable;

public class ClaimStatusServiceResponsedto implements Serializable{
	private static final long serialVersionUID=1L;
	

	private Integer claimStatusId;
	private String message;
	private Integer statusCode;
	private Integer mediclaimID;
	private String hospitalName;
	private String policyName;
	private Long policyAmount;
	
	public Integer getClaimStatusId() {
		return claimStatusId;
	}
	public void setClaimStatusId(Integer claimStatusId) {
		this.claimStatusId = claimStatusId;
	}
	

	public Integer getMediclaimID() {
		return mediclaimID;
	}
	public void setMediclaimID(Integer mediclaimID) {
		this.mediclaimID = mediclaimID;
	}
	
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	public Long getPolicyAmount() {
		return policyAmount;
	}
	public void setPolicyAmount(Long policyAmount) {
		this.policyAmount = policyAmount;
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
