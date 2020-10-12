package com.vti.academy.web.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="news")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class News {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@NotBlank
	@Column(name = "title")
	private String title;
	@NotBlank
	@Column(name = "content")
	private String content;
	@NotBlank
	@Column(name = "author")
	private String author;
	@Column(name = "create_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createDate;
	@NotBlank
	@Column(name = "image")
	private String image;
	@NotBlank
	@Column(name = "short_des")
	private String shortDes;

}