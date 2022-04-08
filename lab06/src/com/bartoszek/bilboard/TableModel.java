package com.bartoszek.bilboard;

import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TableModel implements Serializable {
    private List<Advertisement> listOfAdvertisements;
    private int billboardID;
    private int totalCapacity;
    private int numberOfVacancies;
    private Duration timeToDisplay=Duration.ofMillis(3000);

    public List<Advertisement> getListOfAdvertisements() {
        return listOfAdvertisements;
    }

    public int getBillboardID() {
        return billboardID;
    }

    public void setBillboardID(int billboardID) {
        this.billboardID = billboardID;
    }

    public Duration getTimeToDisplay() {
        return timeToDisplay;
    }

    public void setTimeToDisplay(Duration timeToDisplay) {
        this.timeToDisplay = timeToDisplay;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int i) {
        int occupiedSpace = totalCapacity - numberOfVacancies;
        if (i > occupiedSpace) {
            totalCapacity = i;
            numberOfVacancies = totalCapacity - occupiedSpace;
        }
    }

    public int getNumberOfVacancies() {
        return numberOfVacancies;
    }

    public void setNumberOfVacancies(int numberOfVacancies) {
        this.numberOfVacancies = numberOfVacancies;
    }

    public TableModel() {
        this.listOfAdvertisements = new ArrayList<>();
    }

    public boolean addAdvertisement(String advertText, Duration displayPeriod, int orderId) {
        if (numberOfVacancies > 0) {
            numberOfVacancies--;
            return listOfAdvertisements.add(new Advertisement(advertText, displayPeriod, orderId));
        }
        return false;
    }
    public boolean removeAdvertisement(int orderId) {
        for (Advertisement add : listOfAdvertisements) {
            if (add.getId() == orderId) {
                numberOfVacancies++;
                return listOfAdvertisements.remove(add);
            }
        }
        return false;
    }

}
