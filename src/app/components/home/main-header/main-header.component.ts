import { Component } from '@angular/core';
import { SearchSharedService } from '../../../services/search-shared.service';

@Component({
  selector: 'app-main-header',
  templateUrl: './main-header.component.html',
  styleUrl: './main-header.component.css'
})
export class MainHeaderComponent {

  constructor(private search:SearchSharedService){};
  onType(event: any){
    const inputValue = event.target.value;
    console.log(inputValue);
    this.search.setInputValue(inputValue);
  }

}
