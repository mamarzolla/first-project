import { Component, Input } from '@angular/core';
import { Ishoe } from '../../../models/shoeList.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-shoe',
  templateUrl: './shoe.component.html',
  styleUrl: './shoe.component.css'
})
export class ShoeComponent {
@Input() shoe : Ishoe;

constructor(private router:Router){}

switchOnProductId(){
  this.router.navigate(['/scarpa/'+this.shoe.id])
}
}
