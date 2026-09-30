/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model;

import com.abc.controller.UserControler;
import java.util.Date;

/**
 *
 * @author User
 */
public class Booking {
    
    private String bbokingId;
    private Hall hall;
    private Customer customer;
    private UserControler user;
    private Date checkIn;
    private Date checkOut;
    private Date specificDay;
    private int numberOfDay;

    public String getBbokingId() {
        return bbokingId;
    }

    public void setBbokingId(String bbokingId) {
        this.bbokingId = bbokingId;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public UserControler getUser() {
        return user;
    }

    public void setUser(UserControler user) {
        this.user = user;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public Date getSpecificDay() {
        return specificDay;
    }

    public void setSpecificDay(Date specificDay) {
        this.specificDay = specificDay;
    }

    public int getNumberOfDay() {
        return numberOfDay;
    }

    public void setNumberOfDay(int numberOfDay) {
        this.numberOfDay = numberOfDay;
    }

    public Booking(String bbokingId, Hall hall, Customer customer, UserControler user, Date checkIn, Date checkOut, Date specificDay, int numberOfDay) {
        this.bbokingId = bbokingId;
        this.hall = hall;
        this.customer = customer;
        this.user = user;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.specificDay = specificDay;
        this.numberOfDay = numberOfDay;
    }

    public Booking() {
    }
    
    
    
    
    
}
