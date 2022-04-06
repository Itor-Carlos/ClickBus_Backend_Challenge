package com.clickbus.ClickBus_Challenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.Map;

@ResponseStatus(HttpStatus.NOT_FOUND)//This annotation defines that the status code returned will be 404
public class PlaceNotFoundExceptionDetails extends ExceptionDetails{//PlaceNotFoundExceptionDetails extends ExceptionDetails

    private String message;

    public PlaceNotFoundExceptionDetails(Integer httpStatus, LocalDateTime timeStamp,String exception, String message){//Construtor of this class
        super(httpStatus,timeStamp,exception);//call the ExceptionDetails class constructor
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
