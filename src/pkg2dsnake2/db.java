/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dsnake2;

import java.sql.*;
import javax.swing.*;
/**
 *
 * @author new
 */
public class db {
    
    Connection conn;
    public static Connection java_db(){
        
        try{
            
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\new\\Documents\\NetBeansProjects\\2Dsnake2\\snakedb.sqlite");
            return conn;
            
        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        
    }
    
    
}
