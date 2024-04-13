package org.samydevup.mysqlcruddockerdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String resourceName;
	private String fieldName;
	private Long fieldValue;

	public ResourceNotFoundException(String resourceName, String fieldName, Long userId) {
		super(String.format("%s not found with %s : %s", resourceName, fieldName, userId));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = userId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public Long getFieldValue() {
		return fieldValue;
	}

}
