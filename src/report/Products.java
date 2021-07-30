/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

/**
 *
 * @author rahat
 */
public class Products {
    private String Pname;
    private String price;
    private String qty;
    private String amount;

    public Products(String Pname, String price, String qty, String amount) {
        this.Pname = Pname;
        this.price = price;
        this.qty = qty;
        this.amount = amount;
    }

    /**
     * @return the Pname
     */
    public String getPname() {
        return Pname;
    }

    /**
     * @param Pname the Pname to set
     */
    public void setPname(String Pname) {
        this.Pname = Pname;
    }

    /**
     * @return the price
     */
    public String getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * @return the qty
     */
    public String getQty() {
        return qty;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty(String qty) {
        this.qty = qty;
    }

    /**
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

}