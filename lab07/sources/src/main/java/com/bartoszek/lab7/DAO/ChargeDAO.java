package com.bartoszek.lab7.DAO;

import com.bartoszek.lab7.DTO.Charge;
import com.bartoszek.lab7.DTO.Client;

import java.util.Set;

public interface ChargeDAO {
    Charge getCharge(Integer chargeID);
    Set<Charge>getAllCharges();
    boolean insertCharge(Charge charge);
    boolean updateCharge(Charge charge);
    boolean removeCharge(Integer chargeID);
    Float countChargesForClient(Client client);
}
