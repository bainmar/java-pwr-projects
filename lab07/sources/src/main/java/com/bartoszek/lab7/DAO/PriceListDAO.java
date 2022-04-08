package com.bartoszek.lab7.DAO;

import com.bartoszek.lab7.DTO.PriceList;

import java.util.Set;

public interface PriceListDAO {
    PriceList getPriceList(Integer priceListID);
    Set<PriceList> getAllPriceLists();
    boolean insertPriceList(PriceList priceList);
    boolean updatePriceList(PriceList priceList);
    boolean removePriceList(Integer priceListID);
}
