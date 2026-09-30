/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author User
 */
public class hallDb {
    
    static Connection con;
    
    public static Connection getConnection() throws Exception{
    
    if(con==null){
    
    Class.forName("com.mysql.cj.jdbc.Driver");
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/abcdb","root","root");
    }
    
    return con;
    
    }
    
    
    
}
