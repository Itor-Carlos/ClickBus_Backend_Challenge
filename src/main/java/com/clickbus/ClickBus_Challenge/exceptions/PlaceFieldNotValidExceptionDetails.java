package com.clickbus.ClickBus_Challenge.exceptions;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)//This annotation defines that the status code returned will be 400
public class PlaceFieldNotValidExceptionDetails extends ExceptionDetails{//PlaceFieldNotValidExceptionDetails extends ExceptionDetails

    //ATTRIBUTES
    private Map<String,String> messages;

    //CONSTRUCTOR
    public PlaceFieldNotValidExceptionDetails(Integer httpStatus, LocalDateTime timeStamp, String exception, Map<String,String> messages) {
        super(httpStatus, timeStamp, exception);
        this.messages = messages;
    }

    //GETTERS AND SETTERS
    public Map<String, String> getMessages() {
        return messages;
    }

    public void setMessages(Map<String, String> messages) {
        this.messages = messages;
    }
}
