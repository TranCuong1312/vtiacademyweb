package com.vti.academy.web.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Table(name = "subcourses")
public class SubCourse implements Comparable<SubCourse> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@JsonFormat(pattern="dd-MM-yyyy", timezone = "Asia/Ho_Chi_Minh")
	private Date createDate;
	
	private String content;
	private boolean isActive;
	private String note;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "subcourses", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Course> courses;

	@Override
	public boolean equals(Object obj) {
		
		return this.getId().equals( ((SubCourse)obj).getId());
	}
	
	@Override
    public int compareTo(SubCourse s) {
        return this.getId().compareTo(s.getId());
    }
	
	/* 
	* @see java.lang.Object#toString()
	*/
	@Override
	public String toString() {
		return id + "";
	}

}
