
package com.hcl.mediclaim.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.hcl.mediclaim.dto.ClaimStatusServiceResponsedto;
import com.hcl.mediclaim.service.ClaimStatusService;
import com.hcl.mediclaim.service.ClaimStatusServiceImpl;

@RunWith(MockitoJUnitRunner.class)

public class ClaimControllerTest {
	
	@Mock
	ClaimStatusService claimStatusService;
	
	@Mock
	ClaimStatusServiceImpl claimStatusServiceImpl;
	
	@InjectMocks
	ClaimController ClaimController;

	@Test
	public void raiseClaimTest() throws Exception{
		ClaimStatusServiceResponsedto claimStatusServiceResponsedto= new ClaimStatusServiceResponsedto();
		claimStatusServiceResponsedto.setClaimStatusId(1);
		claimStatusServiceResponsedto.setMessage("success");
		claimStatusServiceResponsedto.setStatusCode(200);
		Mockito.when(claimStatusService.claimStatusService(1, 2, 3)).thenReturn(claimStatusServiceResponsedto);
		ResponseEntity<ClaimStatusServiceResponsedto> claimResponse = ClaimController.raiseClaim(1, 2, 3);
		assertNotNull(claimResponse);
	}
	
	
}
