package com.example.nelobookingapi.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "restaurants")
public class Restaurant {
    
    @Id
    private UUID id = UUID.randomUUID();
    private String name;
    
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
        name = "restaurant_dietary_group",
        joinColumns = {@JoinColumn(name = "restaurant_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "dietary_group_id", referencedColumnName = "id")}
    )
    private List<DietaryGroup> diets = new ArrayList<>();
    
    private int twoSeatTableCapacity, fourSeatTableCapacity, sixSeatTableCapacity;
    
    public Restaurant(String name, List<DietaryGroup> diets, int twoSeatTableCapacity, int fourSeatTableCapacity, int sixSeatTableCapacity) {
        this.name = name;
        this.diets = diets;
        this.twoSeatTableCapacity = twoSeatTableCapacity;
        this.fourSeatTableCapacity = fourSeatTableCapacity;
        this.sixSeatTableCapacity = sixSeatTableCapacity;
    }
    
    public Restaurant() {
    }
    
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<DietaryGroup> getDiets() {
        return diets;
    }
    
    public void setDiets(List<DietaryGroup> diets) {
        this.diets = diets;
    }
    
    public int getTwoSeatTableCapacity() {
        return twoSeatTableCapacity;
    }
    
    public void setTwoSeatTableCapacity(int twoSeatTableCapacity) {
        this.twoSeatTableCapacity = twoSeatTableCapacity;
    }
    
    public int getFourSeatTableCapacity() {
        return fourSeatTableCapacity;
    }
    
    public void setFourSeatTableCapacity(int fourSeatTableCapacity) {
        this.fourSeatTableCapacity = fourSeatTableCapacity;
    }
    
    public int getSixSeatTableCapacity() {
        return sixSeatTableCapacity;
    }
    
    public void setSixSeatTableCapacity(int sixSeatTableCapacity) {
        this.sixSeatTableCapacity = sixSeatTableCapacity;
    }
    
    @Override
    public String toString() {
        return "Restaurant{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", diets=" + diets +
            ", twoSeatTableCapacity=" + twoSeatTableCapacity +
            ", fourSeatTableCapacity=" + fourSeatTableCapacity +
            ", sixSeatTableCapacity=" + sixSeatTableCapacity +
            '}';
    }
}
