package com.bartoszek.client;
import bilboards.IManager;
import bilboards.Order;
import com.bartoszek.manager.ManagerController;
import com.bartoszek.manager.ManagerModel;
import com.bartoszek.manager.ManagerView;
import org.junit.jupiter.api.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.Duration;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("|-- Client Controller --|")
class ClientControllerTest {
    private static final String host = "localhost";
    private static final int port = 1097;
    private static final String service = "service2";
    private static Registry createRegistry;
    private static Registry getRegistry;
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
    }

    @BeforeEach
    void exportManagerObjectAsRemoteAndGetStubForClient() throws RemoteException, NotBoundException {
        managerModel = new ManagerModel();
        managerView = new ManagerView("manager title2");
        managerController = new ManagerController(managerModel, managerView);
        createRegistry.rebind(service, managerController);
        iManager = (IManager) getRegistry.lookup(service);
        clientModel = new ClientModel();
        clientView = new ClientView("client title2");
        clientController = new ClientController(clientModel, clientView, iManager);
    }

    @Nested
    @DisplayName("When client add adevertisement")
    class AddingAdvertisement{
        private Order firstOrder;
        private Order secondOrder;

        @BeforeEach
        void init() {
            firstOrder = new Order("To jest moje pierwsze ogłoszenie", Duration.ofHours(40), clientController);
            secondOrder = new Order("To jest moje drugie ogłoszenie", Duration.ofHours(30), clientController);
        }

        @Test
        @DisplayName("Controller added order to model")
        void controllerShouldAddOrderToModel() {
            clientController.addOrder(firstOrder);
            assertThat(clientModel.getListOfOrders()).contains(firstOrder);
        }

        @Test
        @DisplayName("Client see updated table when he make order")
        void clientSeeRefreshedTableAfterHeOrderedAdd(TestInfo testInfo) {
            System.out.println("---------- " + testInfo.getDisplayName() + " --------");
            clientController.addOrder(firstOrder);
            clientController.addOrder(secondOrder);
            String ordersInformation = clientController.getOrdersInformation();
            System.out.println(ordersInformation);
        }

        @Disabled
        @Test
        @DisplayName("When advertisement can be accepted,client gets ID")
        void clientShouldGetIdWhenAdvertisementIsAccepted(){
            clientController.sendOrder(firstOrder);
            clientController.sendOrder(secondOrder);
            List<Order> listOfOrders = clientModel.getListOfOrders();
            Order order = listOfOrders.get(listOfOrders.size() - 1);
            assertThat(order.getOrderId()).isEqualTo(2);
        }
    }
    @Nested
    @DisplayName("When client remove advertisement")
    class RemovingAdvertisement{
        private Order firstOrder;
        private Order secondOrder;

        @BeforeEach
        void init() {
            firstOrder = new Order("To jest moje pierwsze ogłoszenie", Duration.ofHours(40), clientController);
            secondOrder = new Order("To jest moje drugie ogłoszenie", Duration.ofHours(30), clientController);
        }
        @Test
        @DisplayName("Controller removed order from model")
        void controllerShouldRemoveOrderFromModel() {
            clientController.addOrder(firstOrder);
            clientController.addOrder(secondOrder);
            clientController.removeOrder(secondOrder);
            assertThat(clientModel.getListOfOrders()).doesNotContain(secondOrder);
        }
    }

    @Disabled
    @Nested
    @DisplayName("When client withdraw advertisement")
    class WithdrawAdvertisement{
        private Order firstOrder;
        private Order secondOrder;
        @BeforeEach
        void init() {
            firstOrder = new Order("To jest moje pierwsze ogłoszenie", Duration.ofHours(40), clientController);
            secondOrder = new Order("To jest moje drugie ogłoszenie", Duration.ofHours(30), clientController);
        }

        @Test
        @DisplayName("Client should get all id of ordered advertisements")
        void clientShouldBeAbleToGetAllAdvertisementsIds(){
            clientController.sendOrder(firstOrder);
            clientController.sendOrder(secondOrder);
            List<Order> acceptedOrders=clientController.getAcceptedOrders();
            assertThat(acceptedOrders).contains(firstOrder,secondOrder);
        }
        @Test
        @DisplayName("Client can withdraw order from manager")
        void clientShouldBeAbleToWithdrawAdvertisement(){
            clientController.sendOrder(firstOrder);
            clientController.sendOrder(secondOrder);
            List<Order> acceptedOrders=clientController.getAcceptedOrders();
            Order order=clientController.getOrderWithId(1);
            clientController.withdrawOrder(1);
            List<Order> acceptedOrders1 = clientController.getAcceptedOrders();
            assertThat(acceptedOrders1).doesNotContain(order);
        }
    }
}


