package com.bartoszek.serviceloader.impl01;



import java.util.Arrays;
import java.util.stream.Stream;

public class Record {
	String[] record;
	int clusterNumber;
	public Record(String[]record) {
		this.record=Arrays.copyOf(record, record.length);
	}
	public String getClusterNumber() {
		return String.valueOf(clusterNumber);
	}
	public int[] getRecordInt() {
		return Stream.of(record).mapToInt(Integer::parseInt).toArray();
	}
	public String[]getRecordString(){
		return record;
	}
	public void setClusterNumber(int clusterNumber) {
		this.clusterNumber=clusterNumber;
	}
	@Override
	public String toString() {
		return "Record [record=" + Arrays.toString(record) + ", clusterNumber=" + clusterNumber + "]";
	}
	
	
	
}
