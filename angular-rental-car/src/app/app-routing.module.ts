import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CarsComponent } from './cars/cars.component';
import { CarDetailComponent } from './car-detail/car-detail.component';
import { HowToRentComponent } from './how-to-rent/how-to-rent.component';
import { RentComponent } from './rent/rent.component';
import { ConfirmationComponent } from './confirmation/confirmation.component';
import { AdminViewComponent } from './admin-view/admin-view.component';
import { HomeComponent } from './home/home.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { CustomerLoginComponent } from './customer-login/customer-login.component';

const routes: Routes = [
  { path: '', component: HomeComponent, pathMatch: 'full'},
  { path: 'home', component: HomeComponent},
  { path: 'cars', component: CarsComponent },
  { path: 'detail/:id', component: CarDetailComponent },
  { path: 'rent', component: HowToRentComponent},
  { path: 'book/:id', component: RentComponent},
  { path: 'confirmation/:id', component: ConfirmationComponent},
  { path: 'admin', component: AdminViewComponent },
  { path: 'admin-login', component: AdminLoginComponent},
  { path: 'customer-login', component: CustomerLoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }