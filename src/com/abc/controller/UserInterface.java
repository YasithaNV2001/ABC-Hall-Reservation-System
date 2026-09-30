/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.abc.controller;

import com.abc.model.Customer;
import java.util.List;

/**
 *
 * @author User
 */
public interface UserInterface {
    
    
    public  void addCustomer(Customer customer);
   
    public List<Customer> listCustomer();
    public void addBooking();
    public void checkAvailabality();
    
    
    
}
