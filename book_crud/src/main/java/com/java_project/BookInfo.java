package com.java_project;

import java.time.LocalDate;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="BookInfo")
public class BookInfo {
    /*
     * This will be a table with properties similar
     * to the diagram we have.
     */
    @Id @GeneratedValue
    public long bookId;
    public String isbn;
    public String title;
    public int author;
    public int bookEd;
    public String description;
    public enum Genre {
        FANTASY,
        SCIENCE_FICTION,
        DYSTOPIAN,
        ACTION_ADVENTURE,
        MYSTERY,
        HORROR,
        THRILLER_SUSPENSE,
        HISTORICAL_FICTION,
        ROMANCE,
        GRAPHIC_NOVEL,
        CHILDREN,
        NON_FICTION
    };
    public Genre genre;
    LocalDate publishDate;

     // Always add a non-parameter constructor
    BookInfo() {}

     // Add custom constructors
    public BookInfo(String isbn, String title, int author, int bookEd, String description, Genre genre, LocalDate publishDate) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.bookEd = bookEd;
        this.description = description;
        this.genre = genre;
        this.publishDate = publishDate;
    }

    // Full implement
    public String toString() {
        return this.isbn + ", " + this.title + ", " + this.author + ", " + this.bookEd + ", " + this.description + ", " + this.genre + ", " + this.publishDate;
    }
}