package com.assingment.springrest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.assingment.springrest.entity.Branch;
import com.assingment.springrest.entity.Business;

public interface BranchRepository extends JpaRepository<Branch, Integer>{

	//@Query("SELECT bum from Business_Master bum where bum.pan=?1")
	
	//@Query("SELECT brm from Branch_Master brm WHERE brm.branch_address=?1")
	//List<Business> findByBranchAddress(String branch_address);
	
}
