import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http:HttpClient) { }

  fetchUserByEmail(userDetails){
    return this.http.post('http://localhost:8080/api/user/login', userDetails)
  }

}
