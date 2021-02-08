package com.noroff.MovieCharactersAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
public class NotAllowedExeption extends Exception{

    public NotAllowedExeption(String msg) {
        super(msg);
    }
}
