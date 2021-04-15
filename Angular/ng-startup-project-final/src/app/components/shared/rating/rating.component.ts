import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-rating',
  templateUrl: './rating.component.html',
  styleUrls: ['./rating.component.css']
})
export class RatingComponent implements OnInit {

  ratingValues=[1,2,3,4,5];
 @Input() stars:number=2;
 @Output() change=new EventEmitter<number>()

  constructor() { }

  ngOnInit(): void {
  }
  changeRating(newStars:number)
  {
    this.stars=newStars;
    this.change.emit(newStars);
    
  }

}
