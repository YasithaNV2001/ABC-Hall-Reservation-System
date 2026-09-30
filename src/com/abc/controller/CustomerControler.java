/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.controller;

import com.abc.database.hallDb;
import com.abc.model.BenquetHalls;
import com.abc.model.Booking;
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

    

    
        

    public Hall searchHallForBooking(String HallId) {

        Hall sd = null;
        try {

            Connection con = hallDb.getConnection();
            String sql = "SELECT *FROM halls WHERE hallId=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, HallId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                if (rs.getString("hallType").equals("Standard Hall")) {
                    sd = new StanderdHalls();
                    sd.setHallNo(rs.getInt("hall_key"));
                    sd.setHallId(rs.getString("hallId"));
                    sd.setHallType("Standard Hall");
                    sd.setAcType(rs.getString("acType"));
                    sd.setPricePerDay(Double.parseDouble(rs.getString("pricePerDay")));
                    sd.setHallCap(rs.getString("hallCap"));
                    sd.setHallState(rs.getString("hallState"));
                    sd.setHallInDate(rs.getString("inDate"));
                    sd.setHallOutDate(rs.getString("outDate"));

                } else if (rs.getString("hallType").equals("Benquet Hall")) {

                    sd = new BenquetHalls();
                    sd.setHallNo(rs.getInt("hall_key"));
                    sd.setHallId(rs.getString("hallId"));
                    sd.setHallType("Benquet Hall");
                    sd.setAcType(rs.getString("acType"));
                    sd.setPricePerDay(Double.parseDouble(rs.getString("pricePerDay")));
                    sd.setHallCap(rs.getString("hallCap"));
                    sd.setHallState(rs.getString("hallState"));
                    sd.setHallInDate(rs.getString("inDate"));
                    sd.setHallOutDate(rs.getString("outDate"));

                } else {

                    sd = new LuxuryHalls();
                    sd.setHallNo(rs.getInt("hall_key"));
                    sd.setHallId(rs.getString("hallId"));
                    sd.setHallType("Luxury Hall");
                    sd.setAcType(rs.getString("acType"));
                    sd.setPricePerDay(Double.parseDouble(rs.getString("pricePerDay")));
                    sd.setHallCap(rs.getString("hallCap"));
                    sd.setHallState(rs.getString("hallState"));
                    sd.setHallInDate(rs.getString("inDate"));
                    sd.setHallOutDate(rs.getString("outDate"));

                }

            }
        } catch (Exception e) {

            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");

        }

        return sd;

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

    @Override
    public void addBooking(Booking book) {
         
    try {
            Connection con = hallDb.getConnection();
            String sql = "INSERT INTO booking(cust_key,user_key,checkInDate,checkOutDate,numberOfDate,specificDay,booking_Type,hall_key,payment)VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1,book.getCustomer().getCustNo());
            ps.setInt(2,book.getUser().getId());
            ps.setString(3,book.getCheckIn());
            ps.setString(4,book.getCheckOut());
            ps.setInt(5,book.getNumberOfDay());
            ps.setString(6, book.getSpecificDay());
            ps.setString(7,book.getBookingType());
            ps.setInt(8,book.getHall().getHallNo());
            ps.setDouble(9, book.getPayment());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Successfully booked");

        } catch (Exception ex) {

            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error while booking");

        }
       
    }

    @Override
    public  List<Booking> checkAvailabality( String checkIn, String checkOut, Hall hall) {
        
    
        List<Booking> list = new ArrayList<>();
        UserControler uc=new UserControler();
        try {

            
            Connection con = hallDb.getConnection();
            String sql = "SELECT *FROM booking WHERE hall_key = ? AND ((checkInDate >= ? AND checkOutDate <= ?) OR (checkInDate >= ? AND checkInDate <= ?) OR (checkOutDate >= ? AND checkOutDate <= ?) OR (checkInDate <= ? AND checkOutDate >= ?))";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, hall.getHallNo());
            ps.setString(2, checkIn);
            ps.setString(3, checkOut);
            ps.setString(4, checkIn);
            ps.setString(5, checkOut);
            ps.setString(6, checkIn);
            ps.setString(7, checkOut);
            ps.setString(8, checkIn);
            ps.setString(9, checkOut);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                
                
                
                Booking book = new Booking();
                book.setBookingId(rs.getInt("booking_key"));
                book.setBookingType(rs.getString("booking_Type"));
                book.setCheckIn(rs.getString("checkInDate"));
                book.setCheckOut(rs.getString("checkOutDate"));
                book.setSpecificDay(rs.getString("specificDay"));
                book.setHall(uc.searchHallbyhall_key(rs.getInt("hall_key")));
                list.add(book);
                
            
            }
        }catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");
        }
        return list;
        
       
    }
    
    public List<Hall> selectHallbyHallType(String Halltype) {

        List<Hall> listHall;
        List<Hall> listHallSD = new ArrayList<>();
        List<Hall> listHallBQ = new ArrayList<>();
        List<Hall> listHallLX = new ArrayList<>();
        Hall hall;

        try {

            Connection con = hallDb.getConnection();
            String sql = "SELECT * FROM halls";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                if (rs.getString("hallType").equals("Standard Hall")) {

                    String hState = rs.getString("hallState");
                    if(hState == null ? hState != null : !hState.equals("MAINTENANCE")){
                    hall = new StanderdHalls();
                    hall.setHallId(rs.getString("hallId"));
                    hall.setHallType(rs.getString("hallType"));
                    hall.setAcType(rs.getString("acType"));
                    hall.setPricePerDay(Double.parseDouble(rs.getString("pricePerDay")));
                    hall.setHallCap(rs.getString("hallCap"));
                    

                    listHallSD.add(hall);
                    }

                } else if (rs.getString("hallType").equals("Benquet Hall")) {
                    
                    String hState = rs.getString("hallState");
                    if(hState == null ? hState != null : !hState.equals("MAINTENANCE")){
                    hall = new BenquetHalls();
                    hall.setHallId(rs.getString("hallId"));
                    hall.setHallType(rs.getString("hallType"));
                    hall.setAcType(rs.getString("acType"));
                    hall.setPricePerDay(Double.parseDouble(rs.getString("pricePerDay")));
                    hall.setHallCap(rs.getString("hallCap"));;
                    listHallBQ.add(hall);
                    }

                } else {
                    
                    String hState = rs.getString("hallState");
                    if(hState == null ? hState != null : !hState.equals("MAINTENANCE")){
                    hall = new LuxuryHalls();
                    hall.setHallId(rs.getString("hallId"));
                    hall.setHallType(rs.getString("hallType"));
                    hall.setAcType(rs.getString("acType"));
                    hall.setPricePerDay(Double.parseDouble(rs.getString("pricePerDay")));
                    hall.setHallCap(rs.getString("hallCap"));
                    listHallLX.add(hall);
                    }

                }
            }

        } catch (Exception ex) {

            ex.printStackTrace();;
            JOptionPane.showMessageDialog(null, "Error");

        }

        if (Halltype.equals("Standard Hall")) {

            listHall = listHallSD;
        } else if (Halltype.equals("Benquet Hall")) {

            listHall = listHallBQ;
        } else {

            listHall = listHallLX;
        }

        return listHall;

    
    }

   
}
