package com.bartoszek.lab7.DAO;

import com.bartoszek.lab7.DTO.Client;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ClientDAOImpl implements ClientDAO {

    private Connection connection;

    public ClientDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Client getClient(Integer clientID) {
        try {
            PreparedStatement ps = connection.prepareStatement("Select * from Client " +
                    "where ID=?");
            ps.setInt(1, clientID);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return extractClientFromResultSet(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<Client> getAllClients() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from Client");
            Set<Client> clients = new HashSet<>();
            while (rs.next()) {
                Client client = extractClientFromResultSet(rs);
                clients.add(client);
            }
            return clients;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertClient(Client client) {
        try {
            PreparedStatement ps = connection.prepareStatement("Insert into Client(ID,name,surname,clientNumber) values(default,?,?,?)");
            ps.setString(1, client.getName());
            ps.setString(2, client.getSurname());
            ps.setInt(3, client.getClientNumber());
            int i = ps.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateClient(Client client) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE Client set name=?,surname=?,clientNumber=? where ID=?");
            ps.setString(1, client.getName());
            ps.setString(2, client.getSurname());
            ps.setInt(3, client.getClientNumber());
            ps.setInt(4,client.getID());
            int i = ps.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeClient(Integer clientID) {
        try {
            PreparedStatement ps = connection.prepareStatement("Delete from Client where ID=?");
            ps.setInt(1, clientID.intValue());
            int i = ps.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    private Client extractClientFromResultSet(ResultSet resultSet) throws SQLException {
        Client client = new Client();
        client.setID(resultSet.getInt("ID"));
        client.setName(resultSet.getString("name"));
        client.setSurname(resultSet.getString("surname"));
        client.setClientNumber(resultSet.getInt("clientNumber"));
        return client;
    }
}
