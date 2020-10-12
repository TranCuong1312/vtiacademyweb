package com.vti.academy.web.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vti.academy.web.utils.DateUtils;
import com.vti.academy.web.utils.SecurityUtils;

@MappedSuperclass
public class AbstractAuditEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Column(name = "CREATED_BY")
	private String createdBy;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DDMMYYYYHHMMSS, timezone = "Asia/Tokyo")
	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DDMMYYYYHHMMSS, timezone = "Asia/Tokyo")
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public void initAudit() {
		this.createdBy = SecurityUtils.getUserSecurityLogged().getUserId();
		this.createdDate = new Date();
		this.updatedBy = this.createdBy;
		this.updatedDate = new Date();
	}
	
	public void initAuditDate() {
		this.createdDate = new Date();
		this.updatedDate = new Date();
	}
	
	public void initAuditEdited() {
		this.updatedBy = SecurityUtils.getUserSecurityLogged().getUserId();
		this.updatedDate = new Date();
	}
	
	public void initAuditCreate() {
		this.createdBy = SecurityUtils.getUserSecurityLogged().getUserId();
		this.createdDate = new Date();
	}
}
