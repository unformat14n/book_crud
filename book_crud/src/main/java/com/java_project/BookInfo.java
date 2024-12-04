package com.java_project;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "BookInfo")
public class BookInfo {

    /*
     * This will be a table with properties similar
     * to the diagram we have.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer bookId;

    public String isbn;
    public String title;
    public String author;
    public int bookEd;
    public String description;
    public String genre;
    public String publishDate;
    public String publisher;

    // Always add a non-parameter constructor
    public BookInfo() {
    }

    // Add custom constructors
    public BookInfo(
            String isbn,
            String title,
            String author,
            int bookEd,
            String description,
            String genre,
            String publishDate,
            String publisher) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.bookEd = bookEd;
        this.publisher = publisher;
        this.description = description;
        this.genre = genre;
        this.publishDate = publishDate;
    }

    // Full implement
    public String toString() {
        return this.isbn +
                ", " +
                this.title +
                ", " +
                this.author +
                ", " +
                this.bookEd +
                ", " +
                this.description +
                ", " +
                this.genre +
                ", " +
                this.publishDate;
    }
    
    public boolean validISBN (String isbn) {
        if (isbn.length() != 13 && isbn.length() != 10) { 
            return false; };

        int sum = 0;

        for (int i = 0 ; i < isbn.length(); i++) {
            try {
                int num = isbn.charAt(i) - '0';

                if (isbn.length() == 13) {
                    if (i % 2 == 0) {
                        sum += num;
                    } else {
                        sum += num * 3;
                    }
                } else {
                    sum += num * (10 - i);
                }
            }   
            catch (NumberFormatException e) {
                return false;
            }
        }
        return isbn.length() == 13 ? sum % 10 == 0 : sum % 11 == 0;
    }
}
