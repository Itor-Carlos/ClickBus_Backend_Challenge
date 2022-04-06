package com.clickbus.ClickBus_Challenge.handler;

import com.clickbus.ClickBus_Challenge.exceptions.PlaceNotFoundException;
import com.clickbus.ClickBus_Challenge.exceptions.PlaceNotFoundExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice//Is to catch exceptions thrown by all controllers annotated with @RestController or @ControllerAdvice
public class RestExceptionHandler {

    //This method is gonna catch a Exceptions of type PlaceNotFoundException and return a ResponseEntity<PlaceNotFoundExceptionDetails> with the informations about the exception
    @ExceptionHandler(PlaceNotFoundException.class)
    public ResponseEntity<PlaceNotFoundExceptionDetails> placeNotFoundException(PlaceNotFoundException placeNotFoundException) {
        PlaceNotFoundExceptionDetails placeNotFoundExceptionDetails = new PlaceNotFoundExceptionDetails(
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                "PlaceNotFoundException",
                "Place not find in this slug"
        );
        return new ResponseEntity<>(placeNotFoundExceptionDetails, HttpStatus.NOT_FOUND);
    }
}
