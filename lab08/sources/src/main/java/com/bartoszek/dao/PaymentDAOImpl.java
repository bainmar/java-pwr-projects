package com.bartoszek.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.bartoszek.dto.Client;
import com.bartoszek.dto.Installation;
import com.bartoszek.dto.Payment;

public class PaymentDAOImpl implements PaymentDAO{
    private Connection connection;

    public PaymentDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Payment getPayment(Integer paymentID) {
        try{
            PreparedStatement ps = connection.prepareStatement("Select * from Payment where ID=?");
            ps.setInt(1,paymentID);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()) {
                return extractPaymentFromResultSet(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private Payment extractPaymentFromResultSet(ResultSet resultSet) throws SQLException {
        Payment payment=new Payment();
        payment.setID(resultSet.getInt("ID"));
        payment.setPaymentDate(resultSet.getDate("paymentDate"));
        payment.setDepositAmount(resultSet.getFloat("depositAmount"));
        payment.setInstallationID(new Installation(null,null,null,new Client(null,null,resultSet.getInt("installationID"))));
        return payment;
    }

    @Override
    public Set<Payment> getAllPayments() {
        try {
            PreparedStatement ps =connection.prepareStatement("Select * from Payment");
            ResultSet resultSet = ps.executeQuery();
            Set<Payment>payments=new HashSet<>();
            while(resultSet.next()){
                Payment payment=extractPaymentFromResultSet(resultSet);
                payments.add(payment);
            }
            return payments;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertPayment(Payment payment) {
        try {
            PreparedStatement ps=connection.prepareStatement("Insert into Payment(ID,paymentDate,depositAmount,installationID) values(default,?,?,?)");
            ps.setDate(1,payment.getPaymentDate());
            ps.setFloat(2,payment.getDepositAmount());
            ps.setInt(3,payment.getInstallationID().getID());
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
    public boolean updatePayment(Payment payment) {
        try {
            PreparedStatement ps = connection.prepareStatement("Update Payment set paymentDate=?,depositAmount=?,installationID=? where ID=?");
            ps.setDate(1,payment.getPaymentDate());
            ps.setFloat(2,payment.getDepositAmount());
            ps.setInt(3,payment.getInstallationID().getID());
            ps.setInt(4,payment.getID());
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
    public boolean removePayment(Integer paymentID) {
        try {
            PreparedStatement ps =connection.prepareStatement("Delete from Payment where ID=?");
            ps.setInt(1,paymentID);
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
