package com.bartoszek.serviceloader.impl01;



import java.util.Arrays;
import java.util.stream.Stream;

public class Cluster {
	String[] cluster;
	int clusterNumber;
	public Cluster(int clusterNumber,String [] row) {
		this.clusterNumber=clusterNumber;
		this.cluster=Arrays.copyOf(row,row.length);		
	}
	public void setClusterNumber(int clusterNumber) {
		this.clusterNumber=clusterNumber;
	}
	public int getClusterNumber() {
		return clusterNumber;
	}
	public int[]getCluster(){
		return Stream.of(cluster).mapToInt(Integer::parseInt).toArray();
	}
	
	public double calculateDistance(Record record) {
		int[]centroids=getCluster();
		int[]records=record.getRecordInt();
		double result=0;
		for(int i=1;i<centroids.length;i++) {
			result+=Math.pow((double)(centroids[i]-records[i]), 2);
		}
		return Math.sqrt(result);
	}
	
	public void updateCentroid(Record record) {
		int[]centroids=getCluster();
		int[]records=record.getRecordInt();
		for(int i=1;i<cluster.length;i++) {
			cluster[i]=Integer.toString((centroids[i]+records[i])/2);
		}
	}
	@Override
	public String toString() {
		return "Cluster [cluster=" + Arrays.toString(cluster) + ", clusterNumber=" + clusterNumber + "]";
	}
}
