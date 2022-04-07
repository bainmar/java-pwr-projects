package com.bartoszek.jtzclassloaderapp;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;

import gui.MyFrame;

public class SystemTest {

public static void main(String[] args) {

		EventQueue.invokeLater(()->{

			MyFrame myFrame;

			try {
				myFrame = new MyFrame();
				myFrame.setTitle("custom classloader");
				myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				myFrame.setVisible(true);
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}



		});

	}
}