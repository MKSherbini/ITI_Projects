import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Student } from '../models/Student';
import { StudentResponse } from '../models/StudentResponse';

@Injectable({
  providedIn: 'root'
})
export class CrudService<T> {

  constructor(private _httpClient: HttpClient) { }

  getAll(url: String): Observable<T> {
    return this._httpClient.get<T>(`${environment.empployeeApi}/${url}`);
  }

  add(url: String, s: Student): Observable<T> {
    return this._httpClient.post<T>(`${environment.empployeeApi}/${url}`, s);
  }

  delete(url: String, id: number): Observable<T> {
    return this._httpClient.delete<T>(`${environment.empployeeApi}/${url}?id=` + id);
  }

  get(url: String, id: number): Observable<T> {
    return this._httpClient.get<T>(`${environment.empployeeApi}/${url}?id=` + id);
  }

}
