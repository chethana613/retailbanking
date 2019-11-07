package com.hcl.mediclaim.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.mediclaim.dto.ClaimStatusServiceResponsedto;
import com.hcl.mediclaim.entity.ClaimStatus;
import com.hcl.mediclaim.entity.HospitalDetails;
import com.hcl.mediclaim.entity.MedicalClaim;
import com.hcl.mediclaim.entity.Policy;
import com.hcl.mediclaim.repository.ClaimStatusRepository;
import com.hcl.mediclaim.repository.HospitalDetailsRepository;
import com.hcl.mediclaim.repository.MedicalClaimRepositoty;
import com.hcl.mediclaim.repository.PolicyRepository;
import com.hcl.mediclaim.repository.UserPolicyClaimRepository;


@Service
public class ClaimStatusServiceImpl implements ClaimStatusService{

	@Autowired
	public ClaimStatusRepository claimStatusRepository;
	
	@Autowired
	public MedicalClaimRepositoty medicalClaimRepositoty;
	
	@Autowired
	public UserPolicyClaimRepository userPolicyClaimRepository;
	
	@Autowired
	public PolicyServiceImpl policyServiceImpl;
	
	@Autowired
	public PolicyRepository policyRepository;
	
	@Autowired
	public HospitalDetailsRepository hospitalDetailsRepository;
	
	public ClaimStatusServiceResponsedto claimStatusService(int primaryApprover, int superiorApprover, int medicalClaimId) throws Exception {
		
		ClaimStatus claimStatus= new ClaimStatus();
		claimStatus.setApprover(primaryApprover);
		claimStatus.setSuperiorApprover(superiorApprover);
		claimStatus.setMedicalClaimId(medicalClaimId);
		claimStatus.setPrimaryApproverStatus(1);
		claimStatus.setSuperiorApproverStatus(0);
		claimStatus.setClaimStatus("Pending");
		ClaimStatus responseuser=claimStatusRepository.save(claimStatus);	
		
		ClaimStatusServiceResponsedto claimStatusServiceResponsedto= new ClaimStatusServiceResponsedto();
		claimStatusServiceResponsedto.setClaimStatusId(responseuser.getClaimStatusId());
		claimStatusServiceResponsedto.setMessage("Claim Raised successfully");
		claimStatusServiceResponsedto.setStatusCode(200);
		
		return claimStatusServiceResponsedto;
	}
	
	public ClaimStatusServiceResponsedto checkClaimStatus(int claimStatusId) throws Exception {
		
		
		Optional<ClaimStatus> claimStatusUserInfo=claimStatusRepository.findByclaimStatusId(claimStatusId);
		ClaimStatus claimStatus=null;
		ClaimStatusServiceResponsedto claimStatusServiceResponsedto= new ClaimStatusServiceResponsedto();
		
		if(claimStatusUserInfo.isPresent()) {
			claimStatus = claimStatusUserInfo.get();
			

			int pas=claimStatus.getPrimaryApproverStatus();
			int sas=claimStatus.getSuperiorApproverStatus();
			String oas=claimStatus.getClaimStatus();
			
			
			if(pas==1) {
				claimStatusServiceResponsedto.setMessage("Claim Status:"+oas+","+"APPROVAL PENDING AT L1");
			}
			else if(pas==3) {
				claimStatusServiceResponsedto.setMessage("Claim Status:"+oas+","+"APPROVAL REJECTED AT L1");
			}
			else if(pas==2 && sas==0 || pas==2 && sas==2) {
				claimStatusServiceResponsedto.setMessage("Claim Status:"+oas);
				
			}
			else if(pas==2 && sas==1) {
				claimStatusServiceResponsedto.setMessage("Claim Status:"+oas+","+"APPROVAL PENDING AT L2");
			}
			else if(pas==2 && sas==3) {
				claimStatusServiceResponsedto.setMessage("Claim Status:\"+oas+\",\"+\"APPROVAL REJECTED AT L2");
				
			}
			claimStatusServiceResponsedto.setMessage("Claim Status:"+oas);
			claimStatusServiceResponsedto.setClaimStatusId(claimStatusId);
			claimStatusServiceResponsedto.setStatusCode(200);
			claimStatusServiceResponsedto.setMediclaimID(claimStatus.getMedicalClaimId());
			return claimStatusServiceResponsedto;
		} else {
			throw new RuntimeException("");
		}
		
		
	}

