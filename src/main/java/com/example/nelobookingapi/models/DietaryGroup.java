package com.example.nelobookingapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dietary_groups")
public class DietaryGroup implements Serializable {
    
    private Long id;
    @Enumerated(EnumType.STRING)
    private DietName name;
    
    public DietaryGroup(DietName name) {
        this.name = name;
    }
    
    public DietaryGroup() {
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
    
    public DietName getName() {
        return name;
    }
    
    public void setName(DietName name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name.name();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DietaryGroup)) return false;
        return ((DietaryGroup) obj).name.equals(name);
    }
    
    @Override
    public int hashCode() {
        return name.hashCode();
    }
    
//    private List<Restaurant> restaurants = new ArrayList<>();
//    private List<Client> clients = new ArrayList<>();
//
//    @ManyToMany(mappedBy = "diets", fetch = FetchType.EAGER)
//    public List<Restaurant> getRestaurants() {
//        return restaurants;
//    }
//
//    public void setRestaurants(List<Restaurant> restaurants) {
//        this.restaurants = restaurants;
//    }
//
//    @ManyToMany(mappedBy = "restrictions", fetch = FetchType.EAGER)
//    public List<Client> getClients() {
//        return clients;
//    }
//
//    public void setClients(List<Client> clients) {
//        this.clients = clients;
//    }
}
