package demo.laptopstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception ex) {
		// Handle generic exceptions
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
		// Handle specific NotFoundException
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	// You can define more exception handlers for different exception types here

	// Custom Exception Class for NotFound
	public static class NotFoundException extends Exception {
		public NotFoundException(String message) {
			super(message);
		}
	}
}
