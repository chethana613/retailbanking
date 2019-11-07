package com.hcl.mediclaim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.mediclaim.dto.PolicyListResponsedto;
import com.hcl.mediclaim.service.PolicyService;

@RestController
@RequestMapping("/policy")
public class PolicyController {
	
	@Autowired
	private PolicyService policyService;
	
	@RequestMapping(value = "/isPolicyValid/{policyID}", method = RequestMethod.GET)
	public ResponseEntity<PolicyListResponsedto> policyValidityCheck(@PathVariable(value="policyID") int policyID) throws Exception{
		PolicyListResponsedto userPolicyClaim=policyService.policyValidityCheck(policyID);	
		return new ResponseEntity<>(userPolicyClaim, HttpStatus.OK);		
	}

}
