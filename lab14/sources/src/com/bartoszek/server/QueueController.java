package com.bartoszek.server;

import javax.management.*;
import javax.swing.*;
import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.Random;
import java.util.SortedMap;

public class QueueController {
    private QueueModel queueModel;
    private QueueView queueView;
    private MBeanServer mbs;

    public QueueController(QueueModel queueModel, QueueView queueView) {
        this.queueModel=queueModel;
        this.queueView=queueView;
    }
    public void init() throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        queueView.getChangeDataJButton().addActionListener((e)->{
            changeMBeanData();
        });
        queueView.getGenJButton().addActionListener((e)->{
            generateNumber();
        });

        mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("com.bartoszek.server:type=Queue");
        String categories="";
        String priorities="";
        Queue mbean = new Queue(categories,priorities);
        mbean.addController(this);
        queueModel.setDataMBean(mbean);
        mbs.registerMBean(mbean, name);
        reloadCategories();
        new CaseThread(this).start();
    }

    public void reloadCategories(){
        String categories = queueModel.getDataMBean().getCategories();
        String[] splitArray = categories.split(",");
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.removeAllElements();
        for(String element:splitArray){
            model.addElement(element);
        }
        queueView.getCategoriesJComboBox().setModel(model);
    }

    public void reloadCategoriesInput(String input){
        queueView.getListOfCasesJTextField().setText(input);
    }
    public void reloadPrioritiesInput(String input){
        queueView.getListOfPrioritiesJTextField().setText(input);
    }




    private void generateNumber() {
        String selected = queueView.getCategoriesJComboBox().getSelectedItem().toString();
        if(selected!=null){
            queueModel.addCase(selected);
            String updatedNumber=queueModel.getCase(selected);
            queueModel.addReport(new Report(updatedNumber,Status.NEW));
            updateInformationTable();
            queueView.getGeneratedNumberJTextField().setText(updatedNumber);

        }else{
            JOptionPane.showMessageDialog(queueView.getMyFrame(),"Nie znaleziono kategorii.");
        }
    }

    public QueueModel getQueueModel() {
        return queueModel;
    }

    public QueueView getQueueView() {
        return queueView;
    }

    private void updateInformationTable() {
        List<Report> listOfReports = queueModel.getListOfReports();
        StringBuilder newReports=new StringBuilder();
        StringBuilder inProgressReports=new StringBuilder();
        StringBuilder executedReports=new StringBuilder();

        for(Report report:listOfReports){
            if(report.getStatus()==Status.NEW){
                newReports.append(report.toString()+ "\n");
            }else if(report.getStatus()==Status.IN_PROGRESS){
                inProgressReports.append(report.toString()+"\n");
            }else if(report.getStatus()==Status.EXECUTED){
                executedReports.append(report.toString()+"\n");
            }
        }
        StringBuilder allInformations=new StringBuilder();
        allInformations.append(newReports.toString());
        allInformations.append(inProgressReports.toString());
        allInformations.append(executedReports.toString());

        String allString = allInformations.toString();
        queueView.getCurrentCases().setText(allString);

    }

    private void changeMBeanData() {
        String casesString = queueView.getListOfCasesJTextField().getText();
        String[] casesArray = casesString.split(",");
        String prioritiesString=queueView.getListOfPrioritiesJTextField().getText();
        String[] prioritiesArray = prioritiesString.split(",");
        if(casesArray.length==prioritiesArray.length){
            queueView.getGenJButton().setEnabled(true);
            Queue dataMBean = queueModel.getDataMBean();
            dataMBean.setCategories(casesString);
            dataMBean.setPriorities(prioritiesString);
            SortedMap<String, String> caseWeights = queueModel.getCaseWeights();
            caseWeights.clear();
            for(int i=0;i<casesArray.length;i++){
                queueModel.addCase(casesArray[i]);
                caseWeights.put(prioritiesArray[i],casesArray[i]);
            }
            reloadCategories();
            JOptionPane.showMessageDialog(queueView.getMyFrame(),"Edytowano MBean.");
        }else{
            JOptionPane.showMessageDialog(queueView.getMyFrame(),"Nieprawidłowe dane wejściowe.");
        }
    }

    class CaseThread extends Thread{
        Random random=new Random();
        QueueController controller;
        CaseThread(QueueController controller){
            this.controller=controller;
        }

        @Override
        public void run() {
            List<Report> listOfReports = queueModel.getListOfReports();
            while(true){
                int i = random.nextInt(5);
                if(!listOfReports.isEmpty()) {

                    try {
                        Thread.sleep(i * 1000);
                        for(Report report:listOfReports){
                            if(report.getStatus()==Status.NEW){
                                report.setStatus(Status.EXECUTED);
                                controller.updateInformationTable();
                                break;
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    try {
                        Thread.sleep(i*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}
