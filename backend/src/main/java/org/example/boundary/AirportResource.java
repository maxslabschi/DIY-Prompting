package org.example.boundary;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.DTO.FlightDTO;
import org.example.model.Airport;
import org.example.model.Flight;

import java.util.List;

@Path("/api/airport")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AirportResource {
    @GET
    @Path("/list")
    public static Response getAllAirports() {
        List<Airport> airports = Airport.listAll();
        return Response.ok(airports).build();
    }

    @GET
    @Path("/{airportId}")
    public static Response getAirport(@PathParam("airportId") long airportId) {
        Airport airport = Airport.findById(airportId);
        return Response.ok(airport).build();
    }

    @GET
    @Path("/{airportId}/flights/arrival")
    public Response getAllArrivingFlights(@PathParam("airportId") long airportId) {
        List<Flight> flights = Flight.listAll();
        return Response.ok(flights.stream().filter(f -> f.arrivalAirport.id == airportId).map(FlightDTO::createFlightDTO)).build();
    }

    @GET
    @Path("/{airportId}/flights/departure")
    public Response getAllDepartingFlights(@PathParam("airportId") long airportId) {
        List<Flight> flights = Flight.listAll();
        return Response.ok(flights.stream().filter(f -> f.departureAirport.id == airportId).map(FlightDTO::createFlightDTO)).build();
    }
}
