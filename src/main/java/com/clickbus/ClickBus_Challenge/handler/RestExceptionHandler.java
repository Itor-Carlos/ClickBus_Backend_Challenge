package com.clickbus.ClickBus_Challenge.handler;

import com.clickbus.ClickBus_Challenge.exceptions.PlaceFieldNotValidExceptionDetails;
import com.clickbus.ClickBus_Challenge.exceptions.PlaceNotFoundException;
import com.clickbus.ClickBus_Challenge.exceptions.PlaceNotFoundExceptionDetails;
import com.clickbus.ClickBus_Challenge.exceptions.PlaceAlredyExistExceptionDetails;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    //This method is gonna catch a Exceptions of type PlaceFieldNotValidExceptionDetails and return a ResponseEntity<PlaceFieldNotValidExceptionDetails> with the informations about the exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<PlaceFieldNotValidExceptionDetails> placeFieldNotValid(MethodArgumentNotValidException methodArgumentNotValidException){
        List<FieldError> fieldErrorList = methodArgumentNotValidException.getBindingResult().getFieldErrors();//get a list of erros 
        List<String> fieldMessage = fieldErrorList.stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());//get the message of this errors
        List<String> fieldError = fieldErrorList.stream().map(FieldError::getField).collect(Collectors.toList());;//get the name of field of this erros

        Map<String,String> messageErrors = new HashMap<>();

        for(int i = 0; i<fieldErrorList.size();i++){
            messageErrors.put(fieldError.get(i), fieldMessage.get(i));
        }
        PlaceFieldNotValidExceptionDetails placeFieldNotValidExceptionDetails = new PlaceFieldNotValidExceptionDetails(//create a object of type PlaceFieldNotValidExceptionDetails
            HttpStatus.BAD_REQUEST.value(),//define the STATUS CODE, in this caso, STATUS CODE is equals 400 (BAD_REQUEST)
            LocalDateTime.now(), //define the timestamp
            "PlaceFieldNotValidException", //define a name of Exception
            messageErrors//defines the message of Exception
        );

        return new ResponseEntity<>(placeFieldNotValidExceptionDetails,HttpStatus.BAD_REQUEST);//return a ResponseEntity with STATUS CODE equals 400
    }

    @ExceptionHandler(DataIntegrityViolationException.class)//This annotation indicates that method will be handle exceptions of type DataIntegrityViolationException
    public ResponseEntity<PlaceAlredyExistExceptionDetails> handlePlaceAlredyExist(){//That method return a ResponseEntity with a PlaceAlredyExistExceptionDetails in your body
        PlaceAlredyExistExceptionDetails placeSlugAlredyExistExceptionDetails = new PlaceAlredyExistExceptionDetails(HttpStatus.CONFLICT.value(), LocalDateTime.now(), "DataIntegrityViolationException", "This Place alredy exist in database");
        return new ResponseEntity<>(placeSlugAlredyExistExceptionDetails,HttpStatus.CONFLICT);//return statement
    }
}
