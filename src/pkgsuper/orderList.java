/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgsuper;

/**
 *
 * @author rahat
 */
public class orderList {
    private int No;
    private  String Barcode;
    private  String ProductsName;
    private  double Price;
    private  int Quantity;
    private double Amount;

    public orderList(int No, String Barcode, String ProductsName, double Price, int Quantity, double Amount) {
        this.No = No;
        this.Barcode = Barcode;
        this.ProductsName = ProductsName;
        this.Price = Price;
        this.Quantity = Quantity;
        this.Amount = Amount;
    }

    /**
     * @return the No
     */
    public int getNo() {
        return No;
    }

    /**
     * @param No the No to set
     */
    public void setNo(int No) {
        this.No = No;
    }

    /**
     * @return the Barcode
     */
    public String getBarcode() {
        return Barcode;
    }

    /**
     * @param Barcode the Barcode to set
     */
    public void setBarcode(String Barcode) {
        this.Barcode = Barcode;
    }

    /**
     * @return the ProductsName
     */
    public String getProductsName() {
        return ProductsName;
    }

    /**
     * @param ProductsName the ProductsName to set
     */
    public void setProductsName(String ProductsName) {
        this.ProductsName = ProductsName;
    }

    /**
     * @return the Price
     */
    public double getPrice() {
        return Price;
    }

    /**
     * @param Price the Price to set
     */
    public void setPrice(double Price) {
        this.Price = Price;
    }

    /**
     * @return the Quantity
     */
    public int getQuantity() {
        return Quantity;
    }

    /**
     * @param Quantity the Quantity to set
     */
    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    /**
     * @return the Amount
     */
    public double getAmount() {
        return Amount;
    }

    /**
     * @param Amount the Amount to set
     */
    public void setAmount(double Amount) {
        this.Amount = Amount;
    }

   

   
}