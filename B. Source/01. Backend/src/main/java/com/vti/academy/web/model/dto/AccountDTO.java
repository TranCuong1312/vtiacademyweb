package com.vti.academy.web.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class AccountDTO {
	private Long id;
	private String userName;
	private String nameVN;
	@JsonInclude(Include.NON_NULL)
	private String nameEN;
	private String email;
	private String phoneNumber;
	private String address;
	private Byte active;
	private Short accountType;
	@JsonInclude(Include.NON_NULL)
	private List<String> authorities;
	@JsonInclude(Include.NON_NULL)
	private String token;

	public AccountDTO() {

	}

	/**
	 * @param id
	 * @param userName
	 * @param nameVN
	 * @param nameEN
	 * @param email
	 * @param phoneNumber
	 * @param address
	 * @param active
	 * @paran accountType
	 */
	public AccountDTO(Long id, String userName, String nameVN, String nameEN, String email, String phoneNumber,
			String address, Byte active, Short accountType) {
		this.id = id;
		this.userName = userName;
		this.nameVN = nameVN;
		this.nameEN = nameEN;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.active = active;
		this.accountType = accountType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNameVN() {
		return nameVN;
	}

	public void setNameVN(String nameVN) {
		this.nameVN = nameVN;
	}

	public String getNameEN() {
		return nameEN;
	}

	public void setNameEN(String nameEN) {
		this.nameEN = nameEN;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Byte getActive() {
		return active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	public Short getAccountType() {
		return accountType;
	}

	public void setAccountType(Short accountType) {
		this.accountType = accountType;
	}

	public List<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
