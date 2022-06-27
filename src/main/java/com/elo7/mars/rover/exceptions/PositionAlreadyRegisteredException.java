package com.elo7.mars.rover.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PositionAlreadyRegisteredException extends Exception {

    private static final long serialVersionUID = 1L;

    public PositionAlreadyRegisteredException(String positionName) {
        super(String.format("Position with name %s already registered in the system.", positionName));
    }
}