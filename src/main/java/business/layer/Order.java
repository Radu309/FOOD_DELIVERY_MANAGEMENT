package business.layer;

import java.util.Hashtable;

public class Order {
    private int orderID;
    private int clientID;
    private int orderData;

    public Order(int orderID, int clientID, int orderData) {
        this.orderID = orderID;
        this.clientID = clientID;
        this.orderData = orderData;
    }

    public int getOrderID() {return orderID;}
    public void setOrderID(int orderID) {this.orderID = orderID;}
    public int getClientID() {return clientID;}
    public void setClientID(int clientID) {this.clientID = clientID;}
    public int getOrderData() {return orderData;}
    public void setOrderData(int orderData) {this.orderData = orderData;}
}
