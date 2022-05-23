package business.layer;

import java.util.ArrayList;
import java.util.List;

public class CompositeProduct extends MenuItem{
    private String name;
    private List<BaseProduct> products = new ArrayList<>();
    private String firstProduct;
    private String secondProduct;
    private String thirdProduct;
    private int totalPrice;

    public CompositeProduct(String name, List<BaseProduct> products, String firstProduct, String secondProduct, String thirdProduct, int totalPrice) {
        this.products = products;
        this.name = name;
        this.firstProduct = firstProduct;
        this.secondProduct = secondProduct;
        this.thirdProduct = thirdProduct;
        this.totalPrice = totalPrice;
    }

    public int computePrice() {
        int price = 0;
        for (BaseProduct baseProduct : products) {
            price += baseProduct.getPrice();
        }
        return price;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<BaseProduct> getProducts() {
        return products;
    }
    public int getTotalPrice() {return totalPrice;}
    public void setTotalPrice(int totalPrice) {this.totalPrice = totalPrice;}
    public String getFirstProduct() {return firstProduct;}
    public void setFirstProduct(String firstProduct) {this.firstProduct = firstProduct;}
    public String getSecondProduct() {return secondProduct;}
    public void setSecondProduct(String secondProduct) {this.secondProduct = secondProduct;}
    public String getThirdProduct() {return thirdProduct;}
    public void setThirdProduct(String thirdProduct) {this.thirdProduct = thirdProduct;}
}
