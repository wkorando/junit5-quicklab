package com.ibm.developer.junit5quicklab.model;

import java.util.List;

public class ErrorResponse {

	private List<String> errorMessages;

	public ErrorResponse(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}

}
