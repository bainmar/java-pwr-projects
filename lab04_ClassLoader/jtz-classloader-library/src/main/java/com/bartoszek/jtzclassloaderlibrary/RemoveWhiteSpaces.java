package com.bartoszek.jtzclassloaderlibrary;

import processing.Processor;
import processing.Status;
import processing.StatusListener;

public class RemoveWhiteSpaces implements Processor {

	private String result;

	@Override
	public boolean submitTask(String task, StatusListener sl) {
		if(task==null){
			return false;
		}
		Status status = new Status(0, 0);
		sl.statusChanged(status);
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i <= 100; i += 20) {
			sl.statusChanged(status);
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		result = task.replaceAll("\\s+","");
		sl.statusChanged(status);
		sl.statusChanged(status);
		return true;
	}

	@Override
	public String getInfo() {
		return "Zadanie: usunięcie białych znaków";
	}

	@Override
	public String getResult() {
		return result;
	}

}