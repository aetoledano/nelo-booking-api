package com.example.nelobookingapi.repositories;

import com.example.nelobookingapi.models.Client;
import com.example.nelobookingapi.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, UUID> {
    
    @Query(
        nativeQuery = true,
        value = """
            select count(*) from reservations
            where restaurant_id = :restaurantId
            and date = :date
            and table_size = :tableSize
            and interval && int4range(:start, :end)
            """
    )
    int findOccupiedTablesOfSize(
        @Param("restaurantId") UUID restaurantId,
        @Param("date") LocalDate date,
        @Param("tableSize") int tableSize,
        @Param("start") int start,
        @Param("end") int end
    );
}
