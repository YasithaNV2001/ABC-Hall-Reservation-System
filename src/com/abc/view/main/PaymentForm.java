/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.abc.view.main;

import com.abc.controller.AdminControler;
import com.abc.controller.UserControler;
import com.abc.model.BenquetHalls;
import com.abc.model.Booking;
import com.abc.model.Customer;
import com.abc.model.Hall;
import com.abc.model.LuxuryHalls;
import com.abc.model.StanderdHalls;
import com.abc.view.componont.DatePicker;
import com.abc.view.swing_componont.ButtonOutLine;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class PaymentForm extends javax.swing.JFrame {

    /**
     * Creates new form CustomerUserIView
     */
    public PaymentForm() {
        initComponents();

    }

    Booking b1;

    public double calcPayment(int numberOfDays, double PricePerDay) {

        double amount;
        amount = numberOfDays * PricePerDay;
        return amount;

    }

    public void addBookingafterPayment(Booking book) {

        

        b1 = book;

    }

    double amountpay;

    public void paymentBill(Booking book, double amount) {
        amountpay = amount;
        txtFprintBill.setText("+**************************************************+\n");
        txtFprintBill.setText(txtFprintBill.getText() + "+  Reservation Recipt                              +\n");
        txtFprintBill.setText(txtFprintBill.getText() + "+**************************************************+\n");
        txtFprintBill.setText(txtFprintBill.getText() + "+-------------ABC Hall Management------------------+\n");
        txtFprintBill.setText(txtFprintBill.getText() + "+==================================================+\n");
        txtFprintBill.setText(txtFprintBill.getText() + "+ Booking ID :-" + book.getBookingId() + "             +\n");
        txtFprintBill.setText(txtFprintBill.getText() + "+ Booking Type :-" + book.getBookingType() + "         +\n");
        txtFprintBill.setText(txtFprintBill.getText() + "+ NIC :-" + book.getCustomer().getCustomerId() + "     +\n");
        txtFprintBill.setText(txtFprintBill.getText() + "+ Name :-" + book.getCustomer().getName() + "          +\n");
        txtFprintBill.setText(txtFprintBill.getText() + "+ Telephone Number :-" + book.getCustomer().getTelephoneNumber() + "+\n");
        txtFprintBill.setText(txtFprintBill.getText() + "+---------------------------------------------------+\n");
        txtFprintBill.setText(txtFprintBill.getText() + "+ Hall ID :-" + book.getHall().getHallId() + "          +\n");
        txtFprintBill.setText(txtFprintBill.getText() + "+ Check In date :-" + book.getCheckIn() + "             +\n");
        txtFprintBill.setText(txtFprintBill.getText() + "+ Check Out date :-" + book.getCheckOut() + "           +\n");
        txtFprintBill.setText(txtFprintBill.getText() + "+---------------------------------------------------+\n");
        txtFprintBill.setText(txtFprintBill.getText() + "+---------------------------------------------------+\n");
        txtFprintBill.setText(txtFprintBill.getText() + "+Total Amount:- Rs" + amount + "                        +\n");
        txtFprintBill.setText(txtFprintBill.getText() + "+---------------------------------------------------+\n");
        btnTotalAmount.setText(Double.toString(amount));

    }

    public double payment(boolean isdiscount) {
        double balance;
        double pay;
        double discount;
        double value = 0;

        if (isdiscount) {
            pay = Double.parseDouble(txtPay.getText());
            discount = amountpay * 0.1;
            value = amountpay - discount;
            balance = pay - value;
            txtBalance.setText(Double.toString(balance));
            txtFprintBill.setText(txtFprintBill.getText() + "+ 10% Discount off:- -Rs" + discount + "              +\n");
            txtFprintBill.setText(txtFprintBill.getText() + "  +Total due:- Rs" + value + "/-                       +\n");
            txtFprintBill.setText(txtFprintBill.getText() + "+----------------------------------------------------+\n");
            txtFprintBill.setText(txtFprintBill.getText() + "+ Cash:- Rs" + pay + "/-                            +\n");
            txtFprintBill.setText(txtFprintBill.getText() + "  +Balance:- Rs" + balance + "/-                        +\n");
            txtFprintBill.setText(txtFprintBill.getText() + "+----------------------------------------------------+\n");
            txtFprintBill.setText(txtFprintBill.getText() + "+---------------Thank You Come Again-----------------+\n");

        } else {

            pay = Double.parseDouble(txtPay.getText());
            value = amountpay;
            balance = pay - amountpay;
            txtBalance.setText(Double.toString(balance));

            txtFprintBill.setText(txtFprintBill.getText() + "+ Cash:- Rs" + pay + "   +\n");
            txtFprintBill.setText(txtFprintBill.getText() + "+ Balance:- Rs" + balance + "  + \n");
            txtFprintBill.setText(txtFprintBill.getText() + "+---------------------------------------------------+\n");
            txtFprintBill.setText(txtFprintBill.getText() + "+---------------Thank You Come Again-----------------+\n");

        }
        return value;

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
        gradientBG2 = new com.abc.view.componont.GradientBG();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        HallDetalis = new javax.swing.JLayeredPane();
        jLabel11 = new javax.swing.JLabel();
        checkOutdate = new javax.swing.JLabel();
        txtPay = new com.abc.view.swing_componont.MyTextField();
        btnBack = new com.abc.view.swing_componont.ButtonOutLine();
        btnDiscount = new com.abc.view.swing_componont.ButtonOutLine();
        btnHallAdd8 = new com.abc.view.swing_componont.ButtonOutLine();
        btnPay = new com.abc.view.swing_componont.ButtonOutLine();
        txtBalance = new com.abc.view.swing_componont.MyTextField();
        jLabel1 = new javax.swing.JLabel();
        btnCheckIn4 = new com.abc.view.swing_componont.ButtonOutLine();
        btnTotalAmount = new com.abc.view.swing_componont.ButtonOutLine();
        btnPrintInvoice = new com.abc.view.swing_componont.ButtonOutLine();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtFprintBill = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        HallDetalis.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 255, 255)));

        checkOutdate.setBackground(new java.awt.Color(255, 255, 255));
        checkOutdate.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        checkOutdate.setForeground(new java.awt.Color(255, 255, 255));
        checkOutdate.setText("Total Amount");

        txtPay.setHint("Payment");

        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("Back");
        btnBack.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnDiscount.setForeground(new java.awt.Color(255, 255, 255));
        btnDiscount.setText("10% Discount");
        btnDiscount.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiscountActionPerformed(evt);
            }
        });

        btnHallAdd8.setForeground(new java.awt.Color(255, 255, 255));
        btnHallAdd8.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        btnHallAdd8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHallAdd8ActionPerformed(evt);
            }
        });

        btnPay.setForeground(new java.awt.Color(255, 255, 255));
        btnPay.setText("Pay");
        btnPay.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        btnPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayActionPerformed(evt);
            }
        });

        txtBalance.setHint("Balance");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Balance");

        btnCheckIn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/abc/view/icon/building (6).png"))); // NOI18N
        btnCheckIn4.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        btnCheckIn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckIn4ActionPerformed(evt);
            }
        });

        btnTotalAmount.setForeground(new java.awt.Color(255, 255, 255));
        btnTotalAmount.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        btnTotalAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTotalAmountActionPerformed(evt);
            }
        });

        btnPrintInvoice.setForeground(new java.awt.Color(255, 255, 255));
        btnPrintInvoice.setText("Print Invoice");
        btnPrintInvoice.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        btnPrintInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintInvoiceActionPerformed(evt);
            }
        });

        HallDetalis.setLayer(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        HallDetalis.setLayer(checkOutdate, javax.swing.JLayeredPane.DEFAULT_LAYER);
        HallDetalis.setLayer(txtPay, javax.swing.JLayeredPane.DEFAULT_LAYER);
        HallDetalis.setLayer(btnBack, javax.swing.JLayeredPane.DEFAULT_LAYER);
        HallDetalis.setLayer(btnDiscount, javax.swing.JLayeredPane.DEFAULT_LAYER);
        HallDetalis.setLayer(btnHallAdd8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        HallDetalis.setLayer(btnPay, javax.swing.JLayeredPane.DEFAULT_LAYER);
        HallDetalis.setLayer(txtBalance, javax.swing.JLayeredPane.DEFAULT_LAYER);
        HallDetalis.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        HallDetalis.setLayer(btnCheckIn4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        HallDetalis.setLayer(btnTotalAmount, javax.swing.JLayeredPane.DEFAULT_LAYER);
        HallDetalis.setLayer(btnPrintInvoice, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout HallDetalisLayout = new javax.swing.GroupLayout(HallDetalis);
        HallDetalis.setLayout(HallDetalisLayout);
        HallDetalisLayout.setHorizontalGroup(
            HallDetalisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HallDetalisLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(btnHallAdd8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(HallDetalisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HallDetalisLayout.createSequentialGroup()
                        .addGroup(HallDetalisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnPay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HallDetalisLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(HallDetalisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnPrintInvoice, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtBalance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(10, 10, 10)
                                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(HallDetalisLayout.createSequentialGroup()
                        .addComponent(txtPay, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDiscount, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))))
            .addGroup(HallDetalisLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(btnTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(HallDetalisLayout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(checkOutdate, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HallDetalisLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCheckIn4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(155, 155, 155))
        );
        HallDetalisLayout.setVerticalGroup(
            HallDetalisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HallDetalisLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btnCheckIn4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkOutdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(HallDetalisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(HallDetalisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnHallAdd8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPay, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGroup(HallDetalisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HallDetalisLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel11))
                    .addGroup(HallDetalisLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(HallDetalisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrintInvoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8))
        );

        txtFprintBill.setBackground(new java.awt.Color(255, 255, 255));
        txtFprintBill.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtFprintBill.setForeground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(txtFprintBill);

        jLayeredPane1.setLayer(HallDetalis, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(HallDetalis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(HallDetalis)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        javax.swing.GroupLayout gradientBG2Layout = new javax.swing.GroupLayout(gradientBG2);
        gradientBG2.setLayout(gradientBG2Layout);
        gradientBG2Layout.setHorizontalGroup(
            gradientBG2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradientBG2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        gradientBG2Layout.setVerticalGroup(
            gradientBG2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradientBG2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gradientBG2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gradientBG2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCheckIn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckIn4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCheckIn4ActionPerformed


    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        this.setVisible(false);
        AdminMainUser ac = new AdminMainUser();
        ac.setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

    double totalpayment;
    private void btnDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiscountActionPerformed

        
        if (txtPay.getText().isBlank()) {

            
            JOptionPane.showMessageDialog(null, "Please eneter Payment ");
        } else {
            totalpayment = payment(true);
        }


    }//GEN-LAST:event_btnDiscountActionPerformed

    private void btnHallAdd8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHallAdd8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHallAdd8ActionPerformed

    private void btnPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayActionPerformed

        if (txtPay.getText().isBlank()) {

            JOptionPane.showMessageDialog(null, "Please eneter Payment ");
        } else {

            if (totalpayment==00) {
                totalpayment = payment(false);
                b1.setPayment(totalpayment);
                UserControler uc = new UserControler();
                uc.addBooking(b1);

            }
            b1.setPayment(totalpayment);
            UserControler uc = new UserControler();
            uc.addBooking(b1);
        }

        AdminMainUser ac = new AdminMainUser();
        ac.DataTableForBookingDetails();
    }//GEN-LAST:event_btnPayActionPerformed

    private void btnTotalAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTotalAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTotalAmountActionPerformed

    private void btnPrintInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintInvoiceActionPerformed

        try {

            txtFprintBill.print();

        } catch (Exception e) {

        }


    }//GEN-LAST:event_btnPrintInvoiceActionPerformed

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
            java.util.logging.Logger.getLogger(PaymentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PaymentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PaymentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PaymentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PaymentForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane HallDetalis;
    private com.abc.view.swing_componont.ButtonOutLine btnBack;
    private com.abc.view.swing_componont.ButtonOutLine btnCheckIn4;
    private com.abc.view.swing_componont.ButtonOutLine btnDiscount;
    private com.abc.view.swing_componont.ButtonOutLine btnHallAdd8;
    private com.abc.view.swing_componont.ButtonOutLine btnPay;
    private com.abc.view.swing_componont.ButtonOutLine btnPrintInvoice;
    private com.abc.view.swing_componont.ButtonOutLine btnTotalAmount;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel checkOutdate;
    private com.abc.view.componont.GradientBG gradientBG2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.abc.view.swing_componont.MyTextField txtBalance;
    private javax.swing.JTextPane txtFprintBill;
    private com.abc.view.swing_componont.MyTextField txtPay;
    // End of variables declaration//GEN-END:variables
}
