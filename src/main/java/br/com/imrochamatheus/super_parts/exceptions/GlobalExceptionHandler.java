package br.com.imrochamatheus.super_parts.exceptions;

import br.com.imrochamatheus.super_parts.utils.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status, String message) {
        ErrorResponse errorResponse = new ErrorResponse(status, message);
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler({CarAlreadyExistsException.class})
    public ResponseEntity<ErrorResponse> handleCarAlreadyExistsException (CarAlreadyExistsException exception) {
        return this.buildErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler({CarNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleCarNotFountException(CarNotFoundException exception) {
        return this.buildErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleMethodNotValidException (MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();

        exception.getAllErrors().forEach(error -> {
            String errorField = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();

            errors.put(errorField, errorMessage);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }
}
