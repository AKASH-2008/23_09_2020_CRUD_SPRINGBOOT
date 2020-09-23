package com.assingment.springrest.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Entity(name="Branch_Master")
@Table(name="Branch_Master")
public class Branch {

	@Id
	private Integer branch_id;
	
	private Integer business_id;
	
	private String branch_address;
	
	private Long branch_contact;
	
	private boolean active_ind;
	
	@Column(updatable = false)
	private Date created_date;
	
	private Date updated_date;
	
}
