import { Component, OnInit } from '@angular/core';
import { AdminService } from '../admin.service';
import * as _ from 'lodash';
import {saveAs} from 'file-saver'

@Component({
  selector: 'app-usermanagement',
  templateUrl: './usermanagement.component.html',
  styleUrls: ['./usermanagement.component.css'],
  providers: [AdminService]
})
export class UsermanagementComponent implements OnInit {

  users: Array<Object>
  washers: Array<Object>
  editableCheck: boolean
  selectedAction: string
  userValue: string

  constructor(private adminService: AdminService) { }

  ngOnInit(): void {
    let arr1 = []
    let arr2 = []
    this.adminService.fetchCustomers().subscribe(data => {
      _.forEach(data, val => {
        arr1.push(val)
      })
      this.users = arr1
    })
    this.adminService.fetchWashers().subscribe(data => {
      _.forEach(data, val => {
        arr2.push(val)
      })
      this.washers = arr2
    })
  }
  invalidate(index: number) {
    if (this.users[index]['status'] === 'invalid') this.users[index]['status'] = 'valid'
    else this.users[index]['status'] = 'invalid'
  }

  invalidateWasher(index: number) {
    if (this.washers[index]['status'] === 'invalid') this.washers[index]['status'] = 'valid'
    else this.washers[index]['status'] = 'invalid'
  }

  saveUserData() {
    this.adminService.saveUserList(this.users).subscribe(data => {
      this.users = Object(data)
    })
  }

  saveWasherData() {
    this.adminService.saveWasherList(this.washers).subscribe(data => {
      this.washers = Object(data)
    })
  }

  /* generateWasherReport() {
    this.adminService.generateWasherReport().subscribe((data: Blob) => {
      const blobdownload = new Blob([data], { type: "application/xml.ms-excel;charset=utf-8" });
      saveAs(blobdownload, 'testdata.xls');
    })
  } */

}
