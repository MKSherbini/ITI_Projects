package de.rieckpil.quickstarts.bbeans;

import de.rieckpil.quickstarts.models.Book;
import jakarta.ejb.Remote;

import java.util.Set;

@Remote
public interface LibrarySessionBeanRemote {
    Book addBook(String bookName);

    void removeBook(int id);

    Set getBooks();
}