package com.bartoszek.bilboard;
import bilboards.IManager;

import java.awt.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class TableApp {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        if (args.length < 3) {
            System.out.println("<host><port><service>");
        } else {
            String host = args[0];
            int port = Integer.parseInt(args[1]);
            String service = args[2];
            Registry registry = LocateRegistry.getRegistry(port);
            IManager iManager = (IManager) registry.lookup(service);

            EventQueue.invokeLater(() -> {
                TableModel m = new TableModel();
                m.setTotalCapacity(5);
                TableView v = new TableView("Lab06-RMI-table");
                TableController c = null;
                try {
                    c = new TableController(m, v,iManager);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                try {
                    c.initController();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
