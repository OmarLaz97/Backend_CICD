package com.orientation.backend.Exceptions.projectIDException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class userNameException extends RuntimeException{
    public userNameException(String errorMessage){
        super(errorMessage);
    }
}
