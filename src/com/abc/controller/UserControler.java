/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.controller;

import com.abc.controller.UserInterface;
import com.abc.database.hallDb;
import com.abc.model.BenquetHalls;
import com.abc.model.Booking;
import com.abc.model.Customer;
import com.abc.model.Hall;
import com.abc.model.LuxuryHalls;
import com.abc.model.StanderdHalls;
import com.abc.model.User;
import com.abc.model.UserType;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

/**
 *
 * @author User
 */
public class UserControler implements UserInterface {

    

    
    public void addCustomer(Customer customer) {

        try {
            Connection con = hallDb.getConnection();
            String sql = "INSERT INTO customers(customer_nic,cust_name,telephone_number,gmail,user_key,customerCreateDate)VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, customer.getCustomerId());
            ps.setString(2, customer.getName());
            ps.setString(3, customer.getTelephoneNumber());
            ps.setString(4, customer.getGmail());
            ps.setInt(5, customer.getUser().getId());
            ps.setString(6, customer.getCreateDate());

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Successfully Submited");

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error while Submiting");

        }
    }

    public void updateCustomer(Customer customer) {

        try {
            Connection con = hallDb.getConnection();
            String sql = "UPDATE customers SET cust_name=?,telephone_number=?,gmail=?,user_key=?,customerCreateDate=? WHERE customer_nic=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, customer.getName());
            ps.setString(2, customer.getTelephoneNumber());
            ps.setString(3, customer.getGmail());
            ps.setInt(4, customer.getUser().getId());
            ps.setString(5, customer.getCreateDate());
            ps.setString(6, customer.getCustomerId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Updated");

        } catch (Exception ex) {
            ex.printStackTrace();;
            JOptionPane.showMessageDialog(null, "Error");
        }

    }

    public void deleteCustomer(Customer customer) {
        try {
            Connection con = hallDb.getConnection();
            String sql = "DELETE FROM customers WHERE customer_nic=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, customer.getCustomerId());

            ps.executeUpdate();

            String sqlAuto = "ALTER TABLE customers AUTO_INCREMENT =?";
            PreparedStatement psAuto = con.prepareStatement(sqlAuto);
            psAuto.setInt(1, customer.getCustNo());
            psAuto.executeUpdate();

            JOptionPane.showMessageDialog(null, "Delete Successfull");

        } catch (Exception ex) {
            ex.printStackTrace();;
            JOptionPane.showMessageDialog(null, "Error");
        }

    }

    public Customer customerSearch(String customerId) {

        Customer cust = new Customer();
        try {

            Connection con = hallDb.getConnection();
            String sql = "SELECT *FROM customers WHERE customer_nic=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, customerId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                if (rs.getString("customer_nic").equals(customerId)) {
                    
                cust.setCustomerId(rs.getString("customer_nic"));
                cust.setName(rs.getString("cust_name"));
                cust.setTelephoneNumber(rs.getString("telephone_number"));
                cust.setGmail(rs.getString("gmail"));
                
                
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");
        }

        return cust;
    }
    
    public Hall searchHallbyhall_key(int Hallkey) {

        Hall sd = null;
        try {

            Connection con = hallDb.getConnection();
            String sql = "SELECT *FROM halls WHERE hall_key=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, Hallkey);
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

    public User searchUser(String userkey) {

        User user = new User();
        try {

            Connection con = hallDb.getConnection();
            String sql = "SELECT *FROM user WHERE user_key=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userkey);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                if (rs.getString("UserType").equals("ADMIN")) {
                    user.setId(rs.getInt("user_key"));
                    user.setUserId(rs.getString("userId"));
                    user.setUserType(UserType.ADMIN);

                } else {

                    user.setId(rs.getInt("user_key"));
                    user.setUserId(rs.getString("userId"));
                    user.setUserType(UserType.FRONT_DESK_USER);

                }

            }
        } catch (Exception e) {

            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");

        }

        return user;

    }
    
     public Customer searchCustomerObjbyCust_key(String cust_key) {

        Customer cust = new Customer();
        try {

            Connection con = hallDb.getConnection();
            String sql = "SELECT *FROM customers WHERE cust_key=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cust_key);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                cust.setCustNo(rs.getInt("cust_key"));
                cust.setCustomerId(rs.getString("customer_nic"));
                cust.setName(rs.getString("cust_name"));
                cust.setTelephoneNumber(rs.getString("telephone_number"));
                cust.setGmail(rs.getString("gmail"));

                    

             

            }
        } catch (Exception e) {

            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");

        }

        return cust;

    }

    
    public List<Customer> listCustomer() {

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
                cust.setUser(searchUser(rs.getString("user_key")));
                cust.setCreateDate(rs.getString("customerCreateDate"));
                cust.setCustNo(rs.getInt("cust_key"));
                list.add(cust);

            }

        } catch (Exception ex) {

            ex.printStackTrace();;
            JOptionPane.showMessageDialog(null, "Error");

        }

        return list;

    }

   

    
    
    public boolean serachCustomerNic(String custNic) {

        boolean b = false;
        try {

            Connection con = hallDb.getConnection();
            String sql = "SELECT *FROM customers WHERE customer_nic=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, custNic);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                if (rs.getString("customer_nic").equals(custNic)) {

                    JOptionPane.showMessageDialog(null, "This Customer NIC  is alredy submited");
                } 

            }else {
                    b = true;

                }
        } catch (Exception e) {

            
            JOptionPane.showMessageDialog(null, "Error");

        }

        return b;
    }
    
    public Customer serachCustomerObjbyNic(String custNic) {
 
        Customer cust = new Customer();
         
        try {

            Connection con = hallDb.getConnection();
            String sql = "SELECT *FROM customers WHERE customer_nic=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, custNic);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                if (rs.getString("customer_nic").equals(custNic)) {

                    cust.setCustomerId(rs.getString("customer_nic"));
                    cust.setCustNo(rs.getInt("cust_key"));
                    cust.setName(rs.getString("cust_name"));
                    cust.setTelephoneNumber(rs.getString("telephone_number"));
                    cust.setGmail(rs.getString("gmail"));
                    
                
                } 

            }
        } catch (Exception e) {

            
            JOptionPane.showMessageDialog(null, "Error");

        }

        return cust;
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
    
    public void updateBooking(Booking book) {

        try {
            Connection con = hallDb.getConnection();
            String sql = "UPDATE booking SET cust_key=?,user_key=?,checkInDate=?,checkOutDate=?,numberOfDate=?,specificDay=?,booking_Type=?,hall_key=?,payment=? WHERE booking_key=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, book.getCustomer().getCustNo());
            ps.setInt(2, book.getUser().getId());
            ps.setString(3, book.getCheckIn());
            ps.setString(4, book.getCheckOut());
            ps.setInt(5, book.getNumberOfDay());
            ps.setString(6, book.getSpecificDay());
            ps.setString(7, book.getBookingType());
            ps.setInt(8, book.getHall().getHallNo());
            ps.setDouble(9, book.getPayment());
            ps.setInt(10, book.getBookingId());
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Updated");

        } catch (Exception ex) {
            ex.printStackTrace();;
            JOptionPane.showMessageDialog(null, "Error");
        }

    }
    public void deleteBooking(Booking book) {
        try {
            Connection con = hallDb.getConnection();
            String sql = "DELETE FROM booking WHERE booking_key=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, book.getBookingId());

            ps.executeUpdate();

            String sqlAuto = "ALTER TABLE booking AUTO_INCREMENT =?";
            PreparedStatement psAuto = con.prepareStatement(sqlAuto);
            psAuto.setInt(1, book.getBookingId());
            psAuto.executeUpdate();

            JOptionPane.showMessageDialog(null, "Delete Successfull");

        } catch (Exception ex) {
            ex.printStackTrace();;
            JOptionPane.showMessageDialog(null, "Error");
        }

    }
    
    public Booking BookingSearch(int booking_key) {

        Booking book = new Booking();
        try {

            Connection con = hallDb.getConnection();
            String sql = "SELECT *FROM booking WHERE booking_key=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, booking_key);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                
                book.setBookingId(rs.getInt("booking_key"));
                book.setBookingType(rs.getString("booking_Type"));
                book.setCheckIn(rs.getString("checkInDate"));
                book.setCheckOut(rs.getString("checkOutDate"));
                book.setNumberOfDay(rs.getInt("numberOfDate"));
                book.setSpecificDay(new String(rs.getBytes("specificDay"),StandardCharsets.UTF_8));
                book.setCustomer(searchCustomerObjbyCust_key(rs.getString("cust_key")));
                book.setUser(searchUser(rs.getString("user_key")));
                book.setHall(searchHallbyhall_key(rs.getInt("hall_key")));
                book.setPayment(rs.getDouble("payment"));
                
                
                }
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");
        }

        return book;
    }
    
    

    @Override
    public  List<Booking> checkAvailabality(String checkIn,String checkOut,Hall hall) {

       
        List<Booking> list = new ArrayList<>();
        
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
                book.setHall(searchHallbyhall_key(rs.getInt("hall_key")));
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
    
    
    
    
    
    public List<Hall> listHallTable() {

        List<Hall> listHall = new ArrayList<>();

        try {
            Connection con = hallDb.getConnection();
            String sql = "SELECT * FROM halls";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                if (rs.getString("hallType").equals("Standard Hall")) {

                     String hState = rs.getString("hallState");
                    if(hState == null ? hState != null : !hState.equals("MAINTENANCE")){
                    StanderdHalls sd = new StanderdHalls();
                    sd.setHallId(rs.getString("hallId"));
                    sd.setHallType("Standard Hall");
                    sd.setAcType(rs.getString("acType"));
                    sd.setPricePerDay(Double.parseDouble(rs.getString("pricePerDay")));
                    sd.setHallCap(rs.getString("hallCap"));
                    sd.setHallNo(rs.getInt("hall_key"));
                    sd.setHallState(rs.getString("hallState"));
                    sd.setHallInDate(rs.getString("inDate"));
                    sd.setHallOutDate(rs.getString("outDate"));
                    listHall.add(sd);
                    }

                } else if (rs.getString("hallType").equals("Benquet Hall")) {

                     String hState = rs.getString("hallState");
                    if(hState == null ? hState != null : !hState.equals("MAINTENANCE")){
                    BenquetHalls bq = new BenquetHalls();
                    bq.setHallId(rs.getString("hallId"));
                    bq.setHallType("Benquet Hall");
                    bq.setAcType(rs.getString("acType"));
                    bq.setPricePerDay(Double.parseDouble(rs.getString("pricePerDay")));
                    bq.setHallCap(rs.getString("hallCap"));
                    bq.setHallNo(rs.getInt("hall_key"));
                    bq.setHallState(rs.getString("hallState"));
                    bq.setHallInDate(rs.getString("inDate"));
                    bq.setHallOutDate(rs.getString("outDate"));
                    listHall.add(bq);
                    }

                } else {
                     String hState = rs.getString("hallState");
                    if(hState == null ? hState != null : !hState.equals("MAINTENANCE")){
                    LuxuryHalls lx = new LuxuryHalls();
                    lx.setHallId(rs.getString("hallId"));
                    lx.setHallType("Luxury Hall");
                    lx.setAcType(rs.getString("acType"));
                    lx.setPricePerDay(Double.parseDouble(rs.getString("pricePerDay")));
                    lx.setHallCap(rs.getString("hallCap"));
                    lx.setHallNo(rs.getInt("hall_key"));
                    lx.setHallState(rs.getString("hallState"));
                    lx.setHallInDate(rs.getString("inDate"));
                    lx.setHallOutDate(rs.getString("outDate"));
                    listHall.add(lx);
                    }

                }

            }
        } catch (Exception ex) {

            ex.printStackTrace();;
            JOptionPane.showMessageDialog(null, "Error");

        }

        return listHall;

    }
    public User searchUserObjForBooking(String userid) {

        User user = new User();
        try {

            Connection con = hallDb.getConnection();
            String sql = "SELECT *FROM user WHERE userId=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userid);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                if (rs.getString("UserType").equals("ADMIN")) {
                    user.setId(rs.getInt("user_key"));
                    user.setUserId(rs.getString("userId"));
                    user.setUserType(UserType.ADMIN);
                    

                } else {

                    user.setId(rs.getInt("user_key"));
                    user.setUserId(rs.getString("userId"));
                    user.setUserType(UserType.FRONT_DESK_USER);
                    

                }

            }
        } catch (Exception e) {

            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");

        }

        return user;

    }
    
    public List<Booking> listBooking() {

        List<Booking> list = new ArrayList<>();

        try {
            Connection con = hallDb.getConnection();
            String sql = "SELECT * FROM booking";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Booking book = new Booking();
                book.setBookingId(rs.getInt("booking_key"));
                
                book.setBookingType(rs.getString("booking_Type"));
                book.setHall(searchHallbyhall_key(rs.getInt("hall_key")));
                book.setNumberOfDay(rs.getInt("numberOfDate"));
                book.setUser(searchUser(rs.getString("user_key")));
                book.setCheckIn(rs.getString("checkInDate"));
                book.setCheckOut(rs.getString("checkOutDate"));
                book.setSpecificDay(rs.getString("specificDay"));
                book.setCustomer(searchCustomerObjbyCust_key(rs.getString("cust_key")));
                list.add(book);

            }

        } catch (Exception ex) {

            ex.printStackTrace();;
            JOptionPane.showMessageDialog(null, "Error");

        }

        return list;

    }
    
    
}
