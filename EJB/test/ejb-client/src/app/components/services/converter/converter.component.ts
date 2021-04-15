import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-converter',
  templateUrl: './converter.component.html',
  styleUrls: ['./converter.component.css']
})
export class ConverterComponent implements OnInit {

  methods: String[] = ["dollarToYen", "yenToEuro"];
  currencyOrigin: number = 0;
  currencyResult: number = 0;
  convertMethod: String = "dollarToYen";

  constructor(private _apiService: ApiService) { }

  ngOnInit(): void {
  }

  requestConversion(): void {
    this._apiService.get(`http://localhost:8080/resources/converter/${this.currencyOrigin}?method=${this.convertMethod}`).subscribe(
      response => {
        this.currencyResult = response.data;
      })
  }
}
