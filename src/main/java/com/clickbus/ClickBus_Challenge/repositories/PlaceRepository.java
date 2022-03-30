package com.clickbus.ClickBus_Challenge.repositories;

import com.clickbus.ClickBus_Challenge.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //This annotation indicates that Interface is a repository and will be used for queries
//this interface extend JpaRepostiry and will be use in more simples queries
public interface PlaceRepository extends JpaRepository<Place,Long> {
}
