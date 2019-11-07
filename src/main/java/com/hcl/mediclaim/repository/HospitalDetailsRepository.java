package com.hcl.mediclaim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.mediclaim.entity.HospitalDetails;

@Repository
public interface HospitalDetailsRepository extends JpaRepository<HospitalDetails,Integer>{

	/*
	 * @Query(value="select * from hospital h where h.hospital_id=?1",nativeQuery=
	 * true) public HospitalDetails findByHospitalId(int hospitalId) throws
	 * Exception;
	 */
	
		HospitalDetails findByhospitalId(int hospitalId);
}
