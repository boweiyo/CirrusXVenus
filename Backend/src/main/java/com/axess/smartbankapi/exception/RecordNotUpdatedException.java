package com.axess.smartbankapi.exception;

public class RecordNotUpdatedException extends ApplicationException {

	private String message = "Something went wrong !!. Record did not got update.";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecordNotUpdatedException() {
		super();
	}

	public RecordNotUpdatedException(String message, Throwable arg1, boolean arg2, boolean arg3) {
		super(message, arg1, arg2, arg3);
		message = this.message;
	}

	public RecordNotUpdatedException(String message, Throwable arg1) {
		super(message, arg1);
		message = this.message;
	}

	public RecordNotUpdatedException(String message) {
		super(message);
		message = this.message;
	}

	public RecordNotUpdatedException(Throwable arg0) {
		super(arg0);
	}

	@Override
	public String toString() {
		return RecordNotFoundException.class.getName() + ": " + getMessage() + "\n cause - " + getCause()
				+ "\n localisedMessage - " + getLocalizedMessage();
	}
	
}
