import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SignupService {

  constructor(private http:HttpClient) { }

  saveUser(userDetails) {
    return this.http.post('http://localhost:8080/api/user/saveUser', userDetails)
  }
}
