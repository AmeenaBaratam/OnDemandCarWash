import { Component, OnInit } from '@angular/core';
import { AdminService } from '../admin.service';
import * as _ from 'lodash';

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
    /* let arr = []
    this.adminService.fetchUsers().subscribe(data => {
        this.users = _.filter(Object(data), {'role': 'customer'})
        this.washers = _.filter(Object(data), {'role': 'washer'})
    })
    this.userValue = 'userManagement' */
  }

}
