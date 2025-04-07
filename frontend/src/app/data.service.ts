import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Airport, BookingRequest, Flight, Passenger} from './interfaces';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  httpClient = inject(HttpClient);
  BASE_URL = "http://localhost:8080/api";

  getAllFlights() {
    return this.httpClient.get<Flight[]>(`${this.BASE_URL}/flight/information/list`)
  }

  getAllAirports() {
    return this.httpClient.get<Airport[]>(`${this.BASE_URL}/airport/list`)
  }

  getPassengerByPassport(passport: string) {
    return this.httpClient.get<Passenger>(`${this.BASE_URL}/passenger/passport/${passport}`)
  }

  createBooking(bookingRequest: BookingRequest) {
    return this.httpClient.post(`${this.BASE_URL}/booking`, bookingRequest);
  }
}
