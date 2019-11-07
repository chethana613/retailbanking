package com.hcl.mediclaim.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.mediclaim.constants.ExceptionConstants;
import com.hcl.mediclaim.dto.PolicyListResponsedto;
import com.hcl.mediclaim.entity.Policy;
import com.hcl.mediclaim.entity.UserPolicyClaim;
import com.hcl.mediclaim.exception.PolicyExpiredException;
import com.hcl.mediclaim.repository.PolicyRepository;
import com.hcl.mediclaim.repository.UserPolicyClaimRepository;

@Service
public class PolicyServiceImpl implements PolicyService {

	@Autowired
	public PolicyRepository policyRepository;

	@Autowired
	public UserPolicyClaimRepository userPolicyClaimRepository;

	@Override
	public PolicyListResponsedto policyValidityCheck(int policyID) throws Exception {

		Policy policy = policyRepository.findBypolicyId(policyID);
		UserPolicyClaim userClaimPolicy = userPolicyClaimRepository.findBypolicyId(policyID);

		if (userClaimPolicy == null || policy == null) {
			throw new PolicyExpiredException(ExceptionConstants.INVALID_USER_POLICY);
		} else {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate now = LocalDate.now();
			LocalDate date = userClaimPolicy.getPolicyExpireDate();
			if (now.isAfter(date)) {
				PolicyListResponsedto policyListResponsedto= new PolicyListResponsedto();	
				policyListResponsedto.setPolicyId(policy.getPolicyId());
				policyListResponsedto.setStatusCode(200);
				policyListResponsedto.setMessage("Valid Policy"); 
				return policyListResponsedto	;
			} else {
				throw new PolicyExpiredException(ExceptionConstants.POLICY_EXPIRED);
			}
		}
	}

}
