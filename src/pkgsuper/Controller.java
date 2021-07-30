/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgsuper;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author rahat
 */
public class Controller implements Initializable {
     private Connection con = null;
        private PreparedStatement st = null;
        private ResultSet rs = null;
        private ObservableList<ProductList>data;
    @FXML
    private AnchorPane pane;
    @FXML
    private AnchorPane pane1;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private JFXButton delete;
    @FXML
    private JFXButton btn3;
    @FXML
    private TextField Tbox;
    @FXML
    private TextField Tbox1;
    @FXML
    private TextField Tbox2;
    @FXML
    private TextField Tbox3;
    @FXML
    private JFXTextField search;
    @FXML
    private TableView<ProductList> Ptable;
    @FXML
    private TableColumn <ProductList,String>Pname;
    @FXML
    private TableColumn <ProductList,Double>Pprice;
    @FXML
    private TableColumn <ProductList,Double>Pqty;
   
    @FXML
    private TableColumn <ProductList,String> Pm;       
    private DBConnection dc ;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dc=new DBConnection();
        con= DBConnection.ProductsConnection();
        data =  FXCollections.observableArrayList();
        setCellTable();
        loadDataFromDatabase();
        tableToText();
        searchProduct();
     }
    public void setCellTable(){
        Pname.setCellValueFactory(new PropertyValueFactory<>("ProductsName"));
        Pprice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        Pqty.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        Pm.setCellValueFactory(new PropertyValueFactory<>("Barcode"));
    }
    
    private void loadDataFromDatabase(){
            try {
                con = DBConnection.ProductsConnection();
                data =  FXCollections.observableArrayList();
                st = con.prepareStatement("Select * from products");
                rs=st.executeQuery();
                while(rs.next()){
                    data.add(new ProductList(rs.getString(2) , ""+rs.getDouble(3), ""+rs.getDouble(4), rs.getString(5)));
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        Pname.setCellValueFactory(new PropertyValueFactory<>("ProductsName"));
        Pprice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        Pqty.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        Pm.setCellValueFactory(new PropertyValueFactory<>("Barcode"));
        Ptable.setItems(data);
        
        
}
     @FXML
    public void addProduct(ActionEvent event) throws SQLException{
        String sql = "Insert into products(ProductsName,Price,Quantity,Barcode) Values(?,?,?,?)";
        String ProductsName = Tbox.getText();
        double Price = Double.valueOf(Tbox1.getText());
        double Quantity = Double.valueOf(Tbox2.getText());
        String Barcode =  Tbox3.getText();
        
            try {
                st = con.prepareStatement(sql);
                st.setString(1,ProductsName);
                st.setDouble(2,Price);
                st.setDouble(3,Quantity);
                st.setString(4,Barcode);
                int i = st.executeUpdate();
                if(i==1){
                    loadDataFromDatabase();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{st.close();
            
        
        
            }
    }
    public void tableToText(){
        Ptable.setOnMouseClicked((MouseEvent event) -> {
            ProductList p1 = Ptable.getItems().get(Ptable.getSelectionModel().getSelectedIndex());
            Tbox.setText(p1.getProductsName());
            Tbox1.setText(p1.getPrice());
            Tbox2.setText(p1.getQuantity());
            Tbox3.setText(p1.getBarcode());
        });
    }
    private void clearText(){
        Tbox.clear();
        Tbox1.clear();
        Tbox2.clear();
        Tbox3.clear();
    }
    @FXML
    public void deleteProduct(ActionEvent event) throws SQLException{
        String sql = "Delete FROM products WHERE Barcode=?";
        
        String Model = Tbox3.getText() ;
            try {
                st = con.prepareStatement(sql);
                
                st.setString(1,Model);
                int i = st.executeUpdate();
                if(i==1){
                    loadDataFromDatabase();
                    clearText();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{st.close();
            
        
        
            }
    }
    @FXML
    private void updateProduct(ActionEvent event){
        String sql="Update products set ProductsName=?,Price=?,Quantity=? where Barcode=?";
        String ProductsName = Tbox.getText();
                double Price = Double.valueOf(Tbox1.getText());
                double Quantity = Double.valueOf(Tbox2.getText());
                String Barcode = Tbox3.getText();
            try {
                
                st = con.prepareStatement(sql);
                
                st.setString(1,ProductsName);
                st.setDouble(2,Price);
                st.setDouble(3,Quantity);
                st.setString(4,Barcode);
                
                
                int i = st.executeUpdate();
                if(i==1){
                    loadDataFromDatabase();
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    @FXML
    public void storeIn(ActionEvent event) throws Exception{
          Stage stage = (Stage) pane1.getScene().getWindow();
	Parent root = FXMLLoader.load(getClass().getResource("FXML2.fxml"));
        
        Scene scene = new Scene(root);
        
        
        stage.setScene(scene);
        stage.show();
    }
    
    private void searchProduct(){
        search.setOnKeyReleased(e->{
            String sql="SELECT * FROM products WHERE ProductsName LIKE '%"+search.getText()+"%'";
            if(search.getText().equals("")){
                loadDataFromDatabase();
            }
            else{
                data.clear();
            try {
                
                st = con.prepareStatement(sql);
                rs=st.executeQuery();
                if(rs.next()){
                   data.add(new ProductList(rs.getString(2) , ""+rs.getDouble(3), ""+rs.getDouble(4), rs.getString(5))); 
                }
                Ptable.setItems(data);
            } catch (SQLException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            } 
            
        });
    }
        
        
        
        
        
       
}
