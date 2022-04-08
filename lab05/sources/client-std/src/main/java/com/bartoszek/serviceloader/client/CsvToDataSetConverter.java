package com.bartoszek.serviceloader.client;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import ex.api.DataSet;

public class CsvToDataSetConverter {
	
	public DataSet readDataFromTheFile(String path) throws IOException, CsvException {
		try(Reader reader=Files.newBufferedReader(Paths.get(path));
				CSVReader csvReader=new CSVReader(reader)){
			List<String[]> allData = csvReader.readAll();
			String[] header = allData.remove(0);
			String[][] data= allData.toArray(new String[0][]);
			DataSet dataSet=new DataSet();
			dataSet.setHeader(header);
			dataSet.setData(data);
			return dataSet;
		}
	}
	public void saveDataToTheFile(DataSet data,String path) throws FileNotFoundException, IOException {
		try(Writer writer=Files.newBufferedWriter(Paths.get(path))){
				CSVWriter csvWriter=new CSVWriter(writer,
						CSVWriter.DEFAULT_SEPARATOR,
						CSVWriter.NO_QUOTE_CHARACTER,
						CSVWriter.DEFAULT_ESCAPE_CHARACTER,
						CSVWriter.DEFAULT_LINE_END);
				csvWriter.writeNext(data.getHeader());
				String[][] dataToSave = data.getData();
				for(int i=0;i<dataToSave.length;i++) {
					csvWriter.writeNext(dataToSave[i]);
				}
			
		}
	}
}
