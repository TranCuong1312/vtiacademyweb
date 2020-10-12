package com.vti.academy.web.exception;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.vti.academy.web.common.RestCode;

public class FieldErrorVM implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String field;

	private final String message;

	@JsonInclude(Include.NON_NULL)
	private Integer code;

	public FieldErrorVM(String field, String message) {
		this.field = field;
		this.message = message;
	}

	public FieldErrorVM(String field, String message, Integer code) {
		this.field = field;
		this.message = message;
		this.code = code;
	}

	public FieldErrorVM(String field, RestCode restCode) {
		this.field = field;
		this.message = restCode.getDescription();
		this.code = restCode.getCode();
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
