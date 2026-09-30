package com.abc.view.componont;

import com.abc.controller.AdminControler;
import com.abc.controller.CustomerControler;
import com.abc.controller.UserControler;
import com.abc.database.hallDb;
import com.abc.model.Booking;
import com.abc.model.Hall;
import com.abc.model.Login;
import com.abc.model.User;
import com.abc.model.UserType;

import com.abc.view.main.AdminMainUser;
import com.abc.view.main.CustomerUserView;
import com.abc.view.main.HallSetingsAdmin;
import com.abc.view.main.FrontDeskUser;
import com.abc.view.main.FrontDeskUser;
import com.abc.view.main.MainLoginForm;
import com.abc.view.main.UserAddingForm;
import com.abc.view.swing_componont.Button;
import com.abc.view.swing_componont.MyPasswordField;
import com.abc.view.swing_componont.MyTextField;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

public class PanelLoginAndRegister extends javax.swing.JLayeredPane {

    
    private JCheckBox[] checkboxes;
    private int selectedDays;
    
    
    public PanelLoginAndRegister() {
        initComponents();

        initLogin();
        login.setVisible(false);
        customerCheck.setVisible(true);
        showDate();
        DataTableHall();

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
    
    public void showDate() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd|MM|yyyy");
        Date d = new Date();
        txtCheckInDateCV.setText(sdf.format(d));
        txtCheckOutDateCV.setText(sdf.format(d));

    }

    public String showDateTime() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd|MM|yyyy  HH:mm:ss");
        Date d = new Date();
        String date = sdf.format(d);

