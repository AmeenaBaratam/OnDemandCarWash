import { Component, OnInit } from '@angular/core';
import { WasherService } from '../washer.service'; 
import * as _ from 'lodash';

@Component({
  selector: 'app-pending-requests',
  templateUrl: './pending-requests.component.html',
  styleUrls: ['./pending-requests.component.css']
})
export class PendingRequestsComponent implements OnInit {
  
  statusList1:Array<string>
  status1:string
  statusList2:Array<string>
  status2:string
  orders:any
  selectedAction:string
  requests:any

  constructor(private washerService:WasherService) {
    this.statusList1 = ['Pending', 'Assigned']
    this.status1 = 'Pending'
    this.statusList2 = ['Assigned','Completed']
    this.status2 = 'Assigned'
  }

  ngOnInit(): void {
    let arr = []
    this.washerService.getOrdersByStatus(this.status1).subscribe(data => {
        _.forEach(data, val => {
            arr.push(val)
        })
    })
    this.requests = arr

    let arr1 = []
    this.washerService.getOrdersByStatus(this.status2).subscribe(data => {
        _.forEach(data, val => {
            arr1.push(val)
        })
    })
    this.orders = arr1
  }

  updateToAssigned(){
    let email = JSON.parse(sessionStorage.getItem('email'))
    this.washerService.saveStatus(this.requests,email).subscribe(data => {
      this.requests = Object(data)
    })
  }

  updateToComplete(){
    let email = JSON.parse(sessionStorage.getItem('email'))
    console.log(email)
    console.log(this.orders)
    this.washerService.updateComplete(this.orders,email).subscribe(data => {
      this.orders = Object(data)
    })
  }
}
