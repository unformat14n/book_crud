package com.java_project;

import javax.persistence.*;

@Table(name = "BookCopy")
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    public String acquisitionDate;
    public String status;
    public String acquisitionType;

    @ManyToOne
    @JoinColumn(name = "book_isbn", referencedColumnName = "bookId")
    public String bookISBN;

    public BookCopy() {
    }

    public BookCopy(
            String acquisitionDate,
            String acquisitionType,
            String isbn) {
        this.bookISBN = isbn;
        this.acquisitionDate = acquisitionDate;
        this.acquisitionType = acquisitionType;
        this.status = "AVAILABLE";
    }

    public String toString() {
        return "BookCopy{" +
                "copyId=" + id +
                ", bookISBN=" + bookISBN +
                ", acquisitionDate=" + acquisitionDate +
                ", status='" + status + '\'' +
                ", acquisitionType='" + acquisitionType +
                '}';
    }
}
