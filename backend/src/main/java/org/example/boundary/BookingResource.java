package org.example.boundary;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.DTO.BookingRequest;
import org.example.DTO.TicketSummary;
import org.example.model.Booking;
import org.example.model.Flight;
import org.example.model.Passenger;
import org.example.websocket.BookingWebsocket;

import java.util.List;

@Path("/api/booking")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookingResource {
    @GET
    @Path("/list")
    public Response getAllBookings() {
        List<Booking> bookings = Booking.listAll();
        return Response.ok(bookings).build();
    }

    @GET
    @Path("/{bookingId}")
    public Response getBooking(@PathParam("bookingId") long bookingId) {
        Booking booking = Booking.findById(bookingId);
        return Response.ok(booking).build();
    }

    @GET
    @Path("/{bookingId}/ticket")
    public Response getBookingTicket(@PathParam("bookingId") long id) {
        Booking booking = Booking.findById(id);
        return Response.ok(TicketSummary.createBookingSummary(booking)).build();
    }

    @POST
    @Transactional
    public Response addBooking(BookingRequest bookingRequest) {
        Passenger passenger = Passenger.find("passportNumber", bookingRequest.passenger().passportNumber).firstResult();

        if(passenger == null) {
            passenger = bookingRequest.passenger();
            passenger.persist();
        }

        Flight flight = Flight.find("departureAirport.city = ?1 and arrivalAirport.city = ?2", bookingRequest.departureCity(), bookingRequest.destinationCity()).firstResult();
        Booking booking = new Booking(flight, passenger, bookingRequest.seatNumber(), bookingRequest.price());
        booking.persist();

        String bookingDetails = "New booking for flight " + booking.flight.flightNumber + " mit with passenger " + passenger.firstName + " " + passenger.lastName;
        BookingWebsocket.sendNewBookingNotification(bookingDetails);
        return Response.ok().build();
    }
}
