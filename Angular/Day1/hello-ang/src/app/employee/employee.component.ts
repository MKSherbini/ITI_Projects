import { Component, OnInit } from '@angular/core';
import { Employee } from './Employee';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {

  emp: Employee = new Employee();
  // emp: Employee = new Employee({ firstName: "sponge", lastName: "bob" });

  constructor() { }

  ngOnInit(): void {
  }

  public getFullName(): string {
    return this.emp.firstName + " " + this.emp.lastName;
  }


  public setFirstName(v: string) {
    this.emp.firstName = v;
  }


}
