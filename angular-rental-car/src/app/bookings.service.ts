import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

import { Booking } from './booking';

@Injectable({
  providedIn: 'root'
})
export class BookingsService {

  private handleError<T>(operation = 'operation', result?: T){
    return (error: any): Observable<T> => {
      return of(result as T);
    }
  }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  //get all bookings from the server
  getAllBookings(): Observable<Booking[]> {
    const url : string = `http://localhost:8080/api/bookings`;
    return this.http.get<Booking[]>(url).pipe(
      catchError(this.handleError<Booking[]>('getAllBookings', []))
    );
  }


    //get all bookings by car id will 404 if id not found
  getBookings( id: number ): Observable<Booking[]> {
    const url : string = `http://localhost:8080/api/bookings/${id}`;
     return this.http.get<Booking[]>(url).pipe(
       catchError(this.handleError<Booking[]>(`getBookings id=${id}`, []))
     );
   }

   //delete booking by transaction id
   deleteBooking( id : number ): Observable<Booking> {
     const url : string = `http://localhost:8080/api/delete/${id}`;
     return this.http.delete<Booking>(url, this.httpOptions).pipe(
       catchError(this.handleError<Booking>(`deleteBooking id=${id}`))
     );
   }

   //add booking by car id
   addBooking( booking : Booking ) : Observable<Booking> {
     const url : string = `http://localhost:8080/api/rent`;
     return this.http.put<Booking>(url, booking ,this.httpOptions).pipe(
       catchError(this.handleError<Booking>(`addBooking`))
     );
   }



}
