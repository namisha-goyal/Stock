package com.app.stock.stockmanagement.business.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.stock.stockmanagement.business.service.StockManagementService;
import com.app.stock.stockmanagement.config.ReadCSVHelper;
import com.app.stock.stockmanagement.controller.StockManagementController;
import com.app.stock.stockmanagement.data.model.Stock;
import com.app.stock.stockmanagement.data.service.StockManagementDataService;
import com.app.stock.stockmanagement.gateway.AddStockRequest;

/**
 * 
 * @author Namisha
 *
 */
@Service
public class StockManagementServiceImpl implements StockManagementService {
	
	private final static Logger logger =LoggerFactory.getLogger(StockManagementServiceImpl.class);
	
	@Autowired
	StockManagementDataService stockManagementDataService;
	
	
	@Override
	public void addStockData(AddStockRequest addStockReq) {
		Stock stock = new Stock();
		stock.setClose(addStockReq.getClose());
		stock.setLow(addStockReq.getLow());
		stock.setQuarter(addStockReq.getQuarter());
		stock.setDate(addStockReq.getDate());
		stock.setStock(addStockReq.getStock());
		stock.setDays_to_next_dividend(addStockReq.getDays_to_next_dividend());
		stock.setHigh(addStockReq.getHigh());
		stock.setNext_weeks_close(addStockReq.getNext_weeks_close());
		stock.setNext_weeks_open(addStockReq.getNext_weeks_open());
		stock.setOpen(addStockReq.getOpen());
		stock.setPercent_change_next_weeks_price(addStockReq.getPercent_change_next_weeks_price());
		stock.setPercent_change_price(addStockReq.getPercent_change_price());
		stock.setPercent_change_volume_over_last_wk(addStockReq.getPercent_change_volume_over_last_wk());
		stock.setPercent_return_next_dividend(addStockReq.getPercent_return_next_dividend());
		stock.setPrevious_weeks_volume(addStockReq.getPrevious_weeks_volume());
		stock.setVolume(addStockReq.getVolume());
 		stockManagementDataService.save(stock);
 		logger.info("Stock Data saved successfully");
	}

	@Override
	public Optional<List<Stock>> retrieveStockData(String stock) {
		List<Stock> stocks = stockManagementDataService.findByStockName(stock);
		
		if(CollectionUtils.isEmpty(stocks)) {
			logger.info("Stock Data Not found for" + stock);
			return Optional.empty();
		}
		else {
			logger.info("Stock Data found for" + stock);
			return Optional.of(stocks);
		}
	}

	@Override
	 public void uploadStockData(MultipartFile file) {
		    try {
		      List<Stock> tutorials = ReadCSVHelper.readDataFromFile(file.getInputStream());
		      stockManagementDataService.saveAll(tutorials);
		      logger.info("Stock Data file uploaded successfully");
		    } catch (IOException e) {
		      throw new RuntimeException("fail to store csv data: " + e.getMessage());
		    }
		  }

}
