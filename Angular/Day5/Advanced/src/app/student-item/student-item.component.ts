import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Student } from '../models/Student';

@Component({
  selector: 'app-student-item',
  templateUrl: './student-item.component.html',
  styleUrls: ['./student-item.component.css']
})
export class StudentItemComponent implements OnInit {
  @Input() item: Student;
  @Output() onSpongeEvent = new EventEmitter<number>();

  constructor() { }

  ngOnInit(): void {
    console.log(this.item);

    this.item = new Student().deserialize(JSON.parse(this.item as any));
  }

}
