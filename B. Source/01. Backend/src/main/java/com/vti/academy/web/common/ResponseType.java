package com.vti.academy.web.common;

public enum ResponseType {
	INFO("info"), 
	ERROR("error"),
	WARNING("warning");

	private final String value;

	private ResponseType(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
