import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchSharedService {

  private inputValueSubject = new BehaviorSubject<string>(''); // Valore iniziale vuoto
  inputValue$ = this.inputValueSubject.asObservable(); // Osservabile

  setInputValue(value: string) {
    this.inputValueSubject.next(value); // Aggiorna il valore e notifica gli abbonati
  }
}
