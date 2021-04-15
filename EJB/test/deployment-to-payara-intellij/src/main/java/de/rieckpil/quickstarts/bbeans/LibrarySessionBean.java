package de.rieckpil.quickstarts.bbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.rieckpil.quickstarts.models.Book;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ejb.Remove;
import jakarta.ejb.Stateful;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.SessionScoped;

//todo ask here

//@ApplicationScoped
//@Stateless
//@Dependent
@Stateful
@SessionScoped
public class LibrarySessionBean
        implements
//        LibrarySessionBeanRemote,
        Serializable {

    String ownerName;
    List<Book> bookShelf;

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public LibrarySessionBean() {
        bookShelf = new ArrayList<>();
    }

    public Book addBook(String bookName) {
        var book = new Book(bookShelf.stream().mapToInt(Book::getId).max().orElse(0) + 1, bookName);
//        var book = new Book(1, bookName);
        bookShelf.add(book);
        return book;

//        bookShelf.add(bookName);
//        return bookName;
    }

    public void removeBook(int id) {
        bookShelf.removeIf(book -> book.getId() == id);
    }

    public Book findBook(int id) {
        var optionalBook = bookShelf.stream().filter(book -> book.getId() == id).findAny();
        return optionalBook.orElse(null);
    }

    public List<Book> getBooks() {
        return bookShelf;
    }

    @Remove
    public void remove() {

    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("LibrarySessionBean.postConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("LibrarySessionBean.preDestroy");
    }
}