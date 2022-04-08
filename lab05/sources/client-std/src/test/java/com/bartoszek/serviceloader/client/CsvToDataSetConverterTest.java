package com.bartoszek.serviceloader.client;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.opencsv.exceptions.CsvException;

import ex.api.DataSet;

class CsvToDataSetConverterTest {
	CsvToDataSetConverter converter;

@BeforeEach
public void initialize() {
	converter=new CsvToDataSetConverter();
}
	@Test
	void initializeDataSet() {
		System.out.println(System.getProperty("user.dir"));
		try {
			DataSet dataSet = converter.readDataFromTheFile("test.csv");
			String[] header = dataSet.getHeader();
			String[][] data = dataSet.getData();
			System.out.println(Arrays.toString(header));
			for(int i=0;i<data.length;i++) {
				System.out.println(Arrays.toString(data[i]));
			}
			converter.saveDataToTheFile(dataSet, "tuzapisze.csv");
			
		} catch (IOException | CsvException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
