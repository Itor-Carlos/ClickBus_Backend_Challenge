package com.clickbus.ClickBus_Challenge.controller;

import com.clickbus.ClickBus_Challenge.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //This annotation indicates that class is a RestController, which means it is a Controller
@RequestMapping("/places")//This annotation is used to map web requests, in this case, all requests will contain /places
public class PlaceController {

    @Autowired//This annotaion indicates a Dependency Injection
    private PlaceService placeService;//This attribute will be used to call the methods in class PlaceService

    @GetMapping//Maps this function to GET requests (/places)
    public ResponseEntity<?> getAllPlaces(Pageable pageable){//Receive a Pageable object as a parameter
        return ResponseEntity.ok(this.placeService.getAllPlaces(pageable));//Return a ResponseEntity object as answer
    }

}
