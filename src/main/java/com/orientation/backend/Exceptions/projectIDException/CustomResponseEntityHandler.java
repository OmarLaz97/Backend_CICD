package com.orientation.backend.Exceptions.projectIDException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityHandler extends ResponseEntityExceptionHandler {

    public final ResponseEntity<Object> handle(Exception ex, WebRequest request){
        if(ex instanceof userNameException){
            userNameExceptionResponse response = new userNameExceptionResponse(ex.getMessage());
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity("Internal Server Error",HttpStatus.BAD_REQUEST);
        }
    }
}
