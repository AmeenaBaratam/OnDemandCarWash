import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SignupService } from './signup.service'

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  email:string
  password:string
  fullname:string
  phoneno:string
  role:string

  constructor(private router:Router,private service:SignupService) { 
    
  }

  ngOnInit(): void {
  }

  register(): void {
    if (this.email && this.password && this.fullname && this.role && this.phoneno) {
      let obj = {
        'email': this.email,
        'password': this.password,
        'fullname': this.fullname,
        'phoneno': this.phoneno,
        'role': this.role
      }
      this.service.saveUser(obj).subscribe(data => {
        if(data!=null)
          alert('account create successfully')
      })
    }else {
      alert('Please enter all the required details')
    }
  }

  backToLogin(): void {
    this.router.navigate([''])
  }
}
