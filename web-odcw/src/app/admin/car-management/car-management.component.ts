import { Component, OnInit } from '@angular/core';
import { AdminService } from '../admin.service';
import * as _ from 'lodash';

@Component({
  selector: 'app-car-management',
  templateUrl: './car-management.component.html',
  styleUrls: ['./car-management.component.css'],
  providers: [AdminService]
})
export class CarManagementComponent implements OnInit {

  category:string
  name:string
  brand:string
  categories:Array<string>
  cars:Array<Object>
  editableCheck:boolean
  selectedAction:string

  constructor(private adminService:AdminService) { 
    this.categories = ['SUV', 'Sedan', 'Hatchback']
    this.category = 'SUV'
  }

  ngOnInit(): void {
    let arr = []
    this.adminService.getCars().subscribe(data => {
        _.forEach(data, val => {
            arr.push(val)
        })
    })
    this.cars = arr
  }

  addRow(): void{
    let obj = {
      'category': this.category,
      'brand': this.brand,
      'name': this.name,
      'status': 'Active'
    }
    this.cars.push(obj)
    this.category = ''
    this.brand = ''
    this.name = ''
  }

  saveCarDetails(): void{
    this.adminService.saveCars(this.cars).subscribe(data => {
      this.cars = Object(data)
    })
  }
}
