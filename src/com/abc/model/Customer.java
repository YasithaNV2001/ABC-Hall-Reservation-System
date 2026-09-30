/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model;


import com.abc.controller.UserControler;



/**
 *
 * @author User
 */
public class Customer{
    
    private int custNo;
    private String customerId;
    private String name;
    private String telephoneNumber;
    private String gmail;
    private User user;
    private String createDate;
    
    public int getCustNo() {
        return custNo;
    }

    public void setCustNo(int custNo) {
        this.custNo = custNo;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Customer(int custNo, String customerId, String name, String telephoneNumber, String gmail, User user, String createDate) {
        this.custNo = custNo;
        this.customerId = customerId;
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.gmail = gmail;
        this.user = user;
        this.createDate = createDate;
    }

    
    
    
    
    
    public Customer(String customerId, String name, String telephoneNumber, String gmail, User user) {
        this.customerId = customerId;
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.gmail = gmail;
        this.user = user;
    }

    public Customer(String customerId, String name, String telephoneNumber, String gmail) {
        this.customerId = customerId;
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.gmail = gmail;
    }
    

    public Customer() {
    
    
    }
 
    
    

   
   

    
    
    
    
    
}
