import { Component, OnInit } from '@angular/core';
import { AdminService } from '../admin.service';
import * as _ from 'lodash';

@Component({
  selector: 'app-promocode',
  templateUrl: './promocode.component.html',
  styleUrls: ['./promocode.component.css'],
  providers: [AdminService]
})
export class PromocodeComponent implements OnInit {

  description: string
  name: string
  validity: string
  discount: string
  promos: Array<Object>
  selectedAction: string

  constructor(private adminService: AdminService) { }

  ngOnInit(): void {
    let arr = []
    this.adminService.getPromoCodes().subscribe(data => {
      _.forEach(data, val => {
        arr.push(val)
      })
    })
    this.promos = arr
  }

  addRow() {
    let obj = {
      'name': this.name,
      'description': this.description,
      'discount': this.discount,
      'validity': this.validity,
      'status': 'Active'
    }
    this.promos.push(obj)
    this.description = ''
    this.discount = null
    this.name = ''
    this.validity = null
  }

  savePromoDetails(): void {
    this.adminService.savePromoCodes(this.promos).subscribe(data => {
      this.promos = Object(data)
    })
  }

}
