package com.app.stock.stockmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.stock.stockmanagement.business.service.impl.StockManagementServiceImpl;
import com.app.stock.stockmanagement.config.ReadCSVHelper;
import com.app.stock.stockmanagement.data.model.Stock;
import com.app.stock.stockmanagement.data.service.StockManagementDataService;
import com.app.stock.stockmanagement.exception.ResourceNotFoundException;
import com.app.stock.stockmanagement.gateway.AddStockRequest;
import com.app.stock.stockmanagement.gateway.SearchStockResponse;
import com.app.stock.stockmanagement.gateway.UploadFileResponse;

/**
 * 
 * @author Namisha
 *
 */
@RestController
@RequestMapping("/stockmanagement")
public class StockManagementController {

	private final static Logger logger =LoggerFactory.getLogger(StockManagementController.class);

	@Autowired
	StockManagementServiceImpl stockmngmntService;

	@Autowired
	StockManagementDataService stockManagementDataService;

	/**
	 * 
	 * @param addStockReq
	 * To Add New record in DB
	 */
	@PostMapping("/addStockData")
	public void addStockData(@Validated @RequestBody AddStockRequest addStockReq) {
		logger.info("START-addStockData()");
		if(addStockReq != null) {
			stockmngmntService.addStockData(addStockReq);
		}
	}

	/**
	 * 
	 * @param stock
	 * @return
	 * To retrieve record from DB by stock Name serach criteria
	 * @throws ResourceNotFoundException 
	 */
	@GetMapping(value = "/retrieveStockData/{stock}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<SearchStockResponse>  retrieveStockData(@PathVariable String stock) throws ResourceNotFoundException {
		Optional<List<Stock>> stockData = stockmngmntService.retrieveStockData(stock);
		if(!stockData.isPresent()) {
			throw new ResourceNotFoundException("Data not found for stock" + stock);
		}
		SearchStockResponse searchStockResponse = new SearchStockResponse();
		searchStockResponse.setStocks(stockData.get());

		return new ResponseEntity<SearchStockResponse>(searchStockResponse, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param file
	 * @return
	 * Load file and insert data in DB
	 */
	@PostMapping("/uploadStockData")
	public ResponseEntity<UploadFileResponse> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";

		if (ReadCSVHelper.hasCSVFormat(file)) {
			try {
				stockmngmntService.uploadStockData(file);

				message = "Uploaded the file successfully: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new UploadFileResponse(message));
			} catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new UploadFileResponse(message));
			}
		}

		message = "Please upload a csv file!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UploadFileResponse(message));
	}
}
