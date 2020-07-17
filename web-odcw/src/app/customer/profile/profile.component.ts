import { Component, OnInit } from '@angular/core';
import * as _ from 'lodash';
import { CustomerService } from '../customer.service';
import { WasherService } from '../../washer/washer.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})

export class ProfileComponent implements OnInit {

  emailId: string
  user: any
  role: string
  selectedAction: string

  constructor(private customerService: CustomerService, private washerService:WasherService) { }

  ngOnInit(): void {
    this.emailId = JSON.parse(sessionStorage.getItem('email'))
    this.role = JSON.parse(sessionStorage.getItem('role'))
    if (this.role === 'customer') {
      this.customerService.getUser(this.emailId).subscribe(data => {
        this.user = data
      })
    } else if (this.role === 'washer') {
      this.washerService.getWasher(this.emailId).subscribe(data => {
        this.user = data
      })
    }
  }

  saveDetails() {
    this.role = JSON.parse(sessionStorage.getItem('role'))
    if (this.role === 'customer') {
      this.customerService.updateUser(this.user).subscribe(data =>
        this.user = data)
    }else if(this.role === 'washer'){
      this.washerService.updateWasher(this.user).subscribe(data =>
        this.user = data)
    }
  }
}