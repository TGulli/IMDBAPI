package com.noroff.MovieCharactersAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerError extends Exception {
    public ServerError(String msg) {
        super(msg);
    }
}
