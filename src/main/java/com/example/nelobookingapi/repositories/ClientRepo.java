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
public interface ClientRepo extends JpaRepository<Client, UUID> {
    
    @Query(
        nativeQuery = true,
        value = "select c.* from clients c where name in :names"
    )
    List<Client> findAllByNames(@Param("names") Collection<String> names);
}
