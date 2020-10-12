package com.vti.academy.web.common;

public enum RestCode  {
	
	SUCCESS(1, "SUCCESS"),
	ERROR(2, "ERROR"),
	BAD_REQUEST(3, "BAD_REQUEST"),
	PARAMETER_INVALID(4, "PARAMETER_INVALID"),
	ACCESS_DENIED(5, "ACCESS_DENIED"),
	UNAUTHENTICATED(6, "UNAUTHENTICATED"),
	NOT_FOUND(7, "NOT_FOUND"),
	ERROR_INTERNAL_SERVER(8, "ERROR_INTERNAL_SERVER"),
	NO_CONTENT(9, " NO_CONTENT"),
	VALIDATE_DELETE_TASK(10, "VALIDATE_DELETE_TASK"),
	NOT_SUPPORT_ADMIN(11, "NOT_SUPPORT_ADMIN")
	;

    private final int code;
    private final String description;
    private ResponseType responseType;
    
    private RestCode(final int code, final String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
		return code;
	}
    
    public String getCodeString() {
		return String.valueOf(code);
	}

	public String getDescription() {
		return description;
	}

	public ResponseType getResponseType() {
		return responseType;
	}
}
