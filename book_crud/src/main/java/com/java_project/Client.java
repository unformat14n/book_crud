package com.java_project;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Client")
public class Client {
    @Id @GeneratedValue
    public long clientId;
    public String clientName;
    public String clientLastN;
    public String phoneNumber;
    public String email;

    Client() {}

    public Client(String clientName, String clientLastN, String phoneNumber, String email) {
        this.clientName = clientName;
        this.clientLastN = clientLastN;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}