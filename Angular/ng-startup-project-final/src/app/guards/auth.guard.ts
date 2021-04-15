import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router, CanActivateChild } from '@angular/router';
import { Observable } from 'rxjs';
import { UserService } from './../components/user/user.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate,CanActivateChild {
  constructor(private _userService:UserService
    ,private _router:Router
    ){}
  canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean{
   return this.canActivate();
  }
  canActivate():boolean{
  let logged= this._userService.isLogged();
if(!logged)
this._router.navigateByUrl('/user/login');
  return logged;

  }
  
  
}
