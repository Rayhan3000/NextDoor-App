/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgsuper;


import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author rahat
 */
public class DBConnection  {
   
 
         

    public static Connection ProductsConnection() {
            
      
            
             Connection con = null;
              Statement st = null;
            ResultSet rs = null;
        try {
                 try {
                     Class.forName("com.mysql.jdbc.Driver");
                     con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Products","root","");
                 } catch (ClassNotFoundException ex) {
                     Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                 }
            
            st = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
           
            return con;
            
       
    }
        
        
}
        