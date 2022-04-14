package com.axess.smartbankapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class RecordNotCreatedException extends ApplicationException {

	private String message = "Something went wrong. Record was not created.";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecordNotCreatedException(String message, Throwable arg1) {
		super(message, arg1);
		message = this.message;
	}

	public RecordNotCreatedException(String arg0) {
		super(arg0);
	}

	@Override
	public String toString() {
		return RecordNotCreatedException.class.getName() + ": " + getMessage() + "\n cause - " + getCause()
				+ "\n localisedMessage - " + getLocalizedMessage();
	}
}
