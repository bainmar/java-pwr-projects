package com.bartoszek.jackson.currencies;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CurrenciesData {
	String code;
	@JsonIgnore
	String countryCodes;
	@JsonIgnore
	String symbol;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setCountryCodes(String countryCodes) {
		this.countryCodes = countryCodes;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}



}