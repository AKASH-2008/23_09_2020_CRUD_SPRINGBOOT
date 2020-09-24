package com.assingment.springrest.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
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
	@CreatedDate
	private LocalDate  created_date;
	
	@LastModifiedDate
	private LocalDate  updated_date;
	
}
