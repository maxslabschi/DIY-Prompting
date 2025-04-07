import {Component, inject, OnInit} from '@angular/core';

import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatRadioModule } from '@angular/material/radio';
import { MatCardModule } from '@angular/material/card';
import {KeyValuePipe, NgClass, NgForOf, NgIf} from '@angular/common';
import {DataService} from '../data.service';
import {Airport, BookingRequest, Flight, Passenger} from '../interfaces';


@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrl: './booking.component.css',
  imports: [
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatRadioModule,
    MatCardModule,
    ReactiveFormsModule,
    NgClass
  ]
})
export class BookingComponent implements OnInit {
  nationalities = ['GERMAN', 'BRITISH', 'AMERICAN', 'SPANISH', 'FRENCH', 'ITALIAN'];
  countries = ['GERMANY', 'UK', 'USA', 'SPAIN', 'FRANCE', 'ITALY'];
  dataService: DataService = inject(DataService);
  airports: Airport[] = [];
  flights: Flight[] = [];

  private fb = inject(FormBuilder);
  addressForm = this.fb.group({
    from: [null, Validators.required],
    to: [null, Validators.required],
    email: [null, Validators.required],
    firstName: [null, Validators.required],
    lastName: [null, Validators.required],
    phoneNumber: [null, Validators.required],
    passportNumber: [null, Validators.required],
    city: [null, Validators.required],
    nationality: [null, Validators.required],
    country: [null, Validators.required],
    account: [true, Validators.required]
  });

  ngOnInit() {
    this.dataService.getAllAirports().subscribe(a => {
      this.airports = a
    })

    this.dataService.getAllFlights().subscribe(f => {
      this.flights = f
    })

    this.addressForm.get('account')?.valueChanges.subscribe((hasAccount: boolean | null) => {
      if (hasAccount !== null) {
        console.log(!hasAccount);
        this.updateValidators(!hasAccount);
      }
    });


    this.updateValidators(!this.hasAccount());
  }

  updateValidators(hasAccount: boolean): void {
    if (hasAccount) {
      this.addressForm.get('firstName')?.clearValidators();
      this.addressForm.get('lastName')?.clearValidators();
      this.addressForm.get('phoneNumber')?.clearValidators();
      this.addressForm.get('email')?.clearValidators();
      this.addressForm.get('nationality')?.clearValidators();
      this.addressForm.get('country')?.clearValidators();
      this.addressForm.get('city')?.clearValidators();
    } else {
      this.addressForm.get('firstName')?.setValidators([Validators.required]);
      this.addressForm.get('lastName')?.setValidators([Validators.required]);
      this.addressForm.get('phoneNumber')?.setValidators([Validators.required]);
      this.addressForm.get('email')?.setValidators([Validators.required, Validators.email]);
      this.addressForm.get('nationality')?.setValidators([Validators.required]);
      this.addressForm.get('country')?.setValidators([Validators.required]);
      this.addressForm.get('city')?.setValidators([Validators.required]);
    }

    this.addressForm.get('firstName')?.updateValueAndValidity();
    this.addressForm.get('lastName')?.updateValueAndValidity();
    this.addressForm.get('phoneNumber')?.updateValueAndValidity();
    this.addressForm.get('email')?.updateValueAndValidity();
    this.addressForm.get('nationality')?.updateValueAndValidity();
    this.addressForm.get('country')?.updateValueAndValidity();
    this.addressForm.get('city')?.updateValueAndValidity();
  }

  onSubmit(): void {
    if (this.addressForm.invalid) {
      console.log("Form is invalid, cannot submit.");
      return;
    }

    const formValues = this.addressForm.value;

    if (!this.hasAccount()) {
      if (!formValues.passportNumber) {
        console.log("Passport number is required for existing users.");
        return;
      }

      this.dataService.getPassengerByPassport(formValues.passportNumber).subscribe({
        next: (passenger) => {
          if (passenger) {
            console.log(passenger);
            this.submitBooking(formValues, passenger);
          } else {
            console.error("User not found.");
            alert("No user found with this passport number.");
          }
        },
        error: (err) => {
          console.error("Error fetching user:", err);
          alert("Failed to fetch user by passport number.");
        }
      });
    } else {
      this.submitBooking(formValues);
    }
  }

  private submitBooking(formValues: any, user?: any): void {
    const bookingRequest: BookingRequest = {
      departureCity: formValues.from!,
      destinationCity: formValues.to!,
      passenger: {
        firstName: user ? user.firstName : formValues.firstName!,
        lastName: user ? user.lastName : formValues.lastName!,
        email: user ? user.email : formValues.email!,
        phoneNumber: user ? user.phoneNumber : formValues.phoneNumber!,
        passportNumber: formValues.passportNumber!,
        nationality: user ? user.nationality : formValues.nationality!,
        city: user ? user.city : formValues.city!,
        country: user ? user.country : formValues.country!,
        bookings: user ? user.bookings : []!
      },
      seatNumber: "A22",
      price: 420.187
    };

    this.dataService.createBooking(bookingRequest).subscribe({
      next: (response) => {
        console.log("Booking successful:", response);
        alert("Booking created successfully!");
      },
      error: (err) => {
        console.error("Error creating booking:", err);
        alert("Failed to create booking.");
      }
    });
  }

  hasAccount(): boolean {
    return this.addressForm.get('account')?.value === true;
  }
}
