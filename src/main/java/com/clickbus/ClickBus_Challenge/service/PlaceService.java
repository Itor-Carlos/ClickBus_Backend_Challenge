package com.clickbus.ClickBus_Challenge.service;

import com.clickbus.ClickBus_Challenge.exceptions.PlaceNotFoundException;
import com.clickbus.ClickBus_Challenge.model.Place;
import com.clickbus.ClickBus_Challenge.repositories.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service//This annotation indicates that the Class is a Service and will be used to define the business rules
public class PlaceService {

    @Autowired//This annotaion indicates a Dependency Injection
    private PlaceRepository placeRepository;//This attribute will be used to call the methods that perform operations on the database.

    //This method is used to get all records of the class Place in the database
    public Page<Place> getAllPlaces(Pageable pageable){//Receive a Pageable object as a parameter
        return this.placeRepository.findAll(pageable);//return the Page<Place> for the caller 
    }

    //This method is used to search a specific Place. In case the Place is not found, throws a PlaceNotFoundException
    public Place getBySlug(String slugRequest){
        Place place = this.placeRepository.getBySlug(slugRequest);
        if(place != null){
            return place;
        }
        else{
            throw new PlaceNotFoundException("Place Not Found");
        }
    }
}
