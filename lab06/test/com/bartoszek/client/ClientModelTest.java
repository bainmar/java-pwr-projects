
package com.bartoszek.client;

import bilboards.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.time.Duration;
import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("|--Client Model--|")
class ClientModelTest {
    private ClientModel clientModel;
    private ClientView clientView;
    private ClientController clientController;

    @BeforeEach
    void init() throws RemoteException {
        clientModel = new ClientModel();
        clientView = new ClientView("clientView");
        clientController = new ClientController(clientModel, clientView, null);

    }

    @Test
    @DisplayName("added order is added to client model orders")
    void addOrderShouldAddedToUserOrders() {
        Order order = new Order("To jest moje pierwsze ogłoszenie", Duration.ofHours(40), clientController);
        clientModel.addOrder(order);
        assertThat(clientModel.getListOfOrders()).contains(order);
    }
}



