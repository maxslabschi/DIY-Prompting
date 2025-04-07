package org.example.DTO;

import org.example.model.Booking;

import java.util.Date;

public record TicketSummary(String flightNumber, String departureCity, String arrivalCity, String departureIataCode, String arrivalIataCode, Date departureTime, Date arrivalTime, String name , String seatNumber, double price) {
    public static TicketSummary createBookingSummary(Booking booking) {
        String name = String.format("%s %s", booking.passenger.firstName, booking.passenger.lastName);
        return new TicketSummary(booking.flight.flightNumber, booking.flight.departureAirport.city, booking.flight.arrivalAirport.city, booking.flight.departureAirport.iataCode, booking.flight.arrivalAirport.iataCode, booking.flight.departureTime, booking.flight.arrivalTime, name, booking.seatNumber, booking.ticketPrice);
    }
}
