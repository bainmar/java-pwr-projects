package com.bartoszek.dao;

import java.util.Set;

import com.bartoszek.dto.Client;

public interface ClientDAO {
    Client getClient(Integer clientID);
    Set<Client> getAllClients();
    boolean insertClient(Client client);
    boolean updateClient(Client client);
    boolean removeClient(Integer clientID);

}
