package com.bartoszek.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.bartoszek.ClassHandler;

import processing.Status;
import processing.StatusListener;

public class SEPanel extends JPanel {
	JButton buttonSE;
	JProgressBar progressBarSE;
	NWPanel nwPanel;
	NEPanel nePanel;
	SWPanel swPanel;

	public SEPanel(NWPanel nwPanel, NEPanel nePanel, SWPanel swPanel) {
		this.nwPanel = nwPanel;
		this.nePanel = nePanel;
		this.swPanel = swPanel;
		SpringLayout layoutSE = new SpringLayout();
		setLayout(layoutSE);
		buttonSE = (JButton) add(new JButton("Przetwarzaj"));
		buttonSE.addActionListener(new ButtonSEListener());
		progressBarSE = (JProgressBar) add(new JProgressBar(JProgressBar.HORIZONTAL));
		progressBarSE.setStringPainted(true);
		

		layoutSE.putConstraint(SpringLayout.NORTH, buttonSE, 50, SpringLayout.NORTH, this);
		layoutSE.putConstraint(SpringLayout.WEST, buttonSE, 50, SpringLayout.WEST, this);
		layoutSE.putConstraint(SpringLayout.NORTH, progressBarSE, 10, SpringLayout.SOUTH, buttonSE);
		layoutSE.putConstraint(SpringLayout.WEST, progressBarSE, 5, SpringLayout.WEST, this);
		layoutSE.putConstraint(SpringLayout.EAST, progressBarSE, -5, SpringLayout.EAST, this);

	}

	public class ButtonSEListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String text = nwPanel.textAreaNW.getText();
			int selectedItem = nePanel.comboBoxNE.getSelectedIndex();	

			try {
				ClassHandler.runTask(selectedItem, text, new ProgressBarListener());
				//Object result = ClassHandler.getResult(selectedItem);
			
				

			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| InstantiationException | NoSuchMethodException | SecurityException e1) {
			
				e1.printStackTrace();
			}

		}

	}

	public class ProgressBarListener implements StatusListener {
		int value;

		@Override
		public void statusChanged(Status s) {
			System.out.println(value = s.getProgress());
			progressBarSE.setValue(value);			
			swPanel.textAreaSW.append(s.getTaskName());

		}

	}
	
	

}
