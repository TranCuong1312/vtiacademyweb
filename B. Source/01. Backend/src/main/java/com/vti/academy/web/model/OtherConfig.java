package com.vti.academy.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "other_config")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OtherConfig {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "training_program_img")
	private String training_program_img;
	
	@Column(name = "business_training_text")
	private String business_training_text;
	
}
