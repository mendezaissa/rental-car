import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

import { Transaction } from './transaction';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  private handleError<T>(operation = 'operation', result?: T){
    return (error: any): Observable<T> => {
      return of(result as T);
    }
  }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  //update transaction
  updateTransaction( transaction : Transaction ) : Observable<any> {
  const url : string = `http://localhost:8080/api/update`;
  return this.http.put(url, transaction, this.httpOptions).pipe(
  catchError(this.handleError<any>('updateTransaction'))       
  );
  }


}
