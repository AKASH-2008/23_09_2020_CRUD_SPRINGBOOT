package com.assingment.springrest.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.assingment.springrest.entity.Branch;
import com.assingment.springrest.entity.Business;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Integer>{

	@Query(value="SELECT * from Business_Master bum inner join Branch_Master brm on bum.business_id = brm.business_id where brm.branch_address=?1", nativeQuery = true)
	List<Business> featchByBranchAddress(String branch_address);
	//List<Business> findByContact_no(Long contact_no);
	
	@Query(value="SELECT * from Business_Master bum inner join Branch_Master brm on bum.business_id = brm.business_id where brm.active_ind=?1", nativeQuery = true)
	List<Business> featchByBranchActiveInd(Boolean active_ind);
	
	@Query(value="SELECT * from BUSINESS_MASTER bum inner join BRANCH_MASTER brm on bum.business_id = brm.business_id where brm.created_date="+'?'+"", nativeQuery = true)
	List<Business> featchByBranchCreateDate(LocalDate created_date);
	
	@Query("SELECT bum from Business_Master bum where bum.business_name=?1")
	List<Business> featchByBusiness_name(String business_name);
	
	@Query("SELECT bum from Business_Master bum where bum.pan=?1")
	List<Business> featchByPan(Integer pan);
	
	@Query(value="SELECT EXISTS(SELECT * FROM BUSINESS_MASTER WHERE BUSINESS_ID = ?1)", nativeQuery = true)
	Boolean findByBusinessIdExists(Integer business_id);
	
}
