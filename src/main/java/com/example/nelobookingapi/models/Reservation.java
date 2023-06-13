package com.example.nelobookingapi.models;

import io.hypersistence.utils.hibernate.type.range.PostgreSQLRangeType;
import io.hypersistence.utils.hibernate.type.range.Range;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "reservations")
public class Reservation {
    
    @Id
    private UUID id = UUID.randomUUID();
    
    @Type(PostgreSQLRangeType.class)
    @Column(
        name = "interval",
        columnDefinition = "int4range"
    )
    private Range<Integer> interval;
    
    private LocalDate date;
    
    @ManyToOne(optional = false)
    private Restaurant restaurant;
    
    private int tableSize;
    
    public Reservation() {
    
    }
    
    public Reservation(Range<Integer> interval, LocalDate date, Restaurant restaurant, int tableSize) {
        this.interval = interval;
        this.date = date;
        this.restaurant = restaurant;
        this.tableSize = tableSize;
    }
    
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
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
    
    public Restaurant getRestaurant() {
        return restaurant;
    }
    
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    
    public int getTableSize() {
        return tableSize;
    }
    
    public void setTableSize(int tableSize) {
        this.tableSize = tableSize;
    }
}
