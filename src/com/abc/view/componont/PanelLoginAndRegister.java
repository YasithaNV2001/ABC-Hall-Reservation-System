package com.abc.view.componont;

import com.abc.controller.CustomerControler;
import com.abc.database.hallDb;
import com.abc.model.Hall;
import com.abc.model.Login;
import com.abc.model.User;
import com.abc.model.UserType;

import com.abc.view.main.AdminMainUser;
import com.abc.view.main.CustomerUserView;
import com.abc.view.main.HallSetingsAdmin;
import com.abc.view.main.FrontDeskUser;
import com.abc.view.main.MainLoginForm;
import com.abc.view.main.UserAddingForm;
import com.abc.view.swing_componont.Button;
import com.abc.view.swing_componont.MyPasswordField;
import com.abc.view.swing_componont.MyTextField;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import net.miginfocom.swing.MigLayout;

public class PanelLoginAndRegister extends javax.swing.JLayeredPane {

    public PanelLoginAndRegister() {
        initComponents();

        initLogin();
        login.setVisible(false);
        customerCheck.setVisible(true);
        loadComboBoxSD();
        loadComboBoxBQ();
        loadComboBoxLX();

    }
    
    private void loadComboBoxSD() {

        CustomerControler Cc = new CustomerControler();
        List<Hall> list = Cc.selectComponentValueHallID("Standard Hall");

        for (int i = 0; i < list.size(); i++) {

            cmbSDHallID.addItem(list.get(i).getHallId());

        }

    }
    
    private void loadComboBoxBQ() {

        CustomerControler Cc = new CustomerControler();
        List<Hall> list = Cc.selectComponentValueHallID("Benquet Hall");

        for (int i = 0; i < list.size(); i++) {

            cmbBQHallID.addItem(list.get(i).getHallId());

        }

    }
    private void loadComboBoxLX() {

        CustomerControler Cc = new CustomerControler();
        List<Hall> list = Cc.selectComponentValueHallID("Luxury Hall");

        for (int i = 0; i < list.size(); i++) {

            cmbLXHallID.addItem(list.get(i).getHallId());

        }

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
        txtUserid.setPrefixIcon(new ImageIcon(getClass().getResource("/com/abc/view/icon/mail.png")));
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
                                ml.visible();
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
        StanderdHallType = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        cmbSDHallID = new javax.swing.JComboBox<>();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnCheckIn1 = new com.abc.view.swing_componont.ButtonOutLine();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jCalanderPane = new javax.swing.JLayeredPane();
        checkInCalender = new javax.swing.JLayeredPane();
        txtCheckInDate = new javax.swing.JLabel();
        btnCheckIn = new com.abc.view.swing_componont.ButtonOutLine();
        checkindate = new javax.swing.JLabel();
        checkOutdate = new javax.swing.JLabel();
        checkInCalender2 = new javax.swing.JLayeredPane();
        txtCheckOutDate1 = new javax.swing.JLabel();
        btnCheckIn2 = new com.abc.view.swing_componont.ButtonOutLine();
        jLabel25 = new javax.swing.JLabel();
        BanquetHallType = new javax.swing.JLayeredPane();
        jLabel7 = new javax.swing.JLabel();
        cmbBQHallID = new javax.swing.JComboBox<>();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnCheckIn4 = new com.abc.view.swing_componont.ButtonOutLine();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        LuxuryHallType = new javax.swing.JLayeredPane();
        jLabel19 = new javax.swing.JLabel();
        cmbLXHallID = new javax.swing.JComboBox<>();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        btnCheckIn5 = new com.abc.view.swing_componont.ButtonOutLine();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        btnCheckAcailable = new com.abc.view.swing_componont.KButton();
        btnBookNow = new com.abc.view.swing_componont.KButton();

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
            .addGap(0, 631, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 562, Short.MAX_VALUE)
        );

        add(login, "card3");

        customerCheck.setBackground(new java.awt.Color(194, 217, 214));

        StanderdHallType.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Standerd Halls");

