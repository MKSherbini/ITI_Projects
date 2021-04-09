import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Student } from '../models/Student';
import { StudentCrudService } from '../services/student-crud.service';


@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {

  student: Student = new Student();
  constructor(private _activatedRoute: ActivatedRoute, private studentService: StudentCrudService) {
    // alert("constructor");

  }

  ngOnInit(): void {
    // alert("ngOnInit");

    this._activatedRoute.paramMap.subscribe(params => {
      let id: number = +params.get('id');

      this.studentService.getStudent(id).subscribe(response => {
        if (response.Data)
          this.student = response.Data;
        // alert("getDetails");
      });

      // alert("paramMap");
    });

  }

}
