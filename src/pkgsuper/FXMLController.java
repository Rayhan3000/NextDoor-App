/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgsuper;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import report.Products;

/**
 * FXML Controller class
 *
 * @author rahat
 */
public class FXMLController implements Initializable {
    @FXML
    private AnchorPane pane;
    @FXML
    private AnchorPane pane1;
    @FXML
    private JFXButton btn2;
    @FXML
    private JFXButton btn1;
    @FXML
    private MenuItem shampoo;
    @FXML
    private TableView<orderList> Ptable;
    @FXML
    private TableColumn<orderList,Integer> no;
    @FXML
    private TableColumn<orderList,String> br;
    @FXML
    private TableColumn<orderList,String> Pname;
    @FXML
    private TableColumn<orderList,Integer> Pqty;
    @FXML
    private TableColumn<orderList,Double> Pprice;
    @FXML
    private TableColumn<orderList,Double> am;
    @FXML
    private TextField Tbox;
    @FXML
    private TextField Tbox1;
    @FXML
    private TextField Tbox2;
    @FXML
    private TextField invoice;
    @FXML
    private DatePicker datepicker;
    @FXML
    private TextField Tbox3;
    @FXML
    private Label lbl;
    private double total=0.0;
    private Connection con = null;
        private PreparedStatement st = null;
        private ResultSet rs = null;
        private ObservableList<orderList>orderData;
        int No=0;
        String ProductsName;
        String Barcode;
        int qty=0;
        double Price=0.0;
        double Amount=0.0;
        
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DBConnection dc = new DBConnection();
        con= DBConnection.ProductsConnection();
        invoice.setText(orderID());
        orderData =  FXCollections.observableArrayList();
        no.setCellValueFactory(new PropertyValueFactory<>("No"));
        br.setCellValueFactory(new PropertyValueFactory<>("Barcode"));
        Pname.setCellValueFactory(new PropertyValueFactory<>("ProductsName"));
        Pprice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        Pqty.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        am.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        
    }
    

    @FXML
    public void storeIn(ActionEvent event) throws Exception{
          Stage stage = (Stage) pane1.getScene().getWindow();
	Parent root = FXMLLoader.load(getClass().getResource("FXML1.fxml"));
        
        Scene scene = new Scene(root);
        
        
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void order(ActionEvent event) {
        int qty = Integer.parseInt(Tbox2.getText());
        if(qty!=0){
            Amount = Price*qty;
            total+=Amount;
            orderData.add(new orderList(++No,Barcode,ProductsName,Price,qty,Amount));
            Ptable.setItems(orderData);
            lbl.setText(""+total);
            clearText();
        }        
    }
    private void clearText(){
        Tbox.clear();
        Tbox1.clear();
        Tbox2.clear();
        Tbox3.clear();
    }

    @FXML
    private void scanProduct(KeyEvent event) throws SQLException {
                st = con.prepareStatement("Select * from products where Barcode =?");
                st.setString(1,Tbox3.getText());
                rs=st.executeQuery();
                if(rs.next()){
                    int ProductID;
                    String Barcode;
                    ProductID = rs.getInt(1);
                    Barcode=rs.getString(5);
                    
                    ProductsName = rs.getString(2);
                    Price = rs.getDouble(3);
                    Tbox3.setText(Barcode);
                    Tbox.setText(ProductsName);
                    Tbox1.setText(""+Price);
                    Tbox2.requestFocus();
                    
                    
                    
                }
                rs.close();        
    }
    private String orderID(){
       String orderID="ND000000";
        try {
            st = con.prepareStatement("Select max(OrderID) from orderproducts");
             rs=st.executeQuery();
                if(rs.next()){
                    int n = Integer.parseInt(orderID.substring(2) ) + 1;
                    int x = String.valueOf(n).length();
                    orderID=orderID.substring(0, 8-x) + String.valueOf(n);
                    
                }
                rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return orderID;
    }
    @FXML
    private void handlePrint(ActionEvent event){
        String sql ="INSERT INTO orderproducts(OrderID, OrderDate) VALUES (?,?)";
        try {
            st = con.prepareStatement(sql);
            st.setString(1,invoice.getText());
            st.setDate(2,java.sql.Date.valueOf(datepicker.getValue()));
            int i = st.executeUpdate();
            if(i==1){
                sql="INSERT INTO orderdetails(OrderID, ProductsName, Quantity, Price) VALUES (?,?,?,?)";
                for(orderList ol:orderData){
                    st = con.prepareStatement(sql);
                    st.setString(1,invoice.getText());
                    st.setString(2,ol.getProductsName());
                    
                    st.setInt(3,ol.getQuantity());
                    st.setDouble(4,ol.getPrice());
                    
                    int j = st.executeUpdate();
                    if(j==1){
                        System.out.println("yo yo");
                
                    }
                }
            }
            printInvoice();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
                
    }
    
    private void printInvoice() {
        String sourceFile = "C:\\Users\\rahat\\Documents\\NetBeansProjects\\super\\src\\report\\Invoice.jrxml";
        try {
            JasperReport jr= JasperCompileManager.compileReport(sourceFile);
            HashMap<String,Object>para=new HashMap<>();
            para.put("cashier","Rahat");
            para.put("grandTotal",lbl.getText());
            ArrayList<Products>plist = new ArrayList<>();
            
            for(orderList ol : orderData){
                plist.add(new Products(ol.getProductsName(),""+ol.getPrice(),""+ol.getQuantity(),""+ol.getAmount()));
            }
            JRBeanCollectionDataSource jcs= new JRBeanCollectionDataSource(plist);
            JasperPrint jp = JasperFillManager.fillReport(jr, para, jcs);
            JasperViewer.viewReport(jp);
            
                    
        } catch (JRException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
