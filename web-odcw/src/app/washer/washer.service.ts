import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class WasherService {

  constructor(private httpClient:HttpClient) { }

  getWasher(email:string){
    return this.httpClient.get('http://localhost:8080/api/washers/get/'+email)
  }

  updateWasher(obj:any){
    return this.httpClient.post('http://localhost:8080/api/washers/update',obj)
  }

  getOrdersByWasher(email:string){
    return this.httpClient.get('http://localhost:8080/api/orders/washer/'+email)
  }

  getOrdersByStatus(status:string){
    return this.httpClient.get('http://localhost:8080/api/orders/getOrders/'+status)
  }

  saveStatus(obj:any,email:String){
    return this.httpClient.post(`http://localhost:8080/api/orders/updateStatus/${email}`,obj)
  }

  updateComplete(obj:any,email:String){
    console.log(email)
    return this.httpClient.post(`http://localhost:8080/api/orders/complete/${email}`,obj)
  }
}
