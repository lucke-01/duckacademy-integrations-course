package nl.duckacademy.rest_api.rest;

import com.swagger.client.codegen.rest.model.GenericErrorDTO;
import nl.duckacademy.rest_api.customexception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionManager {
    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<GenericErrorDTO> handleIllegalArgument(UserNotFoundException exception) {
        GenericErrorDTO genericErrorDTO = new GenericErrorDTO();
        genericErrorDTO.setErrorCode(exception.getCode().toString());
        genericErrorDTO.setMessage(exception.getMessage());
        genericErrorDTO.setErrorCode(exception.getDescription());
        return ResponseEntity.status(exception.getCode()).body(genericErrorDTO);
    }
}
