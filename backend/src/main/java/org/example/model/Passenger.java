package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.awt.print.Book;
import java.util.List;

@Entity
public class Passenger extends PanacheEntity {
    @Column(name = "first_name")
    public String firstName;

    @Column(name = "last_name")
    public String lastName;

    @Column(unique = true)
    public String email;

    @Column(name = "phone_number")
    public String phoneNumber;

    @Column(unique = true, name = "passport_number")
    public String passportNumber;

    @Enumerated(EnumType.STRING)
    public Nationality nationality;
    public String city;

    @Enumerated(EnumType.STRING)
    public Country country;

    @OneToMany(mappedBy = "passenger")
    @JsonIgnoreProperties("passenger")
    public List<Booking> bookings;
}
