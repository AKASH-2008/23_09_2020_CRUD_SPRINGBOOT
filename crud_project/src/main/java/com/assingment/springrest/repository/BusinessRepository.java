package com.assingment.springrest.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.assingment.springrest.dto.BusinessResponse;
import com.assingment.springrest.entity.Branch;
import com.assingment.springrest.entity.Business;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Integer>{

	@Query(value="SELECT * from Business_Master bum join Branch_Master brm on bum.business_id = brm.business_id where brm.branch_address=?1", nativeQuery = true)
	List<Business> featchByBranchAddress(String branch_address);
	//List<Business> findByContact_no(Long contact_no);
	
	@Query(value="SELECT * from Business_Master bum join Branch_Master brm on bum.business_id = brm.business_id where brm.active_ind=?1", nativeQuery = true)
	List<Business> featchByBranchActiveInd(boolean active_ind);
	
	@Query(value="SELECT * from Business_Master bum join Branch_Master brm on bum.business_id = brm.business_id where brm.created_date=?1", nativeQuery = true)
	List<Business> featchByBranchCreateDate(Date created_date);
	
	//@Query(value="SELECT * from Business_Master bum join Branch_Master brm on bum.business_id = brm.business_id where brm.branch_address=?1", navtiveQueary = true)
	//List<Business> findByBranch_Id(String branch_address);
	
	@Query("SELECT bum from Business_Master bum where bum.business_name=?1")
	List<Business> featchByBusiness_name(String business_name);
	
	@Query("SELECT bum from Business_Master bum where bum.pan=?1")
	List<Business> featchByPan(Integer pan);
	
}
