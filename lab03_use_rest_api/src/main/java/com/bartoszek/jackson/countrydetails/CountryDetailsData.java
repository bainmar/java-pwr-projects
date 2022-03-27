package com.bartoszek.jackson.countrydetails;

import java.net.URI;
import java.util.List;

public class CountryDetailsData {

		private String capital;
		private String code;
		private List<String>currencyCodes;
		private URI flagImageUri;
		private String name;
		private int numRegions;
		private String wikiDataId;


		public String getCapital() {
			return capital;
		}
		public List<String> getCurrencyCodes() {
			return currencyCodes;
		}
		public void setCurrencyCodes(List<String> currencyCodes) {
			this.currencyCodes = currencyCodes;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public void setFlagImageUri(URI flagImageUri) {
			this.flagImageUri = flagImageUri;
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setNumRegions(int numRegions) {
			this.numRegions = numRegions;
		}
		public void setWikiDataId(String wikiDataId) {
			this.wikiDataId = wikiDataId;
		}
		public void setCapital(String capital) {
			this.capital = capital;
		}




}