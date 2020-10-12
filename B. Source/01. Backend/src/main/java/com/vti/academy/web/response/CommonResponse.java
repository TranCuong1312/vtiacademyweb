package com.vti.academy.web.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.vti.academy.web.request.Pagination;
import com.vti.academy.web.utils.Utils;

public class CommonResponse {
	private String type;
	private int code;
	private String message;
	@JsonInclude(Include.NON_NULL)
	private Object errors;
	@JsonInclude(Include.NON_NULL)
	private Object data;

	@SuppressWarnings("unused")
	private Pagination pagination;
	
	public CommonResponse() {

	}

	public CommonResponse(String type, int code, String message) {
		this.type = type;
		this.code = code;
		this.message = message;
	}
	
	

	public CommonResponse(String type, int code, String message, Object data) {
		this.type = type;
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	public CommonResponse(String type, int code, String message, Object data, Pagination  pagination) {
		this.type = type;
		this.code = code;
		this.message = message;
		this.data = data;
		this.pagination = pagination;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object getErrors() {
		return errors;
	}

	public void setErrors(Object errors) {
		this.errors = errors;
	}


	public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
	public String toString() {
		return Utils.toJson(this);

	}
}
