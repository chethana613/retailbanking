package com.hcl.mediclaim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.mediclaim.dto.MedicalClaimRequestdto;
import com.hcl.mediclaim.entity.MedicalClaim;

@Repository
public interface MedicalClaimRepositoty extends JpaRepository<MedicalClaim,Integer>{

	/*
	 * @Query(value="select * from medical_claim m where m.medical_claim_id=?1"
	 * ,nativeQuery=true) public MedicalClaim findMediClaimById(int medicalClaimId)
	 * throws Exception;
	 */
	MedicalClaim findBymedicalClaimId(int medicalClaimId);
	
}
