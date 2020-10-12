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
@Table(name = "Enterprise")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Enterprise {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "image")
	private String image;

	@Column(name = "content")
	private String content;


	@Column(name = "icon")
	private String icon;
	
	@Column(name = "name")
	private String name;
}
