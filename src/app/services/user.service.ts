import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NewUser, Profile } from '../models/newUser.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor( private http:HttpClient) { }

  private url = 'http://localhost:8080/user'

  addNewUser(data:NewUser){
    return this.http.post(`${this.url}/create`,data);
  }
  logIn(data:Profile){
    return this.http.put(`${this.url}/login`,data);
  }
  
}
