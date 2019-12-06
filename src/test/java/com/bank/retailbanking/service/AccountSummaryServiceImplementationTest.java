package com.bank.retailbanking.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import com.bank.retailbanking.constants.ApplicationConstants;
import com.bank.retailbanking.dto.AccountSummaryResponsedto;
import com.bank.retailbanking.entity.Customer;
import com.bank.retailbanking.entity.CustomerAccountDetails;
import com.bank.retailbanking.entity.CustomerTransactions;
import com.bank.retailbanking.exception.GeneralException;
import com.bank.retailbanking.repository.CustomerAccountDetailsRepository;
import com.bank.retailbanking.repository.CustomerRepository;
import com.bank.retailbanking.repository.CustomerTransactionsRepository;

import lombok.extern.slf4j.Slf4j;

@RunWith(MockitoJUnitRunner.Silent.class)
@Slf4j
public class AccountSummaryServiceImplementationTest {

	@InjectMocks
	AccountSummaryServiceImplementation accountSummaryServiceImplementation;
	
	@Mock
	CustomerTransactionsRepository customerTransactionsRepository;
	
	@Mock
	CustomerAccountDetailsRepository customerAccountDetailsRepository;
	
	@Mock
	CustomerRepository customerRepository;
	
	Customer customer= null;
	Customer customer1= null;
	CustomerAccountDetails customerAccountDetails=null;
	CustomerTransactions customerTransactions=null;
	List<CustomerTransactions> customerTransactionsList= new ArrayList<>();
	Pageable pageable;
	@Before
	public void setup() {
		customer= new Customer();
		customer.setCustomerId(1000L);
		customer.setCustomerEmail("c@gmail.com");
		
		customer1= new Customer();
		customer1.setCustomerId(2000L);
		customer1.setCustomerEmail("ca@gmail.com");
		
		customerAccountDetails= new CustomerAccountDetails();
		customerAccountDetails.setAccountNumber(100001L);
		LocalDate openingDate= LocalDate.parse("2019-06-19");
		customerAccountDetails.setAccountOpeningDate(openingDate);
		customerAccountDetails.setAccountType("savings");
		customerAccountDetails.setAvailableBalance(3000.00);
		customerAccountDetails.setCustomerId(customer);
		
		customerTransactions= new CustomerTransactions();
		customerTransactions.setAccountNumber(customerAccountDetails);
		customerTransactions.setTransactionAmount(300.00);
		customerTransactions.setTransactionComments("rent bill");
		LocalDate transactionDate= LocalDate.parse("2019-06-19");
		customerTransactions.setTransactionDate(transactionDate);
		customerTransactions.setTransactionId(1001L);
		customerTransactions.setTransactionStatus("Success");
		customerTransactions.setTransactionType("debit");
		customerTransactionsList.add(customerTransactions);
		pageable = PageRequest.of(0, ApplicationConstants.TRANSACTION_HISTORY_COUNT, Direction.DESC,"transactionId");
	}
	@Test
	public void testGetAccountSummary() throws GeneralException {
		log.info("Entering into testGetAccountSummary of AccountSummaryServiceImplementationTest");
		Mockito.when(customerRepository.findById(Mockito.any())).thenReturn(Optional.of(customer));
		Mockito.when(customerAccountDetailsRepository.findByCustomerId(customer)).thenReturn(Optional.of(customerAccountDetails));
		Mockito.when(customerTransactionsRepository.findByAccountNumber(customerAccountDetails,pageable)).thenReturn(customerTransactionsList);
		AccountSummaryResponsedto accountSummaryResponsedto=accountSummaryServiceImplementation.getAccountSummary(1000L);
		Assert.assertNotNull(accountSummaryResponsedto);
	}
	
	@Test(expected=GeneralException.class)
	public void testGetInvalidCustomerNegative() throws GeneralException {
		Mockito.when(customerRepository.findById(5L)).thenReturn(Optional.of(customer));
		accountSummaryServiceImplementation.getAccountSummary(1000L);
	}
	
	@Test(expected=GeneralException.class)
	public void testGetInvalidCustomerAccountNegative() throws GeneralException {
		Mockito.when(customerRepository.findById(5L)).thenReturn(Optional.of(customer));
		Mockito.when(customerAccountDetailsRepository.findByCustomerId(customer1)).thenReturn(Optional.of(customerAccountDetails));		
		accountSummaryServiceImplementation.getAccountSummary(5L);
	}
}
