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
@Table(name = "clients")
public class Client {
    
    @Id
    private UUID id = UUID.randomUUID();
    
    private String name;
    
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
        name = "client_dietary_group",
        joinColumns = {@JoinColumn(name = "client_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "dietary_group_id", referencedColumnName = "id")}
    )
    private List<DietaryGroup> restrictions = new ArrayList<>();
    
    public Client() {
    
    }
    
    public Client(String name) {
        this.name = name;
    }
    
    public Client(String name, List<DietaryGroup> diets) {
        this.name = name;
        this.restrictions = diets;
    }
    
    public String getName() {
        return name;
    }
    
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public List<DietaryGroup> getRestrictions() {
        return restrictions;
    }
    
    public void setRestrictions(List<DietaryGroup> restrictions) {
        this.restrictions = restrictions;
    }
}
