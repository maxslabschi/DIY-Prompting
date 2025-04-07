import {Component, inject} from '@angular/core';
import {MatCard, MatCardActions, MatCardContent} from '@angular/material/card';
import {Router} from '@angular/router';
import {BookingUpdateComponent} from '../booking-update/booking-update.component';

@Component({
  selector: 'app-homepage',
  imports: [
    MatCard,
    MatCardContent,
    MatCardActions,
    BookingUpdateComponent
  ],
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.css'
})
export class HomepageComponent {
  router: Router = inject(Router)

  navigateToBooking(): void {
    this.router.navigate(['/bookings']);
  }
}
