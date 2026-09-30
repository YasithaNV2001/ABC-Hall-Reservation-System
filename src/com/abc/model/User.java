/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model;


import static com.abc.model.UserType.FRONT_DESK_USER;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class User {
    
    private String userId;
    private UserType userType;
    private String password;
    private String userCreateDate;
    private int id;
    

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        
        if(userId==" "){
        
        JOptionPane.showMessageDialog(null, "User id is requred");
        }else{
        this.userId = userId;}
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   

    public String getUserCreateDate() {
        return userCreateDate;
    }

    public void setUserCreateDate(String userCreateDate) {
        this.userCreateDate = userCreateDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
     public User(String userId, UserType userType, String password,String userCreateDate,int id) {
        this.userId = userId;
        this.userType = userType;
        this.password = password;
        this.userCreateDate = userCreateDate;
        this.id = id;
    }
     
   
     
    public User() {
         
    }
    
   
    
}
