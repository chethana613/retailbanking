package com.hcl.mediclaim.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.hcl.mediclaim.dto.ClaimStatusServiceResponsedto;
import com.hcl.mediclaim.entity.ClaimStatus;
import com.hcl.mediclaim.repository.ClaimStatusRepository;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ClaimStatusServiceImplTest {

	@InjectMocks
	ClaimStatusServiceImpl claimStatusServiceImpl;

	@Mock
	ClaimStatusRepository claimStatusRepository;

	@Test
	public void checkClaimStatusTest() throws Exception {
		ClaimStatusServiceResponsedto claimStatusServiceResponsedto = new ClaimStatusServiceResponsedto();
		claimStatusServiceResponsedto.setClaimStatusId(1);
		claimStatusServiceResponsedto.setMessage("success");
		claimStatusServiceResponsedto.setStatusCode(200);
		ClaimStatus claimStatus1 = new ClaimStatus();
		claimStatus1.setPrimaryApproverStatus(1);
		claimStatus1.setSuperiorApproverStatus(2);
		claimStatus1.setClaimStatusId(2);
		claimStatus1.setClaimStatus("Pending");
		
		Optional<ClaimStatus> optinalClaimStatus = Optional.of(claimStatus1);		
		lenient().when(claimStatusRepository.findByclaimStatusId(2)).thenReturn(optinalClaimStatus);
		//Mockito.when(claimStatusRepository.findByclaimStatusId(2)).thenReturn(optinalClaimStatus);
		Assert.assertNotNull(optinalClaimStatus);
	}
}
