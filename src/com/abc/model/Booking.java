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
    
    private int bookingId;
    private Hall hall;
    private Customer customer;
    private User user;
    private String checkIn;
    private String checkOut;
    private String specificDay;
    private int numberOfDay;
    private String bookingType;
    private double payment;

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }
    

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getSpecificDay() {
        return specificDay;
    }

    public void setSpecificDay(String specificDay) {
        this.specificDay = specificDay;
    }

    public int getNumberOfDay() {
        return numberOfDay;
    }

    public void setNumberOfDay(int numberOfDay) {
        this.numberOfDay = numberOfDay;
    }

    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

    public Booking(int bookingId, Hall hall, Customer customer, User user, String checkIn, String checkOut, String specificDay, int numberOfDay, String bookingType, double payment) {
        this.bookingId = bookingId;
        this.hall = hall;
        this.customer = customer;
        this.user = user;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.specificDay = specificDay;
        this.numberOfDay = numberOfDay;
        this.bookingType = bookingType;
        this.payment = payment;
    }

    
    

    public Booking() {
    }
    
    
    
    
    
}
