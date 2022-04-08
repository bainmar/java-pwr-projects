package com.bartoszek.client;
import bilboards.IManager;
import java.awt.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientApp {
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
                        ClientModel m = new ClientModel();
                        ClientView v = new ClientView("Lab06-RMI-client");
                        ClientController c = null;
                        try {
                            c = new ClientController(m, v,iManager);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        c.initController();
                    }
                );
            }
        }
    }
