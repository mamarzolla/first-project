import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ishoe } from '../models/shoeList.model';

@Injectable({
  providedIn: 'root'
})
export class ShoesService {
  private url = 'http://localhost:8080/shoe'
 
  constructor( private http: HttpClient) { }
 
  getListShoes(): Observable<Ishoe[]>{
    return this.http.get<Ishoe[]>(`${this.url}/list`);
  }

  getDetailShoe(id : number):Observable<Ishoe>{
    return this.http.get<Ishoe>(`${this.url}/${id}`);
  }

}
