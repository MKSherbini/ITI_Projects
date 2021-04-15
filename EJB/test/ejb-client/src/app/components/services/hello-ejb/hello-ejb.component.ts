import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-hello-ejb',
  templateUrl: './hello-ejb.component.html',
  styleUrls: ['./hello-ejb.component.css']
})
export class HelloEjbComponent implements OnInit {

  serverMessage: string = "";

  constructor(private _apiService: ApiService) { }

  ngOnInit(): void {
    this._apiService.get("http://localhost:8080/resources/helloEjb").subscribe(
      response => {
        this.serverMessage = response.data;
      },
      error => {
        this.serverMessage = "Error"
      })
  }
}
