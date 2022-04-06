package com.clickbus.ClickBus_Challenge.exceptions;

import java.time.LocalDateTime;

public class ExceptionDetails {//this class will be used for inheritance, i.e. other classes will extend from this class

    /*ATTRIBUTES*/
    private Integer httpStatus;
    private LocalDateTime timeStamp;
    private String exception;

    /*CONSTRUCTOR*/
    public ExceptionDetails(Integer httpStatus, LocalDateTime timeStamp, String exception) {
        this.httpStatus = httpStatus;
        this.timeStamp = timeStamp;
        this.exception = exception;
    }

    /*GETTERS E SETTERS*/
    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
