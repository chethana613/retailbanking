package com.hcl.mediclaim.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.mediclaim.constants.ExceptionConstants;
import com.hcl.mediclaim.dto.UserRegistrationResponsedto;
import com.hcl.mediclaim.dto.UserRegistrationdto;
import com.hcl.mediclaim.entity.ClaimUser;
import com.hcl.mediclaim.exception.UserException;
import com.hcl.mediclaim.repository.UserRepository;
@Service
public class ClaimServiceRegisterImpl implements ClaimServiceRegister{
	
	@Autowired
	public UserRepository userRepository;
	
	

	public UserRegistrationResponsedto register(UserRegistrationdto ClaimRequestdto) throws Exception {
		
		Optional<ClaimUser> checkRegisterForEmail=userRepository.findByuserMail(ClaimRequestdto.getUserMail());
		if(checkRegisterForEmail.isPresent()) {
			throw new UserException(ExceptionConstants.EMAIL_EXISTS);
		}
		ClaimUser ClaimUser= new ClaimUser();
		BeanUtils.copyProperties(ClaimRequestdto, ClaimUser);
		
		ClaimUser.setUserMail(ClaimRequestdto.getUserMail());
		ClaimUser.setPass(ClaimRequestdto.getPass());
		ClaimUser.setPhone(ClaimRequestdto.getPhone());		
		ClaimUser responseUser= userRepository.save(ClaimUser);		
		userRepository.save(ClaimUser);
		
		UserRegistrationResponsedto userRegistrationResponsedto= new UserRegistrationResponsedto();	
		userRegistrationResponsedto.setUserId(responseUser.getId());
		userRegistrationResponsedto.setStatusCode(200);
		userRegistrationResponsedto.setMessage("Created");
		return userRegistrationResponsedto;		
	}
}



	