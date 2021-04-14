package de.rieckpil.quickstarts.bbeans;

import java.util.HashSet;
import java.util.Set;

import de.rieckpil.quickstarts.models.Book;
import jakarta.ejb.Stateless;

@Stateless
public class LibrarySessionBean implements LibrarySessionBeanRemote {

    Set<Book> bookShelf;

    public LibrarySessionBean() {
        bookShelf = new HashSet<>();
    }

    public Book addBook(String bookName) {
        var book = new Book(bookShelf.stream().mapToInt(Book::getId).max().orElse(1), bookName);
        bookShelf.add(book);
        return book;
    }

    public void removeBook(int id) {
        bookShelf.removeIf(book -> book.getId() == id);
    }

    public Set<Book> getBooks() {
        return bookShelf;
    }
}