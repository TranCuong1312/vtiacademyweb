package com.vti.academy.web.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "courses")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	private String name;

	private String img;

	private String intro;

	private String curriculum;
	
//	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="dd-MM-yyyy", timezone = "Asia/Ho_Chi_Minh")
	private Date createDate;

	private boolean isActive;

	private String note;

	@ManyToMany(fetch = FetchType.EAGER, cascade={CascadeType.PERSIST,CascadeType.REMOVE})
	@JoinTable(name = "course_syllabuses", 
	    joinColumns = { @JoinColumn(name = "course_id") }, 
	    inverseJoinColumns = {@JoinColumn(name = "subcourse_id") })
	@JsonIgnore
	  private List<SubCourse> subcourses;
//	
//	@OneToOne(mappedBy = "course")
//	@JsonIgnore
//	private MainBanner mainBanner;
	
	
//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "course", cascade = {CascadeType.MERGE})
//	private Set<CourseOutcomes> courseOutcome;
//	
//	
//	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinTable(name = "users_courses", 
//	    joinColumns = { @JoinColumn(name = "course_id") }, 
//	    inverseJoinColumns = {@JoinColumn(name = "user_id") })
//	  private Set<User> user;
	
	@Override
	public boolean equals(Object obj) {
		Course course =(Course) obj;	
 		return course.getId()==this.id;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.id.hashCode();
	}
}
