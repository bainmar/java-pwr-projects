package com.bartoszek.gui;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainPanel extends JPanel {

	public MainPanel() {
		String[] sekcje = {  "sciezka klas" };
		GridLayout gridLayout = new GridLayout(2, 2, 20, 10);
		setLayout(gridLayout);
		setBorder(BorderFactory.createRaisedBevelBorder());
		NWPanel nwPanel = new NWPanel();
		NEPanel nePanel = new NEPanel();
		SWPanel swPanel = new SWPanel();
		SEPanel sePanel = new SEPanel(nwPanel,nePanel,swPanel);
		add(nwPanel);
		add(nePanel);
		add(swPanel);
		add(sePanel);

	}

}
