import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainHeaderComponent } from './components/home/main-header/main-header.component';
import { MainFooterComponent } from './components/home/main-footer/main-footer.component';
import { MainNavFiltersComponent } from './components/home/main-nav-filters/main-nav-filters.component';
import { ShoeComponent } from './components/commonParts/shoe/shoe.component';
import { ProductListComponent } from './pages/home/product-list/product-list.component';
import { HttpClientModule } from '@angular/common/http';
import { HomepageComponentComponent } from './pages/home/homepage-component/homepage-component.component';
import { ButtonConfirmComponent } from './components/commonParts/button-confirm/button-confirm.component';
import { CategoryListComponent } from './pages/home/category-list/category-list.component';
import { ProductDetailsComponent } from './pages/product-details/product-details.component';
import { RegistrationComponent } from './pages/registration/registration.component';
import { FormsModule } from '@angular/forms';
import { ProfileComponent } from './components/user/profile/profile.component';
import { AddressesComponent } from './components/user/addresses/addresses.component';
import { LoginComponent } from './components/user/login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    MainHeaderComponent,
    MainFooterComponent,
    MainNavFiltersComponent,
    ShoeComponent,
    ProductListComponent,
    HomepageComponentComponent,
    ButtonConfirmComponent,
    CategoryListComponent,
    ProductDetailsComponent,
    RegistrationComponent,
    ProfileComponent,
    AddressesComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
