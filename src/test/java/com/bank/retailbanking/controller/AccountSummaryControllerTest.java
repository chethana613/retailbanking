package com.bank.retailbanking.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.bank.retailbanking.dto.AccountSummaryResponsedto;
import com.bank.retailbanking.exception.GeneralException;
import com.bank.retailbanking.service.AccountSummaryServiceImplementation;

@RunWith(MockitoJUnitRunner.Silent.class)
public class AccountSummaryControllerTest {
	@InjectMocks
	AccountSummaryController accountSummaryController;
	
	@Mock
	AccountSummaryServiceImplementation accountSummaryServiceImplementation;
	
	AccountSummaryResponsedto accountSummaryResponsedto= null;
	
	@Before
	public void setUp() {
		accountSummaryResponsedto= new AccountSummaryResponsedto();
		accountSummaryResponsedto.setAccountBalance(4000.00);
		accountSummaryResponsedto.setAccountNumber(10000L);

	}
	@Test
	public void testGetAccountSummary() throws GeneralException {
		Mockito.when(accountSummaryServiceImplementation.getAccountSummary(5L)).thenReturn(accountSummaryResponsedto);
		ResponseEntity<AccountSummaryResponsedto> accountSummaryResponsedto =accountSummaryController.getAccountSummary(5L);
		Assert.assertNotNull(accountSummaryResponsedto);
	}
	
}
