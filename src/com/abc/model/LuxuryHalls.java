/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model;

/**
 *
 * @author User
 */
public class LuxuryHalls extends Hall {

    public LuxuryHalls(int hallNo, String hallId, String acType, String pricePerDay, String hallCap, String hallType, String hallState) {
        super(hallNo, hallId, acType, pricePerDay, hallCap, hallType, hallState);
    }

    

    public LuxuryHalls() {
    }
    
    
    
    
    

    @Override
    public double calcPayment() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public double cancelPayment() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
