/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.abc.controller;

import com.abc.model.Booking;
import com.abc.model.Customer;
import com.abc.model.Hall;
import java.util.List;

/**
 *
 * @author User
 */
public interface UserInterface {
    
    
    
    public void addBooking(Booking book);
    public  List<Booking> checkAvailabality(String checkIn,String checkOut,Hall hall);
    
    
    
}
