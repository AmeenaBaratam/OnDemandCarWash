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
import { ProfileComponent } from './customer/profile/profile.component';
import { BookCarWashComponent } from './customer/book-car-wash/book-car-wash.component';
import { MyOrdersComponent } from './my-orders/my-orders.component';
import { PendingRequestsComponent } from './washer/pending-requests/pending-requests.component';

const routes: Routes = [
  { path: "login", component: LoginComponent },
  { path: "", redirectTo: "login", pathMatch: "full" },
  { path: "signup", component: SignupComponent },
  {
    path: "washer", component: WasherComponent,
    children: [
      { path: "", component: ProfileComponent },
      { path: "profile", component: ProfileComponent },
      { path: "orders", component: MyOrdersComponent },
      { path:"requests", component:PendingRequestsComponent}
    ]
  },
  {
    path: "customer", component: CustomerComponent,
    children: [
      { path: "", component: ProfileComponent },
      { path: "profile", component: ProfileComponent },
      { path: "booking", component: BookCarWashComponent },
      { path: "orders", component: MyOrdersComponent }
    ]
  },
  {
    path: "admin", component: AdminComponent,
    children: [
      { path: "", component: UsermanagementComponent },
      { path: "car", component: CarManagementComponent },
      { path: "addon", component: AddonComponent },
      { path: "promo", component: PromocodeComponent },
      { path: "service", component: ServiceplanComponent },
      { path: "user", component: UsermanagementComponent }
    ]
  }
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