        return date;
    }

    private void initLogin() {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Sign In");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(28, 51, 170));
        login.add(label);
        MyTextField txtUserid = new MyTextField();
        txtUserid.setPrefixIcon(new ImageIcon(getClass().getResource("/com/abc/view/icon/user.png")));
        txtUserid.setHint("User id");
        login.add(txtUserid, "w 60%");
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/com/abc/view/icon/pass.png")));
        txtPass.setHint("Password");
        login.add(txtPass, "w 60%");
        JButton cmdForget = new JButton("Forgot your password ?");
        cmdForget.setForeground(new Color(100, 100, 100));
        cmdForget.setFont(new Font("sansserif", 1, 12));
        cmdForget.setContentAreaFilled(false);
        cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(cmdForget);
        Button cmd = new Button();
        cmd.setBackground(new Color(28, 51, 170));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("SIGN IN");
        // FrontDeskUser ad=new FrontDeskUser();
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String userName = txtUserid.getText();
                String password = txtPass.getText();

                if (userName.equals("") || password.equals("")) {
                    JOptionPane.showMessageDialog(null, "Usrr ID or password field is empty");
                } else {

                    try {

                        Connection con = hallDb.getConnection();
                        String sql = "SELECT *FROM user WHERE userId=? and password=?";
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setString(1, userName);
                        ps.setString(2, password);
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {

                            String s1 = rs.getString("UserType");
                            if (s1.equalsIgnoreCase("ADMIN")) {

                                User u = new User();
                                u.setId(rs.getInt("user_key"));
                                u.setUserId(rs.getString("userId"));
                                u.setUserType(UserType.ADMIN);
                                Login.getLoginInstance().setUser(u);
                                Login.getLoginInstance().setLoginTime(showDateTime());
                              
                               MainLoginForm ml=new MainLoginForm();
                               PanelLoginAndRegister pr=new PanelLoginAndRegister();
                               
                               pr.setVisible(false);
                               PanelCover pc=new PanelCover();
                               pc.setVisible(false);
                                ml.setVisible(false);
                                AdminMainUser amu = new AdminMainUser();
                                amu.setVisible(true);

                            }
                            if (s1.equalsIgnoreCase("FRONT_DESK_USER")) {

                                User u = new User();
                                u.setId(rs.getInt("user_key"));
                                u.setUserId(rs.getString("userId"));
                                u.setUserType(UserType.FRONT_DESK_USER);
                                Login.getLoginInstance().setUser(u);
                                Login.getLoginInstance().setLoginTime(showDateTime());
                                MainLoginForm ml=new MainLoginForm();
                                ml.setVisible(false);
                                PanelCover pc=new PanelCover();
                               pc.setVisible(false);
                                PanelLoginAndRegister pr=new PanelLoginAndRegister();
                               pr.setVisible(false);
                                FrontDeskUser fd = new FrontDeskUser();
                                fd.setVisible(true);

                            }

                        } else {

                            JOptionPane.showMessageDialog(null, "User id or password is invalid");

                        }

                    } catch (Exception ex) {

                        System.out.println(" " + ex);

                    }

                }

            }
        });

        login.add(cmd, "w 40%, h 40");
    }

    public void showRegister(boolean show) {
        if (show) {
            customerCheck.setVisible(true);
            login.setVisible(false);
        } else {
            customerCheck.setVisible(false);
            login.setVisible(true);
        }
    }
    
    String checkIndateCV;
    String checkOutdateCV;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        checkInCalender3 = new javax.swing.JLayeredPane();
        jLabel13 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btnCheckIn3 = new com.abc.view.swing_componont.ButtonOutLine();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        login = new javax.swing.JPanel();
        customerCheck = new javax.swing.JPanel();
        gradientBG1 = new com.abc.view.componont.GradientBG();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jCalanderPane = new javax.swing.JLayeredPane();
        checkInCalender = new javax.swing.JLayeredPane();
        txtCheckInDateCV = new javax.swing.JLabel();
        btnCheckIn = new com.abc.view.swing_componont.ButtonOutLine();
        checkindate = new javax.swing.JLabel();
        checkOutdate = new javax.swing.JLabel();
        checkInCalender2 = new javax.swing.JLayeredPane();
        txtCheckOutDateCV = new javax.swing.JLabel();
        btnCheckIn2 = new com.abc.view.swing_componont.ButtonOutLine();
        jLabel25 = new javax.swing.JLabel();
        btnCheckAcailable = new com.abc.view.swing_componont.JButtonExtend1();
        btnBookNow = new com.abc.view.swing_componont.JButtonExtend1();
        HallType = new javax.swing.JLayeredPane();
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
        lbHalltype = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        halltable = new javax.swing.JTable();
        cmbHtype = new javax.swing.JComboBox<>();
        cbSunday = new javax.swing.JCheckBox();
        cbMonday = new javax.swing.JCheckBox();
        cbTuesday = new javax.swing.JCheckBox();
        cbWednesday = new javax.swing.JCheckBox();
        cbThursday = new javax.swing.JCheckBox();
        cbFriday = new javax.swing.JCheckBox();
        cbSaterday = new javax.swing.JCheckBox();
        cmbBookType = new javax.swing.JComboBox<>();
        cmbNumberOfDay = new javax.swing.JComboBox<>();
        btnCheckAcailable1 = new com.abc.view.swing_componont.JButtonExtend1();

        checkInCalender3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel13.setText("Standerd Halls");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jRadioButton5.setText("NON A/C");

        jRadioButton6.setText("A/C");
        jRadioButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton6ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setText("Maximum Capacity- 500");

        jLabel15.setText("Price Per Day -50 000/-");

        jLabel16.setText("Terms and Conditions apply.");

        btnCheckIn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/abc/view/icon/building (1).png"))); // NOI18N
        btnCheckIn3.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        btnCheckIn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckIn3ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel18.setText(" (Per Person)");

        checkInCalender3.setLayer(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        checkInCalender3.setLayer(jComboBox3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        checkInCalender3.setLayer(jRadioButton5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        checkInCalender3.setLayer(jRadioButton6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        checkInCalender3.setLayer(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);
        checkInCalender3.setLayer(jLabel15, javax.swing.JLayeredPane.DEFAULT_LAYER);
        checkInCalender3.setLayer(jLabel16, javax.swing.JLayeredPane.DEFAULT_LAYER);
        checkInCalender3.setLayer(btnCheckIn3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        checkInCalender3.setLayer(jLabel17, javax.swing.JLayeredPane.DEFAULT_LAYER);
        checkInCalender3.setLayer(jLabel18, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout checkInCalender3Layout = new javax.swing.GroupLayout(checkInCalender3);
        checkInCalender3.setLayout(checkInCalender3Layout);
        checkInCalender3Layout.setHorizontalGroup(
            checkInCalender3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(checkInCalender3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(checkInCalender3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(checkInCalender3Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(checkInCalender3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(checkInCalender3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(checkInCalender3Layout.createSequentialGroup()
                                .addComponent(jRadioButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton5))))
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCheckIn3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        checkInCalender3Layout.setVerticalGroup(
            checkInCalender3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(checkInCalender3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addGroup(checkInCalender3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(checkInCalender3Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel17))
                    .addGroup(checkInCalender3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(checkInCalender3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton5)
                    .addComponent(jRadioButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addGap(12, 12, 12)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, checkInCalender3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCheckIn3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(194, 217, 214));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 746, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 622, Short.MAX_VALUE)
        );

        add(login, "card3");

        customerCheck.setBackground(new java.awt.Color(194, 217, 214));

        jLayeredPane1.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        checkInCalender.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        txtCheckInDateCV.setBackground(new java.awt.Color(255, 255, 255));
        txtCheckInDateCV.setFont(new java.awt.Font("Playbill", 1, 28)); // NOI18N
        txtCheckInDateCV.setForeground(new java.awt.Color(255, 255, 255));
        txtCheckInDateCV.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtCheckInDateCV.setText("10|02|2023");

        btnCheckIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/abc/view/icon/cursor (3).png"))); // NOI18N
        btnCheckIn.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        btnCheckIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckInActionPerformed(evt);
            }
        });

        checkInCalender.setLayer(txtCheckInDateCV, javax.swing.JLayeredPane.DEFAULT_LAYER);
        checkInCalender.setLayer(btnCheckIn, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout checkInCalenderLayout = new javax.swing.GroupLayout(checkInCalender);
        checkInCalender.setLayout(checkInCalenderLayout);
        checkInCalenderLayout.setHorizontalGroup(
            checkInCalenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(checkInCalenderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtCheckInDateCV)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        checkInCalenderLayout.setVerticalGroup(
            checkInCalenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, checkInCalenderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtCheckInDateCV)
                .addGap(27, 27, 27))
            .addGroup(checkInCalenderLayout.createSequentialGroup()
                .addComponent(btnCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        checkindate.setBackground(new java.awt.Color(255, 255, 255));
        checkindate.setText("Check In Date");

        checkOutdate.setBackground(new java.awt.Color(255, 255, 255));
        checkOutdate.setText("Check Out Date");

        checkInCalender2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        txtCheckOutDateCV.setBackground(new java.awt.Color(255, 255, 255));
        txtCheckOutDateCV.setFont(new java.awt.Font("Playbill", 1, 28)); // NOI18N
        txtCheckOutDateCV.setForeground(new java.awt.Color(255, 255, 255));
        txtCheckOutDateCV.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtCheckOutDateCV.setText("10|02|2023");

        btnCheckIn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/abc/view/icon/cursor (3).png"))); // NOI18N
        btnCheckIn2.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        btnCheckIn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckIn2ActionPerformed(evt);
            }
        });

        checkInCalender2.setLayer(txtCheckOutDateCV, javax.swing.JLayeredPane.DEFAULT_LAYER);
        checkInCalender2.setLayer(btnCheckIn2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout checkInCalender2Layout = new javax.swing.GroupLayout(checkInCalender2);
        checkInCalender2.setLayout(checkInCalender2Layout);
        checkInCalender2Layout.setHorizontalGroup(
            checkInCalender2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(checkInCalender2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtCheckOutDateCV)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCheckIn2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        checkInCalender2Layout.setVerticalGroup(
            checkInCalender2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, checkInCalender2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtCheckOutDateCV)
                .addGap(27, 27, 27))
            .addGroup(checkInCalender2Layout.createSequentialGroup()
                .addComponent(btnCheckIn2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jCalanderPane.setLayer(checkInCalender, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jCalanderPane.setLayer(checkindate, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jCalanderPane.setLayer(checkOutdate, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jCalanderPane.setLayer(checkInCalender2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jCalanderPaneLayout = new javax.swing.GroupLayout(jCalanderPane);
        jCalanderPane.setLayout(jCalanderPaneLayout);
        jCalanderPaneLayout.setHorizontalGroup(
            jCalanderPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jCalanderPaneLayout.createSequentialGroup()
                .addGroup(jCalanderPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkInCalender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkInCalender2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkOutdate, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jCalanderPaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(checkindate, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );
        jCalanderPaneLayout.setVerticalGroup(
            jCalanderPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jCalanderPaneLayout.createSequentialGroup()
                .addComponent(checkindate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkInCalender, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkOutdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkInCalender2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("What Day You Looking for");

        jLayeredPane1.setLayer(jCalanderPane, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel25, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCalanderPane, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCalanderPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnCheckAcailable.setText("Check Available");
        btnCheckAcailable.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCheckAcailable.setkBorderRadius(50);
        btnCheckAcailable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckAcailableActionPerformed(evt);
            }
        });

        btnBookNow.setText("BOOK NOW");
        btnBookNow.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnBookNow.setkBorderRadius(50);
        btnBookNow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookNowActionPerformed(evt);
            }
        });

        HallType.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

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

        lbHalltype.setBackground(new java.awt.Color(255, 255, 255));
        lbHalltype.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbHalltype.setForeground(new java.awt.Color(255, 255, 255));
        lbHalltype.setText("Halls");

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
        HallType.setLayer(lbHalltype, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout HallTypeLayout = new javax.swing.GroupLayout(HallType);
        HallType.setLayout(HallTypeLayout);
        HallTypeLayout.setHorizontalGroup(
            HallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HallTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(HallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HallTypeLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(HallTypeLayout.createSequentialGroup()
                        .addGroup(HallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(HallTypeLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(HallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(HallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtHallId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPrice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel22)
                            .addComponent(lbHalltype, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(HallTypeLayout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCap, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(HallTypeLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(HallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HallTypeLayout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAc, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HallTypeLayout.createSequentialGroup()
                                .addComponent(btnSelectLuxuryHall, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53))))))
        );
        HallTypeLayout.setVerticalGroup(
            HallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HallTypeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(HallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HallTypeLayout.createSequentialGroup()
                        .addComponent(lbHalltype, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(HallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(HallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(txtHallId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(HallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HallTypeLayout.createSequentialGroup()
                        .addComponent(btnSelectLuxuryHall, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addGroup(HallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(txtAc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addGap(313, 313, 313)
                .addComponent(jLabel23))
        );

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

        cmbHtype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Standard Hall", "Benquet Hall", "Luxury Hall" }));
        cmbHtype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbHtypeActionPerformed(evt);
            }
        });

        cbSunday.setText("Sunday");

        cbMonday.setText("Monday");

        cbTuesday.setText("Tuesday");

        cbWednesday.setText("Wednesday");

        cbThursday.setText("Thursday");

        cbFriday.setText("Friday");
        cbFriday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFridayActionPerformed(evt);
            }
        });

        cbSaterday.setText("Saterday");
        cbSaterday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSaterdayActionPerformed(evt);
            }
        });

        cmbBookType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A Given Date", "A Continues Period", "A Specific Day" }));
        cmbBookType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBookTypeActionPerformed(evt);
            }
        });

        cmbNumberOfDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15" }));

        btnCheckAcailable1.setText("Exit");
        btnCheckAcailable1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCheckAcailable1.setkBorderRadius(50);
        btnCheckAcailable1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckAcailable1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout gradientBG1Layout = new javax.swing.GroupLayout(gradientBG1);
        gradientBG1.setLayout(gradientBG1Layout);
        gradientBG1Layout.setHorizontalGroup(
            gradientBG1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradientBG1Layout.createSequentialGroup()
                .addContainerGap(170, Short.MAX_VALUE)
                .addGroup(gradientBG1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(HallType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(gradientBG1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradientBG1Layout.createSequentialGroup()
                            .addGroup(gradientBG1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(gradientBG1Layout.createSequentialGroup()
                                    .addGroup(gradientBG1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnCheckAcailable, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnBookNow, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(gradientBG1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbMonday, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbSunday, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbTuesday, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cmbBookType, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(gradientBG1Layout.createSequentialGroup()
                                    .addComponent(btnCheckAcailable1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(gradientBG1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbThursday, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbWednesday, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(gradientBG1Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addGroup(gradientBG1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(cbFriday, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbSaterday, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradientBG1Layout.createSequentialGroup()
                            .addComponent(cmbHtype, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbNumberOfDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(17, 17, 17))))
                .addGap(17, 17, 17))
        );
        gradientBG1Layout.setVerticalGroup(
            gradientBG1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradientBG1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(HallType, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(gradientBG1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(gradientBG1Layout.createSequentialGroup()
                        .addComponent(btnBookNow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCheckAcailable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCheckAcailable1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(gradientBG1Layout.createSequentialGroup()
                        .addComponent(cmbBookType, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                        .addComponent(cbSaterday)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(gradientBG1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbHtype, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(cmbNumberOfDay))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout customerCheckLayout = new javax.swing.GroupLayout(customerCheck);
        customerCheck.setLayout(customerCheckLayout);
        customerCheckLayout.setHorizontalGroup(
            customerCheckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gradientBG1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        customerCheckLayout.setVerticalGroup(
            customerCheckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gradientBG1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        add(customerCheck, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void btnCheckInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckInActionPerformed
        DatePicker checkIn = new DatePicker(customerCheck);
        txtCheckInDateCV.setText(checkIn.setPickedDate());
        checkIndateCV=checkIn.setPickedDate();
    }//GEN-LAST:event_btnCheckInActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jRadioButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton6ActionPerformed

    private void btnCheckIn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckIn3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCheckIn3ActionPerformed

    private void btnCheckIn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckIn2ActionPerformed
        DatePicker checkOut = new DatePicker(customerCheck);
        txtCheckOutDateCV.setText(checkOut.setPickedDate());
        checkOutdateCV=checkOut.setPickedDate();
    }//GEN-LAST:event_btnCheckIn2ActionPerformed

    private void btnBookNowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookNowActionPerformed

        CustomerUserView cuv = new CustomerUserView();
        cuv.setVisible(true);
    }//GEN-LAST:event_btnBookNowActionPerformed

    private void btnSelectLuxuryHallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectLuxuryHallActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSelectLuxuryHallActionPerformed

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

    private void cbFridayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFridayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbFridayActionPerformed

    private void cmbBookTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBookTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbBookTypeActionPerformed

    private void cbSaterdayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSaterdayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSaterdayActionPerformed

    private void btnCheckAcailable1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckAcailable1ActionPerformed
        MainLoginForm f = new MainLoginForm(); 
        WindowEvent closewindow = new WindowEvent(f, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closewindow);
   
    }//GEN-LAST:event_btnCheckAcailable1ActionPerformed

    private void btnCheckAcailableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckAcailableActionPerformed

        if(checkIndateCV==null&&checkOutdateCV==null){
        
            JOptionPane.showMessageDialog(null, "Check in date or Check Out date empty");

        
        }else{
       String id = txtHallId.getText();

        if (cmbBookType.getModel().getSelectedItem().toString().equals("A Specific Day")) {

            UserControler uc = new UserControler();
            AdminControler ac = new AdminControler();

            //String binaryString = Integer.toBinaryString(updateSelectedDays());
            List<Booking> book = uc.checkAvailabality(checkIndateCV, checkOutdateCV, ac.searchHall(id));

            if (book.isEmpty()) {

                JOptionPane.showMessageDialog(null, "This hall avilable this given Specific Day period book is null");

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
            List<Booking> book = uc.checkAvailabality(checkIndateCV, checkOutdateCV, ac.searchHall(id));

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
            List<Booking> book = uc.checkAvailabality(checkIndateCV, checkOutdateCV, ac.searchHall(id));

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
        
    }//GEN-LAST:event_btnCheckAcailableActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane HallType;
    private com.abc.view.swing_componont.JButtonExtend1 btnBookNow;
    private com.abc.view.swing_componont.JButtonExtend1 btnCheckAcailable;
    private com.abc.view.swing_componont.JButtonExtend1 btnCheckAcailable1;
    private com.abc.view.swing_componont.ButtonOutLine btnCheckIn;
    private com.abc.view.swing_componont.ButtonOutLine btnCheckIn2;
    private com.abc.view.swing_componont.ButtonOutLine btnCheckIn3;
    private com.abc.view.swing_componont.ButtonOutLine btnSelectLuxuryHall;
    private javax.swing.JCheckBox cbFriday;
    private javax.swing.JCheckBox cbMonday;
    private javax.swing.JCheckBox cbSaterday;
    private javax.swing.JCheckBox cbSunday;
    private javax.swing.JCheckBox cbThursday;
    private javax.swing.JCheckBox cbTuesday;
    private javax.swing.JCheckBox cbWednesday;
    private javax.swing.JLayeredPane checkInCalender;
    private javax.swing.JLayeredPane checkInCalender2;
    private javax.swing.JLayeredPane checkInCalender3;
    private javax.swing.JLabel checkOutdate;
    private javax.swing.JLabel checkindate;
    private javax.swing.JComboBox<String> cmbBookType;
    private javax.swing.JComboBox<String> cmbHtype;
    private javax.swing.JComboBox<String> cmbNumberOfDay;
    private javax.swing.JPanel customerCheck;
    private com.abc.view.componont.GradientBG gradientBG1;
    private javax.swing.JTable halltable;
    private javax.swing.JLayeredPane jCalanderPane;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbHalltype;
    private javax.swing.JPanel login;
    private javax.swing.JTextField txtAc;
    private javax.swing.JTextField txtCap;
    private javax.swing.JLabel txtCheckInDateCV;
    private javax.swing.JLabel txtCheckOutDateCV;
    private javax.swing.JTextField txtHallId;
    private javax.swing.JTextField txtPrice;
    // End of variables declaration//GEN-END:variables
}
