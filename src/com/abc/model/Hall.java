/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model;

/**
 *
 * @author User
 */
public abstract class Hall {
   
    
    private int hallNo;
    private String hallId;
    private String acType;
    private String pricePerDay;
    private String hallCap;
    private String hallType;
    private String hallState;
    private String hallInDate;
    private String hallOutDate;

    public String getHallState() {
        return hallState;
    }

    public void setHallState(String hallState) {
        this.hallState = hallState;
    }

    public int getHallNo() {
        return hallNo;
    }

    public void setHallNo(int hallNo) {
        this.hallNo = hallNo;
    }

    public String getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(String pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getHallCap() {
        return hallCap;
    }

    public void setHallCap(String hallCap) {
        this.hallCap = hallCap;
    }

    public String getHallType() {
        return hallType;
    }

    public void setHallType(String hallType) {
        this.hallType = hallType;
    }
    public String getHallId() {
        return hallId;
    }

    public void setHallId(String hallId) {
        this.hallId = hallId;
    }

    public String getAcType() {
        return acType;
    }

    public void setAcType(String acType) {
        this.acType = acType;
    }

    public String getHallInDate() {
        return hallInDate;
    }

    public void setHallInDate(String hallInDate) {
        this.hallInDate = hallInDate;
    }

    public String getHallOutDate() {
        return hallOutDate;
    }

    public void setHallOutDate(String hallOutDate) {
        this.hallOutDate = hallOutDate;
    }

    
    
    

    public Hall(int hallNo, String hallId, String acType, String pricePerDay, String hallCap, String hallType, String hallState) {
        this.hallNo = hallNo;
        this.hallId = hallId;
        this.acType = acType;
        this.pricePerDay = pricePerDay;
        this.hallCap = hallCap;
        this.hallType = hallType;
        this.hallState = hallState;
    }

   

    

   
    public Hall() {
    }
    
    
    
    
    
    public abstract double calcPayment();
    public abstract double cancelPayment();
    
}
