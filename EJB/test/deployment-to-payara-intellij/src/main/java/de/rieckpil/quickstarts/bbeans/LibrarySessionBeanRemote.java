package de.rieckpil.quickstarts.bbeans;

import de.rieckpil.quickstarts.models.Book;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface LibrarySessionBeanRemote {
//    Book addBook(String bookName);
//
//    void removeBook(int id);
//
//    Book findBook(int id);

    List<Book> getBooks();
}