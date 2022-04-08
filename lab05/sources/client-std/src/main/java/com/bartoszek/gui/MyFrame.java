package com.bartoszek.gui;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ServiceLoader;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.bartoszek.serviceloader.client.CsvToDataSetConverter;
import com.opencsv.exceptions.CsvException;

import ex.api.ClusterAnalysisService;
import ex.api.ClusteringException;
import ex.api.DataSet;

public class MyFrame extends JFrame {
	public MyFrame() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		setBounds(screenWidth / 4, screenHeight / 4, screenWidth / 2, screenHeight);
		setContentPane(new MainPanel());
	}
}

class MainPanel extends JPanel {
	JButton readData;
	JButton editData;
	JButton saveData;

	JComboBox service;
	JComboBox clusters;

	JButton execute;
	JPanel dataPanel;
	CsvToDataSetConverter converter;
	ServiceLoader<ClusterAnalysisService> loader;
	List<ClusterAnalysisService> services;

	public MainPanel() {

		loader = ServiceLoader.load(ClusterAnalysisService.class);
		services = new ArrayList<ClusterAnalysisService>();
		List<String> servicesNames = new ArrayList<>();
		for (ClusterAnalysisService service : loader) {
			services.add(service);
			servicesNames.add(service.getName());
		}

		String[] servicesNamesForComboBox = servicesNames.toArray(new String[0]);
		converter = new CsvToDataSetConverter();
		String[] menu = { "wczytaj", "edytuj", "zapisz", "usługa", "wykonaj" };
		String[] numberOfClusters = { "1", "2", "3", "4", "5" };
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		readData = (JButton) add(new JButton(menu[0]));
		readData.addActionListener(new ReadDataListener());

		editData = (JButton) add(new JButton(menu[1]));
		editData.addActionListener(new EditDataListener());
		saveData = (JButton) add(new JButton(menu[2]));
		saveData.addActionListener(new SaveResultsListener());
		service = (JComboBox<String>) add(new JComboBox<String>(servicesNamesForComboBox));
		clusters = (JComboBox<String>) add(new JComboBox<String>(numberOfClusters));
		execute = (JButton) add(new JButton(menu[4]));
		execute.addActionListener(new ExecuteListener());
		dataPanel = (DataPanel) add(new DataPanel());

		layout.putConstraint(SpringLayout.NORTH, readData, 5, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, readData, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, editData, 0, SpringLayout.NORTH, readData);
		layout.putConstraint(SpringLayout.WEST, editData, 0, SpringLayout.EAST, readData);
		layout.putConstraint(SpringLayout.NORTH, saveData, 0, SpringLayout.NORTH, readData);
		layout.putConstraint(SpringLayout.WEST, saveData, -5, SpringLayout.EAST, editData);
		layout.putConstraint(SpringLayout.NORTH, execute, 0, SpringLayout.NORTH, readData);
		layout.putConstraint(SpringLayout.WEST, execute, -5, SpringLayout.EAST, saveData);
		layout.putConstraint(SpringLayout.WEST, service, 5, SpringLayout.EAST, execute);
		layout.putConstraint(SpringLayout.NORTH, service, 0, SpringLayout.NORTH, readData);
		layout.putConstraint(SpringLayout.NORTH, clusters, 0, SpringLayout.NORTH, readData);
		layout.putConstraint(SpringLayout.WEST, clusters, 5, SpringLayout.EAST, service);
		layout.putConstraint(SpringLayout.WEST, dataPanel, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, dataPanel, 5, SpringLayout.SOUTH, execute);
		layout.putConstraint(SpringLayout.EAST, dataPanel, -5, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, dataPanel, -5, SpringLayout.SOUTH, this);

	}

