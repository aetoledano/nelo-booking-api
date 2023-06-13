package com.example.nelobookingapi.repositories;

import com.example.nelobookingapi.models.Client;
import com.example.nelobookingapi.models.ClientReservationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface ClientReservationDataRepo extends JpaRepository<ClientReservationData, UUID> {

}
