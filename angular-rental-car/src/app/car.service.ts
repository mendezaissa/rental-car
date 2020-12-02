import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

import { Car } from './car';

@Injectable({
  providedIn: 'root'
})
export class CarService {

  private handleError<T>(operation = 'operation', result?: T){
    return (error: any): Observable<T> => {
      return of(result as T);
    }
  }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  //get all cars
  getCars(): Observable<Car[]> {
    return this.http.get<Car[]>("http://localhost:8080/api/cars")
      .pipe(
        catchError(this.handleError<Car[]>('getCars', []))
      );
  }

  //get car by id, will 404 if id not found
  getCar( id: number ): Observable<Car> {
    const url : string = `http://localhost:8080/api/detail/${id}`;
     return this.http.get<Car>(url).pipe(
       catchError(this.handleError<Car>(`getCar id=${id}`))
     );
   }

   //get car by id, will return undefined when id not found
   getCarNo404<Data>(id: number): Observable<Car> {
    const url = `http://localhost:8080/api/detail/${id}`;
    return this.http.get<Car[]>(url)
      .pipe(
        map(cars => cars[0]), // returns a {0|1} element array
        catchError(this.handleError<Car>(`getCar id=${id}`))
      );
  }


}
