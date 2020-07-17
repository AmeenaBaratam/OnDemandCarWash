import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../customer.service';
import { AdminService } from '../../admin/admin.service';
import * as _ from 'lodash';

@Component({
  selector: 'app-book-car-wash',
  templateUrl: './book-car-wash.component.html',
  styleUrls: ['./book-car-wash.component.css']
})
export class BookCarWashComponent implements OnInit {

  emailId: string
  user: any
  categories: Array<string>
  category: string
  brands: Array<string>
  names: Array<string>
  brand: string
  name: string
  response: Array<Object>
  washTypes: Array<Object>
  washType: string
  addOns: Array<Object>
  addOn: string
  carWashDate: Date
  washers: Array<Object>
  washer: string

  constructor(private customerService: CustomerService, private adminService: AdminService) {
  }

  ngOnInit(): void {
    this.emailId = JSON.parse(sessionStorage.getItem('email'))
    this.customerService.getUser(this.emailId).subscribe(data => {
      this.user = data
    })
    let arr1 = []
    let arr2 = []
    let arr3 = []
    let arr4 = []
    let arr5 = []
    let arr7 = []
    this.adminService.getCars().subscribe(data => {
      this.response = Object(data)
      this.response.forEach(function (value) {
        arr1.push(value['category'])
      })
      this.categories = arr1
      this.response.forEach(function (value) {
        arr2.push(value['brand'])
      })
      this.brands = arr2
      this.response.forEach(function (value) {
        arr3.push(value['name'])
      })
      this.names = arr3
    })

    this.adminService.getServicePlans().subscribe(data => {
      this.response = Object(data)
      this.response.forEach(function (value) {
        arr4.push(value['washType'])
      })
      this.washTypes = arr4
    })

    this.adminService.getAddOns().subscribe(data => {
      this.response = Object(data)
      this.response.forEach(function (value) {
        arr5.push(value['name'])
      })
      this.addOns = arr5
    })

    this.adminService.fetchWashers().subscribe(data => {
      this.response = Object(data)
      this.response.forEach(function (value) {
        arr7.push(value['id'])
      })
      this.washers = arr7
    })
  }

  bookCarWash() {
    let obj = {
      'email': this.emailId,
      'address': this.user.address,
      'category': this.category,
      'name': this.name,
      'brand': this.brand,
      'washType': this.washType,
      'addOn': this.addOn,
      'washer': this.washer,
      'carWashDate': this.carWashDate
    }
    this.customerService.bookCarWash(obj).subscribe(data =>
      alert(data)
    )
    window.location.reload();
  }
}