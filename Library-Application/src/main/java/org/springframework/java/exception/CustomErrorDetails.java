package org.springframework.java.exception;

import java.util.Date;

public class CustomErrorDetails {
	
	private Date date;
	private String errorDetails;
	private String errorMessage;
	
	public CustomErrorDetails(Date date, String errorDetails, String errorMessage) {
		
		this.date = date;
		this.errorDetails = errorDetails;
		this.errorMessage = errorMessage;
	}

	public Date getDate() {
		return date;
	}

	public String getErrorDetails() {
		return errorDetails;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	
	
	
	
	

}
