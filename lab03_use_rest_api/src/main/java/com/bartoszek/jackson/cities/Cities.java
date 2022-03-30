package com.bartoszek.jackson.cities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Cities {

	@JsonIgnore
	List<CitiesData> data;
	@JsonIgnore
	List<CitiesLinks>links;
	CitiesMetadata metadata;




	public void setData(List<CitiesData> data) {
		this.data = data;
	}
	public void setLinks(List<CitiesLinks> links) {
		this.links = links;
	}
	public CitiesMetadata getMetadata() {
		return metadata;
	}
	public void setMetadata(CitiesMetadata metadata) {
		this.metadata = metadata;
	}






}