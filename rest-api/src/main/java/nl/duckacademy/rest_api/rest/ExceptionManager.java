package nl.duckacademy.rest_api.rest;

import nl.duckacademy.rest_api.customexception.UserNotFoundException;
import nl.duckacademy.rest_api.rest.model.GenericErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionManager {
    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<GenericErrorDTO> handleIllegalArgument(IllegalArgumentException exception) {
        GenericErrorDTO genericErrorDTO = new GenericErrorDTO(HttpStatus.NOT_FOUND.name(), exception.getMessage(), "");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(genericErrorDTO);
    }
    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<GenericErrorDTO> handleIllegalArgument(UserNotFoundException exception) {
        GenericErrorDTO genericErrorDTO = new GenericErrorDTO(exception.getCode().toString(), exception.getMessage(), exception.getDescription());
        return ResponseEntity.status(exception.getCode()).body(genericErrorDTO);
    }
}
