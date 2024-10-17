import { Component, Input } from '@angular/core';
import { ControlContainer, NgForm } from '@angular/forms';
import { Profile } from '../../../models/newUser.model';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css',
  viewProviders:[{provide: ControlContainer, useExisting: NgForm}]
})
export class ProfileComponent {

@Input () profile:Profile={
                      username:'',
                        password:''
                    };
}
