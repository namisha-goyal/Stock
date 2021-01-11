package com.app.stock.stockmanagement.gateway;

import java.util.List;

import com.app.stock.stockmanagement.data.model.Stock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchStockResponse {
	
	private List<Stock> stocks;
	
}
