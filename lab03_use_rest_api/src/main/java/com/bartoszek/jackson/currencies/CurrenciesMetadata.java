package com.bartoszek.jackson.currencies;

public class CurrenciesMetadata {
	private int currentOffset;
	private int totalCount;
	public int getCurrenOffSet() {
		return currentOffset;
	}
	public void setCurrentOffset(int currenOffSet) {
		this.currentOffset = currenOffSet;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}


}