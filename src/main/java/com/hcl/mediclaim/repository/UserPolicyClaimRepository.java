package com.hcl.mediclaim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.mediclaim.entity.UserPolicyClaim;

@Repository
public interface UserPolicyClaimRepository extends JpaRepository<UserPolicyClaim,Integer> {

	/*
	 * @Query(value="select * from user_policy_claim p where p.policy_id=?1"
	 * ,nativeQuery=true) public UserPolicyClaim findByPolicyDate(int policyId);
	 */	
	UserPolicyClaim findBypolicyId(int policyId);
}
