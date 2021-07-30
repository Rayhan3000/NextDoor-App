/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgsuper;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Sangbid Zaman
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    AnchorPane pane;
    @FXML
    Label label;
    @FXML
    JFXTextField Tbox;
    @FXML
    JFXPasswordField Pbox;
    private Connection con = null;
        private PreparedStatement st = null;
        private ResultSet rs = null;
        private ObservableList<Login>Data;
    
    @FXML
    private void Signin(ActionEvent event) throws Exception {
        
        if(Tbox.getText().equals(getUserName())&& Pbox.getText().equals(getPassword())){
            Stage stage = (Stage) pane.getScene().getWindow();
	Parent root = FXMLLoader.load(getClass().getResource("FXML1.fxml"));
        
        Scene scene1 = new Scene(root);
        
        
        stage.setScene(scene1);
        stage.show();
			
		}
        else{
            JOptionPane.showMessageDialog(null, "Invalid Username or Password");
        }
        
    }
    private String getUserName(){
        String userName="";
        
        try {
            st=con.prepareStatement("Select UserName from login where UserName=?");
            st.setString(1,Tbox.getText());
            rs=st.executeQuery();
            if(rs.next()){
                userName = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return userName;
    }
    private String getPassword(){
        String Password="";
        
        try {
            st=con.prepareStatement("Select Password from login where UserName=?");
            st.setString(1,Tbox.getText());
            rs=st.executeQuery();
            if(rs.next()){
                Password = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Password;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        con= DBConnection.ProductsConnection();
        Data =  FXCollections.observableArrayList();
     
    }    
    
}
