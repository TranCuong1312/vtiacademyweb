package com.vti.academy.web.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class BasicCodeAndNameDTO {
	
	private String code;
	private String name;
	
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	private String buchoCode;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String mgrCode;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String departmentCode;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String teamCode;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<BasicCodeAndNameDTO> sas;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<BasicCodeAndNameDTO> teams;
	
	public BasicCodeAndNameDTO() {
		
	}
	
	public BasicCodeAndNameDTO(String code, String name, String buchoCode, String mgrCode, String departmentCode,
			String teamCode) {
		super();
		this.code = code;
		this.name = name;
		this.buchoCode = buchoCode;
		this.mgrCode = mgrCode;
		this.departmentCode = departmentCode;
		this.teamCode = teamCode;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBuchoCode() {
		return buchoCode;
	}
	public void setBuchoCode(String userCode) {
		this.buchoCode = userCode;
	}
	public String getMgrCode() {
		return mgrCode;
	}
	public void setMgrCode(String mgrCode) {
		this.mgrCode = mgrCode;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getTeamCode() {
		return teamCode;
	}
	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}
	public List<BasicCodeAndNameDTO> getSas() {
		return sas;
	}
	public void setSas(List<BasicCodeAndNameDTO> sas) {
		this.sas = sas;
	}
	public List<BasicCodeAndNameDTO> getTeams() {
		return teams;
	}
	public void setTeams(List<BasicCodeAndNameDTO> teams) {
		this.teams = teams;
	}
}
