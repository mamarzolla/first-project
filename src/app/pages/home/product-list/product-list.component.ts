import { Component, OnInit } from '@angular/core';
import { Ishoe } from '../../../models/shoeList.model';
import { ShoesService } from '../../../services/shoes.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css'
})
export class ProductListComponent {
  shoes : Ishoe[] =[];
  constructor(private  shoeService:ShoesService){}
  ngOnInit():void{
    this.shoeService.getListShoes().subscribe((data)=>{
      this.shoes = data;
    });
  }
}
