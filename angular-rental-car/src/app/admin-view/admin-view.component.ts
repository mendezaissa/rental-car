import { Component, Input, OnInit } from '@angular/core';
import { BookingsService } from '../bookings.service';
import { Booking } from '../booking';
import { Transaction } from '../transaction';
import { TransactionService } from '../transaction.service';

@Component({
  selector: 'app-admin-view',
  templateUrl: './admin-view.component.html',
  styleUrls: ['./admin-view.component.css']
})
export class AdminViewComponent implements OnInit {

  bookings : Booking[];
  booking : Booking;
  transaction : Transaction;

  constructor(
    private bookingService: BookingsService,
    private transactionService : TransactionService
  ) { }

  ngOnInit(): void {
    this.getAllTransactions();
  }

  getAllTransactions() : void {
    this.bookingService.getAllBookings().subscribe(bookings => this.bookings = bookings);
  }

  deleteTransaction( id: number) : void {
    this.bookingService.deleteBooking(id).subscribe(booking => this.booking = booking);
    window.alert("Transaction with ID: " + id + " has been deleted");
    location.reload();
  }

  updateTransaction( transactionId : number, carId : number , customerId : number , fromDate : any, toDate : any ) : void {
    this.transactionService.updateTransaction( { transactionId, carId, customerId, fromDate, toDate } as Transaction )
    .subscribe( () => location.reload );
  }

}
