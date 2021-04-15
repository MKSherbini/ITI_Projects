import { Component, OnInit } from '@angular/core';
import { UserService } from './../../user/user.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  logged=false;
  constructor(private _userService:UserService) { }

  ngOnInit(): void {
    //this.logged=this._userService.isLogged();
    this._userService.getLoggedStatus().subscribe(status=>{
      this.logged=status;
    });
  }

}
