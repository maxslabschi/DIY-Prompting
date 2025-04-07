import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {
  private socket: WebSocket;
  private messageSubject = new Subject<string>();

  constructor() {

    this.socket = new WebSocket('ws://localhost:8080/ws/bookings');


    this.socket.onmessage = (event) => {
      this.messageSubject.next(event.data);
    };


    this.socket.onopen = () => {
      console.log('Connected to WebSocket');
    };


    this.socket.onclose = () => {
      console.log('Disconnected from WebSocket');
    };
  }


  getMessages() {
    return this.messageSubject.asObservable();
  }

  closeConnection() {
    this.socket.close();
  }
}
