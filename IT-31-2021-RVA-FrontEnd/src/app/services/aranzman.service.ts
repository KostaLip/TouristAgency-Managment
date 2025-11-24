import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ARANZMAN_BY_AGENCIJA_URL, ARANZMAN_URL } from '../constants';
import { Observable } from 'rxjs';
import { Aranzman } from '../models/aranzman';

@Injectable({
  providedIn: 'root'
})
export class AranzmanService {

  constructor(private httpClient:HttpClient) { }

  public getAllAranzman(): Observable<any> {
    return this.httpClient.get(`${ARANZMAN_URL}`)
  }

  public getAranzmanByTuristickaAgencija(turistickaAgencijaId: number): Observable<any> {
    return this.httpClient.get(`${ARANZMAN_BY_AGENCIJA_URL}/${turistickaAgencijaId}`)
  }

  public addAranzman(aranzman: Aranzman): Observable<any> {
    return this.httpClient.post(`${ARANZMAN_URL}`, aranzman);
  }

  public updateAranzman(aranzman: Aranzman): Observable<any> {
    return this.httpClient.put(`${ARANZMAN_URL}/id/${aranzman.id}`, aranzman);
  }

  public deleteAranzman(aranzmanId: number): Observable<any> {
    return this.httpClient.delete(`${ARANZMAN_URL}/id/${aranzmanId}`, {responseType: 'text'});
  }
}
