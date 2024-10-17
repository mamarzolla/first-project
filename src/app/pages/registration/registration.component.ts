import { Component, Input } from '@angular/core';
import { NgForm } from '@angular/forms';
import { InewUser, NewUser, Profile } from '../../models/newUser.model';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.css'
})
export class RegistrationComponent {

  constructor(private userSer:UserService){}

  profile: Profile={
    username:'',
    password:''
  }
  @Input() profileForm!: NgForm;
  onSubsribe( form:NgForm){
    console.log(form)
    console.log(this.profile)
    this.userSer.logIn(this.profile).subscribe(response=>{
      console.log('risposta dal server', response);

    })
  }
}
