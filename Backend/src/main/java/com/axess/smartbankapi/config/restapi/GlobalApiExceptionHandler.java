package com.axess.smartbankapi.config.restapi;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.axess.smartbankapi.exception.ApplicationException;
import com.axess.smartbankapi.exception.RecordExistException;
import com.axess.smartbankapi.exception.RecordNotCreatedException;
import com.axess.smartbankapi.exception.RecordNotDeletedException;
import com.axess.smartbankapi.exception.RecordNotFoundException;
import com.axess.smartbankapi.exception.RecordNotUpdatedException;



@RestControllerAdvice
public class GlobalApiExceptionHandler extends ResponseEntityExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(GlobalApiExceptionHandler.class);

	private ResponseEntity<Object> buildResponseEntity(ApiErrorResponse response, HttpStatus httpStatus) {

		return new ResponseEntity<>(response, httpStatus);
	}

	@Override
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		System.out.println("commmmme");
		ApiErrorResponse response = new ApiErrorResponse();

		BindingResult binding = ex.getBindingResult();
		FieldError error = binding.getFieldError();
		String message = error.getDefaultMessage();
		response.setCause(ex.getLocalizedMessage());
		response.setMessage(message);
		response.setHttpStatus(HttpStatus.BAD_REQUEST);
		response.setHttpStatusCode(400);
		response.setError(true);

		return buildResponseEntity(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	protected ResponseEntity<Object> handleMethodArgumentTypeMismatchExceptions(
			final MethodArgumentTypeMismatchException ex, WebRequest request) {
		System.out.println("type mismatch");
		ApiErrorResponse response = new ApiErrorResponse();

		String message = ex.getName() + " should be of type " + ex.getRequiredType().getName();

		response.setCause(ex.getLocalizedMessage());
		response.setMessage(message);
		response.setHttpStatus(HttpStatus.BAD_REQUEST);
		response.setHttpStatusCode(400);
		response.setError(true);

		return buildResponseEntity(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IllegalStateException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	protected ResponseEntity<Object> handleIllegalStateException(final MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		System.out.println("type mismatch");
		ApiErrorResponse response = new ApiErrorResponse();

		String message = ex.getName() + " should be of type " + ex.getRequiredType().getName();

		response.setCause(ex.getLocalizedMessage());
		response.setMessage(message);
		response.setHttpStatus(HttpStatus.BAD_REQUEST);
		response.setHttpStatusCode(400);
		response.setError(true);

		return buildResponseEntity(response, HttpStatus.BAD_REQUEST);
	}

	@Override
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ApiErrorResponse response = new ApiErrorResponse();
		String message = ex.getParameterName() + " should be of type " + ex.getParameterType();

		response.setCause(ex.getLocalizedMessage());
		response.setMessage(message);
		response.setHttpStatus(HttpStatus.BAD_REQUEST);
		response.setHttpStatusCode(400);
		response.setError(true);

		return buildResponseEntity(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		ApiErrorResponse response = new ApiErrorResponse();

		response.setCause(ex.getLocalizedMessage());
		response.setMessage(ex.getMessage());
		logger.error("exception occured - ");
		ex.printStackTrace();
		response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		response.setHttpStatusCode(500);
		response.setError(true);

		return buildResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(ApplicationException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public final ResponseEntity<Object> handleApplicationExceptions(ApplicationException ex, WebRequest request) {
		ApiErrorResponse response = new ApiErrorResponse();
		logger.error("exception occured in Handleapplicationexception - ");
		ex.printStackTrace();
		response.setCause(ex.getLocalizedMessage());
		response.setMessage(ex.getMessage());
		response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		response.setHttpStatusCode(500);
		response.setError(true);

		return buildResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(RecordNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public final ResponseEntity<Object> handleRecordNotFoundExceptions(RecordNotFoundException ex, WebRequest request) {
		ApiErrorResponse response = new ApiErrorResponse();

		if (ex.getCause() != null) {
			response.setCause(ex.getCause().getMessage());
		} else {
			response.setCause(ex.getLocalizedMessage());
		}
		response.setMessage("Something went wrong !!. Record not found.");
		response.setExceptionMessage(ex.getMessage());
		response.setHttpStatus(HttpStatus.NOT_FOUND);
		response.setHttpStatusCode(404);
		response.setError(true);

		return buildResponseEntity(response, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(RecordNotCreatedException.class)
	@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
	public final ResponseEntity<Object> handleRecordNotCreatedExceptions(RecordNotCreatedException ex,
			WebRequest request) {
		ApiErrorResponse response = new ApiErrorResponse();

		if (ex.getCause() != null) {
			response.setCause(ex.getCause().getMessage());
		} else {
			response.setCause(ex.getLocalizedMessage());
		}
		response.setMessage("Something went wrong !!. Record not created");
		response.setExceptionMessage(ex.getMessage());
		response.setHttpStatus(HttpStatus.UNPROCESSABLE_ENTITY);
		response.setHttpStatusCode(422);
		response.setError(true);

		return buildResponseEntity(response, HttpStatus.UNPROCESSABLE_ENTITY);

	}

	@ExceptionHandler(RecordNotDeletedException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public final ResponseEntity<Object> handleRecordNotDeletedExceptions(RecordNotDeletedException ex,
			WebRequest request) {
		ApiErrorResponse response = new ApiErrorResponse();

		if (ex.getCause() != null) {
			response.setCause(ex.getCause().getMessage());
		} else {
			response.setCause(ex.getLocalizedMessage());
		}
		response.setMessage("Something went wrong !!. Record was not deleted.");
		response.setExceptionMessage(ex.getMessage());
		response.setHttpStatus(HttpStatus.CONFLICT);
		response.setHttpStatusCode(409);
		response.setError(true);

		return buildResponseEntity(response, HttpStatus.CONFLICT);

	}

	@ExceptionHandler(RecordNotUpdatedException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public final ResponseEntity<Object> handleRecordNotUpdatedExceptions(RecordNotUpdatedException ex,
			WebRequest request) {
		ApiErrorResponse response = new ApiErrorResponse();

		if (ex.getCause() != null) {
			response.setCause(ex.getCause().getMessage());
		} else {
			response.setCause(ex.getLocalizedMessage());
		}
		response.setMessage("Something went wrong !!. Record did not got update.");
		response.setExceptionMessage(ex.getMessage());
		response.setHttpStatus(HttpStatus.CONFLICT);
		response.setHttpStatusCode(409);
		response.setError(true);

		return buildResponseEntity(response, HttpStatus.CONFLICT);

	}

	@ExceptionHandler(RecordExistException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public final ResponseEntity<Object> handleRecordExistsExceptions(RecordExistException ex, WebRequest request) {
		ApiErrorResponse response = new ApiErrorResponse();

		if (ex.getCause() != null) {
			response.setCause(ex.getCause().getMessage());
		} else {
			response.setCause(ex.getLocalizedMessage());
		}
		response.setMessage("Something went wrong !!. Record exists already.");
		response.setExceptionMessage(ex.getMessage());
		response.setHttpStatus(HttpStatus.CONFLICT);
		response.setHttpStatusCode(409);
		response.setError(true);

		return buildResponseEntity(response, HttpStatus.CONFLICT);

	}

	@ExceptionHandler(IOException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public final ResponseEntity<Object> handleIOException(IOException ex, WebRequest request) {
		ApiErrorResponse response = new ApiErrorResponse();

		if (ex.getCause() != null) {
			response.setCause(ex.getCause().getMessage());
		} else {
			response.setCause(ex.getLocalizedMessage());
		}
		response.setMessage("IOException - Something went wrong");
		response.setExceptionMessage(ex.getMessage());
		response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		response.setHttpStatusCode(500);
		response.setError(true);

		return buildResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public final ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex,
			WebRequest request) {
		ApiErrorResponse response = new ApiErrorResponse();

		if (ex.getCause() != null) {
			response.setCause(ex.getCause().getMessage());
		} else {
			response.setCause(ex.getLocalizedMessage());
		}
		response.setMessage("IllegalArgumentException - Something went wrong");
		response.setExceptionMessage(ex.getMessage());
		response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		response.setHttpStatusCode(500);
		response.setError(true);

		return buildResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public final ResponseEntity<Object> handleNullPointerException(NullPointerException ex, WebRequest request) {
		ApiErrorResponse response = new ApiErrorResponse();

		if (ex.getCause() != null) {
			response.setCause(ex.getCause().getMessage());
		} else {
			response.setCause(ex.getLocalizedMessage());
		}
		logger.error("exception occured - Nullpointer- ");
		ex.printStackTrace();
		response.setMessage("Something went wrong. Object is empty or null.");
		response.setExceptionMessage(ex.getMessage());
		response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		response.setHttpStatusCode(500);
		response.setError(true);

		return buildResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
