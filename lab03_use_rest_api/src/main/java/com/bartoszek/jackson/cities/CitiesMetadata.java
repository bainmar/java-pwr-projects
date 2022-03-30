package com.bartoszek.jackson.cities;

public class CitiesMetadata {
	private int currentOffset;
	private int totalCount;

	public int getCurrentOffset() {
		return currentOffset;
	}
	public void setCurrentOffset(int currentOffset) {
		this.currentOffset = currentOffset;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}


}