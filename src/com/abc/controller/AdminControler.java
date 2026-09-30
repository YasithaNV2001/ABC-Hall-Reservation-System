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
import com.abc.model.UserType;
import static com.abc.model.UserType.ADMIN;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class AdminControler extends UserControler implements UserInterface {

    

    

    

    

    

    public void addUser(User user) {

        try {
            Connection con = hallDb.getConnection();
            String sql = "INSERT INTO user(userId,UserType,password,userCreateDate)VALUES(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getUserId());
            String type;
            if (user.getUserType() == ADMIN) {
                type = "ADMIN";

            } else {
                type = "FRONT_DESK_USER";

            }
            ps.setString(2, type);
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getUserCreateDate());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Successfully Submited");

        } catch (Exception ex) {

            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error while Submiting");

        }

    }

    public List<User> listUser() {

        List<User> listUser = new ArrayList<>();

        try {
            Connection con = hallDb.getConnection();
            String sql = "SELECT * FROM user";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                User user = new User();

                user.setUserId(rs.getString("userId"));
                UserType type;
                if (rs.getString("UserType").equals("ADMIN")) {

                    type = UserType.ADMIN;

                } else {
                    type = UserType.FRONT_DESK_USER;

                }
                user.setUserType(type);
                user.setId(rs.getInt("user_key"));
                user.setUserCreateDate(rs.getString("userCreateDate"));
                user.setPassword(rs.getString("password"));
                listUser.add(user);

            }
        } catch (Exception ex) {

            ex.printStackTrace();;
            JOptionPane.showMessageDialog(null, "Error");

        }

        return listUser;

    }

    public boolean serachUserId(String userid) {

        boolean b = false;
        try {

            Connection con = hallDb.getConnection();
            String sql = "SELECT *FROM user WHERE userId=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                if (rs.getString("userId").equals(userid)) {

                    JOptionPane.showMessageDialog(null, "This User id  is alredy submited");
                }

            } else {
                b = true;

            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error");

        }

        return b;
    }

    public boolean serachHallId(String hallId) {

        boolean b = false;
        try {

            Connection con = hallDb.getConnection();
            String sql = "SELECT *FROM halls WHERE hallId=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, hallId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                if (rs.getString("hallId").equals(hallId)) {

                    JOptionPane.showMessageDialog(null, "This Hall Id  is alredy submited");
                }

            } else {
                b = true;

            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error");

        }

        return b;
    }

    public User searchUser(String userid) {

        User user = new User();
        try {

            Connection con = hallDb.getConnection();
            String sql = "SELECT *FROM user WHERE userId=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userid);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                if (rs.getString("UserType").equals("ADMIN")) {
                    user.setUserId(rs.getString("userId"));
                    user.setUserType(UserType.ADMIN);
                    user.setPassword(rs.getString("password"));

                } else {

                    user.setUserId(rs.getString("userId"));
                    user.setUserType(UserType.FRONT_DESK_USER);
                    user.setPassword(rs.getString("password"));

                }

            }
        } catch (Exception e) {

            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");

        }

        return user;

    }

    public void updateUser(User user) {

        try {
            Connection con = hallDb.getConnection();
            String sql = "UPDATE user SET UserType=?,password=?,userCreateDate=? WHERE userId=?";
            PreparedStatement ps = con.prepareStatement(sql);
            //ps.setString(1,user.getUserId());
            String type;
            if (user.getUserType() == ADMIN) {
                type = "ADMIN";

            } else {
                type = "FRONT_DESK_USER";

            }
            ps.setString(1, type);
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getUserCreateDate());
            ps.setString(4, user.getUserId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Updated");

        } catch (Exception ex) {
            ex.printStackTrace();;
            JOptionPane.showMessageDialog(null, "Error");
        }

    }

    public void userDelete(User user) {

        try {
            Connection con = hallDb.getConnection();

            String sql = "DELETE FROM user WHERE userId=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getUserId());

            ps.executeUpdate();

            String sqlAuto = "ALTER TABLE user AUTO_INCREMENT =?";
            PreparedStatement psAuto = con.prepareStatement(sqlAuto);
            psAuto.setInt(1, user.getId());
            psAuto.executeUpdate();

            JOptionPane.showMessageDialog(null, "Delete Successfull");

        } catch (Exception ex) {
            ex.printStackTrace();;
            JOptionPane.showMessageDialog(null, "Error");
        }

    }

    public void addHall(Hall hall) {

        try {
            Connection con = hallDb.getConnection();
            String sql = "INSERT INTO halls(hallId,hallType,acType,pricePerDay,hallCap,hallState,inDate,outDate)VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, hall.getHallId());
            ps.setString(2, hall.getHallType());
            ps.setString(3, hall.getAcType());
            ps.setString(4, Double.toString(hall.getPricePerDay()));
            ps.setString(5, hall.getHallCap());
            ps.setString(6, hall.getHallState());
            ps.setString(7, hall.getHallInDate());
            ps.setString(8, hall.getHallOutDate());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Successfully Submited");

        } catch (Exception ex) {

            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error while Submiting");

        }

    }

    public void updateHall(Hall hall) {

        try {
            Connection con = hallDb.getConnection();
            String sql = "UPDATE halls SET hallType=?,acType=?,pricePerDay=?,hallCap=?,hallState=?,inDate=? WHERE hallId=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, hall.getHallType());
            ps.setString(2, hall.getAcType());
            ps.setString(3, Double.toString(hall.getPricePerDay()));
            ps.setString(4, hall.getHallCap());
            ps.setString(5, hall.getHallState());
            ps.setString(6, hall.getHallInDate());
            ps.setString(7, hall.getHallId());

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Updated");

        } catch (Exception ex) {
            ex.printStackTrace();;
            JOptionPane.showMessageDialog(null, "Error");
        }

    }

    public List<Hall> listHalls() {

        List<Hall> listHall = new ArrayList<>();

        try {
            Connection con = hallDb.getConnection();
            String sql = "SELECT * FROM halls";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                if (rs.getString("hallType").equals("Standard Hall")) {

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

                } else if (rs.getString("hallType").equals("Benquet Hall")) {

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

                } else {
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
        } catch (Exception ex) {

            ex.printStackTrace();;
            JOptionPane.showMessageDialog(null, "Error");

        }

        return listHall;

    }

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
    
    public Hall searchHall(String HallId) {

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

    public void hallDelete(Hall hall) {

        try {
            Connection con = hallDb.getConnection();

            String sql = "DELETE FROM halls WHERE hallId=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, hall.getHallId());

            ps.executeUpdate();

            String sqlAuto = "ALTER TABLE halls AUTO_INCREMENT =?";
            PreparedStatement psAuto = con.prepareStatement(sqlAuto);
            psAuto.setInt(1, hall.getHallNo());
            psAuto.executeUpdate();

            JOptionPane.showMessageDialog(null, "Delete Successfull");

        } catch (Exception ex) {
            ex.printStackTrace();;
            JOptionPane.showMessageDialog(null, "Error");
        }

    }

    public void addHallMaintenance(Hall hall) {

        Hall sd = null;
        try {

            Connection con = hallDb.getConnection();
            String sql = "UPDATE halls SET hallState=?,inDate=?,outDate=? WHERE hallId=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, hall.getHallState());
            ps.setString(2, hall.getHallInDate());
            ps.setString(3, hall.getHallOutDate());
            ps.setString(4, hall.getHallId());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Successfull add Maintenanse");

        } catch (Exception e) {

            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");

        }

    }

    public void removeHallMaintenance(Hall hall) {

        try {

            Connection con = hallDb.getConnection();
            String sql = "UPDATE *FROM halls hallState=?,inDate=?,outDate=? WHERE hallId=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, hall.getHallState());
            ps.setString(2, hall.getHallInDate());
            ps.setString(3, hall.getHallOutDate());
            ps.setString(4, hall.getHallId());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Successfull Remove Maintenanse");

        } catch (Exception e) {

            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");

        }

    }

    public void autoDailyMaintenanceUpdateControler(String date) {

        try {
            List<Hall> list = listHalls();

            for (Hall k : list) {

                String id = k.getHallId();
                String State = k.getHallState();
                String outDate = k.getHallOutDate();

                if (outDate == date && State == "MAINTENANCE") {

                    Connection con = hallDb.getConnection();
                    String sql = "UPDATE *FROM halls hallState=?,inDate=?,outDate=? WHERE hallId=?";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, "AVAILABLE");
                    ps.setString(2, "Last Maintenance Date  " + date);
                    ps.setString(3, "continue to BOOK or Maintenance");
                    ps.setString(4, id);
                    ps.executeUpdate();

                }

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error");

        }

    }

    

    

}
