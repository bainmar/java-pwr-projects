package com.bartoszek.lab7.DAO;

import com.bartoszek.lab7.DTO.Charge;
import com.bartoszek.lab7.DTO.Client;
import com.bartoszek.lab7.DTO.Installation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ChargeDAOImpl implements ChargeDAO{
    private Connection connection;

    public ChargeDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Charge getCharge(Integer chargeID) {
        try {
            PreparedStatement ps = connection.prepareStatement("Select * from Charge where ID=?");
            ps.setInt(1,chargeID);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                return extractChargeFromResultSet(resultSet);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private Charge extractChargeFromResultSet(ResultSet rs) throws SQLException {
        Charge charge=new Charge();
        charge.setID(rs.getInt("ID"));
        charge.setPaymentSchedule(rs.getDate("paymentSchedule"));
        charge.setAmountToPay(rs.getFloat("amountToPay"));
        charge.setInstallationID(new Installation(rs.getInt("installationID"),null,null,null,new Client(null,null,null)));
        return charge;
    }

    @Override
    public Set<Charge> getAllCharges() {
        try {
            PreparedStatement ps = connection.prepareStatement("Select * from Charge");
            ResultSet resultSet = ps.executeQuery();
            Set<Charge> charges=new HashSet<>();
            while(resultSet.next()){
                Charge charge = extractChargeFromResultSet(resultSet);
                charges.add(charge);
            }
            return charges;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertCharge(Charge charge) {
        try {
            PreparedStatement ps=connection.prepareStatement("Insert into Charge(ID,paymentSchedule,amountToPay,installationID) values(default,?,?,?)");
            ps.setDate(1,charge.getPaymentSchedule());
            ps.setFloat(2,charge.getAmountToPay());
            ps.setInt(3,charge.getInstallationID().getID());
            int i = ps.executeUpdate();
            if(i==1)return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateCharge(Charge charge) {
        try {
            PreparedStatement ps=connection.prepareStatement("Update Charge set paymentSchedule=?,amountToPay=?,installationId=? where ID=?");
            ps.setDate(1,charge.getPaymentSchedule());
            ps.setFloat(2,charge.getAmountToPay());
            ps.setInt(3,charge.getInstallationID().getID());
            ps.setInt(4,charge.getID());
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
    public boolean removeCharge(Integer chargeID) {
        try {
            PreparedStatement ps=connection.prepareStatement("Delete from Charge where ID=?");
            ps.setInt(1,chargeID);
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
    public Float countChargesForClient(Client client) {
        try{
            PreparedStatement ps= connection.prepareStatement("select amountToPay " +
                    "from Client,Installation,Charge " +
                    "where Client.ID=Installation.clientID " +
                    "and " +
                    "Installation.ID=Charge.installationID " +
                    "and Client.ID=?;");
            ps.setInt(1,client.getID());
            ResultSet resultSet = ps.executeQuery();
            float suma=0;
            if(resultSet.next()){
                suma=suma+resultSet.getFloat("amountToPay");
            }
            return suma;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0f;
    }
}
