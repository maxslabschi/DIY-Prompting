package org.example.boundary;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.DTO.FlightDTO;
import org.example.DTO.PassengerSummary;
import org.example.model.Flight;
import org.example.model.Passenger;

import java.util.List;

@Path("/api/flight")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FlightResource {
    @GET
    @Path("/information/list")
    public Response getAllFlightInfos() {
        List<Flight> flights = Flight.listAll();
        return Response.ok(flights.stream().map(FlightDTO::createFlightDTO)).build();
    }

    @GET
    @Path("/{flightNumber}")
    public Response getFlightByFlightNumber(@PathParam("flightNumber") String flightNumber) {
        Flight flight = (Flight) Flight.find("flightNumber", flightNumber).list().getFirst();
        return Response.ok(FlightDTO.createFlightDTO(flight)).build();
    }

    @GET
    @Path("/{flightNumber}/passenger")
    public Response getPassengerByFlightNumber(@PathParam("flightNumber") String flightNumber) {
        Flight flight = (Flight) Flight.find("flightNumber", flightNumber).list().getFirst();
        List<Passenger> passengers = flight.bookings.stream().map(p -> p.passenger).toList();
        return Response.ok(passengers.stream().map(PassengerSummary::createPassengerDTO)).build();
    }
}
