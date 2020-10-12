package com.vti.academy.web.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "course_outcomes")
@RequiredArgsConstructor
public class CourseOutcomes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	Long id;
	
	@NonNull
	String name;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
	@JsonIgnore
	@JoinColumn(name = "course_id")
	private Course course;

	public CourseOutcomes( String name, Course c) {
		this.name = name;
		this.course = c;
	}
	
}
