export interface Airport {
  id: number;
  name: string;
  country: string;
  city: string;
  iataCode: string;
}

export interface Flight {
  flightNumber: string;
  departureAirport: Airport;
  arrivalAirport: Airport;
  departureTime: string;
  arrivalTime: string;
}

export interface BookingRequest {
  departureCity: string,
  destinationCity: string,
  passenger: Passenger,
  seatNumber: string,
  price: number
}

export interface Passenger {
  firstName: string;
  lastName: string;
  email: string;
  phoneNumber: string;
  passportNumber: string;
  nationality: string;
  city: string;
  country: string;
  bookings: Booking[];
}

export interface Booking {
  id: number;
  flight: Flight;
  passenger: Passenger;
  seatNumber: string;
  ticketPrice: number;
}
