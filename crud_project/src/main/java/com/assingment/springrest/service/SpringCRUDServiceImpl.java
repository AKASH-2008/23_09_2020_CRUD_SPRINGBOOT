package com.assingment.springrest.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.assingment.springrest.dto.BusinessRequest;
import com.assingment.springrest.dto.BusinessResponse;
import com.assingment.springrest.entity.Branch;
import com.assingment.springrest.entity.Business;
import com.assingment.springrest.exception.ResaurceNotFoundException;
import com.assingment.springrest.repository.BranchRepository;
import com.assingment.springrest.repository.BusinessRepository;

@Service
@Transactional
public class SpringCRUDServiceImpl implements SpringCRUDService{

	@Autowired
	private BusinessRepository businessRepository;
	
	@Autowired
	private BranchRepository branchRepository;
	
	@Override
	public List<Business> allDataTestApp() 
	{
		return (List<Business>) this.businessRepository.findAll();
	}
	
	@Override
	public Optional<Business> allDataTestAppById(Integer id) {
		// TODO Auto-generated method stub
		return this.businessRepository.findById(id);
	}
	
	@Override
	public Business add(BusinessRequest request) {
		return addData(request);
	}

	private Business addData(BusinessRequest request) {
		 
		return businessRepository.save(request.getBusiness());
	}
	
	@Override
	public Business update(BusinessRequest request, Integer id) {
		return updateData(request, id);
	}

	private Business updateData(BusinessRequest request, Integer id) 
	{ 
		Optional<Business> businessDb = this.businessRepository.findById(id);
		
		if(businessDb.isPresent()) {
			
			return businessRepository.save(request.getBusiness());
		}
		else {
			//throw new ResaurceNotFoundException("Record not available for id: " + request.getBusiness().getBusiness_id());
			throw new ResaurceNotFoundException("Record not available for id: " + id);
		}
		
	}

	@Override
	public Optional<List<Business>> search(String business_name, Integer pan, String branch_address,
			Boolean active_ind, Date created_date) {
		// TODO Auto-generated method stub
		
		return searchByCreiteria(business_name, pan, branch_address,
				 active_ind, created_date);
	}

	private Optional<List<Business>> searchByCreiteria(String business_name, Integer pan, String branch_address,
			Boolean active_ind, Date created_date) {

		if(Objects.nonNull(business_name)) {
			return Optional.of(businessRepository.featchByBusiness_name(business_name));
			
				}
		if(Objects.nonNull(pan)) {
			return Optional.of(businessRepository.featchByPan(pan));		
			
				}
		if(Objects.nonNull(branch_address)) {
			return Optional.of(businessRepository.featchByBranchAddress(branch_address));
			
		}
		if(Objects.isNull(active_ind)) {
			return Optional.of(businessRepository.featchByBranchActiveInd(active_ind));
			
		}
		if(Objects.nonNull(created_date)) {
			businessRepository.featchByBranchCreateDate(created_date);			
		}
		
		return null;
	}

	

	
	 

}
