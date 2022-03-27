package com.bartoszek.jackson.countries;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CountriesData {

    private String code;
    private String name;
    @JsonIgnore
    private String currencyCodes;
    private String wikiDataId;

    public String getCurrencyCodes() {
        return currencyCodes;
    }

    public void setCurrencyCodes(String currencyCodes) {
        this.currencyCodes = currencyCodes;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWikiDataId() {
        return wikiDataId;
    }

    public void setWikiDataId(String wikiDataId) {
        this.wikiDataId = wikiDataId;
    }

}