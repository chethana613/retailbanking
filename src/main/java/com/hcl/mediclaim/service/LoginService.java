package com.hcl.mediclaim.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hcl.mediclaim.dto.LoginRequestdto;
import com.hcl.mediclaim.dto.LoginResponsedto;

@Service
public interface LoginService {


	public LoginResponsedto login(LoginRequestdto loginRequestdto) throws Exception;
	
}
