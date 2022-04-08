package com.bartoszek.server;

import java.util.*;

public class QueueModel {
    private Map<String,Integer> caseNumbers;
    private Queue dataMBean;
    private SortedMap<String,String> caseWeights;
    private List<Report> listOfReports;


    public QueueModel(){
        listOfReports = new ArrayList<>();
        caseNumbers = new HashMap<>();
        caseWeights = new TreeMap<>();


    }

    public SortedMap<String, String> getCaseWeights() {
        return caseWeights;
    }

    public Queue getDataMBean() {
        return dataMBean;
    }

    public void setDataMBean(Queue dataMBean) {
        this.dataMBean = dataMBean;
    }

    public void addCase(String key){
        if(caseNumbers.containsKey(key)){
            caseNumbers.put(key,caseNumbers.get(key)+1);

        }else{
            caseNumbers.put(key,0);

        }
    }
    public String getCase(String key){
       return (key+(caseNumbers.get(key).toString()));
    }

    public boolean addReport(Report report){
        return listOfReports.add(report);
    }

    public List<Report> getListOfReports() {
        return listOfReports;
    }

}
