package com.bartoszek.manager;

import bilboards.IBillboard;
import bilboards.IManager;
import bilboards.Order;
import com.bartoszek.bilboard.TableController;
import com.bartoszek.bilboard.TableModel;
import com.bartoszek.bilboard.TableView;
import com.bartoszek.client.ClientController;
import com.bartoszek.client.ClientModel;
import com.bartoszek.client.ClientView;
import org.junit.jupiter.api.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("--- Manager Controller ---")
class ManagerControllerTest {
    private static final String host = "localhost";
    private static final int port = 1099;
    private static final String service = "service";
    private static Registry createRegistry;
    private static Registry getRegistry;
    private static Registry registryOne;
    private static Registry registryTwo;
    private IManager iManager;
    private ManagerModel managerModel;
    private ManagerView managerView;
    private ManagerController managerController;
    private ClientModel clientModel;
    private ClientView clientView;
    private ClientController clientController;


    @BeforeAll
    static void createRegistryAndGetRegistry() throws RemoteException {
        createRegistry = LocateRegistry.createRegistry(port);
        getRegistry = LocateRegistry.getRegistry(port);
        //ater table registers
        registryOne=LocateRegistry.createRegistry(1080);
        registryTwo=LocateRegistry.getRegistry(1080);

    }

    @BeforeEach
    void exportManagerObjectAsRemoteAndGetStubForClient() throws RemoteException, NotBoundException {
        managerModel = new ManagerModel();
        managerView = new ManagerView("manager title");
        managerController = new ManagerController(managerModel, managerView);
        createRegistry.rebind(service, managerController);
        iManager = (IManager) getRegistry.lookup(service);
        clientModel = new ClientModel();
        clientView = new ClientView("client title");
        clientController = new ClientController(clientModel, clientView, iManager);
    }

    @Nested
    @DisplayName("After manager gets order from a client")
    class AfterClientOrder {
        private Order firstOrder;
        private Order secondOrder;

        @BeforeEach
        void init() {
            firstOrder = new Order("To jest moje pierwsze ogłoszenie", Duration.ofHours(40), clientController);
            secondOrder = new Order("To jest moje drugie ogłoszenie", Duration.ofHours(30), clientController);
        }

        @Test
        @DisplayName("Manager Stores client Advertisement in Model Layer")
        void managerShouldStoreOrderFromTheClient() throws RemoteException {

            managerController.getManagerModel().addOrder(firstOrder);
            List<Order> listOfOrders = managerController.getManagerModel().getListOfOrders();
            assertThat(listOfOrders).contains(firstOrder);
        }
    }

    @Nested
    @DisplayName("After table registers in manager")
    class TableRegistersInManager {
        private TableModel tableModel;
        private TableController tableController;
        private TableView tableView;
        private String service = "tablica";
        private IBillboard iBillboardOne;


        @BeforeEach
        void initializeTables() throws RemoteException, NotBoundException {
            tableView = new TableView("table view one");
            tableModel = new TableModel();
            tableModel.setTotalCapacity(5);
            tableController = new TableController(tableModel, tableView,null);
           // tableController.setDisplayInterval(Duration.ofSeconds(3000));
            registryOne.rebind(service, tableController);
            iBillboardOne = (IBillboard) registryTwo.lookup(service);
        }

        @Test
        @DisplayName("Manager should have registered tables in model layer")
        void managerShouldRegisterTables() throws RemoteException {
            managerController.bindBillboard(iBillboardOne);
            Map<Integer, IBillboard> listOfBillboards = managerController.getManagerModel().getListOfBillboards();
            assertThat(listOfBillboards).containsValue(iBillboardOne);
        }
        @Test
        @DisplayName("Get all registered billboards")
            void shouldGetAllRegisteredBillboards() throws RemoteException {
                managerController.bindBillboard(iBillboardOne);
                List<Integer> registeredIds=managerController.getManagerModel().getRegisteredTablesID();
                assertThat(registeredIds.size()).isEqualTo(1);
            }


        @Test
        @DisplayName("Manager should be able to unbind table in model from model layer")
        void managerShouldBeAbleToUnbindTableFromModelLayer() throws RemoteException {
            managerController.bindBillboard(iBillboardOne);
            managerController.unbindBillboard(1);
            Map<Integer, IBillboard> listOfBillboards = managerController.getManagerModel().getListOfBillboards();
            assertThat(listOfBillboards).doesNotContainValue(iBillboardOne);

        }

        @Test
        @DisplayName("Manager should add Billboard to his Panel")
        void managerShouldAddBillboardsToHisPanel() throws RemoteException, InterruptedException {
            managerController.bindBillboard(iBillboardOne);

            Thread.sleep(5000);
        }
   }
}


