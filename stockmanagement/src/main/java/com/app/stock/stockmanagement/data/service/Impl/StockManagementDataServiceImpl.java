package com.app.stock.stockmanagement.data.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.app.stock.stockmanagement.data.model.Stock;
/**
 * 
 * @author Namisha
 *
 */
public class StockManagementDataServiceImpl {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	/**
	 * 
	 * @param stockName
	 * @return
	 */
	public List<Stock> findByStockName(String stockName) {
		Query query = new Query();
		query.addCriteria(Criteria.where("stock").regex(stockName));
		return mongoTemplate.find(query,Stock.class);
	}

}
