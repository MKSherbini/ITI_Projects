import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee/Employee';
import { TextService } from '../text.service';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {

  employee: Employee = new Employee({});

  constructor(private _text: TextService, private _text2: TextService) {
    console.log("Test created");
  }

  ngOnInit(): void {
  }

}
