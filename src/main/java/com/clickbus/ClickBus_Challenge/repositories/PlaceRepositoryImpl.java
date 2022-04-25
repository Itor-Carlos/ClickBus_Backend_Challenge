package com.clickbus.ClickBus_Challenge.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository //This annotation indicates that Class is a repository and will be used for more complex queries
public class PlaceRepositoryImpl {


    @PersistenceContext
    private EntityManager entityManager;
}

