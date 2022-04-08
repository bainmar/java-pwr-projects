package com.bartoszek.bilboard;
import org.junit.jupiter.api.*;
import java.rmi.RemoteException;
import java.time.Duration;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class TableControllerTest {
    private TableView tableView;
    private TableModel tableModel;
    private TableController tableController;

    @BeforeEach
    @DisplayName("Initialize table")
    void initialize() throws RemoteException {
        tableView=new TableView("table view 1");
        tableModel=new TableModel();
        tableController = new TableController(tableModel, tableView,null);
    }
    @Nested
    @DisplayName("|--- Add, remove  advertisements---|")
    class AddAdvertisements{
        String add="To jest moja reklama";
        Duration duration= Duration.ofHours(20);
        int id =1;

        String add2="To jest druga reklama";
        Duration duration2 =Duration.ofHours(40);
        int id2=2;

        @Test
        @DisplayName("Advertisement is added to billboard by manager")
        void managerShouldBeAbleAddAdvertisementToBillboard () throws RemoteException {
            tableController.setTotalCapacity(5);
            tableController.addAdvertisement(add,duration,id);
            assertThat(tableController.getAdvertisements().size()).isEqualTo(1);
        }
        @Test
        @DisplayName("Manager can't add advertisement when there is no empty space in billboard")
        public void managerCantAddAdvertisementToBillboardWhenThereIsNoSpace() throws RemoteException {
            tableController.setTotalCapacity(0);
            tableController.addAdvertisement(add,duration,id);
            List<Advertisement> advertisements = tableController.getAdvertisements();
            assertThat(advertisements.size()).isEqualTo(0);
        }
        @Test
        @DisplayName("Manager can remove advertisement by id")
        void managerShouldBeAbleToRemoveAdvertisementFromBillboard() throws RemoteException {
            tableController.setTotalCapacity(5);
            tableController.addAdvertisement(add,duration,id);
            tableController.removeAdvertisement(1);
            assertThat(tableController.getAdvertisements().size()).isEqualTo(0);
        }

        @Test
        @DisplayName("Table show Adds Alternately")
        void tableShouldShowAdsAlternately() throws RemoteException, InterruptedException {
            tableController.setTotalCapacity(2);
            tableController.addAdvertisement(add,duration,id);
            tableController.addAdvertisement(add2,duration2,id2);
            tableController.setDisplayInterval(Duration.ofMillis(2000));
            tableController.start();
            Thread.sleep(10000);
        }


    }

}
