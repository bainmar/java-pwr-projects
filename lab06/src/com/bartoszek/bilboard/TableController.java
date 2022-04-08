package com.bartoszek.bilboard;

import bilboards.IBillboard;
import bilboards.IManager;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.Duration;

import java.util.List;

public class TableController extends UnicastRemoteObject implements IBillboard, Serializable {
    private TableModel tableModel;
    private transient TableView tableView;
    private IManager iManager;
    private boolean isWorking;


    public TableController(TableModel tableModel, TableView tableView, IManager iManager) throws RemoteException {
        super();
        this.tableModel = tableModel;
        this.tableView = tableView;
        this.iManager=iManager;
    }

    public void initController() throws RemoteException {
        int id=iManager.bindBillboard(this);
        tableModel.setBillboardID(id);
        tableView.getUnbindButton().addActionListener(e-> {
            try {
                unbindTable();
            } catch (RemoteException remoteException) {
                remoteException.printStackTrace();
            }
        });
    }

    private void unbindTable() throws RemoteException {
        int billboardID = tableModel.getBillboardID();
        iManager.unbindBillboard(billboardID);
        System.exit(1);
    }

    @Override
    public boolean addAdvertisement(String advertText, Duration displayPeriod, int orderId) throws RemoteException {
        return tableModel.addAdvertisement(advertText, displayPeriod, orderId);
    }

    @Override
    public boolean removeAdvertisement(int orderId) throws RemoteException {
        return tableModel.removeAdvertisement(orderId);
    }

    @Override
    public int[] getCapacity() throws RemoteException {
        return new int[]{tableModel.getTotalCapacity(), tableModel.getNumberOfVacancies()};
    }

    @Override
    public void setDisplayInterval(Duration displayInterval) throws RemoteException {
        tableModel.setTimeToDisplay(displayInterval);
    }

    @Override
    public boolean start() throws RemoteException {
        isWorking = true;
        new DisplayAdvertisements().start();
        return true;
    }

    @Override
    public boolean stop() throws RemoteException {
        isWorking = false;
        return true;
    }

    public List<Advertisement> getAdvertisements() {
        return tableModel.getListOfAdvertisements();
    }

    public void setTotalCapacity(int i) {
        tableModel.setTotalCapacity(i);
    }

    class DisplayAdvertisements extends Thread {
        private final Object lock = new Object();

        @Override
        public void run() {

                List<Advertisement> advertisements = getAdvertisements();
                while (isWorking) {
                    for (Advertisement advertisement : advertisements) {
                        if (!(advertisement.getDuration().minus(advertisement.getDisplayedTime()).isNegative())) {
                            advertisement.setDisplayedTime(advertisement.getDisplayedTime().plus(tableModel.getTimeToDisplay()));
                            tableView.getTableAddJTextArea().setText("");
                            tableView.getTableAddJTextArea().setText(advertisement.toString());
                            try {
                                DisplayAdvertisements.sleep(tableModel.getTimeToDisplay().toMillis());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                removeAdvertisement(advertisement.getId());
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    }

            }
        }
    }

}
