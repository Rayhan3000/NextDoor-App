/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgsuper;

import dba.DBConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author rahat
 */
public class FXML1Controller implements Initializable {
        private Connection con = null;
        private Statement st = null;
        private ResultSet rs = null;
        private ObservableList<ProductList>data;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private TextField Tbox;
    @FXML
    private TextField Tbox1;
    @FXML
    private TextField Tbox2;
    @FXML
    private TextField Tbox3;
    
    @FXML
    private TableView<ProductList> Ptable;
    @FXML
    private TableColumn <ProductList,String>Pname;
    @FXML
    private TableColumn <ProductList,String>Pprice;
    @FXML
    private TableColumn <ProductList,String>Pqty;
    //@FXML
   // private TableColumn <ProductList,String>Pst;
    @FXML
    private TableColumn <ProductList,String> Pm;       
    private DBConnection dc ;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dc=new DBConnection();
        con= DBConnection.ProductsConnection();
        data =  FXCollections.observableArrayList();
        setCellTable();
        
    }    
    public void setCellTable(){
        Pname.setCellValueFactory(new PropertyValueFactory<>("ProductsName"));
        Pprice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        Pqty.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        //Pst.setCellValueFactory(new PropertyValueFactory<>("InStock"));
        Pm.setCellValueFactory(new PropertyValueFactory<>("Model"));
    }
    @FXML
    private void loadDataFromDatabase(ActionEvent event){
            try {
                Connection con = dc.ProductsConnection();
                data =  FXCollections.observableArrayList();
                st= con .createStatement();
                rs=st.executeQuery("SELECT * FROM products");
                while(rs.next()){
                    data.add(new ProductList(rs.getString(1) , rs.getString(2), rs.getString(3), rs.getString(4)));
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(FXML1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        Pname.setCellValueFactory(new PropertyValueFactory<>("ProductsName"));
        Pprice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        Pqty.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        //Pst.setCellValueFactory(new PropertyValueFactory<>("InStock"));
        Pm.setCellValueFactory(new PropertyValueFactory<>("Model"));
        
        
}
    public void addProduct(ActionEvent event) throws SQLException{
        String sql = "INSERT INTO `products`(`Products Name`, `Prize`, `Quantity`, `InStock`, `Model`) VALUES (?,?,?,?,?)";
        String ProductsName = Tbox.getText();
        double Price = Double.valueOf(Tbox1.getText());
        double Quantity = Double.valueOf(Tbox2.getText());
        double Model = Double.valueOf(Tbox3.getText());
        
        
        
    }
}
