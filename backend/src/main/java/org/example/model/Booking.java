package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Booking extends PanacheEntity {
    @ManyToOne
    @JsonIgnoreProperties("bookings")
    public Flight flight;

    @ManyToOne
    @JsonIgnoreProperties("bookings")
    public Passenger passenger;
    @Column(name = "seat_number")
    public String seatNumber;
    @Column(name = "ticket_price")
    public double ticketPrice;

    public Booking() {}

    public Booking(Flight flight, Passenger passenger, String seatNumber, double price) {
        this.flight = flight;
        this.passenger = passenger;
        this.seatNumber = seatNumber;
        this.ticketPrice = price;
    }
}
