/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dba;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author rahat
 */
public class DBConnection {
   
    

    public static Connection ProductsConnection() {
            Connection con=null;
        
        
        
        try {
            
                Class.forName("com.mysql.jdbc.server");
           
            
            
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Products","root","");
            PreparedStatement st = (PreparedStatement) con.createStatement();
            st.executeQuery();
            st.executeUpdate();
            return con;
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
        
}}