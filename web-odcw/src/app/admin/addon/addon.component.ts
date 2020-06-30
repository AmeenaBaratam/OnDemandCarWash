import { Component, OnInit } from '@angular/core';
import { AdminService } from '../admin.service';
import * as _ from 'lodash';

@Component({
  selector: 'app-addon',
  templateUrl: './addon.component.html',
  styleUrls: ['./addon.component.css'],
  providers: [AdminService]
})
export class AddonComponent implements OnInit {

  name: string
  cost: string
  addOns: Array<Object>
  editableCheck: boolean
  selectedAction: string

  constructor(private adminService:AdminService) { }

  ngOnInit(): void {
    let arr = []
    this.adminService.getAddOns().subscribe(data => {
        _.forEach(data, val => {
            arr.push(val)
        })
    })
    this.addOns = arr
  }

  addRow() {
    let obj = {
        'name': this.name,
        'cost': this.cost,
        'status': 'Active'
    }
    this.addOns.push(obj)
    this.cost = null
    this.name = ''
}

  saveAddOnDetails(): void {
    this.adminService.saveAddOns(this.addOns).subscribe(data => {
        this.addOns = Object(data)
    })
  }
}
