import { Routes } from '@angular/router';
import {HomepageComponent} from './homepage/homepage.component';
import {FlightsComponent} from './flights/flights.component';
import {BookingComponent} from './booking/booking.component';

export const routes: Routes = [
  {
    path: '',
    component: HomepageComponent,
    title: 'Slairlines'
  },
  {
    path: 'flights',
    component: FlightsComponent,
    title: 'Slairlines'
  },
  {
    path: 'bookings',
    component: BookingComponent,
    title: 'Slairlines'
  }
];
