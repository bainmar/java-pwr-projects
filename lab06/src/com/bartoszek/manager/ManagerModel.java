package com.bartoszek.manager;

import bilboards.IBillboard;
import bilboards.Order;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class ManagerModel implements Serializable {

    private final long serialVersionUID = 1L;
    private int billboardID=1;
    private Map<Integer,IBillboard> listOfBillboards;
    private List<Order> listOfOrders;

    public ManagerModel() {
        listOfBillboards=new HashMap<>();
        listOfOrders=new ArrayList<>();
    }
    public int addBillboard(IBillboard billboard){
        listOfBillboards.put(billboardID,billboard);
        return billboardID++;
    }
    public boolean removeBillboard(int billboard){
        if(listOfBillboards.containsKey(billboard)){
            listOfBillboards.remove(billboard);
            return true;
        }
        return false;
    }
    private boolean removeOrder(int orderId){
        return false;
    }
    public boolean addOrder(Order order){
        return listOfOrders.add(order);
    }
    public List<Order> getListOfOrders(){
        return listOfOrders;
    }

    public Map<Integer, IBillboard> getListOfBillboards() {
        return listOfBillboards;
    }

    public List<Integer> getRegisteredTablesID() {
        return listOfBillboards.keySet().stream().sorted().collect(Collectors.toList());
    }
}
