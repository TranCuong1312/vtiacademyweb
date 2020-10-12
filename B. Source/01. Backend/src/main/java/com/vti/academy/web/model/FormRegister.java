package com.vti.academy.web.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "formregister")
public class FormRegister {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private Date date;

	private String email;
	
	private String name;
	
	private String phone;

	private int type;
	
	
	public FormRegister(String email, String name, String phone, int type, Course course) {
		this.date = new Date();
		this.email = email;
		this.name = name;
		this.phone = phone;
		this.type = type;
		this.course = course;
	}

	@ManyToOne
	@JoinColumn(name="coursesId", nullable = false)
	private Course course;
	
}
