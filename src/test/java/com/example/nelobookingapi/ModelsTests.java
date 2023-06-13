package com.example.nelobookingapi;

import com.example.nelobookingapi.models.Client;
import com.example.nelobookingapi.models.ClientReservationData;
import com.example.nelobookingapi.models.DietaryGroup;
import com.example.nelobookingapi.models.Reservation;
import com.example.nelobookingapi.models.Restaurant;
import com.example.nelobookingapi.repositories.ClientRepo;
import com.example.nelobookingapi.repositories.ClientReservationDataRepo;
import com.example.nelobookingapi.repositories.ReservationRepo;
import com.example.nelobookingapi.repositories.RestaurantRepo;
import io.hypersistence.utils.hibernate.type.range.Range;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.nelobookingapi.models.DietName.GLUTEN_FREE;
import static com.example.nelobookingapi.models.DietName.VEGAN;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ModelsTests {
    
    @Autowired
    RestaurantRepo restaurantRepo;
    
    @Autowired
    ClientRepo clientRepo;
    
    @Autowired
    ReservationRepo reservationRepo;
    
    @Autowired
    ClientReservationDataRepo clientReservationDataRepo;
    
    @Test
    void TestRestaurantRetrieval() {
        var restaurants = restaurantRepo.findAll();
        
        assertEquals(5, restaurants.size());
        
        // check laredo dietary offers
        Optional<Restaurant> first = restaurants.stream().filter(
            r -> Objects.equals(r.getName(), "Lardo")
        ).findFirst();
        
        assertTrue(first.isPresent());
        
        var r = first.get();
        
        var dg = new HashSet<>(List.of(new DietaryGroup(GLUTEN_FREE)));
        
        assertArrayEquals(dg.toArray(), r.getDiets().toArray());
    }
    
    @Test
    void TestClientsRetrieval() {
        var clients = clientRepo.findAll();
        
        assertEquals(7, clients.size());
        
        // check paulina dietary offers
        Optional<Client> first = clients.stream().filter(c -> Objects.equals(c.getName(), "Paulina")).findFirst();
        
        assertTrue(first.isPresent());
        
        var c = first.get();
        
        var dg = new HashSet<>(List.of(new DietaryGroup(VEGAN)));
        
        assertArrayEquals(dg.toArray(), c.getRestrictions().toArray());
    }
    
    @Test
    void TestRestaurantsByDietaryRestriction() {
        var names = new String[]{"Paulina", "Michael"};
        var clients = clientRepo.findAllByNames(List.of(names));
        
        assertEquals(2, clients.size());
        
        Set<Long> dgs = clients.stream()
            .flatMap(
                c -> c.getRestrictions().stream()
            ).map(
                dg -> dg.getId()
            ).collect(Collectors.toSet());
        
        List<Restaurant> restaurants = restaurantRepo.findAllByDietRestrictions(dgs, dgs.size());
        assertEquals(1, restaurants.size());
        
        var opt = restaurants.stream().findFirst();
        assertTrue(opt.isPresent());
        
        var r = opt.get();
        assertEquals("u.to.pi.a", r.getName());
        assertEquals(r.getDiets().stream().map(DietaryGroup::getId).collect(Collectors.toSet()), dgs);
    }
    
    @Test
    void TestSomeClientNotFoundInDB() {
        var names = new String[]{"Pauline", "Michael"};
        var clients = clientRepo.findAllByNames(List.of(names));
        
        assertEquals(1, clients.size());
    }
    
    @Test
    void TestNewReservationCanBeStoredWithMetadata() {
        var names = new String[]{"Paulina", "Michael"};
        var clients = clientRepo.findAllByNames(List.of(names));
        
        assertEquals(2, clients.size());
        
        Set<Long> dgs = clients.stream()
            .flatMap(
                c -> c.getRestrictions().stream()
            ).map(
                dg -> dg.getId()
            ).collect(Collectors.toSet());
        
        List<Restaurant> restaurants = restaurantRepo.findAllByDietRestrictions(dgs, dgs.size());
        
        var opt = restaurants.stream().findFirst();
        assertTrue(opt.isPresent());
        var restaurant = opt.get();
        
        var range = Range.closed(1000, 1200);
        var date = LocalDate.now();
        
        var reservation = new Reservation(
            range,
            date,
            restaurant,
            2
        );
        
        var crd = clients.stream().map(c -> new ClientReservationData(
            range,
            date,
            reservation,
            c
        )).collect(Collectors.toSet());
        
        assertDoesNotThrow(() -> reservationRepo.save(reservation));
        assertDoesNotThrow(() -> clientReservationDataRepo.saveAll(crd));
    }
    


    @AfterAll
    void cleanReservations() {
        reservationRepo.deleteAll();
    }
}
