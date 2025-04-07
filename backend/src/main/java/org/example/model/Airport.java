package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Airport extends PanacheEntity {
    public String name;
    public String country;
    public String city;
    @Column(unique = true, name = "iata_code")
    public String iataCode;

    @OneToMany(mappedBy = "departureAirport")
    @JsonIgnoreProperties("departureAirport")
    private List<Flight> departureFlights;

    @OneToMany(mappedBy = "arrivalAirport")
    @JsonIgnoreProperties("arrivalAirport")
    private List<Flight> arrivalFlights;
}