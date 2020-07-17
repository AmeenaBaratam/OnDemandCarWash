import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AdminComponent } from './admin/admin.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CarManagementComponent } from './admin/car-management/car-management.component';
import { SignupComponent } from './signup/signup.component';
import { CustomerComponent } from './customer/customer.component';
import { WasherComponent } from './washer/washer.component';
import { AddonComponent } from './admin/addon/addon.component';
import { PromocodeComponent } from './admin/promocode/promocode.component';
import { ServiceplanComponent } from './admin/serviceplan/serviceplan.component';
import { UsermanagementComponent } from './admin/usermanagement/usermanagement.component';
import { ProfileComponent } from './customer/profile/profile.component';
import { BookCarWashComponent } from './customer/book-car-wash/book-car-wash.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from "@angular/material/core";
import { MatInputModule } from '@angular/material/input';
import { MyOrdersComponent } from './my-orders/my-orders.component';
import { PendingRequestsComponent } from './washer/pending-requests/pending-requests.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AdminComponent,
    CarManagementComponent,
    SignupComponent,
    CustomerComponent,
    WasherComponent,
    AddonComponent,
    PromocodeComponent,
    ServiceplanComponent,
    UsermanagementComponent,
    ProfileComponent,
    BookCarWashComponent,
    MyOrdersComponent,
    PendingRequestsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatInputModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }