package com.elo7.mars.rover.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RoverAlreadyRegisteredException extends Exception {

    private static final long serialVersionUID = 1L;

    public RoverAlreadyRegisteredException(String roverName) {
        super(String.format("Rover with name %s already registered in the system.", roverName));
    }
}