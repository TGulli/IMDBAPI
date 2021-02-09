package com.noroff.MovieCharactersAPI.exceptions;

import org.hibernate.PropertyValueException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class RestAPIExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoItemFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(NoItemFoundException ex, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * API RELATED EXCEPTIONS.
     */

    //MISSING FIELD THAT'S TAGGED AS NON-NULL
    @ExceptionHandler(PropertyValueException.class)
    public final ResponseEntity<Object> handleMissingField(Exception ex, WebRequest request)
    {
        ErrorDetails error = new ErrorDetails(new Date(),"You're missing a field that's marked as non-null", ex.getMessage());
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //HANDLE ILLEGAL PARAMETER
    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public final ResponseEntity<Object> handleBadRequest(Exception ex, WebRequest request)
    {
        ErrorDetails error = new ErrorDetails(new Date(),"The parameter contains bad stuff, very technical", ex.getMessage());
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //HANDLE MISUSE OF METHODS OR NON-EXISTENT METHODS
    @ExceptionHandler(MethodNotAllowedException.class)
    public final ResponseEntity<Object> methodNotAllowed(Exception ex, WebRequest request)
    {
        ErrorDetails error = new ErrorDetails(new Date(),"That's not a legal method", ex.getMessage());
        return new ResponseEntity(error, HttpStatus.METHOD_NOT_ALLOWED);
    }

    //HANDLE REST OF EXCEPTIONS
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleExceptionsInGeneral(Exception ex, WebRequest request)
    {
        ErrorDetails error = new ErrorDetails(new Date(),"Something is terribly wrong!", ex.getMessage());
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
