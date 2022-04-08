package com.bartoszek.client;

import bilboards.IClient;
import bilboards.IManager;
import bilboards.Order;

import javax.swing.*;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class ClientController extends UnicastRemoteObject implements IClient,Serializable {
    private static final long serialVersionUID = 1L;
    private  ClientModel clientModel;
    private  transient ClientView clientView;
    private IManager iManager;

    public ClientController(ClientModel clientModel, ClientView clientView, IManager iManager) throws RemoteException {
        super();
        this.clientModel = clientModel;
        this.clientView = clientView;
        this.iManager = iManager;
        initView();
    }

    public void initView() {

    }

    public void initController() {
        clientView.getSendOrderButton().addActionListener(e -> sendOrderBasedOnUIData());
        clientView.getRemoveOrderButton().addActionListener(e -> removeOrderFromUiData());
        clientView.getWithdrawOrderButton().addActionListener(e -> {
            try {
                withdrawOrderBasedOnUIData();
            } catch (RemoteException remoteException) {
                remoteException.printStackTrace();
            }
        });
    }

    private void withdrawOrderBasedOnUIData() throws RemoteException {
        Object selectedItem1 = clientView.getOrdersIdComboBox().getSelectedItem();
        if (selectedItem1 == null) return;
        String selectedItem = selectedItem1.toString();
        if (selectedItem != "") {
            for (Order order : getAcceptedOrders()) {
                if (order.getOrderId() == Integer.parseInt(selectedItem)) {
                    boolean withdrawn = iManager.withdrawOrder(order.getOrderId());
                    if (withdrawn) {
                        removeOrder(order);
                    }
                    updateMyAdvertisementsDetailsTextField();
                    return;
                }
            }
        }
    }
    private void updateMyAdvertisementsDetailsTextField(){
        clientView.getMyAdvertisementsDetailsTextField().setText(getOrdersInformation());
        List<Order> acceptedOrders = getAcceptedOrders();
        JComboBox<String> ordersIdComboBox = clientView.getOrdersIdComboBox();
        ordersIdComboBox.removeAllItems();
        acceptedOrders.stream().forEach(x -> ordersIdComboBox.addItem(Integer.toString(x.getOrderId())));
    }

    private void removeOrderFromUiData() {
        Object selectedItem1 = clientView.getOrdersIdComboBox().getSelectedItem();
        if (selectedItem1 == null) return;
        String selectedItem = selectedItem1.toString();
        if (selectedItem != "") {
            for (Order order : getAcceptedOrders()) {
                if (order.getOrderId() == Integer.parseInt(selectedItem)) {
                    withdrawOrder(order.getOrderId());
                    updateMyAdvertisementsDetailsTextField();
                    return;
                }
            }
        }
    }

    private void sendOrderBasedOnUIData() {
        String addText = clientView.getNewAdvertisementTextField().getText();
        String textDuration = clientView.getDisplayTimeTextField().getText();
        if(addText!=null&&textDuration!=null) {
            if (!textDuration.equals("")  && !addText.equals("")) {
                Duration duration = Duration.ofHours(Integer.parseInt(textDuration));
                Order order = new Order(addText, duration, this);
                sendOrder(order);
                repaintDataOnUserInterface();
            }
        }

    }

    private void repaintDataOnUserInterface() {
        clientView.getMyAdvertisementsDetailsTextField().setText(getOrdersInformation());
        clientView.getNewAdvertisementTextField().setText("");
        clientView.getDisplayTimeTextField().setText("");
    }

    public boolean withdrawOrder(int orderId) {
        Order orderWithId = getOrderWithId(orderId);
        return removeOrder(orderWithId);
    }

    public void sendOrder(Order order) {
        clientModel.addOrder(order);
        try {
            iManager.placeOrder(order);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setOrderId(int orderId) throws RemoteException {
        clientModel.setOrderId(orderId);
        updateMyAdvertisementsDetailsTextField();
    }

    public boolean addOrder(Order order) {
        boolean b = clientModel.addOrder(order);
        return b;
    }

    public boolean removeOrder(Order order) {
        boolean b = clientModel.removeOrder(order);
        return b;
    }

    public String getOrdersInformation() {
        StringBuilder builder = new StringBuilder();
        for (Order order : clientModel.getListOfOrders()) {
            builder.append(order);
            builder.append(System.getProperty("line.separator"));
        }
        return builder.toString();
    }

    public List<Order> getAcceptedOrders() {
        return clientModel.getListOfOrders().stream().filter(x -> x.getOrderId() != 0).collect(Collectors.toList());
    }

    public Order getOrderWithId(int orderId) {
        for (Order order : getAcceptedOrders()) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }
        return null;
    }
}
