package com.bartoszek.manager;
import java.awt.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ManagerApp {
    public static void main(String[] args) throws RemoteException {
        if(args.length<2){
            System.out.println("<port><service>");
        }else{

            int port = Integer.parseInt(args[0]);
            String service = args[1];
            Registry registry= LocateRegistry.createRegistry(port);

            EventQueue.invokeLater(() -> {
                ManagerModel m = new ManagerModel();
                ManagerView v = new ManagerView("Lab06-RMI-manager");
                ManagerController c = null;
                try {
                    c = new ManagerController(m,v);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                c.initController();
                try {
                    registry.rebind(service,c);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}
