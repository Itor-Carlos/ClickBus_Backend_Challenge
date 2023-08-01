package com.clickbus.ClickBus_Challenge.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.clickbus.ClickBus_Challenge.dto.PlaceDTO;
import com.clickbus.ClickBus_Challenge.exceptions.PlaceAlredyExistExceptionDetails;
import com.clickbus.ClickBus_Challenge.exceptions.PlaceFieldNotValidExceptionDetails;
import com.clickbus.ClickBus_Challenge.exceptions.PlaceNotFoundExceptionDetails;
import com.clickbus.ClickBus_Challenge.model.Place;
import com.clickbus.ClickBus_Challenge.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController //This annotation indicates that class is a RestController, which means it is a Controller
@RequestMapping("/places")//This annotation is used to map web requests, in this case, all requests will contain /places
public class PlaceController {

    @Autowired//This annotaion indicates a Dependency Injection
    private PlaceService placeService;//This attribute will be used to call the methods in class PlaceService

    @Operation(summary = "Get All Places in database")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of Places", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))
        })
    })
    @GetMapping/*Maps this function to GET requests (/places) || In this requests you can pass some parameters, like: size (sets the number of page elements), sort: (sorts records based on attribute of the class used, in this case, class Place), page(sets page number)*/
    public ResponseEntity<?> getAllPlaces(Pageable pageable){//Receive a Pageable object as a parameter
       Page pageRequest = this.placeService.getAllPlaces(pageable);
       List<Place> lista = pageRequest.getContent();
       for(Place element: lista){
           String slugElement = element.getSlug();
           element.add(linkTo(methodOn(PlaceController.class).getBySlug(slugElement)).withSelfRel());
       }
       pageRequest = new PageImpl<>(lista, pageable, lista.size());
       return ResponseEntity.ok(pageRequest);
    }

    @Operation(summary = "Search a specific Place in database usign Slug as parameter")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Place found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Place.class))
        }),
        @ApiResponse(responseCode = "404", description = "Place not found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = PlaceNotFoundExceptionDetails.class))
        })
    })
    @GetMapping(path = "/{slug}",produces = MediaType.APPLICATION_JSON_VALUE)//This method will be used in GET requests when the path has "/{slug}
    public ResponseEntity<?> getBySlug(@PathVariable("slug")String slugRequest){
        Pageable pageable = PageRequest.ofSize(5);//Make a Pageable object 
        Place placeReturned = this.placeService.getBySlug(slugRequest);//search a Place usign field Slug what parameter
        placeReturned.add(linkTo(methodOn(PlaceController.class).getAllPlaces(pageable)).withRel("List Places:"));//add a relationship: pass the request to return all places
        return ResponseEntity.ok(placeReturned);//statement return
    }

    @Operation(summary = "Save a Place in database. Ignore 'links' property")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Place created", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Place.class))
        }),
        @ApiResponse(responseCode = "400", description = "Place Field NOT Valid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = PlaceFieldNotValidExceptionDetails.class))
        }),
        @ApiResponse(responseCode = "409", description = "Place Alredy Exist", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = PlaceAlredyExistExceptionDetails.class))
        }) 
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> savePlace(@RequestBody @Valid PlaceDTO placeDto){
        Place savedPlace = this.placeService.savePlace(placeDto);
        URI locationSavedPlace = URI.create("/places/"+savedPlace.getSlug());
        return ResponseEntity.created(locationSavedPlace).body(savedPlace);
    }

    @Operation(summary = "Edit a existent Place. Ignore 'links' property")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Changed Place", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Place.class))
        }),
        @ApiResponse(responseCode = "400", description = "Place Field Not Valid. Operation cannot continue", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = PlaceFieldNotValidExceptionDetails.class))
        }),
        @ApiResponse(responseCode = "404", description = "Place not found in this Slug. Operation cannot continue", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = PlaceNotFoundExceptionDetails.class))
        }),
        @ApiResponse(responseCode = "409", description = "This Place alredy exist in database. Operation cannot continue", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = PlaceAlredyExistExceptionDetails.class))
        })
    })
    @PutMapping("/{slug}")//This method will be used in PUT requets when the url: /places/{slug}
    public ResponseEntity<?> updatePlace(@RequestBody PlaceDTO placeDto, @PathVariable("slug")String slugRequest){//Receives a PlaceDTO object with the informations from Update and a Slug to search the specific Place
        Place placeUpdated = this.placeService.updatePlace(placeDto, slugRequest);
        return ResponseEntity.ok(placeUpdated);
    }

    @Operation(summary = "Delete a specific Place in database")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Deleted Place", content = {
            @Content(mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "404", description = "Place not found in this Slug", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = PlaceNotFoundExceptionDetails.class))
        })
    })
    @DeleteMapping("/{slug}")//This method will be used in DELETE requests when the URL: /places/{slug} || This method is gonna return a Http Status code 200 or a return a ResponseEntity with PlaceNotFoundExceptionDetails in your body
    public ResponseEntity<?> deletePlaceBySlug(@PathVariable("slug")String slugRequest){
        this.placeService.deletePlaceBySlug(slugRequest);//call the method deletePlaceBySlug
        return ResponseEntity.ok().build();//return a Http Status code 200
    }

    @GetMapping("/search")
    public ResponseEntity<List<Place>> searchPlaces(@RequestParam(value = "id", required = false) Long id,@RequestParam(value = "name",required = false) String name ,@RequestParam(value = "state", required = false) String state, @RequestParam(value = "city", required = false) String city, @RequestParam(value = "slug", required = false) String slug){
        List<Place> resultList = this.placeService.searchPlaces(id, name, state, city, slug);
        return ResponseEntity.ok(resultList);
    }
}
