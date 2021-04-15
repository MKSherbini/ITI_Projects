import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiResponse } from '../models/api-response';
import { UserService } from './../components/user/user.service';

@Injectable({
  providedIn: 'root'
})
export class ApiService {


  constructor(private _httpClient:HttpClient,private _userService:UserService) { }
  createHeader(){
    let headers=new HttpHeaders().set('token',this._userService.getToken());
  }
  get(url:string):Observable<any>{
    return this._httpClient.get(url);
   }
  
 
   delete(url:string):Observable<ApiResponse>{
     return this._httpClient.delete<ApiResponse>(url);
    }
    
   post(url:string,obj:any):Observable<ApiResponse>{
     return this._httpClient.post<ApiResponse>(url,obj);
    }
    
   put(url:string,obj:any):Observable<any>{
     return this._httpClient.put(url,obj);
    }
}
