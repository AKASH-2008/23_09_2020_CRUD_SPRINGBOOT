package com.assingment.springrest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.assingment.springrest.entity.Branch;
import com.assingment.springrest.entity.Business;

public interface BranchRepository extends JpaRepository<Branch, Integer>{

	@Query(value="SELECT EXISTS(SELECT * FROM BRANCH_MASTER WHERE BRANCH_ID = ?1)", nativeQuery = true)
	Boolean findByBranchIdExists(Integer branch_id);
	
	
	
}
