
package com.vti.academy.web.utils;

import org.apache.commons.lang3.StringUtils;

import com.vti.academy.web.common.ResponseType;
import com.vti.academy.web.common.RestCode;
import com.vti.academy.web.response.CommonResponse;

public class ResponseUtils {
	public ResponseUtils() {

	}

	/**
	 * Response with data custom restCode, data
	 * 
	 * @param restCode: RestCode
	 * @param data:     Object
	 * @return response: CommonResponse
	 */
	public static CommonResponse msgResponse(RestCode restCode) {
		if (restCode == RestCode.SUCCESS) {
			return getResponse(restCode, ResponseType.INFO, null);
		}
		if (restCode.getResponseType() == null) {
			return getResponse(restCode, ResponseType.ERROR, null);
		}
		return getResponse(restCode, ResponseType.WARNING, null);
	}

	/**
	 * Response with data custom restCode, message, data
	 * 
	 * @param restCode: RestCode
	 * @param message:  String
	 * @param data:     Object
	 * @return response CommonResponse
	 */
	public static CommonResponse msgResponse(RestCode restCode, String message) {
		if (restCode == RestCode.SUCCESS) {
			return getResponse(restCode, ResponseType.INFO, message);
		}
		if (restCode.getResponseType() == null) {
			return getResponse(restCode, ResponseType.ERROR, message);
		}
		return getResponse(restCode, ResponseType.WARNING, message);
	}

	/**
	 * Response message with custom restCode, type
	 * 
	 * @param restCode
	 * @param type
	 * @return
	 */
	public static CommonResponse msgResponse(RestCode restCode, ResponseType type) {
		return getResponse(restCode, type, null);
	}

	private static CommonResponse getResponse(RestCode restCode, ResponseType type, String message) {
		CommonResponse response = new CommonResponse();
		response.setCode(restCode.getCode());
		if (StringUtils.isNotBlank(message)) {
			response.setMessage(message);
		} else {
			response.setMessage(restCode.getDescription());
		}

		response.setType(type.getValue());
		return response;
	}
}
