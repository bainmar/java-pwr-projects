package com.bartoszek.dto;

import java.sql.Date;

public class Charge {
    private Integer ID;
    private Date paymentSchedule;
    private Float amountToPay;
    private Installation installationID;
    public Charge() {
    }

    public Charge(Date paymentSchedule, Float amountToPay, Installation installationID) {
        this.paymentSchedule = paymentSchedule;
        this.amountToPay = amountToPay;
        this.installationID = installationID;
    }

    public Charge(Integer ID, Date paymentSchedule, Float amountToPay, Installation installationID) {
        this.ID = ID;
        this.paymentSchedule = paymentSchedule;
        this.amountToPay = amountToPay;
        this.installationID = installationID;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Date getPaymentSchedule() {
        return paymentSchedule;
    }

    public void setPaymentSchedule(Date paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }

    public Float getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(Float amountToPay) {
        this.amountToPay = amountToPay;
    }

    public Installation getInstallationID() {
        return installationID;
    }

    public void setInstallationID(Installation installationID) {
        this.installationID = installationID;
    }

    @Override
    public String toString() {
        return "Charge{" +
                "ID=" + ID +
                ", paymentSchedule=" + paymentSchedule +
                ", amountToPay=" + amountToPay +
                ", installationID=" + installationID +
                '}';
    }
}
