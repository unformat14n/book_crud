package com.java_project;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.*;

@Table (name = "Checkout")

public class Checkout {
    @Id @GeneratedValue
    public long checkId;
    // int copyId;
    public LocalDate checkoutDate;
    public LocalDate expCheckin;
    public LocalDate checkinDate;
    // public int userId;
    public String status; 

    Checkout () {}

    public Checkout(LocalDate checkouDate, LocalDate expCheckin, LocalDate checkinDate, String status) {
        this.checkoutDate = checkouDate;
        this.expCheckin = expCheckin;
        this.checkinDate = checkinDate;
        this.status = status;
    } 

    public String toString() {
        return  this.checkoutDate + ", " + 
                this.expCheckin + ", " +
                this.checkinDate + ", " +
                this.status;
    } 
}
