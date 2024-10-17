import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductListComponent } from './pages/home/product-list/product-list.component';
import { HomepageComponentComponent } from './pages/home/homepage-component/homepage-component.component';
import { CategoryListComponent } from './pages/home/category-list/category-list.component';
import { ProductDetailsComponent } from './pages/product-details/product-details.component';
import { RegistrationComponent } from './pages/registration/registration.component';


const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  {path:'home', component:HomepageComponentComponent,
    children:[
      { path:'', component:ProductListComponent},
      {path:'categories/:category', component:CategoryListComponent},
      {path:'new', component:CategoryListComponent},
      {path:'best_seller', component:CategoryListComponent}
    ]
  },
  {path:'scarpa/:id', component:ProductDetailsComponent},
  {path:'newUser', component: RegistrationComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
