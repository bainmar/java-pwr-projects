package com.bartoszek.dao;

import java.util.Set;

import com.bartoszek.dto.Charge;
import com.bartoszek.dto.Client;

public interface ChargeDAO {
    Charge getCharge(Integer chargeID);
    Set<Charge>getAllCharges();
    boolean insertCharge(Charge charge);
    boolean updateCharge(Charge charge);
    boolean removeCharge(Integer chargeID);
    Float countChargesForClient(Client client);
}
