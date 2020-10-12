package com.vti.academy.web.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.vti.academy.web.common.RestCode;
import com.vti.academy.web.common.ValidationMessage;
import com.vti.academy.web.response.CommonResponse;
import com.vti.academy.web.utils.ResponseUtils;

@ControllerAdvice
@Component
public class GlobalExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * Handle Exception - Request body variable is invalid value
	 * 
	 * @param exception
	 * @return response
	 */
	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public CommonResponse handle(ConstraintViolationException exception) {
		LOG.error("Error! Request body variable is invalid value. {}.", exception.getMessage());
		List<FieldErrorVM> fieldErrors = new ArrayList<>();
		exception.getConstraintViolations().forEach(ex -> {
			String validName = ex.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName();
			FieldErrorVM fieldError = new FieldErrorVM(ex.getPropertyPath().toString(), getMessages(validName,
					ex.getMessage(), getValidConfig(ex.getConstraintDescriptor().getAttributes(), validName)));
			fieldErrors.add(fieldError);
		});

		return errorMap(fieldErrors);
	}
	
	
	/**
	 * Handle Exception - Request parameter is invalid value
	 * 
	 * @param exception
	 * @return response
	 */
	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public CommonResponse handleMethod(MethodArgumentNotValidException exception) {
		LOG.error("Error! Request parameter is invalid value. {}", exception.getMessage());
		List<FieldErrorVM> fieldErrors = exception.getBindingResult().getAllErrors().stream()
				.map(f -> new FieldErrorVM(((FieldError) f).getField(), f.getDefaultMessage())).collect(Collectors.toList());
		return errorMap(fieldErrors);
	}
	

	/**
	 * Handle Exception - Request parameter is invalid type
	 * 
	 * @param exception
	 * @return response
	 */
	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public CommonResponse handleMethod(MethodArgumentTypeMismatchException exception) {
		LOG.error("Error! Request parameter is invalid type. {}", exception.getMessage());
		List<FieldErrorVM> fieldErrors = new ArrayList<>();
		FieldErrorVM error = new FieldErrorVM(exception.getName(), getMessageWithParam(
				ValidationMessage.MSG_PARAMETER_TYPE_INVALID, getTypeParam(exception.getRequiredType().getName())));
		fieldErrors.add(error);
		return errorMap(fieldErrors);
	}
	
	

	/**
	 * Handle Exception - Access Denied request
	 * 
	 * @param exception
	 * @return response
	 */
	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public CommonResponse handleMethod(AccessDeniedException exception) {
		LOG.error("Error! Access Denied request. {}", exception.getMessage());
		return ResponseUtils.msgResponse(RestCode.ACCESS_DENIED);
	}

	/**
	 * Handle Exception - Internal Server Error
	 * 
	 * @param exception
	 * @return response
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public CommonResponse handleMethod(Exception exception) {
		LOG.error("Error! Internal Server. {}", exception.getMessage());
		return ResponseUtils.msgResponse(RestCode.ERROR_INTERNAL_SERVER);
	}

	/**
	 * Handle Exception - Exception
	 * 
	 * @param exception
	 * @return response
	 */
	@ExceptionHandler(value = ClaimException.class)
	@ResponseBody
	public ResponseEntity<CommonResponse> misetataException(ClaimException exception) {
		LOG.error("Error! Have error by Misetata system {}.", exception.getMessage());
		CommonResponse commonResponse = ResponseUtils.msgResponse(exception.getRestCode());
		if (exception.getHttpStatus() == null) {
			return new ResponseEntity<>(commonResponse, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(commonResponse, exception.getHttpStatus());
	}
	/**
	 * Handle Exception - Date format is error
	 * 
	 * @param exception
	 * @return response
	 */
	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public CommonResponse errorDateFormat(HttpMessageNotReadableException exception) {
		LOG.error("Error! Formate field invalid. {}.", exception);
		List<FieldErrorVM> fieldErrors = new ArrayList<>();
		if (exception.getCause() instanceof InvalidFormatException) {
			InvalidFormatException invalidFormatException = (InvalidFormatException) exception.getCause();
			invalidFormatException.getPath().forEach(item -> 
				fieldErrors.add(new FieldErrorVM(item.getFieldName(), ValidationMessage.MSG_DATE_INVALID_FORMAT))
			);
		}
		return errorMap(fieldErrors);
	}
	
	
	/**
	 * Handle Exception - Validation input is error
	 * 
	 * @param exception
	 * @return response
	 */

	@SuppressWarnings("unused")
	private String getMessages(FieldError fieldError) {
		String validName = fieldError.getCodes()[fieldError.getCodes().length - 1];
		String message = ValidationMessage.mapValidationMSG.get(validName);
		if (StringUtils.isBlank(message)) {
			message = fieldError.getDefaultMessage();
		}
		return getMessageWithParam(message, ArrayUtils.remove(fieldError.getArguments(), 0));
	}

	private String getMessages(String validName, String defaultMessage, Object... params) {
		String message = ValidationMessage.mapValidationMSG.get(validName);
		if (StringUtils.isBlank(message)) {
			message = defaultMessage;
		}
		return getMessageWithParam(message, params);
	}

	private Object[] getValidConfig(Map<String, Object> map, String validName) {
		Object[] valids = null;
		switch (validName) {
		case "Size":
			valids = new Object[2];
			valids[0] = map.get("min");
			valids[1] = map.get("max");
			break;
		case "Min":
		case "Max":
			valids = new Object[1];
			valids[0] = map.get("value");
			break;
		default:
			valids = null;
			break;
		}
		return valids;
	}

	private String getMessageWithParam(String msgFomat, Object... params) {
		Arrays.sort(params);
		return String.format(msgFomat, params);
	}

	private CommonResponse errorMap(List<FieldErrorVM> fieldErrors) {
		CommonResponse commonResponse = ResponseUtils.msgResponse(RestCode.PARAMETER_INVALID);
		commonResponse.setErrors(fieldErrors);
		return commonResponse;
	}

	private String getTypeParam(String type) {
		if (StringUtils.isBlank(type)) {
			return "";
		}
		Integer index = type.lastIndexOf('.');
		return type.substring(index + 1, type.length());
	}

}
