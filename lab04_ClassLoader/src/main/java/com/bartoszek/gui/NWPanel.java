package com.bartoszek.gui;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;

public class NWPanel extends JPanel {
	JLabel instructionNW;
	JTextArea textAreaNW;
	JScrollPane jScrollNW;

	public NWPanel() {
		SpringLayout layoutNW = new SpringLayout();
		setLayout(layoutNW);
		instructionNW = (JLabel) add(new JLabel("Wprowadz text:"));
		textAreaNW = new JTextArea();
		textAreaNW.setWrapStyleWord(true);
		textAreaNW.setLineWrap(true);
		textAreaNW.setFont(new Font("Times New Roman", Font.ITALIC + Font.BOLD, 16));
		jScrollNW = (JScrollPane) add(new JScrollPane(textAreaNW, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER));

		layoutNW.putConstraint(SpringLayout.NORTH, instructionNW, 5, SpringLayout.NORTH, this);
		layoutNW.putConstraint(SpringLayout.WEST, instructionNW, 5, SpringLayout.WEST, this);
		layoutNW.putConstraint(SpringLayout.EAST, instructionNW, 0, SpringLayout.EAST, this);
		layoutNW.putConstraint(SpringLayout.NORTH, jScrollNW, 5, SpringLayout.SOUTH, instructionNW);
		layoutNW.putConstraint(SpringLayout.SOUTH, jScrollNW, 0, SpringLayout.SOUTH, this);
		layoutNW.putConstraint(SpringLayout.WEST, jScrollNW, 0, SpringLayout.WEST, instructionNW);
		layoutNW.putConstraint(SpringLayout.EAST, jScrollNW, 0, SpringLayout.EAST, instructionNW);

	}

}