        cmbSDHallID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSDHallIDActionPerformed(evt);
            }
        });

        jRadioButton1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jRadioButton1.setText("NON A/C");

        jRadioButton2.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jRadioButton2.setText("A/C");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Maximum Capacity- 500");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel3.setText("Price Per Day -50 000/-");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel4.setText("Terms and Conditions apply.");

        btnCheckIn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/abc/view/icon/building (5).png"))); // NOI18N
        btnCheckIn1.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        btnCheckIn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckIn1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel6.setText(" (Per Person)");

        StanderdHallType.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        StanderdHallType.setLayer(cmbSDHallID, javax.swing.JLayeredPane.DEFAULT_LAYER);
        StanderdHallType.setLayer(jRadioButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        StanderdHallType.setLayer(jRadioButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        StanderdHallType.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        StanderdHallType.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        StanderdHallType.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        StanderdHallType.setLayer(btnCheckIn1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        StanderdHallType.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        StanderdHallType.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout StanderdHallTypeLayout = new javax.swing.GroupLayout(StanderdHallType);
        StanderdHallType.setLayout(StanderdHallTypeLayout);
        StanderdHallTypeLayout.setHorizontalGroup(
            StanderdHallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StanderdHallTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(StanderdHallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(StanderdHallTypeLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(334, 334, 334))
                    .addGroup(StanderdHallTypeLayout.createSequentialGroup()
                        .addGroup(StanderdHallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(cmbSDHallID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(StanderdHallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(StanderdHallTypeLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton1)
                                .addGap(177, 177, 177))
                            .addGroup(StanderdHallTypeLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(btnCheckIn1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(StanderdHallTypeLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        StanderdHallTypeLayout.setVerticalGroup(
            StanderdHallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StanderdHallTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(StanderdHallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(StanderdHallTypeLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbSDHallID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCheckIn1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(StanderdHallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(249, 249, 249)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane1.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        checkInCalender.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        txtCheckInDate.setBackground(new java.awt.Color(255, 255, 255));
        txtCheckInDate.setFont(new java.awt.Font("Playbill", 1, 28)); // NOI18N
        txtCheckInDate.setForeground(new java.awt.Color(255, 255, 255));
        txtCheckInDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtCheckInDate.setText("10|02|2023");

        btnCheckIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/abc/view/icon/cursor (3).png"))); // NOI18N
        btnCheckIn.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        btnCheckIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckInActionPerformed(evt);
            }
        });

        checkInCalender.setLayer(txtCheckInDate, javax.swing.JLayeredPane.DEFAULT_LAYER);
        checkInCalender.setLayer(btnCheckIn, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout checkInCalenderLayout = new javax.swing.GroupLayout(checkInCalender);
        checkInCalender.setLayout(checkInCalenderLayout);
        checkInCalenderLayout.setHorizontalGroup(
            checkInCalenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(checkInCalenderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtCheckInDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        checkInCalenderLayout.setVerticalGroup(
            checkInCalenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, checkInCalenderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtCheckInDate)
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

        txtCheckOutDate1.setBackground(new java.awt.Color(255, 255, 255));
        txtCheckOutDate1.setFont(new java.awt.Font("Playbill", 1, 28)); // NOI18N
        txtCheckOutDate1.setForeground(new java.awt.Color(255, 255, 255));
        txtCheckOutDate1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtCheckOutDate1.setText("10|02|2023");

        btnCheckIn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/abc/view/icon/cursor (3).png"))); // NOI18N
        btnCheckIn2.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        btnCheckIn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckIn2ActionPerformed(evt);
            }
        });

        checkInCalender2.setLayer(txtCheckOutDate1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        checkInCalender2.setLayer(btnCheckIn2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout checkInCalender2Layout = new javax.swing.GroupLayout(checkInCalender2);
        checkInCalender2.setLayout(checkInCalender2Layout);
        checkInCalender2Layout.setHorizontalGroup(
            checkInCalender2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(checkInCalender2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtCheckOutDate1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCheckIn2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        checkInCalender2Layout.setVerticalGroup(
            checkInCalender2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, checkInCalender2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtCheckOutDate1)
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
                .addGap(15, 15, 15)
                .addComponent(jCalanderPane, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

        BanquetHallType.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Banquet Halls");

        cmbBQHallID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBQHallIDActionPerformed(evt);
            }
        });

        jRadioButton3.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jRadioButton3.setText("NON A/C");

        jRadioButton4.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jRadioButton4.setText("A/C");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Maximum Capacity- 1000");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel9.setText("Price Per Day -75 000/-");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel10.setText("Terms and Conditions apply.");

        btnCheckIn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/abc/view/icon/building (6).png"))); // NOI18N
        btnCheckIn4.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        btnCheckIn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckIn4ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel12.setText(" (Per Person)");

        BanquetHallType.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        BanquetHallType.setLayer(cmbBQHallID, javax.swing.JLayeredPane.DEFAULT_LAYER);
        BanquetHallType.setLayer(jRadioButton3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        BanquetHallType.setLayer(jRadioButton4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        BanquetHallType.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        BanquetHallType.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        BanquetHallType.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        BanquetHallType.setLayer(btnCheckIn4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        BanquetHallType.setLayer(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        BanquetHallType.setLayer(jLabel12, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout BanquetHallTypeLayout = new javax.swing.GroupLayout(BanquetHallType);
        BanquetHallType.setLayout(BanquetHallTypeLayout);
        BanquetHallTypeLayout.setHorizontalGroup(
            BanquetHallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BanquetHallTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BanquetHallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BanquetHallTypeLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(334, 334, 334))
                    .addGroup(BanquetHallTypeLayout.createSequentialGroup()
                        .addGroup(BanquetHallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(cmbBQHallID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(BanquetHallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BanquetHallTypeLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jRadioButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton3)
                                .addGap(177, 177, 177))
                            .addGroup(BanquetHallTypeLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(btnCheckIn4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(BanquetHallTypeLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        BanquetHallTypeLayout.setVerticalGroup(
            BanquetHallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BanquetHallTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BanquetHallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BanquetHallTypeLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbBQHallID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCheckIn4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BanquetHallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jRadioButton4)
                    .addComponent(jRadioButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(249, 249, 249)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        LuxuryHallType.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Luxury Halls");

        cmbLXHallID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLXHallIDActionPerformed(evt);
            }
        });

        jRadioButton7.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jRadioButton7.setText("NON A/C");

        jRadioButton8.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jRadioButton8.setText("A/C");
        jRadioButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton8ActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Maximum Capacity- 1500");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel21.setText("Price Per Day -100 000/-");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel22.setText("Terms and Conditions apply.");

        btnCheckIn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/abc/view/icon/building (4).png"))); // NOI18N
        btnCheckIn5.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        btnCheckIn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckIn5ActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel24.setText(" (Per Person)");

        LuxuryHallType.setLayer(jLabel19, javax.swing.JLayeredPane.DEFAULT_LAYER);
        LuxuryHallType.setLayer(cmbLXHallID, javax.swing.JLayeredPane.DEFAULT_LAYER);
        LuxuryHallType.setLayer(jRadioButton7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        LuxuryHallType.setLayer(jRadioButton8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        LuxuryHallType.setLayer(jLabel20, javax.swing.JLayeredPane.DEFAULT_LAYER);
        LuxuryHallType.setLayer(jLabel21, javax.swing.JLayeredPane.DEFAULT_LAYER);
        LuxuryHallType.setLayer(jLabel22, javax.swing.JLayeredPane.DEFAULT_LAYER);
        LuxuryHallType.setLayer(btnCheckIn5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        LuxuryHallType.setLayer(jLabel23, javax.swing.JLayeredPane.DEFAULT_LAYER);
        LuxuryHallType.setLayer(jLabel24, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout LuxuryHallTypeLayout = new javax.swing.GroupLayout(LuxuryHallType);
        LuxuryHallType.setLayout(LuxuryHallTypeLayout);
        LuxuryHallTypeLayout.setHorizontalGroup(
            LuxuryHallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LuxuryHallTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(LuxuryHallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LuxuryHallTypeLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(334, 334, 334))
                    .addGroup(LuxuryHallTypeLayout.createSequentialGroup()
                        .addGroup(LuxuryHallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20)
                            .addComponent(cmbLXHallID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(LuxuryHallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(LuxuryHallTypeLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jRadioButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton7)
                                .addGap(177, 177, 177))
                            .addGroup(LuxuryHallTypeLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(btnCheckIn5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(LuxuryHallTypeLayout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        LuxuryHallTypeLayout.setVerticalGroup(
            LuxuryHallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LuxuryHallTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(LuxuryHallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LuxuryHallTypeLayout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbLXHallID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCheckIn5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(LuxuryHallTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jRadioButton8)
                    .addComponent(jRadioButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addGap(249, 249, 249)
                .addComponent(jLabel23)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnCheckAcailable.setText("Check Available");
        btnCheckAcailable.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCheckAcailable.setkBorderRadius(50);

        btnBookNow.setText("BOOK NOW");
        btnBookNow.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnBookNow.setkBorderRadius(50);
        btnBookNow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookNowActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout gradientBG1Layout = new javax.swing.GroupLayout(gradientBG1);
        gradientBG1.setLayout(gradientBG1Layout);
        gradientBG1Layout.setHorizontalGroup(
            gradientBG1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradientBG1Layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addGroup(gradientBG1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(gradientBG1Layout.createSequentialGroup()
                        .addComponent(LuxuryHallType, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(246, 246, 246))
                    .addGroup(gradientBG1Layout.createSequentialGroup()
                        .addGroup(gradientBG1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(StanderdHallType, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BanquetHallType, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(gradientBG1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(gradientBG1Layout.createSequentialGroup()
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33))
                            .addGroup(gradientBG1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(gradientBG1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnBookNow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnCheckAcailable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        gradientBG1Layout.setVerticalGroup(
            gradientBG1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradientBG1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(gradientBG1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gradientBG1Layout.createSequentialGroup()
                        .addComponent(StanderdHallType, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(BanquetHallType, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LuxuryHallType, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(gradientBG1Layout.createSequentialGroup()
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(btnCheckAcailable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBookNow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(188, Short.MAX_VALUE))))
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
        txtCheckInDate.setText(checkIn.setPickedDate());
    }//GEN-LAST:event_btnCheckInActionPerformed

    private void btnCheckIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckIn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCheckIn1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void cmbSDHallIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSDHallIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSDHallIDActionPerformed

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
        txtCheckOutDate1.setText(checkOut.setPickedDate());
    }//GEN-LAST:event_btnCheckIn2ActionPerformed

    private void cmbBQHallIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBQHallIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbBQHallIDActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void btnCheckIn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckIn4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCheckIn4ActionPerformed

    private void cmbLXHallIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLXHallIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbLXHallIDActionPerformed

    private void jRadioButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton8ActionPerformed

    private void btnCheckIn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckIn5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCheckIn5ActionPerformed

    private void btnBookNowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookNowActionPerformed

        CustomerUserView cuv = new CustomerUserView();
        cuv.setVisible(true);
    }//GEN-LAST:event_btnBookNowActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane BanquetHallType;
    private javax.swing.JLayeredPane LuxuryHallType;
    private javax.swing.JLayeredPane StanderdHallType;
    private com.abc.view.swing_componont.KButton btnBookNow;
    private com.abc.view.swing_componont.KButton btnCheckAcailable;
    private com.abc.view.swing_componont.ButtonOutLine btnCheckIn;
    private com.abc.view.swing_componont.ButtonOutLine btnCheckIn1;
    private com.abc.view.swing_componont.ButtonOutLine btnCheckIn2;
    private com.abc.view.swing_componont.ButtonOutLine btnCheckIn3;
    private com.abc.view.swing_componont.ButtonOutLine btnCheckIn4;
    private com.abc.view.swing_componont.ButtonOutLine btnCheckIn5;
    private javax.swing.JLayeredPane checkInCalender;
    private javax.swing.JLayeredPane checkInCalender2;
    private javax.swing.JLayeredPane checkInCalender3;
    private javax.swing.JLabel checkOutdate;
    private javax.swing.JLabel checkindate;
    private javax.swing.JComboBox<String> cmbBQHallID;
    private javax.swing.JComboBox<String> cmbLXHallID;
    private javax.swing.JComboBox<String> cmbSDHallID;
    private javax.swing.JPanel customerCheck;
    private com.abc.view.componont.GradientBG gradientBG1;
    private javax.swing.JLayeredPane jCalanderPane;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JPanel login;
    private javax.swing.JLabel txtCheckInDate;
    private javax.swing.JLabel txtCheckOutDate1;
    // End of variables declaration//GEN-END:variables
}
