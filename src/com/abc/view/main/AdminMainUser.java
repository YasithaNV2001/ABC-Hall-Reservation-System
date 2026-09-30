/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.abc.view.main;

import com.abc.controller.AdminControler;
import com.abc.controller.CustomerControler;
import com.abc.controller.UserControler;
import com.abc.database.hallDb;
import com.abc.model.BenquetHalls;
import com.abc.model.Booking;
import com.abc.model.Customer;
import com.abc.model.Hall;
import com.abc.model.Login;
import com.abc.model.LuxuryHalls;
import com.abc.model.StanderdHalls;
import com.abc.model.User;

import com.abc.view.componont.DatePicker;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import javax.swing.ComboBoxModel;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JPanel;


/**
 *
 * @author User
 */
public class AdminMainUser extends javax.swing.JFrame {

    /**
     * Creates new form CustomerUserIView
     */
    private JCheckBox[] checkboxes;
    private int selectedDays;

    public AdminMainUser() {
        initComponents();
        showDate();
        DataTableForBookingDetails();
        DataTableHall();
        showDateTime();

        jDataTableFD.setAutoCreateRowSorter(true);

        checkboxes = new JCheckBox[7];
        selectedDays = 0;

        checkboxes[0] = cbSunday;
        checkboxes[1] = cbMonday;
        checkboxes[2] = cbTuesday;
        checkboxes[3] = cbWednesday;
        checkboxes[4] = cbThursday;
        checkboxes[5] = cbFriday;
        checkboxes[6] = cbSaterday;

    }

