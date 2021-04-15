import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from './../user.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(private _userService:UserService,private _router:Router) { }

  ngOnInit(): void {
    //Call API
    this._userService.logout();
    this._router.navigateByUrl('/');

  }

}
