package com.hcl.mediclaim.dto;

import java.time.LocalDate;

public class MedicalClaimRequestdto {

	

	private LocalDate admittedDate;
	private LocalDate dischargeDate;
	private int policyId;
	private int hospitalId;
	private long claimAmount;
	private int id;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getClaimAmount() {
		return claimAmount;
	}
	public void setClaimAmount(long claimAmount) {
		this.claimAmount = claimAmount;
	}
	public int getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(int hospitalId) {
		this.hospitalId = hospitalId;
	}

	public LocalDate getAdmittedDate() {
		return admittedDate;
	}
	public void setAdmittedDate(LocalDate admittedDate) {
		this.admittedDate = admittedDate;
	}
	public LocalDate getDischargeDate() {
		return dischargeDate;
	}
	public void setDischargeDate(LocalDate dischargeDate) {
		this.dischargeDate = dischargeDate;
	}
	public int getPolicyId() {
		return policyId;
	}
	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}
	
}
