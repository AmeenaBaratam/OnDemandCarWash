import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../customer/customer.service';
import { WasherService } from '../washer/washer.service';

@Component({
  selector: 'app-my-orders',
  templateUrl: './my-orders.component.html',
  styleUrls: ['./my-orders.component.css']
})
export class MyOrdersComponent implements OnInit {

  role: string
  email: string
  orders: any

  constructor(private customerService:CustomerService,private washerService:WasherService) { }

  ngOnInit(): void {
    this.email = JSON.parse(sessionStorage.getItem('email'))
    this.role = JSON.parse(sessionStorage.getItem('role'))
    if (this.role === 'customer') {
      this.customerService.getOrdersByCustomer(this.email).subscribe(data => {
        this.orders = data
      })
    } else if (this.role === 'washer') {
      this.washerService.getOrdersByWasher(this.email).subscribe(data => {
        this.orders = data
      })
    }
  }
}