	public String approveClaim(int claimStatusId) throws Exception {
		
		Optional<ClaimStatus> claimStatusUserInfo=claimStatusRepository.findByclaimStatusId(claimStatusId);
		ClaimStatus claimStatus=null;
		if(claimStatusUserInfo.isPresent()) {
			claimStatus=claimStatusUserInfo.get();
		}
		
		if(claimStatus.getClaimStatus().equalsIgnoreCase("Pending") &&  claimStatus.getPrimaryApproverStatus()==1) {	
		claimStatus.setPrimaryApproverStatus(2);
		claimStatus.setClaimStatus("Approved");
		claimStatusRepository.save(claimStatus);
		return "Claim Status Approved";
		}
		else if(claimStatus.getClaimStatus().equalsIgnoreCase("Pending") &&  claimStatus.getPrimaryApproverStatus()==2 && claimStatus.getSuperiorApproverStatus()==1) {	
			claimStatus.setSuperiorApproverStatus(2);
			claimStatus.setClaimStatus("Approved");
			claimStatusRepository.save(claimStatus);
			return "Claim Status Approved";
			}
		else {
			return "Claim Already" +claimStatus.getClaimStatus();
		}
		
	}

		public String rejectClaim(int claimStatusId,String comments) throws Exception {
		
			Optional<ClaimStatus> claimStatusUserInfo=claimStatusRepository.findByclaimStatusId(claimStatusId);
			ClaimStatus claimStatus=null;
			if(claimStatusUserInfo.isPresent()) {
				claimStatus=claimStatusUserInfo.get();
			}
		if(claimStatus.getClaimStatus().equalsIgnoreCase("Pending") &&  claimStatus.getPrimaryApproverStatus()==1) {
		claimStatus.setPrimaryApproverStatus(3);
		claimStatus.setClaimStatus("Rejected");
		claimStatusRepository.save(claimStatus);
		return "Claim Status Rejected at L1";
		}
		else if(claimStatus.getClaimStatus().equalsIgnoreCase("Pending") &&  claimStatus.getPrimaryApproverStatus()==2 && claimStatus.getSuperiorApproverStatus()==1) {
			claimStatus.setSuperiorApproverStatus(3);
			claimStatus.setClaimStatus("Rejected");
			claimStatusRepository.save(claimStatus);
			return "Claim Status Rejected at L2";
		}
		else {
			return "Claim Already" +claimStatus.getClaimStatus();
		}
	}

	public String assignClaimToSuperior(int claimStatusId) throws Exception {
		
		Optional<ClaimStatus> claimStatusUserInfo=claimStatusRepository.findByclaimStatusId(claimStatusId);
		ClaimStatus claimStatus=null;
		if(claimStatusUserInfo.isPresent()) {
			claimStatus=claimStatusUserInfo.get();
		}
		if(claimStatus.getClaimStatus().equalsIgnoreCase("Pending")&&  claimStatus.getPrimaryApproverStatus()==1 && claimStatus.getSuperiorApproverStatus()==0){	
		claimStatus.setPrimaryApproverStatus(2);
		claimStatus.setSuperiorApproverStatus(1);
		claimStatusRepository.save(claimStatus);
		return "Claim Assigned to L2";
		}
		else {
			return "Cannot Assign Claim to L2";
		}
	}

	public ClaimStatusServiceResponsedto viewClaim(int claimStatusId) throws Exception {
		Optional<ClaimStatus> claimStatusUserInfo=claimStatusRepository.findByclaimStatusId(claimStatusId);
		ClaimStatus claimStatus=null;
		ClaimStatusServiceResponsedto claimStatusServiceResponsedto= new ClaimStatusServiceResponsedto();
		if(claimStatusUserInfo.isPresent()) {
			claimStatus=claimStatusUserInfo.get();
		}
		
		boolean isWaiverAvailable=false;
		
			MedicalClaim medicalClaim=medicalClaimRepositoty.findBymedicalClaimId(claimStatus.getMedicalClaimId());
			//UserPolicyClaim userPolicyClaim=userPolicyClaimRepository.findByPolicyDate(medicalClaim.getPolicyId());
			Policy policy=policyRepository.findBypolicyId(medicalClaim.getPolicyId());
			HospitalDetails hospitalDetails=hospitalDetailsRepository.findByhospitalId(medicalClaim.getHospitalId());
			
			if(hospitalDetails.getPolicyId()==medicalClaim.getPolicyId()) {
				isWaiverAvailable=true;
			}
			if(policyServiceImpl.policyValidityCheck(medicalClaim.getPolicyId()).getMessage().equals("Valid Policy")) {
			
			if(isWaiverAvailable) {
				claimStatusServiceResponsedto.setPolicyAmount(policy.getPolicyAmount()*(85)/100);
				
			}
			else {
				claimStatusServiceResponsedto.setPolicyAmount(policy.getPolicyAmount()*(80)/100);
			}
			
			
			
			claimStatusServiceResponsedto.setClaimStatusId(claimStatus.getClaimStatusId());
			claimStatusServiceResponsedto.setHospitalName(hospitalDetails.getHospitalName());
			claimStatusServiceResponsedto.setPolicyName(policy.getPolicyName());
			claimStatusServiceResponsedto.setPolicyAmount(50L);
			claimStatusServiceResponsedto.setMessage("Success");
			claimStatusServiceResponsedto.setMediclaimID(claimStatus.getMedicalClaimId());
			claimStatusServiceResponsedto.setStatusCode(200);
			
			
			}
			
		
		return claimStatusServiceResponsedto;
	}
	
}
