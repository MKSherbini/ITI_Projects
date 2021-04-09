import { Component, OnInit } from '@angular/core';
import { Employee } from './Employee';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {

  employee: Employee = new Employee({});
  employees: Employee[] = [new Employee({}), new Employee({})];
  // emp: Employee = new Employee({ firstName: "sponge", lastName: "bob" });

  constructor() { }

  ngOnInit(): void {
  }

  public getFullName(): string {
    return this.employee.getFullName();
  }

  public setFirstName(v: string) {
    this.employee.firstName = v;
  }

  public deleteEmployee(e: number) {
    this.employees.splice(e, 1);
    // delete this.employees[e];
  }

  public getCountUnderAge(): number {
    return this.employees.filter(e => e.age < 18).length;
  }

  public addEmployee(e: any) {
    if (this.employees.filter(ee => ee.firstName == e.firstName).length > 0) return;
    this.employees.push(new Employee(e));
    // this.employees.unshift(new Employee(e));
  }

}
