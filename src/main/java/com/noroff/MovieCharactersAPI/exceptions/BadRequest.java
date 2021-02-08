package com.noroff.MovieCharactersAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequest extends Exception {
    public BadRequest(String msg) {
        super(msg);
    }
}
