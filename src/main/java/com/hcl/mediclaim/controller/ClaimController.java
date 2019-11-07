package com.hcl.mediclaim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.mediclaim.dto.ClaimStatusServiceResponsedto;
import com.hcl.mediclaim.dto.MedicalClaimRequestdto;
import com.hcl.mediclaim.dto.UserRegistrationResponsedto;
import com.hcl.mediclaim.service.ClaimStatusService;
import com.hcl.mediclaim.service.MedicalClaimService;

@RestController
@RequestMapping("/claim")

public class ClaimController {
	
	@Autowired
	private MedicalClaimService medicalClaimService;
	
	@Autowired
	private ClaimStatusService claimStatusService;
	

	@PostMapping("/medicalClaim")
	public String registerMedicalClaim(@RequestBody MedicalClaimRequestdto medicalClaimRequestdto) throws Exception{	
		return medicalClaimService.registerMedicalClaim(medicalClaimRequestdto);
	}
	
	@RequestMapping(value = "/raiseClaim/{primaryApprover}/{superiorApprover}/{medicalClaimId}", method = RequestMethod.POST)
	public ResponseEntity<ClaimStatusServiceResponsedto> raiseClaim(@PathVariable(value="primaryApprover") int primaryApprover,@PathVariable(value="superiorApprover") int superiorApprover,@PathVariable(value="medicalClaimId") int medicalClaimId) throws Exception{	
		ClaimStatusServiceResponsedto response=claimStatusService.claimStatusService(primaryApprover,superiorApprover,medicalClaimId);
		return new ResponseEntity<>(response, HttpStatus.CREATED);	
	}
	
	@RequestMapping(value="/checkClaimStatus/{claimStatusID}", method=RequestMethod.GET)
	public ResponseEntity<ClaimStatusServiceResponsedto> checkClaimStatus(@PathVariable(value="claimStatusID") int claimStatusID) throws Exception{
		ClaimStatusServiceResponsedto response= claimStatusService.checkClaimStatus(claimStatusID);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/approve/{claimStatusID}",method=RequestMethod.PUT)
	public String approveClaim(@PathVariable(value="claimStatusID") int claimStatusID) throws Exception{
		return claimStatusService.approveClaim(claimStatusID);
		
	}
	
	@RequestMapping(value="/rejectClaim/{claimStatusID}/{comments}",method=RequestMethod.PUT)
	public String rejectClaim(@PathVariable(value="claimStatusID") int claimStatusID, @PathVariable(value="comments") String comments)throws Exception {
		return claimStatusService.rejectClaim(claimStatusID,comments);
	}
	
	@RequestMapping(value="/assignClaimToSuperior/{claimStatusID}",method=RequestMethod.PUT)
	public String assignClaimToSuperior(@PathVariable(value="claimStatusID") int claimStatusID)throws Exception {
		return claimStatusService.assignClaimToSuperior(claimStatusID);
	}
	
	@RequestMapping(value="/viewClaim/{claimStatusID}",method=RequestMethod.GET)
	public ResponseEntity<ClaimStatusServiceResponsedto> viewClaim(@PathVariable(value="claimStatusID") int claimStatusID)throws Exception {
		ClaimStatusServiceResponsedto response=claimStatusService.viewClaim(claimStatusID);
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}
}
