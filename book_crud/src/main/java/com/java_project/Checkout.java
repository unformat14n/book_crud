package com.java_project;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Table (name = "Checkout")

public class Checkout {
    @Id
    public String checkId;
    public String checkoutDate;
    public String expCheckin;
    public String checkinDate;
    public String status;
    public String copyID;
    public String clientID;

    @Transient
    public BookCopy copy;

    public Checkout () {}

    public Checkout(String checkoutDate, String clientID, String copyID) {
        this.copyID = copyID;
        this.checkId = generateUUID();
        this.checkoutDate = checkoutDate;
        this.checkinDate = "";
        this.expCheckin = generateExpireDate(this.checkoutDate);
        this.status = "ACTIVE";
        this.clientID = clientID;
    } 

    public String toString() {
        return  this.checkoutDate + ", " + 
                this.expCheckin + ", " +
                this.checkinDate + ", " +
                this.status;
    }

    public String generateExpireDate(String d) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String result = "";

        LocalDate date = LocalDate.parse(d, formatter);
        date.plusDays(60);
        System.out.println("Parsed date: " + date.toString());
        result = date.plusDays(60).toString();

        return result;
    }

    public String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
