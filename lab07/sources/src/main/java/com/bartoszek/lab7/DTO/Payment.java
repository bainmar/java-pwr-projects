package com.bartoszek.lab7.DTO;

import java.sql.Date;

public class Payment {
    private Integer ID;
    private Date paymentDate;
    private Float depositAmount;
    private Installation installationID;

    public Payment() {
    }

    public Payment(Date paymentDate, Float depositAmount, Installation installationID) {
        this.paymentDate = paymentDate;
        this.depositAmount = depositAmount;
        this.installationID = installationID;
    }

    public Payment(Integer ID, Date paymentDate, Float depositAmount, Installation installationID) {
        this.ID = ID;
        this.paymentDate = paymentDate;
        this.depositAmount = depositAmount;
        this.installationID = installationID;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Float getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(Float depositAmount) {
        this.depositAmount = depositAmount;
    }

    public Installation getInstallationID() {
        return installationID;
    }

    public void setInstallationID(Installation installationID) {
        this.installationID = installationID;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "ID=" + ID +
                ", paymentDate=" + paymentDate +
                ", depositAmount=" + depositAmount +
                ", installationID=" + installationID +
                '}';
    }
}
