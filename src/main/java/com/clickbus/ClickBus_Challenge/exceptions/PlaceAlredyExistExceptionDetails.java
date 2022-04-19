package com.clickbus.ClickBus_Challenge.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PlaceAlredyExistExceptionDetails extends ExceptionDetails {//This class will be used into the body of ResponseEntity<> objects when the Place altery exist in database
    

    //Attribute of this class
    private String message;

    /*CONSTRUCTOR*/
    public PlaceAlredyExistExceptionDetails(Integer httpStatus, LocalDateTime timeStamp, String exception, String message) {
        super(httpStatus, timeStamp, exception);
        this.message = message;
    }

    /*Getters and Setters*/
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
