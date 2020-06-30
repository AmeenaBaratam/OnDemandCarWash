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
    UsermanagementComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
