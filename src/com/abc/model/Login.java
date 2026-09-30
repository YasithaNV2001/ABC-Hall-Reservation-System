/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model;

import com.abc.controller.UserControler;
import java.util.Date;

/**
 *
 * @author User
 */
public class Login {
    
    private static Login instance=null;
    private  String loginTime;
    private  User user;

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

   

    private Login(String loginTime,User user) {
        this.loginTime = loginTime;
        this.user=user;
    }

    private Login() {
    }
    
    
    
    
    public static Login getLoginInstance(){
    
    if(instance==null){
    instance=new Login();
    
    
    }
        return instance;
   
    
    }
    
    
    
    
    
    public void userLogin(){}
    
    public boolean isUserLogin(){
        
        
        
    
        return false;
    

    }
    
    
    public void userLogOut(){
    
    
    
    
    }
}
