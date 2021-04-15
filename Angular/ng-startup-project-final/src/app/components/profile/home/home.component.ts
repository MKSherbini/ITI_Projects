import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor() { }
  instRate=3;
  courseRate=3;
  ngOnInit(): void {
  }

  onIntRatingChanged(rate:number)
  {
    this.instRate=rate;
    //Call API chnage rate
  }
  
  onCourseChanged(rate:number)
  {
    this.courseRate=rate;
    //Call API chnage rate
  }

}
