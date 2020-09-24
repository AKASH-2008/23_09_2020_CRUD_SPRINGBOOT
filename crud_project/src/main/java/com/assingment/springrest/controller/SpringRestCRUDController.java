package com.assingment.springrest.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assingment.springrest.dto.BusinessRequest;
import com.assingment.springrest.entity.Business;
import com.assingment.springrest.repository.BranchRepository;
import com.assingment.springrest.repository.BusinessRepository;
import com.assingment.springrest.service.SpringCRUDService;
import com.assingment.springrest.service.SpringCRUDServiceImpl;

@RestController
@RequestMapping("/springrest")
public class SpringRestCRUDController {

	@Autowired
	SpringCRUDService springCRUDService;
	
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Business> saveBusiness(@RequestBody BusinessRequest request)
	{
		
		return ResponseEntity.ok().body(springCRUDService.add(request));
	    
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Business> updateBusiness(@PathVariable Integer id, @RequestBody BusinessRequest request)
	{
		
		//System.out.println(">>>>>>>>>>>> "+id);
		return ResponseEntity.ok().body(springCRUDService.update(request, id));
	    
	}
	
	
	//	 ResponseEntity<Optional<List<Business>>> searchByKey(@RequestParam(value="business_name") String business_name, 

	
	 @RequestMapping(value = "/search", method = RequestMethod.GET) public
	 ResponseEntity<Optional<List<Business>>> searchByKey(
			 @RequestParam(value="business_name", required = false) String business_name, 
			 @RequestParam(value="pan", required = false) Integer pan,
			 @RequestParam(value="branch_address", required = false) String branch_address,
			 @RequestParam(value="active_ind", required = false) Boolean active_ind,
			 @RequestParam(value="created_date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate created_date)
			  {
		
		 return ResponseEntity.ok().body(springCRUDService.search(business_name, pan, branch_address, active_ind, created_date));
		 
		 //System.out.println(">>> " + business_name+" >>>> "+ pan+" >>> "+ branch_address +" >>>> " + active_ind +" >>> "+created_date);
	 }
	 
	
	
		/*
		 * @RequestMapping(value = "/test", method = RequestMethod.GET) public
		 * ResponseEntity<List<Business>> testProduct() {
		 * 
		 * return ResponseEntity.ok().body(springCRUDService.allDataTestApp());
		 * 
		 * }
		 * 
		 * @RequestMapping(value = "/test/{id}", method = RequestMethod.GET) public
		 * ResponseEntity<Optional<Business>> testProductById(@PathVariable Integer id)
		 * {
		 * 
		 * return ResponseEntity.ok().body(springCRUDService.allDataTestAppById(id));
		 * 
		 * }
		 */
	
	
}
