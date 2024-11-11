package com.java_project;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Client")

public class Client {
    @Id @GeneratedValue
    public long clientId;
    public String name;
    public String surname;
    public String phone;
    public String email;

    Client() {}

    public Client(String name, String surname, String phone, String email) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
    }

    public String toString(){
        return this.name + ", " + this.surname + ", " + this.phone + ", " + this.email;
    }
}

