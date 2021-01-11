package com.app.stock.stockmanagement.data.service;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.app.stock.stockmanagement.data.model.Stock;

@Repository
public interface StockManagementDataService extends MongoRepository<Stock, Integer> {

	public List<Stock> findByStockName(String stockName);
}
