package com.assingment.springrest.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.assingment.springrest.dto.BusinessRequest;
import com.assingment.springrest.dto.BusinessResponse;
import com.assingment.springrest.entity.Business;

@Service
public interface SpringCRUDService {
	
	Business add(BusinessRequest request);
	Business update(BusinessRequest request, Integer id);
	List<Business> allDataTestApp();
	Optional<Business> allDataTestAppById(Integer id);
	Optional<List<Business>> search(String business_name, Integer pan, String branch_address, Boolean active_ind, Date created_date);

}
