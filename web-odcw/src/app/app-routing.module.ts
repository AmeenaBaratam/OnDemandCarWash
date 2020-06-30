import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AdminComponent } from './admin/admin.component';
import { SignupComponent } from './signup/signup.component';
import { CustomerComponent } from './customer/customer.component';
import { WasherComponent } from './washer/washer.component';
import { CarManagementComponent } from './admin/car-management/car-management.component';
import { AddonComponent } from './admin/addon/addon.component';
import { PromocodeComponent } from './admin/promocode/promocode.component';
import { ServiceplanComponent } from './admin/serviceplan/serviceplan.component';
import { UsermanagementComponent } from './admin/usermanagement/usermanagement.component';

const routes: Routes = [
  { path: "", component: LoginComponent },
  { path: "signup", component: SignupComponent },
  { path: "washer", component: WasherComponent },
  { path: "customer", component: CustomerComponent },
  { path: "admin", component: AdminComponent,
    children :[
      { path: "car",component:CarManagementComponent },
      { path: "addon", component: AddonComponent },
      { path: "promo", component: PromocodeComponent },
      { path: "service", component: ServiceplanComponent },
      { path: "user", component: UsermanagementComponent }
    ]
}]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
