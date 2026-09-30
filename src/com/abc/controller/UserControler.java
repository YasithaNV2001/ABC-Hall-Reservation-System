/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.controller;

import com.abc.controller.UserInterface;
import com.abc.database.hallDb;
import com.abc.model.Customer;
import com.abc.model.User;
import com.abc.model.UserType;
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

    public void addPayment() {
    }

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

    @Override
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

    @Override
    public void addBooking() {

    }

    @Override
    public void checkAvailabality() {

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

                if (rs.getString("userId").equals(custNic)) {

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
}
