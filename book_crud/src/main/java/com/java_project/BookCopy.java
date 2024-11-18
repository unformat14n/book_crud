package com.java_project;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.UUID;

// Norm-based entity class
@Table(name = "BookCopy")
public class BookCopy {

    /*
     * This will be a table with properties similar
     * to the diagram we have.
     */
    @Id
    public String copyId; // Primary key

    public String acquisitionDate;
    public String status;
    public String acquisitionType;
    public String bookISBN; // Foreign key

    @Transient
    public BookInfo book; // Non-persistent field for linking BookInfo

    // Default constructor
    public BookCopy() {
    }

    // Constructor for creating new instances
    public BookCopy(String acquisitionDate, String acquisitionType, String isbn) {
        this.copyId = generateUUID();
        this.bookISBN = isbn;
        this.acquisitionDate = acquisitionDate;
        this.acquisitionType = acquisitionType;
        this.status = "AVAILABLE";
    }

    @Override
    public String toString() {
        return "BookCopy{" +
                "id=" + copyId +
                ", bookISBN='" + bookISBN + '\'' +
                ", acquisitionDate='" + acquisitionDate + '\'' +
                ", status='" + status + '\'' +
                ", acquisitionType='" + acquisitionType + '\'' +
                '}';
    }

    public String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
