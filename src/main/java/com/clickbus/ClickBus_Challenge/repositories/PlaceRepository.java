package com.clickbus.ClickBus_Challenge.repositories;

import com.clickbus.ClickBus_Challenge.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository //This annotation indicates that Interface is a repository and will be used for queries
//this interface extend JpaRepostiry and will be use in more simples queries and extend the interface PagingAndSortingRepository (will be used to display chunks of data of suitable sizes with high readability
public interface PlaceRepository extends JpaRepository<Place,Long>, PagingAndSortingRepository<Place,Long> {

    //Method to search a specific Place in database, this method receive a slug and use that to search a place
    Place getBySlug(String slugRequest);
}
