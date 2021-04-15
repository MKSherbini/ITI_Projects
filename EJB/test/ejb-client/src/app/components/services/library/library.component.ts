import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/models/Book';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-library',
  templateUrl: './library.component.html',
  styleUrls: ['./library.component.css']
})
export class LibraryComponent implements OnInit {

  books: Book[] = [];

  constructor(private _apiService: ApiService) { }

  ngOnInit(): void {
    this._apiService.get("http://localhost:8080/resources/library").subscribe(
      response => {
        this.books = (response.data as Book[])
      }
    )
  }

  deleteBook(index: number, id: number): void {
    this.books[index].isDeleting = true;
    this._apiService.delete(`http://localhost:8080/resources/library/${id}`).subscribe(
      response => {
        this.books.splice(index, 1);
      })
  }
  addBook(name: string) {
    this._apiService.post("http://localhost:8080/resources/library", name).subscribe(
      response => {
        this.books.push(response.data as Book)
      }
    )
  }
}
