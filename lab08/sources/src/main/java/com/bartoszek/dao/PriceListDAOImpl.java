package com.bartoszek.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.bartoszek.dto.Client;
import com.bartoszek.dto.PriceList;

public class PriceListDAOImpl implements PriceListDAO {

    private Connection connection;

    public PriceListDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public PriceList getPriceList(Integer priceListID) {

        try {
            PreparedStatement ps = connection.prepareStatement("Select * from PriceList where ID=?");
            ps.setInt(1, priceListID);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                return extractPriceListFromResultSet(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<PriceList> getAllPriceLists() {
        try {
            PreparedStatement ps = connection.prepareStatement("Select * from PriceList");
            ResultSet rs = ps.executeQuery();
            Set<PriceList> priceLists = new HashSet<>();
            while (rs.next()) {
                PriceList priceList = extractPriceListFromResultSet(rs);
                priceLists.add(priceList);
            }
            return priceLists;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    private PriceList extractPriceListFromResultSet(ResultSet rs) throws SQLException {
        PriceList priceList = new PriceList();
        priceList.setID(rs.getInt("ID"));
        priceList.setType(rs.getString("priceType"));
        priceList.setServiceName(rs.getString("serviceName"));
        priceList.setClientID(new Client(null, null, rs.getInt("clientID")));
        return priceList;
    }

    @Override
    public boolean insertPriceList(PriceList priceList) {
        try {
            PreparedStatement ps = connection.prepareStatement("Insert into PriceList(ID,priceType,serviceName,clientID) values(default,?,?,?)");
            ps.setString(1, priceList.getType());
            ps.setString(2, priceList.getServiceName());
            ps.setInt(3, priceList.getClientID().getID());
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
    public boolean updatePriceList(PriceList priceList) {
        try {
            PreparedStatement ps=connection.prepareStatement("UPDATE PriceList Set priceType=?,serviceName=?,clientID=? where ID=?");
            ps.setString(1,priceList.getType());
            ps.setString(2,priceList.getServiceName());
            ps.setInt(3,priceList.getClientID().getID());
            ps.setInt(4,priceList.getID());
            int i = ps.executeUpdate();
            if(i==1){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    return false;
    }

    @Override
    public boolean removePriceList(Integer priceListID) {
        try {
            PreparedStatement ps=connection.prepareStatement("Delete from PriceList where ID=?");
            ps.setInt(1,priceListID);
            int i = ps.executeUpdate();
            if(i==1){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    return false;
    }
}
