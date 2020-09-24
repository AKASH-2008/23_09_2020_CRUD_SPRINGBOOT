package com.assingment.springrest.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
@Entity(name="Business_Master")
@Table(name="Business_Master")
public class Business {

	@Id
	private Integer business_id;
	
	private String business_name;
	
	private Long contact_no;

	private Integer pan;
	
	@Column(updatable = false)
	@CreatedDate
	private LocalDate  created_date; 
	 
	@LastModifiedDate
	private LocalDate  updated_date;
	
	 @OneToMany(targetEntity = Branch.class, cascade = CascadeType.ALL)
	 @JoinColumn(name = "FK", referencedColumnName = "business_id") 
	 private List<Branch> branches;

	 
}
