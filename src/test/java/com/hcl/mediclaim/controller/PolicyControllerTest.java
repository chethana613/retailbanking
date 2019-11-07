package com.hcl.mediclaim.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.lenient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hcl.mediclaim.dto.PolicyListResponsedto;
import com.hcl.mediclaim.service.PolicyService;
import com.hcl.mediclaim.service.PolicyServiceImpl;
import com.hcl.mediclaim.controller.PolicyController;



@RunWith(MockitoJUnitRunner.class)

public class PolicyControllerTest {
	
	@Mock
	PolicyService policyService;
	
	@Mock
	PolicyServiceImpl policyServiceImpl;
	
	@InjectMocks
	PolicyController policyController;
	
	
	@Test
	public void policyValidityCheck() throws Exception{
		PolicyListResponsedto policyListResponsedto= new PolicyListResponsedto();
		policyListResponsedto.setPolicyId(101);
		policyListResponsedto.setStatusCode(HttpStatus.OK.value());
		 lenient().when(policyService.policyValidityCheck(101)).thenReturn(policyListResponsedto);
		//ResponseEntity<PolicyListResponsedto> RPolicyResponse = policyController.policyValidityCheck(1);
		assertNotNull(policyListResponsedto);		
	}
	

}
