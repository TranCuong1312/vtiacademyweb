//
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

/**
 * This class is . 
 * 
 * @Description: .
 * @author: NVANH
 * @create_date: Sep 16, 2020
 * @version: 1.0
 * @modifer: NVAnh
 * @modifer_date: Sep 16, 2020
 */
@Entity
@Table(name="mainbanner")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MainBanner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="url")
	private String url;
	
	@Column(name = "image")
	private String image;
}
