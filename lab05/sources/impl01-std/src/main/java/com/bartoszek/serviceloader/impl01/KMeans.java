package com.bartoszek.serviceloader.impl01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import com.google.auto.service.AutoService;
import com.sun.jdi.Value;

import ex.api.ClusterAnalysisService;
import ex.api.ClusteringException;
import ex.api.DataSet;

@AutoService(ClusterAnalysisService.class)
public class KMeans implements ClusterAnalysisService {
	
	private String name;
	private List<Record> data;
	private List<Cluster> clusters;
	private Map<Cluster, List<Record>> clusterRecords;
	private int numberOfClusters=2;
		
	public KMeans() {
		this.name="Algorytm centroidów";
		data=new ArrayList<>();
		clusters=new ArrayList<>();
		clusterRecords=new HashMap<>();
	}
	@Override
	public void setOptions(String[] options) throws ClusteringException {
		this.numberOfClusters=Integer.parseInt(options[0]);	
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void submit(DataSet ds) throws ClusteringException {
		data.clear();
		clusters.clear();
		clusterRecords.clear();
		int clusterNumber=numberOfClusters;	
		String[][] records = ds.getData();
		Record record=null;
		for(int i =0;i<records.length;i++) {
			record=new Record(records[i]);
			data.add(record);
		}
		initializeClusterAndCentroid(clusterNumber);
//		printRecordInformation();
//		printClusterInformation();
				
	}

	@Override
	public DataSet retrieve(boolean clear) throws ClusteringException {
		DataSet dataSet=new DataSet();
		List<List<String>> allRows=new ArrayList<>();
		List<String>row;
		for(Record record:data) {
			row=new ArrayList<>();
			String[] recordString = record.getRecordString();
			for(String element:recordString) {
				row.add(element);
			}
			row.add(record.getClusterNumber());
			allRows.add(row);
		}
		String[][] array = new String[allRows.size()][];
		String[] blankArray = new String[0];
		for(int i=0; i < allRows.size(); i++) {
		    array[i] = allRows.get(i).toArray(blankArray);
		}
		dataSet.setData(array);
		return dataSet;
		
	}

	private void initializeClusterAndCentroid(int clusterNumber) {
		int counter=1;
		Iterator<Record> iterator = data.iterator();
		Record record=null;
		while(iterator.hasNext()) {
			record=iterator.next();
			if(counter<=clusterNumber) {
				record.setClusterNumber(counter);
				initializeCluster(counter,record);
				counter++;
			}else {
//				System.out.println(record.toString());
//				System.out.println("**Cluster Information**");
//				for(Cluster cluster:clusters) {
//					System.out.println(cluster);
//				}
//				System.out.println("**************");
				double minDistance=Integer.MAX_VALUE;
				Cluster whichCluster=null;
				for(Cluster cluster:clusters) {
					double distance=cluster.calculateDistance(record);
//					System.out.println(distance);
					if(minDistance>distance) {
						minDistance=distance;
						whichCluster=cluster;
					}
				}
				record.setClusterNumber(whichCluster.getClusterNumber());
				whichCluster.updateCentroid(record);
				clusterRecords.get(whichCluster).add(record);
			}
//			System.out.println("**Cluster information**");
//			for(Cluster cluster:clusters) {
//				System.out.println(cluster);
//			}
//			System.out.println("***************");
			
		}
		
	}

	private void initializeCluster(int clusterNumber, Record record) {
		Cluster cluster=new Cluster(clusterNumber,record.getRecordString());
		clusters.add(cluster);
		List<Record> clusterRecord=new ArrayList<>();
		clusterRecord.add(record);
		clusterRecords.put(cluster, clusterRecord);
	}
//	
//	private void printRecordInformation() {
//		System.out.println("***** Each Record Information*****");
//		for(Record record: data) {
//			System.out.println(record);
//		}
//	}
//	
//	
//	
//	private void printClusterInformation() {
//		System.out.println("*****Final Cluster Information***");
//		for(Map.Entry<Cluster, List<Record>> entry:clusterRecords.entrySet()) {
//			System.out.println("Key= "+entry.getKey()+
//					", Value= "+entry.getValue());
//		}
//	}
//	

}
