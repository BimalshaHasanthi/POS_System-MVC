package model;

import java.util.ArrayList;

public class Record {
    private String orderId;
    private String orderDate;
    private String customerId;
    private String orderTime;
    private double orderCost;
    private ArrayList<OrderDetail> items;


    public Record(String orderId, String orderDate, String customerId, String orderTime, double orderCost) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerId = customerId;
        this.orderTime = orderTime;
        this.orderCost = orderCost;
    }

    public Record(String orderId, String orderDate, String customerId, String orderTime, double orderCost, ArrayList<OrderDetail> items) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerId = customerId;
        this.orderTime = orderTime;
        this.orderCost = orderCost;
        this.items = items;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public double getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(double orderCost) {
        this.orderCost = orderCost;
    }

    public ArrayList<OrderDetail> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrderDetail> items) {
        this.items = items;
    }
}
