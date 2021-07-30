package pkgsuper;

/**
 *
 * @author rahat
 */
public class ProductList {
    
    private  String ProductsName;
    private  String Price;
    private  String Quantity;
    private  String Barcode;
    

    public ProductList(String ProductsName, String Price, String Quantity, String Barcode) {
        this.ProductsName = ProductsName;
        this.Price = Price;
        this.Quantity = Quantity;
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
    public String getPrice() {
        return Price;
    }

    /**
     * @param Price the Price to set
     */
    public void setPrice(String Price) {
        this.Price = Price;
    }

    /**
     * @return the Quantity
     */
    public String getQuantity() {
        return Quantity;
    }

    /**
     * @param Quantity the Quantity to set
     */
    public void setQuantity(String Quantity) {
        this.Quantity = Quantity;
    }

    /**
     * @return the Model
     */
    public String getBarcode() {
        return Barcode;
    }

 
    public void setBarcode(String Barcode) {
        this.Barcode = Barcode;
    }
}