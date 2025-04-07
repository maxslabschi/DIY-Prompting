package org.example.boundary;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.model.Passenger;

import java.util.List;

@Path("/api/passenger")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PassengerResource {
    @Path("/list")
    @GET
    public Response getAllPassengers() {
        List<Passenger> passengers = Passenger.listAll();
        return Response.ok(passengers).build();
    }

    @GET
    @Path("/{id}")
    public Response getPassengerById(@PathParam("id") long id) {
        Passenger passenger = Passenger.findById(id);
        return Response.ok(passenger).build();
    }

    @GET
    @Path("/passport/{passport}")
    public Response getPassengerByEmail(@PathParam("passport") String passport) {
        Passenger passenger = Passenger.find("passportNumber", passport).firstResult();
        return Response.ok(passenger).build();
    }

    @POST
    @Transactional
    public Response addPassenger(Passenger passenger) {
        passenger.persistAndFlush();
        return Response.ok(passenger).build();
    }
}
