package com.clickbus.ClickBus_Challenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(HttpStatus.NOT_FOUND)//This annotation defines that the status code returned will be 404
public class PlaceNotFoundExceptionDetails extends ExceptionDetails{//PlaceNotFoundExceptionDetails extends ExceptionDetails

    public PlaceNotFoundExceptionDetails(String message, Integer httpStatus, LocalDateTime timeStamp,String exception){//Construtor of this class
        super(message,httpStatus,timeStamp,exception);//call the ExceptionDetails class constructor
    }

}
