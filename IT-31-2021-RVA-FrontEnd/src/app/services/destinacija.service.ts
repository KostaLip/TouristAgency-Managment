import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DESTINACIJA_URL, HOTEL_URL } from '../constants';
import { Destinacija } from '../models/destinacija';

@Injectable({
  providedIn: 'root'
})
export class DestinacijaService {

  constructor(private httpClient:HttpClient) { }

  public getAllDestinacija(): Observable<any> {
    return this.httpClient.get(`${DESTINACIJA_URL}`)
  }

  public addDestinacija(destinacija: Destinacija): Observable<any> {
    return this.httpClient.post(`${DESTINACIJA_URL}`, destinacija);
  }

  public updateDestinacija(destinacija: Destinacija): Observable<any> {
    return this.httpClient.put(`${DESTINACIJA_URL}/id/${destinacija.id}`, destinacija);
  }

  public deleteDestinacija(destinacijaId: number): Observable<any> {
    return this.httpClient.delete(`${DESTINACIJA_URL}/id/${destinacijaId}`, {responseType: 'text'});
  }

}
