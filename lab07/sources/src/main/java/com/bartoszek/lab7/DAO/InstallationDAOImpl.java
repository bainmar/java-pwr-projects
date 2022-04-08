package com.bartoszek.lab7.DAO;

import com.bartoszek.lab7.DTO.Client;
import com.bartoszek.lab7.DTO.Installation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class InstallationDAOImpl implements InstallationDAO {
    private Connection connection;

    public InstallationDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Installation getInstallation(Integer installationID) {
        try {
            PreparedStatement ps = connection.prepareStatement("Select * from Installation where ID=?");
            ps.setInt(1, installationID);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return extractInstallationFromResultSet(resultSet);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private Installation extractInstallationFromResultSet(ResultSet resultSet) throws SQLException {
        Installation installation = new Installation();
        installation.setID(resultSet.getInt("ID"));
        installation.setAddress(resultSet.getString("address"));
        installation.setRouterNumber(resultSet.getInt("routerNumber"));
        installation.setServiceType(resultSet.getString("serviceType"));
        installation.setClientID(new Client(null, null, resultSet.getInt("clientID")));
        return installation;
    }

    @Override
    public Set<Installation> getAllInstallations() {
        try {
            PreparedStatement ps = connection.prepareStatement("Select * from Installation");
            Set<Installation> installationSet = new HashSet<>();
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                installationSet.add(extractInstallationFromResultSet(resultSet));
            }
            return installationSet;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertInstallation(Installation installation) {
        try {
            PreparedStatement ps = connection.prepareStatement("Insert into Installation (ID,address,routerNumber,serviceType,clientID) values(default,?,?,?,?)");
            ps.setString(1, installation.getAddress());
            ps.setInt(2, installation.getRouterNumber());
            ps.setString(3, installation.getServiceType());
            ps.setInt(4, installation.getClientID().getID());
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
    public boolean removeInstallation(Integer installationID) {
        try {
            PreparedStatement ps = connection.prepareStatement("Delete from Installation where ID=?");
            ps.setInt(1, installationID);
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
    public boolean updateInstallation(Installation installation) {
        try {
            PreparedStatement ps = connection.prepareStatement("Update Installation set address=?,routerNumber=?,serviceType=?,clientID=? where ID=?");
            ps.setString(1, installation.getAddress());
            ps.setInt(2, installation.getRouterNumber());
            ps.setString(3, installation.getServiceType());
            ps.setInt(4, installation.getClientID().getID());
            ps.setInt(5,installation.getID());
            int i = ps.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
