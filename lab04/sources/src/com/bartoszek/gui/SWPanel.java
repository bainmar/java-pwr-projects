package com.bartoszek.gui;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;

import processing.Status;
import processing.StatusListener;

public class SWPanel extends JPanel {
	JLabel instructionSW;
	JTextArea textAreaSW;
	JScrollPane jScrollSW;

	public SWPanel() {
		SpringLayout layoutSW = new SpringLayout();
		setLayout(layoutSW);
		instructionSW = (JLabel) add(new JLabel("Wyniki przetwarzania:"));
		textAreaSW = new JTextArea();
		textAreaSW.setWrapStyleWord(true);
		textAreaSW.setLineWrap(true);
		textAreaSW.setFont(new Font("Times New Roman", Font.ITALIC + Font.BOLD, 16));
		jScrollSW = (JScrollPane) add(new JScrollPane(textAreaSW, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER));
		

		layoutSW.putConstraint(SpringLayout.NORTH, instructionSW, 5, SpringLayout.NORTH, this);
		layoutSW.putConstraint(SpringLayout.WEST, instructionSW, 5, SpringLayout.WEST, this);
		layoutSW.putConstraint(SpringLayout.EAST, instructionSW, 0, SpringLayout.EAST, this);
		layoutSW.putConstraint(SpringLayout.NORTH, jScrollSW, 5, SpringLayout.SOUTH, instructionSW);
		layoutSW.putConstraint(SpringLayout.SOUTH, jScrollSW, -5, SpringLayout.SOUTH, this);
		layoutSW.putConstraint(SpringLayout.WEST, jScrollSW, 0, SpringLayout.WEST, instructionSW);
		layoutSW.putConstraint(SpringLayout.EAST, jScrollSW, 0, SpringLayout.EAST, instructionSW);

	}
	
	public class SWPanelListener implements StatusListener{

		@Override
		public void statusChanged(Status s) {
			textAreaSW.append(s.getTaskName());			
		}
		
	}
	
}
