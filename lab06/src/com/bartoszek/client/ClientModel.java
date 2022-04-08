package com.bartoszek.client;

import bilboards.Order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClientModel implements Serializable {
    private List<Order> listOfOrders;
    public ClientModel(){
        listOfOrders=new ArrayList<>();
    }
    public boolean addOrder(Order order){
        return listOfOrders.add(order);
    }
    public boolean removeOrder(Order order){
        return listOfOrders.remove(order);
    }
    public void setOrderId(int orderID){
        listOfOrders.get(listOfOrders.size()-1).setOrderId(orderID);
    }

    public List<Order> getListOfOrders() {
        return listOfOrders;
    }
}
