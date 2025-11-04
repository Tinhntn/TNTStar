package tnt.backend.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import tnt.backend.Response.ErrorResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity<?> handValidationErros(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((error) ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler (NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handNotFoundErros(NotFoundException ex) {
        return new tnt.backend.Response.ErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
    }

    @ExceptionHandler (BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handBadRequestErros(BadRequestException ex) {
        return new ErrorResponse(ex.getMessage(),HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
    }

    @ExceptionHandler(InternalServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handInternalServiceErros(InternalServiceException ex) {
        return new ErrorResponse(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handHttpMessageNotReadableErros(HttpMessageNotReadableException ex) {
        Map<String, String> errors = new HashMap<>();
        if(ex.getCause() instanceof com.fasterxml.jackson.databind.JsonMappingException) {
            return ResponseEntity.badRequest().body("Invalid date format! Please use dd/MM/yyyy");
        }
        return ResponseEntity.badRequest().body("Invalid request format!");
    }


}


