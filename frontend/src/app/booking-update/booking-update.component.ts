import {Component, inject} from '@angular/core';
import {Subscription} from 'rxjs';
import {WebsocketService} from '../websocket.service';
import {NgForOf} from '@angular/common';

@Component({
  selector: 'app-booking-update',
  imports: [
    NgForOf
  ],
  templateUrl: './booking-update.component.html',
  styleUrl: './booking-update.component.css'
})
export class BookingUpdateComponent {
  bookingUpdates: string[] = [];
  messagesSubscription: Subscription | undefined;
  websocketService: WebsocketService = inject(WebsocketService);

  ngOnInit() {
    this.messagesSubscription = this.websocketService.getMessages().subscribe((message: string) => {
      this.bookingUpdates.push(message);
    });
  }

  ngOnDestroy() {
    if (this.messagesSubscription) {
      this.messagesSubscription.unsubscribe();
    }
  }
}

