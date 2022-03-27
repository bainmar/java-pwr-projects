package com.bartoszek.jackson.currencies;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CurrenciesModel {
	List<CurrenciesData> data;
	@JsonIgnore
	String links;
	CurrenciesMetadata metadata;

	public List<CurrenciesData> getData() {
		return data;
	}

	public void setData(List<CurrenciesData> data) {
		this.data = data;
	}

	public void setLinks(String links) {
		this.links = links;
	}

	public CurrenciesMetadata getMetadata() {
		return metadata;
	}

	public void setMetadata(CurrenciesMetadata metadata) {
		this.metadata = metadata;
	}


}