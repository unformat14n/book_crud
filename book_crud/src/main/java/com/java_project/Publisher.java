package com.java_project;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Publisher")

public class Publisher {
    @Id @GeneratedValue

    public long publisherId;
    public String name;

    Publisher() {}

    public Publisher(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
