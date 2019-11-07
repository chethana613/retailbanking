package com.hcl.mediclaim.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hcl.mediclaim.dto.ClaimStatusServiceResponsedto;
import com.hcl.mediclaim.entity.ClaimStatus;

@Service
public interface ClaimStatusService {

	public ClaimStatusServiceResponsedto claimStatusService(int primaryApprover,int superiorApprover,int medicalClaimId) throws Exception;
	
	public ClaimStatusServiceResponsedto checkClaimStatus(int claimStatusId)throws Exception;
	
	public String approveClaim(int claimStatusId) throws Exception;
	
	public String rejectClaim(int claimStatusId,String comments) throws Exception;
	
	public String assignClaimToSuperior(int claimStatusId) throws Exception;
	
	public ClaimStatusServiceResponsedto viewClaim(int claimStatusId) throws Exception;
	
	
}
