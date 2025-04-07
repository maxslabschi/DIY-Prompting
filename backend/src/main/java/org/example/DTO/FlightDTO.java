package org.example.DTO;

import org.example.model.Airplane;
import org.example.model.Airport;
import org.example.model.Flight;

import java.util.Date;

public record FlightDTO(String flightNumber, Airport departureAirport, Airport arrivalAirport, Date departureTime, Date arrivalTime) {
    public static FlightDTO createFlightDTO(Flight flight) {
        return new FlightDTO(flight.flightNumber, flight.departureAirport, flight.arrivalAirport, flight.departureTime, flight.arrivalTime);
    }
}
