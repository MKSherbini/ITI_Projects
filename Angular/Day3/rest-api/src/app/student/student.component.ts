import { Component, OnInit } from '@angular/core';
import { Student, StudentType } from '../models/Student';
import { StudentCrudService } from '../services/student-crud.service';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit {

  student: Student = new Student({});
  students: Student[] = [];
  // emp: Employee = new Employee({ firstName: "sponge", lastName: "bob" });

  constructor(private _studentCrud: StudentCrudService) { }

  ngOnInit(): void {
    this._studentCrud.getAllStudents().subscribe(
      response => {
        console.log("success");
        (response.Data as Student[]).forEach(element => {
          this.students.push(new Student().deserialize(element));
        });
      }, error => {
        alert(error.Message);
      }
    );
  }

  public getFullName(): string {
    return this.student.getFullName();
  }

  public setFirstName(v: string) {
    this.student.FirstName = v;
  }

  public deleteEmployee(index: number, id: number) {
    this.students[index].IsDeleting = true;
    this._studentCrud.deleteStudent(id).subscribe(Response => {
      this.students.splice(index, 1);
      alert(Response.Message);
    }, error => {
      alert(error.response);
    })
    // delete this.employees[e];
  }

  public getCountUnderAge(): number {
    return this.students.filter(e => e.Age < 18).length;
  }

  public addEmployee(s: StudentType) {
    if (this.students.filter(e => e.FirstName == s.FirstName).length > 0) return;
    let std = new Student(s);
    this._studentCrud.addStudent(std).subscribe(Response => {
      if (Response.Data == false) return;
      std.ID = Response.Data;
      // this.students.push(std);
      this.students.unshift(std);
      alert(Response.Message);
    }, error => {
      alert(error.response);
    })
  }

}
