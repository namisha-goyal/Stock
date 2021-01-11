package com.app.stock.stockmanagement.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.app.stock.stockmanagement.data.model.Stock;
/**
 * 
 * @author Namisha
 *
 */
public class ReadCSVHelper {
	
	 public static String TYPE = "text/csv";
	  static String[] HEADERs = { "quarter","stock","date","open","high","low","close","volume",
				  "percent_change_price",
				  "percent_change_volume_over_last_wk","previous_weeks_volume",
				  "next_weeks_open","next_weeks_close",
				  "percent_change_next_weeks_price","days_to_next_dividend",
				  "percent_return_next_dividend" };

	  public static boolean hasCSVFormat(MultipartFile file) {

	    if (!TYPE.equals(file.getContentType())) {
	      return false;
	    }

	    return true;
	  }
	  
	  
	  public static List<Stock> readDataFromFile(InputStream is) {
		    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		        CSVParser csvParser = new CSVParser(fileReader,
		            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

		      List<Stock> stocks = new ArrayList<Stock>();

		      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

		      for (CSVRecord csvRecord : csvRecords) {
		    	  int quarter = (null != csvRecord.get("quarter") && !csvRecord.get("quarter").isEmpty()) ? 
		    			  Integer.parseInt(csvRecord.get("quarter")) : null ;
		    	  int volume = (null != csvRecord.get("volume") && !csvRecord.get("volume").isEmpty()) ? 
		    			  Integer.parseInt(csvRecord.get("volume")) : null ;
		    	  Stock stock = new Stock(
		    			  quarter, 
		              csvRecord.get("stock"), 
		              csvRecord.get("date"), 
		              csvRecord.get("open"), 
		              csvRecord.get("high"), 
		              csvRecord.get("low"),
		              csvRecord.get("close"),  
		              volume, 
		              csvRecord.get("percent_change_price"), 
		              csvRecord.get("percent_change_volume_over_last_wk"), 
		              csvRecord.get("previous_weeks_volume"), 
		              csvRecord.get("next_weeks_open"), 
		              csvRecord.get("next_weeks_close"), 
		              csvRecord.get("percent_change_next_weeks_price"), 
		              csvRecord.get("days_to_next_dividend"), csvRecord.get("percent_return_next_dividend")
		            );

		    	  stocks.add(stock);
		      }

		      return stocks;
		    } catch (IOException e) {
		      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		    }
		  }

}
