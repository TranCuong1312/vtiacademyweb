package com.vti.academy.web.common;

import java.util.HashMap;
import java.util.Map;

public class ValidationMessage {

	private ValidationMessage() {

	}

	public static final Map<String, String> mapValidationMSG = new HashMap<>();
	public static final String MSG_PARAMETER_INVALID = "Tham số không hợp lệ.";
	public static final String MSG_PARAMETER_TYPE_INVALID = "Chỉ cho phép dùng kiểu dữ liệu %s.";
	public static final String MSG_DATE_INVALID_FORMAT = "Trường không đúng định dạng cho phép";
}
