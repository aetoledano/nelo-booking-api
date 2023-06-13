package com.example.nelobookingapi.models;

import io.hypersistence.utils.hibernate.type.range.PostgreSQLRangeType;
import io.hypersistence.utils.hibernate.type.range.Range;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "client_reservation_data")
public class ClientReservationData {
    
    @Id
    @Generated
    private Long id;
    
    @Type(PostgreSQLRangeType.class)
    @Column(
        name = "interval",
        columnDefinition = "int4range"
    )
    private Range<Integer> interval;
    
    private LocalDate date;
    
    @OneToOne
    @JoinColumn(name = "reservation_id", referencedColumnName = "id")
    private Reservation reservation;
    
    @OneToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;
    
    public ClientReservationData(Range<Integer> interval, LocalDate date, Reservation reservation, Client client) {
        this.interval = interval;
        this.date = date;
        this.reservation = reservation;
        this.client = client;
    }
    
    public ClientReservationData() {
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Range<Integer> getInterval() {
        return interval;
    }
    
    public void setInterval(Range<Integer> interval) {
        this.interval = interval;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public Reservation getReservation() {
        return reservation;
    }
    
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
    
    public Client getClient() {
        return client;
    }
    
    public void setClient(Client client) {
        this.client = client;
    }
}
