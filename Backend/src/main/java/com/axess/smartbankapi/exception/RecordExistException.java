package com.axess.smartbankapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class RecordExistException extends ApplicationException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message = "Something went wrong !!. Record exists already.";

	public RecordExistException() {
		super();
	}

	public RecordExistException(String message, Throwable arg1, boolean arg2, boolean arg3) {
		super(message, arg1, arg2, arg3);
		message = this.message;
	}

	public RecordExistException(String message, Throwable arg1) {
		super(message, arg1);
		message = this.message;
	}

	public RecordExistException(String message) {
		super(message);
		message = this.message;
	}

	public RecordExistException(Throwable arg0) {
		super(arg0);
	}

	@Override
	public String toString() {
		return RecordExistException.class.getName() + ": " + getMessage() + "\n cause - " + getCause()
				+ "\n localisedMessage - " + getLocalizedMessage();
	}

}
