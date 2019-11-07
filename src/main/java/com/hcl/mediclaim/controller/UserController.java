package com.hcl.mediclaim.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.mediclaim.dto.LoginRequestdto;
import com.hcl.mediclaim.dto.LoginResponsedto;
import com.hcl.mediclaim.dto.UserRegistrationResponsedto;
import com.hcl.mediclaim.dto.UserRegistrationdto;
import com.hcl.mediclaim.service.ClaimServiceRegister;
import com.hcl.mediclaim.service.LoginService;

@RestController
@RequestMapping("/claimUser")
public class UserController {
	
	private static final Logger logger=LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private ClaimServiceRegister ClaimServiceRegister;
		
	@Autowired
	private LoginService LoginService;
	
	@PostMapping("/register")
	public ResponseEntity<UserRegistrationResponsedto> register(@RequestBody UserRegistrationdto  ClaimRequestdto) throws Exception{
		logger.info("Inside Registation Controller");
		UserRegistrationResponsedto response=ClaimServiceRegister.register(ClaimRequestdto);
		return new ResponseEntity<>(response, HttpStatus.CREATED);		
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<LoginResponsedto> login(@RequestBody LoginRequestdto  loginRequestdto) throws Exception{
		logger.info("Inside Login Controller");
		LoginResponsedto response=LoginService.login(loginRequestdto);
		return  new ResponseEntity<>(response, HttpStatus.OK);		
	}
}
