package com.assingment.springrest.dto;

import com.assingment.springrest.entity.Business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class BusinessRequest {

	private Business business;
	
}
