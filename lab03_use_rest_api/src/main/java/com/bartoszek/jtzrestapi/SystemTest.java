package com.bartoszek.jtzrestapi;

import java.awt.EventQueue;
public class SystemTest {

	public static void main(String[] args) {

		EventQueue.invokeLater(()->{
			AppView appView = new AppView("use-client-api");
			AppController appController = new AppController(appView);
			appController.initController();
			appView.pack();
			appView.setVisible(true);
		});

	}

}