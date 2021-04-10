import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Student } from '../models/Student';
import { StudentResponse } from '../models/StudentResponse';
import { CrudService } from './crud.service';

@Injectable({
  providedIn: 'root'
})
export class StudentCrudService {

  constructor(private _httpClient: CrudService<StudentResponse>) { }

  getAllStudents(): Observable<StudentResponse> {
    return this._httpClient.getAll("Student/Get");
  }

  addStudent(s: Student): Observable<StudentResponse> {
    return this._httpClient.add("Student/POST", s);
  }

  deleteStudent(id: number): Observable<StudentResponse> {
    return this._httpClient.delete("Student/Delete", id);
  }

  getStudent(id: number): Observable<StudentResponse> {
    return this._httpClient.get("Student/GetByID", id);
  }

}