	class ReadDataListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("."));
			chooser.setDialogTitle("lokalizacja");
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			if (chooser.showOpenDialog(MainPanel.this) == JFileChooser.APPROVE_OPTION) {
				((DataPanel) dataPanel).setInputDataTable(chooser.getSelectedFile().toString());
			}
		}

	}

	class EditDataListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("."));
			chooser.setDialogTitle("lokalizacja zapisu");
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			if (chooser.showOpenDialog(MainPanel.this) == JFileChooser.APPROVE_OPTION) {
				((DataPanel) dataPanel).saveDataToCsvFile(chooser.getSelectedFile().toString(),
						((DataPanel) dataPanel).inputData);
			}

		}

	}

	class ExecuteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				int selectedIndex = service.getSelectedIndex();
				int selectedCluster = clusters.getSelectedIndex();
				String[] numberOfClusters = { (String) clusters.getSelectedItem() };
				services.get(selectedIndex).setOptions(numberOfClusters);
				services.get(selectedIndex).submit(((DataPanel) dataPanel).dataSet);
				DataSet retrieveData = services.get(selectedIndex).retrieve(true);
				String[] headerToSave = ((DataPanel) dataPanel).dataSet.getHeader();
				List<String> headers = new ArrayList<>();
				for (String element : headerToSave) {
					headers.add(element);
				}
				String category = "CategoryID";
				headers.add(category);
				String[] header = headers.toArray(new String[0]);
				retrieveData.setHeader(header);

				((DataPanel) dataPanel).setOutputDataTable(retrieveData);

				String[][] data = retrieveData.getData();
//			

			} catch (ClusteringException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	class SaveResultsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("."));
			chooser.setDialogTitle("lokalizacja wyników");
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			if (chooser.showOpenDialog(MainPanel.this) == JFileChooser.APPROVE_OPTION) {
				((DataPanel) dataPanel).saveDataToCsvFile(chooser.getSelectedFile().toString(),
						((DataPanel) dataPanel).outputData);
			}

		}
	}

	class DataPanel extends JPanel {
		JTable inputData;
		JTable outputData;
		DefaultTableModel modelInput;
		DefaultTableModel modelOutput;
		CsvToDataSetConverter converter;
		DataSet dataSet;

		public DataPanel() {
			converter = new CsvToDataSetConverter();
			modelInput = new DefaultTableModel();
			modelOutput = new DefaultTableModel();
			inputData = new JTable(modelInput);
			outputData = new JTable(modelOutput);

			GridLayout gridLayout = new GridLayout(2, 1, 20, 10);
			setLayout(gridLayout);

			JScrollPane inputDataScrollPane = new JScrollPane(inputData);
			inputDataScrollPane.setBorder(BorderFactory.createTitledBorder("Dane Wejściowe"));
			JScrollPane outputDataScrollPane = new JScrollPane(outputData);
			outputDataScrollPane.setBorder(BorderFactory.createTitledBorder("Dane Wyjściowe"));
			add(inputDataScrollPane);
			add(outputDataScrollPane);
		}

		public void setInputDataTable(String fileName) {

			modelInput.setRowCount(0);
			modelInput.setColumnCount(0);
			try {
				dataSet = converter.readDataFromTheFile(fileName);
				String[] header = dataSet.getHeader();

				for (String element : header) {
					modelInput.addColumn(element);
				}
				String[][] data = dataSet.getData();
				for (String[] element : data) {
					modelInput.insertRow(modelInput.getRowCount(), element);
				}

			} catch (IOException | CsvException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void setOutputDataTable(DataSet dataSet) {
			modelOutput.setRowCount(0);
			modelOutput.setColumnCount(0);
			String[] header = dataSet.getHeader();
			for (String element : header) {
				modelOutput.addColumn(element);
			}
			String[][] data = dataSet.getData();
			for (String[] element : data) {
				modelOutput.insertRow(modelOutput.getRowCount(), element);
			}

		}

		public void saveDataToCsvFile(String fileName, JTable inputData) {
			List<List<String>> numdata = new ArrayList<>();
			List<String> columns = new ArrayList<>();
			for (int i = 0; i < inputData.getColumnCount(); i++) {
				columns.add(inputData.getColumnName(i));
			}

			for (int count = 0; count < inputData.getRowCount(); count++) {
				List<String> row = new ArrayList<>();
				for (int j = 0; j < inputData.getColumnCount(); j++) {
					row.add((String) inputData.getValueAt(count, j));
				}
				numdata.add(row);
			}

			DataSet dataSet = new DataSet();
			String[] header = columns.toArray(new String[0]);
			dataSet.setHeader(header);
			String[][] array = new String[numdata.size()][];
			String[] blankArray = new String[0];
			for (int i = 0; i < numdata.size(); i++) {
				array[i] = numdata.get(i).toArray(blankArray);
			}
			dataSet.setData(array);
			try {
				converter.saveDataToTheFile(dataSet, fileName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
