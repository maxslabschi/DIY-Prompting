package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Flight extends PanacheEntity {
    @Column(unique = true, name = "flight_number")
    public String flightNumber;

    @ManyToOne
    @JoinColumn(name = "departure_airport")
    @JsonIgnoreProperties("departureFlights")
    public Airport departureAirport;

    @ManyToOne
    @JsonIgnoreProperties("arrivalFlights")
    @JoinColumn(name = "arrival_airport")
    public Airport arrivalAirport;

    @ManyToOne
    @JoinColumn(name = "airplane_id")
    @JsonIgnoreProperties("flights")
    public Airplane airplane;

    @Column(name = "departure_time")
    public Date departureTime;
    @Column(name = "arrival_time")
    public Date arrivalTime;

    @OneToMany(mappedBy = "flight")
    @JsonIgnoreProperties("flight")
    public List<Booking> bookings;

    @ManyToMany(mappedBy = "flights")
    @JsonIgnoreProperties("flights")
    public List<Crew> crews;
}
