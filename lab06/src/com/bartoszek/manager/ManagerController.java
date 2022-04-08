package com.bartoszek.manager;
import bilboards.IBillboard;
import bilboards.IManager;
import bilboards.Order;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class ManagerController extends UnicastRemoteObject implements IManager, Serializable {

    private static final long serialVersionUID = 1L;
    private ManagerModel managerModel;
    private transient ManagerView managerView;
    private int orderNumber;

    public ManagerController(ManagerModel m, ManagerView v) throws RemoteException {
        super();
        this.managerModel = m;
        this.managerView = v;
        this.orderNumber = 1;
        initView();
    }

    public ManagerModel getManagerModel() {
        return managerModel;
    }

    public void initView() {
        //TODO
    }

    public void initController() {
        managerView.getStartThreadBtn().addActionListener(e -> {
            try {
                startDisplayingAd();
            } catch (RemoteException remoteException) {
                remoteException.printStackTrace();
            }
        });
        managerView.getStopThreadBtn().addActionListener(e-> {
            try {
                stopDisplayingAdd();
            } catch (RemoteException remoteException) {
                remoteException.printStackTrace();
            }
        });
    }

    private void showUpdatedRegisteredTablesIDs(){
        List<Integer> integerList = managerModel.getListOfBillboards().keySet().stream().sorted().collect(Collectors.toList());
        managerView.getRegisteredTableIdJComboBox().removeAllItems();
        for(Integer id:integerList){
            managerView.getRegisteredTableIdJComboBox().addItem(id.toString());
        }

    }
    private void stopDisplayingAdd() throws RemoteException {
        if(getSelectedAd()!=-1){
            managerModel.getListOfBillboards().get(getSelectedAd()).stop();
        }
    }

    private void startDisplayingAd() throws RemoteException {
        if(getSelectedAd()!=-1){
            managerModel.getListOfBillboards().get(getSelectedAd()).start();
            managerModel.getListOfBillboards().get(getSelectedAd()).start();
        }
    }
    private int getSelectedAd(){

        Object selectedItem = managerView.getRegisteredTableIdJComboBox().getSelectedItem();
        if(selectedItem!=null){
            return Integer.parseInt(selectedItem.toString());
        }else{
            return -1;
        }
    }

    @Override
    public int bindBillboard(IBillboard billboard) throws RemoteException {

        int i = managerModel.addBillboard(billboard);

        showUpdatedTablesInfo();
        showUpdatedRegisteredTablesIDs();
        return i;
    }

    @Override
    public boolean unbindBillboard(int billboardId) throws RemoteException {
        boolean b = managerModel.removeBillboard(billboardId);
        showUpdatedTablesInfo();
        showUpdatedRegisteredTablesIDs();
        return b;
    }

    @Override
    public boolean placeOrder(Order order) throws RemoteException {

        Map<Integer, IBillboard> listOfBillboards = managerModel.getListOfBillboards();
        Collection<IBillboard> values = listOfBillboards.values();
        for (IBillboard iBillboard : values) {
            int[] capacity = iBillboard.getCapacity();
            int notOccupiedPlaces = capacity[1];
            if (notOccupiedPlaces > 0) {
                order.getClient().setOrderId(orderNumber);
                managerModel.addOrder(order);
                iBillboard.addAdvertisement(order.getAdvertText(), order.getDisplayPeriod(), orderNumber);
                showUpdatedTablesInfo();
                //for junit test
                //order.orderArrayList=managerModel.getListOfOrders();
                orderNumber++;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean withdrawOrder(int orderId) throws RemoteException {
        boolean result = managerModel.getListOfBillboards().get(orderId).removeAdvertisement(orderId);
        showUpdatedTablesInfo();
        return result;
    }

    private String getAllRegisteredBillboards() throws RemoteException {
        StringBuilder stringBuilder = new StringBuilder();
        Map<Integer, IBillboard> listOfBillboards = managerModel.getListOfBillboards();
        stringBuilder.append("--------------Zarejestrowane tablice ----------------------");
        for (Map.Entry<Integer, IBillboard> billboard : listOfBillboards.entrySet()) {
            int[] capacity = billboard.getValue().getCapacity();
            stringBuilder.append("\n-------------------------------------------------------------------\n");
            stringBuilder.append("Tablica id: " +billboard.getKey() );
            stringBuilder.append("\nLiczba wszystkich miejsc: " + capacity[0]);
            stringBuilder.append("\nLiczba wolnych miejsc: " + capacity[1]);
            stringBuilder.append("\n-------------------------------------------------------------------\n");
        }
        return stringBuilder.toString();
    }
    private void showUpdatedTablesInfo() throws RemoteException {

       managerView.getTablesDataTextField().setText(getAllRegisteredBillboards());
    }


}