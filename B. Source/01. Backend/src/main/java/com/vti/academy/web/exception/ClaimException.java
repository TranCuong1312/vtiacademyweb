package com.vti.academy.web.exception;

import org.springframework.http.HttpStatus;

import com.vti.academy.web.common.RestCode;

public class ClaimException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final String LOG_FORMAT_ERROR = "Error! RestCode: %s, Reason: %s";

	private RestCode restCode;

	private HttpStatus httpStatus;

	public ClaimException(RestCode restCode) {
		super(restCode.getDescription());
		this.restCode = restCode;
	}

	public ClaimException(RestCode restCode, HttpStatus httpStatus) {
		super(restCode.getDescription());
		this.restCode = restCode;
		this.httpStatus = httpStatus;
	}
	
	public ClaimException(String messages) {
		super(messages);
		this.restCode = RestCode.ERROR;
	}

	public ClaimException(Exception e) {
		super(e);
	}

	public RestCode getRestCode() {
		return restCode;
	}

	public void setRestCode(RestCode restCode) {
		this.restCode = restCode;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	@Override
	public String getMessage() {
		return String.format(LOG_FORMAT_ERROR, logParam(restCode));
	}

	private static Object[] logParam(RestCode restCode) {
		return new Object[] { restCode.getCode(), restCode.getDescription() };
	}
}
