import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private httpClient:HttpClient) { }

  getUser(email:string){
    return this.httpClient.get('http://localhost:8080/api/customers/get/'+email)
  }

  updateUser(obj:Object){
    return this.httpClient.post('http://localhost:8080/api/customers/update',obj)
  }
  
  bookCarWash(obj:Object){
    return this.httpClient.post('http://localhost:8080/api/orders/add',obj)
  }

  getOrdersByCustomer(email:string){
    return this.httpClient.get('http://localhost:8080/api/orders/customer/'+email)
  }
}