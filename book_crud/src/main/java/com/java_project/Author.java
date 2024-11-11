package com.java_project;

import java.time.LocalDate;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Author")

public class Author {
    @Id @GeneratedValue

    public long authorId;
    public String name;
    public String surname;
    public LocalDate dob;
    public String description; // or bibliography?

    Author() {}

    public Author(String name, String surname, LocalDate dob, String description){
        this.name = name;
        this.surname = surname;
        this.dob = dob;
        this.description = description;
    }

    public String toString() {
        return this.name + ", " + this.surname + ", " + this.dob + ", " + this.description;
    }
}
