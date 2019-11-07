package com.hcl.mediclaim.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="claimStatus")
public class ClaimStatus {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int claimStatusId;
	private String claimStatus;
	private int approver;
	private int superiorApprover;
	private int primaryApproverStatus;
	private int superiorApproverStatus;
	private int medicalClaimId;
	private String comments;
	
	public String getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}
	public int getApprover() {
		return approver;
	}
	public void setApprover(int approver) {
		this.approver = approver;
	}
	public int getSuperiorApprover() {
		return superiorApprover;
	}
	public void setSuperiorApprover(int superiorApprover) {
		this.superiorApprover = superiorApprover;
	}
	public int getPrimaryApproverStatus() {
		return primaryApproverStatus;
	}
	public void setPrimaryApproverStatus(int primaryApproverStatus) {
		this.primaryApproverStatus = primaryApproverStatus;
	}
	public int getSuperiorApproverStatus() {
		return superiorApproverStatus;
	}
	public void setSuperiorApproverStatus(int superiorApproverStatus) {
		this.superiorApproverStatus = superiorApproverStatus;
	}
	public int getMedicalClaimId() {
		return medicalClaimId;
	}
	public void setMedicalClaimId(int medicalClaimId) {
		this.medicalClaimId = medicalClaimId;
	}
	public int getClaimStatusId() {
		return claimStatusId;
	}
	public void setClaimStatusId(int claimStatusId) {
		this.claimStatusId = claimStatusId;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
				
}
