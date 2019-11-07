package com.hcl.mediclaim.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.hcl.mediclaim.repository.MedicalClaimRepositoty;
import org.springframework.stereotype.Service;

import com.hcl.mediclaim.dto.MedicalClaimRequestdto;
import com.hcl.mediclaim.entity.MedicalClaim;

@Service
public class MedicalClaimServiceImpl implements MedicalClaimService{

	@Autowired	
	public MedicalClaimRepositoty medicalClaimRepositoty;
	
	
	public String registerMedicalClaim(MedicalClaimRequestdto medicalClaimRequestdto) throws Exception {
		
		MedicalClaim medicalClaim = new MedicalClaim();
		medicalClaim.setAdmittedDate(medicalClaimRequestdto.getAdmittedDate());
		medicalClaim.setClaimAmount(medicalClaimRequestdto.getClaimAmount());
		medicalClaim.setDischargeDate(medicalClaimRequestdto.getDischargeDate());
		medicalClaim.setHospitalId(medicalClaimRequestdto.getHospitalId());
		medicalClaim.setId(medicalClaimRequestdto.getId());
		medicalClaim.setPolicyId(medicalClaimRequestdto.getPolicyId());
		medicalClaimRepositoty.save(medicalClaim);
		return "Claim Raised successfully";
	}

}
