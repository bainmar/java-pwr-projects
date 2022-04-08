package com.bartoszek.classes;

import processing.Processor;
import processing.Status;
import processing.StatusListener;

public class ToUpperCases implements Processor{

	private String result;

	@Override
	public boolean submitTask(String task, StatusListener sl) {
		if(task==null){
			return false;
		}
		Status status = new Status("Przygotowanie....tekst: " + task, 0);
		sl.statusChanged(status);
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		status.setTaskName("\nPrzetwarzanie..");
		for (int i = 0; i <= 100; i += 20) {
			status.setProgress(i);
			sl.statusChanged(status);
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		result = task.toUpperCase();
		status.setTaskName("\nZakończono!\n");
		sl.statusChanged(status);
		status.setTaskName(result);
		sl.statusChanged(status);
		return true;
	}

	@Override
	public String getInfo() {
		return "Zadanie: zamiana na duże litery";
	}

	@Override
	public String getResult() {
		return result;
	}

}
