package org.example.DTO;

import org.example.model.Country;
import org.example.model.Passenger;

public record PassengerSummary(String firstName, String lastName, String email, String phoneNumber, String passportNumber, String city, Country country) {
    public static PassengerSummary createPassengerDTO(Passenger passenger) {
        return new PassengerSummary(passenger.firstName, passenger.lastName, passenger.email, passenger.phoneNumber, passenger.passportNumber, passenger.city, passenger.country);
    }
}
