package com.bartoszek.lab7.DAO;

import com.bartoszek.lab7.DTO.Client;

import java.util.Set;

public interface ClientDAO {
    Client getClient(Integer clientID);
    Set<Client> getAllClients();
    boolean insertClient(Client client);
    boolean updateClient(Client client);
    boolean removeClient(Integer clientID);

}