    private int updateSelectedDays() {
        selectedDays = 0;
        for (int i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i].isSelected()) {

                selectedDays += (int) Math.pow(2, i);
            }
        }
        return selectedDays;
    }

    private void updateCheckboxes() {
        for (int i = 0; i < checkboxes.length; i++) {
            checkboxes[i].setSelected((selectedDays & (1 << i)) != 0);
        }
    }

    public String showDateTime() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd|MM|yyyy  HH:mm:ss");
        Date d = new Date();
        String date = sdf.format(d);

        return date;
    }

    public void DataTableHall() {

        UserControler Cc = new UserControler();
        List<Hall> list = Cc.listHallTable();
        DefaultTableModel DFT = (DefaultTableModel) halltable.getModel();
        DFT.setRowCount(0);
        for (Hall k : list) {

            String htype = k.getHallType();
            String id = k.getHallId();
            String hallCap = k.getHallCap();
            double pricePerDay = k.getPricePerDay();
            String actype = k.getAcType();
            DFT.addRow(new Object[]{htype, id, hallCap, pricePerDay, actype});

        }
    }

    public void showDate() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd|MM|yyyy");
        Date d = new Date();
        txtCheckInDateFD.setText(sdf.format(d));
        txtCheckOutDateFD.setText(sdf.format(d));

    }
    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public boolean validateSubmit() {

        boolean b = false;
        if (txtNicAM.getText().equals("")) {

            b = false;
            JOptionPane.showMessageDialog(null, "Customer NIC is requried");
            txtNicAM.requestFocus();
        } else if (txtNameAM.getText().equals("")) {

            b = false;
            JOptionPane.showMessageDialog(null, "Customer Name is requried");
            txtNameAM.requestFocus();

        } else if (txtTeleAM.getText().equals("")) {

            b = false;
            JOptionPane.showMessageDialog(null, "Customer Telephone Number is requried");
            txtTeleAM.requestFocus();

        } else if (txtEmailAM.getText().equals("")) {

            b = false;
            JOptionPane.showMessageDialog(null, "Customer Email is requried");
            txtEmailAM.requestFocus();

        } else if (!(Pattern.matches(EMAIL_PATTERN, txtEmailAM.getText()))) {

            JOptionPane.showMessageDialog(null, "Email is invalid ");

        } else {

            b = true;
        }

        return b;

    }

    public boolean validateSearch() {

        boolean b = false;
        if (txtSearch.getText().equals("")) {

            b = false;
            JOptionPane.showMessageDialog(null, "Enter Search field is empty");
            txtSearch.requestFocus();
        } else {

            b = true;

        }

        return b;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        gradientBG2 = new com.abc.view.componont.GradientBG();
        BookPanel = new javax.swing.JLayeredPane();
        btnAddCustomer = new com.abc.view.swing_componont.ButtonOutLine();
        txtSearch = new com.abc.view.swing_componont.MyTextField();
        btnSearch = new com.abc.view.swing_componont.JButtonExtend1();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        txtNicAM = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNameAM = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTeleAM = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtEmailAM = new javax.swing.JTextField();
        btnCustomerVerify = new com.abc.view.swing_componont.JButtonExtend1();
        btnExit = new com.abc.view.swing_componont.JButtonExtend1();
        btnClear = new com.abc.view.swing_componont.JButtonExtend1();
        btnUpdateCustomer = new com.abc.view.swing_componont.ButtonOutLine();
        btnDeleteCustomer = new com.abc.view.swing_componont.ButtonOutLine();
        jLabel26 = new javax.swing.JLabel();
        calnderpane = new javax.swing.JLayeredPane();
        cbFriday = new javax.swing.JCheckBox();
        cbThursday = new javax.swing.JCheckBox();
        cbWednesday = new javax.swing.JCheckBox();
        cbSaterday = new javax.swing.JCheckBox();
        cbTuesday = new javax.swing.JCheckBox();
        cbMonday = new javax.swing.JCheckBox();
        cbSunday = new javax.swing.JCheckBox();
        btnCheckAvailability = new com.abc.view.swing_componont.JButtonExtend1();
        cmbNumberOfDay = new javax.swing.JComboBox<>();
        checkindate1 = new javax.swing.JLabel();
        cmbBookType = new javax.swing.JComboBox<>();
        CheckAvailabelPanel = new javax.swing.JLayeredPane();
        checkInCalender = new javax.swing.JLayeredPane();
        txtCheckInDateFD = new javax.swing.JLabel();
        btnCheckInFD = new com.abc.view.swing_componont.ButtonOutLine();
        checkindate = new javax.swing.JLabel();
        checkOutdate = new javax.swing.JLabel();
        checkOutCalender = new javax.swing.JLayeredPane();
        txtCheckOutDateFD = new javax.swing.JLabel();
        btnCheckOutFD = new com.abc.view.swing_componont.ButtonOutLine();
        jLabel25 = new javax.swing.JLabel();
        HallType = new javax.swing.JLayeredPane();
        lbHalltype = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        btnSelectLuxuryHall = new com.abc.view.swing_componont.ButtonOutLine();
        jLabel22 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtHallId = new javax.swing.JTextField();
        txtCap = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        txtAc = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        cmbHtype = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        halltable = new javax.swing.JTable();
        btnAddBook = new com.abc.view.swing_componont.JButtonExtend1();
        btnUpdateBook = new com.abc.view.swing_componont.JButtonExtend1();
        btnDeleteBook = new com.abc.view.swing_componont.JButtonExtend1();
        btnUserSetings = new com.abc.view.swing_componont.JButtonExtend1();
        btnHallSetings = new com.abc.view.swing_componont.JButtonExtend1();
        btnCustomerDetails = new com.abc.view.swing_componont.JButtonExtend1();
        jScrollPane1 = new javax.swing.JScrollPane();
        jDataTableFD = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        javax.swing.GroupLayout BookPanelLayout = new javax.swing.GroupLayout(BookPanel);
        BookPanel.setLayout(BookPanelLayout);
        BookPanelLayout.setHorizontalGroup(
            BookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 932, Short.MAX_VALUE)
        );
        BookPanelLayout.setVerticalGroup(
            BookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        btnAddCustomer.setForeground(new java.awt.Color(255, 255, 255));
        btnAddCustomer.setText("ADD Customer");
        btnAddCustomer.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAddCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCustomerActionPerformed(evt);
            }
        });

        txtSearch.setHint("Search");
        txtSearch.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/com/abc/view/icon/search.png"))); // NOI18N
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        btnSearch.setText("Search");
        btnSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSearch.setkBorderRadius(0);
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLayeredPane2.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane2.setOpaque(true);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setForeground(new java.awt.Color(0, 73, 227));
        jLabel1.setText("NIC");

        txtNicAM.setBackground(new java.awt.Color(255, 255, 255));
        txtNicAM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNicAM.setForeground(new java.awt.Color(0, 73, 227));
        txtNicAM.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 73, 227)));
        txtNicAM.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtNicAM.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtNicAM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNicAMActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 73, 227));
        jLabel2.setText("Enter Your Information");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setForeground(new java.awt.Color(0, 73, 227));
        jLabel3.setText("Name");

        txtNameAM.setBackground(new java.awt.Color(255, 255, 255));
        txtNameAM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNameAM.setForeground(new java.awt.Color(0, 73, 227));
        txtNameAM.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 73, 227)));
        txtNameAM.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtNameAM.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtNameAM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameAMActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setForeground(new java.awt.Color(0, 73, 227));
        jLabel4.setText("Telephone Number");

        txtTeleAM.setBackground(new java.awt.Color(255, 255, 255));
        txtTeleAM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTeleAM.setForeground(new java.awt.Color(0, 73, 227));
        txtTeleAM.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 73, 227)));
        txtTeleAM.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtTeleAM.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTeleAM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTeleAMActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setForeground(new java.awt.Color(0, 73, 227));
        jLabel5.setText("E-Mail");

        txtEmailAM.setBackground(new java.awt.Color(255, 255, 255));
        txtEmailAM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtEmailAM.setForeground(new java.awt.Color(0, 73, 227));
        txtEmailAM.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 73, 227)));
        txtEmailAM.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtEmailAM.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtEmailAM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailAMActionPerformed(evt);
            }
        });

        btnCustomerVerify.setText("Verify");
        btnCustomerVerify.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCustomerVerify.setkBorderRadius(0);
        btnCustomerVerify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCustomerVerifyActionPerformed(evt);
            }
        });

        jLayeredPane2.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(txtNicAM, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(txtNameAM, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(txtTeleAM, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(txtEmailAM, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(btnCustomerVerify, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jLayeredPane2Layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                            .addComponent(btnCustomerVerify, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtEmailAM, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtTeleAM, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtNameAM, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtNicAM, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGap(21, 21, 21))
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCustomerVerify, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNicAM, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNameAM, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTeleAM, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmailAM, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        btnExit.setText("Exit");
        btnExit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnExit.setkBorderRadius(0);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnClear.setkBorderRadius(0);
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnUpdateCustomer.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateCustomer.setText("UPDATE Customer");
        btnUpdateCustomer.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnUpdateCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateCustomerActionPerformed(evt);
            }
        });

        btnDeleteCustomer.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteCustomer.setText("DELETE Customer");
        btnDeleteCustomer.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnDeleteCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCustomerActionPerformed(evt);
            }
        });

        jLabel26.setBackground(new java.awt.Color(255, 255, 255));
        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("What Day You Looking for");

        calnderpane.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        cbFriday.setText("Friday");
        cbFriday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFridayActionPerformed(evt);
            }
        });

        cbThursday.setText("Thursday");

        cbWednesday.setText("Wednesday");

        cbSaterday.setText("Saterday");

        cbTuesday.setText("Tuesday");

        cbMonday.setText("Monday");

        cbSunday.setText("Sunday");

        btnCheckAvailability.setText("Check Availability");
        btnCheckAvailability.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnCheckAvailability.setkBorderRadius(25);
        btnCheckAvailability.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckAvailabilityActionPerformed(evt);
            }
        });

        cmbNumberOfDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15" }));

        checkindate1.setBackground(new java.awt.Color(204, 204, 204));
        checkindate1.setText("Number Of Days");

        cmbBookType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A Given Date", "A Continues Period", "A Specific Day" }));
        cmbBookType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBookTypeActionPerformed(evt);
            }
        });

        CheckAvailabelPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        checkInCalender.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        txtCheckInDateFD.setBackground(new java.awt.Color(255, 255, 255));
        txtCheckInDateFD.setFont(new java.awt.Font("Calibri", 1, 28)); // NOI18N
        txtCheckInDateFD.setForeground(new java.awt.Color(255, 255, 255));
        txtCheckInDateFD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtCheckInDateFD.setText("10|02|2023");

        btnCheckInFD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/abc/view/icon/cursor (3).png"))); // NOI18N
        btnCheckInFD.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        btnCheckInFD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckInFDActionPerformed(evt);
            }
        });

        checkInCalender.setLayer(txtCheckInDateFD, javax.swing.JLayeredPane.DEFAULT_LAYER);
        checkInCalender.setLayer(btnCheckInFD, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout checkInCalenderLayout = new javax.swing.GroupLayout(checkInCalender);
        checkInCalender.setLayout(checkInCalenderLayout);
        checkInCalenderLayout.setHorizontalGroup(
            checkInCalenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(checkInCalenderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtCheckInDateFD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCheckInFD, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        checkInCalenderLayout.setVerticalGroup(
            checkInCalenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(checkInCalenderLayout.createSequentialGroup()
                .addGroup(checkInCalenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCheckInDateFD)
                    .addComponent(btnCheckInFD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        checkindate.setBackground(new java.awt.Color(204, 204, 204));
        checkindate.setText("Check In Date");

        checkOutdate.setBackground(new java.awt.Color(204, 204, 204));
        checkOutdate.setText("Check Out Date");

        checkOutCalender.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        txtCheckOutDateFD.setBackground(new java.awt.Color(255, 255, 255));
        txtCheckOutDateFD.setFont(new java.awt.Font("Calibri", 1, 28)); // NOI18N
        txtCheckOutDateFD.setForeground(new java.awt.Color(255, 255, 255));
        txtCheckOutDateFD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtCheckOutDateFD.setText("10|02|2023");

        btnCheckOutFD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/abc/view/icon/cursor (3).png"))); // NOI18N
        btnCheckOutFD.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        btnCheckOutFD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckOutFDActionPerformed(evt);
            }
        });

        checkOutCalender.setLayer(txtCheckOutDateFD, javax.swing.JLayeredPane.DEFAULT_LAYER);
        checkOutCalender.setLayer(btnCheckOutFD, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout checkOutCalenderLayout = new javax.swing.GroupLayout(checkOutCalender);
        checkOutCalender.setLayout(checkOutCalenderLayout);
        checkOutCalenderLayout.setHorizontalGroup(
            checkOutCalenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(checkOutCalenderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtCheckOutDateFD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCheckOutFD, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        checkOutCalenderLayout.setVerticalGroup(
            checkOutCalenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtCheckOutDateFD, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(btnCheckOutFD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        CheckAvailabelPanel.setLayer(checkInCalender, javax.swing.JLayeredPane.DEFAULT_LAYER);
        CheckAvailabelPanel.setLayer(checkindate, javax.swing.JLayeredPane.DEFAULT_LAYER);
        CheckAvailabelPanel.setLayer(checkOutdate, javax.swing.JLayeredPane.DEFAULT_LAYER);
        CheckAvailabelPanel.setLayer(checkOutCalender, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout CheckAvailabelPanelLayout = new javax.swing.GroupLayout(CheckAvailabelPanel);
        CheckAvailabelPanel.setLayout(CheckAvailabelPanelLayout);
        CheckAvailabelPanelLayout.setHorizontalGroup(
            CheckAvailabelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CheckAvailabelPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CheckAvailabelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkindate, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkOutdate)
                    .addComponent(checkOutCalender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkInCalender, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        CheckAvailabelPanelLayout.setVerticalGroup(
            CheckAvailabelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CheckAvailabelPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(checkindate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkInCalender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkOutdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkOutCalender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 14, Short.MAX_VALUE))
        );

        calnderpane.setLayer(cbFriday, javax.swing.JLayeredPane.DEFAULT_LAYER);
        calnderpane.setLayer(cbThursday, javax.swing.JLayeredPane.DEFAULT_LAYER);
        calnderpane.setLayer(cbWednesday, javax.swing.JLayeredPane.DEFAULT_LAYER);
        calnderpane.setLayer(cbSaterday, javax.swing.JLayeredPane.DEFAULT_LAYER);
        calnderpane.setLayer(cbTuesday, javax.swing.JLayeredPane.DEFAULT_LAYER);
        calnderpane.setLayer(cbMonday, javax.swing.JLayeredPane.DEFAULT_LAYER);
        calnderpane.setLayer(cbSunday, javax.swing.JLayeredPane.DEFAULT_LAYER);
        calnderpane.setLayer(btnCheckAvailability, javax.swing.JLayeredPane.DEFAULT_LAYER);
        calnderpane.setLayer(cmbNumberOfDay, javax.swing.JLayeredPane.DEFAULT_LAYER);
        calnderpane.setLayer(checkindate1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        calnderpane.setLayer(cmbBookType, javax.swing.JLayeredPane.DEFAULT_LAYER);
        calnderpane.setLayer(CheckAvailabelPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout calnderpaneLayout = new javax.swing.GroupLayout(calnderpane);
        calnderpane.setLayout(calnderpaneLayout);
        calnderpaneLayout.setHorizontalGroup(
            calnderpaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(calnderpaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(calnderpaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(calnderpaneLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(cmbBookType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCheckAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(calnderpaneLayout.createSequentialGroup()
                        .addGroup(calnderpaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkindate1)
                            .addComponent(cbThursday, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbWednesday, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbTuesday, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbMonday, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbSunday, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbFriday, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbSaterday, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(calnderpaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(calnderpaneLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(cmbNumberOfDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(149, 149, 149))
                            .addGroup(calnderpaneLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(CheckAvailabelPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(14, Short.MAX_VALUE))))))
        );
        calnderpaneLayout.setVerticalGroup(
            calnderpaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(calnderpaneLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(calnderpaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(calnderpaneLayout.createSequentialGroup()
                        .addComponent(cbSunday)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbMonday)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbTuesday)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbWednesday)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbThursday)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbFriday)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbSaterday)
                        .addGap(72, 72, 72))
                    .addGroup(calnderpaneLayout.createSequentialGroup()
                        .addComponent(CheckAvailabelPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(calnderpaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbNumberOfDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkindate1))
                        .addGap(12, 12, 12)))
                .addGroup(calnderpaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbBookType, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(btnCheckAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Select Your Prefered Match");

        HallType.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        lbHalltype.setBackground(new java.awt.Color(255, 255, 255));
        lbHalltype.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbHalltype.setForeground(new java.awt.Color(255, 255, 255));
        lbHalltype.setText("Halls");

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Maximum Capacity");

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText(" (Per Person)");

        btnSelectLuxuryHall.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/abc/view/icon/building (4).png"))); // NOI18N
        btnSelectLuxuryHall.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        btnSelectLuxuryHall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectLuxuryHallActionPerformed(evt);
            }
        });

        jLabel22.setBackground(new java.awt.Color(204, 204, 204));
        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel22.setText("Terms and Conditions apply.");

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Price Per Day ");

        jLabel27.setBackground(new java.awt.Color(255, 255, 255));
        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Hall ID");

        jLabel28.setBackground(new java.awt.Color(255, 255, 255));
        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Air Condithion");

        HallType.setLayer(lbHalltype, javax.swing.JLayeredPane.DEFAULT_LAYER);
        HallType.setLayer(jLabel20, javax.swing.JLayeredPane.DEFAULT_LAYER);
        HallType.setLayer(jLabel23, javax.swing.JLayeredPane.DEFAULT_LAYER);
        HallType.setLayer(jLabel24, javax.swing.JLayeredPane.DEFAULT_LAYER);
        HallType.setLayer(btnSelectLuxuryHall, javax.swing.JLayeredPane.DEFAULT_LAYER);
        HallType.setLayer(jLabel22, javax.swing.JLayeredPane.DEFAULT_LAYER);
        HallType.setLayer(jLabel21, javax.swing.JLayeredPane.DEFAULT_LAYER);
        HallType.setLayer(txtHallId, javax.swing.JLayeredPane.DEFAULT_LAYER);
        HallType.setLayer(txtCap, javax.swing.JLayeredPane.DEFAULT_LAYER);
        HallType.setLayer(txtPrice, javax.swing.JLayeredPane.DEFAULT_LAYER);
        HallType.setLayer(txtAc, javax.swing.JLayeredPane.DEFAULT_LAYER);
        HallType.setLayer(jLabel27, javax.swing.JLayeredPane.DEFAULT_LAYER);
        HallType.setLayer(jLabel28, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout HallTypeLayout = new javax.swing.GroupLayout(HallType);
        HallType.setLayout(HallTypeLayout);
        HallTypeLayout.setHorizontalGroup(
            HallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HallTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(HallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HallTypeLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(HallTypeLayout.createSequentialGroup()
                        .addGroup(HallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(HallTypeLayout.createSequentialGroup()
                                .addGroup(HallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HallTypeLayout.createSequentialGroup()
                                        .addGroup(HallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(HallTypeLayout.createSequentialGroup()
                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(39, 39, 39)))
                                .addGroup(HallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtHallId, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel22)
                            .addComponent(lbHalltype, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(HallTypeLayout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCap, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(HallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSelectLuxuryHall, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HallTypeLayout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAc, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        HallTypeLayout.setVerticalGroup(
            HallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HallTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(HallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSelectLuxuryHall, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(HallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(HallTypeLayout.createSequentialGroup()
                            .addComponent(lbHalltype, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(HallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(HallTypeLayout.createSequentialGroup()
                                    .addGroup(HallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(22, 22, 22))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HallTypeLayout.createSequentialGroup()
                                    .addComponent(jLabel24)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                            .addGroup(HallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel27)
                                .addComponent(txtHallId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, 0)
                            .addGroup(HallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addGap(313, 313, 313)
                .addComponent(jLabel23)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cmbHtype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Standard Hall", "Benquet Hall", "Luxury Hall" }));
        cmbHtype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbHtypeActionPerformed(evt);
            }
        });

        halltable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Hall Type", "Hall ID", "Hall Cap", "Price Per Day", "AC type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        halltable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                halltableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(halltable);

        btnAddBook.setText("ADD Book");
        btnAddBook.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnAddBook.setkBorderRadius(65);
        btnAddBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddBookActionPerformed(evt);
            }
        });

        btnUpdateBook.setText("Update BOOK");
        btnUpdateBook.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnUpdateBook.setkBorderRadius(65);
        btnUpdateBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateBookActionPerformed(evt);
            }
        });

        btnDeleteBook.setText("Delete Book");
        btnDeleteBook.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnDeleteBook.setkBorderRadius(65);
        btnDeleteBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteBookActionPerformed(evt);
            }
        });

        btnUserSetings.setText("User Setings");
        btnUserSetings.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnUserSetings.setkBorderRadius(20);
        btnUserSetings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserSetingsActionPerformed(evt);
            }
        });

        btnHallSetings.setText("Hall Setings");
        btnHallSetings.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnHallSetings.setkBorderRadius(20);
        btnHallSetings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHallSetingsActionPerformed(evt);
            }
        });

        btnCustomerDetails.setText("Customer details");
        btnCustomerDetails.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnCustomerDetails.setkBorderRadius(20);
        btnCustomerDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCustomerDetailsActionPerformed(evt);
            }
        });

        jDataTableFD.setBackground(new java.awt.Color(204, 204, 204));
        jDataTableFD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Booking NO", "BookType", "HallType", "Hall ID", "Customer NIC", "Name", "Telephone Number", "Spesific Day", "Check IN DATE", "Check OUT Date", "Number Of Day"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jDataTableFD);

        javax.swing.GroupLayout gradientBG2Layout = new javax.swing.GroupLayout(gradientBG2);
        gradientBG2.setLayout(gradientBG2Layout);
        gradientBG2Layout.setHorizontalGroup(
            gradientBG2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradientBG2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(gradientBG2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gradientBG2Layout.createSequentialGroup()
                        .addGroup(gradientBG2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradientBG2Layout.createSequentialGroup()
                                .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(gradientBG2Layout.createSequentialGroup()
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(gradientBG2Layout.createSequentialGroup()
                                .addGroup(gradientBG2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbHtype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(HallType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(gradientBG2Layout.createSequentialGroup()
                                        .addComponent(btnAddBook, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnUpdateBook, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnDeleteBook, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(calnderpane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(36, 36, 36)
                        .addGroup(gradientBG2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(gradientBG2Layout.createSequentialGroup()
                                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addGroup(gradientBG2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(gradientBG2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnUpdateCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnAddCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnDeleteCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(gradientBG2Layout.createSequentialGroup()
                                .addComponent(btnUserSetings, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnHallSetings, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCustomerDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1245, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(BookPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gradientBG2Layout.setVerticalGroup(
            gradientBG2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradientBG2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(gradientBG2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gradientBG2Layout.createSequentialGroup()
                        .addGroup(gradientBG2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(gradientBG2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(gradientBG2Layout.createSequentialGroup()
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradientBG2Layout.createSequentialGroup()
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(gradientBG2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(gradientBG2Layout.createSequentialGroup()
                                .addComponent(HallType, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbHtype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(gradientBG2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnAddBook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnUpdateBook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnDeleteBook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(calnderpane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(gradientBG2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnAddCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(btnUpdateCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(btnDeleteCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(gradientBG2Layout.createSequentialGroup()
                        .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(gradientBG2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnUserSetings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHallSetings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCustomerDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(gradientBG2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gradientBG2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BookPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradientBG2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gradientBG2, javax.swing.GroupLayout.PREFERRED_SIZE, 1310, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(gradientBG2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNicAMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNicAMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNicAMActionPerformed

    private void txtNameAMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameAMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameAMActionPerformed

    private void txtTeleAMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTeleAMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTeleAMActionPerformed

    private void txtEmailAMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailAMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailAMActionPerformed

    private void btnCheckAvailabilityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckAvailabilityActionPerformed

        if (checkIndateAM == null && checkOutdateAM == null) {

            JOptionPane.showMessageDialog(null, "Check in date or Check Out date empty");

        } else {
            String id = txtHallId.getText();

            if (cmbBookType.getModel().getSelectedItem().toString().equals("A Specific Day")) {

                UserControler uc = new UserControler();
                AdminControler ac = new AdminControler();

                //String binaryString = Integer.toBinaryString(updateSelectedDays());
                List<Booking> book = uc.checkAvailabality(checkIndateAM, checkOutdateAM, ac.searchHall(id));

                if (book.isEmpty()) {

                    JOptionPane.showMessageDialog(null, "This hall avilable this given Specific Day period ");

                } else {
                    for (Booking k : book) {

                        if (k.getBookingType().equals("A Continues Period")) {

                            int integerValue = Integer.parseInt(k.getSpecificDay(), 2);

                            if ((updateSelectedDays() & integerValue) >= updateSelectedDays()) {

                                JOptionPane.showMessageDialog(null, "This hall not avilable this given Specific Day period C");

                            } else {

                                JOptionPane.showMessageDialog(null, "This hall avilable this given Specific Day period C");

                            }

                        }

                        if (k.getBookingType().equals("A Specific Day")) {

                            int integerValue = Integer.parseInt(k.getSpecificDay(), 2);

                            if ((updateSelectedDays() & integerValue) >= updateSelectedDays()) {

                                JOptionPane.showMessageDialog(null, "This hall not avilable this given Specific Day period S");

                            } else {

                                JOptionPane.showMessageDialog(null, "This hall avilable this given  Specific Day period S");

                            }

                        }
                        if (k.getBookingType().equals("A Given Date")) {

                            int integerValue = Integer.parseInt(k.getSpecificDay(), 2);

                            if ((updateSelectedDays() & integerValue) >= updateSelectedDays()) {

                                JOptionPane.showMessageDialog(null, "This hall not avilable this given Specific Day period G");

                            } else {

                                JOptionPane.showMessageDialog(null, "This hall avilable this given Specific Day period G");

                            }

                        }

                    }
                }

            } else if (cmbBookType.getModel().getSelectedItem().toString().equals("A Continues Period")) {

                UserControler uc = new UserControler();
                AdminControler ac = new AdminControler();
                List<Booking> book = uc.checkAvailabality(checkIndateAM, checkOutdateAM, ac.searchHall(id));

                if (book.isEmpty()) {

                    JOptionPane.showMessageDialog(null, "This hall avilable this given Continues period book is null");

                } else {
                    for (Booking k : book) {

                        if (k.getBookingType().equals("A Continues Period")) {

                            JOptionPane.showMessageDialog(null, "This hall not avilable this given Continues period C");

                        }

                        if (k.getBookingType().equals("A Specific Day")) {

                            int integerValue = Integer.parseInt(k.getSpecificDay(), 2);

                            if ((updateSelectedDays() & integerValue) >= updateSelectedDays()) {

                                JOptionPane.showMessageDialog(null, "This hall not avilable this given Continues period S");

                            } else {

                                JOptionPane.showMessageDialog(null, "This hall avilable this given Continues period S");

                            }

                        }
                        if (k.getBookingType().equals("A Given Date")) {

                            JOptionPane.showMessageDialog(null, "This hall not avilable this given Continues period G");

                        }

                    }
                }

            } else {

                UserControler uc = new UserControler();
                AdminControler ac = new AdminControler();
                List<Booking> book = uc.checkAvailabality(checkIndateAM, checkOutdateAM, ac.searchHall(id));

                if (book.isEmpty()) {

                    JOptionPane.showMessageDialog(null, "This hall avilable this given day book is null");

                } else {
                    for (Booking k : book) {

                        if (k.getBookingType().equals("A Continues Period")) {

                            JOptionPane.showMessageDialog(null, "This hall not avilable this given day C");

                        }

                        if (k.getBookingType().equals("A Specific Day")) {

                            int integerValue = Integer.parseInt(k.getSpecificDay(), 2);

                            if ((updateSelectedDays() & integerValue) >= updateSelectedDays()) {

                                JOptionPane.showMessageDialog(null, "This hall not avilable this given day S");

                            } else {

                                JOptionPane.showMessageDialog(null, "This hall avilable this given day S");

                            }

                        }
                        if (k.getBookingType().equals("A Given Date")) {

                            JOptionPane.showMessageDialog(null, "This hall not avilable this given day G");

                        }

                    }

                }
            }
        }
    }//GEN-LAST:event_btnCheckAvailabilityActionPerformed

    String checkIndateAM;
    String checkOutdateAM;

    private void btnCheckInFDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckInFDActionPerformed
        DatePicker checkIn = new DatePicker(this);
        txtCheckInDateFD.setText(checkIn.setPickedDate());
        checkIndateAM = checkIn.setPickedDate();
    }//GEN-LAST:event_btnCheckInFDActionPerformed

    private void btnCheckOutFDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckOutFDActionPerformed
        DatePicker checkIn = new DatePicker(this);
        txtCheckOutDateFD.setText(checkIn.setPickedDate());
        checkOutdateAM = checkIn.setPickedDate();

        /* SimpleDateFormat date=new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);
        
        try{
        Date checkInD= date.parse(checkIndateAM);
        Date checkOutD= date.parse(checkOutdateAM);
        
        long difInMilles = Math.abs(checkInD.getTime()-checkOutD.getTime());
        long dif=TimeUnit.DAYS.convert(difInMilles, TimeUnit.MILLISECONDS);
        txtNumberOfDates.setText(String.valueOf(dif));
        
        
        }catch(Exception ex){
        
        ex.printStackTrace();
        }*/
    }//GEN-LAST:event_btnCheckOutFDActionPerformed

    private void btnSelectLuxuryHallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectLuxuryHallActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSelectLuxuryHallActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void btnAddCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCustomerActionPerformed

        if (validateSubmit()) {
            String nic = txtNicAM.getText();
            UserControler uc = new UserControler();

            if (uc.serachCustomerNic(nic)) {
                String custname = txtNameAM.getText();

                String telephone = txtTeleAM.getText();
                String custEmail = txtEmailAM.getText();

                Customer cust = new Customer();

                cust.setCustomerId(nic);
                cust.setName(custname);
                cust.setTelephoneNumber(telephone);
                cust.setGmail(custEmail);
                cust.setUser(Login.getLoginInstance().getUser());
                cust.setCreateDate("C " + showDateTime());

                uc.addCustomer(cust);

                DataTableForBookingDetails();

                txtNameAM.setText("");
                txtNicAM.setText("");
                txtTeleAM.setText("");
                txtEmailAM.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(null, "input fields error");
        }
    }//GEN-LAST:event_btnAddCustomerActionPerformed

    private void btnUpdateCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateCustomerActionPerformed

        if (validateSubmit()) {
            String custname = txtNameAM.getText();
            String nic = txtNicAM.getText();
            String telephone = txtTeleAM.getText();
            String custEmail = txtEmailAM.getText();

            Customer cust = new Customer();

            cust.setCustomerId(nic);
            cust.setName(custname);
            cust.setTelephoneNumber(telephone);
            cust.setGmail(custEmail);
            cust.setUser(Login.getLoginInstance().getUser());
            cust.setCreateDate("U " + Login.getLoginInstance().getUser().getUserType() + " " + showDateTime());

            UserControler uc = new UserControler();
            uc.updateCustomer(cust);

            DataTableForBookingDetails();

            txtNameAM.setText("");
            txtNicAM.setText("");
            txtTeleAM.setText("");
            txtEmailAM.setText("");

        } else {
            JOptionPane.showMessageDialog(null, "input fields error");
        }


    }//GEN-LAST:event_btnUpdateCustomerActionPerformed
    String search;
    private void btnDeleteCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCustomerActionPerformed

        if (validateSubmit()) {

            Customer cust = new Customer();
            cust.setCustomerId(search);
            UserControler uc = new UserControler();
            uc.deleteCustomer(cust);

            DataTableForBookingDetails();

            txtNameAM.setText("");
            txtNicAM.setText("");
            txtTeleAM.setText("");
            txtEmailAM.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Enter Customer NIC to search and delete");
        }
    }//GEN-LAST:event_btnDeleteCustomerActionPerformed


    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

        if (validateSearch()) {
            int searchBooking = Integer.parseInt(txtSearch.getText());
            UserControler uc = new UserControler();
            Booking book = uc.BookingSearch(searchBooking);
            if (searchBooking == book.getBookingId()) {

                txtNameAM.setText(book.getCustomer().getName());
                txtNicAM.setText(book.getCustomer().getCustomerId());
                txtTeleAM.setText(book.getCustomer().getTelephoneNumber());
                txtEmailAM.setText(book.getCustomer().getGmail());
                txtHallId.setText(book.getHall().getHallId());
                txtCap.setText(book.getHall().getHallCap());
                txtPrice.setText(Double.toString(book.getHall().getPricePerDay()));
                txtAc.setText(book.getHall().getAcType());
                txtCheckInDateFD.setText(book.getCheckIn());
                checkIndateAM = book.getCheckIn();
                txtCheckOutDateFD.setText(book.getCheckOut());
                checkOutdateAM = book.getCheckOut();
                cmbBookType.setSelectedItem(book.getBookingType());
                cmbNumberOfDay.setSelectedItem(book.getNumberOfDay());

                String binaryString = book.getSpecificDay();
                if (binaryString != null && !binaryString.isEmpty()) {
                    try {
                        selectedDays = Integer.parseInt(binaryString, 2);
                        updateCheckboxes();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Invalid binary string format");
                    }
                }

            } else {

                JOptionPane.showMessageDialog(null, "Enter Customer NIC is invalid");
                txtSearch.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Enter Customer NIC to search ");
        }


    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnUserSetingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserSetingsActionPerformed

        UserAddingForm uaf = new UserAddingForm();
        uaf.setVisible(true);


    }//GEN-LAST:event_btnUserSetingsActionPerformed

    private void btnHallSetingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHallSetingsActionPerformed
        HallSetingsAdmin hsa = new HallSetingsAdmin();
        hsa.setVisible(true);
    }//GEN-LAST:event_btnHallSetingsActionPerformed

    private void cmbBookTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBookTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbBookTypeActionPerformed

    private void cbFridayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFridayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbFridayActionPerformed

    private void halltableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_halltableMouseClicked

        int row = halltable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) halltable.getModel();

        lbHalltype.setText(model.getValueAt(row, 0).toString());
        txtHallId.setText(model.getValueAt(row, 1).toString());
        txtCap.setText(model.getValueAt(row, 2).toString());
        txtPrice.setText(model.getValueAt(row, 3).toString());
        txtAc.setText(model.getValueAt(row, 4).toString());


    }//GEN-LAST:event_halltableMouseClicked

    private void cmbHtypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbHtypeActionPerformed

        String value = cmbHtype.getModel().getSelectedItem().toString();

        DataTableHallbyHallType(value);

    }//GEN-LAST:event_cmbHtypeActionPerformed

    public void DataTableHallbyHallType(String hType) {

        UserControler Cc = new UserControler();
        List<Hall> list = Cc.selectHallbyHallType(hType);
        DefaultTableModel DFT = (DefaultTableModel) halltable.getModel();
        DFT.setRowCount(0);
        for (Hall k : list) {

            String htype = k.getHallType();
            String id = k.getHallId();
            String hallCap = k.getHallCap();
            double pricePerDay = k.getPricePerDay();
            String actype = k.getAcType();
            DFT.addRow(new Object[]{htype, id, hallCap, pricePerDay, actype});

        }
    }
    private void btnCustomerVerifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCustomerVerifyActionPerformed

        if (txtNicAM.getText() != null) {
            String value = txtNicAM.getText();
            UserControler uc = new UserControler();
            Customer cust = uc.customerSearch(value);
            if (!cust.getCustomerId().equals(value)) {

                JOptionPane.showMessageDialog(null, "Customer NIC is not registerd,please register  ");
                txtNicAM.requestFocus();
            } else {

                txtNameAM.setText(cust.getName());
                txtNicAM.setText(cust.getCustomerId());
                txtTeleAM.setText(cust.getTelephoneNumber());
                txtEmailAM.setText(cust.getGmail());
                JOptionPane.showMessageDialog(null, "This Customer NIC  is alredy Registerd");

            }
        }else{
        JOptionPane.showMessageDialog(null, " Customer NIC is required  ");

        }


    }//GEN-LAST:event_btnCustomerVerifyActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed

        WindowEvent closewindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closewindow);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        txtNameAM.setText("");
        txtNicAM.setText("");
        txtTeleAM.setText("");
        txtEmailAM.setText("");
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnUpdateBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateBookActionPerformed

        if (checkIndateAM == null && checkOutdateAM == null) {

            JOptionPane.showMessageDialog(null, "Check in date or Check Out date empty");

        } else {

            String id = txtHallId.getText();
            String custNic = txtNicAM.getText();
            String bookingType = cmbBookType.getModel().getSelectedItem().toString();

            if (cmbBookType.getModel().getSelectedItem().toString().equals("A Specific Day")) {

                UserControler uc = new UserControler();
                AdminControler ac = new AdminControler();

                updateSelectedDays();
                String binaryString = Integer.toBinaryString(selectedDays);
                int numberOfDays = Integer.parseInt(cmbNumberOfDay.getModel().getSelectedItem().toString());

                Customer cust = uc.serachCustomerObjbyNic(custNic);
                Hall hall = ac.searchHallForBooking(id);
                User user = uc.searchUserObjForBooking(Login.getLoginInstance().getUser().getUserId());
                Booking book = new Booking();

                book.setHall(hall);
                book.setCustomer(cust);
                book.setUser(user);
                book.setCheckIn(checkIndateAM);
                book.setCheckOut(checkOutdateAM);
                book.setSpecificDay(binaryString);
                book.setBookingType(bookingType);
                book.setNumberOfDay(numberOfDays);

                uc.updateBooking(book);

                DataTableForBookingDetails();
                txtNameAM.setText("");
                txtNicAM.setText("");
                txtTeleAM.setText("");
                txtEmailAM.setText("");

            } else if (cmbBookType.getModel().getSelectedItem().toString().equals("A Given Date")) {

                if (cmbNumberOfDay.getModel().getSelectedItem().toString().equals("1")) {

                    UserControler uc = new UserControler();
                    AdminControler ac = new AdminControler();

                    updateSelectedDays();
                    String binaryString = Integer.toBinaryString(selectedDays);

                    int numberOfDays = Integer.parseInt(cmbNumberOfDay.getModel().getSelectedItem().toString());

                    Customer cust = uc.serachCustomerObjbyNic(custNic);
                    Hall hall = ac.searchHallForBooking(id);
                    User user = uc.searchUserObjForBooking(Login.getLoginInstance().getUser().getUserId());
                    Booking book = new Booking();

                    if (checkIndateAM == checkOutdateAM) {
                        book.setHall(hall);
                        book.setCustomer(cust);
                        book.setUser(user);
                        book.setCheckIn(checkIndateAM);
                        book.setCheckOut(checkOutdateAM);
                        book.setSpecificDay(binaryString);
                        book.setBookingType(bookingType);
                        book.setNumberOfDay(numberOfDays);

                        uc.addBooking(book);
                        DataTableForBookingDetails();
                        txtNameAM.setText("");
                        txtNicAM.setText("");
                        txtTeleAM.setText("");
                        txtEmailAM.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Check in date and Check out date must be same day");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Number of Days must be 1");
                }

            } else {
                UserControler uc = new UserControler();
                AdminControler ac = new AdminControler();

                updateSelectedDays();
                String binaryString = Integer.toBinaryString(selectedDays);

                int numberOfDays = Integer.parseInt(cmbNumberOfDay.getModel().getSelectedItem().toString());

                Customer cust = uc.serachCustomerObjbyNic(custNic);
                Hall hall = ac.searchHallForBooking(id);
                User user = uc.searchUserObjForBooking(Login.getLoginInstance().getUser().getUserId());
                Booking book = new Booking();

                book.setHall(hall);
                book.setCustomer(cust);
                book.setUser(user);
                book.setCheckIn(checkIndateAM);
                book.setCheckOut(checkOutdateAM);
                book.setSpecificDay(binaryString);
                book.setBookingType(bookingType);
                book.setNumberOfDay(numberOfDays);

                uc.updateBooking(book);

                DataTableForBookingDetails();
                txtNameAM.setText("");
                txtNicAM.setText("");
                txtTeleAM.setText("");
                txtEmailAM.setText("");
            }
        }
    }//GEN-LAST:event_btnUpdateBookActionPerformed

    private void btnDeleteBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteBookActionPerformed

        if (validateSubmit()) {

            UserControler ac = new UserControler();

            Booking book = new Booking();
            book.setBookingId(Integer.parseInt(txtSearch.getText()));

            ac.deleteBooking(book);
            DataTableForBookingDetails();
            txtNameAM.setText("");
            txtNicAM.setText("");
            txtTeleAM.setText("");
            txtEmailAM.setText("");

        } else {
            JOptionPane.showMessageDialog(null, "Enter User id to search and delete");
        }


    }//GEN-LAST:event_btnDeleteBookActionPerformed

    private void btnAddBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddBookActionPerformed

        if (checkIndateAM == null && checkOutdateAM == null) {

            JOptionPane.showMessageDialog(null, "Check in date or Check Out date empty");

        } else {

            String id = txtHallId.getText();
            String custNic = txtNicAM.getText();
            String bookingType = cmbBookType.getModel().getSelectedItem().toString();

            UserControler uc = new UserControler();
            AdminControler ac = new AdminControler();

            if (id == null || custNic == null) {

                JOptionPane.showMessageDialog(null, "Hall Id or Customer Nic is empty,please enter");

            } else {

                if (cmbBookType.getModel().getSelectedItem().toString().equals("A Specific Day")) {

                    String binaryString = Integer.toBinaryString(updateSelectedDays());

                    int numberOfDays = Integer.parseInt(cmbNumberOfDay.getModel().getSelectedItem().toString());

                    Customer cust = uc.serachCustomerObjbyNic(custNic);
                    Hall hall = ac.searchHallForBooking(id);
                    User user = uc.searchUserObjForBooking(Login.getLoginInstance().getUser().getUserId());
                    Booking book = new Booking();

                    book.setHall(hall);
                    book.setCustomer(cust);
                    book.setUser(user);
                    book.setCheckIn(checkIndateAM);
                    book.setCheckOut(checkOutdateAM);
                    book.setSpecificDay(binaryString);
                    book.setBookingType(bookingType);
                    book.setNumberOfDay(numberOfDays);

                    PaymentForm pf = new PaymentForm();
                    double amount = pf.calcPayment(numberOfDays, hall.getPricePerDay());
                    pf.paymentBill(book, amount);
                    pf.addBookingafterPayment(book);
                    this.setVisible(false);
                    pf.setVisible(true);

                    txtNameAM.setText("");
                    txtNicAM.setText("");
                    txtTeleAM.setText("");
                    txtEmailAM.setText("");

                } else if (cmbBookType.getModel().getSelectedItem().toString().equals("A Given Date")) {

                    if (cmbNumberOfDay.getModel().getSelectedItem().toString().equals("1")) {

                        String binaryString = Integer.toBinaryString(updateSelectedDays());

                        int numberOfDays = Integer.parseInt(cmbNumberOfDay.getModel().getSelectedItem().toString());

                        Customer cust = uc.serachCustomerObjbyNic(custNic);

                        Hall hall = ac.searchHallForBooking(id);
                        User user = uc.searchUserObjForBooking(Login.getLoginInstance().getUser().getUserId());
                        Booking book = new Booking();

                        if (checkIndateAM.equals(checkOutdateAM)) {
                            book.setHall(hall);
                            book.setCustomer(cust);
                            book.setUser(user);
                            book.setCheckIn(checkIndateAM);
                            book.setCheckOut(checkOutdateAM);
                            book.setSpecificDay(binaryString);
                            book.setBookingType(bookingType);
                            book.setNumberOfDay(numberOfDays);

                            PaymentForm pf = new PaymentForm();
                            double amount = pf.calcPayment(numberOfDays, hall.getPricePerDay());
                            pf.paymentBill(book, amount);
                            pf.addBookingafterPayment(book);
                            this.setVisible(false);
                            pf.setVisible(true);

                            txtNameAM.setText("");
                            txtNicAM.setText("");
                            txtTeleAM.setText("");
                            txtEmailAM.setText("");
                        } else {
                            JOptionPane.showMessageDialog(null, "Check in date and Check out date must be same day");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Number of Days must be 1");
                    }

                } else {

                    String binaryString = Integer.toBinaryString(updateSelectedDays());
                    int numberOfDays = Integer.parseInt(cmbNumberOfDay.getModel().getSelectedItem().toString());

                    Customer cust = uc.serachCustomerObjbyNic(custNic);
                    Hall hall = ac.searchHallForBooking(id);
                    User user = uc.searchUserObjForBooking(Login.getLoginInstance().getUser().getUserId());
                    Booking book = new Booking();

                    book.setHall(hall);
                    book.setCustomer(cust);
                    book.setUser(user);
                    book.setCheckIn(checkIndateAM);
                    book.setCheckOut(checkOutdateAM);
                    book.setSpecificDay(binaryString);
                    book.setBookingType(bookingType);
                    book.setNumberOfDay(numberOfDays);

                    PaymentForm pf = new PaymentForm();
                    double amount = pf.calcPayment(numberOfDays, hall.getPricePerDay());
                    pf.paymentBill(book, amount);
                    pf.addBookingafterPayment(book);
                    this.setVisible(false);
                    pf.setVisible(true);
                    txtNameAM.setText("");
                    txtNicAM.setText("");
                    txtTeleAM.setText("");
                    txtEmailAM.setText("");
                }
            }

        }
    }//GEN-LAST:event_btnAddBookActionPerformed

    private void btnCustomerDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCustomerDetailsActionPerformed
        CustomerDetails hsa = new CustomerDetails();
        hsa.setVisible(true);
    }//GEN-LAST:event_btnCustomerDetailsActionPerformed

    public void DataTableForBookingDetails() {

        UserControler Cc = new UserControler();
        List<Booking> list = Cc.listBooking();
        DefaultTableModel DFT = (DefaultTableModel) jDataTableFD.getModel();
        DFT.setRowCount(0);
        for (Booking k : list) {

            int bookId = k.getBookingId();
            String bookType = k.getBookingType();
            String hallType = k.getHall().getHallType();
            String hallId = k.getHall().getHallId();
            String customerNic = k.getCustomer().getCustomerId();
            String name = k.getCustomer().getName();
            String telenumber = k.getCustomer().getTelephoneNumber();
            String spesificDay = k.getSpecificDay();
            String checkInDate = k.getCheckIn();
            String checkOutDate = k.getCheckOut();
            int numberOfDate = k.getNumberOfDay();

            DFT.addRow(new Object[]{bookId, bookType, hallType, hallId, customerNic, name, telenumber, spesificDay, checkInDate, checkOutDate, numberOfDate});

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminMainUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminMainUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminMainUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminMainUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AdminMainUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane BookPanel;
    private javax.swing.JLayeredPane CheckAvailabelPanel;
    private javax.swing.JLayeredPane HallType;
    private com.abc.view.swing_componont.JButtonExtend1 btnAddBook;
    private com.abc.view.swing_componont.ButtonOutLine btnAddCustomer;
    private com.abc.view.swing_componont.JButtonExtend1 btnCheckAvailability;
    private com.abc.view.swing_componont.ButtonOutLine btnCheckInFD;
    private com.abc.view.swing_componont.ButtonOutLine btnCheckOutFD;
    private com.abc.view.swing_componont.JButtonExtend1 btnClear;
    private com.abc.view.swing_componont.JButtonExtend1 btnCustomerDetails;
    private com.abc.view.swing_componont.JButtonExtend1 btnCustomerVerify;
    private com.abc.view.swing_componont.JButtonExtend1 btnDeleteBook;
    private com.abc.view.swing_componont.ButtonOutLine btnDeleteCustomer;
    private com.abc.view.swing_componont.JButtonExtend1 btnExit;
    private com.abc.view.swing_componont.JButtonExtend1 btnHallSetings;
    private com.abc.view.swing_componont.JButtonExtend1 btnSearch;
    private com.abc.view.swing_componont.ButtonOutLine btnSelectLuxuryHall;
    private com.abc.view.swing_componont.JButtonExtend1 btnUpdateBook;
    private com.abc.view.swing_componont.ButtonOutLine btnUpdateCustomer;
    private com.abc.view.swing_componont.JButtonExtend1 btnUserSetings;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JLayeredPane calnderpane;
    private javax.swing.JCheckBox cbFriday;
    private javax.swing.JCheckBox cbMonday;
    private javax.swing.JCheckBox cbSaterday;
    private javax.swing.JCheckBox cbSunday;
    private javax.swing.JCheckBox cbThursday;
    private javax.swing.JCheckBox cbTuesday;
    private javax.swing.JCheckBox cbWednesday;
    private javax.swing.JLayeredPane checkInCalender;
    private javax.swing.JLayeredPane checkOutCalender;
    private javax.swing.JLabel checkOutdate;
    private javax.swing.JLabel checkindate;
    private javax.swing.JLabel checkindate1;
    private javax.swing.JComboBox<String> cmbBookType;
    private javax.swing.JComboBox<String> cmbHtype;
    private javax.swing.JComboBox<String> cmbNumberOfDay;
    private com.abc.view.componont.GradientBG gradientBG2;
    private javax.swing.JTable halltable;
    private javax.swing.JTable jDataTableFD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbHalltype;
    private javax.swing.JTextField txtAc;
    private javax.swing.JTextField txtCap;
    private javax.swing.JLabel txtCheckInDateFD;
    private javax.swing.JLabel txtCheckOutDateFD;
    private javax.swing.JTextField txtEmailAM;
    private javax.swing.JTextField txtHallId;
    private javax.swing.JTextField txtNameAM;
    private javax.swing.JTextField txtNicAM;
    private javax.swing.JTextField txtPrice;
    private com.abc.view.swing_componont.MyTextField txtSearch;
    private javax.swing.JTextField txtTeleAM;
    // End of variables declaration//GEN-END:variables
}
