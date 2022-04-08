package com.bartoszek;

import com.bartoszek.lab7.DAO.*;
import com.bartoszek.lab7.DB;
import com.bartoszek.lab7.DTO.*;
import org.junit.jupiter.api.*;

import java.sql.*;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class DatabaseConnectionTest {
    private static Connection conn;
    private static ClientDAOImpl clientDAOimpl;
    private static PriceListDAOImpl priceListDAOimpl;
    private static InstallationDAOImpl installationDAOimpl;
    private static PaymentDAOImpl paymentDAOImpl;
    private static ChargeDAOImpl chargeDAOImpl;

    @BeforeAll
    public static void initializeConnection() throws SQLException {
        conn = DB.getConnection();
        clientDAOimpl = new ClientDAOImpl(conn);
        priceListDAOimpl = new PriceListDAOImpl(conn);
        installationDAOimpl = new InstallationDAOImpl(conn);
        paymentDAOImpl = new PaymentDAOImpl(conn);
        chargeDAOImpl = new ChargeDAOImpl(conn);

    }

    @AfterAll
    public static void connectionClose() throws SQLException {
        conn.close();
    }


    //cennikiem manipulowanie,klientem i danymi instalacji

    //naliczać opłaty i wysyłać monity

    //reczne rejestrowanie wpłat oraz nanoszenie korekt

    //przeglądanie należności i wplat


    @Nested
    @DisplayName("----------------Client CRUD------------------------")
    class ClientCRUD {
        @Test
        @DisplayName("Add client to table 'Client'")
        void shouldAddClientToDatabase(TestInfo testInfo) {
            Client client = new Client("Jakub", "Bartoszek", 103);
            System.out.println("Metoda: " + testInfo.getDisplayName());
            boolean b = clientDAOimpl.insertClient(client);
            assertThat(b).isEqualTo(true);
        }

        @Test
        @DisplayName("Get Client from 'Client' with know ID")
        void shouldGetUserFromDB(TestInfo testInfo) {
            int ID = 5;
            System.out.println("Metoda: " + testInfo.getDisplayName());
            Client client = clientDAOimpl.getClient(ID);
            System.out.println(client);
            assertThat(client).isNotNull();
        }

        @Test
        @DisplayName("Get all clients from table 'Client'")
        void shouldGetAllClientsFromTableClient(TestInfo testInfo) {
            System.out.println("Metoda: " + testInfo.getDisplayName());
            Set<Client> clients = clientDAOimpl.getAllClients();
            for (Client client : clients) {
                System.out.println(client);
            }
            assertThat(clients).isNotEmpty();
        }
        @Test
        @DisplayName("Update client form table 'Client'")
        void clientFromTableClientCanBeUpdated(TestInfo testInfo) {
            Client client = new Client(5, "Ktos", "Cos", 300);
            System.out.println("Metoda: " + testInfo.getDisplayName());
            boolean b = clientDAOimpl.updateClient(client);
            assertThat(b).isEqualTo(true);
        }

        @Disabled
        @Test
        @DisplayName("Remove client from table 'Client' based on ID")
        void shouldRemoveClientFromTableClient(TestInfo testInfo) {
            int ID = 6;
            System.out.println("Metoda: " + testInfo.getDisplayName());
            boolean b = clientDAOimpl.removeClient(ID);
            assertThat(b).isEqualTo(true);
        }
    }

    @Nested
    @DisplayName("--------------------PriceList CRUD------------------")
    class PriceListCRUD {
        @Test
        @DisplayName("Add PriceList to  table 'PriceList'")
        void priceListIsAddedToTablePriceList(TestInfo testInfo) {
            System.out.println("Metoda: " + testInfo.getDisplayName());
            Client client = new Client(5, "Jan", "Los", 105);
            PriceList priceList = new PriceList("internet mobilny", "internet24h", client);
            boolean b = priceListDAOimpl.insertPriceList(priceList);
            assertThat(b).isEqualTo(true);
        }
        @Test
        @DisplayName("Get PriceList from 'PriceList' based on ID")
        void getPriceListWithSpecifiedID(TestInfo testInfo) {
            int ID = 3;
            System.out.println("Metoda: " + testInfo.getDisplayName());
            PriceList priceList = priceListDAOimpl.getPriceList(ID);
            System.out.println(priceList);
        }

        @Test
        @DisplayName("Get all PriceLists from 'PriceList' table")
        void getAllPriceLists(TestInfo testInfo) {
            System.out.println("Metoda: " + testInfo.getDisplayName());
            Set<PriceList> allPriceLists = priceListDAOimpl.getAllPriceLists();
            for (PriceList priceList : allPriceLists) {
                System.out.println(priceList);
            }
        }

        @Test
        @DisplayName("Update PriceList from table 'PriceList' basedOnID")
        void updatePriceListElement(TestInfo testInfo) {
            System.out.println("Metoda: " + testInfo.getDisplayName());
            Client client = new Client(5, "Jan", "Los", 105);
            PriceList priceList = new PriceList(3, "internet bez limitu", "fullpack", client);
            boolean b = priceListDAOimpl.updatePriceList(priceList);
            assertThat(b).isEqualTo(true);
        }

        @Disabled
        @Test
        @DisplayName("Remove PriceList element from table 'Price List' based on ID")
        void removePriceListElement(TestInfo testInfo) {
            System.out.println("Metoda: " + testInfo.getDisplayName());
            int ID = 1;
            boolean b = priceListDAOimpl.removePriceList(ID);
            assertThat(b).isEqualTo(true);
        }
    }

    @Nested
    @DisplayName("------------------------Installation CRUD ----------------")
    class InstallationCRUD {
        @Test
        @DisplayName("Insert new installation to table 'Installation'")
        void insertNewInstallation(TestInfo testInfo) {
            System.out.println("Metoda: " + testInfo.getDisplayName());
            Client client = new Client(5, "marcin", "jaskolka", 2);
            Installation installation = new Installation("Wasilewskiego 11", 99, "usluga", client);
            boolean b = installationDAOimpl.insertInstallation(installation);
            assertThat(b).isEqualTo(true);
        }

        @Test
        @DisplayName("Get Installation from table 'Installation' with known ID")
        void getInstallationWithKnownID(TestInfo testInfo) {
            System.out.println("Metoda: " + testInfo.getDisplayName());
            int ID = 4;
            Installation installation = installationDAOimpl.getInstallation(ID);
            System.out.println(installation);
            assertThat(installation).isNotNull();
        }

        @Test
        @DisplayName("Get all installations from table 'Installation'")
        void getAllInstallation(TestInfo testInfo) {
            System.out.println("Metoda: " + testInfo.getDisplayName());
            Set<Installation> installationSet = installationDAOimpl.getAllInstallations();
            for (Installation installation : installationSet) {
                System.out.println(installation);
            }
        }

        @Test
        @DisplayName("Update Installation from table 'Installation based on ID")
        void InstallationIsUpdated(TestInfo testInfo) {
            System.out.println("Metoda: " + testInfo.getDisplayName());
            Client client = new Client(5, "Marcin", "Bartoszek", 10);
            Installation installation = new Installation(4, "warszawa", 99, "zdalnie", client);
            boolean b = installationDAOimpl.updateInstallation(installation);
            assertThat(b).isEqualTo(true);
        }

        @Disabled
        @Test
        @DisplayName("Delete installation from table 'Installation' with know ID")
        void deleteInstallationWithKnownID(TestInfo testInfo) {
            System.out.println("Metoda: " + testInfo.getDisplayName());
            int ID = 1;
            boolean b = installationDAOimpl.removeInstallation(ID);
            assertThat(b).isEqualTo(true);
        }
    }

    @Nested
    @DisplayName("----------------------Payment-----------------------")
    class PaymentCRUD {
        @Test
        @DisplayName("Insert payment to 'Payment' table")
        void insertPaymentToPaymentTable(TestInfo testInfo) {
            System.out.println("Metoda: " + testInfo.getDisplayName());
            String str = "2015-03-31";
            Installation installation = new Installation(4, "wasiolka", 99, "cos", null);
            Date date = Date.valueOf(str);//converting string into sql date
            Payment payment = new Payment(date, 40000f, installation);
            boolean b = paymentDAOImpl.insertPayment(payment);
            assertThat(b).isEqualTo(true);
        }

        @Test
        @DisplayName("Get payment from table 'Payment' with known ID")
        void getPaymentWithKnownID(TestInfo testInfo) {
            System.out.println("Metoda: " + testInfo.getDisplayName());
            int ID = 3;
            Payment payment = paymentDAOImpl.getPayment(ID);
            System.out.println(payment);
            assertThat(payment).isNotNull();
        }

        @Test
        @DisplayName("Get all payments from table 'Payment'")
        void getAllPayments(TestInfo testInfo) {
            System.out.println("Metoda: " + testInfo.getDisplayName());
            Set<Payment> payments = paymentDAOImpl.getAllPayments();
            for (Payment payment : payments) {
                System.out.println(payment);
            }
            assertThat(payments).isNotEmpty();
        }

        @Test
        @DisplayName("Payment should be updated int table 'Payment' based on ID")
        void paymentShouldBeUpdated(TestInfo testInfo) {
            System.out.println("Metoda: " + testInfo.getDisplayName());
            String str = "2015-03-31";
            Date date = Date.valueOf(str);//converting string into sql date
            Installation installation = new Installation(4, "wasiolka", 99, "cos", null);
            Payment payment = new Payment(3, date, 40040f, installation);
            boolean b = paymentDAOImpl.updatePayment(payment);
            assertThat(b).isEqualTo(true);
        }

        @Disabled
        @Test
        @DisplayName("Payment should be removed from table 'Payment' based on ID")
        void shouldBeRemovedBasedOnID(TestInfo testInfo) {
            System.out.println("Metoda: " + testInfo.getDisplayName());
            int ID = 1;
            boolean b = paymentDAOImpl.removePayment(ID);
            assertThat(b).isEqualTo(true);
        }
    }

    @Nested
    @DisplayName("-----------------Charge CRUD ----------------------")
    class ChargeCrud {
        @Test
        @DisplayName("Add charge to table 'Charge'")
        void chargeShouldBeAddedToTableCharge(TestInfo testInfo) {
            System.out.println("Metoda: " + testInfo.getDisplayName());
            String str = "2015-04-10";
            Date date = Date.valueOf(str);//converting string into sql date
            Client client = new Client(5, null, null, 2);
            Installation installation = new Installation(4, null, null, null, client);
            Charge charge = new Charge(date, 40000.0f, installation);
            boolean b = chargeDAOImpl.insertCharge(charge);
            assertThat(b).isEqualTo(true);
        }

        @Test
        @DisplayName("Get charge from table 'Charge' based on ID")
        void getChargeFromTableChargeBasedOnID(TestInfo testInfo) {
            System.out.println("Metoda: " + testInfo.getDisplayName());
            int ID = 3;
            Charge charge = chargeDAOImpl.getCharge(ID);
            System.out.println(charge);
            assertThat(charge).isNotNull();
        }

        @Test
        @DisplayName("Get all charges from table 'Charge'")
        void getAllChargesFromTableCharge(TestInfo testInfo) {
            System.out.println("Metoda: " + testInfo.getDisplayName());
            Set<Charge> charges = chargeDAOImpl.getAllCharges();
            for (Charge charge : charges) {
                System.out.println(charge);
            }
            assertThat(charges).doesNotContainNull();
        }

        @Test
        @DisplayName("charge from table 'Charge' should be updated")
        void chargeFromTableChargeShouldBeUpdated(TestInfo testInfo) {
            System.out.println("Metoda: " + testInfo.getDisplayName());
            String str = "2020-04-10";
            Installation installation = new Installation(4, null, null, null, null);
            Date date = Date.valueOf(str);//converting string into sql date
            Charge charge = new Charge(3, date, 100000.0f, installation);
            boolean b = chargeDAOImpl.updateCharge(charge);
            assertThat(b).isEqualTo(true);
        }

        @Test
        @DisplayName("count charge for client from table 'Client'")
        void countChargeForClientFromTableClient(){
            Client client=new Client(5,"Marcin","Bartoszek",100);
            Float wynik=chargeDAOImpl.countChargesForClient(client);
            assertThat(wynik).isEqualTo(100000);
        }

        @Disabled
        @Test
        @DisplayName("Remove charge from table 'Charge' based on ID")
        void removeFromTableChargeBasedOnId(TestInfo testInfo) {
            System.out.println("Metoda: " + testInfo.getDisplayName());
            int ID = 3;
            boolean b = chargeDAOImpl.removeCharge(1);
            assertThat(b).isEqualTo(true);
        }
    }



}
