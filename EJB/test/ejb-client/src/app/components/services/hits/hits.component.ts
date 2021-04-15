import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-hits',
  templateUrl: './hits.component.html',
  styleUrls: ['./hits.component.css']
})
export class HitsComponent implements OnInit {

  numberOfHits: number = 0;

  constructor(private _apiService: ApiService) { }

  ngOnInit(): void {
    this._apiService.get("http://localhost:8080/resources/hits").subscribe(
      response => {
        this.numberOfHits = response.data;
      })
  }

  hitIt(): void {
    this._apiService.post("http://localhost:8080/resources/hits", null).subscribe(
      response => {
        this.numberOfHits++;
      })
  }

}
