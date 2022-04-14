package com.axess.smartbankapi.exception;

public class RecordNotDeletedException extends ApplicationException {

	private String message = "Something went wrong !!. Record was not deleted.";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecordNotDeletedException() {
		super();
	}

	public RecordNotDeletedException(String message, Throwable arg1, boolean arg2, boolean arg3) {
		super(message, arg1, arg2, arg3);
		message = this.message;
	}

	public RecordNotDeletedException(String message, Throwable arg1) {
		super(message, arg1);
		message = this.message;
	}

	public RecordNotDeletedException(String message) {
		super(message);
		message = this.message;
	}

	public RecordNotDeletedException(Throwable arg0) {
		super(arg0);
	}

	@Override
	public String toString() {
		return RecordNotDeletedException.class.getName() + ": " + getMessage() + "\n cause - " + getCause()
				+ "\n localisedMessage - " + getLocalizedMessage();
	}

}
