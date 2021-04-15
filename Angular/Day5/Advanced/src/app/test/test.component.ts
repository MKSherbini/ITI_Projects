import { Component, OnInit } from '@angular/core';
import { Student } from '../models/Student';
import { TextService } from '../services/text.service';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {

  employee: Student = new Student({});

  constructor(private _text: TextService, private _text2: TextService) {
    console.log("Test created");
  }

  ngOnInit(): void {
  }

}
