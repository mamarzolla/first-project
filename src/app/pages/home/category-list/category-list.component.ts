import { Component, input, OnInit} from '@angular/core';
import { Ishoe } from '../../../models/shoeList.model';
import { ShoesService } from '../../../services/shoes.service';
import { ActivatedRoute } from '@angular/router';
import { SearchSharedService } from '../../../services/search-shared.service';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrl: './category-list.component.css'
})
export class CategoryListComponent {
  shoes:Ishoe[]=[];
  filteredShoes : Ishoe[]=[];
  category : string;


  constructor(private search : SearchSharedService,  private shoeservice : ShoesService, private route:ActivatedRoute){  }

    ngOnInit() : void {
      
      this.shoeservice.getListShoes().subscribe((data)=>{
        this.shoes= data;
        console.log(data);
        this.route.url.subscribe((segments) =>{
          const path = segments.map(segment => segment.path).join('/');
          console.log("Percorso attuale:", path);

          if (path==='new'){
            this.filteredShoes= this.shoes.filter(shoe=> shoe.bestSeller==true)
          }
          else if(path==='best_seller'){
            this.filteredShoes= this.shoes.filter(shoe=> shoe.rating>=4.5)
          }
          else if(path.includes('categories')){
            this.route.params.subscribe((params)=>{
               // const category = params.get('category'); Usando paramMap
              this.category= params['category'];
              this.filteredShoes= this.shoes.filter(shoe => shoe.category.name.toLowerCase()=== this.category.toLowerCase())
            });
          };
      this.search.inputValue$.subscribe(input=> {
        if(input) // necessario altrimenti estromette sopra
       this.filteredShoes = this.shoes.filter(shoe => shoe.name.toLowerCase().includes(input.toLowerCase()));
        });
      });
    });


  }
}
