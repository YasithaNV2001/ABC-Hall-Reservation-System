/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.controller;

import com.abc.database.hallDb;
import com.abc.model.BenquetHalls;
import com.abc.model.Customer;
import com.abc.model.Hall;
import com.abc.model.LuxuryHalls;
import com.abc.model.StanderdHalls;
import com.abc.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class CustomerControler implements UserInterface {

    @Override
    public void addCustomer(Customer customer) {
        
        try {
            Connection con = hallDb.getConnection();
            String sql = "INSERT INTO customers(customer_nic,cust_name,telephone_number,gmail,user_key,customerCreateDate)VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, customer.getCustomerId());
            ps.setString(2, customer.getName());
            ps.setString(3, customer.getTelephoneNumber());
            ps.setString(4, customer.getGmail());
            ps.setObject(5, customer.getUser());
            ps.setString(6, customer.getCreateDate());

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Successfully Submited");

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error while Submiting");

        }
        
         }

    @Override
    public List<Customer> listCustomer(){
        
        
        List<Customer> list = new ArrayList<>();
    

        try {
            Connection con = hallDb.getConnection();
            String sql = "SELECT * FROM customers";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Customer cust = new Customer();
                cust.setCustomerId(rs.getString("customer_nic"));
                cust.setName(rs.getString("cust_name"));
                cust.setTelephoneNumber(rs.getString("telephone_number"));
                cust.setGmail(rs.getString("gmail"));
                UserControler ul=new UserControler();
                cust.setUser(ul.searchUser(rs.getString("user_key")));
                cust.setCreateDate(rs.getString("customerCreateDate"));
                cust.setCustNo(rs.getInt("cust_key"));
                list.add(cust);

                
            }
        }catch (Exception ex) {
            
            ex.printStackTrace();;
        JOptionPane.showMessageDialog(null, "Error");
            
        }

            return list;
    }
        

    @Override
    public void addBooking() {
        }

    @Override
    public void checkAvailabality() {
    
    
    } 
    
        public List<Hall> selectComponentValueHallID(String Halltype) {

        List<Hall> listHallId;
        List<Hall> listHallIdSD = new ArrayList<>();
        List<Hall> listHallIdBQ = new ArrayList<>();
        List<Hall> listHallIdLX = new ArrayList<>();
        Hall hall;

        try {

            Connection con = hallDb.getConnection();
            String sql = "SELECT *FROM halls WHERE hallType=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, Halltype);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                if (Halltype.equals("Standard Hall")) {

                    String hState = rs.getString("hallState");
                    if(hState == null ? hState != null : !hState.equals("MAINTENANCE")){
                    hall = new StanderdHalls();
                    String id = rs.getString("hallId");
                    hall.setHallId(id);

                    listHallIdSD.add(hall);
                    }

                } else if (Halltype.equals("Benquet Hall")) {
                    
                    String hState = rs.getString("hallState");
                    if(hState == null ? hState != null : !hState.equals("MAINTENANCE")){
                    hall = new BenquetHalls();
                    String id = rs.getString("hallId");
                    hall.setHallId(id);
                    listHallIdBQ.add(hall);
                    }

                } else {
                    
                    String hState = rs.getString("hallState");
                    if(hState == null ? hState != null : !hState.equals("MAINTENANCE")){
                    hall = new LuxuryHalls();
                    String id = rs.getString("hallId");
                    hall.setHallId(id);
                    listHallIdLX.add(hall);
                    }

                }
            }

        } catch (Exception ex) {

            ex.printStackTrace();;
            JOptionPane.showMessageDialog(null, "Error");

        }

        if (Halltype.equals("Standard Hall")) {

            listHallId = listHallIdSD;
        } else if (Halltype.equals("Benquet Hall")) {

            listHallId = listHallIdBQ;
        } else {

            listHallId = listHallIdLX;
        }

        return listHallId;

    }

   
}
