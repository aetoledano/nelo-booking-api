package com.example.nelobookingapi.models;

import io.hypersistence.utils.hibernate.type.range.PostgreSQLRangeType;
import io.hypersistence.utils.hibernate.type.range.Range;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.Type;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "client_reservation_data")
public class ClientReservationData {
    
    @Id
    @Generated
    private UUID id;
    
    @Type(PostgreSQLRangeType.class)
    @Column(
        name = "interval",
        columnDefinition = "int4range"
    )
    private Range<Integer> interval;
    
    private Date date;
    
    private UUID reservationId;
    
    private UUID clientId;
}
