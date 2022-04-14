package com.axess.smartbankapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends ApplicationException {

	private String message = "Sorry!! Record not found";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecordNotFoundException() {
		super();
	}

	public RecordNotFoundException(String message, Throwable arg1, boolean arg2, boolean arg3) {
		super(message, arg1, arg2, arg3);
		message = this.message;
	}

	public RecordNotFoundException(String message, Throwable arg1) {
		super(message, arg1);
		message = this.message;
	}

	public RecordNotFoundException(String message) {
		super(message);
		message = this.message;
	}

	public RecordNotFoundException(Throwable arg0) {
		super(arg0);
	}

	@Override
	public String toString() {
		return RecordNotFoundException.class.getName() + ": " + getMessage() + "\n cause - " + getCause()
				+ "\n localisedMessage - " + getLocalizedMessage();
	}
}
