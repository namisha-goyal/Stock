package com.app.stock.stockmanagement.business.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.app.stock.stockmanagement.data.model.Stock;
import com.app.stock.stockmanagement.gateway.AddStockRequest;

public interface StockManagementService {
	
	void addStockData(AddStockRequest addStockReq);

	public Optional<List<Stock>> retrieveStockData(String stock);
	
	public void uploadStockData(MultipartFile file);

}
