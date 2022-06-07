package com.clickbus.ClickBus_Challenge.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.clickbus.ClickBus_Challenge.model.Place;

@Repository //This annotation indicates that Class is a repository and will be used for more complex queries
public class PlaceRepositoryImpl {


    @PersistenceContext
    private EntityManager entityManager;

    public List<Place> searchPlaces(Long id, String name, String state, String city, String slug){
        StringBuilder jpql = new StringBuilder();
        jpql.append("from Place WHERE 0 = 0");

        Map<String, Object> mapParameter = new HashMap<>();

        if(id != null){
            jpql.append(" and id = :id");
            mapParameter.put("id", id);
        }
        
        if(name != null){
            jpql.append(" and name LIKE :name");
            mapParameter.put("name", name.toLowerCase()+"%");
        }

        if(state != null){
            jpql.append(" and state LIKE :state");
            mapParameter.put("state", state.toLowerCase()+"%");
        }

        if(city != null){
            jpql.append(" and city LIKE :city");
            mapParameter.put("city", city.toLowerCase()+"%");
        }

        if(slug != null){
            jpql.append(" and slug = :slug");
            mapParameter.put("slug", slug);
        }

        TypedQuery<Place> query = this.entityManager.createQuery(jpql.toString(), Place.class);
        mapParameter.forEach((key,value) -> query.setParameter(key, value));

        return query.getResultList();
    } 

}

