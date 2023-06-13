package com.example.nelobookingapi.repositories;

import com.example.nelobookingapi.models.Client;
import com.example.nelobookingapi.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, UUID> {
    
    @Query(
        nativeQuery = true,
        value = """
            WITH restaurant_ids (id) AS (
              select restaurant_id
              from restaurant_dietary_group rdg
              where rdg.dietary_group_id in :restrictions
              group by restaurant_id
              having count(restaurant_id) = :size
            )
            select r.* from restaurants r join restaurant_ids rids on r.id = rids.id
            """
    )
    List<Restaurant> findAllByDietRestrictions(
        @Param("restrictions") Collection<Long> restrictions,
        @Param("size") int size
    );
}
