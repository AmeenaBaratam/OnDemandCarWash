import { Component, OnInit } from '@angular/core';
import { LoginService } from './login.service';
import { Router } from '@angular/router';
import * as _ from 'lodash';
import { SignupComponent } from '../signup/signup.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username:string
  password:string

  constructor(private service:LoginService, private router:Router) {}

  ngOnInit(): void {
  }

  doLogin(): void{
    if (this.username && this.password) {
      let obj = {
        'email': this.username,
        'password': this.password
      }
      this.service.fetchUserByEmail(obj).subscribe(data => {
        let url = data['role']
        this.router.navigate([url])
      })
    }else {
      alert('Please enter all the required details')
    }
  }

  register(): void{
    this.router.navigate(['signup'])
  }
}
