package com.bartoszek.jackson.countries;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Countries {

    private List<CountriesData> data;
    @JsonIgnore
    private List<CountriesLinks> links;
    private CountriesMetadata metadata;

    public List<CountriesData> getData() {
        return data;
    }

    public void setData(List<CountriesData> data) {
        this.data = data;
    }

    public List<CountriesLinks> getLinks() {
        return links;
    }

    public void setLinks(List<CountriesLinks> links) {
        this.links = links;
    }

    public CountriesMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(CountriesMetadata metadata) {
        this.metadata = metadata;
    }

}