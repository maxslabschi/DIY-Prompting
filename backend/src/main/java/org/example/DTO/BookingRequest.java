package org.example.DTO;

import org.example.model.Passenger;

public record BookingRequest(String departureCity, String destinationCity, Passenger passenger, String seatNumber, double price) {
}
