import { Component, OnInit } from '@angular/core';
import { AdminService } from '../admin.service';
import * as _ from 'lodash';

@Component({
  selector: 'app-serviceplan',
  templateUrl: './serviceplan.component.html',
  styleUrls: ['./serviceplan.component.css'],
  providers: [AdminService]
})
export class ServiceplanComponent implements OnInit {

  washPackage: string
  washType: string
  description: string
  services: Array<Object>
  editableCheck: boolean
  selectedAction: string

  constructor(private adminService: AdminService) { }

  ngOnInit(): void {
    let arr = []
    this.adminService.getServicePlans().subscribe(data => {
      _.forEach(data, val => {
        arr.push(val)
      })
    })
    this.services = arr
  }

  addRow() {
    let obj = {
      'washPackage': this.washPackage,
      'washType': this.washType,
      'description': this.description,
      'status': 'Active'
    }
    this.services.push(obj)
    this.washPackage = null
    this.washType = ''
    this.description = ''

  }

  saveServiceDetails(): void {
    this.adminService.saveServicePlans(this.services).subscribe(data => {
      this.services = Object(data)
    })
  }
}
