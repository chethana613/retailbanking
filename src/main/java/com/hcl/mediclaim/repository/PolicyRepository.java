package com.hcl.mediclaim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.mediclaim.entity.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy,Integer> {
	
	/*
	 * @Query(value="select * from policy p where p.policy_id=?1",nativeQuery=true)
	 * public Policy findByPolicy(int policyId);
	 */	
	Policy findBypolicyId(int policyId);
	

}
