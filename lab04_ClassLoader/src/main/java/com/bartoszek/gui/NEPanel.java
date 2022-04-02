package com.bartoszek.gui;

import java.awt.Checkbox;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;

import com.bartoszek.ClassHandler;

public class NEPanel extends JPanel {
	JLabel pathLabelNE;
	JTextField pathNE;
	JButton buttonNE;
	JButton refreshNE;
	JLabel areaLabelNE;
	JComboBox<String> comboBoxNE;
	String currentPath;

	public NEPanel() {
		SpringLayout layoutNE = new SpringLayout();
		setLayout(layoutNE);
		currentPath = System.getProperty("user.dir");
		System.out.println(currentPath);
		pathLabelNE = (JLabel) add(new JLabel("Lokalizacja klas:"));
		pathNE = (JTextField) add(new JTextField(currentPath));
		pathNE.setEditable(false);
		buttonNE = (JButton) add(new JButton("zmień"));
		buttonNE.addActionListener(new ButtonNEListener());
		refreshNE = (JButton) add(new JButton("odśwież"));
		refreshNE.addActionListener(new RefreshNEListener());
		areaLabelNE = (JLabel) add(new JLabel("Wybór klasy:"));
		comboBoxNE = (JComboBox<String>) add(new JComboBox<>());
		comboBoxNE.setEditable(false);

		layoutNE.putConstraint(SpringLayout.NORTH, pathLabelNE, 5, SpringLayout.NORTH, this);
		layoutNE.putConstraint(SpringLayout.WEST, pathLabelNE, 5, SpringLayout.WEST, this);
		layoutNE.putConstraint(SpringLayout.EAST, pathLabelNE, -5, SpringLayout.EAST, this);
		layoutNE.putConstraint(SpringLayout.WEST, pathNE, 5, SpringLayout.WEST, this);
		layoutNE.putConstraint(SpringLayout.EAST, pathNE, -5, SpringLayout.EAST, this);
		layoutNE.putConstraint(SpringLayout.NORTH, pathNE, 0, SpringLayout.SOUTH, pathLabelNE);
		layoutNE.putConstraint(SpringLayout.WEST, buttonNE, 5, SpringLayout.WEST, this);
		layoutNE.putConstraint(SpringLayout.NORTH, buttonNE, 5, SpringLayout.SOUTH, pathNE);
		layoutNE.putConstraint(SpringLayout.WEST, refreshNE, 5, SpringLayout.EAST, buttonNE);
		layoutNE.putConstraint(SpringLayout.NORTH, refreshNE, 5, SpringLayout.SOUTH, pathNE);
		layoutNE.putConstraint(SpringLayout.WEST, areaLabelNE, 0, SpringLayout.WEST, buttonNE);
		layoutNE.putConstraint(SpringLayout.EAST, areaLabelNE, -5, SpringLayout.EAST, this);
		layoutNE.putConstraint(SpringLayout.NORTH, areaLabelNE, 5, SpringLayout.SOUTH, buttonNE);
		layoutNE.putConstraint(SpringLayout.WEST, comboBoxNE, 0, SpringLayout.WEST, areaLabelNE);
		layoutNE.putConstraint(SpringLayout.EAST, comboBoxNE, 0, SpringLayout.EAST, areaLabelNE);
		layoutNE.putConstraint(SpringLayout.NORTH, comboBoxNE, 5, SpringLayout.SOUTH, areaLabelNE);
		layoutNE.putConstraint(SpringLayout.SOUTH, comboBoxNE, -50, SpringLayout.SOUTH, this);
	}

	class ButtonNEListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser jFileChooser = new JFileChooser();
			jFileChooser.setCurrentDirectory(new java.io.File("."));
			jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			if (jFileChooser.showOpenDialog(NEPanel.this) == JFileChooser.APPROVE_OPTION) {
				String currentPath = jFileChooser.getSelectedFile().getPath();
				pathNE.setText(currentPath);
			}
			try {
				comboBoxNE.removeAllItems();
				System.out.println(currentPath);
				ClassHandler.ClassHandlerInitialzie(currentPath);
				try {
					List<String> processorInfo = ClassHandler.getProcessorInfo();
					for (String element : processorInfo) {
						comboBoxNE.addItem(element);
					}
				} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

	class RefreshNEListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				comboBoxNE.removeAllItems();
				System.out.println(currentPath);
				ClassHandler.ClassHandlerInitialzie(currentPath);
				try {
					List<String> processorInfo = ClassHandler.getProcessorInfo();
					for (String element : processorInfo) {
						comboBoxNE.addItem(element);
					}
				} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

}
