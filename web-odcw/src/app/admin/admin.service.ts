import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private httpClient:HttpClient) { }

  getCars () {
    return this.httpClient.get('http://localhost:8080/api/car/all')
  }

  saveCars(obj:Object) {
    return this.httpClient.post('http://localhost:8080/api/car/addOrUpdate', obj)
  }

  getAddOns() {
    return this.httpClient.get('http://localhost:8080/api/addon/all')
  }

  saveAddOns(obj:Object) {
    return this.httpClient.post('http://localhost:8080/api/addon/addOrUpdate', obj)
  }
  
  getPromoCodes() {
    return this.httpClient.get('http://localhost:8080/api/promocode/all')
  }

  savePromoCodes(obj:Object) {
    return this.httpClient.post('http://localhost:8080/api/promocode/addOrUpdate', obj)
  }

  getServicePlans() {
    return this.httpClient.get('http://localhost:8080/api/servicePlan/all')
  }

  saveServicePlans(obj:Object) {
    return this.httpClient.post('http://localhost:8080/api/servicePlan/addOrUpdate', obj)
  }

  fetchCustomers(){
    return this.httpClient.get('http://localhost:8080/api/user/customer/getCustomer')
  }

  fetchWashers(){
    return this.httpClient.get('http://localhost:8080/api/user/washer/getWasher')
  }

  saveUserList(obj:Object) {
    return this.httpClient.post('http://localhost:8080/api/user/customer/addOrUpdate', obj)
  }

  saveWasherList(obj:Object) {
    return this.httpClient.post('http://localhost:8080/api/user/washer/addUpdateWasher', obj)
  }

  generateWasherReport(){
    return this.httpClient.get('http://localhost:8080/api/user/washer/download/washerReport.xlsx')
  }
}
