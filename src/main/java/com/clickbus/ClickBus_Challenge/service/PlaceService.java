package com.clickbus.ClickBus_Challenge.service;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.clickbus.ClickBus_Challenge.dto.PlaceDTO;
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

    //This method is gonna used to uptaed a existent Place. In case the Place is not found, throw a PlaceNotFoundException. Case the Place is found, update that Place using the informations sended in the PlaceDTO body in  updatePlace method in PlaceController)
    public Place savePlace(PlaceDTO placeDto){
        LocalDateTime localDateTime = LocalDateTime.now();
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date dateCreatedAt = Date.from(instant);
        Date dateUpdateAt = Date.from(instant);
        Place place = new Place(placeDto.getName(),placeDto.getCity(),placeDto.getState(), dateCreatedAt,dateUpdateAt);
        return this.placeRepository.save(place);
    }
    
    //This method will be used to realize a update in a specific Place (in case its exists). Case the specific Place search not exists, throws a PlaceNotFoundException. Case the searched Place exists, realize a updated 
    public Place updatePlace(PlaceDTO placeDto, String slugRequest){//Receives a PlaceDto body with the informations for Update and Receives a Slug that will be used to search in the database
        Place place = this.getBySlug(slugRequest);//Search the Place in database
        String placeSlug = place.getSlug();//get a slug from the Place searched before

        if(placeDto.getName() != null){
            placeSlug = placeSlug.replaceAll(place.getName().toLowerCase(), placeDto.getName().toLowerCase());//Replace the part with old Name of Place for a new Name of Place in the placeSlug
            place.setName(placeDto.getName());//change the value of property Name of Place for a value of property Name in placeDto
        }
        if(placeDto.getCity() != null){
            placeSlug = placeSlug.replaceAll(place.getCity().toLowerCase(), placeDto.getCity().toLowerCase());
            place.setCity(placeDto.getCity());//change the value of property Name of Place for a value of property Name in placeDto
        }
        if(placeDto.getState() != null){
            placeSlug = placeSlug.replaceAll(place.getState().toLowerCase(), placeDto.getState().toLowerCase());
            place.setState(placeDto.getState());//change the value of property Name of Place for a value of property Name in placeDto
        }
        
        LocalDateTime localDateTime = LocalDateTime.now();
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date dateUpdateAt = Date.from(instant);
        
        place.setSlug(placeSlug);//set the new Slug for the existent Place
        place.setUpdateAt(dateUpdateAt);//set the dateUpdatedAt for the existent Place
        return this.placeRepository.save(place);//Save the updated Place in database (in this case, will just update the old Place)
    }

    public void deletePlaceBySlug(String slugRequest){//This method will be used to delete Place. If the Place is found, it will be deleted. If not, an exception of type PlaceNotFoundExcpetion will be thrown.
        Place place = this.getBySlug(slugRequest);//Realize a search in the database searching the Place using the slugRequest parameter 
        this.placeRepository.delete(place);//call the method delete from interface PlaceRepository
    }

    public List<Place> searchPlaces(Long id, String name, String state, String city, String slug){
       return this.placeRepository.searchPlaces(id, name, state, city, slug);
    }

}
