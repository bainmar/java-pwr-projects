package com.bartoszek.dao;

import java.util.Set;

import com.bartoszek.dto.PriceList;

public interface PriceListDAO {
    PriceList getPriceList(Integer priceListID);
    Set<PriceList> getAllPriceLists();
    boolean insertPriceList(PriceList priceList);
    boolean updatePriceList(PriceList priceList);
    boolean removePriceList(Integer priceListID);
}
