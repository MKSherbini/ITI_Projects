import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from './../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form:FormGroup;
  constructor(private _formBuilder:FormBuilder
    ,private _userService:UserService
    ,private _router:Router
    ) { }

  ngOnInit(): void {
    this.form=this._formBuilder.group({
      // FirstName:['',[Validators.required,Validators.minLength(6),Validators.maxLength(30)]],

      Email:['',[Validators.required,Validators.minLength(6),Validators.maxLength(30)]],
      Password:['',[Validators.required,Validators.minLength(6),Validators
      .maxLength(20)]]
    })
  }
  isControlValid(name:string)
  {
    return this.form.controls[name].invalid;
  }
  login(){
    
    console.log(this.form.value);
    this._userService.login("5646456646645");
    this._router.navigateByUrl('/task/index');

  }
  addValidations(){
    this.form.controls['Password'].setValidators([Validators.required,Validators.minLength(10)]);
    this.form.controls['Password'].updateValueAndValidity();
  }

}
