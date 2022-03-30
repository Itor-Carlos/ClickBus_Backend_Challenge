package com.clickbus.ClickBus_Challenge.controller;

import com.clickbus.ClickBus_Challenge.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //This annotation indicates that class is a RestController, which means it is a Controller
@RequestMapping("/places")//This annotation is used to map web requests, in this case, all requests will contain /places
public class PlaceController {

    @Autowired//This annotaion indicates a Dependency Injection
    private PlaceService placeService;//This attribute will be used to call the methods in class PlaceService
}
