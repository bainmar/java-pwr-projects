package com.bartoszek.serviceloader.client;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.Iterator;
import java.util.ServiceLoader;

import javax.swing.JFrame;

import com.bartoszek.gui.MyFrame;
import com.opencsv.exceptions.CsvException;

import ex.api.ClusterAnalysisService;
import ex.api.ClusteringException;
import ex.api.DataSet;

public class Client {
	public static void main(String[] args) {

		EventQueue.invokeLater(()->{
			MyFrame myFrame = new MyFrame();
			myFrame.setTitle("Lab05-Marcin Bartoszek");
			myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			myFrame.setVisible(true);
		});
	}
		
	
		
		
    
}
