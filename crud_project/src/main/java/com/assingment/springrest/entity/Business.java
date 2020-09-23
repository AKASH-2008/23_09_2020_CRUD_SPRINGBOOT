package com.assingment.springrest.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name="Business_Master")
@Table(name="Business_Master")
public class Business {

	@Id
	private Integer business_id;
	
	private String business_name;
	
	private Long contact_no;

	private Integer pan;
	
	@Column(updatable = false)
	private Date created_date;
	 
	private Date updated_date;
	
	 @OneToMany(targetEntity = Branch.class, cascade = CascadeType.ALL)
	 @JoinColumn(name = "FK", referencedColumnName = "business_id") 
	 private List<Branch> branches;

	 
}
