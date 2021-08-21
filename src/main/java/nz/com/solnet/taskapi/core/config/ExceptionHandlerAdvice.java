package nz.com.solnet.taskapi.core.config;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import lombok.Data;
import nz.com.solnet.taskapi.core.exception.custom.CustomBadRequestException;
import nz.com.solnet.taskapi.core.exception.custom.CustomResourceNotFoundException;

@Data
class CustomErrorResponse {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime timestamp;
	private String message;

}

@ControllerAdvice
public class ExceptionHandlerAdvice {

	private CustomErrorResponse generateCustomErrorResponse(Exception e) {
		return generateCustomErrorResponse(
				(e.getMessage() == null || e.getMessage().isEmpty()) ? "Undefined Message" : e.getMessage());
	}

	private CustomErrorResponse generateCustomErrorResponse(String message) {
		CustomErrorResponse customErrorResponse = new CustomErrorResponse();
		customErrorResponse.setTimestamp(LocalDateTime.now());
		customErrorResponse.setMessage(message);
		return customErrorResponse;
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<?> handleException(Exception e) {
		return new ResponseEntity<>(generateCustomErrorResponse(e), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ CustomBadRequestException.class })
	public ResponseEntity<?> handleBaseInternalException(Exception e) {
		return new ResponseEntity<>(generateCustomErrorResponse(e), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ CustomResourceNotFoundException.class })
	public ResponseEntity<?> handleExternalServiceHttpException(Exception e) {
		return new ResponseEntity<>(generateCustomErrorResponse(e), HttpStatus.NOT_FOUND);
	}


}
