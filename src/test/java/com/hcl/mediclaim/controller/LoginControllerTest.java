package com.hcl.mediclaim.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.mediclaim.dto.LoginRequestdto;
import com.hcl.mediclaim.dto.LoginResponsedto;
import com.hcl.mediclaim.exception.GeneralException;
import com.hcl.mediclaim.service.LoginServiceImpl;

@RunWith(MockitoJUnitRunner.class)

@WebAppConfiguration
public class LoginControllerTest {
	private static Logger logger = LoggerFactory.getLogger(UserControllerTest.class);
	@InjectMocks
	UserController userController;

	@Mock
	LoginServiceImpl loginServiceImpl;
	
	MockMvc mockMvc;
	
	LoginRequestdto loginRequestdto;
	LoginResponsedto loginResponsedto;
	
	public LoginRequestdto getUserLoginRequestdto() {
		loginRequestdto = new LoginRequestdto();
		loginRequestdto.setUserMail("happy@gmail.com");
		loginRequestdto.setPass("happy");
		return loginRequestdto;
	}

	public LoginResponsedto getUserLoginResponsedto() {
		loginResponsedto = new LoginResponsedto();
		loginResponsedto.setMessage("User Logged in Successfully");
		return loginResponsedto;
	}
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
		loginRequestdto = getUserLoginRequestdto();
		loginResponsedto = getUserLoginResponsedto();	
	}

	@Test
	public void userLoginTest() throws Exception{
		logger.info("Entering into userLoginTest");
		
		mockMvc.perform(MockMvcRequestBuilders.post("/claimUser/login").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(asJsonString(loginRequestdto)));
						
	}

	/*
	 * @Test(expected = GeneralException.class) public void userLoginNegativeTest()
	 * throws Exception { logger.info("Entering into userLoginNegativeTest");
	 * Mockito.when(loginServiceImpl.login(Mockito.any())).thenReturn(null);
	 * ResponseEntity<LoginResponsedto> responseClaimApproveDto =
	 * userController.login(loginRequestdto);
	 * Assert.assertNotNull(responseClaimApproveDto); }
	 */
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
