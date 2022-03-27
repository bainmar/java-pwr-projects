package com.bartoszek.jtzrestapi;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.Locale;

import javax.swing.JFrame;

import GUI.MyFrame;

public class SystemTest {

	public static void main(String[] args) {

		EventQueue.invokeLater(()->{

			MyFrame myFrame;

			try {
				myFrame = new MyFrame();
				myFrame.setTitle("Lab03 - Marcin Bartoszek");
				myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				myFrame.setVisible(true);
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



		});

	}

}