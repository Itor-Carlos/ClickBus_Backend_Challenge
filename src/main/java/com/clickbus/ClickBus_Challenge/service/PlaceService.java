package com.clickbus.ClickBus_Challenge.service;

import com.clickbus.ClickBus_Challenge.repositories.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service//This annotation indicates that the Class is a Service and will be used to define the business rules
public class PlaceService {

    @Autowired//This annotaion indicates a Dependency Injection
    private PlaceRepository placeRepository;//This attribute will be used to call the methods that perform operations on the database.

}
