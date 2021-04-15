import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {


  loggedStatus=new BehaviorSubject<boolean>(this.isLogged());
  constructor() { }

  login(token:string){
    localStorage.setItem('token',token);
    this.loggedStatus.next(true);
  }

  
  logout(){
    localStorage.removeItem('token');
    this.loggedStatus.next(false);
  }

  getToken():string{
    return localStorage.getItem('token');
  }

  getLoggedStatus()
  {
    return this.loggedStatus.asObservable();
  }

  isLogged():boolean{
    let logged=(this.getToken()!=null && this.getToken()!="");
    //alert(logged);
    return logged;
    if(this.getToken()!=null && this.getToken()!="")
    {
      return true;
    }
    else{
      return false;
    }
  }

}
