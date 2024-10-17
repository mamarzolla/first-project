import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-button-confirm',
  templateUrl: './button-confirm.component.html',
  styleUrl: './button-confirm.component.css'
})
export class ButtonConfirmComponent {
  @Input() text : string;
}
