import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Car } from '../car';
import { CarService } from '../car.service';
import { BookingsService } from '../bookings.service';
import { Booking } from '../booking';

@Component({
  selector: 'app-rent',
  templateUrl: './rent.component.html',
  styleUrls: ['./rent.component.css']
})
export class RentComponent implements OnInit {

  car : Car;
  bookings : Booking[];
  booking : Booking;

  constructor(
    private route: ActivatedRoute,
    private carService: CarService,
    private bookingsService: BookingsService,
    private router : Router,
  ) { }

  ngOnInit(): void {
    this.getCar();
  }

  //get car to book
  getCar(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.carService.getCar(id)
      .subscribe(car => this.car = car);

    //this.bookings = this.bookingsService.getBookings(id);
    this.bookingsService.getBookings(id).subscribe(bookings => this.bookings = bookings); //retrieves bookings by car id
  }

  //add bookings
  addBooking( carId : number , customerId : number , from : any , to : any) : void {

    this.bookingsService.addBooking( { carId, customerId, from, to } as Booking)
      .subscribe(booking => {
        this.bookings.push(booking);

        window.alert("Your booking has been completed");

        this.router.navigate(['/confirmation', carId]);
      });
  }

  

}
