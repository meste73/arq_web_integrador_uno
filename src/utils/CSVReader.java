package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class CSVReader {
	
	public Iterable<CSVRecord> read(String filePath, String[] csvHeader){
		
		try {
			Reader in = new FileReader(filePath);
			CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
					.setHeader(csvHeader)
					.setSkipHeaderRecord(true)
					.build();
			return csvFormat.parse(in);			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
