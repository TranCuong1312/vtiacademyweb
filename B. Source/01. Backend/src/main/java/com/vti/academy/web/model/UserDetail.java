package com.vti.academy.web.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_details")
public class UserDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String lastName;
	
	private String firstName;
	
	private String phone;
	
	private String companyName;
	
	private String VAT_Number;
	
	private String street;
	
	private String city;
	
	private String zip;
	
	private String country;
	
	private Date createDate;
	
	private String note;
	
	  @OneToOne
	  @JoinColumn(name = "user_id", nullable = false)
	  private User user;

}
